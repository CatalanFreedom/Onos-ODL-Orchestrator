package com.northbound.chainreader;

public class ChainReader {

	private String flowSymbol;		// Chain obtained from the DPI.
	private String[] arrayWords;	// Chain separated by Strings.
	private int lenght;				// Number of Strings that have the chain.
	
	// Constructor: receiving the chain.
	public ChainReader(String flowSymbol) {
		this.flowSymbol = flowSymbol;
	}
	
	
	// Method to return the ports to flow the packet depending on the: Number of the Switch, @IPs, @IPd, Type of protocol.
	public int[] getFlowDecoded(){
		
		int OUTPUT_PORT_SWITCH_1_go = 0;
		int OUTPUT_PORT_SWITCH_2_go = 0;
		int OUTPUT_PORT_SWITCH_3_go = 0;
		
		int OUTPUT_PORT_SWITCH_1_return = 0;
		int OUTPUT_PORT_SWITCH_2_return = 0;
		int OUTPUT_PORT_SWITCH_3_return = 0;
		
		int IPNumSrc_go;
		int IPNumDst_go;
		
		int IPNumSrc_return;
		int IPNumDst_return;
		
		setArrayWords(flowSymbol);
		
		if (lenght > 7) {
			OUTPUT_PORT_SWITCH_1_go = -1;		// ICMPV6 & MDNS packets that do not have @IPs and @IPd.
	
		} else {							// All the other packets.
			
			FullMatrix matrix = new FullMatrix();
			IPNumSrc_go = decodeSourceIP(arrayWords[0]);
			IPNumDst_go = decodeDestinationIP(arrayWords[2]);
			
			// To come back, the Source and the Destinations are just interchanged.
			IPNumSrc_return = decodeDestinationIP(arrayWords[2]);
			IPNumDst_return = decodeSourceIP(arrayWords[0]);
			
			if (IPNumSrc_go != -1){		// The packet is from a known source.
				OUTPUT_PORT_SWITCH_1_go = matrix.getOutputPortSwitch1(IPNumSrc_go, Integer.parseInt(arrayWords[5]));
				OUTPUT_PORT_SWITCH_2_go = matrix.getOutputPortSwitch2(IPNumSrc_go, IPNumDst_go, Integer.parseInt(arrayWords[5]));
				OUTPUT_PORT_SWITCH_3_go = matrix.getOutputPortSwitch3(IPNumSrc_go, Integer.parseInt(arrayWords[5]), 0);
				
				// Using the symmetric property of the 4 firsts ports of our Switches, we will use the same matrix to get the ports of the come back way of the packet.
				OUTPUT_PORT_SWITCH_2_return = matrix.getOutputPortSwitch1(IPNumSrc_go, Integer.parseInt(arrayWords[5]));
				OUTPUT_PORT_SWITCH_1_return = matrix.getOutputPortSwitch2(IPNumSrc_return, IPNumDst_return, Integer.parseInt(arrayWords[5]));
				OUTPUT_PORT_SWITCH_3_return = matrix.getOutputPortSwitch3(IPNumSrc_return, Integer.parseInt(arrayWords[5]), 1);
	
			} else {					// The packet is from an unknown source.
				OUTPUT_PORT_SWITCH_1_go = -2;
			}
		}
		
		int[] OUTPUT_PORTS = new int[]{OUTPUT_PORT_SWITCH_1_go, OUTPUT_PORT_SWITCH_2_go, OUTPUT_PORT_SWITCH_3_go ,
				OUTPUT_PORT_SWITCH_1_return, OUTPUT_PORT_SWITCH_2_return, OUTPUT_PORT_SWITCH_3_return};
		
		return OUTPUT_PORTS;
	}
	

	
	
	
	// Method that separate the chain in the strings with 'usually' the next format--> @IPs:Ps:@IPd:Pd:Protocol:NumProtocol:Protocol
	private void setArrayWords(String flowSymbol){
		arrayWords = flowSymbol.split(":");
		lenght = arrayWords.length;
	}
	
	
	// Method to decode the @IPs of the chain sent by the DPI
	public int decodeSourceIP(String IPs){
		
		if (IPs.equals("10.0.0.1"))
			return 0;
		else if (IPs.equals("10.0.0.2"))
			return 1;
		else
			return -1;
	}
	
	
	// Method to decode the @IPd of the chain sent by the DPI
	public int decodeDestinationIP(String IPs){
		
		if (IPs.equals("10.0.0.3"))
			return 0;
		else if (IPs.equals("10.0.0.4"))
			return 1;
		else
			return -1;
	}
	
	
	// Getter of the @IPs of the chain sent by the DPI; Returns the @IPs
	public String getNwSrc(){
		if (lenght > 7) {
			return "-1";
		} else{
			return arrayWords[0];
		}
	}
	
	
	// Getter of the @IPd of the chain sent by the DPI; Returns the @IPd
	public String getNwDst(){
		if (lenght > 7) {
			return "-1";
		} else{
			return arrayWords[2];
		}
	}
	
	
	// Getter of the Name of the chain sent by the DPI; Returns the Name
	public String getName(){
		return arrayWords[lenght-1];
	}
}
