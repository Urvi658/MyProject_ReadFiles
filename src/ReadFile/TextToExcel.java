package ReadFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.*;

//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class TextToExcel {
//    public static void main(String[] args) {
//        Map<String, String> dataMap = readDataFromText("D:\\Urvi Folder\\Details.txt");
//        writeDataToExcel(dataMap, "output.xlsx");
//    }
//
//    public static Map<String, String> readDataFromText(String filePath) {
//        Map<String, String> dataMap = new HashMap<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(",");
//                if (parts.length == 2) {
//                    String key = parts[0].trim();
//                    String value = parts[1].trim();
//                    dataMap.put(key, value);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return dataMap;
//    }
//
//    public static void writeDataToExcel(Map<String, String> dataMap, String filePath) {
//        try (Workbook workbook = new XSSFWorkbook();
//             FileOutputStream out = new FileOutputStream(filePath)) {
//            Sheet sheet = workbook.createSheet("Sheet1");
//    	
//   
//            System.out.println("Excel file created successfully");
//            int rowNum = 0;
//            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
//                Row row = sheet.createRow(rowNum++);
//
//                Cell keyCell = row.createCell(0);
//                keyCell.setCellValue(entry.getKey());
//
//                Cell valueCell = row.createCell(1);
//                valueCell.setCellValue(entry.getValue());
//            }
//
//            workbook.write(out);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
import org.apache.commons.collections4.ListValuedMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.XmlOptions.BeanMethod;
import org.apache.xmlbeans.XmlOptions.XmlOptionsKeys;

public class TextToExcel {

	public static void main(String[] args) {

	}

	public static void ReadAndWriteExcel(Map<String, List<String>> sHashmap)
			throws IOException, InvalidFormatException {
		String sPath = "D:\\Urvi Folder\\HashMap.xlsx";
		System.out.println(sHashmap);
		File file = new File(sPath);

		FileInputStream ExcelFile = new FileInputStream(sPath);
		XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFile);
		XSSFSheet sheet = ExcelWBook.getSheet("Student Data");
//		XSSFSheet sheet = ExcelWBook.createSheet("Student Data"); 

		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Name");
		headerRow.createCell(1).setCellValue("Age");
		headerRow.createCell(2).setCellValue("Address");

		int rowno = 0;

		for (HashMap.Entry entry : sHashmap.entrySet()) {

			XSSFRow row = sheet.createRow(rowno++);
			row.createCell(0).setCellValue(entry.getKey().toString());
			row.createCell(1).setCellValue(entry.getValue().toString());

			List<String> values = (List<String>) entry.getValue();
			if (values.size() >= 2) {
				String age = values.get(0).replaceAll("age", "");
				String address = values.get(1).replaceAll("add-", "");
				row.createCell(1).setCellValue(age);
				row.createCell(2).setCellValue(address);
			}

			// System.out.println("Your excel file has been generated!");
			for (int i = 0; i < 3; i++) {
				sheet.autoSizeColumn(i);
			}
		}

		FileOutputStream out = new FileOutputStream(new File(sPath));
		ExcelWBook.write(out);
		ExcelWBook.close();
		out.close();
		System.out.println("Your excel file has been generated!");
	}

}

//Row row = sheet.createRow(2);
//Cell cell = row.createCell(0);
//cell.setCellValue("John Smith");

//if (!file.exists()) {
//
//	file.createNewFile();
//}