package Comparision;

import java.io.File;
import java.io.FileNotFoundException;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.specification.QueryableRequestSpecification;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import com.aventstack.extentreports.Status;

import org.apache.commons.io.FileUtils;
import io.restassured.response.Response;

public class CucumberHtmlReport {


////	@After
//	public void tearDown(Scenario scenario) throws IOException {
//		
//		File file;
//		ChromeDriver driver = new ChromeDriver();
//		if (scenario.isFailed()) {
//			
//			String screenshotPath = System.getProperty("user.dir") + "\\target\\screenshots\\" + scenario.getName()
//					+ "\\";
//			file = new File(screenshotPath);
//			file.mkdir();
//			try {
//
//				final File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//				FileUtils.copyFile(scrFile, new File(screenshotPath + "screenshot.png"));
//
//			} catch (final Exception e) {
//				e.printStackTrace();
//			}
//			String failedScreenShot = screenshotPath + "screenshot.png";
//			String url = "<img src=" + failedScreenShot + " alt='failed screenshot'>";
//			//scenario.embed(url.getBytes(),"png", "Click Here To See Screenshot");
//
//  			scenario.attach(screenshotPath, "image/png", scenario.getName());
//		}
//	}
//
////	public static void addRequestResponseToHtmlReport(QueryableRequestSpecification request, Response response,
////			String baseUrl) {
// 
//	public static void addRequestResponseToHtmlReport(String RequestType, String ReqRes, String ContentType, String apiName) throws FileNotFoundException, IOException {
//
//		
//		
//		
//		if (apiName != null) {
//			
//			
////			ScenarioStorage.getScenario().log(ScenarioStorage.attachReport(request, response, response));
////			
////			ScenarioStorage.getScenario().log(ScenarioStorage.attachReport(request, response, response));
//			
//			
////			ScenarioStorage.getScenario().attach(ScenarioStorage.attachReport(RequestType, ReqRes, apiName), RequestType, apiName);
//			
//			ScenarioStorage.getScenario().attach(ScenarioStorage.attachReport(RequestType, ReqRes, apiName), ContentType, apiName);
//			
//		
//			
////			CucumberHtmlReport.addRequestResponseToHtmlReport(sRequestBody,sResponseBody,scenario.servicename);
////			ScenarioStorage.getScenario().write("baseUrl :" + baseUrl);
////			ScenarioStorage.getScenario().write("Request:");
////			
////			ScenarioStorage.getScenario().attach(baseUrl, "json", "hello");
////			ScenarioStorage.getScenario().attach(baseUrl, "json", "hello");
//			
////			ScenarioStorage.getScenario().log(ScenarioStorage.AttachRequestResponse(response));
//			
////			ScenarioStorage.AttachRequestResponse(response);
//			 
//		}
//
////		if (request != null) {
////			ScenarioStorage.getScenario().write("Headers :" + request.getHeaders());
////			ScenarioStorage.getScenario().write("HttpMethod :" + request.getMethod());
////			ScenarioStorage.getScenario().write("Body :" + request.getBody());
////		}
////
////		if (response != null) {
////			ScenarioStorage.getScenario().write("Response:" + response.prettyPrint());
////		}
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//		
	}
	


