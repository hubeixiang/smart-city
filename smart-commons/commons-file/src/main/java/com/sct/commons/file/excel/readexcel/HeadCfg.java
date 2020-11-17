package com.sct.commons.file.excel.readexcel;

/*Copyright (C) 2014-2015 BOCO,.LTD. All rights reserved.
 *
 * @author 123 create at 2015年3月28日 下午12:26:17
 *
 */

public class HeadCfg {
	//列头所在行号,此行就是列头.excel里面行是从0开始编号
	private int lineNumber = 0;
	//列头从第几列开始读取,excel里面列数是从0开始编号
	private int startColumn = 0;

	public int getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	public int getStartColumn() {
		return startColumn;
	}
	public void setStartColumn(int startColumn) {
		this.startColumn = startColumn;
	}
}
