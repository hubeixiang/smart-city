package com.sct.commons.file.excel.readexcel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*Copyright (C) 2014-2015 BOCO,.LTD. All rights reserved.
 *
 * @author 123 create at 2015年5月27日 上午11:29:13
 *
 */

public class ExcelPKGControl {
	private boolean isNewSheet = true;
	private HeadCfg headCfg = null;
	private List<String> headList = null;
	private DataCfg dataCfg = null;
	private List<List<String>> excelvalues = new ArrayList<List<String>>();
	private boolean isBreak = false;
	private List<String> timeHeadList = null;
	private List<Integer> timeHeadIndex = null;
	//处理新打开的sheet
	public void processNewSheet(HeadCfg headCfg){
		this.isNewSheet = true;
		this.headCfg = headCfg;
	}

	//处理新的行
	public void processRow(int sheetIndex, int curRow, List<String> rowlist){
		if(rowlist == null || rowlist.size()==0 || this.isBreak){
			return;
		}
		if(this.isNewSheet){
			if(this.excelvalues.size() > 0){
				//表明此时已经解析出来了excel的值了
				//现阶段,我们值需要解析出来excel中第一个有用的Sheet就行
				this.isBreak = true;
				return;
			}
			if(this.headCfg.getLineNumber() == curRow){
				//当前行,正好是列头配置中的行
				List<String> headListTmp = new ArrayList<String>();
				//行尾的空格可以忽略,但是行中间如果出现空格需要保留标识
				boolean isNeedPassd = true;
				for (int i = rowlist.size() - 1; i >= this.headCfg.getStartColumn(); i--) {
					//找到列头所在行的,开始计算列
					String value = rowlist.get(i);
					if ((value == null || value.equals("")) && isNeedPassd) {
						continue;
					} else {
						headListTmp.add(value);
						isNeedPassd = false;
					}
				}
				Collections.reverse(headListTmp);
				headList = headListTmp;
				if(headList.size() > 0){
					//找到列头,并生成列头
					this.isNewSheet = false;
					createDataCfg();
					parseTimeColumnIndex();
				}
			}
		}else{
			//没有生成列头行
			if(headList == null) return;
			if(curRow >= this.dataCfg.getStartLine()){
				List<String> rowvalue = new ArrayList<String>();
				boolean isAllNull = true;
				for(int i=this.dataCfg.getStartColumn();i<rowlist.size();i++){
					String value = rowlist.get(i) == null ? "" : rowlist.get(i).trim();
					rowvalue.add(value);
					if(!value.equals("")){
						isAllNull = false;
					}
					if(rowvalue.size() >= this.dataCfg.getTotalColumn()){
						//超出列头的个数,不会获取
						break;
					}
				}
				if(isAllNull){
					rowvalue.clear();
				}
				if(rowvalue.size() > 0 && rowvalue.size() != dataCfg.getTotalColumn()){
					for(int i=rowvalue.size();i<dataCfg.getTotalColumn();i++){
						rowvalue.add("");
					}
				}
				if(rowvalue.size() > 0 && headList.size() == rowvalue.size()){
					parseTimeValue(rowvalue);
					excelvalues.add(rowvalue);
				}
			}
		}
	}

	private void parseTimeColumnIndex(){
		if(timeHeadList == null || timeHeadList.size() == 0 || headList == null || headList.size() == 0){
			return;
		}else{
			timeHeadIndex = new ArrayList<Integer>();
			for(int i=0;i<headList.size();i++){
				if(timeHeadList.contains(headList.get(i))){
					timeHeadIndex.add(i);
				}
			}
		}
	}

	private void parseTimeValue(List<String> rowvalue){
		if(timeHeadIndex != null && timeHeadIndex.size() > 0){
			for(int i : timeHeadIndex){
				if(i>=0 && i<rowvalue.size()){
					String value = rowvalue.get(i);
					if(!value.equals("")){
						try{
							double d = Double.parseDouble(value);
							value = ExcelUtil.getJavaFormatDate(d);
						}catch(RuntimeException re){
							//不能转换为double类型的,就做字符串分析
							value = ExcelUtil.dateStringFormat(value);
						}
						rowvalue.set(i, value);
					}
				}
			}
		}
	}

	public DataCfg createDataCfg(){
		if(headList == null || headList.size() == 0){
			dataCfg = null;
			return null;
		}else{
			dataCfg = new DataCfg();
			dataCfg.setStartLine(headCfg.getLineNumber() + 1);
			dataCfg.setStartColumn(headCfg.getStartColumn());
			dataCfg.setTotalColumn(headList.size());
			return dataCfg;
		}
	}

	public List<String> getHeadList(){
		return headList;
	};

	public List<List<String>> getExcelValueList(){
		return excelvalues;
	};

	public void close(){
		if(headList != null) headList = null;
		if(excelvalues != null) excelvalues = null;
	}

	public void setTimeHeadInfo(List<String> timeHeadList){
		this.timeHeadList = timeHeadList;
	};

	public boolean isNewSheet(){
		return isNewSheet;
	}
}
