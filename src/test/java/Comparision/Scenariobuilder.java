package Comparision;

import java.util.List;
import org.junit.Assert;
import com.aventstack.extentreports.Status;
import groovyjarjarpicocli.CommandLine.Model.Messages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import Comparision.CustomReport;
import Comparision.ExtendReportCreation;
import Comparision.GlobalVariable;
import Comparision.Scenario;
import Comparision.ScenarioInvoker;

public class Scenariobuilder {

	CustomReport customReport = CustomReport.getInstance();

	@Given("^a countrycode (.*) with citycode (.*) and iata (.*)$")
	public void a_countrycode_us_with_citycode_bos_and_iata(String CountryCode, String CityCode, String Iatanumber) {

		GlobalVariable.CountryCode = CountryCode;
		GlobalVariable.CityCode = CityCode;
		GlobalVariable.Iatanumber = Iatanumber;
//		SearchValidation.Comparision();

//		System.out.println(GlobalVariable.CountryCode);
//		System.out.println(GlobalVariable.CityCode);
//		System.out.println(GlobalVariable.Iatanumber);

		String sTestStep = "<span style=color:white>" + Scenario.sTestStep.trim() + "</span>";
		new ExtendReportCreation().createStepNodeInstance(sTestStep);

	}

	@And("^with dept flt from (.*) to (.*) with dept date (.*) days$")
	public void DefineDepFlight(String DeptflightFrom, String DeptflightTo, String DeptDate) {
		GlobalVariable.DeptflightFrom = DeptflightFrom;
		GlobalVariable.DeptflightTo = DeptflightTo;
//		System.out.println(GlobalVariable.DeptflightFrom);
//		System.out.println(GlobalVariable.DeptflightTo);
		GlobalVariable.DeptDate = ScenarioInvoker.ConvertDaystoDate(DeptDate, "yyyy-MM-dd");
		
		GlobalVariable.sOrigDest.add(DeptflightFrom);
		GlobalVariable.sOrigDest.add(DeptflightTo);
		GlobalVariable.sOrigDest.add(GlobalVariable.DeptDate.toString());
//		System.out.println(GlobalVariable.DeptDate);
		
		String sTestStep = "<span style=color:white>" + Scenario.sTestStep.trim() + "</span>";
		new ExtendReportCreation().createStepNodeInstance(sTestStep);
	}

	
	@And("^with retn flight from (.*) to (.*) with retn date (.*) days$")
	public void DefineRetnFlight(String RetnflightFrom, String RetnflightTo, String RetnDate) {
		GlobalVariable.RetnflightFrom = RetnflightFrom;
		GlobalVariable.RetnflightTo = RetnflightTo;
		GlobalVariable.RetnDate = ScenarioInvoker.ConvertDaystoDate(RetnDate, "yyyy-MM-dd");
		GlobalVariable.sOrigDest.add(RetnflightFrom);
		GlobalVariable.sOrigDest.add(RetnflightTo);
		GlobalVariable.sOrigDest.add(GlobalVariable.RetnDate.toString());
//		System.out.println(GlobalVariable.RetnDate);
		String sTestStep = "<span style=color:white>" + Scenario.sTestStep.trim() + "</span>";
		new ExtendReportCreation().createStepNodeInstance(sTestStep);
	}
	
	@And("^with onward flt from (.*) to (.*) with onward flt date (.*) days$")
	public void DefineOnwrdFlight(String OnwrdflightFrom, String OnwrdflightTo, String fltDate) {
		GlobalVariable.OnwrdflightFrom = OnwrdflightFrom;
		GlobalVariable.OnwrdflightTo = OnwrdflightTo;
		GlobalVariable.OnwrdDate = ScenarioInvoker.ConvertDaystoDate(fltDate, "yyyy-MM-dd");
		GlobalVariable.sOrigDest.add(OnwrdflightFrom);
		GlobalVariable.sOrigDest.add(OnwrdflightTo);
		GlobalVariable.sOrigDest.add(GlobalVariable.OnwrdDate.toString());
//		System.out.println(GlobalVariable.OnwrdDate);
		String sTestStep = "<span style=color:white>" + Scenario.sTestStep.trim() + "</span>";
		new ExtendReportCreation().createStepNodeInstance(sTestStep);
	}


