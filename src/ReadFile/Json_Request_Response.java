package ReadFile;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json_Request_Response {
	public static String sPath = sPath = "D:\\Urvi Folder\\Json Sample.json";
	public static void main(String[] args) {
		JsonReader();
	}

	public static void JsonReader() {

		try {

			String spath = "D:\\Urvi Folder\\Json Sample.json";
			Gson gson = new Gson();
			JsonObject jsonObject = gson.fromJson(new FileReader(spath), JsonObject.class);
			JsonArray peopleArray = jsonObject.getAsJsonArray("people");

			for (int i = 0; i < peopleArray.size(); i++) {
				JsonObject person = peopleArray.get(i).getAsJsonObject();
				String firstName = person.get("firstName").getAsString();
				String lastName = person.get("lastName").getAsString();
				int age = person.get("age").getAsInt();
				String gender = person.get("gender").getAsString();
				String number = person.get("number").getAsString();

				System.out.println("Name: " + "firstName" + " " + lastName + ", Urvi Age: " + age + ", Gender: "
						+ gender + ", number:" + number);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeJson() throws IOException {

		File file = new File(sPath);
		FileWriter fw = new FileWriter(file);
		fw.write("Urvi");
		fw.close();

		String path = sPath;
		String text = "Jackson";

		try {
			FileWriter fwd = new FileWriter(path, true);
			fwd.write(text);
			fwd.close();
		} catch (IOException e) {
		}

	}
}

//try {
//ObjectMapper objectMapper = new ObjectMapper();
//JsonNode jsonNode = objectMapper.readTree(new File("D:\\Urvi Folder\\Json Sample.json"));
//System.out.println(jsonNode.toPrettyString());
//Iterator<JsonNode> sArray = jsonNode.iterator();
//System.out.println(sArray.next());
//    for (int i=0; i<jsonNode.size(); i++) {
//	
//	JsonNode iList = jsonNode.get(i);
//	
//	 
//	System.out.println(jsonNode.size());
//	
//	
//}
//String name = jsonNode.get("firstName").asText();
//int age = jsonNode.get("age").asInt();

//System.out.println("Name: " + name);
//System.out.println("Age: " + age);	
