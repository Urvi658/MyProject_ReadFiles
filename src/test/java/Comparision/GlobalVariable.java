package Comparision;

import java.util.ArrayList;
import java.util.List;

public class GlobalVariable {
	 	
//	private static GlobalVariable instance  = null;
//	
//    public static GlobalVariable getInstance() {
//    	
//        if (instance  == null) {
//        	
//        	instance  = new GlobalVariable();
//        }
//        
//        return instance ;
//    }
    
//    public static Object CustomReport;
    
	/**
     * <p>Profile default : (Manual) Name of the person who executes the test cases</p>
     */
    public static Object testerName;
     
    /**
     * <p>Profile default : (Manual) Indicates if the minimun required test data is manually loaded on the execution profile</p>
     */
    public static String isManualExec;
     
    /**
     * <p>Profile default : (Manaul&#47;Auto) Indicates if the Oauth is required in the web service object's requests</p>
     */
    public static Object isRequiredOAuth;
     
    /**
     * <p>Profile default : (Manual&#47;Auto) Pseudo City Code. A travel provider's identification code for the JSON API. (Overrided value when isManualExec is set to false)</p>
     */
    public static String PCC;
    
    
    public static String Resource_Identifier;
    
    /**
     * <p>Profile default : (Manual&#47;Auto) The execution environment where the tests are going to run  Values &lt;DEV | INT | QAB | QAG | PF2 |PP&gt;  (Overrided value when isManualExec is set to false)</p>
     */
    
    public static String sCatalogIdenfierValue;
     
    /**
     * <p>sCatalogIdenfierValue to use in price as token</p>
     */
    public static Object testEnv;
     
    /**
     * <p>Profile default : (Manual&#47;Auto) Reservation locator of the generated itinerary (Overrided value when isManualExec is set to false)</p>
     */
    public static Object resLocator;
    
    
    public static String sProjectPath;
     
    /**
     * <p>Profile default : (Manual&#47;Auto) Ticket number in the reservation (Overrided value when isManualExec is set to false)</p>
     */
    public static Object ticketNumber;
     
    /**
     * <p>Profile default : (Auto) Test Case Identifier</p>
     */
    public static Object testCaseID;
     
    /**
     * <p>Profile default : (Auto) Test Case Name geting from the Test Case object</p>
     */
    public static Object testCaseName;
     
    /**
     * <p>Profile default : (Auto) Test Suite Identifier</p>
     */
    public static Object testSuiteID;
     
    /**
     * <p>Profile default : (Auto) Test Suite Name geting from the Test Case object</p>
     */
    public static Object testSuiteName;
     
    /**
     * <p>Profile default : (Auto) Test Case Type Identifier for set the test data according to the type</p>
     */
    public static String testCaseType;
     
    /**
     * <p>Profile default : (Auto) Date of the execution of the test</p>
     */
    public static Object executionDate;
     
    /**
     * <p>Profile default : (Auto) Trace identifier for Web Service Object's requests</p>
     */
    public static Object traceID;
     
    /**
     * <p>Profile default : (Auto) End to End Trace identifier UUID for Web Service Object's requests</p>
     */
    public static Object e2eTrackingID;
     
    /**
     * <p>Profile default : (Auto) The component to test</p>
     */
    public static Object component;
     
    /**
     * <p>Profile default : (Auto) Unique access group associated to the PCC</p>
     */
    public static Object accessGroup;
     
    /**
     * <p>Profile default : (Auto) Profile Identifier to realize request to the HCA microservice</p>
     */
    public static Object customerProfileID;
     
    /**
     * <p>Profile default : (Auto) Generated Token to realize operations in the HCA microservice</p>
     */
    public static Object sToken;
     
    /**
     * <p>Profile default : (Auto) Authorization Token required to in the web service object's requests</p>
     */
    
//    public static Object sProductBrandOptionsToken;
    
    public static List<String> sProductBrandOptionsToken = new ArrayList<String>();
    
    /**
     * <p>Profile default : sProductBrandOptionsToken Token required to in the web service object's requests</p>
     */
    
    public static List<String> sCatalogProductOfferingTokens = new ArrayList<String>();
    
    
    
    /**
     * <p>Profile default : sProductBrandOptionsToken Token required to in the web service object's requests</p>
     */
    
    public static Object authorizationToken;
     
    /**
     * <p>Profile default : (Auto) Path where the reports are going to be store</p>
     */
    public static Object extentFolder;
     
    /**
     * <p>Profile default : (Auto) Name of the output html report</p>
     */
    public static String extentHTML;
     
