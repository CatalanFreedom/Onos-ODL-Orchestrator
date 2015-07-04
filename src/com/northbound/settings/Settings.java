package com.northbound.settings;

public class Settings {

	// Chose the OpendDaylight release control (HIDROGEN or HELIUM_&_LITHIUM)
//	public static final String RELEASE = "HIDROGEN";
	public static final String RELEASE = "HELIUM_or_LITHIUM";
	
	
	// REST Parameters to log.
	public static final String USERNAME = "admin";
	public static final String PASSWORD = "admin";
	public static final String URL = choseURL(RELEASE);
	
	// Socket to connect with the DPI.
	public static final int SOCKET_PORT = 4000;

	// Option to Make our own Array to check the flows already installed, or check them 
	// asking the controller the already installed flows.
	// If 0, we are creating our oun Array
	// If 1, we are using REST API to ask the controller if it has the flow.
	public static int RESTReciverOn = 0;
	
	// Node identifiers.
	public static final String SWITCH_1 = "00:00:00:00:00:00:00:01";
	public static final String SWITCH_2 = "00:00:00:00:00:00:00:02";
	public static final String SWITCH_3 = "00:00:00:00:00:00:00:03";
	
	// IP addresses of the HOSTs.
	public static final String IP_HOST_1 = "10.0.0.1";
	public static final String IP_HOST_2 = "10.0.0.2";
	public static final String IP_HOST_3 = "10.0.0.3";
	public static final String IP_HOST_4 = "10.0.0.4";
	
	// IP address where the DPI is listening.
	public static final String HOST_DPI_LISTENING = "10.0.0.5";
	


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