	@And("^with carrier (.*) and idm_carrier_list (.*)$")
	public void DefineCarrierAndIDMCarrierList(String Carrier, String IDM_CARRIER_LIST) {
		GlobalVariable.Carrier = Carrier;
		GlobalVariable.IDM_CARRIER_LIST = IDM_CARRIER_LIST;
//		System.out.println(GlobalVariable.Carrier);
//		System.out.println(GlobalVariable.IDM_CARRIER_LIST);
	}

	@And("^with currencycode (.*) and pseudocitycode (.*) and domainregion (.*)$")
	public void DefineCurrencyPseudoAndDomain(String CurrencyCode, String PseudoCityCode, String DomainRegion) {
		GlobalVariable.CurrencyCode = CurrencyCode;
		GlobalVariable.PseudoCityCode = PseudoCityCode;
		GlobalVariable.DomainRegion = DomainRegion;

		String sTestStep = "<span style=color:white>" + Scenario.sTestStep.trim() + "</span>";
		new ExtendReportCreation().createStepNodeInstance(sTestStep);
	}

	@And("^with oAuthResourceInfo (.*) and Accessgroup (.*)$")
	public void oAuthResourceInfoAndAccessgroup(String AuthResource, String Accessgroup) {

		if (Accessgroup.contains("'")) {

			Accessgroup = Accessgroup.replace("'", "").trim();
			GlobalVariable.Accessgroup = Accessgroup;

		} else if (Accessgroup.contains("\"")) {

			Accessgroup = Accessgroup.replace("\"", "").trim();
//			System.out.println(Accessgroup);
			GlobalVariable.Accessgroup = Accessgroup;

		} else {

			GlobalVariable.Accessgroup = Accessgroup;
		}

		if (AuthResource.contains("'")) {

			AuthResource = AuthResource.replace("'", "").trim();
			GlobalVariable.oAuthResourceInfo = AuthResource;

		} else if (AuthResource.contains("\"")) {

			AuthResource = AuthResource.replace("\"", "").trim();
//			System.out.println(AuthResource);
			GlobalVariable.oAuthResourceInfo = AuthResource;

		} else {

			GlobalVariable.oAuthResourceInfo = AuthResource;
		}

		String sTestStep = "<span style=color:white>" + Scenario.sTestStep.trim() + "</span>";
		new ExtendReportCreation().createStepNodeInstance(sTestStep);
	}

	@And("^with domainlistenerchannelid (.*) and environment (.*)$")
	public void Definedomainlistener(String domainlistener, String Env) {

		if (domainlistener.contains("'")) {

			domainlistener = domainlistener.replace("'", "").trim();
			GlobalVariable.domainlistener = domainlistener;
		} else if (domainlistener.contains("\"")) {

			domainlistener = domainlistener.replace("\"", "").trim();
//			System.out.println(domainlistener);
			GlobalVariable.domainlistener = domainlistener;

		} else {

			GlobalVariable.domainlistener = domainlistener;
		}

		if (Env.contains("'")) {

			Env = Env.replace("'", "").trim();
			GlobalVariable.testEnv = Env;
		} else if (Env.contains("\"")) {

			Env = Env.replace("\"", "").trim();
//			System.out.println(Env);
			GlobalVariable.testEnv = Env;

		} else {

			GlobalVariable.testEnv = Env;
		}

		String sTestStep = "<span style=color:white>" + Scenario.sTestStep.trim() + "</span>";
		new ExtendReportCreation().createStepNodeInstance(sTestStep);
	}

	@And("^with ReservationResource_Identifier (.*) and PCC (.*)$")
	public void Resource_IdentifierAndPCC(String ReservationResource_Identifier, String PCC) {

		if (ReservationResource_Identifier.contains("'")) {

			ReservationResource_Identifier = ReservationResource_Identifier.replace("'", "").trim();
			GlobalVariable.Resource_Identifier = ReservationResource_Identifier;
		} else if (ReservationResource_Identifier.contains("\"")) {

			ReservationResource_Identifier = ReservationResource_Identifier.replace("\"", "").trim();
//			System.out.println(ReservationResource_Identifier);
			GlobalVariable.Resource_Identifier = ReservationResource_Identifier;

		} else {

			GlobalVariable.Resource_Identifier = ReservationResource_Identifier;
		}

		if (PCC.contains("'")) {

			PCC = PCC.replace("'", "").trim();
			GlobalVariable.PCC = PCC;
		} else if (PCC.contains("\"")) {

			PCC = PCC.replace("\"", "").trim();
//			System.out.println(PCC);
			GlobalVariable.PCC = PCC;

		} else {

			GlobalVariable.PCC = PCC;
		}

		String sTestStep = "<span style=color:white>" + Scenario.sTestStep.trim() + "</span>";
		new ExtendReportCreation().createStepNodeInstance(sTestStep);
	}

