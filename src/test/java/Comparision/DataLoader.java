package Comparision;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;



import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.junit.Cucumber;

import org.junit.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.runner.RunWith;
//import cucumber.api.*;
import groovy.transform.Field;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.plugin.event.PickleStepTestStep;
import junit.framework.TestCase;
import io.cucumber.junit.*;

public class DataLoader {

	static boolean scriptExecuter = false;
	static boolean isTC = false;
	static int iTestStep = 0;
	static int iTestStepCnt = 0;
	static boolean RetriveTestStepName = true;
	static int iFailCnt = 0;
	static int iPassCnt = 0;
	static int iSkipCnt = 0;
//	CustomReport customReport = new CustomReport();
	CustomReport customReport = CustomReport.getInstance();
	ExtendReportCreation extendReportCreation = new ExtendReportCreation();
	static List<PickleStepTestStep> testSteps;
	static int iFailPercentage = 0;
	static int iPassPercentage = 0;
	static int iSkipPercentage = 0;

	@BeforeAll
	public static void before_or_after_all()
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

//		System.out.println("Before All");
		
	

	}

	@BeforeClass
	public static void BeforeClass() {

//		System.out.println("BeforeClass");
		
		

	}

	
	


	@AfterStep
	public void AfterTestStep(Scenario scenario) {

//		String sStepStatus = scenario.getStatus().toString();

		System.out.println(iTestStep + " TestStep is " + customReport.getExtentTest().getStatus().toString());

		String sStepStatus = customReport.getExtentTest().getStatus().toString();

		if (sStepStatus == "Fail") {

			iFailCnt = iFailCnt + 1;

		} else if (sStepStatus == "Pass") {

			iPassCnt = iPassCnt + 1;

		} else {

			iSkipCnt = iSkipCnt + 1;

		}

		if (iTestStep == 6) {

//			System.out.println("6th step is " + customReport.getExtentTest().getStatus().toString());

		}

//		iTestStep = iTestStep + 1;

	}


	@Before(order = 1)
	public void getScenario(Scenario scenario) {
		
//		System.out.println("executing ScenarioStorage");		
		ScenarioStorage.putScenario(scenario);
		isTC = true;
		iTestStep = 0;
		iTestStepCnt = 0;
		RetriveTestStepName = true;
		iFailCnt = 0;
		iPassCnt = 0;
		iSkipCnt = 0;
		iFailPercentage = 0;
		iPassPercentage = 0;
		iSkipPercentage = 0;
	
	}
	
	@Before(order = 2)
	public void BeforeScenario(Scenario scenario)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
//		System.out.println("Capturing all the test steps in scenario");		 
//		System.out.println("Executing "+ iTestStep + " TestStep " + sTestStep);
		if (RetriveTestStepName == true) {

//			System.out.println("capturing all test steps");
			java.lang.reflect.Field f = scenario.getClass().getDeclaredField("delegate");
			f.setAccessible(true);
			io.cucumber.core.backend.TestCaseState sc = (io.cucumber.core.backend.TestCaseState) f.get(scenario);
			java.lang.reflect.Field f1 = sc.getClass().getDeclaredField("testCase");
			f1.setAccessible(true);
			io.cucumber.plugin.event.TestCase testCase = (io.cucumber.plugin.event.TestCase) f1.get(sc);

			testSteps = testCase.getTestSteps().stream().filter(x -> x instanceof PickleStepTestStep)
					.map(x -> (PickleStepTestStep) x).collect(Collectors.toList());

			for (PickleStepTestStep ts : testSteps) {

//		       System.out.println("TestStepName is "+ ts.getStep().getKeyword() + ts.getStep().getText());

			}

			RetriveTestStepName = false;

		}

