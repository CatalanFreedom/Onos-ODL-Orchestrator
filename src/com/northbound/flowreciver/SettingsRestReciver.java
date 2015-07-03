package com.northbound.flowreciver;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jettison.json.JSONObject;

public class SettingsRestReciver {

    public JSONObject getNodes(String user, String password,
            String baseURL) {

        StringBuffer result = new StringBuffer();
        try {

            if (!baseURL.contains("http")) {
                baseURL = "http://" + baseURL;
            }
//            baseURL = baseURL + "/restconf/operational/opendaylight-inventory:nodes/";
            baseURL = baseURL + "/restconf/config/opendaylight-inventory:nodes/node/openflow:1/flow-node-inventory:table/0/flow/20";
            
            // Create URL = base URL + container
            URL url = new URL(baseURL);

            // Create authentication string and encode it to Base64
            String authStr = user + ":" + password;
            String encodedAuthStr = Base64.encodeBase64String(authStr
                    .getBytes());

            // Create Http connection
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();

            // Set connection properties
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Basic "
                    + encodedAuthStr);
            connection.setRequestProperty("Accept", "application/json");

            // Get the response from connection's inputStream
            InputStream content = (InputStream) connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    content));
            String line = "";
            while ((line = in.readLine()) != null) {
                result.append(line);
            }

            JSONObject nodes = new JSONObject(result.toString());
            return nodes;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}