    /**
     * <p>Profile default : (Auto) OAuth - Way to obtain an access token outside of the context of a user</p>
     */
    public static Object oAuthGrantType;
     
    /**
     * <p>Profile default : (Auto) OAuth - Resource owner's username</p>
     */
    public static Object oAuthUsername;
     
    /**
     * <p>Profile default : (Auto) OAuth - Resource owner's password</p>
     */
    public static Object oAuthPassword;
     
    /**
     * <p>Profile default : (Auto) OAuth - Unique identifier of the client application</p>
     */
    public static Object oAuthClientID;
     
    /**
     * <p>Profile default : (Auto) OAuth - OAuth Client secret value; this value identifies the client with the provider</p>
     */
    public static Object oAuthClientSecret;
     
    /**
     * <p>Profile default : (Auto) OAuth - Scope of the access request</p>
     */
    public static Object oAuthScope;
     
    /**
     * <p>Profile default : (Auto) The city code where tavel agency is located</p>
     */
    public static String agencyCity;
    
    public static String oAuthResourceInfo;
    
    public static String Accessgroup;
      
    /**
     * <p>Profile default : (Auto) The type of the trip scenario to create</p>
     */
    public static Object tripType;
     
    /**
     * <p>Profile default : (Auto) Handle all the parameters for the scenario data</p>
     */
    public static Scenario objScenarioData;
     
    /**
     * <p>Profile default : (Auto) Stores a map to Handle the information related with the endpoints in the test case</p>
     */
    public static Object objEndpointData;
     
    /**
     * <p></p>
     */
    public static Object isPNRCreated;
     
    /**
     * <p></p>
     */
    public static Object sTicketGenerated;
     
    /**
     * <p>Profile default : To capture scenario status</p>
     */
    public static Object sScenarioStatus;
     
    /**
     * <p>Profile default : To capture scenario name</p>
     */
    public static Object sScenarioName;
     
    /**
     * <p></p>
     */
    public static Object sOpenJawSegment;
     
    /**
     * <p></p>
     */
    public static Object DepartureDateWB;
     
    /**
     * <p></p>
     */
    public static Object OpenJawReturnDateWB;
     
    /**
     * <p></p>
     */
    public static String sAccountCode;
     
    /**
     * <p></p>
     */
    
    public static String sCabinType ;
    /**
     * <p></p>
     */
    
    public static String sCabinPreference;
    /**
     * <p></p>
     */
    
    public static Object sTourCode;
     
    /**
     * <p></p>
     */
    public static Object OBDoubleConnection;
     
    /**
     * <p></p>
     */
    public static Object ExtentHTMLSDrive;
     
    /**
     * <p></p>
     */
    public static Object ExtentFolderSDrive;
     
    /**
     * <p></p>
     */
    public static Object sShareDriveDir;
     
    /**
     * <p></p>
     */
    public static Object start_time;
     
    /**
     * <p></p>
     */
    public static Object SDriveResultPath;
     
    /**
     * <p></p>
     */
    public static Object CountryCode;
     
    /**
     * <p></p>
     */
    public static Object CityCode;
     
    /**
     * <p></p>
     */
    public static Object Iatanumber;
     
    /**
     * <p></p>
     */
    public static Object DeptflightFrom;
     
    /**
     * <p></p>
     */
    public static Object DeptflightTo;
     
    /**
     * <p></p>
     */
    public static Object DeptDate;
     
    /**
     * <p></p>
     */
    public static Object RetnflightFrom;
     
    /**
     * <p></p>
     */
    public static Object RetnflightTo;
     
    /**
     * <p></p>
     */
    public static Object RetnDate;
     
    /**
     * <p></p>
     */
    public static Object OnwrdflightFrom;
     
    /**
     * <p></p>
     */
    public static Object OnwrdflightTo;
     
    /**
     * <p></p>
     */
    public static Object OnwrdDate;
     
    /**
     * <p></p>
     */
    public static Object Carrier;
     
    /**
     * <p></p>
     */
    public static Object IDM_CARRIER_LIST;
     
    /**
     * <p></p>
     */
    public static Object CurrencyCode;
     
    /**
     * <p></p>
     */
    public static Object PseudoCityCode;
     
    /**
     * <p></p>
     */
    public static Object DomainRegion;
     
    /**
     * <p></p>
     */
    public static Object Version;
     
    /**
     * <p></p>
     */
    public static Object sGen3_Token;
     
    /**
     * <p></p>
     */
    public static Object objToken;
     
