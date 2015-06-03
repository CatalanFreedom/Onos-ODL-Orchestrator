package com.northbound.socket;

import java.io.*;
import java.net.*;


public class StockQuoteServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket client = null;
		
		BufferedReader inbound = null;
		
		try {
			//Server Socket
			serverSocket = new ServerSocket(3000);
			
			System.out.println("Waiting a request");
			while(true){
				//Waiting the client
				client = serverSocket.accept();
				
				//Obtaining the fluxes
				inbound = new BufferedReader(new InputStreamReader(client.getInputStream()));
				
				String symbol = inbound.readLine();
				System.out.println(symbol);
			}
		} catch(IOException ioe) {
			System.out.println("Error in the server: " + ioe);
		
		} finally {
			try{
			inbound.close();
			} catch(Exception e){
				System.out.println("Can't close the stream " + e.getMessage());
			}
		}

	}

}
