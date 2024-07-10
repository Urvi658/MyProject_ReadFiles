package Comparision;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Comparision.CustomReport;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;

import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import groovy.json.JsonBuilder;
import groovy.json.JsonOutput;
import groovy.json.JsonParserType;
import groovy.json.JsonSlurper;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseBodyData;

public class EndpointHandler {

	public static Map<String, String> sToken = new HashMap<String, String>();
//	public static String sFilePath = "C:\\Users\\uma.pal\\eclipse-workspace\\VersionComparision\\src\\test\\resources\\Payloads\\";
	public static String sFilePath = GlobalVariable.sProjectPath + "\\src\\test\\resources\\Payloads\\";
	public static String URL;

	public static void main(String[] args) throws Exception {

//		Scenario scenario = null;
//		EndpointHandler.UpdateJsonFile();
//		String sResponse = ParameterizeSearchRequest(scenario);

	}

	public static String SearchRequest(Scenario scenario) throws Exception {

		String sRequest = ParameterizeSearchRequest(scenario);

		String sResponseBody = null;
		URL = Endpoint.baseUrl;
//		System.out.println(scenario.version);

		try {

//			GlobalVariable.Resource_Identifier = "11588c82-d48c-43e0-88c3-9b1dba2f7ad5";
//			GlobalVariable.CoreAffinity = "1G";
			JsonOutput jsonOutput = new JsonOutput();
			JSONParser parser = new JSONParser();
			WebServiceUtil WebServiceUtil = new WebServiceUtil();
//			String sRequestType = sFilePath.trim() + scenario.RequestType.trim() + ".json";
//			File jsonDataInFile = new File(sRequestType);
			ObjectMapper mapper = new ObjectMapper();
//			Object obj = parser.parse(new FileReader(jsonDataInFile));			
			Object obj = parser.parse(sRequest);
			JSONObject jsonObject = (JSONObject) obj;
			List<String> infoList = new ArrayList();
//			JSONArray obj = (JSONArray) parser.parse(new FileReader(sFilePath));
//			Object obj = parser.parse(new FileReader(jsonDataInFile));
//			System.out.println(jsonObject.toJSONString()); 
//			System.out.println(JsonOutput.prettyPrint(jsonObject.toJSONString()));

			RequestSpecification sSearchRequest = RestAssured.given().baseUri(URL).contentType(ContentType.JSON)
					.body(sRequest).header("Content-Type", "application/json").header("Accept", "application/json")
					.header("CityCode", GlobalVariable.CityCode)
					.header("domainlistenerchannelid", GlobalVariable.domainlistener)
					.header("IATANumber", GlobalVariable.Iatanumber).header("E2ETrackingID", "E2ETrackingId")
					.header("PseudoCityCode", GlobalVariable.PseudoCityCode)
					.header("CurrencyCode", GlobalVariable.CurrencyCode)
					.header("ReservationResource_Identifier", GlobalVariable.Resource_Identifier)
					.header("DomainRegion", GlobalVariable.DomainRegion)
					.header("CountryCode", GlobalVariable.CountryCode)
					.header("CoreAffinity", GlobalVariable.CoreAffinity)
					.header("OAUTH_RESOURCEOWNERINFO", GlobalVariable.oAuthResourceInfo)
					.header("XAUTH_TRAVELPORT_ACCESSGROUP", GlobalVariable.Accessgroup).header("ChannelId", "1234")
					.header("PCC", GlobalVariable.PCC).header("scc-rule-match-id", "test1234567")
					.header("traceId", "srip_01").header("IDM_CARRIER_LIST", GlobalVariable.IDM_CARRIER_LIST);

////		 if(sSearchRequest instanceof FilterableRequestSpecification){

			FilterableRequestSpecification requestObject = (FilterableRequestSpecification) sSearchRequest;

////		 }

			String sRequestBody = requestObject.getBody().toString();
//			System.out.println("sRequestBody"+sRequestBody);
			JsonNode jsonnode = mapper.readTree(sRequestBody);
//		    System.out.println(jsonnode.toPrettyString());

			Response sSearchResponse = (Response) sSearchRequest.when().post().then().contentType(ContentType.JSON)
					.extract().response().body();
//			
//			Response sSearchResponse = (Response) sSearchRequest.when().post().then().extract().response().body();

			boolean sResponseStatus = sSearchResponse.getStatusCode() == 200;
//			String sRequestBody = jsonObject.toJSONString();

			String sRequestHeader = requestObject.getHeaders().toString();			
			String sResponseHeader = sSearchResponse.getHeaders().toString();
			sResponseBody = sSearchResponse.asString();
			String url = requestObject.getBaseUri();
			String method = requestObject.getMethod();
			String sContentType = sSearchResponse.contentType();
			System.out.println(sContentType);

			if (sResponseStatus) {

				infoList.add(scenario.version + " Search Request Successfull");
				ExtendReportCreation.addPassAndFailValidation(sResponseStatus, " Search Request Successfull");

			} else {

				infoList.add(scenario.version + " Search Request fails");
				ExtendReportCreation.addPassAndFailValidation(sResponseStatus, " Search Request fails");

			}

			GlobalVariable.objScenarioData = scenario;

//			ExtendReportCreation.addPassAndFailValidation(sResponseStatus, sContentType);

			ExtendReportCreation.addRequestResponse(sRequestBody, sResponseBody, sResponseStatus, scenario.servicename,
					infoList, WebServiceUtil.getRequestHeadersInfo(requestObject),
					WebServiceUtil.getResponseHeaderInfo(sSearchResponse));

//			ScenarioStorage.addRequestResponseToHtmlReport("RequestHeader",WebServiceUtil.getRequestHeadersInfo(requestObject),sContentType,scenario.servicename);
//			ScenarioStorage.addRequestResponseToHtmlReport("ResponseHeader",WebServiceUtil.getResponseHeaderInfo(sSearchResponse),sContentType,scenario.servicename);
//			ScenarioStorage.addRequestResponseToHtmlReport("Request",sRequestBody,sContentType,scenario.servicename);
//			ScenarioStorage.addRequestResponseToHtmlReport("Response",sResponseBody,sContentType,scenario.servicename);
//			Assert.assertTrue(sResponseStatus); 

//			EndpointHandler.UpdateJsonFile(sSearchResponse);
			RetrieveTokens(sResponseBody);

//		return sToken;

		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		GlobalVariable.objScenarioData = scenario;

		return sResponseBody;

	}

	public static String ParameterizeSearchRequest(Scenario scenario) throws Exception {

		JsonSlurper newparser = new JsonSlurper();
//			Object jsonResp = newparser.parse(new FileReader(jsonDataInFile));
		String sRequestType = sFilePath.trim() + scenario.RequestType.trim() + ".json";
		ObjectMapper mapper = new ObjectMapper();
		File jsonDataInFile = new File(sRequestType);
		Object jsonResp = newparser.parse(new FileReader(jsonDataInFile));
		String JSONFileString = new JsonBuilder(jsonResp).toString();
		JSONFileString = new JsonBuilder(jsonResp).toString();
		JSONParser jsonparser = new JSONParser();
		Object obj = jsonparser.parse(JSONFileString);
		JSONObject jsonObject = (JSONObject) obj;
		JSONArray jsonarray = new JSONArray();

		try {

//			Boolean isCarrier = JsonPath.parse(JSONFileString)
//					.equals("CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchModifiersAir.CarrierPreference[0].carriers[0]");

//			System.out.println("isCarrier "+isCarrier);

//			JSONFileString = new JsonBuilder(sRequestType).toString();

//			String s1stPtcType = JsonPath.parse(JSONFileString).set(
//				"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.PassengerCriteria[0].passengerTypeCode",
//				GlobalVariable.s1stPtcType).jsonString();		
//		
//			String s1stPtcCnt = JsonPath.parse(s1stPtcType).set(
//				"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.PassengerCriteria[0].number",
//				GlobalVariable.s1stPtcCnt).jsonString();

//			System.out.println(scenario.sPtccnt.size());

			ArrayList<Integer> sPassengerCountTag = new ArrayList<Integer>();
			sPassengerCountTag = JsonPath.parse(JSONFileString)
					.read("CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.PassengerCriteria");
//			System.out.println("sPassengerCountTagsize" + sPassengerCountTag.size());

			int i = 0;
			String s1stPtcType;
			String s1stPtcCnt = null;

//			for (i = 0; i < sPassengerCountTag.size(); i++) {
			for (String sPTC : scenario.sPtccnt.keySet()) {

//			System.out.println("key: " + sPTC + " value: " + scenario.sPtccnt.get(sPTC));

				JSONFileString = JsonPath.parse(JSONFileString)
						.set("CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.PassengerCriteria[" + i
								+ "].passengerTypeCode", sPTC)
						.jsonString();

				JSONFileString = JsonPath.parse(JSONFileString)
						.set("CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.PassengerCriteria[" + i
								+ "].number", scenario.sPtccnt.get(sPTC))
						.jsonString();

				i = i + 1;

			}

			ArrayList<Integer> SearchCriteriaFlight = new ArrayList<Integer>();

			SearchCriteriaFlight = JsonPath.parse(JSONFileString)
					.read("CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchCriteriaFlight");

//			System.out.println("SearchCriteriaFlight" + SearchCriteriaFlight.size());

//			for (i = 0; i < SearchCriteriaFlight.size(); i++) {

			if (SearchCriteriaFlight.size() == 1) {

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchCriteriaFlight[0].departureDate",
						GlobalVariable.DeptDate).jsonString();

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchCriteriaFlight[0].From.value",
						GlobalVariable.DeptflightFrom).jsonString();

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchCriteriaFlight[0].To.value",
						GlobalVariable.DeptflightTo).jsonString();

			} else if (SearchCriteriaFlight.size() == 2) {

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchCriteriaFlight[0].departureDate",
						GlobalVariable.DeptDate).jsonString();

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchCriteriaFlight[0].From.value",
						GlobalVariable.DeptflightFrom).jsonString();

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchCriteriaFlight[0].To.value",
						GlobalVariable.DeptflightTo).jsonString();

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchCriteriaFlight[1].departureDate",
						GlobalVariable.RetnDate).jsonString();

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchCriteriaFlight[1].From.value",
						GlobalVariable.RetnflightFrom).jsonString();

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchCriteriaFlight[1].To.value",
						GlobalVariable.RetnflightTo).jsonString();

			} else if (SearchCriteriaFlight.size() == 3) {

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchCriteriaFlight[0].departureDate",
						GlobalVariable.DeptDate).jsonString();

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchCriteriaFlight[0].From.value",
						GlobalVariable.DeptflightFrom).jsonString();

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchCriteriaFlight[0].To.value",
						GlobalVariable.DeptflightTo).jsonString();

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchCriteriaFlight[1].departureDate",
						GlobalVariable.OnwrdDate).jsonString();

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchCriteriaFlight[1].From.value",
						GlobalVariable.OnwrdflightFrom).jsonString();

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchCriteriaFlight[1].To.value",
						GlobalVariable.OnwrdflightTo).jsonString();
				JSONFileString = JsonPath.parse(JSONFileString).set(
						"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchCriteriaFlight[2].departureDate",
						GlobalVariable.RetnDate).jsonString();

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchCriteriaFlight[2].From.value",
						GlobalVariable.RetnflightFrom).jsonString();

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchCriteriaFlight[2].To.value",
						GlobalVariable.RetnflightTo).jsonString();

			}

