package com.northbound.mainflowinstaller;

import org.codehaus.jettison.json.JSONException;

import com.northbound.chainreader.ChainReader;
import com.northbound.flowconstructor.FlowInicializer;
import com.northbound.flowconstructor.FlowModel;
import com.northbound.flowconstructor.MakeNewFlowDPI;
import com.northbound.flowreciver.RestInterfaceReciver;
import com.northbound.settings.Settings;
import com.northbound.socket.StockQuoteServerClass;

public class MainFlowInstallerNB {

	public static void main(String[] args) throws JSONException {

		// Creation of a new Object (Model) to save each time the name, the @IPs and the @IPd of the packet that we have obtained the chain by the DPI.
		FlowModel flowIn = new FlowModel(); 
		
		// Creation of a array of these Objects just to save every new flow and not install flows already installed.
		FlowModel flowInArray[] = new FlowModel[55]; 
		
		// Initialization of the flows in the controller (ARP & IP). This flows are just active until the DPI sent the information required to update them.
		FlowInicializer initFlow = new FlowInicializer();
		initFlow.installFlowInicializer();

		// loop to listen continuously the socket connected with the DPI and actualize the flows.
		while(true){
			// Open the socket and receive the answers
			StockQuoteServerClass serverSocket = new StockQuoteServerClass();
			String flowSymbol = serverSocket.socketServer();
			System.out.println(flowSymbol);
			
			// Call to the function ChainReader to decrypt the chain received.
			ChainReader flowDecoded = new ChainReader(flowSymbol);
			int OUTPUT_PORTS[] = flowDecoded.getFlowDecoded();
			String actionToDoS1_go;
			String actionToDoS2_go;
			String actionToDoS3_go;
			
			String actionToDoS1_return = null;
			String actionToDoS2_return = null;
			String actionToDoS3_return = null;
			
			// Getting the action to do.
			if (OUTPUT_PORTS[0] != -1){			// If it is not a ICMPV6 or MDNS packet, that do not have @IPs and @IPd.
				if (OUTPUT_PORTS[0] != -2){		// If the packet comes from a known source.
					
					actionToDoS1_go = Integer.toString(OUTPUT_PORTS[0]);
					actionToDoS2_go = Integer.toString(OUTPUT_PORTS[1]);
					actionToDoS3_go = Integer.toString(OUTPUT_PORTS[2]);
					
					actionToDoS1_return = Integer.toString(OUTPUT_PORTS[3]);
					actionToDoS2_return = Integer.toString(OUTPUT_PORTS[4]);
					actionToDoS3_return = Integer.toString(OUTPUT_PORTS[5]);
					
				} else {					// If the packet comes from an unknown source.
					actionToDoS1_go = "DROP";
					actionToDoS2_go = "DROP";
					actionToDoS3_go = "DROP";
				}
				
				// Getting the name, the @IPs and the @IPd of the packet that we have obtained the chain by the DPI.
				String nwSrc_go = flowDecoded.getNwSrc();
				String nwDst_go = flowDecoded.getNwDst();
				String name_go = flowDecoded.getName() + ":from:" + nwSrc_go + ":dest:" + nwDst_go;
				
				// In the come back way, the Source is interchanged with the destination.
				String nwSrc_return = flowDecoded.getNwDst();
				String nwDst_return = flowDecoded.getNwSrc();
				String name_return = flowDecoded.getName() + ":from:" + nwDst_go + ":dest:" + nwSrc_go;
				
				// Filling the Object Model to compare it with the previous Flows installed in the case that we are not using the
				// REST interface to know the already
				flowIn.setName(name_go);
				flowIn.setNwSrc(nwSrc_go);
				flowIn.setNwDst(nwDst_go);
					
				if (Settings.RESTReciverOn == 1){
					RestInterfaceReciver recivingFlowsAlreadyInstalled = new RestInterfaceReciver();
					flowInArray = recivingFlowsAlreadyInstalled.flowsAlreadyInstalled();
				}
				
				// Installing the new flow.
				int etherType = 2048;
				int priority = 999;
				String inPort = "-1";
				String portNotUsed = "-1";
				String portDPIListening = "5";
				int j=0;
				while (j < flowInArray.length){
				
					if (flowInArray[j] == null){		// If the flow have not been installed before, we installed it now.
						MakeNewFlowDPI newInstall = new MakeNewFlowDPI();
						newInstall.flowInstallDPI(actionToDoS1_go, portDPIListening, portNotUsed, priority, name_go, etherType, inPort, nwSrc_go, nwDst_go, Settings.SWITCH_1);
						newInstall.flowInstallDPI(actionToDoS2_go, portNotUsed, portNotUsed, priority, name_go, etherType, inPort, nwSrc_go, nwDst_go, Settings.SWITCH_2);
						newInstall.flowInstallDPI(actionToDoS3_go, portNotUsed, portNotUsed, priority, name_go, etherType, inPort, nwSrc_go, nwDst_go, Settings.SWITCH_3);
						
						// Flows of the come back journey.
						if(actionToDoS1_return != null){
							newInstall.flowInstallDPI(actionToDoS1_return, portNotUsed, portNotUsed, priority, name_return, etherType, inPort, nwSrc_return, nwDst_return, Settings.SWITCH_1);
							newInstall.flowInstallDPI(actionToDoS2_return, portNotUsed, portNotUsed, priority, name_return, etherType, inPort, nwSrc_return, nwDst_return, Settings.SWITCH_2);
							newInstall.flowInstallDPI(actionToDoS3_return, portNotUsed, portNotUsed, priority, name_return, etherType, inPort, nwSrc_return, nwDst_return, Settings.SWITCH_3);

						}
						
						// Filling the Comparing array if we are not using the REST Receiving application.
						if (Settings.RESTReciverOn == 0){
							flowInArray[j] = new FlowModel();
							flowInArray[j].setName(name_go);
							flowInArray[j].setNwSrc(nwSrc_go);
							flowInArray[j].setNwDst(nwDst_go);
						}
						
						break;
						
					} 							// If the flow have been installed before, we do not install it and we wait for the next flow.
					else if (flowIn.getName().equals(flowInArray[j].getName()) &&
								flowIn.getNwDst().equals(flowInArray[j].getNwDst()) &&
								flowIn.getNwSrc().equals(flowInArray[j].getNwSrc())){
						System.out.println("Same flow already installed previously");
						break;
			
					} else{
						j++;
					}
				}
			} else{		// Omitting the ICMPV6 & MDNS packets that do not have @IPs and @IPd.
				System.out.println("We omit this packet becouse it is a ICMPV6 or MDNS packet, that do not have @IPs and @IPd");
			}
		}
	}	
}
