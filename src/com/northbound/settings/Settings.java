package com.northbound.settings;

public class Settings {

//  -----------------------------------------------------------------------------------------------------------------
	// Chose the OpendDaylight release control (HIDROGEN or HELIUM_&_LITHIUM)
	
//			public static final String RELEASE = "HIDROGEN";
			public static final String RELEASE = "HELIUM_or_LITHIUM";
			
//  -----------------------------------------------------------------------------------------------------------------
	
	
	// REST Parameters to log.
		public static final String USERNAME = "onos";
		public static final String PASSWORD = "rocks";
		public static final String URL = choseURL(RELEASE);
	
	// IP address where the DPI is listening.
		public static final String HOST_DPI_LISTENING = "10.0.0.5";
	
	// Socket to connect with the DPI.
		public static final int SOCKET_PORT = 5000;

	// Option to Make our own Array to check the flows already installed, or check them 
	// asking the controller the already installed flows.
	// If 0, we are creating our oun Array
	// If 1, we are using REST API to ask the controller if it has the flow.
		public static int RESTReciverOn = 0;
	
		public static final String SWITCH_TABLE = "0";	//Used to install the flows in an specific table in Lithium
		public static final String HOST_MASK = "/32";	//Used to install the flows in Lithium
//  -----------------------------------------------------------------------------------------------------------------	
	
	// Parameters for HELIUM & LITHIUM Release
	
		// URL to install the flows
			public static final String FLOW_PROGRAMMER_REST_API = "/onos/v1/flows";
		// IP addresses of the HOSTs.
			public static final String IP_HOST_1 = "10.0.0.1/32";
			public static final String IP_HOST_2 = "10.0.0.2/32";
			public static final String IP_HOST_3 = "10.0.0.3/32";
			public static final String IP_HOST_4 = "10.0.0.4/32";
		
		// Node identifiers.
			public static final String SWITCH_1 = "of:0000000000000001";
			public static final String SWITCH_2 = "of:0000000000000002";
			public static final String SWITCH_3 = "of:0000000000000003";
		
		// Input Ports per Switch
			public static final String S1_in_PORT_1 = "openflow:1:1";
			public static final String S1_in_PORT_2 = "openflow:1:2";
			public static final String S1_in_PORT_3 = "openflow:1:3";
			public static final String S2_in_PORT_1 = "openflow:2:1";
			public static final String S2_in_PORT_2 = "openflow:2:2";
			public static final String S2_in_PORT_4 = "openflow:2:4";
			public static final String S3_in_PORT_1 = "openflow:3:1";
	
	
//  ------------------------------------------------------------------------------------------------------------------
/*

	// Parameters for HIDROGEN Release
	
		// URL to install the flows
			public static final String FLOW_PROGRAMMER_REST_API = "/controller/nb/v2/flowprogrammer/default/node/OF/";
		
			// IP addresses of the HOSTs.
			public static final String IP_HOST_1 = "10.0.0.1";
			public static final String IP_HOST_2 = "10.0.0.2";
			public static final String IP_HOST_3 = "10.0.0.3";
			public static final String IP_HOST_4 = "10.0.0.4";
		
		// Node identifiers.
			public static final String SWITCH_1 = "00:00:00:00:00:00:00:01";
			public static final String SWITCH_2 = "00:00:00:00:00:00:00:02";
			public static final String SWITCH_3 = "00:00:00:00:00:00:00:03";
			
		// Input Ports per Switch
			public static final String S1_in_PORT_1 = "1";
			public static final String S1_in_PORT_2 = "2";
			public static final String S1_in_PORT_3 = "3";
			public static final String S2_in_PORT_1 = "1";
			public static final String S2_in_PORT_2 = "2";
			public static final String S2_in_PORT_4 = "4";
			public static final String S3_in_PORT_1 = "1";
	
*/
//	-------------------------------------------------------------------------------------------------------------------



	private static String choseURL(String RELEASE){
		String URL = null;
		if (RELEASE=="HIDROGEN"){
			URL = "http://192.168.56.101:8080";
		} else if (RELEASE=="HELIUM_or_LITHIUM"){
			URL = "http://192.168.56.101:8181";
		}
		return URL;
		
	}
}