    /**
     * <p></p>
     */
    public static Object sProductRef;
     
    /**
     * <p></p>
     */
    public static Object sAdtPtc;
     
    /**
     * <p></p>
     */
    public static Object sChdPtc;
     
    /**
     * <p></p>
     */
    public static Object sCnnPtc;
     
    /**
     * <p></p>
     */
    public static Object sInsPtc;
     
    /**
     * <p></p>
     */
    public static Object sInfPtc;
     
    /**
     * <p></p>
     */
    public static Object sSrcPtc;
     
    /**
     * <p></p>
     */
    public static Object sYthPtc;
     
    /**
     * <p></p>
     */
    public static String upsell;
     
    /**
     * <p></p>
     */
    public static Object CoreAffinity;
     
    /**
     * <p></p>
     */
    public static Object s1stPtcType;
     
    /**
     * <p></p>
     */
    public static Object s1stPtcCnt;
     
    /**
     * <p></p>
     */
    public static Object s2ndPtcType;
     
    /**
     * <p></p>
     */
    public static Object s2ndPtcCnt;
     
    /**
     * <p></p>
     */
    public static Object s3rdPtcType;
     
    /**
     * <p></p>
     */
    public static Object s3rdPtcCnt;
     
    /**
     * <p></p>
     */
    public static Object s4thPtcType;
     
    /**
     * <p></p>
     */
    public static Object s4thPtcCnt;
     
    /**
     * <p></p>
     */
    public static Object s5thPtcType;
     
    /**
     * <p></p>
     */
    public static Object s5thPtcCnt;
     
    /**
     * <p></p>
     */
    public static Object s6thPtcType;
     
    /**
     * <p></p>
     */
    public static Object s6thPtcCnt;
     
    /**
     * <p></p>
     */
    public static Object s7thPtcType;
     
    /**
     * <p></p>
     */
    public static Object s7thPtcCnt;
     
    /**
     * <p></p>
     */
    public static Object s8thPtcType;
     
    /**
     * <p></p>
     */
    public static Object s8thPtcCnt;
     
    /**
     * <p></p>
     */
    public static Object domainlistener;
     
    /**
     * <p></p>
     */
    public static Object scenario;    
    
    public static Object Env;

    public static String sCarrierPreferenceType;
    
    public static String AgencyGroup ;
    
    public static String BSPCode;
    
    public static String offertoreturn ;
    
    
    public static String HCAprofileId = "";
    
    public static String AgencyCountryCode = "";
    


