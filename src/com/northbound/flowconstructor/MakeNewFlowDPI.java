package com.northbound.flowconstructor;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.northbound.flowsender.RestInterfaceSender;
import com.northbound.settings.Settings;

public class MakeNewFlowDPI {
	
	public void flowInstallDPI(String actionToDo1, String actionToDo2, String actionToDo3,  int priority, String name, int etherType,
			String inPort, String nwSrc, String nwDst, String nodeToInstall) throws JSONException{
		
		
		if (Settings.RELEASE=="HELIUM_or_LITHIUM"){
		
			// Table in the REST API where we want to install the Flow
			String table = Settings.SWITCH_TABLE;
			
			// Translation of IPs. In Helium & in Lithium the Mask is needed.
			nwSrc = IPsrcVerifier(nwSrc);
			nwDst = IPdstVerifier(nwDst);

	
			JSONObject outputAction1 = new JSONObject();
			outputAction1.put("output-node-connector", actionToDo1);
			
			JSONObject insideAction1 = new JSONObject();
			insideAction1.put("order", 0);
			insideAction1.put("output-action", outputAction1);

			JSONArray action = new JSONArray();
			action.put(insideAction1);
			if (!actionToDo2.equals("-1")){
				JSONObject outputAction2 = new JSONObject();
				outputAction2.put("output-node-connector", actionToDo2);
				
				JSONObject insideAction2 = new JSONObject();
				insideAction2.put("order", 1);
				insideAction2.put("output-action", outputAction2);
				action.put(insideAction2);
				
				if (!actionToDo3.equals("-1")){
					JSONObject outputAction3 = new JSONObject();
					outputAction3.put("output-node-connector", actionToDo3);
	
					JSONObject insideAction3 = new JSONObject();
					insideAction3.put("order", 2);
					insideAction3.put("output-action", outputAction3);
					action.put(insideAction3);;
				}
			}
			
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
			
			JSONObject ethernetMatch = new JSONObject();
			// ARP Packet FLOW
			if(etherType!=-1){		
				JSONObject ethernetType = new JSONObject();
				ethernetType.put("type", etherType);
				ethernetMatch.put("ethernet-type", ethernetType);
			}
			
			JSONObject match = new JSONObject();
			if(nwSrc!="-1"){
				match.put("ipv4-destination", nwDst);
				match.put("ipv4-source", nwSrc);
			}else{
				match.put("in-port", inPort);
			}
			if(etherType!=-1){
				match.put("ethernet-match", ethernetMatch);
			}
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
// ----------------------------------------------------------------------------------------------------
				
					
					
		// HIDROGEN RELEASE
		
		else if(Settings.RELEASE=="HIDROGEN"){
			
			String actionToDo;
			if(actionToDo2.equals("-1")){
				actionToDo = "OUTPUT=" + actionToDo1;
			} else{
				if(actionToDo3.equals("-1")){
					actionToDo = "OUTPUT=" + actionToDo1 + "," + actionToDo2;
				} else{
					actionToDo = "OUTPUT=" + actionToDo1 + "," + actionToDo2 + "," + actionToDo3;
				}
			}
			
			
		// Flow Parameters and Actions to install the new flow that will supply the flow installed by the Inicializator.
		JSONObject flow = new JSONObject();
		flow.put("name", name);
		if(!nwSrc.equals("-1")){
			flow.put("nwSrc", nwSrc);
			flow.put("nwDst", nwDst);
		} else{
			flow.put("ingressPort", inPort);
		}
//		flow.put("installInHw", "true");
		flow.put("priority", priority);
		if(etherType!=-1){	
			flow.put("etherType", etherType);
		}
			flow.put("actions", new JSONArray().put(actionToDo));
		
	
		// Node on which this flow will be installed
		JSONObject node = new JSONObject();
		node.put("id", nodeToInstall);
		node.put("type", "OF");
		flow.put("node", node);
	
		
		// Setting the URL where to install the flow to call it
		String FLOW_PROGRAMMER_REST_API = Settings.FLOW_PROGRAMMER_REST_API;
		String baseURL = Settings.URL + FLOW_PROGRAMMER_REST_API + nodeToInstall + "/staticFlow/" + name;
	
		// Installation of the flow.
		RestInterfaceSender.installFlow(baseURL, flow);
		}
		
		
	}
	
	
// -------------------------------------------------------------------------------------------------------------
	
	
	// FUNCTIONS
	
	private String IPsrcVerifier(String nwSrc){
		if (nwSrc.equals("10.0.0.1")){
			nwSrc = nwSrc + Settings.HOST_MASK;
		}
		if (nwSrc.equals("10.0.0.2")){
			nwSrc = nwSrc + Settings.HOST_MASK;
		}
		if (nwSrc.equals("10.0.0.3")){
			nwSrc = nwSrc + Settings.HOST_MASK;
		}
		if (nwSrc.equals("10.0.0.4")){
			nwSrc = nwSrc + Settings.HOST_MASK;
		}
		return nwSrc;
	}
	
	
	private String IPdstVerifier(String nwDst){
		if (nwDst.equals("10.0.0.1")){
			nwDst = nwDst + Settings.HOST_MASK;
		}
		if (nwDst.equals("10.0.0.2")){
			nwDst = nwDst + Settings.HOST_MASK;
		}
		if (nwDst.equals("10.0.0.3")){
			nwDst = nwDst + Settings.HOST_MASK;
		}
		if (nwDst.equals("10.0.0.4")){
			nwDst = nwDst + Settings.HOST_MASK;
		}
		return nwDst;
	}
	
}
