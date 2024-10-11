package Base_Class_Configuration;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;


public class ExcelUtils_With_DataProvider {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
//	String path=null;
//	
//	void XLUtility(String path){
//		this.path=path;
//	}
	@DataProvider (name="Login_Data_Excel")
	public String [][] getData() throws IOException{
		String path="C:\\Users\\Ram prathees\\Downloads\\ApachiPoi\\ExcelData-ApachiPOi\\ExcelFile\\fortest.xlsx";
	
		ExcelUtils_With_DataProvider xlutil=new ExcelUtils_With_DataProvider();
		int totalrows=xlutil.getRowCount(path, "Sheet1");
		int totalcols=xlutil.getCellCount(path, "Sheet1", 1);
		
		String loginData[][]=new String[totalrows][totalcols];
		for (int i = 1; i <=totalrows; i++) {
			for (int j = 0; j < totalcols; j++) {
			loginData[i-1][j]=ExcelUtils_With_DataProvider.getCellData(path, "Sheet1", i, j);
			}
		}
		return loginData;
	}
	
	public static int getRowCount(String path, String sheetName) throws IOException 
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;	
	}
	
	public static int getCellCount(String path, String sheetName,int rownum) throws IOException {
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}
	
	public static  String getCellData(String path, String sheetName,int rownum, int colnum ) throws IOException {
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		DataFormatter formatter=new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);//Return the formatted value of a cell as a string regardless of the data type.		
		} catch (Exception e) {
			data="";
		}
		workbook.close();
		fi.close();
		return data;
	}

	public static void setCellData(String path,String sheetName,int rownum,int colnum,String data) throws IOException {
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}

	public static void fillGreenColor(String path,String sheetName,int rownum,int colnum) throws IOException {
		
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		style=workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
}