    static {
    	
        try {
        	
//            def selectedVariables = TestCaseMain.getGlobalVariables("default");
//			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile());
//            selectedVariables += TestCaseMain.getParsedValues(RunConfiguration.getOverridingParameters(), selectedVariables);
//    
//            testerName = selectedVariables['testerName'];
//            isManualExec = selectedVariables['isManualExec'];
//            isRequiredOAuth = selectedVariables['isRequiredOAuth'];
//            pcc = selectedVariables['pcc'];
//            testEnv = selectedVariables['testEnv'];
//            resLocator = selectedVariables['resLocator'];
//            ticketNumber = selectedVariables['ticketNumber'];
//            testCaseID = selectedVariables['testCaseID'];
//            testCaseName = selectedVariables['testCaseName'];
//            testSuiteID = selectedVariables['testSuiteID'];
//            testSuiteName = selectedVariables['testSuiteName'];
//            testCaseType = selectedVariables['testCaseType'];
//            executionDate = selectedVariables['executionDate'];
//            traceID = selectedVariables['traceID'];
//            e2eTrackingID = selectedVariables['e2eTrackingID'];
//            component = selectedVariables['component'];
//            accessGroup = selectedVariables['accessGroup'];
//            customerProfileID = selectedVariables['customerProfileID'];
//            sToken = selectedVariables['sToken'];
//            authorizationToken = selectedVariables['authorizationToken'];
//            extentFolder = selectedVariables['extentFolder'];
//            extentHTML = selectedVariables['extentHTML'];
//            oAuthGrantType = selectedVariables['oAuthGrantType'];
//            oAuthUsername = selectedVariables['oAuthUsername'];
//            oAuthPassword = selectedVariables['oAuthPassword'];
//            oAuthClientID = selectedVariables['oAuthClientID'];
//            oAuthClientSecret = selectedVariables['oAuthClientSecret'];
//            oAuthScope = selectedVariables['oAuthScope'];
//            agencyCity = selectedVariables['agencyCity'];
//            tripType = selectedVariables['tripType'];
//            objScenarioData = selectedVariables['objScenarioData'];
//            objEndpointData = selectedVariables['objEndpointData'];
//            isPNRCreated = selectedVariables['isPNRCreated']
//            sTicketGenerated = selectedVariables['sTicketGenerated'];
//            sScenarioStatus = selectedVariables['sScenarioStatus'];
//            sScenarioName = selectedVariables['sScenarioName'];
//            sOpenJawSegment = selectedVariables['sOpenJawSegment'];
//            DepartureDateWB = selectedVariables['DepartureDateWB'];
//            OpenJawReturnDateWB = selectedVariables['OpenJawReturnDateWB'];
//            sAccountCode = selectedVariables['sAccountCode'];
//            sTourCode = selectedVariables['sTourCode'];
//            OBDoubleConnection = selectedVariables['OBDoubleConnection'];
//            ExtentHTMLSDrive = selectedVariables['ExtentHTMLSDrive'];
//            ExtentFolderSDrive = selectedVariables['ExtentFolderSDrive'];
//            sShareDriveDir = selectedVariables['sShareDriveDir'];
//            start_time = selectedVariables['start_time'];
//            SDriveResultPath = selectedVariables['SDriveResultPath'];
//            CountryCode = selectedVariables['CountryCode'];
//            CityCode = selectedVariables['CityCode'];
//            Iatanumber = selectedVariables['Iatanumber'];
//            DeptflightFrom = selectedVariables['DeptflightFrom'];
//            DeptflightTo = selectedVariables['DeptflightTo'];
//            DeptDate = selectedVariables['DeptDate'];
//            RetnflightFrom = selectedVariables['RetnflightFrom'];
//            RetnflightTo = selectedVariables['RetnflightTo'];
//            RetnDate = selectedVariables['RetnDate'];
//            OnwrdflightFrom = selectedVariables['OnwrdflightFrom'];
//            OnwrdflightTo = selectedVariables['OnwrdflightTo'];
//            OnwrdDate = selectedVariables['OnwrdDate'];
//            Carrier = selectedVariables['Carrier'];
//            IDM_CARRIER_LIST = selectedVariables['IDM_CARRIER_LIST'];
//            CurrencyCode = selectedVariables['CurrencyCode'];
//            PseudoCityCode = selectedVariables['PseudoCityCode'];
//            DomainRegion = selectedVariables['DomainRegion'];
//            Version = selectedVariables['Version'];
//            sGen3_Token = selectedVariables['sGen3_Token'];
//            objToken = selectedVariables['objToken'];
//            sProductRef = selectedVariables['sProductRef'];
//            sAdtPtc = selectedVariables['sAdtPtc'];
//            sChdPtc = selectedVariables['sChdPtc'];
//            sCnnPtc = selectedVariables['sCnnPtc'];
//            sInsPtc = selectedVariables['sInsPtc'];
//            sInfPtc = selectedVariables['sInfPtc'];
//            sSrcPtc = selectedVariables['sSrcPtc'];
//            sYthPtc = selectedVariables['sYthPtc'];
//            upsell = selectedVariables['upsell'];
//            CoreAffinity = selectedVariables['CoreAffinity'];
//            s1stPtcType = selectedVariables['s1stPtcType'];
//            s1stPtcCnt = selectedVariables['s1stPtcCnt'];
//            s2ndPtcType = selectedVariables['s2ndPtcType'];
//            s2ndPtcCnt = selectedVariables['s2ndPtcCnt'];
//            s3rdPtcType = selectedVariables['s3rdPtcType'];
//            s3rdPtcCnt = selectedVariables['s3rdPtcCnt'];
//            s4thPtcType = selectedVariables['s4thPtcType'];
//            s4thPtcCnt = selectedVariables['s4thPtcCnt'];
//            s5thPtcType = selectedVariables['s5thPtcType'];
//            s5thPtcCnt = selectedVariables['s5thPtcCnt'];
//            s6thPtcType = selectedVariables['s6thPtcType'];
//            s6thPtcCnt = selectedVariables['s6thPtcCnt'];
//            s7thPtcType = selectedVariables['s7thPtcType'];
//            s7thPtcCnt = selectedVariables['s7thPtcCnt'];
//            s8thPtcType = selectedVariables['s8thPtcType'];
//            s8thPtcCnt = selectedVariables['s8thPtcCnt'];
//            domainlistener = selectedVariables['domainlistener'];
//            scenario = selectedVariables['scenario'];
            
        } catch (Exception e) {
//            TestCaseMain.logGlobalVariableError(e)
        }
    }

}
