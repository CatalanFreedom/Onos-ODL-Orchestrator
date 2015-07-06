package com.northbound.mainflowinstaller;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.northbound.flowsender.RestInterfaceSender;
import com.northbound.settings.Settings;

public class TesterKaraf {

	public static void main(String[] args) throws JSONException {
		
// {"flow": [{"id": "1","instructions": {"instruction": [{"order": 0,"apply-actions": {"action":[{"order": 0,"output-action": {"output-node-connector": "2"}}]}}]},     "flow-name": "Edu","match": {"in-port": "1"},"strict": false,"table_id": 0,"priority": 999}]}]}]}}
// {"flow": [{"id": "1","instructions": {"instruction": [{"order": 0,"apply-actions": {"action":[{"order": 0,"output-action": {"output-node-connector": "2"}}]}}]},     "flow-name": "Edu","match": {"in-port": "1"},"strict": false,"table_id": 0,"priority": 999}]}
// {"flow": [{"id": "1","instructions": {"instruction": [{"order": 0,"apply-actions": {"action":[{"order": 0,"output-action": {"output-node-connector": "2"}}]}}]},"table_id":0,"priority":10,"flow-name":"S1-S2","match":{"ipv4-destination":"10.0.0.2\/32","ipv4-source":"10.0.0.1\/32","ethernet-match":{"ethernet-type":{"type":2048}}}}]}		
// {"flow": [{"id": "5","instructions": {"instruction": [{"order": 0,"apply-actions": {"action":[{"order": 0,"output-action": {"output-node-connector": "2"}}]}}]},"table_id":0,"priority":99,"flow-name":"Edu",  "match":{"ipv4-destination":"10.0.0.2\/32","ipv4-source":"10.0.0.1\/32","ethernet-match":{"ethernet-type":{"type":2048}}}}]}
		// Sample post data.
		
// }}]}}]},     "flow-name": "Edu", "match": {"in-port": "1"},"strict": false,"table_id": 0,"priority": 999}]}
// }}]}}]},     "flow-name":"S1-S2","match": {"ipv4-destination":"10.0.0.2\/32","ipv4-source":"10.0.0.1\/32","ethernet-match":{"ethernet-type":{"type":2048}}}}]}		
		
		String actionToDo = "2";
		int priority = 555;
		String name = "H1-H2";
		int etherType = 2048;
		String nwDst = "10.0.0.2/32";
		String nwSrc = "10.0.0.1/32";
		String nodeToInstall = "openflow:1";
		String table = "0";

		
		
		JSONObject outputAction = new JSONObject();
		outputAction.put("output-node-connector", actionToDo);
		JSONArray action = new JSONArray();
		JSONObject insideAction = new JSONObject();
		insideAction.put("order", 0);
		insideAction.put("output-action", outputAction);
		action.put(insideAction);
		
		JSONObject applyActions = new JSONObject();
		applyActions.put("action", action);
		
		JSONObject insideInstruction = new JSONObject();
		insideInstruction.put("order", 0);
		insideInstruction.put("apply-actions",applyActions);
		
		JSONArray insideInstructionArray = new JSONArray();
		insideInstructionArray.put(insideInstruction);
	
		JSONObject Instruction = new JSONObject();
		Instruction.put("instruction",insideInstructionArray);
		
		JSONObject flowInside = new JSONObject();
		flowInside.put("id", name);
		flowInside.put("instructions", Instruction);
		flowInside.put("table_id", table);
		flowInside.put("priority", priority);
		flowInside.put("flow-name", name);
		
		
		
		JSONObject ethernetType = new JSONObject();
		ethernetType.put("type", etherType);
				
		JSONObject ethernetMatch = new JSONObject();
		ethernetMatch.put("ethernet-type", ethernetType);
		
		JSONObject match = new JSONObject();
		match.put("ipv4-destination", nwDst);
		match.put("ipv4-source", nwSrc);
		match.put("ethernet-match", ethernetMatch);
		flowInside.put("match", match);
		
		flowInside.put("table_id", 0);
		flowInside.put("priority", priority);
		
		JSONArray flowInsideArray = new JSONArray();
		flowInsideArray.put(flowInside);
		
		JSONObject flow = new JSONObject();
		flow.put("flow", flowInsideArray);
		
		
		// Setting the URL where to install the flow to call it
					String FLOW_PROGRAMMER_REST_API = Settings.FLOW_PROGRAMMER_REST_API;
					String baseURL = Settings.URL + FLOW_PROGRAMMER_REST_API + nodeToInstall + "/table/" + table
							+ "/flow/" + name;
					
					
					// Actual flow install
					RestInterfaceSender.installFlow(baseURL, flow);

	}

}
