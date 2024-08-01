package Comparision;
import java.io.FileInputStream;
import java.io.FileOutputStream; 
import java.io.IOException;
import java.util.HashMap;
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
		
//		RetrievePCCDetails("C:\\Users\\uma.pal\\eclipse-workspace\\ComparisionJSONAndXML\\TestData\\PCC.xlsx","E9G");

	}
	
	
	public static void RetrievePCCDetails(String sExcelPath, String sPCC) throws IOException {
		
		// Try block to check for exceptions 
		
		int iPCC = 0;
		int iAccessgrp = 0;
		 
		
        try { 
		 // Reading file from local directory 	
		 FileInputStream file = new FileInputStream(new File (sExcelPath)); 
		 
		 
		// Blank workbook 
        XSSFWorkbook workbook = new XSSFWorkbook(file); 
  
        // Creating a blank Excel sheet 
        XSSFSheet sheet = workbook.getSheet("Sheet1"); 
        
//        System.out.println(sheet.getRow(1).getCell(iPCC));
//        System.out.println(sheet.getRow(0).getLastCellNum());
//        System.out.println(sheet.getRow(0).getPhysicalNumberOfCells());
          int iRowCnt = sheet.getLastRowNum();
          
          for(int i = 0 ; i< iRowCnt; i++) {
        	  
        	  
        	  GlobalVariable.sPCCAndAccessGroupMap.put(sheet.getRow(i+1).getCell(0).toString(),sheet.getRow(i+1).getCell(2).toString());
        	  
        	  
          }
          
          
        
        // Iterate through each rows one by one 
        Iterator<Row> rowIterator = sheet.iterator(); 
        
        while (rowIterator.hasNext()) { 
        	
        	
        	Row row = rowIterator.next(); 
        	Row row2 = rowIterator.next(); 
        	
        	Iterator<Cell> cellIterator = row.cellIterator(); 
        	
//        	System.out.print(workbook.getSheet("Sheet1").getRow(0).getCell(iPCC)+ "\t");
        	
//        	row.setRowNum(iPCC);
//        	System.out.print(row.getCell(0));
//        	row2.setRowNum(iAccessgrp);
//        	System.out.print(row2.getCell(0));
        	
        	 while (cellIterator.hasNext()) { 
        		  
                 Cell cell = cellIterator.next(); 

                 // Checking the cell type and format 
                 // accordingly
                 
                 String sCelltype = cell.getCellType().toString();
                 
//                 System.out.println(sCelltype);
                 switch (sCelltype) { 
                 
                 
                 // Case 1 
                 case "NUMERIC": 
//                     System.out.print(Math.round(cell.getNumericCellValue())); 
                     
                     break; 

                 // Case 2 
                 case "STRING":
                 
//                     System.out.print(cell.getStringCellValue()+ "\t");
                      
                     break; 
                 } 
                 
                 
                 
             } 
        	 
        	 iPCC++;
        	 iAccessgrp++;
        	
        	 System.out.println(""); 
        	
        }
		
        // Closing file output streams 
        file.close(); 
        
//        System.out.println(GlobalVariable.sPCCAndAccessGroupMap);
		
	 // Catch block to handle exceptions 
        }  catch (Exception e) { 
  
            // Display the exception along with line number 
            // using printStackTrace() method 
            e.printStackTrace(); 
        } 
	
	}
}
