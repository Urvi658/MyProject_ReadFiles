package Comparision;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map.Entry;

import com.aventstack.extentreports.Status;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class CompareJson {

	public static ArrayList<String> IgnoreString = new ArrayList<String>();

	public static void compareTwoJsons(String Jsonfile1, String Jsonfile2, String Path)
			throws JsonIOException, JsonSyntaxException, FileNotFoundException {

		Scenario scenario = GlobalVariable.objScenarioData;
		String sScenarioName = "<span style=color:white>Comparing:- " + scenario.RequestType.trim()
				+ "-Gen-1 And Gen-3 Responses<br></span><span style = color:white;> </span>";
		new ExtendReportCreation().createStepNodeInstance(sScenarioName);
	
		File file1 = new File(Jsonfile1);
		File file2 = new File(Jsonfile2);
		JsonElement jsonobject1;
		JsonElement jsonobject2;
		JsonParser parser = new JsonParser();

		if (file1.exists() && file2.exists()) {			 
			
			jsonobject1 = new JsonParser().parse(new FileReader(Jsonfile1));
			jsonobject2 = new JsonParser().parse(new FileReader(Jsonfile2));
		
			compareJson(jsonobject1, jsonobject2, "");

		}else if(scenario.Response_Gen2 != null && scenario.Response_Gen3 !=null ) {
			
			jsonobject1 = new JsonParser().parse((String) scenario.Response_Gen2);
			jsonobject2 = new JsonParser().parse((String) scenario.Response_Gen3);

			compareJson(jsonobject1, jsonobject2, "");
			
		}else {
			
			System.out.println("Either files doesn't exist or Strings empty");
			new ExtendReportCreation().NewTestLog("Either files or response doesn't exist", Status.FAIL);
			
		}
		
		

	}

	private static void compareJson(JsonElement json, JsonElement other, String path) {

		
		
		IgnoreString.add("HostToken");
		IgnoreString.add("flightsRefs");
		IgnoreString.add("TermsAndConditionsID");
		IgnoreString.add("termsAndConditionsRef");
		IgnoreString.add("TermsAndConditions");
		IgnoreString.add("transactionId");
		IgnoreString.add("refresh_token:");
		IgnoreString.add("Identifier");
		IgnoreString.add("TransactionIdentifier");
		IgnoreString.add("Identifier|value|");
		IgnoreString.add("ExpiryDate");
		


		for (int i = 0; i < Scenario.sIgnoredata.size(); i++) {

			IgnoreString.add(Scenario.sIgnoredata.get(i));
		}
		
		String sPath = "";
		String sSource = "";
		String sDest = "";
		String Source = "Gen-1";
		String Dest = "Gen-3";
		
		 

		if (json.equals(other))
			return;

		if (json.isJsonArray() && other.isJsonArray()) {

			JsonArray arrJ = (JsonArray) json;
			JsonArray arrO = (JsonArray) other;
			int size = Math.min(arrJ.size(), arrO.size());

			for (int i = 0; i < size; i++) {
				compareJson(arrJ.get(i), arrO.get(i), path + i + "|");
			}

			if (arrJ.size() > arrO.size()) {

				for (int i = arrO.size(); i < arrJ.size(); i++) {
					// System.out.println("Path-: "+path + "\nSource:- " + arrJ.get(i)+ "\nDest:-Not
					// Available");
					sPath = "<span style=color:Aqua>Path-: " + path + "</span>";
					sSource = "<span style=color:white><br>"+Source+"-: " + arrJ.get(i) + "</span>";
					sDest = "<span style=color:yellow><br>"+Dest+"-: Not Available </span>";
					String Diff = sPath + sSource + sDest;
					// new ReportUtils().NewTestLog(Diff, Status.FAIL)
					if (CompareToIgnore(Diff.trim()) == true) {

					} else {

						
						new ExtendReportCreation().NewTestLog(Diff, Status.FAIL);

					}
				}
			} else if (arrJ.size() < arrO.size()) {
				for (int i = arrJ.size(); i < arrO.size(); i++) {
					// System.out.println("Path-: "+path + "\nSource:-Tag Not Available" +
					// "\nDest:-"+arrO.get(i));
					sPath = "<span style=color:Aqua>Path-: " + path + "</span>";
					sSource = "<span style=color:white><br>"+Source+"-: Tag Not Available</span>";
					sDest = "<span style=color:yellow><br>"+Dest+"-: " + arrO.get(i) + "</span>";
					String Diff = sPath + sSource + sDest;
					// new ReportUtils().NewTestLog(Diff, Status.FAIL)
					if (CompareToIgnore(Diff.trim()) == true) {

					} else {

						
						new ExtendReportCreation().NewTestLog(Diff, Status.FAIL);

					}

				}
			}
		} else if (json.isJsonObject() && other.isJsonObject()) {
			JsonObject objJ = (JsonObject) json;
			JsonObject objO = (JsonObject) other;

			for (Entry<String, JsonElement> entry : objJ.entrySet()) {

				String key = entry.getKey();
				JsonElement value = entry.getValue();
				if (objO.has(key)) {
					compareJson(value, objO.get(key), path + key + "|");
				} else {
					// System.out.println("Path:-"+path + "\nSource:- " + key + ": " + value
					// +"\nDest:- Not Available");

					sPath = "<span style=color:Aqua>Path-: " + path + "</span>";
					sSource = "<span style=color:white><br>"+Source+"-: " + key + ": " + value + "</span>";
					sDest = "<span style=color:yellow><br>"+Dest+"-: Not Available </span>";

					// sPath = path;
					// sSource = "Source:"+ key + ": " + value ;
					// sDest = "Dest:- Not Available" ;

					String Diff = sPath + sSource + sDest;
					// new ReportUtils().NewTestLog(Diff, Status.FAIL)
					if (CompareToIgnore(Diff.trim()) == true) {

					} else {

						
						new ExtendReportCreation().NewTestLog(Diff, Status.FAIL);

					}
				}
			}
			for (Entry<String, JsonElement> entry : objO.entrySet()) {
				String key = entry.getKey();
				JsonElement value = entry.getValue();

				if (!objJ.has(key)) {

					// System.out.println("Path:-"+path + "\nSource:- Not Available" +
					// "\nDest-:"+key + ": " + value);

					sPath = "<span style=color:Aqua>Path-: " + path + "</span>";
					sSource = "<span style=color:white><br>"+Source+"-: Not Available</span>";
					sDest = "<span style=color:yellow><br>"+Dest+"-: " + key + ": " + value + "</span>";

					// sPath = path;
					// sSource = "<br>Source:- Not Available";
					// sDest = "<br>Dest-:"+key + ": " + value ;

					String Diff = sPath + sSource + sDest;
					// new ReportUtils().NewTestLog(Diff, Status.FAIL)
					if (CompareToIgnore(Diff.trim()) == true) {

					} else {

						
						new ExtendReportCreation().NewTestLog(Diff, Status.FAIL);

					}

				}
			}

		} else if (json.isJsonPrimitive() && other.isJsonPrimitive()) {

			// System.out.println("Path:-"+path + "\nSource:-" + json + "\nDest:-" + other);

			sPath = "<span style=color:Aqua>Path-: " + path + "</span>";
			sSource = "<br><span style=color:white>"+Source+"-: " + json + "</span>";
			sDest = "<br><span style=color:yellow>"+Dest+"-: " + other + "</span>";

			// sPath = path;
			// sSource = "Source:-" + json ;
			// sDest = "Dest-:"+ other ;

			String Diff = sPath + sSource + sDest;
			// new ReportUtils().NewTestLog(Diff, Status.FAIL)
			if (CompareToIgnore(Diff.trim()) == true) {

			} else {

				// ReportUtils.TestLog(Diff , Status.FAIL);
				new ExtendReportCreation().NewTestLog(Diff, Status.FAIL);

			}

		} else {

			// System.out.println("Path:-"+path + "Source:-" + json + "\nDest:-" + other);
			sPath = "<span style=color:Aqua>Path-: " + path + "</span>";
			sSource = "<span style=color:white>"+Source+"-: " + json + "</span>";
			sDest = "<span style=color:yellow>"+Dest+"-: " + other + "</span>";

			// sPath = path;
			// sSource = "Source:-" + json ;
			// sDest = "Dest-:"+ other ;

			String Diff = sPath + sSource + sDest;
			// new ReportUtils().NewTestLog(Diff, Status.FAIL)
			if (CompareToIgnore(Diff.trim()) == true) {

			} else {

				// ReportUtils.TestLog(Diff , Status.FAIL);
				new ExtendReportCreation().NewTestLog(Diff, Status.FAIL);

			}

		}
	}
	
	
	
	public static boolean CompareToIgnore(String IgnoreStr) {

		boolean matched = false;

		Scenario scenario = GlobalVariable.objScenarioData;


//		System.out.println("IgnoreStr"+IgnoreStr);
//		System.out.println("IgnoreStr"+IgnoreString);
		for (String str : IgnoreString) {
			
			str = str.toUpperCase().strip();
			IgnoreStr = IgnoreStr.toUpperCase().strip();
//			System.out.println("IgnoreStr"+IgnoreStr);
//			System.out.println("str"+str);

//			if(IgnoreStr.contains("brand")) {
//				
//				System.out.println(IgnoreStr);
//			}

			if ((IgnoreStr.contains(str) && !IgnoreStr.contains("DESCRIPTION"))) {
//			if ((IgnoreStr.contains(str)) || (IgnoreStr.length() > 80) && (!IgnoreStr.contains("DESCRIPTION"))) {

//				System.out.println("IgnoreStr"+IgnoreStr);
				matched = true;
				return matched;

			} else if (str.contains("IGNOREDECIMALZERO")) {
//			} else if (str.contains("IGNOREDECIMALZERO") && (line1.trim().matches(regex) || line1.trim().matches(regex1)
//					|| line1.trim().matches(regex2) || line1.trim().matches(regex3))) {

//				 System.out.println("line1"+line1);
//				 System.out.println("line2"+line2);

//				matched = IgnoreDecimalZero(line1, line2);

				// println(matched)

				return matched;

			}

		}

		return matched;

	}
	
	

}