//		  System.out.println("testStepssize"+testSteps.size());

	}

	@Before(order = 3)
	public void BeforeTestCase(Scenario scenario) throws IOException {

		GlobalVariable.sProjectPath = System.getProperty("user.dir");
		
	 
//		CustomReport customReport = CustomReport.instance;
//		System.out.println("BeforeTestCase");
		String sTest = scenario.getId();

		TestListenerUtil.configCommonTestCaseParams(scenario);
		TestListenerUtil.configureTestCaseData();
//				println testCaseContext.getTestCaseId()
//				println testCaseContext.getTestCaseVariables()
		// println testCaseContext.getTestCaseStatus()
//				String testCaseId = scenario.getId();
//				GlobalVariable.testCaseName = testCaseId.substring(testCaseId.lastIndexOf("/")+ 1);
		System.out.println("The test case is:- " + GlobalVariable.testCaseName);

		// Gets the instance of the Report
		// Validates if there's a previously created report
		if (scriptExecuter == false) {

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HHmmss");
			String start_time = sdf.format(date);

			String dirName = RunConfiguration.getProjectDir();

			File sfile = new File("S:\\");

			if (!sfile.exists()) {

				TestListenerUtil.MapNetworkDrive();

			}

//			System.out.println("dirName " + dirName);

			String sCResultPath = dirName + "\\Extents";
			File cDir = new File(sCResultPath);

			if (!cDir.exists()) {
				cDir.mkdirs();
			}

			String sTestFolder = dirName.substring(dirName.lastIndexOf('\\') + 1).trim();

//					System.out.println("sTestFolderL "+sTestFolder);
			String sShareDriveDir = "S:\\Results\\" + sTestFolder;
			GlobalVariable.sShareDriveDir = sShareDriveDir;

			String sResultPath = "S:\\Results\\" + sTestFolder + "\\Extents";
			GlobalVariable.SDriveResultPath = sResultPath;

			File theDir = new File(sResultPath);

			if (!theDir.exists()) {
				theDir.mkdirs();
			}

			GlobalVariable.ExtentHTMLSDrive = sResultPath + "\\" + GlobalVariable.testCaseName + '_' + start_time
					+ "\\ExtentReport_" + start_time + ".html";
			GlobalVariable.ExtentFolderSDrive = sResultPath + "\\" + GlobalVariable.testCaseName + '_' + start_time;
			GlobalVariable.extentHTML = dirName + "\\Extents\\" + GlobalVariable.testCaseName + '_' + start_time
					+ "\\ExtentReport_" + start_time + ".html";
			GlobalVariable.extentFolder = dirName + "\\Extents\\" + GlobalVariable.testCaseName + '_' + start_time;

//					System.out.println("GlobalVariable.extentFolder "+GlobalVariable.extentFolder);
//					System.out.println("GlobalVariable.ExtentFolderSDrive "+GlobalVariable.ExtentFolderSDrive);//					 
//					System.out.println("GlobalVariable.extentHTML "+ GlobalVariable.extentHTML);
//					System.out.println("GlobalVariable.ExtentHTMLSDrive "+ GlobalVariable.ExtentHTMLSDrive);

			// customReport.initExtentReportsTestCaseSDrive(GlobalVariable.ExtentHTMLSDrive)
			customReport.initExtentReportsTestCase(GlobalVariable.extentHTML);

			scriptExecuter = true;
			isTC = true;

		}

		if (isTC == true) {
			customReport.startTestCaseTest("TEST CASE:- " + GlobalVariable.testCaseName.toString());
			customReport.getExtentTest().log(Status.INFO,
					MarkupHelper.createLabel("Scenario:-" + scenario.getName() + "\t - Started", ExtentColor.BLUE));
			customReport.getExtentTest().log(Status.INFO, "Tester Name: " + GlobalVariable.testerName);
//					customReport.startTestStepNode("hellotestnode"); 
//					extendReportCreation.createTestStepNodeInstance("hellohello");
//					customReport.AddNodeInstance("hello");

		} else {
			customReport.startTestCaseNode("TEST CASE:- " + GlobalVariable.testCaseName.toString());
			customReport.getExtentTest().log(Status.INFO,
					MarkupHelper.createLabel("Scenario:-" + scenario.getName() + "\t - Started", ExtentColor.BLUE));
//					customReport.getExtentTest().log(Status.INFO,MarkupHelper.createLabel(GlobalVariable.testCaseName + "\t Started", ExtentColor.BLUE));
			customReport.getExtentTest().log(Status.INFO, "Tester Name: " + GlobalVariable.testerName);
//					customReport.startTestStepNode("hellotestnode");  
//					extendReportCreation.createTestStepNodeInstance("hellohello");
//					customReport.AddNodeInstance("hello");	
		}

	}
	
	@BeforeStep(order = 1)
	public void BeforeTestStep( )
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	 
//		System.out.println("BeforeStep");
		
		iTestStep = iTestStep + 1;

		System.out.println(iTestStep + " TestStep Executing ' " + testSteps.get(iTestStepCnt).getStep().getKeyword()
				+ testSteps.get(iTestStepCnt).getStep().getText() + "'");
		Comparision.Scenario.sTestStep  = testSteps.get(iTestStepCnt).getStep().getKeyword()+ testSteps.get(iTestStepCnt).getStep().getText();
