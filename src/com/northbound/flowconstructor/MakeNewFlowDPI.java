package com.northbound.flowconstructor;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.northbound.flowsender.RestInterfaceSender;
import com.northbound.settings.Settings;

public class MakeNewFlowDPI {
	
//	public static void main(String[] args){
//		try {
//			//owInstallDPI("2", "4", "5", priority, "S1:P1:arpFlowInsert", etherType, Settings.S1_in_PORT_1, "-1", "-1", nodeToInstall);
//			flowInstallDPI("1", "2", "4", 40000, "name", -1, "1", "10.0.0.1/32", "10.0.0.3/32", "of:0000000000000001");
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public void flowInstallDPI(String actionToDo1, String actionToDo2, String actionToDo3,  int priority, String name, int etherType,
			String inPort, String nwSrc, String nwDst, String nodeToInstall) throws JSONException{
		
		
		if (Settings.RELEASE=="HELIUM_or_LITHIUM"){
		
			
			JSONObject instructions1 = new JSONObject();
			instructions1.put("type", "OUTPUT");
			instructions1.put("port", actionToDo1);

			JSONArray instructions = new JSONArray();
			instructions.put(instructions1);
			if (!actionToDo2.equals("-1")){
				JSONObject instructions2 = new JSONObject();
				instructions2.put("type", "OUTPUT");
				instructions2.put("port", actionToDo2);
				
				instructions.put(instructions2);
				
				if (!actionToDo3.equals("-1")){
					JSONObject instructions3 = new JSONObject();
					instructions3.put("type", "OUTPUT");
					instructions3.put("port", actionToDo3);
	
					instructions.put(instructions3);
				}
			}
			
			JSONObject treatment = new JSONObject();
			treatment.put("instructions", instructions);
			
			
			JSONObject flowInside = new JSONObject();
			flowInside.put("priority", priority);
			flowInside.put("timeout", 0);
			flowInside.put("isPermanent", true);
			flowInside.put("deviceId", nodeToInstall);
			flowInside.put("treatment", treatment);


			JSONObject criteria1 = new JSONObject();
			criteria1.put("type", "IPV4_DST");
			criteria1.put("ip", nwDst);
			
			JSONObject criteria2 = new JSONObject();
			criteria2.put("type", "IPV4_SRC");
			criteria2.put("ip", nwSrc);	
			
			JSONObject criteria3 = new JSONObject();
			criteria3.put("type", "ETH_TYPE");
			criteria3.put("ethType", 2048);	
			
			JSONArray criteria = new JSONArray();
			criteria.put(criteria1);
			criteria.put(criteria2);
			criteria.put(criteria3);
			
			JSONObject selector = new JSONObject();
			selector.put("criteria", criteria);
			
			flowInside.put("selector", selector);
			
//			String flowString1 = "{\"priority\":50000,\"timeout\":0,\"isPermanent\":true,\"deviceId\":\"of:0000000000000001\",\"treatment\":{\"instructions\":[{\"type\":\"OUTPUT\",\"port\":\"2\"}]},\"selector\":{\"criteria\":[{\"type\":\"IN_PORT\",\"port\":1},{\"type\":\"ETH_TYPE\",\"ethType\":\"0x800\"},{\"type\":\"IPV4_SRC\",\"ip\":\"10.0.0.1/32\"},{\"type\":\"IPV4_DST\",\"ip\":\"10.0.0.2/32\"}]}}";
			
			// Setting the URL where to install the flow to call it
			String baseURL = "http://192.168.56.101:8181/onos/v1/flows/" + nodeToInstall;
			
			// Actual flow install
			RestInterfaceSender.installFlow(baseURL, flowInside);
			
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
	
	private static String IPsrcVerifier(String nwSrc){
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
	
	
	private static String IPdstVerifier(String nwDst){
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
