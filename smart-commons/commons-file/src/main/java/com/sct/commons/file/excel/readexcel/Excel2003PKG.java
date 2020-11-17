package com.sct.commons.file.excel.readexcel;

import java.sql.SQLException;
import java.util.List;


/*Copyright (C) 2014-2015 BOCO,.LTD. All rights reserved.
 *
 * @author 123 create at 2015年5月26日 下午3:36:43
 *
 */

public class Excel2003PKG extends XlsAbstract{
	private HeadCfg headCfg = null;
	private ExcelPKGControl control = new ExcelPKGControl();
	private List<String> timeHeadList = null;

	public void startSheet() {
//		System.out.println("currentSheetIndex:" + getSheetIndex());
		control.processNewSheet(headCfg);
		control.setTimeHeadInfo(this.timeHeadList);
	}

	public void optRows(int sheetIndex, int curRow, List<String> rowlist)
			throws SQLException {
		if(this.headCfg == null){

		}else{
			control.processRow(sheetIndex, curRow, rowlist);
		}
	}

	public List<String> getHeadList(){
		return control.getHeadList();
	};

	public List<List<String>> getExcelValueList(){
		return control.getExcelValueList();
	};

	public void setHeadCfg(HeadCfg headCfg) {
		this.headCfg = headCfg;
	}

	public void setTimeHeadInfo(List<String> timeHeadList){
		this.timeHeadList = timeHeadList;
	};

	@Override
	public void close(){
		super.close();
		if(headCfg != null) headCfg = null;
		control.close();
	}
}