	@And("^with (\\d+) and (.*) passenger$")
	public void definePassengers(int paxCount, String ptc) {
		Scenario scenario = GlobalVariable.objScenarioData;
//		System.out.println("paxCount "+paxCount);

		if (ptc.contains("'")) {

			ptc = ptc.replace("'", "").trim();

		} else if (ptc.contains("\"")) {

			ptc = ptc.replace("\"", "").trim();

		}

		scenario.sPassengerType.put(ptc, paxCount);

//		System.out.println("sPassengerType"+scenario.sPassengerType);

		GlobalVariable.objScenarioData = scenario;

//		String infoMsg = "Test";
//		boolean sBoolean = false; 

		String sTestStep = "<span style=color:white>" + Scenario.sTestStep.trim() + "</span>";
		new ExtendReportCreation().createStepNodeInstance(sTestStep);

	}
	
	@And("^with HCAProfileId (.*) and BSPCode (.*) and AgencyCountryCode (.*)$")
	public void defineHCAProfileID(String HCAprofileId, String BSPCode, String AgencyCountryCode) {

		if (HCAprofileId.length() > 2) {

			if (HCAprofileId.contains("'")) {

				HCAprofileId = HCAprofileId.replace("'", "").trim();
				GlobalVariable.HCAprofileId = HCAprofileId;
			} else {

				GlobalVariable.HCAprofileId = HCAprofileId;
//				System.out.println(GlobalVariable.CoreAffinity);

			}

		}

		if (BSPCode.length() > 2) {

			if (BSPCode.contains("'")) {

				BSPCode = BSPCode.replace("'", "").trim();
				GlobalVariable.BSPCode = BSPCode;
			} else {

				GlobalVariable.BSPCode = BSPCode;
//				System.out.println(GlobalVariable.CoreAffinity);

			}

		}
		
		
		if (AgencyCountryCode.length() > 2) {

			if (AgencyCountryCode.contains("'")) {

				AgencyCountryCode = AgencyCountryCode.replace("'", "").trim();
				GlobalVariable.AgencyCountryCode = AgencyCountryCode;
			} else {

				GlobalVariable.AgencyCountryCode = AgencyCountryCode;
//				System.out.println(GlobalVariable.CoreAffinity);

			}

		}

		String sTestStep = "<span style=color:white>" + Scenario.sTestStep.trim() + "</span>";
//		String infoMsg =  "Test";		
//		boolean sBoolean = false; 
		new ExtendReportCreation().createStepNodeInstance(sTestStep);
//		ExtendReportCreation.addPassAndFailValidation(sBoolean,infoMsg);
//		System.out.println("7step is "+customReport.getExtentTestStep().getStatus().toString());

	}

	@And("^with CoreAffinity as (.*) and upsells value (.*) and OffersToReturn (.*)$")
	public void defineUpsellAndCoreAffinity(String CoreAffinity, String upsell, String offertoreturn) {

		if (CoreAffinity.length() > 2) {

			if (CoreAffinity.contains("'")) {

				CoreAffinity = CoreAffinity.replace("'", "").trim();
				GlobalVariable.CoreAffinity = CoreAffinity;
			} else if (CoreAffinity.contains("\"")) {

				CoreAffinity = CoreAffinity.replace("\"", "").trim();
				GlobalVariable.CoreAffinity = CoreAffinity;

			} else {

				GlobalVariable.CoreAffinity = CoreAffinity;
			}

//			System.out.println(GlobalVariable.CoreAffinity);

		}

		if (upsell.length() > 2) {

			if (upsell.contains("'")) {

				upsell = upsell.replace("'", "").trim();

				GlobalVariable.upsell = upsell;
//			System.out.println(GlobalVariable.upsell);

			} else if (upsell.contains("\"")) {

				upsell = upsell.replace("\"", "").trim();
				GlobalVariable.upsell = upsell;

			} else {

				GlobalVariable.upsell = upsell;
			}

		}
		
		
		if (offertoreturn.length() > 2) {

			if (offertoreturn.contains("'")) {

				offertoreturn = offertoreturn.replace("'", "").trim();

				GlobalVariable.offertoreturn = offertoreturn;
//			System.out.println(GlobalVariable.upsell);

			} else if (offertoreturn.contains("\"")) {

				offertoreturn = offertoreturn.replace("\"", "").trim();
				GlobalVariable.offertoreturn = offertoreturn;

			} else {

				GlobalVariable.offertoreturn = offertoreturn;
			}

		}
		
		
		
		

		String sTestStep = "<span style=color:white>" + Scenario.sTestStep.trim() + "</span>";
//		String infoMsg =  "Test";		
//		boolean sBoolean = false; 
		new ExtendReportCreation().createStepNodeInstance(sTestStep);
//		ExtendReportCreation.addPassAndFailValidation(sBoolean,infoMsg);
//		System.out.println("7step is "+customReport.getExtentTestStep().getStatus().toString());

	}

