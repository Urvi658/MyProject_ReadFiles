package ReadFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class XMLResponse {

	public static void main(String[] args) throws IOException {
		XMLpractice();
	}

	public static void XMLpractice() throws IOException {
		//String line;
		String ipath="D:\\Urvi Folder\\XML Sample.xml";
//		BufferedReader br = new BufferedReader(new FileReader("D:\\Urvi Folder\\XML Sample.xml"));
//
//		while ((line = br.readLine()) != null) {
//			System.out.println(br.readLine());
//	    }
//	    br.close();
//		try (BufferedReader br = new BufferedReader(new FileReader(ipath))) {
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
		try (BufferedReader br = new BufferedReader(new FileReader(ipath))) {
            String line;
            StringBuilder xmlContent = new StringBuilder();
            while ((line = br.readLine()) != null) {
               System.out.println(line);
//            	xmlContent.append(line).append("\n");
            }
            
            String xml = xmlContent.toString();
            
           
        }
    }
    
    
    }
	    
	
		
		
		
		
		
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



