package com.northbound.flowreciver;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.northbound.flowconstructor.FlowModel;
import com.northbound.settings.Settings;

public class RestInterfaceReciver {

	
	public FlowModel[] flowsAlreadyInstalled() throws JSONException {
		SettingsRestReciver nodes = new SettingsRestReciver();
		JSONObject myFlows = nodes.getNodes(Settings.USERNAME, Settings.PASSWORD, Settings.URL);
	//	System.out.println(myFlows);
		
		// Creation of a array of these Objects just to save every new flow and not install flows already installed.
		JSONArray array = myFlows.getJSONArray("flowConfig");
		FlowModel flowInArray[] = new FlowModel[55]; 
		
		
		for(int i = 0 ; i < array.length() ; i++){
			try{
				String nwSrc = array.getJSONObject(i).getString("nwSrc");
				flowInArray[i] = new FlowModel();
				flowInArray[i].setName(array.getJSONObject(i).getString("name"));
				flowInArray[i].setNwSrc(nwSrc);
				flowInArray[i].setNwDst(array.getJSONObject(i).getString("nwDst"));
//				System.out.println(flowInArray[i]);
			} catch(Exception e){
				continue;
			}
		}
		
		return compactingString(flowInArray);
	}
	
	
	
	public static FlowModel[] compactingString(FlowModel[] chainUnCompacted) {
	    FlowModel[] compactedString = new FlowModel[chainUnCompacted.length];
	    int j=0;
	    for (int i = 0; i < chainUnCompacted.length; i++)
	        if (chainUnCompacted[i] != null){
	        	compactedString[j] = chainUnCompacted[i];
	    		j++;
	        }

	    return compactedString;
	}
}

