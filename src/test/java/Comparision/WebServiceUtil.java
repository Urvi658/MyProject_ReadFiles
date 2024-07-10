package Comparision;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import groovy.json.JsonBuilder;
import groovy.json.JsonOutput;
import groovy.json.JsonSlurper;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonParser;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonMappingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class WebServiceUtil {
	
	static JsonOutput jsonOutput = new JsonOutput();
	
	/**
	 * Convert String to Data Structure
	 * @param text - Text to be converted to Data Structure
	 * @return 
	 * @return the Json data Structure
	 */
	public static Object convertStringToDataStructure(String text) {
		return new JsonSlurper().parseText(text);
	}

	/**
	 * Convert String to Data Structure
	 * @param headerFields - Text to be converted to Data Structure
	 * @return 
	 * @return 
	 * @return A String representation of the data structure
	 */
	@SuppressWarnings("static-access")
	public static String convertDataStructureToString(String headerFields) {
		
		new JsonOutput();
		return JsonOutput.toJson(headerFields);
	}


	/**
	 * Get a string representation of the request headers information
	 * @param requestObject
	 * @return A String representation of the request headers
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public static String getRequestHeadersInfo(FilterableRequestSpecification requestObject) throws ParseException, IOException {
		
		JsonOutput jsonOutput = new JsonOutput();
		JSONParser parser = new JSONParser();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node;
		String url = requestObject.getBaseUri();
		String method = requestObject.getMethod();
		ArrayList<String> list = new ArrayList<String>();
		Map<String, List<String>> headerFields = new HashMap<String, List<String>>();
		ArrayList<String> arrayheader = new ArrayList<String>();
		ArrayList<String> arraymethod = new ArrayList<String>();
		ArrayList<String> arrayurl = new ArrayList<String>();
		arrayheader.add(requestObject.getHeaders().toString());
		arraymethod.add(method);
		arrayurl.add(url);
//		list.add(requestObject.getHeaders().toString());
		headerFields.put("Headers", arrayheader);
		headerFields.put("Method",arraymethod);			
		headerFields.put("URL", arrayurl);		
		String Requestheaders = convertDataStructureToString(headerFields.toString());		
//		String newRequestHeader = parser.parse(Requestheaders).toString();
//		new JsonOutput();		 
//		String reqobject = JsonOutput.toJson(sRequestHeader);
//		JsonNode jsonnode = mapper.readTree(newRequestHeader);
//		Requestheaders = jsonnode.toPrettyString();
//		obj = parser.parse(reqobject);
//		String JSONFileString = new JsonBuilder(Requestheaders).toString();
//		JSONFileString = new JsonBuilder(obj).toString();		
//		String reqobject = JsonOutput.toJson(sRequestHeader);
//		System.out.println("requestheader"+reqobject.toString());
		return Requestheaders;
//		return newRequestHeader;
		
		
	}

	/**
	 * Get a string representation of the response headers information
	 * @param requestObject
	 * @return A String representation of the response headers
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 */
	public static String getResponseHeaderInfo(Response responseObject) throws JsonMappingException, JsonProcessingException {
		String headersJson = null;
		ArrayList<String> list = new ArrayList<String>();
		Map<String, List<String>> headerFields = new HashMap<String, List<String>>();
		ArrayList<Integer> arraystatus = new ArrayList<Integer>();
		ArrayList<String> arrayheader = new ArrayList<String>();
		arrayheader.add(responseObject.headers().toString());
		int iStatusCode = responseObject.getStatusCode();
		arraystatus.add(responseObject.getStatusCode());
		headerFields.put("Headers", arrayheader);		
		String sStatusCode =Integer.toString(iStatusCode);
//		arraystatus.add(sStatusCode);
		list.add(sStatusCode);
		headerFields.put("Status Code", list);
		String Responseheaders = convertDataStructureToString(headerFields.toString());

		return Responseheaders;
		
	}

	
	

}
