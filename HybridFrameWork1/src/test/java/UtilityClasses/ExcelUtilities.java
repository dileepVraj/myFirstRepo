package UtilityClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat.Style;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {


	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook book;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	public String path;

	// create an constructor which invoke path of file.
//	public ExcelUtilities(String path)
//	{
//		this.path = path;
//	}


	// get number of rows method

	public int getNumberOfRows(String path, int sheetNumber) throws IOException
	{

		fis = new FileInputStream(path);
		book = new XSSFWorkbook(fis);
		sheet = book.getSheetAt(sheetNumber);
		int rowCount = sheet.getLastRowNum();

		fis.close();
		book.close();

		return rowCount;
	}


	public int getNumberOfCells(String path,int sheetNumber,int rowNumber) throws IOException
	{

		fis = new FileInputStream(path);
		book = new XSSFWorkbook(fis);
		sheet = book.getSheetAt(sheetNumber);
		row = sheet.getRow(rowNumber);
		int cellCount = row.getLastCellNum();

		fis.close();
		book.close();

		return cellCount;
	}


	public String getCellData(String path,int sheetNumber, int rowNumber, int cellNumber) throws IOException
	{

		fis = new FileInputStream(path);
		book = new XSSFWorkbook(fis);
		sheet = book.getSheetAt(sheetNumber);
		row = sheet.getRow(rowNumber);
		cell = row.getCell(cellNumber);

		DataFormatter formatter = new DataFormatter();
		String data;
		try {

			data = formatter.formatCellValue(cell);


		} catch (Exception e) {
			data = "Empty";
		}

		return data;

	}


	public void setCellData(String path,String sheetName, int rowNum, int CellNum, String data) throws IOException
	{

		/*
		 * fis = new FileInputStream(filePath); book = new XSSFWorkbook(fis); sheet =
		 * book.getSheet(sheetName); row = sheet.getRow(rowNum); cell =
		 * row.getCell(CellNum);
		 * 
		 * cell.setCellValue(data);
		 * 
		 * fos = new FileOutputStream(filePath); book.write(fos);
		 * 
		 * book.close(); fis.close(); fos.close();
		 */

		////////////////////////////////////////////////// updated version of setCellData method ///////////////////////////////////

		// to find if a file is present in directory or not have to use FILE class.

		File excelFile = new File(path);
		if(!excelFile.exists())
		{
			book = new XSSFWorkbook();
			fos = new FileOutputStream(path);
			book.write(fos);
		}

		fis = new FileInputStream(path);
		book = new XSSFWorkbook(fis);

		// if workbook dosent got sheet lets create a sheet.
		// note when getSheetIndex method is applied and if sheet is not exist in book then -1 value will be returned.

		if(book.getSheetIndex(sheetName)==-1)
		{
			book.createSheet(sheetName);
		}

		sheet = book.getSheet(sheetName);

		if(sheet.getRow(rowNum)==null)
		{
			sheet.createRow(rowNum);
		}
		row = sheet.getRow(rowNum);

		cell = row.createCell(CellNum);
		cell.setCellValue(data);

		fos = new FileOutputStream(path);
		book.write(fos);
		book.close();






	}





}
