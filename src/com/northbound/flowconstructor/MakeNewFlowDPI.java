package com.northbound.flowconstructor;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.northbound.flowsender.RestInterfaceSender;

public class MakeNewFlowDPI {
	
	public void flowInstallDPI(String name, String nwSrc, String nwDst, String actionToDo, String nodeToInstall) throws JSONException{
		
		// Flow Parameters and Actions to install the new flow that will supply the flow installed by the Inicializator.
		JSONObject postData = new JSONObject();
		postData.put("name", name);
		postData.put("nwSrc", nwSrc);
		postData.put("nwDst", nwDst);
		postData.put("installInHw", "true");
		postData.put("priority", "999");
		postData.put("etherType", "0x800");
		postData.put("actions", new JSONArray().put(actionToDo));

	
		// Node on which this flow will be installed
		JSONObject node = new JSONObject();
		node.put("id", nodeToInstall);
		node.put("type", "OF");
		postData.put("node", node);
	
		// Installation of the flow.
		RestInterfaceSender.installFlow(nodeToInstall, name, postData);
		
	}
}
