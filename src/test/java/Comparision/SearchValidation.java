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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.*;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.ReadContext;

import static com.jayway.jsonpath.JsonPath.*;
import groovy.json.JsonBuilder;
import groovy.json.JsonOutput;
import groovy.json.JsonSlurper;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.custommonkey.xmlunit.XMLAssert;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonMappingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.node.TextNode;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.flipkart.zjsonpatch.JsonDiff;

import com.google.gson.stream.JsonReader;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.skyscreamer.jsonassert.JSONCompare;
import com.aventstack.extentreports.Status;
 
public class SearchValidation {

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

//			    io.cucumber.core.internal.com.fasterxml.jackson.core.JsonFactory factory = mapper.getJsonFactory();
//			    io.cucumber.core.internal.com.fasterxml.jackson.core.JsonParser parser = factory.createParser(sRequestType);
//			    JsonNode actualObj = mapper.readTree(parser);

				nodeGen2 = mapper.readTree(objGen2.toString());
				nodeGen3 = mapper.readTree(objGen3.toString());
//				JsonNode patch = JsonDiff.asJson(nodeGen2, nodeGen3 );
//				String diffs = patch.toString();

			} else {

				objGen2 = scenario.Response_Gen2_NextLeg;
				objGen3 = scenario.Response_Gen3_NextLeg;
				nodeGen2 = mapper.readTree(objGen2.toString());
				nodeGen3 = mapper.readTree(objGen3.toString());
//				nodeGen2 = mapper.readValue(objGen2.toString());

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
//				XMLAssert.assertXMLEqual(sScenarioName, sRequestType);
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
	
	
	

	public static void JsonComparision(Scenario scenario) {

		IgnoreString.add("HostToken");
		IgnoreString.add("ExpiryDate");
		IgnoreString.add("flightsRefs");
		IgnoreString.add("TermsAndConditionsID");
		IgnoreString.add("termsAndConditionsRef");
		IgnoreString.add("TermsAndConditions");
		IgnoreString.add("transactionId");
		IgnoreString.add("refresh_token:");
		IgnoreString.add("Identifier");
		IgnoreString.add("OfferListResponse.OfferID[0].Identifier.value");

		for (int i = 0; i < Scenario.sIgnoredata.size(); i++) {

			IgnoreString.add(Scenario.sIgnoredata.get(i));
		}

		String sTestStep = "<span style=color:white>" + Scenario.sTestStep.trim() + "</span>";

		new ExtendReportCreation().createStepNodeInstance(sTestStep);

		try {

			ObjectMapper mapper = new ObjectMapper();

//			FileReader firstfilereader = new FileReader(FirstFile);
//			FileReader secondfilereader = new FileReader(SecondFile);		
//			String jsonobject1 = FileUtils.readFileToString(FirstFile);
//			String jsonobject2 = FileUtils.readFileToString(SecondFile);

			String jsonobject1 = (String) scenario.Response_Gen2;
			String jsonobject2 = (String) scenario.Response_Gen3;
//			
			JSONCompareResult result = JSONCompare.compareJSON((String) jsonobject1, (String) jsonobject2,
					JSONCompareMode.STRICT);
			List<String> list = new ArrayList<String>();
//			list  = result.toString().split(";");
			list = Arrays.asList(result.toString().split(";"));

			for (int i = 0; i < list.size(); i++) {

				String sValue = list.get(i);

				if (sValue.contains("Expected:")) {

					sValue = sValue.replace("Expected:", "<br>Gen-1:-").trim();
				}

				if (sValue.contains("got:")) {

					sValue = sValue.replace("got:", "<br>Gen-3:-").trim();
				}

				if (sValue.contains("     ")) {

					sValue = sValue.replace("     ", "").trim();
				}

				String sPath = "<html><span style=color:yellow>"
						+ sValue.substring(sValue.indexOf(0) + 1, sValue.lastIndexOf("Gen-1:-")) + "</span></html>";
				String sGen1 = "<html><span style=color:white>"
						+ sValue.substring(sValue.indexOf("Gen-1:-"), sValue.lastIndexOf("Gen-3:-")) + "</span></html>";
				String sGen3 = "<html><span style=color:yellow>" + sValue.substring(sValue.indexOf("Gen-3:-"))
						+ "</span></html>";

				String Diff = sPath + sGen1 + sGen3;
//				ExtendReportCreation.addPassAndFailComment(false, Diff);
//				String Diff = "<html><span style=color:yellow> Path:- " + sValue.trim().toString()
//						+ "<br></span></html>";

				if (CompareToIgnore(Diff.trim()) == true) {

				} else {

					ExtendReportCreation.addPassAndFailComment(false, Diff);

				}

				i = i + 1;

			}

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


//		System.out.println("IgnoreStr"+IgnoreStr);
		System.out.println("IgnoreStr"+IgnoreString);
		for (String str : IgnoreString) {
			
			str = str.toUpperCase().strip();
			IgnoreStr = IgnoreStr.toUpperCase().strip();
			System.out.println("IgnoreStr"+IgnoreStr);
			System.out.println("str"+str);

//			if(IgnoreStr.contains("brand")) {
//				
//				System.out.println(IgnoreStr);
//			}

			if ((IgnoreStr.contains(str) && !IgnoreStr.contains("DESCRIPTION"))) {
//			if ((IgnoreStr.contains(str)) || (IgnoreStr.length() > 80) && (!IgnoreStr.contains("DESCRIPTION"))) {

//				System.out.println("IgnoreStr"+IgnoreStr);
				matched = true;
				return matched;

			} else if (str.contains("IGNOREDECIMALZERO")) {
//			} else if (str.contains("IGNOREDECIMALZERO") && (line1.trim().matches(regex) || line1.trim().matches(regex1)
//					|| line1.trim().matches(regex2) || line1.trim().matches(regex3))) {

//				 System.out.println("line1"+line1);
//				 System.out.println("line2"+line2);

//				matched = IgnoreDecimalZero(line1, line2);

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

	public static Object RemoveTagsByPath(Object sResponse, ArrayList<String> sTags) throws FileNotFoundException {
//		public static Object RemoveTagsAndRows(String sResponse, ArrayList<String> sTags) throws FileNotFoundException {

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

//				String sUpdatedResponseFile = GlobalVariable.sProjectPath+"\\"+scenario.version+"UpdatedResponseFile.json";
//				jsonResp = newparser.parse(new FileReader(sResponse)); //sResponse is path of json file, comment this line when executing end to end flow and not getting response from json file

			jsonResp = sResponse; // activate this line when exeucting end to end flow
			jsonResp = jsonparser.parse((String) jsonResp);
			JSONFileString = new JsonBuilder(jsonResp).toString();
			JSONObject object = new JSONObject((Map) jsonResp);
			
		
			for (int i = 0; i < sTags.size(); i++) {

//				Configuration conf = Configuration.builder().options(Option.AS_PATH_LIST).build();
//				List<String> pathList = using(conf).parse(JSONFileString).read(sTags.get(i));

//				for (String path : pathList) {

//					if (path.contains("$")) {
//
//						path = path.replace("$", "");
//
//						System.out.println("sTags" + sTags.get(i));
//
//						System.out.println("JSONString" + path);
//						
//
//					}
				
				System.out.println("sTags"+sTags.get(i));
				System.out.println("ContainsKey"+object.containsKey(sTags.get(i)));
//				if (object.containsKey(sTags.get(i))) {
//					
//					  object.get("key");
//					  //etc. etc.
//					}
					
//					boolean isTagAvailable = path.contains(sTags.get(i));
//					System.out.println(isTagAvailable);
//					
//					if (isTagAvailable) {
//
//						sUpdatedResponse = JsonPath.parse(JSONFileString).delete(sTag);
//
//						JSONFileString = sUpdatedResponse.jsonString();
//
//					}
				 

				Configuration suppressExceptionConfiguration = Configuration.defaultConfiguration()
						.addOptions(Option.SUPPRESS_EXCEPTIONS);
				ReadContext jsonData = JsonPath.using(suppressExceptionConfiguration).parse(JSONFileString);

		
				
//				String pathData = jsonData.read(sTags.get(i));
				String pathData = jsonData.jsonString();
			
				System.out.println("sTags"+sTags.get(i));
				System.out.println("spathdata"+pathData);

//				if (pathData != null) {
//
//					sUpdatedResponse = JsonPath.parse(JSONFileString).delete(sTags.get(i));
//
//					JSONFileString = sUpdatedResponse.jsonString();
//
//				}
				
//				boolean isTagAvailable = JsonPath.parse(JSONFileString).jsonString().contains(sTags.get(i));
//
//				
//				if (isTagAvailable) {
//
//					sUpdatedResponse = JsonPath.parse(JSONFileString).delete(sTag);
//
//					JSONFileString = sUpdatedResponse.jsonString();
//
//				}

			}

//			System.out.println(jsonOutput.prettyPrint(JSONFileString));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		jsonResp = JSONFileString;

		return jsonResp;

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

//					String[] sIntArray = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
//							"14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25" };
//
//					for (int k = 0; k < sIntArray.length; k++) {
//
//						char[] sPathChar = path.toCharArray();
						
//
//						for (int l = 0; l < sPathChar.length; l++) {
//
//							String sString = Character.toString(sPathChar[l]);
//
//							if (sString.contains(sIntArray[k])) {
//
//								path = path.replace("." + sString + ".", "[" + sIntArray[k] + "]");
//
//							}
//
//						}
//
//					}

					sTag = path;

					System.out.println(sTag);

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
// ------------------------------------------------------------------------------------

// String[] linearray1 = reader1.readLines();
// String[] linearray2 = reader2.readLines();
//
// int size1 = linearray1.length;
// int size2 = linearray2.length;
//
//
// for(int i = 1; i < size2; i++) {
//
// String line1 = linearray1[i].trim()
// String line2 = linearray2[i].trim()
//
// if (line1 != null || line2 != null){
//
// if(!line1.equalsIgnoreCase(line2)) {
//
// println(line1.trim() +" not same "+ line2.trim())
// println('lineNum'+i)
//
// }
//
//
// }
//
//
//
// }
//
// reader1.close();
// reader2.close();

// -----------------------------------------------------------------------------------------------------------------

// String file1 =
// "S:\\Results\\NDC_KatalonFramework\\Extents\\NDC-FareFamilySearch-OpenJaw_1ADT_1CHD_1CNN_1INF_1INS_1SRC_3YTH_Upsells_2_12_12_2023_161116\\NDC-Search\\ReqRes12_12_2023_161138444.json"
//
// String file2 =
// "S:\\Results\\NDC_KatalonFramework\\Extents\\NDC-FareFamilySearch-OpenJaw_1ADT_1CHD_1CNN_1INF_1INS_1SRC_3YTH_Upsells_2_12_12_2023_161116\\NDC-Search\\ReqRes12_12_2023_161206719.json"
//
//
// List<String> listOfStrings = new ArrayList<String>();
// BufferedReader reader3 = new BufferedReader(new FileReader(file1));
// BufferedReader reader4 = new BufferedReader(new FileReader(file2));
// String line3 = reader3.readLine().trim();
// String line4 = reader4.readLine().trim();
// int lineNum = 1;
// boolean areEqual = true;
//
//
//
// while (line3 != null || line4 != null){
//
// // println(line3)
// // println(line4)
//
// if(line3 == null || line4 == null){
//
// break
//
// }else if(CompareToIgnore(line3.trim()) == true ||
// CompareToIgnore(line4.trim()) == true ) {
//
// println(line3)
// println(line4)
//
//
// }else if(!line3.trim().equalsIgnoreCase(line4.trim())) {
// areEqual = false;
// // println('line :- '+lineNum + ":"+ line3.trim() +" : "+ line4.trim())
// String Diff = '-----line no. :- '+lineNum + "------"+"<br>"+ \
// 'Gen-2:- ' + line3.trim() +"<br>"+ 'Gen-3:- '+ line4.trim() \
//
// ExtendReportCreation.addPassAndFailComment(areEqual , Diff)
//
//
//
//
// }
//
//
// line3 = reader3.readLine();
// line4 = reader4.readLine();
// lineNum++;
//
// }
//
//
// reader3.close();
// reader4.close();
//
// if (areEqual == false) {
//
// KeywordUtil.markFailed('Gen-2 And Gen-3 responses are not identical.')
//
// }
//
//
//
//
//
//	assertEquals(mapper.readTree(objGen2.toString()), mapper.readTree(objGen3.toString()));

//Scanner reader1 = new Scanner(new FileReader("C:\\TestData\\NDC\\Search-Gen1.json"));
//Scanner reader2 = new Scanner(new FileReader("C:\\TestData\\NDC\\Search-Gen3.json"));

//List<String> list = Files.readAllLines(Paths.get("C:\\TestData\\NDC\\Search-Gen1.json"));

//BufferedReader brReader1 = new BufferedReader(new FileReader("C:\\TestData\\NDC\\Search-Gen1.json"));
//BufferedReader brReader2 = new BufferedReader(new FileReader("C:\\TestData\\NDC\\Search-Gen3.json"));
//
//
//ArrayList<String> list = (ArrayList<String>) Files.readAllLines(new File("C:\\TestData\\NDC\\Search-Gen1.json").toPath(), Charset.defaultCharset() );
//
//System.out.println(list);

//JSONObject jsonobject1 = new JSONObject();
//			JSONObject jsonobject2 = new JSONObject();
//JSONParser jsonparser1 = new JSONParser();
//JSONParser jsonparser2 = new JSONParser();
//			jsonobject1 = jsonparser1.parse(firstfilereader);
//			jsonobject2 = jsonparser2.parse(secondfilereader);
