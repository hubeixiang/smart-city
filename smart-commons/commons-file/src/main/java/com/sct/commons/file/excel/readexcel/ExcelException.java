package com.sct.commons.file.excel.readexcel;

/*Copyright (C) 2014-2015 BOCO,.LTD. All rights reserved.
 *
 * @author 123 create at 2015年5月27日 上午11:01:01
 *
 */

public class ExcelException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private String message = "";
	private Throwable throwable = null;

	public ExcelException(String message){
		super(message);
		this.message = message;
	}

	public ExcelException(String message,Throwable t){
		super(message,t);
		this.message = message;
		this.throwable = t;
	}

	public String getMessageInfo(){
		return message;
	}

	@Override
	public String getMessage(){
		return this.toString();
	}

	public String toString(){
		if(this.throwable == null){
			return this.message;
		}else{
			return this.message + this.throwable.getMessage();
		}
	}
}
