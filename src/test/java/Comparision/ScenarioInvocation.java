package Comparision;

import io.cucumber.java.en.Given;
import groovy.json.JsonOutput;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonMappingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.messages.IdGenerator;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.But;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class ScenarioInvocation {

	@When("^Invoke the Search request (.*) with endpoint (.*) Request for (.*) Version$")
	public void ExecuteSearchRequest(String RequestType, String Servicename, String Version) throws Exception {

		Scenario scenario = new Scenario();
		scenario = GlobalVariable.objScenarioData;

		String sTestStep = "<span style=color:white>" + scenario.sTestStep.trim() + "</span>";
		new ExtendReportCreation().createStepNodeInstance(sTestStep);
		GlobalVariable.Version = Version.replace("'", "").trim();
		scenario.RequestType = RequestType.replace("'", "").trim();
		scenario.version = Version.replace("'", "").trim();
		scenario.servicename = Servicename.replace("'", "").trim();
//		System.out.println(scenario.version);
//		System.out.println(scenario.servicename);
		ScenarioUtil.configEndpointByComponent();

//		Map<String, String> objToken = (Map<String, String>) GlobalVariable.objToken;

		if (!scenario.RequestType.contains("NextLeg")) {

			System.out.println(scenario.RequestType);
			ScenarioUtil.RetrievePTC(scenario);

		}

		 
		 
		if (scenario.servicename.contains("NextLeg")) {

			try {
//			System.out.println("helloNextLeg");
				List<String> sValueList = new ArrayList();
//			System.out.println("sMultipleValue"+scenario.sMultipleValue);
//			System.out.println("scenario.version"+scenario.version.toString().trim());

//			System.out.println("sValueList"+sValueList);

				sValueList = Scenario.sMultipleValue.get(scenario.version.toString().trim());
								
				if (sValueList != null) {

					 
					GlobalVariable.sToken = sValueList.get(0).toString().trim();
					GlobalVariable.sProductRef = sValueList.get(1).toString().trim();
//					GlobalVariable.sProductBrandOptionsToken =  sValueList.get(2).toString().trim();
//					System.out.println(GlobalVariable.sProductBrandOptionsToken);

				} else {

					ExtendReportCreation.addPassAndFailValidation(false, "Search Response not proper,Kindly Check it");

				}
			} catch (Exception ex) {

				ex.printStackTrace();

			}
//			System.out.println("GlobalVariable.sToken"+GlobalVariable.sToken);
//			System.out.println("GlobalVariable.sProductRef"+GlobalVariable.sProductRef);

		}

		if (scenario.version.contains("GEN-1") && (!scenario.RequestType.contains("NextLeg"))) {

 
//			String sRequest = EndpointHandler.ParameterizeSearchRequest(scenario);
			scenario.Response_Gen2 = EndpointHandler.SearchRequest(scenario);
			
			

		} else if (scenario.version.contains("GEN-3") && (!scenario.RequestType.contains("NextLeg"))) {

//			System.out.println("hellogen3");
			scenario.Response_Gen3 = EndpointHandler.SearchRequest(scenario);

		} else if (scenario.version.contains("GEN-1") && (scenario.RequestType.contains("NextLeg"))) {

//			System.out.println("hellogen2 and NextLeg");
//			scenario.Response_Gen2_NextLeg = EndpointHandler.SearchRequest(scenario);
			scenario.Response_Gen2_NextLeg = EndpointHandler.SearchNextLeg(scenario);

		} else if (scenario.version.contains("GEN-3") && (scenario.RequestType.contains("NextLeg"))) {

//			System.out.println("hellogen3 and NextLeg");
			scenario.Response_Gen3_NextLeg = EndpointHandler.SearchNextLeg(scenario);

		}

		GlobalVariable.objScenarioData = scenario;

	}

	@When("^Invoke the Price request (.*) with endpoint (.*) for (.*) Version$")
	public void ExecutePriceRequest(String RequestType, String Servicename, String Version) throws Exception {

		Scenario scenario = new Scenario();
		scenario = GlobalVariable.objScenarioData;

		String sTestStep = "<span style=color:white>" + scenario.sTestStep.trim() + "</span>";
		new ExtendReportCreation().createStepNodeInstance(sTestStep);

		GlobalVariable.Version = Version.replace("'", "").trim();
		scenario.RequestType = RequestType.replace("'", "").trim();
		scenario.version = Version.replace("'", "").trim();
		scenario.servicename = Servicename.replace("'", "").trim();
		
		
		ScenarioUtil.configEndpointByComponent();

		if (scenario.servicename.toUpperCase().contains("PRICE")) {
			
			System.out.println(scenario.version);
			System.out.println(scenario.servicename);
			System.out.println(scenario.RequestType);
			

			try {

				List<String> sValueList = new ArrayList();
				sValueList = Scenario.sMultipleValue.get(scenario.version.toString().trim());

//				GlobalVariable.sToken = sValueList.get(0).toString().trim();
//				GlobalVariable.sProductRef = sValueList.get(1).toString().trim();
//				GlobalVariable.sProductBrandOptionsToken = sValueList.get(2).toString().trim();
				
				if (sValueList != null) {

					GlobalVariable.sToken = sValueList.get(0).toString().trim();
					GlobalVariable.sProductRef = sValueList.get(1).toString().trim();
//					GlobalVariable.sProductBrandOptionsToken = sValueList.get(2).toString().trim();
					

				} else {

//					ExtendReportCreation.addPassAndFailValidation(false, "Price Response not proper,Kindly Check it");

				}
				
//				System.out.println("Invoking price request");
				if (scenario.version.toUpperCase().contains("GEN-1")
						&& (scenario.RequestType.toUpperCase().contains("PRICE"))) {
					
					scenario.Response_Gen2 = EndpointHandler.PriceRequest(scenario);

				} else if (scenario.version.toUpperCase().contains("GEN-3")
						&& (scenario.RequestType.toUpperCase().contains("PRICE"))) {
//					System.out.println("Invoking price request");
 
					scenario.Response_Gen3 = EndpointHandler.PriceRequest(scenario);

				}

				GlobalVariable.objScenarioData = scenario;

			} catch (Exception ex) {

				ex.printStackTrace();

			}

		}

	}

//	@Then("^Remove (.*) tags followed by next (.*) rows from the (.*) response for (.*)$")
//	public void RemoveTags(String sTags, String iRow, String sRequestType, String sVersion) throws Throwable {

	 
	@Then("^Remove tags from the response for below RequestType and Version$")
	public void RemoveTags(DataTable data) throws Throwable {

		Scenario scenario = GlobalVariable.objScenarioData;

		List<List<String>> dataList = data.asLists(String.class);

		ArrayList<String> sTags = new ArrayList();

		int iSize = dataList.size();

		System.out.println("size" + iSize);

		for (int i = 1; i < dataList.size(); i++) {

			sTags.add(dataList.get(i).get(0));

		}

		String sRequestType = dataList.get(1).get(1);
		String sVersion = dataList.get(1).get(2);

//		scenario.version = sVersion;
//		scenario.RequestType = sRequestType;

//		System.out.println("sTags" + sTags);
//		System.out.println("sRequestType" + sRequestType);
//		System.out.println("sVersion" + sVersion);

		if (sVersion.contains("GEN-1") && (!sRequestType.contains("NextLeg"))) {

			scenario.Response_Gen2 = SearchValidation.RemoveTagsAndRows(scenario.Response_Gen2, sTags);

		} else if (sVersion.contains("GEN-3") && (!sRequestType.contains("NextLeg"))) {

			scenario.Response_Gen3 = SearchValidation.RemoveTagsAndRows(scenario.Response_Gen3, sTags);

		} else if (sVersion.contains("GEN-1") && (sRequestType.contains("NextLeg"))) {

//			sRequestType = "C:\\TestData\\NDC\\g1-buildnextRS.json";
//			scenario.Response_Gen2_NextLeg = SearchValidation.RemoveTagsAndRows(sRequestType, sTags) ;
			scenario.Response_Gen2_NextLeg = SearchValidation.RemoveTagsAndRows(scenario.Response_Gen2_NextLeg, sTags);

		} else if (sVersion.contains("GEN-3") && (sRequestType.contains("NextLeg"))) {

//			sRequestType = "C:\\TestData\\NDC\\g3-buildnextRS.json";
//			scenario.Response_Gen3_NextLeg = SearchValidation.RemoveTagsAndRows(sRequestType, sTags) ;	 

			scenario.Response_Gen3_NextLeg = SearchValidation.RemoveTagsAndRows(scenario.Response_Gen3_NextLeg, sTags);

		}

		GlobalVariable.objScenarioData = scenario;

	}
	
	@SuppressWarnings("null")
	@Then("^Remove tags by path from the response for below RequestType and Version$")
	public void RemoveTagsByPath(DataTable data) throws Throwable {

		Scenario scenario = GlobalVariable.objScenarioData;

		List<List<String>> dataList = data.asLists(String.class);

		ArrayList<String> sTags = new ArrayList();

		int iSize = dataList.size();

		System.out.println("size" + iSize);

		for (int i = 1; i < dataList.size(); i++) {

			sTags.add(dataList.get(i).get(0));

		}

		String sRequestType = dataList.get(0).get(1);
		String sVersion = dataList.get(0).get(2);

//		scenario.version = sVersion;
//		scenario.RequestType = sRequestType;

//		System.out.println("sTags" + sTags);
//		System.out.println("sRequestType" + sRequestType);
//		System.out.println("sVersion" + sVersion);

		if (sVersion.contains("GEN-1") && (!sRequestType.contains("NextLeg"))) {

			scenario.Response_Gen2 = SearchValidation.RemoveTagsByPath(scenario.Response_Gen2, sTags);

		} else if (sVersion.contains("GEN-3") && (!sRequestType.contains("NextLeg"))) {

			scenario.Response_Gen3 = SearchValidation.RemoveTagsByPath(scenario.Response_Gen3, sTags);

		} else if (sVersion.contains("GEN-1") && (sRequestType.contains("NextLeg"))) {

//			sRequestType = "C:\\TestData\\NDC\\g1-buildnextRS.json";
//			scenario.Response_Gen2_NextLeg = SearchValidation.RemoveTagsAndRows(sRequestType, sTags) ;
			scenario.Response_Gen2_NextLeg = SearchValidation.RemoveTagsByPath(scenario.Response_Gen2_NextLeg, sTags);

		} else if (sVersion.contains("GEN-3") && (sRequestType.contains("NextLeg"))) {

//			sRequestType = "C:\\TestData\\NDC\\g3-buildnextRS.json";
//			scenario.Response_Gen3_NextLeg = SearchValidation.RemoveTagsAndRows(sRequestType, sTags) ;	 

			scenario.Response_Gen3_NextLeg = SearchValidation.RemoveTagsByPath(scenario.Response_Gen3_NextLeg, sTags);

		}

		GlobalVariable.objScenarioData = scenario;

	}

	@Then("Skip below tags while doing comparisions")
	public void SkipTags(List<String> IgnoreTags) throws Throwable {

//	    System.out.println("the ignore data is "+IgnoreTags);		

		Scenario scenario = GlobalVariable.objScenarioData;

		Scenario.sSkiptags = IgnoreTags;

		if (Scenario.sSkiptags.size() > 0) {

			Scenario.sSkiptagsValue = true;

		}

		GlobalVariable.objScenarioData = scenario;

	}

	@Then("^Validate and Compare the responses for (.*) both Versions$")
	public void ValidateAndCompareSearchResponse(String RequestType)
			throws JsonMappingException, JsonProcessingException, JsonIOException, JsonSyntaxException, FileNotFoundException {

		Scenario scenario = GlobalVariable.objScenarioData;

		scenario.RequestType = RequestType.trim();

		CompareJson.compareTwoJsons(scenario.Response_Gen2.toString(),scenario.Response_Gen3.toString(),"");
//		SearchValidation.JsonComparision(scenario);
//		SearchValidation.validateResultObject(scenario);

		GlobalVariable.objScenarioData = scenario;

		String ReqResGen2 = JsonOutput.prettyPrint(scenario.Response_Gen2.toString());
		String ReqResGen3 = JsonOutput.prettyPrint(scenario.Response_Gen3.toString()); 
//		System.out.println(ReqResGen2);
//		System.out.println(ReqResGen3);

	}

}