//		System.out.println("TestStepName is " + testSteps.get(iTestStep));

		iTestStepCnt = iTestStepCnt + 1;

	}

	@After(order = 2)
	public void AfterScenario(Scenario scenario) {

//		System.out.println("AfterScenario");		
//		System.out.println(iFailCnt);
//		System.out.println(iPassCnt);
//		System.out.println(iSkipCnt);

		int iTotalSteps = testSteps.size();

//		System.out.println("iTotalSteps" + iTotalSteps);

		iFailPercentage = Math.round((iFailCnt * 100 / iTotalSteps));

//		iPassPercentage = (iPassCnt * iTotalSteps/100);
		iPassPercentage = Math.round((iPassCnt * 100 / iTotalSteps));

		iSkipPercentage = Math.round((iSkipCnt * 100 / iTotalSteps));

	}


	@After(order = 1)
	public void AfterTestCase(Scenario scenario) throws IOException {

//		System.out.println("iFailPercentage" + iFailPercentage);
//		System.out.println("iPassPercentage" + iPassPercentage);
//		System.out.println("iSkipPercentage" + iSkipPercentage);

//		System.out.println("AfterTestCase");		
//		System.out.println("scenario.getStatus"+scenario.getStatus());		
//		System.out.println("Extent report status is" + customReport.getExtentTest().getStatus());
		
		String sExtentTestStatus = customReport.getExtentTest().getStatus().toString();
		String sScenarioStatus = scenario.getStatus().toString();
		GlobalVariable.sScenarioStatus = sScenarioStatus;
		String execType = (GlobalVariable.isManualExec = "Automated");
		customReport.getExtentTest().log(Status.INFO, "Execution Type: " + execType);
		customReport.getExtentTest().log(Status.INFO, "Test ID: " + GlobalVariable.testCaseID);
		customReport.getExtentTest().log(Status.INFO, "Environment Tested: " + GlobalVariable.testEnv);
		customReport.getExtentTest().log(Status.INFO, "Trace ID: " + GlobalVariable.traceID);

		if (sExtentTestStatus.contains("Pass")) {
//		if (sScenarioStatus.toString().contains("PASSED")) {

			// customReport.getExtentTest().log(Status.INFO, "Test Scenario:- " +
			// GlobalVariable.sScenarioStatus)
			// customReport.getExtentTest().log(Status.INFO, "Test Case Status: - PASSED")
//			customReport.getExtentTest().log(Status.INFO,MarkupHelper.createLabel("Test Scenario:-  " + scenario.getStatus(), ExtentColor.GREEN));
			customReport.getExtentTest().log(Status.PASS, MarkupHelper.createLabel("Passed", ExtentColor.GREEN));
//			customReport.getExtentTest().log(Status.PASS, "Pass");	        
//	        Assert.assertEquals(customReport.getExtentTest().getStatus(), Status.PASS);	        
//			System.out.println("Test Scenario Status:-  " + GlobalVariable.sScenarioStatus);
//			System.out.println("Test Case Status:-  " + sExtentTestStatus);
//			System.out.println("Test Case Status:- - PASSED & COMPLETED");
			String stestCaseId = GlobalVariable.testCaseName.toString();
			System.out.println(stestCaseId);
			System.out.println("Test Scenario Status:-  " + sExtentTestStatus);
//			TestListenerUtil.UpdateTestStatus(stestCaseId, scenario);

		} else if (sExtentTestStatus.contains("Fail")) {

//		} else if (sScenarioStatus.toString().contains("FAILED")) {

//			customReport.getExtentTest().log(Status.INFO,MarkupHelper.createLabel("All Test Data:-  " + GlobalVariable.sScenarioStatus, ExtentColor.RED));
			customReport.getExtentTest().log(Status.FAIL, MarkupHelper.createLabel("Failed", ExtentColor.RED));
//			customReport.getExtentTest().log(Status.FAIL, "Fail");				        
//	        Assert.assertEquals(customReport.getExtentTest().getStatus(), Status.FAIL);
//			System.out.println("All Test Data :-  " + GlobalVariable.sScenarioStatus);
//			System.out.println("Test Scenario Status:-  " + sScenarioStatus);
//			System.out.println("Test Case Status: " + sScenarioStatus + " & Completed");			
			System.out.println("Test Scenario Status:-  " + sExtentTestStatus);
			String stestCaseId = GlobalVariable.testCaseName.toString();
			System.out.println(stestCaseId);
//			TestListenerUtil.UpdateTestStatus(stestCaseId, scenario);

		}

		if (isTC == true) {

			if (sExtentTestStatus.contains("Fail")) {
//			if (sScenarioStatus.toString().contains("FAILED")) {
//				customReport.getExtentTest().log(Status.INFO,
//						MarkupHelper.createLabel(
//								" Success Percentage: " + customReport.getReportStats().getChildPercentage() + "%",
//								ExtentColor.GREEN));

				customReport.getExtentTest().log(Status.INFO,
						MarkupHelper.createLabel(" Success Percentage: " + iPassPercentage + "%", ExtentColor.GREEN));

//				customReport.getExtentTest().log(Status.INFO,
//						MarkupHelper.createLabel(
//								" Fail Percentage: " + customReport.getReportStats().getChildPercentage() + "%",
//								ExtentColor.RED));

				customReport.getExtentTest().log(Status.INFO,
						MarkupHelper.createLabel(" Fail Percentage: " + iFailPercentage + "%", ExtentColor.RED));

//				customReport.getExtentTest().log(Status.INFO,
//						MarkupHelper.createLabel(
//								" Skipped Percentage: " + customReport.getReportStats().getChildPercentage() + "%",
//								ExtentColor.BLUE));

				customReport.getExtentTest().log(Status.INFO,
						MarkupHelper.createLabel(" Skipped Percentage: " + iSkipPercentage + "%", ExtentColor.BLUE));

				customReport.tearDownExtentReports();

			} else {

//				customReport.getExtentTest().log(Status.PASS,
//						MarkupHelper.createLabel(
//								" Success Percentage: " + customReport.getReportStats().getParentPercentage() + "%",
//								ExtentColor.GREEN));
//				customReport.getExtentTest().log(Status.INFO, MarkupHelper.createLabel(" Success Percentage: " + customReport.getReportStats().getChildPercentage() + "%",ExtentColor.GREEN));

				customReport.getExtentTest().log(Status.INFO,
						MarkupHelper.createLabel(" Success Percentage: " + iPassPercentage + "%", ExtentColor.GREEN));

				customReport.tearDownExtentReports();

			}

		}

		String source = (String) GlobalVariable.extentFolder;
		File srcDir = new File(source);

		String destination = (String) GlobalVariable.SDriveResultPath;
		File destDir = new File(destination);

		try {

//			System.out.println("");
			if(srcDir.getName()== "UMA") {
				
			FileUtils.copyDirectoryToDirectory(srcDir, destDir);
//			 FileUtils.copyDirectory(srcDir, destDir);
			}
		} catch (IOException e) {

			e.printStackTrace();

		}
		
		

	}


}