//		}

			JSONFileString = JsonPath.parse(JSONFileString).set(
					"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchModifiersAir.CarrierPreference[0].carriers[0]",
					GlobalVariable.Carrier).jsonString();

			boolean isCurrencyCode = JsonPath.parse(JSONFileString).jsonString().contains("currencyCode");

			if (isCurrencyCode && GlobalVariable.CurrencyCode != null) {

//				System.out.println("isCurrencyCode " + isCurrencyCode);

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.PricingModifiersAir.currencyCode",
						GlobalVariable.CurrencyCode).jsonString();

			}

			boolean isAccountCode = JsonPath.parse(JSONFileString).jsonString().contains("organizationCodeType");

//			System.out.println("isAccountCode " + isAccountCode);

			if (GlobalVariable.sAccountCode != null && isAccountCode) {

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.PricingModifiersAir.OrganizationInformation.OrganizationIdentifier[0].value",
						GlobalVariable.sAccountCode.trim()).jsonString();

			}

			boolean isCabinPreference = JsonPath.parse(JSONFileString).jsonString().contains("CabinPreference");

//			System.out.println("isCabinPreference " + isCabinPreference);

			if (isCabinPreference) {

				if (GlobalVariable.sCabinPreference != null) {

					JSONFileString = JsonPath.parse(JSONFileString).set(
							"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchModifiersAir.CabinPreference[0].preferenceType",
							GlobalVariable.sCabinPreference.trim()).jsonString();

				}

				if (GlobalVariable.sCabinType != null) {

					JSONFileString = JsonPath.parse(JSONFileString).set(
							"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.SearchModifiersAir.CabinPreference[0].cabins[0]",
							GlobalVariable.sCabinType.trim()).jsonString();
				}
			}

			if (GlobalVariable.upsell != null) {

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.maxNumberOfUpsellsToReturn",
						GlobalVariable.upsell.trim()).jsonString();
			}

			if (GlobalVariable.offertoreturn != null) {

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"CatalogProductOfferingsQueryRequest.CatalogProductOfferingsRequest.maxNumberOfOffersToReturn",
						GlobalVariable.offertoreturn.trim()).jsonString();
			}

