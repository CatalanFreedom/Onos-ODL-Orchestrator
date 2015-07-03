package com.northbound.flowconstructor;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.northbound.flowsender.RestInterfaceSender;

public class MakeNewFlowDPI {
	
	public void flowInstallDPI(String actionToDo1, String actionToDo2, String actionToDo3,  String numID, int priority, String name, int etherType, String nwSrc, String nwDst, 
			String nodeToInstall) throws JSONException{
		
		
				
		String table = "0";
		/*		
				actionToDo = "2"; //Port to send the packet
				numID = "5";
				priority = 555;
				name = "H1-H2";
				etherType = 2048;
				nwSrc = "10.0.0.1/32";
				nwDst = "10.0.0.2/32";
				nodeToInstall = "openflow:1";
		 */
				
		
				JSONObject outputAction1 = new JSONObject();
				outputAction1.put("output-node-connector", actionToDo1);
				
				JSONObject outputAction2 = new JSONObject();
				outputAction2.put("output-node-connector", actionToDo2);
				
				JSONObject outputAction3 = new JSONObject();
				outputAction3.put("output-node-connector", actionToDo3);
				
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
				action.put(insideAction3);;
				
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
				flowInside.put("id", numID);
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
				
				
				
				
				// Actual flow install
				RestInterfaceSender.installFlow(nodeToInstall, table, numID, flow);
		
		
		
		
		
		
		
		
		
		
		
		
/*		
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
*/
	}
}
