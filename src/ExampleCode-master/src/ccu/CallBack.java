package ccu;

import java.net.UnknownHostException;

import json.Constants;

public class CallBack {
	
	
	public CallBack() {
		
	}
	
	public void done() {
		CallBackClient client = null;
		try {
			client = new CallBackClient(Constants.PORT_CCU, null);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client.run();
	}
}
