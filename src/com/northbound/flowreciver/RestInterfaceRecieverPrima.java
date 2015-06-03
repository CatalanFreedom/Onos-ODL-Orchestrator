package com.northbound.flowreciver;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.northbound.flowconstructor.FlowModel;
import com.northbound.settings.Settings;


public class RestInterfaceRecieverPrima {

	/**
	 * @param args
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws JSONException {
		SettingsRestReciver nodes = new SettingsRestReciver();
		JSONObject myFlows = nodes.getNodes(Settings.USERNAME, Settings.PASSWORD, Settings.URL);
//		System.out.println(myFlows);
		
		// Creation of a array of these Objects just to save every new flow and not install flows already installed.
		FlowModel flowInArray[] = new FlowModel[55]; 
		JSONArray array = myFlows.getJSONArray("flowConfig");
		
		
		for(int i = 0 ; i < array.length() ; i++){
			try{
				String nwSrc = array.getJSONObject(i).getString("nwSrc");
				flowInArray[i] = new FlowModel();
				flowInArray[i].setName(array.getJSONObject(i).getString("name"));
				flowInArray[i].setName(nwSrc);
				flowInArray[i].setName(array.getJSONObject(i).getString("nwDst"));
				System.out.println(flowInArray[i]);
			} catch(Exception e){
				continue;
			}
		}
	}
}
