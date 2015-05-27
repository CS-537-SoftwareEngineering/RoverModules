package main;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class CommandSubSystemClass 
{
	
	public GenQueue<String> queue;
	public Map<String, Integer> portsList;
	public Map<String, Integer> jsonFileList;
	
	public CommandSubSystemClass()
	{
		this.queue = new GenQueue<String>();
		this.portsList = new HashMap<String, Integer>();
		this.jsonFileList = new HashMap<String, Integer>();
	}
	
	public GenQueue<String> readFile(String fileName)
	{
		String line=null;
		 try {
	         // FileReader reads text files in the default encoding.
	         FileReader fileReader = 
	             new FileReader(fileName);

	         // Always wrap FileReader in BufferedReader.
	         BufferedReader bufferedReader = 
	             new BufferedReader(fileReader);

	         while((line = bufferedReader.readLine()) != null) {
	        	 queue.enqueue(line);
	        	 //System.out.println("First item in the queue "+queue.returnHead());
	         }    

	         // Always close files.
	         bufferedReader.close();            
	     }
	     catch(FileNotFoundException ex) {
	         System.out.println(
	             "Unable to open file '" + 
	             fileName + "'");                
	     }
	     catch(IOException ex) {
	         System.out.println(
	             "Error reading file '" 
	             + fileName + "'");                   
	         // Or we could just do this: 
	         // ex.printStackTrace();
	     }
		 
		 return this.queue;
	}
	
	public void printQueue()
	{
		LinkedList<String> list = this.queue.returnQueue();
		for(int i=0;i<this.queue.size();i++)
		{
			System.out.println("item no "+ i +": "+ list.get(i));		
		}
	}

	public Map<String, Integer> getPortNumbers() 
	{
		this.portsList.put("REMS", 9001);
		this.portsList.put("TEL", 9002);
		this.portsList.put("RAD", 9003);
		this.portsList.put("ATT", 9004);
		this.portsList.put("THRM", 9005);
		this.portsList.put("MCAM", 9006);
		this.portsList.put("NCAM", 9007);
		this.portsList.put("CMIN", 9008);
		this.portsList.put("MAHLI", 9010);
		this.portsList.put("CCAM", 9011);
		this.portsList.put("PADS", 9012);
		this.portsList.put("POW", 9013);
		this.portsList.put("SAM", 9014);
		this.portsList.put("HCAM", 9015);
		this.portsList.put("ARM", 9016);
		this.portsList.put("APXS", 9017);
		this.portsList.put("MBLTY", 9018);
		this.portsList.put("DAN", 9019);
		this.portsList.put("CCDS", 9020);	
		
		return this.portsList;
	}

	public Map<String, Integer> getJsonFileNames() 
	{
		this.jsonFileList.put("REMS", 1);
		this.jsonFileList.put("TEL", 2);
		this.jsonFileList.put("RAD", 3);
		this.jsonFileList.put("ATT", 4);
		this.jsonFileList.put("THRM", 5);
		this.jsonFileList.put("MCAM", 6);
		this.jsonFileList.put("NCAM", 7);
		this.jsonFileList.put("CMIN", 8);
		this.jsonFileList.put("MAHLI", 10);
		this.jsonFileList.put("CCAM", 11);
		this.jsonFileList.put("PADS", 12);
		this.jsonFileList.put("POW", 13);
		this.jsonFileList.put("SAM", 14);
		this.jsonFileList.put("HCAM",15);
		this.jsonFileList.put("ARM", 16);
		this.jsonFileList.put("APXS",17);
		this.jsonFileList.put("MBLTY",18);
		this.jsonFileList.put("DAN", 19);
		this.jsonFileList.put("CCDS",20);	
		
		return this.jsonFileList;
	}

}
