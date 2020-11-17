package com.sct.commons.file.excel.readexcel;

import org.apache.poi.hssf.eventusermodel.EventWorkbookBuilder.SheetRecordCollectingListener;
import org.apache.poi.hssf.eventusermodel.FormatTrackingHSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.hssf.eventusermodel.MissingRecordAwareHSSFListener;
import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.hssf.eventusermodel.dummyrecord.MissingCellDummyRecord;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.record.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*Copyright (C) 2014-2015 BOCO,.LTD. All rights reserved.
 *
 * @author 123 create at 2015年5月26日 下午5:18:59
 *
 */

public abstract class XlsAbstract implements HSSFListener,IExcelPKG {
	private static Logger logger = LoggerFactory.getLogger(XlsAbstract.class);
	private int minColumns = -1;
	private POIFSFileSystem fs;
	private int lastRowNumber;
	private int lastColumnNumber;
	/** Should we output the formula, or the value it has? */
	private boolean outputFormulaValues = true;
	/** For parsing Formulas */
	private SheetRecordCollectingListener workbookBuildingListener;
	// excel2003 工作薄
	private HSSFWorkbook stubWorkbook;
	// Records we pick up as we process
	private SSTRecord sstRecord;
	private FormatTrackingHSSFListener formatListener;
	// 表索引
	private int sheetIndex = -1;
	private BoundSheetRecord[] orderedBSRs;
	@SuppressWarnings("rawtypes")
	private ArrayList boundSheetRecords = new ArrayList();
	// For handling formulas with string results
	private int nextRow;
	private int nextColumn;
	private boolean outputNextStringRecord;
	// 当前行
	private int curRow = 0;
	// 存储行记录的容器
	private List<String> rowlist = new ArrayList<String>();
	@SuppressWarnings("unused")
	private String sheetName;

	/**
	 * 遍历 excel 下所有的 sheet
	 * @throws IOException
	 */
	public void process(String fileName) throws IOException {
		this.fs = new POIFSFileSystem(new FileInputStream(fileName));
		processPOIFSFileSystem();
	}

	public void process(InputStream excelio) throws IOException{
		this.fs = new POIFSFileSystem(excelio);
		processPOIFSFileSystem();
	}

	private void processPOIFSFileSystem() throws IOException{
		MissingRecordAwareHSSFListener listener = new MissingRecordAwareHSSFListener(this);
		formatListener = new FormatTrackingHSSFListener(listener);
		HSSFEventFactory factory = new HSSFEventFactory();
		HSSFRequest request = new HSSFRequest();
		if (outputFormulaValues) {
			request.addListenerForAllRecords(formatListener);
		} else {
			workbookBuildingListener = new SheetRecordCollectingListener(formatListener);
			request.addListenerForAllRecords(workbookBuildingListener);
		}
		factory.processWorkbookEvents(request, this.fs);
	}

