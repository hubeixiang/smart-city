package com.sct.webtools.context;

import com.sct.commons.excel.ExcelHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ExcelHelperContext {
	private static Logger logger = LoggerFactory.getLogger(ExcelHelperContext.class);

	private static String XLS_TEMPLATE  = "tmpl/excel-v3.xls";
	private static String XLSX_TEMPLATE = "tmpl/excel-v3.xlsx";
	
	@Bean
	public ExcelHelper createExcelHelper() throws IOException {
		ExcelHelper helper = new ExcelHelper();
		try {
			ClassPathResource xlsTemplateResource  = new ClassPathResource(XLS_TEMPLATE);
			ClassPathResource xlsxTemplateResource = new ClassPathResource(XLSX_TEMPLATE);
			helper.setXlsTemplateResource(xlsTemplateResource);
			helper.setXlsxTemplateResource(xlsxTemplateResource);
			logger.info("init excel helper,xls:{},xlsx:{}", xlsTemplateResource.exists(), xlsxTemplateResource.exists());
			return helper;
		} catch (Exception e) {
			throw e;
		}
	}
	
}