//		JsonNode jsonnode = mapper.readTree(sRequestBody);
//		System.out.println(jsonnode.toPrettyString());

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		return JSONFileString;

	}

	public static String ParameterizePriceRequest(Scenario scenario) throws Exception {

		JsonSlurper newparser = new JsonSlurper();
//			Object jsonResp = newparser.parse(new FileReader(jsonDataInFile));
		String sRequestType = sFilePath.trim() + "\\Price_Payload\\" + scenario.RequestType.trim() + ".json";
		ObjectMapper mapper = new ObjectMapper();
		File jsonDataInFile = new File(sRequestType);
		Object jsonResp = newparser.parse(new FileReader(jsonDataInFile));
		String JSONFileString = new JsonBuilder(jsonResp).toString();
		JSONFileString = new JsonBuilder(jsonResp).toString();
		JSONParser jsonparser = new JSONParser();
		Object obj = jsonparser.parse(JSONFileString);
		JSONObject jsonObject = (JSONObject) obj;
		JSONArray jsonarray = new JSONArray();
		String sProductOfferingsToken = GlobalVariable.sToken.toString().trim();
		String sProductRefToken = GlobalVariable.sProductRef.toString().trim();
		String sProductBrandOptionsToken = GlobalVariable.sProductBrandOptionsToken.toString().trim();
		int i = 0;
		String s1stPtcType;
		String s1stPtcCnt = null;

		try {

			ArrayList<Integer> iConfigurationInfo = new ArrayList<Integer>();
			iConfigurationInfo = JsonPath.parse(JSONFileString)
					.read("OfferQueryBuildFromProductsAtomic.RuntimeAccessGroup.ConfigurationInfo");

			System.out.println("iConfigurationInfo" + iConfigurationInfo.size());

			for (int j = 0; j < iConfigurationInfo.size(); j++) {

				String sName = JsonPath.parse(JSONFileString)
						.read("OfferQueryBuildFromProductsAtomic.RuntimeAccessGroup.ConfigurationInfo[" + j + "].name");

//				System.out.println("sName" + sName);

				if (sName.contains("BSPCode")) {

					JSONFileString = JsonPath.parse(JSONFileString).set(
							"OfferQueryBuildFromProductsAtomic.RuntimeAccessGroup.ConfigurationInfo[" + j + "].value",
							GlobalVariable.BSPCode).jsonString();

				} else if (sName.contains("IATAnumber")) {

					JSONFileString = JsonPath.parse(JSONFileString).set(
							"OfferQueryBuildFromProductsAtomic.RuntimeAccessGroup.ConfigurationInfo[" + j + "].value",
							GlobalVariable.Iatanumber).jsonString();

				} else if (sName.contains("AgencyRegion")) {

					JSONFileString = JsonPath.parse(JSONFileString).set(
							"OfferQueryBuildFromProductsAtomic.RuntimeAccessGroup.ConfigurationInfo[" + j + "].value",
							GlobalVariable.CityCode).jsonString();

				} else if (sName.contains("AgencyCountry")) {

					JSONFileString = JsonPath.parse(JSONFileString).set(
							"OfferQueryBuildFromProductsAtomic.RuntimeAccessGroup.ConfigurationInfo[" + j + "].value",
							GlobalVariable.AgencyCountryCode).jsonString();

				} else if (sName.contains("PseudoCityCode") && !sName.contains("AgencyPFARPseudoCityCode")) {

					JSONFileString = JsonPath.parse(JSONFileString).set(
							"OfferQueryBuildFromProductsAtomic.RuntimeAccessGroup.ConfigurationInfo[" + j + "].value",
							GlobalVariable.PseudoCityCode).jsonString();

				} else if (sName.contains("HCAprofileId")) {

					if (GlobalVariable.HCAprofileId != null) {

						JSONFileString = JsonPath.parse(JSONFileString)
								.set("OfferQueryBuildFromProductsAtomic.RuntimeAccessGroup.ConfigurationInfo[" + j
										+ "].value", GlobalVariable.HCAprofileId)
								.jsonString();
					}

				} else if (sName.contains("GDSaffinity") && !sName.contains("AgencyPFARGDSaffinity")) {

					JSONFileString = JsonPath.parse(JSONFileString).set(
							"OfferQueryBuildFromProductsAtomic.RuntimeAccessGroup.ConfigurationInfo[" + j + "].value",
							GlobalVariable.CoreAffinity).jsonString();

				} else if (sName.contains("CurrencyCode")) {

					JSONFileString = JsonPath.parse(JSONFileString).set(
							"OfferQueryBuildFromProductsAtomic.RuntimeAccessGroup.ConfigurationInfo[" + j + "].value",
							GlobalVariable.CurrencyCode).jsonString();

				} else if (sName.contains("CustomerCountry")) {

					JSONFileString = JsonPath.parse(JSONFileString).set(
							"OfferQueryBuildFromProductsAtomic.RuntimeAccessGroup.ConfigurationInfo[" + j + "].value",
							GlobalVariable.CountryCode).jsonString();
					
//					JSONFileString = JsonPath.parse(JSONFileString).set(
//							"OfferQueryBuildFromProductsAtomic.RuntimeAccessGroup.ConfigurationInfo[" + j + "].value",
//							"ES").jsonString();

				}

			}

			JSONFileString = JsonPath.parse(JSONFileString)
					.set("OfferQueryBuildFromProductsAtomic.CatalogOffering.Identifier.value", sProductOfferingsToken)
					.jsonString();

			ArrayList<Integer> ProductOptions = new ArrayList<Integer>();
			ProductOptions = JsonPath.parse(JSONFileString)
					.read("OfferQueryBuildFromProductsAtomic.CatalogOffering.ProductOptions");

			 
			for (int k = 0; k < ProductOptions.size(); k++) {
//			for (int k = 0; k < GlobalVariable.sProductBrandOptionsToken.size(); k++) {
				
				String sToken = GlobalVariable.sProductBrandOptionsToken.get(k); 
				JSONFileString = JsonPath.parse(JSONFileString).set(
						"OfferQueryBuildFromProductsAtomic.CatalogOffering.ProductOptions[" + k + "].Identifier.value",
						sToken).jsonString();

//			JSONFileString = JsonPath.parse(JSONFileString)
//					.set("OfferQueryBuildFromProductsAtomic.CatalogOffering.ProductOptions[1].Identifier.value",sProductBrandOptionsToken).jsonString();

			}

			JSONFileString = JsonPath.parse(JSONFileString)
					.set("OfferQueryBuildFromProductsAtomic.CatalogOffering.ProductOptions[0].Identifier.authority",
							GlobalVariable.Carrier.toString())
					.jsonString();

//			for (i = 0; i < sPassengerCountTag.size(); i++) {
			for (String sPTC : scenario.sPtccnt.keySet()) {

//			System.out.println("key: " + sPTC + " value: " + scenario.sPtccnt.get(sPTC));

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"OfferQueryBuildFromProductsAtomic.OfferQueryBuildFromProducts.BuildFromProductsRequest.PassengerCriteria["
								+ i + "].passengerTypeCode",
						sPTC).jsonString();

				JSONFileString = JsonPath.parse(JSONFileString).set(
						"OfferQueryBuildFromProductsAtomic.OfferQueryBuildFromProducts.BuildFromProductsRequest.PassengerCriteria["
								+ i + "].number",
						scenario.sPtccnt.get(sPTC)).jsonString();

				i = i + 1;

			}

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		return JSONFileString;

	}

	public static String PriceRequest(Scenario scenario) throws Exception {

		String sRequest = ParameterizePriceRequest(scenario);
//		System.out.println(sRequest);
		JsonOutput jsonOutput = new JsonOutput();
		JSONParser parser = new JSONParser();

		String JSONFileString = null;
		String sConfigInfo = null;
		URL = Endpoint.baseUrl;
		String sResponseBody = null;
//		Object obj = parser.parse(new FileReader(jsonDataInFile));
//		JSONObject jsonObject = (JSONObject) obj;
		List<String> infoList = new ArrayList();
		ObjectMapper mapper = new ObjectMapper();

		try {

			JsonSlurper newparser = new JsonSlurper();
//			JsonNode jsonnode = mapper.readTree(JSONFileString);
			JSONArray ja = new JSONArray();

			RequestSpecification sPriceRequest = RestAssured.given().baseUri(URL).contentType(ContentType.JSON)
					.body(sRequest).header("Content-Type", "application/json").header("Accept", "application/json")
					.header("CityCode", GlobalVariable.CityCode)
					.header("domainlistenerchannelid", GlobalVariable.domainlistener)
					.header("IATANumber", GlobalVariable.Iatanumber)
					.header("E2ETrackingID", "E2ETrackingId")
					.header("PseudoCityCode", GlobalVariable.PseudoCityCode)
					.header("CurrencyCode", GlobalVariable.CurrencyCode)
					.header("ReservationResource_Identifier", GlobalVariable.Resource_Identifier)
					.header("DomainRegion", GlobalVariable.DomainRegion)
					.header("CountryCode", GlobalVariable.CountryCode)
					.header("CoreAffinity", GlobalVariable.CoreAffinity)
					.header("OAUTH_RESOURCEOWNERINFO", GlobalVariable.oAuthResourceInfo)
					.header("XAUTH_TRAVELPORT_ACCESSGROUP", GlobalVariable.Accessgroup).header("ChannelId", "1234")
					.header("PCC", GlobalVariable.PCC).header("scc-rule-match-id", "test1234567")
					.header("traceId", "srip_01").header("IDM_CARRIER_LIST", GlobalVariable.IDM_CARRIER_LIST)
					;

			FilterableRequestSpecification requestObject = (FilterableRequestSpecification) sPriceRequest;

			Response sPriceResponse = (Response) sPriceRequest.when().post().then().contentType(ContentType.JSON)
					.extract().response().body();

			boolean sResponseStatus = sPriceResponse.getStatusCode() == 200;
			// String sRequestBody = JSONFileString.toString();
			String sRequestBody = requestObject.getBody().toString();
			String sRequestHeader = requestObject.getHeaders().toString();
			
			String sResponseHeader = sPriceResponse.getHeaders().toString();
			sResponseBody = sPriceResponse.asString();
			String url = requestObject.getBaseUri();
			String method = requestObject.getMethod();
			String sContentType = sPriceResponse.contentType();

			if (sResponseStatus) {

				infoList.add(scenario.version + " - Price Request Successfull");

			} else {

				infoList.add(scenario.version + ":-Price Request fails");

			}
			GlobalVariable.objScenarioData = scenario;

			ExtendReportCreation.addRequestResponse(sRequestBody, sResponseBody, sResponseStatus, scenario.servicename,
					infoList, WebServiceUtil.getRequestHeadersInfo(requestObject),
					WebServiceUtil.getResponseHeaderInfo(sPriceResponse));

		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return sResponseBody;

	}

// 

	public static Map<String, String> RetrieveTokens(String sResponse) throws Exception {

		Scenario scenario = GlobalVariable.objScenarioData;
		ArrayList<String> sValueList = new ArrayList();
		JSONParser parser = new JSONParser();
		String sVersion = scenario.version.trim();
		HashMap<String, ArrayList<String>> MultipleValue = new HashMap<String, ArrayList<String>>();
		String sProductBrandOptionsToken = ""; 
		String sProductOfferingsToken = JsonPath.read(sResponse,
				"CatalogProductOfferingsResponse.CatalogProductOfferings.CatalogProductOffering[0].Identifier.value");
		sToken.put("ProductOfferingsToken", sProductOfferingsToken);

//		x.CatalogProductOfferingsResponse.CatalogProductOfferings.CatalogProductOffering[0].ProductBrandOptions[0].ProductBrandOffering[0].Product[0].productRef

		String sProductRefToken = JsonPath.read(sResponse,
				"CatalogProductOfferingsResponse.CatalogProductOfferings.CatalogProductOffering[0].ProductBrandOptions[0].ProductBrandOffering[0].Product[0].productRef");
//			   x.CatalogProductOfferingsResponse.CatalogProductOfferings.CatalogProductOffering[0].ProductBrandOptions[0].ProductBrandOffering[0].Product[0].productRef
		sToken.put("ProductRefToken", sProductRefToken);

		ArrayList<Integer> iProductBrandOptionsToken = new ArrayList<Integer>();
		iProductBrandOptionsToken = JsonPath.read(sResponse,
				"CatalogProductOfferingsResponse.CatalogProductOfferings.CatalogProductOffering[0].ProductBrandOptions");

		for (int i = 0; i < iProductBrandOptionsToken.size(); i++) {

			sProductBrandOptionsToken = JsonPath.read(sResponse,
					"CatalogProductOfferingsResponse.CatalogProductOfferings.CatalogProductOffering[0].ProductBrandOptions["+i+"].ProductBrandOffering[0].Identifier.value");
			
			GlobalVariable.sProductBrandOptionsToken.add(sProductBrandOptionsToken);
			
		}

//		       x.CatalogProductOfferingsResponse.CatalogProductOfferings.CatalogProductOffering[0].ProductBrandOptions[0].ProductBrandOffering[0].Identifier.value
		System.out.println(GlobalVariable.sProductBrandOptionsToken);

//		System.out.println("sProductRefToken"+sProductRefToken);		
//		System.out.println("sProductOfferingsToken"+sProductOfferingsToken);
//		System.out.println("sVersion"+sVersion);		
		sValueList.add(0, sProductOfferingsToken);
		sValueList.add(1, sProductRefToken);
		sValueList.add(2, sProductBrandOptionsToken);
		MultipleValue.put(sVersion, sValueList);
//		System.out.println(MultipleValue);
		scenario.sMultipleValue.put(sVersion, sValueList);
//		System.out.println(scenario.sMultipleValue);
//		SearchNextLeg(sToken);		
		GlobalVariable.objScenarioData = scenario;
		return sToken;

	}

	public static String SearchNextLeg(Scenario scenario) throws Exception {

//		String sScenarioName = "<span style=color:white>Test Step:- " + scenario.version.trim() + ":- "
//				+ scenario.RequestType.trim() + "_Request & Response<br></span><span style = color:white;> </span>";
//		new ExtendReportCreation().createStepNodeInstance(sScenarioName);

		JsonOutput jsonOutput = new JsonOutput();
		JSONParser parser = new JSONParser();
		String sRequestType = sFilePath.trim() + scenario.RequestType.trim() + ".json";
		File jsonDataInFile = new File(sRequestType);
		String sProductOfferingsToken = GlobalVariable.sToken.toString().trim();
		String sProductRefToken = GlobalVariable.sProductRef.toString().trim();
		String JSONFileString = null;
		URL = Endpoint.baseUrl;
		String sResponseBody = null;
		Object obj = parser.parse(new FileReader(jsonDataInFile));
		JSONObject jsonObject = (JSONObject) obj;
		List<String> infoList = new ArrayList();
		ObjectMapper mapper = new ObjectMapper();
//		System.out.println("GlobalVariable.sToken" + GlobalVariable.sToken);
//		System.out.println("GlobalVariable.sProductRef" + GlobalVariable.sProductRef);

		try {

			JsonSlurper newparser = new JsonSlurper();
			Object jsonResp = newparser.parse(new FileReader(jsonDataInFile));

//			Object jsonResp = newparser.parseText(sSearchResponse.asString());

			JSONFileString = new JsonBuilder(jsonResp).toString();
			JSONFileString = JsonPath.parse(JSONFileString).set(
					"CatalogProductOfferingsQueryBuildNext.BuildFromCatalogProductOfferingsRequest.CatalogProductOfferingSelection[0].CatalogProductOfferingIdentifier.Identifier.value",
					sProductOfferingsToken).jsonString();

			JSONFileString = JsonPath.parse(JSONFileString).set(
					"CatalogProductOfferingsQueryBuildNext.BuildFromCatalogProductOfferingsRequest.CatalogProductOfferingSelection[0].ProductIdentifier[0].Identifier.value",
					sProductRefToken).jsonString();
			JsonNode jsonnode = mapper.readTree(JSONFileString);
//			System.out.println(jsonnode.toPrettyString());

//			JSONFileString = generateStringFromResource(sRequestType).replace("sProductOfferingsToken",
//					sProductOfferingsToken);
//
//			JSONFileString = JSONFileString.replace("sProductRefToken", sProductRefToken);

			String jsonfile = "{\r\n" + "   \"CatalogProductOfferingsQueryBuildNext\" : {\r\n"
					+ "      \"@type\" : \"CatalogProductOfferingsQueryBuildNext\",\r\n"
					+ "      \"BuildFromCatalogProductOfferingsRequest\" : {\r\n"
					+ "         \"@type\" : \"BuildFromCatalogProductOfferingsRequest\",\r\n"
					+ "         \"CatalogProductOfferingsIdentifier\" : {\r\n" + "            \"Identifier\" : {\r\n"
					+ "               \"value\" : \"pseudoshopffsntac:E2ETrackingId\"\r\n" + "            }\r\n"
					+ "         },\r\n" + "         \"CatalogProductOfferingSelection\" : [\r\n" + "            {\r\n"
					+ "               \"@type\" : \"CatalogProductOfferingSelection\",\r\n"
					+ "               \"CatalogProductOfferingIdentifier\" : {\r\n"
					+ "                  \"Identifier\" : {\r\n" + "                     \"value\" : \""
					+ sProductOfferingsToken + "\"\r\n" + "                  }\r\n" + "               },\r\n"
					+ "               \"ProductIdentifier\" : [\r\n" + "                  {\r\n"
					+ "                     \"Identifier\" : {\r\n" + "                        \"value\" : \""
					+ sProductRefToken + "\"\r\n" + "                     }\r\n" + "                  }\r\n"
					+ "               ]\r\n" + "            }\r\n" + "         ]\r\n" + "      }\r\n" + "   }\r\n"
					+ "}";

			JSONArray ja = new JSONArray();

			RequestSpecification sSearchRequest = RestAssured.given().baseUri(URL).contentType(ContentType.JSON)
					.body(JSONFileString).header("Content-Type", "application/json")
					.header("Accept", "application/json").header("CityCode", GlobalVariable.CityCode)
					.header("domainlistenerchannelid", GlobalVariable.domainlistener)
					.header("IATANumber", GlobalVariable.Iatanumber).header("E2ETrackingID", "E2ETrackingId")
					.header("PseudoCityCode", GlobalVariable.PseudoCityCode)
					.header("CurrencyCode", GlobalVariable.CurrencyCode)
					.header("ReservationResource_Identifier", "ReservationResource_Identifier")
					.header("IDM_CARRIER_LIST", GlobalVariable.IDM_CARRIER_LIST)
					.header("DomainRegion", GlobalVariable.DomainRegion)
					.header("CountryCode", GlobalVariable.CountryCode)
					.header("CoreAffinity", GlobalVariable.CoreAffinity);

			FilterableRequestSpecification requestObject = (FilterableRequestSpecification) sSearchRequest;

			Response sSearchResponse = (Response) sSearchRequest.when().post().then().contentType(ContentType.JSON)
					.extract().response().body();

			boolean sResponseStatus = sSearchResponse.getStatusCode() == 200;
//			String sRequestBody = JSONFileString.toString();
			String sRequestBody = requestObject.getBody().toString();
			String sRequestHeader = requestObject.getHeaders().toString();
			String sResponseHeader = sSearchResponse.getHeaders().toString();
			sResponseBody = sSearchResponse.asString();
			String url = requestObject.getBaseUri();
			String method = requestObject.getMethod();
			String sContentType = sSearchResponse.contentType();

			if (sResponseStatus) {

				infoList.add(scenario.version + " - SearchNextLeg Request Successfull");

			} else {

				infoList.add(scenario.version + ":-SearchNextLeg Request fails");

			}
			GlobalVariable.objScenarioData = scenario;

			ExtendReportCreation.addRequestResponse(sRequestBody, sResponseBody, sResponseStatus, scenario.servicename,
					infoList, WebServiceUtil.getRequestHeadersInfo(requestObject),
					WebServiceUtil.getResponseHeaderInfo(sSearchResponse));

//			ScenarioStorage.addRequestResponseToHtmlReport("RequestHeader",WebServiceUtil.getRequestHeadersInfo(requestObject),sContentType,scenario.servicename);
//			ScenarioStorage.addRequestResponseToHtmlReport("ResponseHeader",WebServiceUtil.getResponseHeaderInfo(sSearchResponse),sContentType,scenario.servicename);
//			ScenarioStorage.addRequestResponseToHtmlReport("Request",sRequestBody,sContentType,scenario.servicename);
//			ScenarioStorage.addRequestResponseToHtmlReport("Response",sResponseBody,sContentType,scenario.servicename);

		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return sResponseBody;
	}

	private static String generateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));

	}

