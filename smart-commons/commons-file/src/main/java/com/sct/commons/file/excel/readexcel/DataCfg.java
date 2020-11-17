package com.sct.commons.file.excel.readexcel;

/*Copyright (C) 2014-2015 BOCO,.LTD. All rights reserved.
 *
 * @author 123 create at 2015年3月28日 下午12:26:35
 *
 */

public class DataCfg {
	//数据开始行,此行就是数据,excel中编号是从0开始的
	private int startLine = 0;
	//数据开始列,此列就是数据,excel中编号是从0开始的
	private int startColumn = 0;
	//读取数据时需要读取多少列才算读取完成,默认读取一列,值为-1时,表明需要将整行都读取,直到没有值
	private int totalColumn =1;

	public int getStartLine() {
		return startLine;
	}
	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}
	public int getStartColumn() {
		return startColumn;
	}
	public void setStartColumn(int startColumn) {
		this.startColumn = startColumn;
	}
	public int getTotalColumn() {
		return totalColumn;
	}
	public void setTotalColumn(int totalColumn) {
		this.totalColumn = totalColumn;
	}

	/**
	 * 获取excel中需要读取的列编号数组,返回为null时,表明整行全读取,直到没有数据
	 * author:123 create at 2015年5月26日 下午5:11:27
	 * @return
	 */
	public int[] getColumnIndexArray(){
		int[] array = null;
		if(this.totalColumn > 0){
			array = new int[totalColumn];
			for(int i=0;i<totalColumn;i++){
				array[i] = startColumn + i;
			}
		}
		return array;
	}
}
