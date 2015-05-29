import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String myFilePath = "13.json";
		
		// JSONParser is used to parse the data
		JSONParser parser = new JSONParser();
		
		// notice JSON passes integers as Longs
		long myPower = 0;
		
		
		// the double list is passed as its own object
		Object myList = null;
		
		try {
			/*Object obj = parser.parse(new FileReader(myFilePath));
			JSONObject jsonObject = (JSONObject) obj;
			myPower = (long) jsonObject.get("power");
			System.out.println(">>>>>>>"+myPower);*/
			
			 JSONParser jsonParser = new JSONParser();
			 JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(myFilePath));
			 
			 JSONArray lang= (JSONArray) jsonObject.get("info");
			 
			 for(int i=0; i<lang.size(); i++){
               System.out.println("The " + i + " element of the array: "+lang.get(i));
			 }

		} catch (ParseException e) {
			System.out.println("Parse exception found.");
			e.printStackTrace();
		}
  catch(Exception error){
	System.out.println("Server: Error:" + error.getMessage());
}
	}

}