//	public static void UpdateJsonFile(Response sSearchResponse) throws IOException, ParseException {
	public static void UpdateJsonFile() throws IOException, ParseException {

		JsonOutput jsonOutput = new JsonOutput();
		JSONParser parser = new JSONParser();
		String sRequestType = sFilePath.trim() + "Search_FareFamily_NextLeg.json";
		File jsonDataInFile = new File(sRequestType);
		FileReader FileReader = new FileReader(jsonDataInFile);

		System.out.println(FileReader.toString());
		Object obj = parser.parse(new FileReader(jsonDataInFile));
//		JSONObject jsonObject = (JSONObject) obj;
		List<String> infoList = new ArrayList();
//		JSONArray obj = (JSONArray) parser.parse(new FileReader(sFilePath));
//		Object obj = parser.parse(new FileReader(jsonDataInFile));
//		System.out.println(jsonObject.toJSONString()); 

		JsonSlurper newparser = new JsonSlurper();
		Object jsonResp = newparser.parse(new FileReader(jsonDataInFile));
//		Object jsonResp = newparser.parseText(sSearchResponse.asString());

		String json = new JsonBuilder(jsonResp).toString();
//		Object json = new JsonBuilder(jsonResp).getContent();
		ObjectMapper mapper = new ObjectMapper();

		String NewToken = JsonPath.parse(json.toString()).set(
				"CatalogProductOfferingsQueryBuildNext.BuildFromCatalogProductOfferingsRequest.CatalogProductOfferingSelection[0].CatalogProductOfferingIdentifier.Identifier.value",
				"hello").jsonString();
//		System.out.println(NewToken);

		NewToken = JsonPath.parse(NewToken.toString()).set(
				"CatalogProductOfferingsQueryBuildNext.BuildFromCatalogProductOfferingsRequest.CatalogProductOfferingSelection[0].ProductIdentifier[0].Identifier.value",
				"hi").jsonString();
//		System.out.println(NewToken);
		JsonNode jsonnode = mapper.readTree(NewToken);
		// x.CatalogProductOfferingsQueryBuildNext.BuildFromCatalogProductOfferingsRequest.CatalogProductOfferingSelection[0].CatalogProductOfferingIdentifier.Identifier.value

		// x.CatalogProductOfferingsQueryBuildNext.BuildFromCatalogProductOfferingsRequest.CatalogProductOfferingSelection[0].ProductIdentifier[0].Identifier.value

		// De-serialize to an object
//		JsonNode jsonnode = mapper.readTree(sSearchResponse.asString());
//		JsonNode jsonnode = mapper.readTree(new FileReader(jsonDataInFile));
//		for (JsonNode doc: jsonnode) { // this is pseudo-code
//			  String s = doc.path("CatalogProductOfferingsQueryBuildNext.BuildFromCatalogProductOfferingsRequest.CatalogProductOfferingSelection").asText();
//			  System.out.println(doc);
//			}

//		System.out.println(jsonnode.toPrettyString()); // John
//		System.out.println(jsonnode.get("CatalogProductOfferingsQueryBuildNext.BuildFromCatalogProductOfferingsRequest.CatalogProductOfferingSelection[0].CatalogProductOfferingIdentifier.Identifier.value").asText()); //John
//		
//		Configuration config = Configuration.builder().jsonProvider(new JacksonJsonNodeJsonProvider())
//		.mappingProvider(new JacksonMappingProvider()).build();
//
//		JsonNode newJson = JsonPath.using(config).parse(sSearchResponse.toString()).set("CatalogProductOfferingsResponse.CatalogProductOfferings.CatalogProductOffering[0].Identifier.value", "UPDATED").json();
//		System.out.println(newJson.toString());

//		String NewToken = JsonPath.parse(sSearchResponse.asString()).set("CatalogProductOfferingsResponse.CatalogProductOfferings.CatalogProductOffering[0].Identifier.value","hello").jsonString();
//		
//		String sToken = JsonPath.read(sSearchResponse.asString(),
//				"CatalogProductOfferingsResponse.CatalogProductOfferings.CatalogProductOffering[0].Identifier.value");
//
//		System.out.println("sToken" + NewToken);

		// Read a single attribute
//		JsonNode nameNode = mapper.readTree("{\"name\": \"John\"}");

//		JSONObject CatalogProductOfferingsResponse = (JSONObject) jsonObject.get("CatalogProductOfferingsResponse");
//		JSONObject CatalogProductOfferings = (JSONObject) CatalogProductOfferingsResponse
//				.get("CatalogProductOfferings");
//		JSONArray CatalogProductOffering = (JSONArray) CatalogProductOfferings.get("CatalogProductOffering");
//		JSONObject Identifiers = (JSONObject) CatalogProductOffering.get(0);
//		JSONObject Identifier = (JSONObject) Identifiers.get("Identifier");
//		String sProductOfferingsToken = Identifier.get("value").toString();
//		sToken.put("ProductOfferingsToken", sProductOfferingsToken);
//
//	    System.out.println("sProductOfferingsToken"+sProductOfferingsToken);
//	    x.CatalogProductOfferingsQueryBuildNext.BuildFromCatalogProductOfferingsRequest.CatalogProductOfferingSelection[0].CatalogProductOfferingIdentifier.Identifier.value

//		x.CatalogProductOfferingsResponse.CatalogProductOfferings.CatalogProductOffering[0].Identifier.value
	}

	//
