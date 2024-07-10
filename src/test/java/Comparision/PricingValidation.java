package Comparision;

import static org.junit.Assert.assertEquals;
import com.jayway.jsonpath.Configuration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.*;
import com.jayway.jsonpath.Option;
import static com.jayway.jsonpath.JsonPath.*;
import groovy.json.JsonBuilder;
import groovy.json.JsonOutput;
import groovy.json.JsonSlurper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonMappingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.node.TextNode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;

public class PricingValidation {

	public static Map<String, String> sToken = new HashMap<String, String>();
	public static String sFilePath = GlobalVariable.sProjectPath + "\\src\\test\\resources\\Payloads\\Price_Payload\\";
	public static String URL;
	public static final String OBJECT = "Object";
	public static final String FIELD = "field";
	public static String line1;
	public static String line2;
	public static ArrayList<String> IgnoreString = new ArrayList<String>();
	public static String regex = ".*\\s[0-9].[0-9].*";
	public static String regex1 = ".*\\s[0-9][0-9].[0-9].*";
	public static String regex2 = ".*\\s[0-9][0-9][0-9].[0-9].*";
	public static String regex3 = ".*\\s[0-9][0-9][0-9][0-9].[0-9].*";
	SearchValidation sObject;

	
	
	
	public static void validateResultObject(Scenario scenario) throws JsonMappingException, JsonProcessingException {

		String sScenarioName = "<span style=color:white>Comparing:- " + scenario.RequestType.trim()
				+ "-Gen-1 And Gen-3 Responses<br></span><span style = color:white;> </span>";
		new ExtendReportCreation().createStepNodeInstance(sScenarioName);

//		new ExtendReportCreation().createStepNodeInstance("Comparing-"+scenario.RequestType+"-Gen-2 And Gen-3 Responses");

//		def slurper = new groovy.json.JsonSlurper();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode nodeGen2;
		JsonNode nodeGen3;
		Object objGen2;
		Object objGen3;
		String sRequestType = scenario.RequestType;
		boolean areEquals = false;

		try {

			if (scenario.sIgnoreValue == true) {

				IgnoreString.add("HostToken");
				IgnoreString.add("ExpiryDate");
				IgnoreString.add("flightsRefs");
				IgnoreString.add("TermsAndConditionsID");
				IgnoreString.add("termsAndConditionsRef");
				IgnoreString.add("TermsAndConditions");

			}

			for (int i = 0; i < scenario.sIgnoredata.size(); i++) {

				IgnoreString.add(scenario.sIgnoredata.get(i));

			}

//			System.out.println("The ignore data is" + scenario.sIgnoretags);
//			System.out.println(IgnoreString);

			if (!sRequestType.contains("NextLeg")) {

				objGen2 = scenario.Response_Gen2;
				objGen3 = scenario.Response_Gen3;

//				System.out.println("objGen2"+objGen2);
//				System.out.println("objGen3"+objGen3);

				nodeGen2 = mapper.readTree(objGen2.toString());
				nodeGen3 = mapper.readTree(objGen3.toString());

			} else {

				objGen2 = scenario.Response_Gen2_NextLeg;
				objGen3 = scenario.Response_Gen3_NextLeg;
				nodeGen2 = mapper.readTree(objGen2.toString());
				nodeGen3 = mapper.readTree(objGen3.toString());

			}

			if (nodeGen2.equals(nodeGen3)) {

				areEquals = true;
				ExtendReportCreation.addPassAndFailValidation(areEquals, "Gen-2 And Gen-3 responses are identical.");
				System.out.println("Gen-2 And Gen-3 responses are identical.");

			} else if (scenario.sIgnoreValue == true && areEquals == false) {

				ExtendReportCreation.addPassAndFailValidation(areEquals,
						"Gen-2 And Gen-3 may not be identical. As there are tags that are being skipped");

			} else {

//				areEquals = false;
				ExtendReportCreation.addPassAndFailValidation(areEquals,
						"Gen-2 And Gen-3 responses are not identical. Below are the differences.");

			}

			if (areEquals == false) {

				String ReqResGen2 = JsonOutput.prettyPrint(objGen2.toString());
				String ReqResGen3 = JsonOutput.prettyPrint(objGen3.toString());
				Scanner reader1 = new Scanner(ReqResGen2);
				Scanner reader2 = new Scanner(ReqResGen3);

//				System.out.println(ReqResGen2);

//				Scanner reader1 = new Scanner(new FileReader("C:\\Users\\uma.pal\\eclipse-workspace\\VersionComparision\\Extents\\NDC-FAREFAMILYSEARCH-ONEWAY_2PTC_01_03_2024_080922\\NDC-Search\\GEN-1_ReqRes01_03_2024_080928340.json"));
//				Scanner reader2 = new Scanner(new FileReader("C:\\Users\\uma.pal\\eclipse-workspace\\VersionComparision\\Extents\\NDC-FAREFAMILYSEARCH-ONEWAY_2PTC_01_03_2024_080922\\NDC-Search\\GEN-3_ReqRes01_03_2024_080931944.json"));

				line1 = reader1.nextLine();
				line2 = reader2.nextLine();

////		    String[] Gen2 = ReqResGen2.split(System.getProperty("line.separator"));
//	//	        String[] Gen3 = ReqResGen3.split(System.getProperty("line.separator"));

				int lineNumber = 1;

//				while (line1 != null || line2 != null) {

				while (reader1.hasNextLine() || reader2.hasNextLine()) {

//					System.out.println("line1" + line1);
//					System.out.println("line2" + line2);

					if (line1 != null && line2 == null) {

						areEquals = false;

						String Diff = "<span style=color:yellow> -----line no. :- " + lineNumber + "------"
								+ "<br> Gen-1 :- " + line1.trim()
								+ "<br></span><span style = color:white;> Gen-3 :- </span>";

						ExtendReportCreation.addPassAndFailComment(areEquals, Diff);

					} else if (line1 == null && line2 != null) {

						areEquals = false;

						String Diff = "<span style=color:yellow> -----line no. :- " + lineNumber + "------"
								+ "<br> Gen-1 :- <br></span><span style = color:white;> Gen-3 :- " + line2.trim()
								+ "</span>";

						ExtendReportCreation.addPassAndFailComment(areEquals, Diff);

					} else if (line1 == null && line2 == null) {

						break;

					} else if (CompareToIgnore(line1.trim()) == true || CompareToIgnore(line2.trim()) == true) {

//						System.out.println(line1);
//						System.out.println(line2);

					} else if (!line1.trim().equalsIgnoreCase(line2.trim())) {

						areEquals = false;

//						 System.out.println("line :- "+lineNumber + ":"+ line1.trim() +" : "+ line2.trim());

						String Diff = "<span style=color:yellow> -----line no. :- " + lineNumber + "------"
								+ "<br> Gen-1 :- " + line1.trim() + "<br></span><span style = color:white;> Gen-3 :- "
								+ line2.trim() + "</span>";

						ExtendReportCreation.addPassAndFailComment(areEquals, Diff);

					}

					String Previousline1 = line1;
					String Previousline2 = line2;

					line1 = null;
					line2 = null;

					boolean sExistLine1 = false;
					boolean sExistLine2 = false;

					String sIgnoreTag = null;

					if (reader1.hasNextLine()) {

						if (Scenario.sSkiptagsValue == true) {

//							sIgnoreTag = Scenario.sSkiptags.get(0);

							if (Previousline1 != null) {

//								if (Previousline1.contains(sIgnoreTag)) {
								if (SkipTag(Previousline1)) {

									sExistLine1 = true;
//									System.out.println("Line1 is true");

								}

							}

							if (Previousline2 != null) {

//								if (Previousline2.contains(sIgnoreTag)) {
								if (SkipTag(Previousline2)) {

									sExistLine2 = true;
//									System.out.println("Line 2 is true");

								}
							}

						}

						if (sExistLine2 == true && sExistLine1 == false) {

							line1 = Previousline1;
							System.out.println("entering line 1" + line1);
							sExistLine2 = false;
//						    lineNumber--;

						} else {
//						} else if(sExistLine2 == sExistLine1) {

							line1 = reader1.nextLine();
						}

					}

					if (reader2.hasNextLine()) {

						if (sExistLine1 == true && sExistLine2 == false) {

							line2 = Previousline2;
							System.out.println("entering line 2" + line2);
							sExistLine1 = false;
//						    lineNumber--;

						} else {
//						} else if(sExistLine1 == sExistLine2) {

							line2 = reader2.nextLine();
						}

					}

					lineNumber++;

				}

				reader1.close();
				reader2.close();
			}

			scenario.Response_Gen2 = null;
			scenario.Response_Gen3 = null;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static boolean SkipTag(String Previousline1) {

//		String sIgnoreTag = null; 

		for (int i = 0; i < Scenario.sSkiptags.size(); i++) {

			String sIgnoreTag = Scenario.sSkiptags.get(i);

			if (Previousline1.contains(sIgnoreTag)) {

				return true;

			}

		}

		return false;

	}

	public static boolean CompareToIgnore(String IgnoreStr) {

		boolean matched = false;

		Scenario scenario = GlobalVariable.objScenarioData;

		// String[] IgnoreString = ['ExpiryDate', 'TermsAndConditions', 'flightsRefs',
		// 'TermsAndConditionsID', 'termsAndConditionsRef', 'HostToken']
		// println(line1+line2)

		for (String str : IgnoreString) {

			str = str.toUpperCase().strip();
			IgnoreStr = IgnoreStr.toUpperCase().strip();

//			System.out.println(str);
//			System.out.println(IgnoreStr);

//			if(IgnoreStr.contains("brand")) {
//				
//				System.out.println(IgnoreStr);
//			}

			// if(IgnoreStr.contains(str) && IgnoreStr.length() > 45 ) {
			if ((IgnoreStr.contains(str)) || (IgnoreStr.length() > 40) && (!IgnoreStr.contains("DESCRIPTION"))) {

//				System.out.println("IgnoreStr"+IgnoreStr);
				matched = true;
				return matched;

			} else if (str.contains("IGNOREDECIMALZERO")) {
//			} else if (str.contains("IGNOREDECIMALZERO") && (line1.trim().matches(regex) || line1.trim().matches(regex1)
//					|| line1.trim().matches(regex2) || line1.trim().matches(regex3))) {

//				 System.out.println("line1"+line1);
//				 System.out.println("line2"+line2);

				matched = IgnoreDecimalZero(line1, line2);

				// println(matched)

				return matched;

			}

		}

		return matched;

	}

	public static boolean IgnoreDecimalZero(String sFirst, String sSecond) {

//		 System.out.println("sFirst"+sFirst);
//		 System.out.println("sSecond"+sSecond);

		String sAddLastDigit;

		if (((sFirst.length() + 1) == sSecond.length()) || (sFirst.length() == (sSecond.length() + 1))) {

			if (sFirst.length() > sSecond.length()) {

				// sAddLastDigit = sFirst.substring(sFirst.length() - 1, sFirst.length())
				// sSecond = (sSecond + sAddLastDigit).trim()

				if (sSecond.contains(",")) {

					sSecond = sSecond.replace(",", "");
					sSecond = (sSecond + "0,").trim();
					// println(sSecond)

				} else {

					sSecond = (sSecond + "0").trim();
					// println(sSecond)

				}

				// println(sFirst == sSecond)

			} else {

				// sAddLastDigit = sSecond.substring(sSecond.length() - 1, sSecond.length())
				// sFirst = (sFirst + sAddLastDigit).trim()

				if (sFirst.contains(",")) {

					sFirst = sFirst.replace(",", "");
					sFirst = (sFirst + "0,").trim();
					// println(sFirst)

				} else {

					sFirst = (sFirst + "0").trim();
					// println(sFirst)

				}

				// println(sFirst)
				// println(sSecond)
				// println(sFirst.trim() == sSecond.trim())

			}
		}

		return (sFirst.trim() == sSecond.trim());

	}

	public int compare(JsonNode node1, JsonNode node2) {
		// compare node1 and node2 by using equals() method
		if (node1.equals(node2)) {
			return 0;
		}

		// check whether node1 and node2 is of type TextNode
		if ((node1 instanceof TextNode) && (node2 instanceof TextNode)) {

			// get JsonNode value as String
			String value1 = ((TextNode) node1).asText();
			String value2 = ((TextNode) node2).asText();

			// use compareTo() method to compare values
			if (value1.equalsIgnoreCase(value2)) {
				return 0;
			}
		}

		return 1;
	}

	public static void Comparision() {

		BufferedReader b1;
		BufferedReader b2;

//	 list_file1 = new HashSet<>();
		List<String> list_file1 = new ArrayList<>();

		String lineText = null;
		int linenumber = 1;

		boolean sExist = false;

		try {

			b1 = new BufferedReader(new FileReader("C:\\TestData\\NDC\\Search-Gen3.json"));
			while ((lineText = b1.readLine()) != null) {

				list_file1.add(lineText);

			}

			b2 = new BufferedReader(new FileReader("C:\\TestData\\NDC\\Search-Gen1.json"));

			while ((lineText = b2.readLine()) != null) {

				if (!list_file1.contains(lineText)) {

//                 System.out.println("Line no."+linenumber+" Not Matches with" + lineText);

				} else if (CompareToIgnore(list_file1.get(linenumber).trim()) == true
						|| CompareToIgnore(lineText.trim()) == true) {

				}

				linenumber++;

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Object RemoveTagsAndRows(Object sResponse, ArrayList<String> sTags) throws FileNotFoundException {
//	public static Object RemoveTagsAndRows(String sResponse, ArrayList<String> sTags) throws FileNotFoundException {

		Scenario scenario = GlobalVariable.objScenarioData;
		JsonOutput jsonOutput = new JsonOutput();
		String JSONFileString = null;

		String sTag = null;
		ObjectMapper mapper = new ObjectMapper();
		JsonSlurper newparser = new JsonSlurper();
		Object jsonResp;
		DocumentContext sUpdatedResponse = null;
		JSONParser jsonparser = new JSONParser();

		try {

//			String sUpdatedResponseFile = GlobalVariable.sProjectPath+"\\"+scenario.version+"UpdatedResponseFile.json";
//			jsonResp = newparser.parse(new FileReader(sResponse)); //sResponse is path of json file, comment this line when executing end to end flow and not getting response from json file

			jsonResp = sResponse; // activate this line when exeucting end to end flow
			jsonResp = jsonparser.parse((String) jsonResp);
			JSONFileString = new JsonBuilder(jsonResp).toString();

			for (int i = 0; i < sTags.size(); i++) {

				Configuration conf = Configuration.builder().options(Option.AS_PATH_LIST).build();
//			    List<String> pathList = using(conf).parse(JSONFileString).read("$..SearchModifiersAir");
				List<String> pathList = using(conf).parse(JSONFileString).read("$.." + sTags.get(i));

				for (String path : pathList) {

//        	    System.out.println(path);

					if (path.contains("$")) {

						path = path.replace("$", "");
					}

					if (path.contains("[")) {

						path = path.replace("[", ".");

					}

					if (path.contains("]")) {

						path = path.replace("]", "");

					}

					if (path.startsWith(".")) {

						path = path.replaceFirst(".", "");

					}

					if (path.contains("'")) {

//					path = path.replace("'", "\"");
						path = path.replace("'", "");

					}

					sTag = path;

//			System.out.println(sTag);

					boolean isTagAvailable = JsonPath.parse(JSONFileString).jsonString().contains(sTags.get(i));

//			DocumentContext sUpdatedJson = JsonPath.parse(JSONFileString)
//					.delete("CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchModifiersAir");

					if (isTagAvailable) {

						sUpdatedResponse = JsonPath.parse(JSONFileString).delete(sTag);

						JSONFileString = sUpdatedResponse.jsonString();

					}

				}

			}

//			WriteFile(sUpdatedResponseFile,jsonOutput.prettyPrint(sUpdatedResponse.jsonString()));

//			System.out.println(jsonOutput.prettyPrint(sUpdatedResponse.jsonString()));
//
//			System.out.println(JSONFileString);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		jsonResp = JSONFileString;

		return jsonResp;

	}

	public static void WriteFile(String sFileName, String UpdatedResponse) {

		try {

			File file = new File(sFileName);
			FileWriter fw = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.write(UpdatedResponse);
			writer.flush();
			writer.close();
			fw.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	

}
