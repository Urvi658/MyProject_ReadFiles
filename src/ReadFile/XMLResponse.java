package ReadFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xmlbeans.impl.inst2xsd.util.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLResponse {
	public static final String Filename = "D:\\Urvi Folder\\sample.xml";
	public static final String tagList = ("query");
	public static void main(String[] args) throws Exception {
			
		
		try {
			
			ArrayList arraylist = new ArrayList();
			arraylist.add("ns1:networkCode");
			arraylist.add("query");
//			XMLpractice();
			 getFullNameFromXml("response", arraylist);
//			 getFullNameFromXml("response", "query");
		} catch (IOException e) {
			System.err.println("An IOException was caught: " + e.getMessage());
		}
	}

	 public static void XMLpractice() throws IOException {
	        String ipath = "D:\\Urvi Folder\\sample.xml";

	        try (BufferedReader br = new BufferedReader(new FileReader(ipath))) {
	            StringBuilder xmlContent = new StringBuilder();
	            String line;
	            while ((line = br.readLine()) != null) {
	                xmlContent.append(line).append("\n");
	            }

	            String xml = xmlContent.toString();


	            // Extract query
	            Pattern queryPattern = Pattern.compile("<query>([^<]+)</query>");
	            Matcher queryMatcher = queryPattern.matcher(xml);
	            if (queryMatcher.find()) {
	                System.out.println("Query: " + queryMatcher.group(1));
	            }

	            // Extract adUnitCode
	            Pattern adUnitCodePattern = Pattern.compile("<adUnitCode>([^<]+)</adUnitCode>");
	            Matcher adUnitCodeMatcher = adUnitCodePattern.matcher(xml);
	            if (adUnitCodeMatcher.find()) {
	                System.out.println("Ad Unit Code: " + adUnitCodeMatcher.group(1));
	            }
	        }
	    }
	 
	 
	 public static Document loadXMLString(String response) throws Exception
	 {
		 String ipath = "D:\\Urvi Folder\\sample.xml";
		 File xmlFile = new File(ipath);    
		 FileReader fileReader = new FileReader(xmlFile);
		 BufferedReader bufReader = new BufferedReader(fileReader);    
		 StringBuilder sb = new StringBuilder();    
		 String line = bufReader.readLine();    
		 while (line != null) {    
		 sb.append(line).append("\n");    
		 line = bufReader.readLine();    
		 }    
		 String xml2String = sb.toString();           
		 bufReader.close();

		 
	     DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();
	     DocumentBuilder db = dbf.newDocumentBuilder();
	     InputSource is = new InputSource(new StringReader(xml2String));

	     return db.parse(is);
	 }

	 public static List<String> getFullNameFromXml(String response, ArrayList arraylist) throws Exception {
	     Document xmlDoc = loadXMLString(response);
		List<String> ids = null;
	     
	     for(int j =0; j<arraylist.size();j++) {
	     NodeList nodeList = xmlDoc.getElementsByTagName(arraylist.get(j).toString());
	     ids = new ArrayList<String>(nodeList.getLength());
	     for(int i=0;i<nodeList.getLength(); i++) {
	         Node x = nodeList.item(i);
	         ids.add(x.getFirstChild().getNodeValue());             
	         System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
	     }
	     
	 }
	     return ids;
	     
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	}