//	sValueList.add(0, sToken);
//	sValueList.add(1, sProductRefToken);
//	scenario.sMultipleValue.put(sVersion,sValueList);
//	GlobalVariable.objScenarioData = scenario;

//	URL obj = new URL(url);
//		Map<String,Object> params = new LinkedHashMap();
//		params.put(url, params);
//	params.put(url, params);
//		params.put(url, params);
//		params.put(url, params);/		 
//
//		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
//		con.setRequestMethod("POST");
//		con.setRequestProperty("Content-Type", "application/json");
//		con.setRequestProperty("XAUTH_TRAVELPORT_ACCESSGROUP", "7C7ED10A-EEBC-4468-B499-879DE63F1B7D");
//		con.setRequestProperty("mergeDiagFlag", "false");
//		con.setRequestProperty("Authorization", "Basic VW5pdmVyc2FsIEFQSS91QVBJNDM1NDgyNTAwMC0yNDk1YTRlNjpwN0EzeVV6RA==");
//		con.setRequestProperty("OAUTH_RESOURCEOWNERINFO", "7C7ED10A-EEBC-4468-B499-879DE63F1B7D");
//		con.setRequestProperty("Accept", "application/json");

//	JSONObject ProductOfferingsResponse = (JSONObject) jsonObject.get("CatalogProductOfferingsResponse");
//	JSONObject CatalogProduct = (JSONObject) ProductOfferingsResponse.get("CatalogProductOfferings");
//	JSONArray ProductOfferings = (JSONArray) CatalogProduct.get("CatalogProductOffering");
//	JSONObject ProductOffering = (JSONObject) ProductOfferings.get(0);
//	JSONArray ProductBrandOptions = (JSONArray) ProductOffering.get("ProductBrandOptions");
//	JSONObject ProductBrandOption = (JSONObject) ProductBrandOptions.get(0);
//	JSONArray ProductBrandOfferings = (JSONArray) ProductBrandOption.get("ProductBrandOffering");
//	JSONObject ProductBrandOffering = (JSONObject) ProductBrandOfferings.get(0);
//	JSONArray Product = (JSONArray) ProductBrandOffering.get("Product");
//	JSONObject ProductRef = (JSONObject) Product.get(0);
//	String sProductRefToken = ProductRef.get("productRef").toString();
//	sToken.put("ProductRefToken", sProductRefToken);

