package Comparision;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ScenarioInvoker {

	public static String city = GlobalVariable.agencyCity;
	public static String PCC = GlobalVariable.PCC;
	public static String output;

	public static String ConvertDaystoDate(String sNumberofDays, String formattype) {

//		System.out.println(sNumberofDays);

		if (sNumberofDays != null) {

			int sDays = Integer.parseInt(sNumberofDays);

			if (formattype.equalsIgnoreCase("ddmmm")) {

				SimpleDateFormat sdf = new SimpleDateFormat("ddmmm");
				Calendar c = Calendar.getInstance();
				c.setTime(new Date()); // Using today's date
				c.add(Calendar.DATE, sDays); // Adding days
				output = sdf.format(c.getTime());
//				System.out.println(output);

			} else if (formattype.equalsIgnoreCase("yyyy-MM-dd")) {

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = Calendar.getInstance();
				c.setTime(new Date()); // Using today's date
				c.add(Calendar.DATE, sDays); // Adding days
				output = sdf.format(c.getTime());
//				System.out.println(output);

			}

		}

		return output;

	}

	public static boolean isAlphaNumeric(String s) {

		char[] chars = s.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char c : chars) {
			if (Character.isDigit(c)) {
				sb.append(c);
				return true;
			}
		}

		return false;
	}
}
