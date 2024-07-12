package ReadFile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.text.html.HTMLEditorKit.Parser;

public class SubString {

	private static final String Integar = null;

	public static void main(String[] args) throws IOException {

		// fetchsubstring();
		//Practicesubstring();
		//NewPractice();
		//Practicetrial();
		
	}

	public static void fetchsubstring() {

		String sString = ReadandWriteFile.sPath;
		System.out.println(sString);
//		String sSubString = sString.substring(sString.indexOf("x"));
		String sSubString = sString.substring(sString.indexOf("v"), sString.lastIndexOf("."));
		sSubString = sSubString.substring(sSubString.indexOf("\\"), sSubString.lastIndexOf("r"));
//		System.out.println(sSubString);

//		for(int i=0;i<sString.length();i++) {
//			
//			System.out.println(sString.charAt(i));
//						
//			
//		}

		int j = 0;
		while (j < sString.length()) {

			char sLetter = sString.charAt(j);
			String sUpperCase = Character.toString(sLetter);

			if (sUpperCase.contentEquals(sUpperCase.toUpperCase()) == true && Character.isLetter(sLetter)) {

				System.out.println(sUpperCase);
			}

			j++;

		}

	}

	public static void NewPractice() {
		
		String sString = ReadandWriteFile.sPath.trim();
		System.out.println(sString);
		
		//String pString = sString.substring(sString.lastIndexOf("#")-1);
		//System.out.println(pString);
		
		String[] pString = sString.split("H", 2);
		System.out.println(pString[1]);
		
		String iString= sString.substring(sString.indexOf("#")+ 1, sString.lastIndexOf("@"));
		System.out.println(iString);
		
		String uString= sString.substring(sString.indexOf("@")+1, sString.lastIndexOf("$"));
		System.out.println(uString);
		
		String sNumber = sString.substring(sString.indexOf("$")+1, sString.lastIndexOf("*"));
		System.out.println(sNumber);
		
		String pNumber = sString.substring(sString.indexOf("*")+1, sString.lastIndexOf("7")+1);
		System.out.println(pNumber);
		
		int iNum = Integer.parseInt(sNumber);
		int kNum = Integer.parseInt(pNumber);
		System.out.println(iNum*kNum);
		
		String jString = sString.replaceAll("[^a-zA-Z\\s]", "");
		System.out.println(jString);
		
		sString = ("my name is urvi. i study in msud.");
	       
		String stringToInsert = "I am 19 years old. ";

        StringBuilder sb = new StringBuilder(sString);
        int insertionIndex = sString.indexOf(". ") + 2; // Find the index after the first period and space

        sb.insert(insertionIndex, stringToInsert);

        String modifiedString = sb.toString();
        System.out.println(modifiedString);
	
	}
	
	public static void Practicetrial()  throws IOException {
		
		String line;

		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Pratibha\\OneDrive\\Desktop\\LearningJava.txt"));

		while ((line = br.readLine()) != null) {

			System.out.println(line);
		
			br.close();
		
		
		
		//FileInputStream fstream = new (FileInputStream("C:\\Users\\Pratibha\\OneDrive\\Desktop\\LearningJava.txt"));
		//BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		//String strLine;

		
		//while ((strLine = br.readLine()) != null)   {
		 // System.out.println (strLine);
		}

		//fstream.close();
	
		String sString = "My name is urvi. I study in msud.";
       
		String stringToInsert = "I am 19 years old. ";

        StringBuilder sb = new StringBuilder(sString);
        int insertionIndex = sString.indexOf(". ") + 2; // Find the index after the first period and space

        sb.insert(insertionIndex, stringToInsert);

        String modifiedString = sb.toString();
        System.out.println(modifiedString);
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	public static void Practicesubstring() {

		String sString = ReadandWriteFile.sPath.trim();
		System.out.println(sString);

		int j = sString.length();
		j = sString.length() - 1;
		for (int i = j; i >= 0; i--) {

			System.out.println(sString.charAt(i)); // this will read and write the string in a reverse order

		}

		String num = ReadandWriteFile.sPath.trim();
		System.out.println(num);

		String[] IstString = sString.split("\\|", 2); // this will split the sString from second pipe symbol

		String sTxt = sString.substring(sString.indexOf("."));
		System.out.println(sTxt);
		
		String sNumber = sString.substring(sString.indexOf("|")+1, sString.lastIndexOf("|"));
		System.out.println(sNumber);
		
		int iNum = Integer.parseInt(sNumber);
		System.out.println(iNum + 7);

		
			// sString1.substring(sString1.indexOf(0)+1,sString1.lastIndexOf(":")); // this
			// code will retrive the substring from sString between first value of string
			// and till :
			// sSystem.out.println(sSubString);

		}
	}


