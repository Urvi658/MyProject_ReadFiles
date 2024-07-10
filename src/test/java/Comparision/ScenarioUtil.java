package Comparision;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ScenarioUtil {

	public static String excelFilePath;
	
	public static String sPropertyFilePath = GlobalVariable.sProjectPath; 

	static void initScenarioObject() {

		GlobalVariable.objScenarioData = new Scenario();

	}


	static void configEndpointByComponent() {
		
		Scenario scenario = GlobalVariable.objScenarioData;
		
		String sVersion = scenario.version.trim().toUpperCase();
		String sRequestType = scenario.servicename.trim().toUpperCase();
		
//		System.out.println(sVersion);
//		System.out.println(sRequestType);
		Endpoint endpoint = null;		
				
		Properties props = new Properties();
		
		try {
//			
//			props.load(new FileInputStream(
//					"C:\\Users\\uma.pal\\eclipse-workspace\\VersionComparision\\src\\test\\resources\\EndPoints\\Endpoint.properties"));
			
//			System.out.println( sPropertyFilePath + "\\src\\test\\resources\\EndPoints\\Endpoint.properties");
			
			props.load(new FileInputStream(sPropertyFilePath + "\\src\\test\\resources\\EndPoints\\Endpoint.properties"));
			
			
			
			GlobalVariable.objEndpointData = props.getProperty(sVersion+"."+sRequestType.trim());
			endpoint.baseUrl = props.getProperty(sVersion+"."+sRequestType.trim()); 
//			System.out.println(GlobalVariable.objEndpointData);
			
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}

	static void RetrievePTC(Scenario scenario) {

		int iCnt = scenario.sPassengerType.size();
		
		// println(iCnt);
		// String[] sPtcTypes = scenario.sPassengerType.keySet();
		// println(sPtcTypes[1]);
		
		Object sPtcType;
		Integer sPtcCnt;

		for (int i = 0; i < iCnt; i++) {

			// println(i);
			switch (i) {

			case 0:

//				System.out.println("the iterator is "+i);
				sPtcType = scenario.sPassengerType.keySet().toArray()[i];
				sPtcCnt = scenario.sPassengerType.get(sPtcType);
				GlobalVariable.s1stPtcType = sPtcType;
				GlobalVariable.s1stPtcCnt = sPtcCnt;
//				System.out.println(sPtcType);
//				System.out.println(sPtcCnt);
				scenario.sPtccnt.put(GlobalVariable.s1stPtcType.toString(), sPtcCnt);
				break;

			case 1:

				sPtcType = scenario.sPassengerType.keySet().toArray()[i];
				sPtcCnt = scenario.sPassengerType.get(sPtcType);
				GlobalVariable.s2ndPtcType = sPtcType;
				GlobalVariable.s2ndPtcCnt = sPtcCnt;
				scenario.sPtccnt.put(GlobalVariable.s2ndPtcType.toString(), sPtcCnt);
				// println(sPtcType);
				// println(sPtcCnt);
				break;

			case 2:

				sPtcType = scenario.sPassengerType.keySet().toArray()[i];
				sPtcCnt = scenario.sPassengerType.get(sPtcType);
				GlobalVariable.s3rdPtcType = sPtcType;
				GlobalVariable.s3rdPtcCnt = sPtcCnt;
				scenario.sPtccnt.put(GlobalVariable.s3rdPtcType.toString(), sPtcCnt);
				// println(sPtcType);
				// println(sPtcCnt);
				break;

			case 3:

				sPtcType = scenario.sPassengerType.keySet().toArray()[i];
				sPtcCnt = scenario.sPassengerType.get(sPtcType);
				GlobalVariable.s4thPtcType = sPtcType;
				GlobalVariable.s4thPtcCnt = sPtcCnt;
				scenario.sPtccnt.put(GlobalVariable.s4thPtcType.toString(), sPtcCnt);
				// println(sPtcType);
				// println(sPtcCnt);
				break;

			case 4:
				sPtcType = scenario.sPassengerType.keySet().toArray()[i];
				sPtcCnt = scenario.sPassengerType.get(sPtcType);
				GlobalVariable.s5thPtcType = sPtcType;
				GlobalVariable.s5thPtcCnt = sPtcCnt;
				scenario.sPtccnt.put(GlobalVariable.s5thPtcType.toString(), sPtcCnt);
				// println(sPtcType);
				// println(sPtcCnt);
				break;

			case 5:
				sPtcType = scenario.sPassengerType.keySet().toArray()[i];
				sPtcCnt = scenario.sPassengerType.get(sPtcType);
				GlobalVariable.s6thPtcType = sPtcType;
				GlobalVariable.s6thPtcCnt = sPtcCnt;
				scenario.sPtccnt.put(GlobalVariable.s6thPtcType.toString(), sPtcCnt);
				// println(sPtcType);
				// println(sPtcCnt);
				break;

			case 6:
				sPtcType = scenario.sPassengerType.keySet().toArray()[i];
				sPtcCnt = scenario.sPassengerType.get(sPtcType);
				GlobalVariable.s7thPtcType = sPtcType;
				GlobalVariable.s7thPtcCnt = sPtcCnt;
				scenario.sPtccnt.put(GlobalVariable.s7thPtcType.toString(), sPtcCnt);
				// println(sPtcType);
				// println(sPtcCnt);
				break;

			case 7:

				sPtcType = scenario.sPassengerType.keySet().toArray()[i];
				sPtcCnt = scenario.sPassengerType.get(sPtcType);
				GlobalVariable.s8thPtcType = sPtcType;
				GlobalVariable.s8thPtcCnt = sPtcCnt;
				scenario.sPtccnt.put(GlobalVariable.s8thPtcType.toString(), sPtcCnt);
				// println(sPtcType);
				// println(sPtcCnt);
				break;

			}

		}

		GlobalVariable.objScenarioData = scenario;

	}

}
