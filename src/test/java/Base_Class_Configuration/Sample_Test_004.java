package Base_Class_Configuration;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class Sample_Test_004 extends Base_Class {
	@Test (dataProvider = "Login_Data_Excel")
	public void excel_Test(String user,String pass, String value) throws IOException {
		//print(readCellDatum("C:\\Users\\Ram prathees\\eclipse-workspace\\Base_Class_Full_Inputs\\Excel File\\fortest.xlsx", 0, 2, 1));
		print(user+pass+value);
	}
	
	
	/* public String readCellData() throws IOException {
		String data="";
		String FilePath="C:\\Users\\Ram prathees\\eclipse-workspace\\Base_Class_Full_Inputs\\Excel File\\fortest.xlsx";
		FileInputStream input=new FileInputStream(FilePath);
		
		XSSFWorkbook workbook=new XSSFWorkbook(input);
		XSSFSheet sheet=workbook.getSheetAt(0); 
		//XSSFSheet sheet =workbook.getSheet("Sheet1");//for sheet Name
	
		for (int i = 0; i<=2; i++) {   
			 XSSFRow row=sheet.getRow(i);
			
			for (int j = 0; j <2; j++) {
				XSSFCell cell=row.getCell(j);	
			}		
			}
		return data;
		}
}	*/
	@DataProvider (name="Login_Data_Excel")
   public String [][] getData() throws IOException{
		String path ="C:\\Users\\Ram prathees\\eclipse-workspace\\Base_Class_Full_Inputs\\Excel File\\fortest.xlsx";
		int startRows=0;
		int totalrows=3;
		int startCols=0;
		int totalcols=3;
		
		
		String loginData[][]=new String[totalrows][totalcols];
		for (int i = startRows; i <totalrows; i++) {
			for (int j = startCols; j < totalcols; j++) {
			loginData[i][j]=getCellData(path, 0, i, j);
			}
		}
		return loginData;
		}
	

}