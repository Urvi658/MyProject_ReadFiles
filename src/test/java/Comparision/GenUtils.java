package Comparision;
import java.io.FileInputStream;
import java.io.FileOutputStream; 
import java.io.IOException;
import java.util.Iterator;
import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row; 
import org.apache.poi.xssf.usermodel.XSSFSheet; 
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 
 

public class GenUtils {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		RetrievePCCDetails("C:\\Users\\uma.pal\\eclipse-workspace\\ComparisionJSONAndXML\\TestData\\PCC.xlsx","E9G");

	}
	
	
	public static void RetrievePCCDetails(String sExcelPath, String sPCC) throws IOException {
		
		// Try block to check for exceptions 
		
		int iPCC = 0;
		
        try { 
		 // Reading file from local directory 	
		 FileInputStream file = new FileInputStream(new File (sExcelPath)); 
		 
		 
		// Blank workbook 
        XSSFWorkbook workbook = new XSSFWorkbook(file); 
  
        // Creating a blank Excel sheet 
        XSSFSheet sheet = workbook.getSheet("Sheet1"); 
        
        
        // Iterate through each rows one by one 
        Iterator<Row> rowIterator = sheet.iterator(); 
        
        while (rowIterator.hasNext()) { 
        	
        	Row row = rowIterator.next(); 
        	
        	Iterator<Cell> cellIterator = row.cellIterator(); 
        	
//        	System.out.print(workbook.getSheet("Sheet1").getRow(0).getCell(iPCC)+ "\t");
        	
        	row.setRowNum(iPCC);
        	System.out.print(row.getCell(0));
        	
        	 while (cellIterator.hasNext()) { 
        		  
                 Cell cell = cellIterator.next(); 

                 // Checking the cell type and format 
                 // accordingly
                 
                 String sCelltype = cell.getCellType().toString();
                 
//                 System.out.println(sCelltype);
                 switch (sCelltype) { 
                 
                 
                 // Case 1 
                 case "NUMERIC": 
                     System.out.print(Math.round(cell.getNumericCellValue())); 
                     
                     break; 

                 // Case 2 
                 case "STRING":
                 
//                     System.out.print(cell.getStringCellValue()+ "\t");
                      
                     break; 
                 } 
                 
                 
                 
             } 
        	 
        	 iPCC++;
        	
        	 System.out.println(""); 
        	
        }
		
        // Closing file output streams 
        file.close(); 
		
	 // Catch block to handle exceptions 
        }  catch (Exception e) { 
  
            // Display the exception along with line number 
            // using printStackTrace() method 
            e.printStackTrace(); 
        } 
	
	}
}
