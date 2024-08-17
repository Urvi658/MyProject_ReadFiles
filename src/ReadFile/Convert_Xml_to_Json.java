package ReadFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import org.json.JSONObject;
import org.json.XML;
import java.util.*;
import org.json.*;

public class Convert_Xml_to_Json {

	public static void main(String[] args) {

		ConvertXML();
	}

	public static void ConvertXML() {

		String xmlFilePath = "D:\\Urvi Folder\\sample.xml";
		File file = new File(xmlFilePath);

		if (file.exists()) {

			try {
				// Read XML file content
				String xmlContent = new String(Files.readAllBytes(Paths.get(xmlFilePath)));

				// Convert XML content to JSON object
				JSONObject jsonObject = XML.toJSONObject(xmlContent);

				if (jsonObject.getClass().toString().contains("JSONObject")) {
					System.out.println(jsonObject.getClass());
					// Convert JSON object to string with pretty print
					String jsonPrettyPrintString = jsonObject.toString();
					System.out.println(jsonPrettyPrintString.getClass());
					System.out.println(jsonPrettyPrintString);
					// Write JSON content to file
					String jsonFilePath = "D:\\Urvi Folder\\JSON_Sample.json";

					Files.write(Paths.get(jsonFilePath), jsonPrettyPrintString.getBytes());

					System.out.println("XML successfully converted to JSON");
				} else {

					System.out.println("XML not successfully converted to JSON.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {

			System.out.println("File doesnt exist");

		}
	}

}