//	System.out.println("the url is " + URL);
//	System.out.println("the method type is  " + method);
//	System.out.println("Request parameters "+requestObject.getHttpClient().getParams());
//	System.out.println("Request body "+jsonObject.toJSONString());
//	System.out.println("Request headers are" +requestObject.getHeaders());
//	System.out.println("Response headers are" +sSearchResponse.getHeaders());//
//	String Requestheaders = WebServiceUtil.getRequestHeadersInfo(requestObject);
//	String ResponseHeaders = WebServiceUtil.getResponseHeaderInfo(sSearchResponse);
//	System.out.println("Requestheaders" + Requestheaders);
//	System.out.println("ResponseHeaders" + ResponseHeaders);
//	System.out.println("sResponseBody" + sResponseBody);

//	JSONParser parser = new JSONParser();			 			
//	JSONArray obj = (JSONArray) parser.parse(new FileReader(sFilePath));
//	Object obj = parser.parse(new FileReader(jsonDataInFile));
//	Object obj = parser.parse(jsonDataInFile);
//	JSONObject jsonObject = (JSONObject) obj;
//	JSONObject QueryBuildNext = (JSONObject) jsonObject.get("CatalogProductOfferingsQueryBuildNext");
//	JSONObject BuildFromCatalogProduct = (JSONObject) QueryBuildNext
//			.get("BuildFromCatalogProductOfferingsRequest");
//	JSONArray CatalogProductOfferingSelection = (JSONArray) BuildFromCatalogProduct
//			.get("CatalogProductOfferingSelection");
//	JSONObject ProductOfferingSelection = (JSONObject) CatalogProductOfferingSelection.get(0);
//	JSONObject CatalogProductOfferingIdentifier = (JSONObject) ProductOfferingSelection
//			.get("CatalogProductOfferingIdentifier");
//	JSONObject Identifier = (JSONObject) CatalogProductOfferingIdentifier.get("Identifier");
//	Identifier.put("value", sProductOfferingsToken);
//
//	JSONArray ProductOfferings = (JSONArray) ProductOfferingSelection.get("ProductIdentifier");
//	JSONObject ProductOffering = (JSONObject) ProductOfferings.get(0);
//	JSONObject ProductOfferingsIdentifier = (JSONObject) ProductOffering.get("Identifier");
//	ProductOfferingsIdentifier.put("value", sProductRefToken);

