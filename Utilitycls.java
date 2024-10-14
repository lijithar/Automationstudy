package POC;


import java.io.IOException;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class Utilitycls {
	
	@DataProvider(name="logindata")
	public String[][] getdata() throws IOException 
	{
		XSSFWorkbook workbook = new XSSFWorkbook("C:/Users/lijithar/Downloads/Book 18.xlsx");
		XSSFSheet sheet= workbook.getSheet("Sheet1");
		int rowcount=sheet.getPhysicalNumberOfRows();
		//System.out.println("row="+rowcount);
		int colcount=sheet.getRow(0).getLastCellNum();
		//System.out.println("col="+colcount);
		
		String[][] data= new String[rowcount-1][colcount]; 
		for(int i=0;i<rowcount-1;i++)
		{
			for(int j=0;j<colcount;j++)
			{ 
				DataFormatter df=new DataFormatter();
				data[i][j]=df.formatCellValue(sheet.getRow(i+1).getCell(j));
				
			}
			
		}
		
		workbook.close();
		return data;
	  }
}




