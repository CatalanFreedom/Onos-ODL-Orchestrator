package com.northbound.flowconstructor;

import org.codehaus.jettison.json.JSONException;

import com.northbound.settings.Settings;

public class FlowInicializer {
	
	public void installFlowInicializer() throws JSONException {
	
	// ARP -----------------------------------------------------------------------------------------------------------------------------
		// Data in common
				int priority = 501;
				int etherType = -1;
				MakeNewFlowDPI newFlowIP = new MakeNewFlowDPI();
				
				
		// OF13|1 - First Switch -----------------------------------------------------------------------------
				String nodeToInstall = Settings.SWITCH_1;

			// Port 1 listening: Variables between flows		
				// name:   S1:P1:arpFlowInsert
				// in Port: ("1");
				// out Port: ("2,4,5");
				// Actual flow install:
				newFlowIP.flowInstallDPI("2", "4", "5", priority, "S1:P1:arpFlowInsert", etherType, Settings.S1_in_PORT_1, "-1", "-1", nodeToInstall);

				
				
			// Port 2 listening: Variables between flows		
				// name:   ("S1:P2:arpFlowInsert");
				// in Port: ("2");
				// out Port: ("1,4,5"));
				// Actual flow install:
				newFlowIP.flowInstallDPI("1", "4", "5", priority, "S1:P2:arpFlowInsert", etherType, Settings.S1_in_PORT_2, "-1", "-1", nodeToInstall);

				
			// Port 3 listening: Variables between flows		
				// name:   ("S1:P3:arpFlowInsert");
				// in Port: "3");
				// out Port: ("1,2,3"));
				// Actual flow install:
				newFlowIP.flowInstallDPI("1", "2", "3", priority, "S1:P3:arpFlowInsert", etherType, Settings.S1_in_PORT_3, "-1", "-1", nodeToInstall);

				
				
				
		// OF13|2 - Second Switch -----------------------------------------------------------------------------
				nodeToInstall = Settings.SWITCH_2;
				
			// Port 1 listening: Variables between flows		
				// name:   ("S2:P1:arpFlowInsert");
				// in Port: ("1");
				// out Port: ("2,3"));
				// Actual flow install:
				newFlowIP.flowInstallDPI("2", "3", "-1", priority, "S2:P1:arpFlowInsert", etherType, Settings.S2_in_PORT_1, "-1", "-1", nodeToInstall);

			// Port 2 listening: Variables between flows		
				// name:   ("S2:P2:arpFlowInsert");
				// in Port: ("2");
				// out Port: ("1,3"));
				// Actual flow install:
				newFlowIP.flowInstallDPI("1", "3", "-1", priority, "S2:P2:arpFlowInsert", etherType, Settings.S2_in_PORT_2, "-1", "-1", nodeToInstall);

			// Port 4 listening: Variables between flows		
				// name:   ("S2:P4:arpFlowInsert");
				// in Port: ("4");
				// out Port: ("1,2,3"));
				// Actual flow install:
				newFlowIP.flowInstallDPI("1", "2", "3", priority, "S2:P4:arpFlowInsert", etherType, Settings.S2_in_PORT_4, "-1", "-1", nodeToInstall);

				
				
		// OF13|3 - Third Switch -----------------------------------------------------------------------------
				nodeToInstall = Settings.SWITCH_3;
				
			// Port 1 listening: Variables between flows		
				// name:   ("S3:P1:arpFlowInsert");
				// in Port: ("1");
				// out Port: ("2"));
				// Actual flow install:
				newFlowIP.flowInstallDPI("2", "-1", "-1", priority, "S3:P1:arpFlowInsert", etherType, Settings.S3_in_PORT_1, "-1", "-1", nodeToInstall);

	
				
		
		
	// IP   --------------------------------------------------------------------------------------------------------------------------------
		
		// Data in common
				priority = 601;
				etherType = 2048;
				String inPort = "-1";
				String IP_HOST_1 = Settings.IP_HOST_1;
				String IP_HOST_2 = Settings.IP_HOST_2;
				String IP_HOST_3 = Settings.IP_HOST_3;
				String IP_HOST_4 = Settings.IP_HOST_4;

		// OF13|1 - First Switch -----------------------------------------------------------------------------
				nodeToInstall = Settings.SWITCH_1;
				
		// H1 to H2: Variables between flows
				// name:   ("S1:H1-H2:InicialFlowInsert");
				// IP_src: ("10.0.0.1");
				// IP_dst: ("10.0.0.2");
				// out Port: ("2"));
				// Actual flow install:
				newFlowIP.flowInstallDPI("2", "-1", "-1", priority, "S1:H1-H2:InicialFlowInsert", etherType, inPort, IP_HOST_1, IP_HOST_2, nodeToInstall);
				
			// H1 to H3: Variables between flows
				// name:   ("S1:H1-H3:InicialFlowInsert");
				// IP_src: ("10.0.0.1");
				// IP_dst: ("10.0.0.3");
				// out Port: ("=3,5"));
				// Actual flow install:
				newFlowIP.flowInstallDPI("3", "5", "-1", priority, "S1:H1-H3:InicialFlowInsert", etherType, inPort, IP_HOST_1, IP_HOST_3, nodeToInstall);
				
			// H1 to H4: Variables between flows
				// name:   ("S1:H1-H4:InicialFlowInsert");
				// IP_src: ("10.0.0.1");
				// IP_dst: ("10.0.0.4");
				// Actual flow install:
				newFlowIP.flowInstallDPI("3", "5", "-1", priority, "S1:H1-H4:InicialFlowInsert", etherType, inPort, IP_HOST_1, IP_HOST_4, nodeToInstall);
				
				
				
		// From H2 to H1: Variables between flows
				// name:   ("S1:H2-H1:InicialFlowInsert");
				// IP_src: ("10.0.0.2");
				// IP_dst: ("10.0.0.1");
				// out Port: ("1"));
				// Actual flow install:
				newFlowIP.flowInstallDPI("1", "-1", "-1", priority, "S1:H2-H1:InicialFlowInsert", etherType, inPort, IP_HOST_2, IP_HOST_1, nodeToInstall);

				
			// From H2 to H3: Variables between flows
				// name:   ("S1:H2-H3:InicialFlowInsert");
				// IP_src: ("10.0.0.2");
				// IP_dst: ("10.0.0.3");
				// out Port: ("3,5"));
				// Actual flow install:
				newFlowIP.flowInstallDPI("3", "5", "-1", priority, "S1:H2-H3:InicialFlowInsert", etherType, inPort, IP_HOST_2, IP_HOST_3, nodeToInstall);

			// From H2 to H4: Variables between flows
				// name:   ("S1:H2-H4:InicialFlowInsert");
				// IP_src: ("10.0.0.2");
				// IP_dst: ("10.0.0.4");
				// Actual flow install:
				newFlowIP.flowInstallDPI("3", "5", "-1", priority, "S1:H2-H4:InicialFlowInsert", etherType, inPort, IP_HOST_2, IP_HOST_4, nodeToInstall);

				
				
		// From H3 to H1: Variables between flows
				// name:   ("S1:H3-H1:InicialFlowInsert");
				// IP_src: ("10.0.0.3");
				// IP_dst: ("10.0.0.1");
				// out Port: ("1"));
				// Actual flow install:
				newFlowIP.flowInstallDPI("1", "-1", "-1", priority, "S1:H3-H1:InicialFlowInsert", etherType, inPort, IP_HOST_3, IP_HOST_1, nodeToInstall);

			// From H3 to H2: Variables between flows
				// name:   ("S1:H3-H2:InicialFlowInsert");
				// IP_src: ("10.0.0.3");
				// IP_dst: ("10.0.0.2");
				// out Port: ("2"));
				// Actual flow install:
				newFlowIP.flowInstallDPI("2", "-1", "-1", priority, "S1:H3-H2:InicialFlowInsert", etherType, inPort, IP_HOST_3, IP_HOST_2, nodeToInstall);

			
				
		// From H4 to H1: Variables between flows
				// name:   ("S1:H4-H1:InicialFlowInsert");
				// IP_src: ("10.0.0.4");
				// IP_dst: ("10.0.0.1");
				// out Port: ("1"));
				// Actual flow install:
				newFlowIP.flowInstallDPI("1", "-1", "-1", priority, "S1:H4-H1:InicialFlowInsert", etherType, inPort, IP_HOST_4, IP_HOST_1, nodeToInstall);

			// From H4 to H2: Variables between flows
				// name:   ("S1:H4-H2:InicialFlowInsert");
				// IP_src: ("10.0.0.4");
				// IP_dst: ("10.0.0.2");
				// out Port: ("2"));
				// Actual flow install:
				newFlowIP.flowInstallDPI("2", "-1", "-1", priority, "S1:H4-H2:InicialFlowInsert", etherType, inPort, IP_HOST_4, IP_HOST_2, nodeToInstall);
				
				
	// OF13|2 - Second Switch -----------------------------------------------------------------------------
				nodeToInstall = Settings.SWITCH_2;		
		
		// FROM H1
			// H1 to H3: Variables between flows
				// name:   ("S2:H1-H3:InicialFlowInsert");
				// IP_src: ("10.0.0.1");
				// IP_dst: ("10.0.0.3");
				// out Port: ("1"));
				// Actual flow install:
				newFlowIP.flowInstallDPI("1", "-1", "-1", priority, "S2:H1-H3:InicialFlowInsert", etherType, inPort, IP_HOST_1, IP_HOST_3, nodeToInstall);

				
			// H1 to H4: Variables between flows
				// name:   ("S2:H1-H4:InicialFlowInsert");
				// IP_src: ("10.0.0.1");
				// IP_dst: ("10.0.0.4");
				// out Port: ("2"));
				// Actual flow install:
				newFlowIP.flowInstallDPI("2", "-1", "-1", priority, "S2:H1-H4:InicialFlowInsert", etherType, inPort, IP_HOST_1, IP_HOST_4, nodeToInstall);

				
				
		// FROM H2		
			// H2 to H3: Variables between flows
				// name:   ("S2:H2-H3:InicialFlowInsert");
				// IP_src: ("10.0.0.2");
				// IP_dst: ("10.0.0.3");
				// out Port: ("1"));
				// Actual flow install:
				newFlowIP.flowInstallDPI("1", "-1", "-1", priority, "S2:H2-H3:InicialFlowInsert", etherType, inPort, IP_HOST_2, IP_HOST_3, nodeToInstall);

				
			// H2 to H4: Variables between flows
				// name:   ("S2:H2-H4:InicialFlowInsert");
				// IP_src: ("10.0.0.2");
				// IP_dst: ("10.0.0.4");
				// out Port: ("2"));
				// Actual flow install:
				newFlowIP.flowInstallDPI("2", "-1", "-1", priority, "S2:H2-H4:InicialFlowInsert", etherType, inPort, IP_HOST_2, IP_HOST_4, nodeToInstall);

				
				
		// FROM H3		
			// H3 to H1: Variables between flows
				// name:   ("S2:H3-H1:InicialFlowInsert");
				// IP_src: ("10.0.0.3");
				// IP_dst: ("10.0.0.1");
				// out Port: ("3"));
				// Actual flow install:
				newFlowIP.flowInstallDPI("3", "-1", "-1",priority, "S2:H3-H1:InicialFlowInsert", etherType, inPort, IP_HOST_3, IP_HOST_1, nodeToInstall);

				
			// H3 to H2: Variables between flows
				// name:   ("S2:H3-H2:InicialFlowInsert");
				// IP_src: ("10.0.0.3");
				// IP_dst: ("10.0.0.2");
				// Actual flow install:
				newFlowIP.flowInstallDPI("3", "-1", "-1", priority, "S2:H3-H2:InicialFlowInsert", etherType, inPort, IP_HOST_3, IP_HOST_2, nodeToInstall);

				
			// H3 to H4: Variables between flows
				// name:   ("S2:H3-H4:InicialFlowInsert");
				// IP_src: ("10.0.0.3");
				// IP_dst: ("10.0.0.4");
				// out Port: ("2"));
				// Actual flow install:
				newFlowIP.flowInstallDPI("2", "-1", "-1", priority, "S2:H3-H4:InicialFlowInsert", etherType, inPort, IP_HOST_3, IP_HOST_4, nodeToInstall);

				
				
		// FROM H4		
			// H4 to H1: Variables between flows
				// name:   ("S2:H4-H1:InicialFlowInsert");
				// IP_src: ("10.0.0.4");
				// IP_dst: ("10.0.0.1");
				// out Port: ("3"));
				// Actual flow install:
				newFlowIP.flowInstallDPI("3", "-1", "-1", priority, "S2:H4-H1:InicialFlowInsert", etherType, inPort, IP_HOST_4, IP_HOST_1, nodeToInstall);

				
			// H4 to H2: Variables between flows
				// name:   ("S2:H4-H2:InicialFlowInsert");
				// IP_src: ("10.0.0.4");
				// IP_dst: ("10.0.0.2");
				// Actual flow install:
				newFlowIP.flowInstallDPI("3", "-1", "-1", priority, "S2:H4-H2:InicialFlowInsert", etherType, inPort, IP_HOST_4, IP_HOST_2, nodeToInstall);

				// 
				// // H4 to H3: Variables between flows
				// name:   ("S2:H4-H3:InicialFlowInsert");
				// IP_src: ("10.0.0.4");
				// IP_dst: ("10.0.0.3");
				// out Port: ("1"));
				// Actual flow install:
				newFlowIP.flowInstallDPI("1", "-1", "-1", priority, "S2:H4-H3:InicialFlowInsert", etherType, inPort, IP_HOST_4, IP_HOST_3, nodeToInstall);

			
				
	}
	
}