//	ja.add(ProductOfferingsIdentifier); 
//	sToken.put("CatalogIdentifierToken", CatalogIdentifierToken);
//	String ProductOfferingsToken = ProductOfferingsIdentifier.get("value").toString();
//	System.out.println("ProductOfferingsToken is "+ProductOfferingsToken);			
//	sToken.put("ProductOfferingsToken", ProductOfferingsToken);
//	given().body(JSONFileString).post(URL).then().statusCode(201);
//	System.out.println(jsonObject.toJSONString());
//	String jsonDataInFile = jsonObject.toJSONString();
//	System.out.println(JsonOutput.prettyPrint(JSONFileString.toString()));
//	obj = parser.parse(jsonObject.toJSONString());
//	jsonObject = (JSONObject) obj; 
//	System.out.println(JsonOutput.prettyPrint(jsonObject.toString()));

//	Object obj = parser.parse(sResponse);
//	JSONObject jsonObject = (JSONObject) obj;
//	JSONObject CatalogProductOfferingsResponse = (JSONObject) jsonObject.get("CatalogProductOfferingsResponse");
//	JSONObject CatalogProductOfferings = (JSONObject) CatalogProductOfferingsResponse
//			.get("CatalogProductOfferings");
//	JSONArray CatalogProductOffering = (JSONArray) CatalogProductOfferings.get("CatalogProductOffering");
//	JSONObject Identifiers = (JSONObject) CatalogProductOffering.get(0);
//	JSONObject Identifier = (JSONObject) Identifiers.get("Identifier");
//	String sProductOfferingsToken = Identifier.get("value").toString();
//	sToken.put("ProductOfferingsToken", sProductOfferingsToken);

//	System.out.println("the url is " + URL);
//	System.out.println("the method type is  " + method);
//	System.out.println("Request parameters "+requestObject.getHttpClient().getParams());
//	System.out.println("Request body " + sRequestBody);
//	System.out.println("Request headers are" +requestObject.getHeaders());
//	System.out.println("Response headers are" +sSearchResponse.getHeaders());//
//	String Requestheaders = WebServiceUtil.getRequestHeadersInfo(requestObject);
//	String ResponseHeaders = WebServiceUtil.getResponseHeaderInfo(sSearchResponse);
//	System.out.println("Requestheaders" + Requestheaders);
//	System.out.println("ResponseHeaders" + ResponseHeaders);
//	System.out.println("sResponseBody" + sResponseBody);
}
