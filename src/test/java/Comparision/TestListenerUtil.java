package Comparision;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import io.cucumber.java.Scenario;

public class TestListenerUtil {

	

	static void configCommonTestCaseParams(Scenario scenario) {

		// Define the test Case ID and setting up to the global variable
		String testCaseID = scenario.getUri().toString();
		String testCaseName = scenario.getName();
//		System.out.println("getId"+scenario.getId());
//		System.out.println("testCaseScenario"+scenario.getName());
//		System.out.println("testgetUri"+scenario.getUri());
//		String fullFeatureName = FilenameUtils.getName(scenario.getUri().toString());
//		System.out.println("fullFeatureName"+fullFeatureName);
		String[] testCaseFolder = testCaseID.split("/");

		testCaseID = testCaseID.trim();
		testCaseID = testCaseID.substring((testCaseID.lastIndexOf("/") + 1));
		testCaseID = testCaseID.substring(testCaseID.indexOf(0) + 1, testCaseID.indexOf("."));
		testCaseID = testCaseID.toUpperCase();
//		System.out.println("testCaseID"+testCaseID);

//		KeywordUtil.logInfo("---------> Test Case ID: " + testCaseID);
//		KeywordUtil.logInfo("---------> Test Type: " + testCaseFolder[1]);

		GlobalVariable.testCaseType = testCaseFolder[1].toUpperCase();

		if (GlobalVariable.testCaseType == CommonConstants.COMPONENT) {
			GlobalVariable.component = testCaseFolder[2].toUpperCase();
//			KeywordUtil.logInfo("---------> Test Component: " + GlobalVariable.component);

		} else {
			GlobalVariable.component = testCaseFolder[1].toUpperCase();
//			KeywordUtil.logInfo("---------> Test Component: " + GlobalVariable.component);
		}

//		KeywordUtil.logInfo("---------> Test Name: " + testCaseFolder[testCaseID.lastIndexOf("/")]);
//		KeywordUtil.logInfo("---------> Execution Date: " + LocalDateTime.now());
//		KeywordUtil.logInfo("---------> Executed By: " + GlobalVariable.testerName);

		if (GlobalVariable.traceID == null || GlobalVariable.traceID == "") {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			GlobalVariable.traceID = "TestExec_" + sdf.format(date) + "_"
					+ UUID.randomUUID().toString().replace("-", "").substring(0, 9);
		}

		if (GlobalVariable.e2eTrackingID == null || GlobalVariable.e2eTrackingID == "") {
			GlobalVariable.e2eTrackingID = UUID.randomUUID().toString();
		}

//		KeywordUtil.logInfo("---------> Test Case TraceID: " + GlobalVariable.traceID);
//		KeywordUtil.logInfo("---------> Test Case E2ETrackingID: " + GlobalVariable.e2eTrackingID);

		GlobalVariable.testCaseID = UUID.randomUUID().toString();
//		System.out.println("GlobalVariable.testCaseID"+GlobalVariable.testCaseID);
//		GlobalVariable.testCaseName = testCaseFolder[testCaseID.lastIndexOf("/")];
		GlobalVariable.testCaseName = testCaseID;
//		System.out.println("GlobalVariable.testCaseName"+GlobalVariable.testCaseName);
	}

	/**
	 * Sets and shows in console the global variables related to the test suite
	 * context based on the following conventions:
	 *
	 * <ul>
	 * <li>testSuiteID - UUID Random to identify the test suites</li>
	 * <li>testSuiteName - Name of the test Suite</li>
	 * <li>testerName - Person who executes the testing</li>
	 * </ul>
	 *
	 * @param testSuiteContext - related information of the executed test suite.
	 */
	static void configCommonTestSuiteParams(Scenario scenario) {

		// Define the test suite id and setting up to the global variable
		String testSuiteID = scenario.getId();
		String[] aTestSuiteFolder = testSuiteID.split("/");

		testSuiteID = testSuiteID.trim();
		testSuiteID = testSuiteID.substring(testSuiteID.lastIndexOf("/") + 1);
		testSuiteID = testSuiteID.toUpperCase();

//		KeywordUtil.logInfo('---------> Katalon Test Suite ID: ' + testSuiteID)
//		KeywordUtil.logInfo('---------> Execution Date: ' + LocalDateTime.now())
//		KeywordUtil.logInfo('---------> Executed By: ' + GlobalVariable.testerName)

		GlobalVariable.testSuiteID = UUID.randomUUID().toString();
		GlobalVariable.testSuiteName = aTestSuiteFolder[testSuiteID.lastIndexOf("/")];

	}

