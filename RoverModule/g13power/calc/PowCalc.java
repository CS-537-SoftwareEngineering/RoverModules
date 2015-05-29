package calc;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.Attributes.Name;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PowCalc {

	public static void calc(ArrayList<Integer> powerNeed, ArrayList<String> groupNames) throws InterruptedException {
		ArrayList<Integer> Power = new ArrayList<Integer>();
        ArrayList<String>  Names = new ArrayList<String>();
        String jsonFilePath ="log.json";
		int TotalPower=504;
		int NewPower=(int) (TotalPower*(0.6));
		int div=(int) (TotalPower*(0.6));
		int temp,soc=0;
		//System.out.println("----"+NewPower);
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		//jsonObject.put("Info:", "Battery Log");
		for(int i=0;i<powerNeed.size();i++){
			System.out.println();
			temp = powerNeed.get(i);
			System.out.println("---------------------------------\n"+"POW_ON REQUEST FROM-"+groupNames.get(i)+"\n---------------------------------");
			Thread.sleep(2000);
			System.out.println("POW_CALC "+powerNeed.get(i));
			
			int x = NewPower-temp;
			if(x>0){
			NewPower=NewPower-temp;
			Thread.sleep(2000);
			System.out.println("POW_ON");
			Thread.sleep(1000);
			System.out.println("POW_OFF");
			Thread.sleep(1000);
			System.out.println("Available Power--"+NewPower);
			Thread.sleep(2000);
			 soc =((int)(NewPower*100)/TotalPower)+40;
			
			//System.out.println(NewPower+"--------"+soc);
			
				jsonArray.add(groupNames.get(i));
				jsonArray.add(powerNeed.get(i));
				jsonArray.add(NewPower+temp);
				jsonArray.add(soc);
				
			}
			else if(x<0){
				System.out.println("NOT ENOUGH POWER"+"\nSUSPENDED"+ "\nAVAILABLE POWE--"+NewPower);
				/*Power.add(powerNeed.get(i));
				Names.add(groupNames.get(i));*/
				jsonArray.add(groupNames.get(i));
				jsonArray.add(powerNeed.get(i));
				jsonArray.add(NewPower);
				jsonArray.add(soc);
			}
			 if (x>0 && x<15){
				System.out.println("\nBATTERY IS IN CHARGING MODE");
				Thread.sleep(5000);
				NewPower=(int) (TotalPower*(0.6));
				
			}
		}
		jsonObject.put("log", jsonArray);
		try {
        	
			FileWriter jsonFileWriter = new FileWriter(jsonFilePath);
        	jsonFileWriter.write(jsonObject.toJSONString());
			jsonFileWriter.flush();
			jsonFileWriter.close();
			
		//System.out.print(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
		/*System.out.println();
		System.out.println("***************************************");
		System.out.println(NewPower+"--------------------------"+Power.size());
		for(int i=0;i<Power.size();i++){
			temp = Power.get(i);
			Thread.sleep(2000);
			System.out.println("POW_CALC "+Power.get(i)+"\t"+Names.get(i));
			
			int x = NewPower-temp;
			if(x>0){
			NewPower=NewPower-temp;
			Thread.sleep(2000);
			System.out.println("POW_ON");
			Thread.sleep(2000);
			System.out.println("POW_OFF");
			System.out.println("Available Power : "+NewPower);
			}
			else if(x<0){
				System.out.println("NOT ENOUGH POWER \n"+"AVAILABLE POWER "+NewPower);
				
			}
			else{
				System.out.println("BATTERY IS IN CHARGING MODE");
				Thread.sleep(5000);
				NewPower=TotalPower;
				temp=powerNeed.get(i);
				NewPower=NewPower-temp;
				Thread.sleep(2000);
				System.out.println("POW_ON");
				Thread.sleep(2000);
				System.out.println("POW_OFF");
				System.out.println("Availabel Power : "+NewPower);
			}
			
		}*/
		
	}

	
	
}
