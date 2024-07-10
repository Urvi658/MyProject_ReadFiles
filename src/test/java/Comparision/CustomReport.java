package Comparision;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.model.ReportStats;
import com.aventstack.extentreports.reporter.ExtentSparkReporter; //instead of ExtentHtmlReporter
import com.aventstack.extentreports.reporter.configuration.Theme;
import groovy.json.JsonOutput;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FilenameUtils;

public class CustomReport {

	private ExtentSparkReporter extentHtmlReport;
	private ExtentReports extentReport;
	private ExtentTest testLogger;
	private ExtentTest testSuiteLogger;
	private String currentDir;
	private String fileSeperator;
	private ExtentTest testStepLogger;
	private ExtentTest testChildStepLogger;
	private ExtentTest AddTestNode;
	private GherkinKeyword gherkinKeyword;
	private Status status;
//	private ReportStatusStats;

	private static CustomReport instance = null;

	public static CustomReport getInstance() {

		if (instance == null) {

			instance = new CustomReport();
		}

		return instance;
	}

	public ExtentTest getExtentTestSuite() {

		return testSuiteLogger;

	}

	public ExtentTest getExtentTest() {
		return testLogger;
	}

	public ExtentTest getExtentTestStep() {
		
		return testStepLogger;
	}

	public void setExtentTest(ExtentTest testLogger) {
		this.testLogger = testLogger;
	}

	public void GherkinReport() {

//		String feature = extentReport.createTest(new GherkinKeyword("Feature"), "Refund item");
	}

	/**
	 * Configures and Initializes a new HTML Report for the test suite execution
	 * 
	 * @param filePath - The path where the report is going to be store
	 * @throws IOException
	 *
	 */
	public ExtentReports initExtentReportsTestSuite(String filePath) throws IOException {

		// Gets the current directory, file separator and gets the start time to prepare
		// the test file properties
		currentDir = System.getProperty("user.dir");
		fileSeperator = System.getProperty("file.separator");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HHmmss");
		String start_time = sdf.format(date);

		// Creates the HTML report
		String dirName = RunConfiguration.getProjectDir();
		extentReport = new ExtentReports();

		extentHtmlReport = new ExtentSparkReporter(FilenameUtils.separatorsToSystem(filePath));
		// println(filePath)
		extentReport.attachReporter(extentHtmlReport);

		// Loads the report configuration properties
		extentHtmlReport.loadXMLConfig("${currentDir}${fileSeperator}ExtentsConfig${fileSeperator}extent-config.xml");
		// Gets the system properties to set it on the report
		extentReport.setSystemInfo("OS", System.getProperty("os.name"));
		extentReport.setSystemInfo("Host", RunConfiguration.getHostAddress());
		extentReport.setSystemInfo("User", RunConfiguration.getHostName());
		extentReport.setSystemInfo("Environment", RunConfiguration.getExecutionProfile());
//		extentReport.setSystemInfo("Katalon Version", RunConfiguration.getAppVersion());
		extentReport.setAnalysisStrategy(AnalysisStrategy.SUITE);

		return extentReport;

	}

	/**
	 * Configures and Initializes a new HTML Report for the test case execution
	 *
	 * @param filePath - The path where the report is going to be store
	 * @throws IOException
	 *
	 */
	public ExtentReports initExtentReportsTestCase(String filePath) throws IOException {

		// Gets the current directory, file separator and gets the start time to prepare
		// the test file properties
		currentDir = System.getProperty("user.dir");
//		System.out.println("currentDir"+currentDir);
		fileSeperator = System.getProperty("file.separator");
//		System.out.println("fileSeperator"+fileSeperator);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HHmmss");
		String start_time = sdf.format(date);
//		System.out.println("start_time"+start_time);
//		System.out.println(System.getProperty("os.name"));

		// Creates the HTML report
		String dirName = RunConfiguration.getProjectDir();
		extentReport = new ExtentReports();
		extentHtmlReport = new ExtentSparkReporter(FilenameUtils.separatorsToSystem(filePath));
		extentReport.attachReporter(extentHtmlReport);

		// Loads the report configuration properties
		String sConfigxml = currentDir + fileSeperator + "src\\test\\resources\\ExtentsConfig" + fileSeperator
				+ "extent-config.xml";
		extentHtmlReport.loadXMLConfig(sConfigxml);
		extentHtmlReport.config().setTheme(Theme.DARK);
		extentReport.setSystemInfo("OS", System.getProperty("os.name"));
		GlobalVariable.testerName = System.getProperty("user.name");
		extentReport.setSystemInfo("Username", GlobalVariable.testerName.toString());
		InetAddress id = InetAddress.getLocalHost();
		extentReport.setSystemInfo("Host", id.getHostAddress());
		extentReport.setSystemInfo("User", id.getHostName());
		extentReport.setSystemInfo("Environment", "Test");
//		extentReport.setSystemInfo("Katalon Version", RunConfiguration.getAppVersion());
		extentReport.setAnalysisStrategy(AnalysisStrategy.TEST);
		return extentReport;

	}

	/**
	 * Get the report status and statistics for fill the percentage
	 */
	public ReportStats getReportStats() {

		return extentReport.getStats();

	}

	/**
	 * Adds a new child step to the current active step
	 * 
	 * @param testChildStep - Name of the step
	 * @return the step node
	 */
	public ExtentTest startTestChildStepNode(String testChildStep) {
		testChildStepLogger = testStepLogger.createNode(testChildStep);
		return testChildStepLogger;
	}