	@And("^with OrganizationInformation accountcode (.*) is added to Search$")
	public void defineIgnoreData(String accountCode) throws Throwable {

		if (accountCode.contains("'")) {

			accountCode = accountCode.replace("'", "").trim();
		}

		GlobalVariable.sAccountCode = accountCode;

		String sTestStep = "<span style=color:white>" + Scenario.sTestStep.trim() + "</span>";
		new ExtendReportCreation().createStepNodeInstance(sTestStep);

	}

	@And("^with cabinPreference (.*) and cabinType (.*)$")
	public void defineCabinPreferences(String cabinPreference, String cabinType) throws Throwable {

		if (cabinType.contains("'")) {

			cabinType = cabinType.replace("'", "").trim();
		}

		if (cabinPreference.contains("'")) {

			cabinPreference = cabinPreference.replace("'", "").trim();
		}

		System.out.println(cabinType);
		System.out.println(cabinPreference);
		GlobalVariable.sCabinType = cabinType;
		GlobalVariable.sCabinPreference = cabinPreference;

		String sTestStep = "<span style=color:white>" + Scenario.sTestStep.trim() + "</span>";
		new ExtendReportCreation().createStepNodeInstance(sTestStep);

	}

	@And("^Ignore below keywords or values while comparing Gen-1 and Gen-3 responses$")
	public void defineIgnoreDatas(List<String> IgnoreData) throws Throwable {

		Scenario scenario = GlobalVariable.objScenarioData;

//		System.out.println("the ignore data is "+IgnoreData);		

		scenario.sIgnoredata = IgnoreData;

		System.out.println("the ignore data is " + scenario.sIgnoredata);

		if (Scenario.sIgnoredata.size() > 0) {

			scenario.sIgnoreValue = true;

		}

		GlobalVariable.objScenarioData = scenario;

		String sTestStep = "<span style=color:white>" + Scenario.sTestStep.trim() + "</span>";
		new ExtendReportCreation().createStepNodeInstance(sTestStep);

	}

	@And("^with carrier (.*) and carrier preference type (.*)$")
	public void withCarrierAndCarrierPreferenceCarrierPrefType(String carrier, String carrierPreferenceType) {
		GlobalVariable.Carrier = carrier;
		GlobalVariable.IDM_CARRIER_LIST = carrier;
		carrierPreferenceType = carrierPreferenceType.replace("'", "").trim().isBlank() ? "Preferred"
				: carrierPreferenceType;

		GlobalVariable.sCarrierPreferenceType = carrierPreferenceType;
	}

	// @When("Execute the (.*) with endpoint (.*) Request for (.*) Version")
//	public void ExecuteSearchRequest(String RequestType, String Servicename, String Version) {
//		
//		
//		
//		
//		
//
//	}

//	@Then("Validate and Compare the responses for (.*) both Versions")
//	public void ValidateAndCompareSearchResponse(String RequestType) {
//
//		Scenario scenario = (Scenario) GlobalVariable.objScenarioData;
//
//		scenario.RequestType = RequestType.trim();
//
//		SearchValidation.validateResultObject(scenario);
//
//		
//
//		GlobalVariable.objScenarioData = scenario;
//
//	}

}
