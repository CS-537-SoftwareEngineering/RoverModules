package JSONExamples;

import java.io.FileNotFoundException;  
import java.io.FileReader;  
import java.io.IOException;  
import java.util.Iterator;  
import org.json.simple.JSONArray;  
import org.json.simple.JSONObject;  
import org.json.simple.parser.JSONParser;  
import org.json.simple.parser.ParseException;  
  
public class JSONSimpleReadingFromFileExample {  
  
 public static void main(String[] args) {  
  
  JSONParser parser = new JSONParser();  
  
  try {  
  
   Object obj = parser.parse(new FileReader("E:/MS/cs537/JSONFiles/JSONExamples/CountryJSONFile.json"));  
  
   JSONObject jsonObject = (JSONObject) obj;  
  
   String nameOfCountry = (String) jsonObject.get("Name");  
   System.out.println("Name Of Country: "+nameOfCountry);  
  
   long population = (long) jsonObject.get("Population");  
   System.out.println("Population: "+population);  
  
   System.out.println("States are :");  
   JSONArray listOfStates = (JSONArray) jsonObject.get("States");  
   Iterator<String> iterator = listOfStates.iterator();  
   while (iterator.hasNext()) {  
    System.out.println(iterator.next());  
   }  
  
  } catch (FileNotFoundException e) {  
   e.printStackTrace();  
  } catch (IOException e) {  
   e.printStackTrace();  
  } catch (ParseException e) {  
   e.printStackTrace();  
  }  
  
 }  
}  