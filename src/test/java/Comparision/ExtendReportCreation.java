package Comparision;

import java.io.IOException;
import java.util.List;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.*;

public class ExtendReportCreation {

//	static ExtentTest node = null;
//	static CustomReport customReport;
//	Status Status
	Status status;
	Scenario scenario;
	public static ExtentTest node = null;
	static CustomReport reporter;

//	private static ExtendReportCreation instance  = null;
//	
//    public static ExtendReportCreation getInstance() {
//    	
//        if (instance  == null) {
//        	
//        	instance  = new ExtendReportCreation();
//        }
//        
//        return instance ;
//    }

	/**
	 * Creates a step node
	 * 
	 * @param nodeName - Name of the step to report
	 */
	void createTestStepNodeInstance(String nodeName) {
//				customReport = CustomReport.instance;
		CustomReport customReport = CustomReport.getInstance();
//		 
		ExtendReportCreation.node = customReport.startTestStepNode(nodeName);

	}

	void createStepNodeInstance(String nodeName) {

		CustomReport customReport = CustomReport.getInstance();
		ExtendReportCreation.node = customReport.startTestStepNode(nodeName);
//		 customReport.startTestStepNode(nodeName);
	}

	void AddStepNodeInstance(String nodeName) {

		CustomReport customReport = CustomReport.getInstance();
//		CustomReport customReport = new CustomReport();
		ExtendReportCreation.node = customReport.AddNodeInstance(nodeName);

	}

	/**
	 * Creates a chlid step node
	 *
	 * @param nodeName - Name of the step to report
	 */
	void createChildStepNodeInstance(String nodeName) {

		CustomReport customReport = CustomReport.getInstance();
//		CustomReport customReport = new CustomReport();
		ExtendReportCreation.node = customReport.startTestChildStepNode(nodeName);
	}

	static ExtentTest getActualNode() {

		CustomReport customReport = CustomReport.getInstance();
//		CustomReport customReport = new CustomReport();
		return customReport.getExtentTestStep();
//		return getCustomReport().getExtentTestStep();

	}

	/**
	 * Adds a validated step to the log
	 * 
	 * 
	 * @param request  - Request string representation
	 * @param response
	 * @param status
	 * @param api
	 * @param infoList
	 * @throws IOException
	 */
	static void addRequestResponse(String request, String response, boolean status, String api, List<String> infoList)
			throws IOException {

		if (status) {

			ExtendReportCreation.node.pass("Passed").log(Status.PASS, MarkupHelper.createLabel(api, ExtentColor.GREEN));
		} else {
			ExtendReportCreation.node.fail("Failed").log(Status.FAIL, MarkupHelper.createLabel(api, ExtentColor.RED));
		}

//		for(String info in infoList) {
		for (String info : infoList) {

			ExtendReportCreation.node.log(Status.INFO, info);
		}

		attachReport(CommonConstants.REQUEST, request, api);
		attachReport(CommonConstants.RESPONSE, response, api);

		// attachReportSDrive(CommonConstants.REQUEST, request, api);
		// attachReportSDrive(CommonConstants.REQUEST, request, api);
	}

	static void addRequestResponse(String request, String response, boolean status, String api, List<String> infoList,
			String requestHeader, String responseHeader) throws IOException {

		if (status) {
			ExtendReportCreation.node.pass("Passed").log(Status.PASS, MarkupHelper.createLabel(api, ExtentColor.GREEN));
		} else {
			ExtendReportCreation.node.fail("Failed").log(Status.FAIL, MarkupHelper.createLabel(api, ExtentColor.RED));
		}

		for (String info : infoList) {

			ExtendReportCreation.node.log(Status.INFO, info);
		}

//				for(info in infoList) {
//
//					ExtendReportCreation.node.log(Status.INFO, info);
//				}

		attachReport("Request Header-INFO", requestHeader, api);
		// attachReportSDrive("Request Header-INFO", requestHeader, api)

		if (request != null) {

			attachReport(CommonConstants.REQUEST, request, api);
			// attachReportSDrive("CommonConstants.REQUEST", request, api)
		}

		attachReport("Response Header-INFO", responseHeader, api);
		attachReport(CommonConstants.RESPONSE, response, api);

		// attachReportSDrive("Response Header-INFO", responseHeader, api)
		// attachReportSDrive(CommonConstants.RESPONSE, response, api)
	}