	/**
	 * Adds a new step to the test case
	 * 
	 * @param testStep - Name of the step
	 * @return the step node
	 */
	public ExtentTest startTestStepNode(String testStep) {

		testStepLogger = testLogger.createNode(testStep);
		return testStepLogger;
	}

	public ExtentTest AddNodeInstance(String testStep) {

		testStepLogger = testLogger.createNode(testStep);
		// testStepLogger = testLogger.log(status.INFO, 'TEST')
		// testStepLogger = testLogger.info('hello').createNode("MyFirstChildTest",
		// "Node Description").info('hello')
		// testStepLogger = testLogger.createNode("MyFirstChildTest", "Node
		// Description").info('hello')
		// testStepLogger = testLogger.createLog(Status.INFO, 'mystatus')

		return testStepLogger;

	}

	/**
	 * Starts a new test case instance
	 *
	 * @param testCaseName - name of the test case instance
	 * @return The test instance
	 */
	public ExtentTest startTestCaseTest(String testCaseName) {
		testLogger = extentReport.createTest(testCaseName);
		return testLogger;
	}

	/**
	 * Starts a new Test Node from the test suite Instance
	 *
	 * @param testCaseName - name of the test case node
	 * @return the created test node
	 */
	public ExtentTest startTestCaseNode(String testCaseName) {
		testLogger = testSuiteLogger.createNode(testCaseName);
		return testLogger;
	}

	/**
	 * Starts a new suite test case instance
	 * 
	 * @param testSuiteName
	 * @return The test instance
	 */
	public ExtentTest startTestSuiteTest(String testSuiteName) {
		
		return testSuiteLogger = extentReport.createTest(testSuiteName);
		
	}

	/**
	 * Takes a screen capture to add it to the report
	 * 
	 * @return A String representation of the absolute path of the file
	 * @throws IOException
	 */
	/*public String captureScreen() throws IOException {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat();
		ChromeDriver driver = new ChromeDriver();

		// Call Webdriver to click the screenshot.
		File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dest = GlobalVariable.extentFolder + "\\screencaptures\\" + sdf.format("dd_MM_yyy_hh_mm_ss") + ".png";
		File target = new File(dest);
//		FileUtils.copyFile(screen, target);

		return target.getAbsolutePath();

	}*/

	/**
	 * Attach the request/response to the report
	 * 
	 * @param ReqRes A String with the request response text
	 * @return the path of the file to attach
	 * @throws IOException if the file can not be written
	 */
	public String AttachRequestResponse(String ReqRes) throws IOException {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HHmmssSSS");

		String start_time = sdf.format(date);

		String XMLPath = GlobalVariable.ExtentFolderSDrive + "\\ReqRes" + start_time + ".json";

		try (OutputStreamWriter oResponseInputFile = new OutputStreamWriter(new FileOutputStream(XMLPath),
				StandardCharsets.UTF_8)) {
			oResponseInputFile.write(ReqRes);
		}

		return XMLPath;
	}

	/**
	 * Write all the changes on the report and closes it
	 * 
	 */

	public ExtentReports tearDownExtentReports() {
		extentReport.flush();
		return extentReport;
	}

	/**
	 * Attach the request/response to the report
	 *
	 * @param ReqRes A String with the request response text
	 * @return the path of the file to attach
	 * @throws IOException if the file can not be written
	 */
	public String AttachJSONRequestResponse(String ReqRes) throws IOException {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HHmmssSSS");

		String start_time = sdf.format(date);

		String ReqResJSON = JsonOutput.prettyPrint(ReqRes);
		String JSONPath = GlobalVariable.extentFolder + "\\ReqRes" + start_time + ".json";

		try (OutputStreamWriter fileJson = new OutputStreamWriter(new FileOutputStream(JSONPath),
				StandardCharsets.UTF_8)) {
			fileJson.write(ReqResJSON);
		}
//		File fileJson = new File(JSONPath);
//		fileJson.write(ReqResJSON, "UTF-8");

		return JSONPath;
	}

	public String AttachJSONRequestResponse(String ReqRes, String api) throws IOException {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HHmmssSSS");
		String start_time = sdf.format(date);

		File file = new File(GlobalVariable.extentFolder + "\\" + api);
		file.mkdir();

		GlobalVariable.start_time = start_time;
		String ReqResJSON = JsonOutput.prettyPrint(ReqRes);
		String JSONPath = GlobalVariable.extentFolder + "\\" + api + "\\ReqRes" + start_time + ".json";

		// println(ReqRes)
		// println(ReqResJSON)
		// println(JSONPath)

		try (OutputStreamWriter fileJson = new OutputStreamWriter(new FileOutputStream(JSONPath),
				StandardCharsets.UTF_8)) {
			
			fileJson.write(ReqResJSON);
			
		}

//		File fileJson = new File(JSONPath);
//		fileJson.write(ReqResJSON, "UTF-8");

		JSONPath = api + "\\ReqRes" + start_time + ".json";
		// JSONPath = "<html><body><a target=_blank rel=noopener href="api +
		// "\\ReqRes"+start_time+".json></body></html>"

		return JSONPath;
	}

	/*public void AfterMethod(ITestResult result) {
		
		System.out.println("this is after method");
		

		if (result.getStatus() == ITestResult.FAILURE) {

			testLogger.log(Status.FAIL, result.getThrowable());

		} else if (result.getStatus() == ITestResult.SUCCESS) {

			testLogger.log(Status.PASS, result.getTestName());

		} else {

			testLogger.log(Status.SKIP, result.getTestName());

		}

	}*/

}
