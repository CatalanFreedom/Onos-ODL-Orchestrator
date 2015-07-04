package com.northbound.mainflowinstaller;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.northbound.flowsender.RestInterfaceSender;

public class TesterFirstKarafOrdenat {

	public static void main(String[] args) throws JSONException {
		
// {"flow": [{"id": "1","instructions": {"instruction": [{"order": 0,"apply-actions": {"action":[{"order": 0,"output-action": {"output-node-connector": "2"}}]}}]},     "flow-name": "Edu","match": {"in-port": "1"},"strict": false,"table_id": 0,"priority": 999}]}]}]}}
// {"flow": [{"id": "1","instructions": {"instruction": [{"order": 0,"apply-actions": {"action":[{"order": 0,"output-action": {"output-node-connector": "2"}}]}}]},     "flow-name": "Edu","match": {"in-port": "1"},"strict": false,"table_id": 0,"priority": 999}]}
// {"flow": [{"id": "1","instructions": {"instruction": [{"order": 0,"apply-actions": {"action":[{"order": 0,"output-action": {"output-node-connector": "2"}}]}}]},"table_id":0,"priority":10,"flow-name":"S1-S2","match":{"ipv4-destination":"10.0.0.2\/32","ipv4-source":"10.0.0.1\/32","ethernet-match":{"ethernet-type":{"type":2048}}}}]}		
// {"flow": [{"id": "5","instructions": {"instruction": [{"order": 0,"apply-actions": {"action":[{"order": 0,"output-action": {"output-node-connector": "2"}}]}}]},"table_id":0,"priority":99,"flow-name":"Edu",  "match":{"ipv4-destination":"10.0.0.2\/32","ipv4-source":"10.0.0.1\/32","ethernet-match":{"ethernet-type":{"type":2048}}}}]}
// {"flow": [{"id":"20","instructions": {"instruction": [{"order": 0,"apply-actions": {"action":[{"order": 1,"output-action": {"output-node-connector":"6"}},{"order":2,"output-action":{"max-length":65535,"output-node-connector":"7"}},{"order":0,"output-action":{"max-length":65535,"output-node-connector":"5"}}]}}]},"hard-timeout":0,"flow-name":"ARP","idle-timeout":0,"match":{"ethernet-match":{"ethernet-type":{"type":2054}}},"table_id":0,"priority":3}]}
		// Sample post data.
		
// }}]}}]},     "flow-name": "Edu", "match": {"in-port": "1"},"strict": false,"table_id": 0,"priority": 999}]}
// }}]}}]},     "flow-name":"S1-S2","match": {"ipv4-destination":"10.0.0.2\/32","ipv4-source":"10.0.0.1\/32","ethernet-match":{"ethernet-type":{"type":2048}}}}]}		
		
		String idName = "S1:H1-H3:InicialFlowInsert";
		String nwSrc = "10.0.0.1/32";
		String nwDst = "10.0.0.3/32";
		int priority = 601;
		String switchToInstall = "openflow:1";
		String table = "0";
		
		JSONObject outputAction1 = new JSONObject();
		outputAction1.put("output-node-connector", "3");
		
		JSONObject outputAction2 = new JSONObject();
		outputAction2.put("output-node-connector", "5");
		
		JSONObject outputAction3 = new JSONObject();
		outputAction3.put("output-node-connector", "5");
		
		JSONObject insideAction1 = new JSONObject();
		insideAction1.put("order", 0);
		insideAction1.put("output-action", outputAction1);
		
		JSONObject insideAction2 = new JSONObject();
		insideAction2.put("order", 1);
		insideAction2.put("output-action", outputAction2);
		
		JSONObject insideAction3 = new JSONObject();
		insideAction3.put("order", 2);
		insideAction3.put("output-action", outputAction3);
		
		JSONArray action = new JSONArray();
		action.put(insideAction1);
		action.put(insideAction2);
		action.put(insideAction3);
		
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
		flowInside.put("id", idName);
		flowInside.put("instructions", Instruction);
		flowInside.put("table_id", 0);
		flowInside.put("priority", 10);
		flowInside.put("flow-name", idName);
		
		
		
		JSONObject ethernetType = new JSONObject();
		ethernetType.put("type", 2048);
				
		JSONObject ethernetMatch = new JSONObject();
		ethernetMatch.put("ethernet-type", ethernetType);
		
		JSONObject match = new JSONObject();
		match.put("ipv4-destination", nwSrc);
		match.put("ipv4-source", nwDst);
		match.put("ethernet-match", ethernetMatch);
		flowInside.put("match", match);
		
		flowInside.put("table_id", 0);
		flowInside.put("priority", priority);
		
		JSONArray flowInsideArray = new JSONArray();
		flowInsideArray.put(flowInside);
		
		JSONObject flow = new JSONObject();
		flow.put("flow", flowInsideArray);
		
		

		
		
		// Actual flow install
		RestInterfaceSender.installFlow(switchToInstall, table, idName, flow);

	}

}