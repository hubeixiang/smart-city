package com.sct.commons.file.excel.readexcel;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

/*Copyright (C) 2014-2015 BOCO,.LTD. All rights reserved.
 *
 * @author 123 create at 2015年5月27日 上午10:53:14
 *
 */

public interface IExcelPKG {
	//设置新Sheet的列头信息读取方式
	public void setHeadCfg(HeadCfg headCfg);
	//设置为时间类型的字段
	public void setTimeHeadInfo(List<String> timeHeadList);
	public void process(String fileName) throws Exception ;
	public void process(InputStream excelio) throws Exception ;
	//excel记录行操作方法，以sheet索引，行索引和行元素列表为参数，对sheet的一行元素进行操作，元素为String类型
	public void optRows(int sheetIndex, int curRow, List<String> rowlist) throws SQLException;
	//每打开一个sheet时需要处理的调用的方法
	public void startSheet();
	//关闭打开的信息,并清理占用的内存
	public void close();

	public List<String> getHeadList();

	public List<List<String>> getExcelValueList();
}
