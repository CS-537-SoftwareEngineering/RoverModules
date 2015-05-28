package apxsCode;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.jfree.ui.RefineryUtilities;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import json.MyWriter;


public class apxsSpectra {
	
	public apxsSpectra(String apxs){
	
	
		System.out.println("Sample checking.");
		JSONParser jsonParser = new JSONParser();
		
		try{
			FileReader reader= new FileReader("17.json");
			
			int count = (int) 0;
			Double numberOfSamples;
			Double elementWeight;
			Double offset, resp, constCross, umean, deviation;
			
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			JSONArray elements = (JSONArray) jsonObject.get("element");
			Iterator i = elements.iterator();
			
			while (i.hasNext()){
					JSONObject innerObj = (JSONObject) i.next();
					numberOfSamples =  (Double) innerObj.get("numberOfSamples");
					count += numberOfSamples;
			}
			i = elements.iterator();
			double[] cps = new double[count];
			while (i.hasNext()){
				JSONObject innerObj = (JSONObject) i.next();
				elementWeight = (Double) innerObj.get("elementWeight");
				offset = (Double) innerObj.get("offset");
				resp = (Double) innerObj.get("resp");
				constCross = (Double) innerObj.get("const");
				umean = (Double) innerObj.get("umean");
				deviation = (Double) innerObj.get("deviation");
				double max= (Double) (umean + deviation);
				double min= (Double) (umean - deviation);
				for(int j= 0; j < count; j++){
					cps[j] = (Double) ((offset + (constCross * resp * elementWeight) + 
							((1 - constCross) * resp * elementWeight * (Math.random()* (max-min) + min))))/1000;	
				}
			}
			
			double[] kev = new double[count];
			double energy = 491.4;
			
			for(int j=0; j < count ; j++){
				kev[j] = energy/1000;
				energy += (Math.random() * 10.00 + 45);
			}
			
			JSONArray keV = new JSONArray();
			JSONArray countPerSec = new JSONArray();
			for(int j=0; j< count; j++){
				keV.add(kev[j]);
				countPerSec.add(cps[j]);
			}
			
			JSONObject obj = new JSONObject();
			obj.put("keV", keV);
			obj.put("countPerSec", countPerSec);
			System.out.println(obj);
			
			MyWriter mywriter = new MyWriter(obj, 18);
			
			final XYSeriesDemo demo = new XYSeriesDemo("XY Series Demo");
			demo.pack();
			RefineryUtilities.centerFrameOnScreen(demo);
			demo.setVisible(true);
			
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	

	}
}
