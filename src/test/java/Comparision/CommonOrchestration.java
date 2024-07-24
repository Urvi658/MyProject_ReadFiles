package Comparision;

import groovy.json.JsonOutput;
import groovy.json.JsonSlurper;

public class CommonOrchestration {
	
	
	

	/*
	 * DESCRIPTION	: Convert String to Data Structure
	 * INPUT		: Text to be converted to Data Structure
	 */
	public static Object convertStringToDataStructure(String text) {
		return new JsonSlurper().parseText(text);
		 
	}

	/*
	 * DESCRIPTION	: Convert String to Data Structure
	 * INPUT		: Text to be converted to Data Structure
	 */
	public static Object convertDataStructureToString(String dataStructure) {
		return new JsonOutput().toJson(dataStructure);
	}


}
