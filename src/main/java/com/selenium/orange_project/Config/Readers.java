package com.selenium.orange_project.Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Readers { 
	
	public static XSSFWorkbook workbook = null;
	public static XSSFSheet worksheet  = null;
	
	
	public static Properties getPropertiesFile() {
		Properties prop  = new Properties();
		try {
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\resources\\config.properties");
			prop.load(ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return prop;
	}
	
	public static Map<String,String> readExcel(int rowNum) throws IOException{
		workbook = new XSSFWorkbook(System.getProperty("user.dir")+"\\resources\\Data.xlsx");
		worksheet = workbook.getSheet("Sheet1");
		DataFormatter formatter = new DataFormatter();
		Map<String,String> data = new HashMap<String, String>();
		XSSFRow header = worksheet.getRow(0);
		XSSFRow datas = worksheet.getRow(rowNum);
		for(int i = 0;i<header.getLastCellNum();i++) {
			String key = formatter.formatCellValue(header.getCell(i));
			String value = formatter.formatCellValue(datas.getCell(i));
			data.put(key, value);
		}
		return data;
		
	}
	
	
	
}