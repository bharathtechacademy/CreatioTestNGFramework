package com.framework.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {	
	
	public static String [][] readData (String filePath ,String sheetName){
		String [][] data = null;		
		try {
			FileInputStream fis = new FileInputStream(filePath);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet(sheetName);
			int totalRows = sh.getPhysicalNumberOfRows();
			int totalColumns = sh.getRow(0).getPhysicalNumberOfCells();
			data = new String [totalRows-1][totalColumns];			
			for(int r=1;r<totalRows;r++) {
				for(int c=0;c<totalColumns;c++) {
					data[r-1][c] = sh.getRow(r).getCell(c).getStringCellValue();
				}
			}			
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return data;
	}

}
