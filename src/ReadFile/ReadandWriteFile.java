package ReadFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadandWriteFile {

	// public static String sPath = "D:\\Urvi Folder\\import.txt";

	// public static String sPath = "HareRama:HareKrishna|123|.txt";

	// public static String sPath = "Hare#Rama@.txt$123*567";

//	public static String sPath = "C:\\Users\\Pratibha\\OneDrive\\Desktop\\LearningJava.txt";
	public static String sPath = sPath = "D:\\Urvi Folder\\Details.txt";

	public static void main(String[] args) throws IOException, InvalidFormatException {

		readfiles();

		// write();
	}

	public static void readfiles() throws IOException, InvalidFormatException {
		String line;
		Map<String, List<String>> sHashmap = new HashMap<String, List<String>>();

		BufferedReader br = new BufferedReader(new FileReader(sPath));

		while ((line = br.readLine()) != null) {

			line = line.replaceAll("\\s", "");

			if (!line.isBlank()) {
				List<String> sList = new ArrayList<>();
				String[] sName = line.split("\\|", 0);
//				System.out.println(line);
				int sNameLength = sName.length;
//				System.out.println(sNameLength);
//				System.out.println(sName[0]);
//				System.out.println(sName[1]);
//				System.out.println(sName[2]);

				sList.add(sName[1]);
				sList.add(sName[2]);
//				System.out.println(sList);

//				System.out.println(sList);

//				}
				sHashmap.put(sName[0], sList);

			}
//			sList.clear();
		}

//		sList.clear();
		br.close();
//		for (Entry<String, List<String>> entry : sHashmap.entrySet())
//			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

//		System.out.println(sHashmap);
//
//		System.out.println(sHashmap.size());
		TextToExcel.ReadAndWriteExcel(sHashmap);
		
			}

	
	
		
		
		
		
		

	private static int lastIndexOf(String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void write() throws IOException {

		File file = new File("D:\\Urvi Folder\\import.txt");
		FileWriter fw = new FileWriter(file);
		fw.write("Hello World!");
		fw.close();

		String path = sPath;
		String text = "\nAdded text";

		try {
			FileWriter fwd = new FileWriter(path, true);
			fwd.write(text);
			fwd.close();
		} catch (IOException e) {
		}

	}
}