	static void addRequestResponse(String response, boolean status, String api, List<String> infoList)
			throws IOException {

		CustomReport customReport = new CustomReport();

		if (status) {
			ExtendReportCreation.node.pass("Passed").log(Status.PASS, MarkupHelper.createLabel(api, ExtentColor.GREEN));
		} else {
			ExtendReportCreation.node.fail("Failed").log(Status.FAIL, MarkupHelper.createLabel(api, ExtentColor.RED));
		}

//		for(info in infoList) {
//			ExtendReportCreation.node.log(Status.INFO, info);
//		}

		for (String info : infoList) {

			ExtendReportCreation.node.log(Status.INFO, info);
		}

		attachReport(CommonConstants.RESPONSE, response, api);

	}

	static void addinfo(String infoMsg) {
		CustomReport customReport = CustomReport.getInstance();
//		CustomReport customReport = new CustomReport();
		ExtendReportCreation.node.log(Status.INFO, infoMsg);

	}

	static void addValidation(boolean status, String api, String infoMsg) {

		if (status) {
			ExtendReportCreation.node.pass("Passed").log(Status.PASS, MarkupHelper.createLabel(api, ExtentColor.GREEN));
		} else {
			ExtendReportCreation.node.fail("Failed").log(Status.FAIL, MarkupHelper.createLabel(api, ExtentColor.RED));
		}

		ExtendReportCreation.node.log(Status.INFO, infoMsg);
	}

	static void addSkipValidation(String api, String infoMsg) {

		ExtendReportCreation.node.skip("Retrying with manual data").log(Status.SKIP,
				MarkupHelper.createLabel(api, ExtentColor.BLUE));

		ExtendReportCreation.node.log(Status.INFO, infoMsg);
	}

	static void addInfoLabel(String infoMsg) {

		ExtendReportCreation.node.log(Status.INFO, infoMsg);
	}

	static void addHighLightInfoLabel(String infoMsg) {

		ExtendReportCreation.node.log(Status.INFO, MarkupHelper.createLabel(infoMsg, ExtentColor.BLUE));
	}

	static void addHighLightLabel(String infoMsg) {

		// ExtendReportCreation.node.log(Status.INFO,
		// MarkupHelper.createLabel(infoMsg,ExtentColor.BLUE))
		ExtendReportCreation.node.info(MarkupHelper.createLabel(infoMsg, ExtentColor.BLUE));
	}

	static void addPassAndFailValidation(boolean sBoolean, String infoMsg) {

		 
		CustomReport customReport = new CustomReport();

		if (sBoolean) {

			ExtendReportCreation.node.log(Status.PASS, MarkupHelper.createLabel(infoMsg, ExtentColor.GREEN));
			
		} else if (Scenario.sIgnoreValue == true && sBoolean == false) {

			ExtendReportCreation.node.log(Status.INFO, MarkupHelper.createLabel(infoMsg, ExtentColor.BLUE));
		

	    } else if (sBoolean == false) {

			ExtendReportCreation.node.log(Status.FAIL, MarkupHelper.createLabel(infoMsg, ExtentColor.RED));

		}
	}

	static void addPassAndFailComment(boolean sBoolean, String infoMsg) {

//		CustomReport customReport = new CustomReport();

		if (sBoolean) {

			// String sStatus = (infoMsg)
			String sStatus = ("<html><body><p style=color:green;>" + infoMsg + "</p></body></html>");

			ExtendReportCreation.node.log(Status.PASS, sStatus);

		} else {

			String sStatus = (infoMsg);
			// String sStatus = ('<html><body><p
			// style=color:yellow;>'+infoMsg+'</p></body></html>')
			ExtendReportCreation.node.log(Status.FAIL, sStatus);

		}

	}

	static void attachReport(String reportType, String report, String apiName) throws IOException {

//		CustomReport customReport = new CustomReport();
		CustomReport customReport = CustomReport.getInstance();

		String attachReport = customReport.AttachJSONRequestResponse(report, apiName);

		// String reportLink = ((apiName+' '+reportType+' : <a href=\'' + attachReport)
		// + '\'>'+apiName+' '+reportType+'</a>')
		String reportLink = apiName + " " + reportType + " : " + "<a target=_blank rel=noopener noreferrer href="
				+ attachReport + ">" + apiName + " " + reportType + "</a>";
		// println(reportLink)
		ExtendReportCreation.node.log(Status.INFO, reportLink);

	}

	
	static void NewTestLog(String infoMsg, Status status) {

		switch (status) {

		case INFO:
			ExtendReportCreation.node.log(Status.INFO, infoMsg);
			break;
		case WARNING:
			ExtendReportCreation.node.log(Status.WARNING, infoMsg);
			break;
		case FAIL:
			ExtendReportCreation.node.log(Status.FAIL, infoMsg);
			break;
		case PASS:
			ExtendReportCreation.node.log(Status.PASS, infoMsg);
			break;
		default:
			ExtendReportCreation.node.log(Status.INFO, infoMsg);
			break;
		}

//		ReportUtils.node.log(Status.FAIL, infoMsg);

	}
}
