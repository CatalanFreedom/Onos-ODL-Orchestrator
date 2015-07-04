package com.northbound.mainflowinstaller;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.northbound.flowsender.RestInterfaceSender;

public class TesterFirst {

	public static void main(String[] args) throws JSONException {
		
// {"flow": [{"id": "1","instructions": {"instruction": [{"order": 0,"apply-actions": {"action":[{"order": 0,"output-action": {"output-node-connector": "2"}}]}}]},     "flow-name": "Edu","match": {"in-port": "1"},"strict": false,"table_id": 0,"priority": 999}]}]}]}}
// {"flow": [{"id": "1","instructions": {"instruction": [{"order": 0,"apply-actions": {"action":[{"order": 0,"output-action": {"output-node-connector": "2"}}]}}]},     "flow-name": "Edu","match": {"in-port": "1"},"strict": false,"table_id": 0,"priority": 999}]}
// {"flow": [{"id": "1","instructions": {"instruction": [{"order": 0,"apply-actions": {"action":[{"order": 0,"output-action": {"output-node-connector": "2"}}]}}]},"table_id":0,"priority":10,"flow-name":"S1-S2","match":{"ipv4-destination":"10.0.0.2\/32","ipv4-source":"10.0.0.1\/32","ethernet-match":{"ethernet-type":{"type":2048}}}}]}		
// {"flow": [{"id": "5","instructions": {"instruction": [{"order": 0,"apply-actions": {"action":[{"order": 0,"output-action": {"output-node-connector": "2"}}]}}]},"table_id":0,"priority":99,"flow-name":"Edu",  "match":{"ipv4-destination":"10.0.0.2\/32","ipv4-source":"10.0.0.1\/32","ethernet-match":{"ethernet-type":{"type":2048}}}}]}
// {"flow": [{"id":"20","instructions": {"instruction": [{"order": 0,"apply-actions": {"action":[{"order": 1,"output-action": {"output-node-connector":"6"}},{"order":2,"output-action":{"output-node-connector":"7"}},{"order":0,"output-action":{"output-node-connector":"5"}}]}}]},"flow-name":"ARP","idle-timeout":0,"match":{"ethernet-match":{"ethernet-type":{"type":2054}}},"table_id":0,"priority":3}]}
// {"flow": [{"id":"S1","instructions": {"instruction": [{"order": 0,"apply-actions": {"action":[{"order": 0,"output-action": {"output-node-connector":"2"}},{"order":2,"output-action":{"output-node-connector":"2"}},{"order":1,"output-action":{"output-node-connector":"2"}}]}}]},"table_id":0,"priority":601,"match":{"ethernet-match":{"ethernet-type":{"type":2048}},"ipv4-destination":"10.0.0.5\/32","ipv4-source":"10.0.0.1\/32"},"flow-name":"S1:H1-H2:InicialFlowInsert"}]}
		
		// {"flow": [{"id": "3","instructions": {"instruction": [{"order": 0,"apply-actions": {"action":[{"order": 0,"output-action": {"output-node-connector":"1"}}]}}]},"priority":10,                                  "match":{"in-port":"openflow:1:3","ethernet-match":{"ethernet-type":{"type":2048}}},"table_id":0,"flow-name":"StdFlow"}]}		
		// Sample post data.
		
		String nwSrc = "1";
		
		// IP FLOW
		
		String switchToInstall = "openflow:1";
		String table = "0";
		String flowNum = "1";
		int etherType = 2048;

		
		JSONObject outputAction1 = new JSONObject();
		outputAction1.put("output-node-connector", "2");
		
		JSONObject outputAction2 = new JSONObject();
		outputAction2.put("output-node-connector", "4");
		
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
		flowInside.put("id", flowNum);
		flowInside.put("instructions", Instruction);
		flowInside.put("table_id", 0);
		flowInside.put("priority", 501);
		flowInside.put("flow-name", flowNum);
		
		JSONObject ethernetMatch = new JSONObject();
		if(etherType!=-1){		// ARP Packet FLOW
			JSONObject ethernetType = new JSONObject();
			ethernetType.put("type", 2054);
					
			ethernetMatch.put("ethernet-type", ethernetType);
		}
		
		JSONObject match = new JSONObject();
		if(nwSrc!="-1"){
			match.put("ipv4-destination", "10.0.0.5/32");
			match.put("ipv4-source", "10.0.0.1/32");
		}else{
			match.put("in-port", "openflow:1:4");
		}
		if(etherType!=-1){
			match.put("ethernet-match", ethernetMatch);
		}
		flowInside.put("match", match);
		
		flowInside.put("table_id", 0);
		flowInside.put("priority", 501);
		
		JSONArray flowInsideArray = new JSONArray();
		flowInsideArray.put(flowInside);
		
		JSONObject flow = new JSONObject();
		flow.put("flow", flowInsideArray);
		
		
		// Actual flow install
		RestInterfaceSender.installFlow(switchToInstall, table, flowNum, flow);

/*		
		
		// ARP FLOW
		// {"flow": [{"id":"20","instructions": {"instruction": [{"order": 0,"apply-actions": {"action":[{"order": 1,"output-action": {"output-node-connector":"6"}},{"order":2,"output-action":{"max-length":65535,"output-node-connector":"7"}},{"order":0,"output-action":{"max-length":65535,"output-node-connector":"5"}}]}}]},"hard-timeout":0,"flow-name":"ARP","idle-timeout":0,"match":{"ethernet-match":{"ethernet-type":{"type":2054}}},"table_id":0,"priority":3}]}
		// {"flow": [{"id": "3","instructions": {"instruction": [{"order": 0,"apply-actions": {"action":[{"order": 0,"output-action": {"output-node-connector":"1"}}]}}]},"priority":10,                                                                                                                                                                                                "match":{"in-port":"openflow:1:3","ethernet-match":{"ethernet-type":{"type":2048}}},"table_id":0,"flow-name":"StdFlow"}]}		

		String switchToInstall = "openflow:1";
		String table = "0";
		String flowNum = "S2:H2-H3:InicialFlowInsert";

		
		JSONObject outputAction1 = new JSONObject();
		outputAction1.put("output-node-connector", "77");
		
		JSONObject outputAction2 = new JSONObject();
		outputAction2.put("output-node-connector", "5");
		
		JSONObject outputAction3 = new JSONObject();
		outputAction3.put("output-node-connector", "17");
		
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
		flowInside.put("id", flowNum);
		flowInside.put("instructions", Instruction);
		flowInside.put("table_id", 0);
		flowInside.put("priority", 10);
		flowInside.put("flow-name", "Marieta");
		

		JSONObject ethernetType = new JSONObject();
		ethernetType.put("type", 2048);
				
		JSONObject ethernetMatch = new JSONObject();
		ethernetMatch.put("ethernet-type", ethernetType);
		
		JSONObject match = new JSONObject();
		match.put("ipv4-destination", "10.0.0.5/32");
		match.put("ipv4-source", "10.0.0.1/32");
		match.put("ethernet-match", ethernetMatch);
		flowInside.put("match", match);
		
		flowInside.put("table_id", 0);
		flowInside.put("priority", 999);
		
		JSONArray flowInsideArray = new JSONArray();
		flowInsideArray.put(flowInside);
		
		JSONObject flow = new JSONObject();
		flow.put("flow", flowInsideArray);
*/
		
		
		
	}

}
