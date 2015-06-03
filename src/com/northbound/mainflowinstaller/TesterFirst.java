package com.northbound.mainflowinstaller;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.northbound.flowsender.RestInterfaceSender;

public class TesterFirst {

	public static void main(String[] args) throws JSONException {
		
		
//		First Switch
		
		// Sample post data.
		JSONObject postData = new JSONObject();
		postData.put("name", "drop1FlowInsert");
		postData.put("nwSrc", "10.0.0.3");
		postData.put("nwDst", "192.168.56.67");
		postData.put("installInHw", "true");
		postData.put("priority", "999");
//		postData.put("etherType", "0x800");
		postData.put("ingressPort", "1");
		JSONArray accio = new JSONArray();
		postData.put("actions", accio.put("OUTPUT=2"));
		
		
		// Node on which this flow should be installed
		JSONObject node = new JSONObject();
		node.put("id", "00:00:00:00:00:00:00:01");
		node.put("type", "OF");
		postData.put("node", node);

		// Actual flow install
		RestInterfaceSender.installFlow("00:00:00:00:00:00:00:01", "drop1FlowInsert", postData);

//		FlowManager.deleteFlow("dropFlowInsert", "00:00:00:00:00:00:00:01");

	}

}
