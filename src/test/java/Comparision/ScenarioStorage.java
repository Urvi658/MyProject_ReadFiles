package Comparision;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import groovy.json.JsonOutput;
import io.cucumber.java.Scenario;

public class ScenarioStorage {

	private static final HashMap<Thread, Scenario> map = new HashMap<>();
//	 private static Scenario scenario;

//	 public void beforetest() {
//		 
//		 this.scenario = scenario;
//	 }

	public static void putScenario(Scenario scenario) {

		map.put(Thread.currentThread(), scenario);

	}

	public static Scenario getScenario() {

		return map.get(Thread.currentThread());

	}

	static String attachReport(String reportType, String ReqRes, String apiName) throws IOException {

		String attachReport = AttachRequestResponse(reportType, ReqRes, apiName);

		String reportLink = apiName + "-" + reportType + " : "
				+ "<a style=font-weight:bold target=_blank rel=noopener noreferrer href=" + attachReport + ">"
				+ reportType + "-Body " + "</a>";

		return reportLink;

	}

	public static String AttachRequestResponse(String reportType, String ReqRes, String api) throws IOException {

		String currentDir = System.getProperty("user.dir");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HHmmss");

		String start_time = sdf.format(date);

		currentDir = currentDir + "\\target\\" + api;

		File file = new File(currentDir);

		if (!file.exists()) {

//			System.out.println("not exist");
			file.mkdir();

		}else {
			
//			System.out.println("Directory exist");
		}
		
		String ReqResJSON = JsonOutput.prettyPrint(ReqRes);
		String JSONPath = currentDir + "\\" + reportType + "_" + start_time + ".json";

		try (OutputStreamWriter fileJson = new OutputStreamWriter(new FileOutputStream(JSONPath),
				StandardCharsets.UTF_8)) {

			fileJson.write(ReqResJSON);

		}

//		JSONPath = api + "\\ReqRes" + start_time + ".json";	

//		try (OutputStreamWriter oResponseInputFile = new OutputStreamWriter(new FileOutputStream(JsonPath),
//				StandardCharsets.UTF_8)) {
//			
//			oResponseInputFile.write(ReqRes);
//			
//		}

		return JSONPath;

	}

	static void addPassAndFailComment(boolean sBoolean, String infoMsg) {

//		CustomReport customReport = new CustomReport();

		if (sBoolean) {

			// String sStatus = (infoMsg)
			String sStatus = ("<html><body><p style=color:green;>" + infoMsg + "</p></body></html>");

//			ExtendReportCreation.node.log(Status.PASS, sStatus);

		} else {

			String sStatus = (infoMsg);
			// String sStatus = ('<html><body><p
			// style=color:yellow;>'+infoMsg+'</p></body></html>')
//			ExtendReportCreation.node.log(Status.FAIL, sStatus);

		}

	}
	
	
	
	//@After
	/*public void tearDown(Scenario scenario) throws IOException {
		
		File file;
		ChromeDriver driver = new ChromeDriver();
		if (scenario.isFailed()) {
			
			String screenshotPath = System.getProperty("user.dir") + "\\target\\screenshots\\" + scenario.getName()
					+ "\\";
			file = new File(screenshotPath);
			file.mkdir();
			try {

				final File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(screenshotPath + "screenshot.png"));

			} catch (final Exception e) {
				e.printStackTrace();
			}
			String failedScreenShot = screenshotPath + "screenshot.png";
			String url = "<img src=" + failedScreenShot + " alt='failed screenshot'>";
			//scenario.embed(url.getBytes(),"png", "Click Here To See Screenshot");

  			scenario.attach(screenshotPath, "image/png", scenario.getName());
		}
	}*/

//	public static void addRequestResponseToHtmlReport(QueryableRequestSpecification request, Response response,
//			String baseUrl) {
 
	public static void addRequestResponseToHtmlReport(String RequestType, String ReqRes, String ContentType, String apiName) throws FileNotFoundException, IOException {

		
		
		
		if (apiName != null) {
			
			
//			ScenarioStorage.getScenario().log(ScenarioStorage.attachReport(request, response, response));
//			
//			ScenarioStorage.getScenario().log(ScenarioStorage.attachReport(request, response, response));
			
			
//			ScenarioStorage.getScenario().attach(ScenarioStorage.attachReport(RequestType, ReqRes, apiName), RequestType, apiName);
			
			ScenarioStorage.getScenario().attach(ScenarioStorage.attachReport(RequestType, ReqRes, apiName), ContentType, apiName);
			
		
			
//			CucumberHtmlReport.addRequestResponseToHtmlReport(sRequestBody,sResponseBody,scenario.servicename);
//			ScenarioStorage.getScenario().write("baseUrl :" + baseUrl);
//			ScenarioStorage.getScenario().write("Request:");
//			
//			ScenarioStorage.getScenario().attach(baseUrl, "json", "hello");
//			ScenarioStorage.getScenario().attach(baseUrl, "json", "hello");
			
//			ScenarioStorage.getScenario().log(ScenarioStorage.AttachRequestResponse(response));
			
//			ScenarioStorage.AttachRequestResponse(response);
			 
		}

//		if (request != null) {
//			ScenarioStorage.getScenario().write("Headers :" + request.getHeaders());
//			ScenarioStorage.getScenario().write("HttpMethod :" + request.getMethod());
//			ScenarioStorage.getScenario().write("Body :" + request.getBody());
//		}
//
//		if (response != null) {
//			ScenarioStorage.getScenario().write("Response:" + response.prettyPrint());
//		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
