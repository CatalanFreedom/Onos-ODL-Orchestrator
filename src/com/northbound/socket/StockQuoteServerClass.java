package com.northbound.socket;

import java.io.*;
import java.net.*;

import com.northbound.settings.Settings;

public class StockQuoteServerClass {		// Server Socket to get the analysis of the DPI(Socket Client)

	public String socketServer() {
		ServerSocket serverSocket = null;
		Socket client = null;
		
		BufferedReader inbound = null;
		String symbolFlow = null;
		try {
			//Server Socket
			serverSocket = new ServerSocket(Settings.SOCKET_PORT);
			
			System.out.println("\nWaiting a request");
				
				//Waiting the client
				client = serverSocket.accept();
				
				//Obtaining the fluxes
				inbound = new BufferedReader(new InputStreamReader(client.getInputStream()));
				
				symbolFlow = inbound.readLine();
//				System.out.println(symbolFlow);
		
		} catch(IOException ioe) {
			System.out.println("Error in the server: " + ioe);
		
		} finally {
			try{
				serverSocket.close();
				inbound.close();
			} catch(Exception e){
				System.out.println("Can't close the stream " + e.getMessage());
			}
		}

		return symbolFlow;
	}

}
