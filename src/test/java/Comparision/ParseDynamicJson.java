//package versioncomparision;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
//import org.json.JSONObject;
//
//import groovy.json.JsonBuilder;
//import groovy.json.JsonOutput;
//import groovy.json.JsonSlurper;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//
//public class ParseDynamicJson {
//
//	// how to parse json
//	// how to parse nested json
//
//	public static void main(String[] args) throws FileNotFoundException {
//		// TODO Auto-generated method stub
//
//		JsonOutput jsonOutput = new JsonOutput();
//		JsonSlurper newparser = new JsonSlurper();
//		String Jsonpath = "C:\\Users\\uma.pal\\eclipse-workspace\\VersionComparision\\src\\test\\resources\\Payloads\\Search_FareFamily_NextLeg.json";
//		File jsonDataInFile = new File(Jsonpath);
//		Object jsonResp = newparser.parse(new FileReader(jsonDataInFile));		
//		String inputJson = "{\r\n" + "   \"CatalogProductOfferingsQueryBuildNext\" : {\r\n"
//				+ "      \"@type\" : \"CatalogProductOfferingsQueryBuildNext\",\r\n"
//				+ "      \"BuildFromCatalogProductOfferingsRequest\" : {\r\n"
//				+ "         \"@type\" : \"BuildFromCatalogProductOfferingsRequest\",\r\n"
//				+ "         \"CatalogProductOfferingsIdentifier\" : {\r\n" + "            \"Identifier\" : {\r\n"
//				+ "               \"value\" : \"pseudoshopffsntac:E2ETrackingId\"\r\n" + "            }\r\n"
//				+ "         },\r\n" + "         \"CatalogProductOfferingSelection\" : [\r\n" + "            {\r\n"
//				+ "               \"@type\" : \"CatalogProductOfferingSelection\",\r\n"
//				+ "               \"CatalogProductOfferingIdentifier\" : {\r\n"
//				+ "                  \"Identifier\" : {\r\n" + "                     \"value\" : \""
//				+ "sProductOfferingsToken\"\r\n" + "                  }\r\n" + "               },\r\n"
//				+ "               \"ProductIdentifier\" : [\r\n" + "                  {\r\n"
//				+ "                     \"Identifier\" : {\r\n" + "                        \"value\" : \""
//				+ "sProductRefToken \"\r\n" + "                     }\r\n" + "                  }\r\n"
//				+ "               ]\r\n" + "            }\r\n" + "         ]\r\n" + "      }\r\n" + "   }\r\n"
//				+ "}";
//
//		inputJson = new JsonBuilder(jsonResp).toString();		
////		System.out.println(JsonOutput.prettyPrint(inputJson.toString()));
////		System.out.println(inputJson);
//		JSONObject inputJSONOBject = new JSONObject(inputJson);
//		getKey(inputJSONOBject, "value");
//		
//		
//		
////		try {
////		    String contents = inputJson;
////		    JSONObject jsonFile = new JSONObject(contents);
////		    JSONObject VariableList = jsonFile.getJSONObject("Value"); // <-- use getJSONObject
////		    JSONArray VariableList = jsonFile.getJSONArray("value");
//		    
////		    for (Object Item: VariableList) {
////			    Map.Entry Item2 = (Map.Entry)Item;
////			    System.out.println("Key: " + Item2.getKey() + ", Value: " + Item2.getValue());
////			}
//		    
////		    VariableList.keySet().forEach(key -> {
////		        Object value = VariableList.get(key);
////		        System.out.println("key: "+ key + ", value: " + value);
////		    });
//		    
////		    JSONArray keys = VariableList.names ();
////		    for (int i = 0; i < keys.length (); ++i) {
////		        String key = keys.getString(i);
////		        String value = VariableList.getString(key);
////		        System.out.println("key: " + key + " value: " + value);
////		}
//		    
//		    
////		} catch (Exception e) {
////		    e.printStackTrace();
////		}
//	
//		
//		
//		 
//
//	}
//
//	public static void parseObject(JSONObject json,String key) throws FileNotFoundException {
//		
//		System.out.println(json.has(key));		
//		System.out.println(json.get(key));
//		
//	}
//
//	public static void getKey(JSONObject json, String key) throws FileNotFoundException {
//
//		boolean exists = json.has(key);
////		boolean exists = json.isEmpty();
////		System.out.println("key "+key);
//		Iterator<?> keys;
//		String nextKeys;
//		System.out.println("exists"+exists);
//		
//		if (!exists) {
//
//			keys = json.keys();
//			
//			while (keys.hasNext()) {
//				
//				nextKeys = (String) keys.next();
//				
//				try {
//
//					if (json.get(nextKeys) instanceof JSONObject) {
//
//						if (exists == false) {
//
////							System.out.println("hello");
//							getKey(json.getJSONObject(nextKeys), key);
//						}
//
//					} else if (json.get(nextKeys) instanceof JSONArray) {
//						
//						JSONArray jsonarray = json.getJSONArray(nextKeys);
//						for(int i =0; i<jsonarray.length();i++) {
//							
//							String jsonarrayString = jsonarray.get(i).toString();
//							JSONObject innerJSON = new JSONObject(jsonarrayString);
//							
//							if(exists == false) {
//								
//								getKey(innerJSON,nextKeys);
//							}
//							
//						
//							
//						}
//						
//
//					}
//
//				} catch (Exception e) {
//
//				}
//
//			}
//
//		} else {
//
//			
//			parseObject(json, key);
//			
//		}
//
//	}
//
//}
package Comparision;