	/**
	 * Configures all the required data to realize the test execution. The sequence
	 * of the execution flow is:
	 * <p>
	 * <b>
	 * <ol>
	 * <li>Configure the scenario by test case type</li>
	 * <li>Configure the PCC data</li>
	 * <li>Configure the OAuth Data</li>
	 * </ol>
	 * </b>
	 * 
	 */
	static void configureTestCaseData() {

		switch (GlobalVariable.testCaseType) {

		case "COMPONENT":
//				ScenarioUtil.configScenarioByExecType();
//				ScenarioUtil.configEndpointByComponent();
//				ScenarioUtil.configPccData();
//				ScenarioUtil.configOauthData();
			break;

		case "E2E":
//				ScenarioUtil.configScenarioByExecType();
//				ScenarioUtil.configAllEndpoints();
//				ScenarioUtil.configPccData();
//				ScenarioUtil.configOauthData();
			break;

		case "CUCUMBER":
//				CucumberKW.GLUE = ["com.travelport.ndc.cucumber"];
			// CucumberKW.GLUE = ['com.travelport.nextgen.tripchange.cucumber']
			ScenarioUtil.initScenarioObject();
			break;
		}

	}

	static void MapNetworkDrive() {

		String command = "powershell.exe -Command \"New-PSDrive -Name S -PSProvider FileSystem -Root //AP-QA/Factory -Persist\"";

		String sCommand = "";

		for (int i = 0; i < command.length(); i++) {

			if (command.substring(i).contains("/")) {

				String sString = command.substring(i).replace("/", "\\");
				sCommand = sCommand + sString;
				// println(sCommand)

			} else {

				sCommand = sCommand + command.indexOf(i);
				// println(sCommand)

			}
		}

		System.out.println(sCommand);
		ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", sCommand.trim());
		processBuilder.redirectErrorStream(true);

		try {

			Process process = processBuilder.start();
			process.waitFor();

			if (process.exitValue() == 0) {
				System.out.println("Network drive mapped successfully.");
			} else {
				System.out.println("Failed to map network drive.");
			}
		} catch (Exception e) {
			System.out.println("An error occurred while mapping network drive: ${e.message}");
		}

	}

	static void UpdateTestStatus(String stestCaseId, Scenario scenario) throws IOException {

		 
	 
		PrintWriter pw = null;
		String oldContent = "";		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String sDate = sdf.format(date);
		SimpleDateFormat sdftime = new SimpleDateFormat("HH:mm:ss");
		String start_time = sdftime.format(date);
		String dDatetime = sDate + "  " + start_time;
		String HTMLPath = "TestExecutionStatus.html";
		File file = new File(HTMLPath);
		String Newline = null;
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String Line = reader.readLine();
		
		
		
//		System.out.println("First line is "+Line);
		String newfileContent = null;
		boolean Exist = false;
		int i = 0 ;

	 
		if (!(file.exists())) {
			System.out.println("file not exist");
			file.createNewFile();
		}		
		
		// String sTestCase = testCaseContext.testCaseId
		// String sTestStatus = testCaseContext.getTestCaseStatus()
		
		String sTestCase = stestCaseId;
		String sTestStatus = scenario.getStatus().toString();
		 
		sTestCase = sTestCase.substring(sTestCase.lastIndexOf("/") + 1).trim() + ".tc";
		String sTestWithStatus;
		String sExtentHTMLSDrive = (String) GlobalVariable.ExtentHTMLSDrive;
		

		if (sTestStatus.contains("PASSED")) {

			sTestWithStatus = ("<html><body><a target=_blank rel=noopener noreferrer href=" + sExtentHTMLSDrive
					+ "><p style=color:black;>" + sTestCase + "</a><p style=color:green;>" + sTestStatus + " - "
					+ dDatetime + "</p></body></html>");

		} else {

			sTestWithStatus = ("<html><body><a target=_blank rel=noopener noreferrer href=" + sExtentHTMLSDrive
					+ "><p style=color:black;>" + sTestCase + "</a><p style=color:red;>" + sTestStatus + " - "
					+ dDatetime + "</p></body></html>");


		}

	

		while (Line != null) {

//			System.out.println(i +" Line" + Line);
//			System.out.println("sTestWithStatus" + sTestWithStatus);
			
			oldContent = oldContent + Line + System.lineSeparator();

			if (Line.toUpperCase().trim().contains(sTestCase.toUpperCase())) {

//				System.out.println("Test is already present in existing html report");
				Exist = true;
				Newline = Line;

			}
			

			Line = reader.readLine();
			 i = i + 1;

		}
		
		
		if (Exist) {

			newfileContent = oldContent.replace(Newline, sTestWithStatus);
//			System.out.println(newfileContent);
			Exist = true;

		}

		if (Exist == false) {

//			System.out.println("Test is not present in existing html report");
//			System.out.println("oldContent"+oldContent);
			newfileContent = oldContent + System.lineSeparator() + sTestWithStatus;
//			System.out.println("newfileContent"+newfileContent);

		}


		FileWriter fw = new FileWriter(HTMLPath); 
		fw.write(newfileContent);
		reader.close();
//		bw.close();
		fw.close();


	}

}
