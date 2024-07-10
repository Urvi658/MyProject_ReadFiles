package Comparision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scenario {

	public String carrierOption = "";

	public static String RequestType = "";

	public static String version = "";

	public static String endpoint = "";

	public static String servicename = "";

	public static String sTestStep = "";
	 
	public static Object Response_Gen2;

	public static Object Response_Gen3;

	public static Object Response_Gen2_NextLeg;

	public static Object Response_Gen3_NextLeg;
	
	public static Object Price_Response_Gen1;
	
	public static Object Price_Response_Gen3;
	
 

	public Integer upsell = 0;

	public String sPTC = "";

	public static HashMap<String, Integer> sPassengerType = new HashMap<String, Integer>();

	public Map<String, String> sTokenValue = new HashMap<String, String>();

	public List<String> sNewArraylist = new ArrayList<String>();

	public static HashMap<String, ArrayList<String>> sMultipleValue = new HashMap<String, ArrayList<String>>();

	public static List<String> sIgnoredata = new ArrayList<String>();
	
	public static List<String> sSkiptags = new ArrayList<String>();	
	
	public static boolean sIgnoreValue = false;
	
	public static boolean sSkiptagsValue = false;
	
	public static HashMap<String, Integer> sPtccnt = new HashMap<String, Integer>();
	
	

}