	/**
	 * HSSFListener 监听方法，处理
	 * Record
	 */
	@SuppressWarnings("unchecked")
	public void processRecord(Record record) {
		int thisRow = -1;
		int thisColumn = -1;
		String thisStr = null;
		String value = null;
		switch (record.getSid()) {
			case BoundSheetRecord.sid:
				boundSheetRecords.add(record);
				break;
			case BOFRecord.sid:
				BOFRecord br = (BOFRecord) record;
				if (br.getType() == BOFRecord.TYPE_WORKSHEET) {
					// 如果有需要，则建立子工作薄
					if (workbookBuildingListener != null && stubWorkbook == null) {
						stubWorkbook = workbookBuildingListener.getStubHSSFWorkbook();
					}
					sheetIndex++;
					if (orderedBSRs == null) {
						orderedBSRs = BoundSheetRecord.orderByBofPosition(boundSheetRecords);
					}
					sheetName = orderedBSRs[sheetIndex].getSheetname();
					startSheet();
				}
				break;
			case SSTRecord.sid:
				sstRecord = (SSTRecord) record;
				break;
			case BlankRecord.sid:
				BlankRecord brec = (BlankRecord) record;
				thisRow = brec.getRow();
				thisColumn = brec.getColumn();
				thisStr = "";
				rowlist.add(thisColumn, thisStr);
				break;
			case BoolErrRecord.sid: //
				// 单元格为布尔类型
				BoolErrRecord berec = (BoolErrRecord) record;
				thisRow = berec.getRow();
				thisColumn = berec.getColumn();
				thisStr = berec.getBooleanValue() + "";
				rowlist.add(thisColumn, thisStr);
				break;
			case FormulaRecord.sid: //
				// 单元格为公式类型
				FormulaRecord frec = (FormulaRecord) record;
				thisRow = frec.getRow();
				thisColumn = frec.getColumn();
				if (outputFormulaValues) {
					if (Double.isNaN(frec.getValue())) {
						// Formula result is a string
						// This is stored in the next record
						outputNextStringRecord = true;
						nextRow = frec.getRow();
						nextColumn = frec.getColumn();
					} else {
						thisStr = formatListener.formatNumberDateCell(frec);
					}
				} else {
					thisStr = '"' + HSSFFormulaParser.toFormulaString(stubWorkbook, frec.getParsedExpression()) + '"';
				}
				rowlist.add(thisColumn, thisStr);
				break;
			case StringRecord.sid://
				// 单元格中公式的字符串
				if (outputNextStringRecord) {
					// String for formula
					StringRecord srec = (StringRecord) record;
					thisStr = srec.getString();
					thisRow = nextRow;
					thisColumn = nextColumn;
					outputNextStringRecord = false;
				}
				break;
			case LabelRecord.sid:
				LabelRecord lrec = (LabelRecord) record;
				curRow = thisRow = lrec.getRow();
				thisColumn = lrec.getColumn();
				value = lrec.getValue().trim();
				value = processEmptyString(value);
				this.rowlist.add(thisColumn, value);
				break;
			case LabelSSTRecord.sid:
				// 单元格为字符串类型
				LabelSSTRecord lsrec = (LabelSSTRecord) record;
				curRow = thisRow = lsrec.getRow();
				thisColumn = lsrec.getColumn();
				if (sstRecord == null) {
					rowlist.add(thisColumn, "");
				} else {
					value =	sstRecord.getString(lsrec.getSSTIndex()).toString().trim();
					value = processEmptyString(value);
					rowlist.add(thisColumn, value);
				}
				break;
			case NumberRecord.sid:
				// 单元格为数字类型
				NumberRecord numrec = (NumberRecord) record;
				curRow = thisRow = numrec.getRow();
				thisColumn = numrec.getColumn();
				value = formatListener.formatNumberDateCell(numrec).trim();
				value = processEmptyString(value);
				// 向容器加入列值
				rowlist.add(thisColumn, value);
				break;
			default:
				break;
		}
		// 遇到新行的操作
		if (thisRow != -1 && thisRow != lastRowNumber) {
			lastColumnNumber = -1;
		}

		// 空值的操作
		if (record instanceof MissingCellDummyRecord) {
			MissingCellDummyRecord mc = (MissingCellDummyRecord) record;
			curRow = thisRow = mc.getRow();
			thisColumn = mc.getColumn();
			rowlist.add(thisColumn, "");
		}

		// 更新行和列的值
		if (thisRow > -1)
			lastRowNumber = thisRow;
		if (thisColumn > -1)
			lastColumnNumber = thisColumn;
		// 行结束时的操作
		if (record instanceof LastCellOfRowDummyRecord) {
			if (minColumns > 0) {
				// 列值重新置空
				if (lastColumnNumber == -1) {
					lastColumnNumber = 0;
				}
			}
			lastColumnNumber = -1;
			// 每行结束时， 调用 getRows() 方法
//			rowReader.getRows(sheetIndex, curRow, rowlist);
			try {
				optRows(sheetIndex, curRow, rowlist);
			} catch (SQLException e) {
				logger.error("file line index=["+curRow+"] exception",e);
			}
			// 清空容器
			rowlist.clear();
		}
	}
	public int getSheetIndex() {
		return sheetIndex;
	}
	private String processEmptyString(String value){
		return value.equals("") ? "" : value;
	}

	public void close(){
		if(fs != null) fs = null;
		if(workbookBuildingListener != null) workbookBuildingListener = null;
		if(stubWorkbook != null) stubWorkbook = null;
		if(sstRecord != null) sstRecord = null;
		if(formatListener != null) formatListener = null;
		if(orderedBSRs != null) orderedBSRs = null;
		if(boundSheetRecords != null) {boundSheetRecords.clear(); boundSheetRecords = null;}
		if(rowlist != null) {rowlist.clear();rowlist=null;}
	}
}
