package com.northbound.northboundconf;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jettison.json.JSONObject;

import com.northbound.settings.Settings;

public class RestInterface {

	private static String FLOW_PROGRAMMER_REST_API = "/controller/nb/v2/flowprogrammer/default/node/OF/";

	// HTTP statuses for checking the call output
	private static final int ACTUALIZED = 200;
	private static final int NO_CONTENT = 204;
	private static final int CREATED = 201;

	public static boolean installFlow(String nodeId, String flowName,
			JSONObject postData) {

		HttpURLConnection connection = null;
		int callStatus = 0;

		// Creating the actual URL to call
		String baseURL = Settings.URL + FLOW_PROGRAMMER_REST_API + nodeId
				+ "/staticFlow/" + flowName;

		try {

			// Create URL = base URL + container
			URL url = new URL(baseURL);

			// Create authentication string and encode it to Base64
			String authStr = Settings.USERNAME + ":" + Settings.PASSWORD;
			String encodedAuthStr = Base64.encodeBase64String(authStr
					.getBytes());

			// Create Http connection
			connection = (HttpURLConnection) url.openConnection();

			// Set connection properties
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Authorization", "Basic "
					+ encodedAuthStr);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Set Post Data
			OutputStream os = connection.getOutputStream();
			os.write(postData.toString().getBytes());
			os.close();

			// Getting the response code
			callStatus = connection.getResponseCode();

		} catch (Exception e) {
			System.err.println("Unexpected error while flow installation.. "
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			if (connection != null)
				connection.disconnect();
		}

		if (callStatus == CREATED) {
			System.out.println("Flow installed Successfully");
			return true;
		} else {
			if(callStatus != ACTUALIZED){
				System.err.println("Failed to install flow.. " + callStatus);
			} else{
				System.err.println("Flow actualized.. " + callStatus);
			}
			return false;
		}
	}

	public static boolean deleteFlow(String flowName, String nodeId) {

		HttpURLConnection connection = null;
		int callStatus = 0;
		String baseURL = Settings.URL + FLOW_PROGRAMMER_REST_API + nodeId
				+ "/staticFlow/" + flowName;

		try {

			// Create URL = base URL + container
			URL url = new URL(baseURL);

			// Create authentication string and encode it to Base64
			String authStr = Settings.USERNAME + ":" + Settings.PASSWORD;
			String encodedAuthStr = Base64.encodeBase64String(authStr
					.getBytes());

			// Create Http connection
			connection = (HttpURLConnection) url.openConnection();

			// Set connection properties
			connection.setRequestMethod("DELETE");
			connection.setRequestProperty("Authorization", "Basic "
					+ encodedAuthStr);
			connection.setRequestProperty("Content-Type", "application/json");

			callStatus = connection.getResponseCode();

		} catch (Exception e) {
			System.err.println("Unexpected error while flow deletion.."
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			if (connection != null)
				connection.disconnect();
		}

		if (callStatus == NO_CONTENT) {
			System.out.println("Flow deleted Successfully ");
			return true;
		} else {
			System.err.println("Failed to delete the flow...\n" + callStatus + "\n");
			return false;
		}
	}

}
