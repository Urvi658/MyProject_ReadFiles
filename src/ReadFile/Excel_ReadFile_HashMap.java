package ReadFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_ReadFile_HashMap {

	public static void main(String[] args) {
		ReadExcel();
	}

	public static void ReadExcel() {
		try {
			File file = new File("D:\\Urvi Folder\\ReadFileOfficeWork.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			String sPCC;
			String sAccess;
			
			
  
			 HashMap<String, String> mPCCAccessmap = new HashMap<String, String>();

	            for (int r = 0; r <= sheet.getLastRowNum(); r++) {
	            	
	            	CellType cPCCType = sheet.getRow(r).getCell(0).getCellType();
	            	CellType cAccessType = sheet.getRow(r).getCell(2).getCellType();
	            	
	            	
	            	sPCC = sheet.getRow(r).getCell(0).toString();
		            sAccess = sheet.getRow(r).getCell(2).toString();
		            mPCCAccessmap.put(sPCC, sAccess);
	                
//	                if (cPCCType.toString() == "STRING" && cAccessType.toString() == "STRING") {
//	                	
//	                	 sPCC = sheet.getRow(r).getCell(0).toString();
//			             sAccess = sheet.getRow(r).getCell(2).toString();
//			             mPCCAccessmap.put(sPCC, sAccess);
//	                	
//	                }else if (cPCCType.toString() == "NUMERIC" && cAccessType.toString() == "NUMERIC") {	
//	                	
//	                	sPCC = sheet.getRow(r).getCell(0).toString();
//		                sAccess = sheet.getRow(r).getCell(2).toString();
//		                mPCCAccessmap.put(sPCC, sAccess);
//	                	
//	                }else if (cPCCType.toString() == "STRING" && cAccessType.toString() == "NUMERIC") {	
//	                	
//	                	sPCC = sheet.getRow(r).getCell(0).toString();
//		                sAccess = sheet.getRow(r).getCell(2).toString();
//		                mPCCAccessmap.put(sPCC, sAccess);
//	                	
//	                }else if (cPCCType.toString() == "NUMERIC" && cAccessType.toString() == "STRING") {	
//	                	
//	                	sPCC = sheet.getRow(r).getCell(0).toString();
//		                sAccess = sheet.getRow(r).getCell(2).toString();
//		                mPCCAccessmap.put(sPCC, sAccess);
//	                	
//	                }
					
   
	                   

	                    }
	                
	            System.out.println(mPCCAccessmap);   
			
	            
	            
	            
//                switch (sCellType) {
//				case STRING:
//					
//					 sPCC = sheet.getRow(r).getCell(0).toString();
//		             sAccess = sheet.getRow(r).getCell(2).toString();
//		             System.out.print(sPCC.getStringCellValue()
//		             break;
//					
//				case NUMERIC:
//					
//					sPCC = sheet.getRow(r).getCell(0).toString();
//	                sAccess = sheet.getRow(r).getCell(2).toString();
//
//					break;
//                
//                }
//                               

//                if (keyCell != null && keyCell.getCellType() == CellType.NUMERIC) {
//                    String key = (String) keyCell.getNumericCellValue();
//                    if (valueCell != null && valueCell.getCellType() == CellType.STRING) {
//                        String value = valueCell.getStringCellValue();
//                        map.put( value, value);
//                    }
	            
	            
			Iterator<Row> itr = sheet.iterator();

//			while (itr.hasNext()) {
//				Row row = itr.next();
//				Iterator<Cell> cellIterator = row.cellIterator();
//				
//
//				while (cellIterator.hasNext()) {
//					Cell cell = cellIterator.next();
//					
////					System.out.println(cell.getCellType());
//					
//					switch (cell.getCellType()) {
//					
//					case STRING:
//						System.out.print(cell.getStringCellValue()+"\t");
////						System.out.println("\t") ;
////						System.out.println("\n");
//						break;
//					case NUMERIC:
//						System.out.print(Math.round(cell.getNumericCellValue())+"\t");
////						System.out.println("\t");
//						break;
//
//					}
//				}
//				System.out.println("");
//			}

			
			wb.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}














//case NUMERIC:
//System.out.print(cell.getNumericCellValue()+ "\t");
//break;
//case BOOLEAN:
//System.out.print(cell.getBooleanCellValue()+ "\t");
//break;
//case BLANK:
//System.out.print("[BLANK]\t\t\t");
//break;
//default:
//System.out.print("[OTHER]\t\t\t");