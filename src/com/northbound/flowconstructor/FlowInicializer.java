package com.northbound.flowconstructor;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.northbound.flowsender.RestInterfaceSender;

public class FlowInicializer {
	
	public void installFlowInicializer() throws JSONException {
/*	
		// ARP -----------------------------------------------------------------------------------------------------------------------------
		
	// Data in common
				JSONObject arpData = new JSONObject();
				arpData.put("installInHw", "true");
				arpData.put("priority", "501");
				
		// OF13|1 - First Switch -----------------------------------------------------------------------------
				JSONObject nodo = new JSONObject();
				nodo.put("id", "00:00:00:00:00:00:00:01");
				nodo.put("type", "OF");
				arpData.put("node", nodo);

			// Port 1 listening: Variables between flows		
				arpData.put("name", "S1:P1:arpFlowInsert");
				arpData.put("ingressPort", "1");
				JSONArray accion = new JSONArray();
				arpData.put("actions", accion.put("OUTPUT=2,4,5"));
				// Actual flow install
				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:01", "S1:P1:arpFlowInsert", arpData);
				arpData.put("actions", accion.remove("OUTPUT=2,4,5"));
				
			// Port 2 listening: Variables between flows		
				arpData.put("name", "S1:P2:arpFlowInsert");
				arpData.put("ingressPort", "2");
				arpData.put("actions", accion.put("OUTPUT=1,4,5"));
				// Actual flow install
				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:01", "S1:P2:arpFlowInsert", arpData);
				arpData.put("actions", accion.remove("OUTPUT=1,4,5"));
				
			// Port 3 listening: Variables between flows		
				arpData.put("name", "S1:P3:arpFlowInsert");
				arpData.put("ingressPort", "3");
				arpData.put("actions", accion.put("OUTPUT=1,2,3"));
				// Actual flow install
				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:01", "S1:P3:arpFlowInsert", arpData);
				arpData.put("actions", accion.remove("OUTPUT=1,2,3"));
				
				
		// OF13|2 - Second Switch -----------------------------------------------------------------------------
				nodo.put("id", "00:00:00:00:00:00:00:02");
				
			// Port 1 listening: Variables between flows		
				arpData.put("name", "S2:P1:arpFlowInsert");
				arpData.put("ingressPort", "1");
				arpData.put("actions", accion.put("OUTPUT=2,3"));
				// Actual flow install
				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:02", "S2:P1:arpFlowInsert", arpData);
				arpData.put("actions", accion.remove("OUTPUT=2,3"));
				
			// Port 2 listening: Variables between flows		
				arpData.put("name", "S2:P2:arpFlowInsert");
				arpData.put("ingressPort", "2");
				arpData.put("actions", accion.put("OUTPUT=1,3"));
				// Actual flow install
				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:02", "S2:P2:arpFlowInsert", arpData);
				arpData.put("actions", accion.remove("OUTPUT=1,3"));
				
			// Port 4 listening: Variables between flows		
				arpData.put("name", "S2:P4:arpFlowInsert");
				arpData.put("ingressPort", "4");
				arpData.put("actions", accion.put("OUTPUT=1,2,3"));
				// Actual flow install
				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:02", "S2:P4:arpFlowInsert", arpData);
				arpData.put("actions", accion.remove("OUTPUT=1,2,3"));
				
				
		// OF13|3 - Third Switch -----------------------------------------------------------------------------
				nodo.put("id", "00:00:00:00:00:00:00:03");
				
			// Port 1 listening: Variables between flows		
				arpData.put("name", "S3:P1:arpFlowInsert");
				arpData.put("ingressPort", "1");
				arpData.put("actions", accion.put("OUTPUT=2"));
				// Actual flow install
				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:03", "S3:P1:arpFlowInsert", arpData);
				arpData.put("actions", accion.remove("OUTPUT=2"));
				
	
	*/	
		
		// IP   --------------------------------------------------------------------------------------------------------------------------------
		
				int priority = 601;
				int etherType = 2048;
				String nodeToInstall = "openflow:1";
	// Data in common
				MakeNewFlowDPI newFlowIP = new MakeNewFlowDPI();
//				JSONObject postData = new JSONObject();
//				postData.put("installInHw", "true");
//				postData.put("priority", "601");
//				postData.put("etherType", "0x800");

		// OF13|1 - First Switch -----------------------------------------------------------------------------
//				JSONObject node = new JSONObject();
//				node.put("id", "00:00:00:00:00:00:00:01");
//				node.put("type", "OF");
//				postData.put("node", node);
				
				
		// H1 to H2: Variables between flows
//				postData.put("name", "S1:H1-H2:InicialFlowInsert");
//				postData.put("nwSrc", "10.0.0.1");
//				postData.put("nwDst", "10.0.0.2");
//				JSONArray accio = new JSONArray();
//				postData.put("actions", accio.put("OUTPUT=2"));
//				// Actual flow install
//				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:01", "S1:H1-H2:InicialFlowInsert", postData);
//				postData.put("actions", accio.remove("OUTPUT=2"));
			//flowInstallDPI(String actionToDo,  String numID, int priority, String name, int etherType, String nwSrc, String nwDst, String nodeToInstall)	
				newFlowIP.flowInstallDPI("2", "3", "4", "10", priority, "S1:H1-H2:InicialFlowInsert", etherType, "10.0.0.1/32", "10.0.0.2/32", nodeToInstall);
				
			// H1 to H3: Variables between flows
//				postData.put("name", "S1:H1-H3:InicialFlowInsert");
//				postData.put("nwSrc", "10.0.0.1");
//				postData.put("nwDst", "10.0.0.3");
//				postData.put("actions", accio.put("OUTPUT=3,5"));
//				// Actual flow install
//				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:01", "S1:H1-H3:InicialFlowInsert", postData);
				newFlowIP.flowInstallDPI("3", "5", "5", "11", priority, "S1:H1-H3:InicialFlowInsert", etherType, "10.0.0.1/32", "10.0.0.3/32", nodeToInstall);
				
			// H1 to H4: Variables between flows
//				postData.put("name", "S1:H1-H4:InicialFlowInsert");
//				postData.put("nwSrc", "10.0.0.1");
//				postData.put("nwDst", "10.0.0.4");
//				// Actual flow install
//				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:01", "S1:H1-H4:InicialFlowInsert", postData);
//				postData.put("actions", accio.remove("OUTPUT=3,5"));
				newFlowIP.flowInstallDPI("3", "5", "5", "12", priority, "S1:H1-H4:InicialFlowInsert", etherType, "10.0.0.1/32", "10.0.0.4/32", nodeToInstall);
				
				
				
		// From H2 to H1: Variables between flows
//				postData.put("name", "S1:H2-H1:InicialFlowInsert");
//				postData.put("nwSrc", "10.0.0.2");
//				postData.put("nwDst", "10.0.0.1");
//				postData.put("actions", accio.put("OUTPUT=1"));
//				// Actual flow install
//				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:01", "S1:H2-H1:InicialFlowInsert", postData);
//				postData.put("actions", accio.remove("OUTPUT=1"));
				newFlowIP.flowInstallDPI("1", "1", "1", "13", priority, "S1:H2-H1:InicialFlowInsert", etherType, "10.0.0.2/32", "10.0.0.1/32", nodeToInstall);

				
			// From H2 to H3: Variables between flows
//				postData.put("name", "S1:H2-H3:InicialFlowInsert");
//				postData.put("nwSrc", "10.0.0.2");
//				postData.put("nwDst", "10.0.0.3");
//				postData.put("actions", accio.put("OUTPUT=3,5"));
//				// Actual flow install
//				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:01", "S1:H2-H3:InicialFlowInsert", postData);
				newFlowIP.flowInstallDPI("3", "5", "5", "14", priority, "S1:H2-H3:InicialFlowInsert", etherType, "10.0.0.2/32", "10.0.0.3/32", nodeToInstall);

			// From H2 to H4: Variables between flows
//				postData.put("name", "S1:H2-H4:InicialFlowInsert");
//				postData.put("nwSrc", "10.0.0.2");
//				postData.put("nwDst", "10.0.0.4");
//				// Actual flow install
//				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:01", "S1:H2-H4:InicialFlowInsert", postData);
//				postData.put("actions", accio.remove("OUTPUT=3,5"));
				newFlowIP.flowInstallDPI("3", "5", "5", "15", priority, "S1:H2-H4:InicialFlowInsert", etherType, "10.0.0.2/32", "10.0.0.4/32", nodeToInstall);

				
				
		// From H3 to H1: Variables between flows
//				postData.put("name", "S1:H3-H1:InicialFlowInsert");
//				postData.put("nwSrc", "10.0.0.3");
//				postData.put("nwDst", "10.0.0.1");
//				postData.put("actions", accio.put("OUTPUT=1"));
//				// Actual flow install
//				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:01", "S1:H3-H1:InicialFlowInsert", postData);
//				postData.put("actions", accio.remove("OUTPUT=1"));
				newFlowIP.flowInstallDPI("1", "1", "1", "16", priority, "S1:H3-H1:InicialFlowInsert", etherType, "10.0.0.3/32", "10.0.0.1/32", nodeToInstall);

			// From H3 to H2: Variables between flows
//				postData.put("name", "S1:H3-H2:InicialFlowInsert");
//				postData.put("nwSrc", "10.0.0.3");
//				postData.put("nwDst", "10.0.0.2");
//				postData.put("actions", accio.put("OUTPUT=2"));
//				// Actual flow install
//				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:01", "S1:H3-H2:InicialFlowInsert", postData);
//				postData.put("actions", accio.remove("OUTPUT=2"));
				newFlowIP.flowInstallDPI("2", "2", "2", "17", priority, "S1:H3-H2:InicialFlowInsert", etherType, "10.0.0.3/32", "10.0.0.2/32", nodeToInstall);

			
				
		// From H4 to H1: Variables between flows
//				postData.put("name", "S1:H4-H1:InicialFlowInsert");
//				postData.put("nwSrc", "10.0.0.4");
//				postData.put("nwDst", "10.0.0.1");
//				postData.put("actions", accio.put("OUTPUT=1"));
//				// Actual flow install
//				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:01", "S1:H4-H1:InicialFlowInsert", postData);
//				postData.put("actions", accio.remove("OUTPUT=1"));
				newFlowIP.flowInstallDPI("1", "1", "1", "18", priority, "S1:H4-H1:InicialFlowInsert", etherType, "10.0.0.4/32", "10.0.0.1/32", nodeToInstall);

			// From H4 to H2: Variables between flows
//				postData.put("name", "S1:H4-H2:InicialFlowInsert");
//				postData.put("nwSrc", "10.0.0.4");
//				postData.put("nwDst", "10.0.0.2");
//				postData.put("actions", accio.put("OUTPUT=2"));
//				// Actual flow install
//				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:01", "S1:H4-H2:InicialFlowInsert", postData);
//				postData.put("actions", accio.remove("OUTPUT=2"));
				newFlowIP.flowInstallDPI("2", "2", "2", "19", priority, "S1:H4-H2:InicialFlowInsert", etherType, "10.0.0.4/32", "10.0.0.2/32", nodeToInstall);
				
				
	nodeToInstall = "openflow:2";		
	// OF13|2 - Second Switch -----------------------------------------------------------------------------
//				node.put("id", "00:00:00:00:00:00:00:02");
	
		// H1 to H3: Variables between flows
//				postData.put("name", "S2:H1-H3:InicialFlowInsert");
//				postData.put("nwSrc", "10.0.0.1");
//				postData.put("nwDst", "10.0.0.3");
//				postData.put("actions", accio.put("OUTPUT=1"));
//				// Actual flow install
//				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:02", "S2:H1-H3:InicialFlowInsert", postData);
//				postData.put("actions", accio.remove("OUTPUT=1"));
				newFlowIP.flowInstallDPI("2", "2", "2", "10", priority, "S1:H3-H2:InicialFlowInsert", etherType, "10.0.0.3/32", "10.0.0.2/32", nodeToInstall);

				
			// H1 to H4: Variables between flows
//				postData.put("name", "S2:H1-H4:InicialFlowInsert");
//				postData.put("nwSrc", "10.0.0.1");
//				postData.put("nwDst", "10.0.0.4");
//				postData.put("actions", accio.put("OUTPUT=2"));
//				// Actual flow install
//				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:02", "S2:H1-H4:InicialFlowInsert", postData);
//				postData.put("actions", accio.remove("OUTPUT=2"));
				newFlowIP.flowInstallDPI("2", "2", "2", "11", priority, "S1:H3-H2:InicialFlowInsert", etherType, "10.0.0.3/32", "10.0.0.2/32", nodeToInstall);

				
				
		// H2 to H3: Variables between flows
//				postData.put("name", "S2:H2-H3:InicialFlowInsert");
//				postData.put("nwSrc", "10.0.0.2");
//				postData.put("nwDst", "10.0.0.3");
//				postData.put("actions", accio.put("OUTPUT=1"));
//				// Actual flow install
//				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:02", "S2:H2-H3:InicialFlowInsert", postData);
//				postData.put("actions", accio.remove("OUTPUT=1"));
				newFlowIP.flowInstallDPI("2", "2", "2", "12", priority, "S1:H3-H2:InicialFlowInsert", etherType, "10.0.0.3/32", "10.0.0.2/32", nodeToInstall);

				
			// H2 to H4: Variables between flows
//				postData.put("name", "S2:H2-H4:InicialFlowInsert");
//				postData.put("nwSrc", "10.0.0.2");
//				postData.put("nwDst", "10.0.0.4");
//				postData.put("actions", accio.put("OUTPUT=2"));
//				// Actual flow install
//				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:02", "S2:H2-H4:InicialFlowInsert", postData);
//				postData.put("actions", accio.remove("OUTPUT=2"));
				newFlowIP.flowInstallDPI("2", "2", "2", "13", priority, "S1:H3-H2:InicialFlowInsert", etherType, "10.0.0.3/32", "10.0.0.2/32", nodeToInstall);

				
				
				
		// H3 to H1: Variables between flows
//				postData.put("name", "S2:H3-H1:InicialFlowInsert");
//				postData.put("nwSrc", "10.0.0.3");
//				postData.put("nwDst", "10.0.0.1");
//				postData.put("actions", accio.put("OUTPUT=3"));
//				// Actual flow install
//				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:02", "S2:H3-H1:InicialFlowInsert", postData);
				newFlowIP.flowInstallDPI("2", "2", "2", "14", priority, "S1:H3-H2:InicialFlowInsert", etherType, "10.0.0.3/32", "10.0.0.2/32", nodeToInstall);

				
			// H3 to H2: Variables between flows
//				postData.put("name", "S1:H3-H2:InicialFlowInsert");
//				postData.put("nwSrc", "10.0.0.3");
//				postData.put("nwDst", "10.0.0.2");
//				// Actual flow install
//				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:02", "S1:H3-H2:InicialFlowInsert", postData);
//				postData.put("actions", accio.remove("OUTPUT=3"));
				newFlowIP.flowInstallDPI("2", "2", "2", "15", priority, "S1:H3-H2:InicialFlowInsert", etherType, "10.0.0.3/32", "10.0.0.2/32", nodeToInstall);

				
			// H3 to H4: Variables between flows
//				postData.put("name", "S2:H3-H4:InicialFlowInsert");
//				postData.put("nwSrc", "10.0.0.3");
//				postData.put("nwDst", "10.0.0.4");
//				postData.put("actions", accio.put("OUTPUT=2"));
//				// Actual flow install
//				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:02", "S2:H3-H4:InicialFlowInsert", postData);
//				postData.put("actions", accio.remove("OUTPUT=2"));
				newFlowIP.flowInstallDPI("2", "2", "2", "16", priority, "S1:H3-H2:InicialFlowInsert", etherType, "10.0.0.3/32", "10.0.0.2/32", nodeToInstall);

				
				
				
		// H4 to H1: Variables between flows
//				postData.put("name", "S2:H4-H1:InicialFlowInsert");
//				postData.put("nwSrc", "10.0.0.4");
//				postData.put("nwDst", "10.0.0.1");
//				postData.put("actions", accio.put("OUTPUT=3"));
//				// Actual flow install
//				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:02", "S2:H4-H1:InicialFlowInsert", postData);
				newFlowIP.flowInstallDPI("2", "2", "2", "17", priority, "S1:H3-H2:InicialFlowInsert", etherType, "10.0.0.3/32", "10.0.0.2/32", nodeToInstall);

				
			// H4 to H2: Variables between flows
//				postData.put("name", "S2:H4-H2:InicialFlowInsert");
//				postData.put("nwSrc", "10.0.0.4");
//				postData.put("nwDst", "10.0.0.2");
//				// Actual flow install
//				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:02", "S2:H4-H2:InicialFlowInsert", postData);
//				postData.put("actions", accio.remove("OUTPUT=3"));
				newFlowIP.flowInstallDPI("2", "2", "2", "18", priority, "S1:H3-H2:InicialFlowInsert", etherType, "10.0.0.3/32", "10.0.0.2/32", nodeToInstall);

//				
//				// H4 to H3: Variables between flows
//				postData.put("name", "S2:H4-H3:InicialFlowInsert");
//				postData.put("nwSrc", "10.0.0.4");
//				postData.put("nwDst", "10.0.0.3");
//				postData.put("actions", accio.put("OUTPUT=1"));
//				// Actual flow install
//				RestInterfaceSender.installFlow("00:00:00:00:00:00:00:02", "S2:H4-H3:InicialFlowInsert", postData);
				newFlowIP.flowInstallDPI("2", "2", "2", "19", priority, "S1:H3-H2:InicialFlowInsert", etherType, "10.0.0.3/32", "10.0.0.2/32", nodeToInstall);

			
				
	}
	
}
