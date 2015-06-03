package com.northbound.chainreader;

public class FullMatrix {

	// Matrix to return the ports to flow the packet depending on the: [Number of the Switch][@IPs][@IPd][Type of protocol].
	private int[][][][] matrix = new int[4][2][2][200];

	public FullMatrix(){
//	First Switch
//		Class A
	    matrix[0][0][0][2] = 3;			// POP3
	    matrix[0][0][0][3] = 3;			// SMTP
	    matrix[0][0][0][4] = 3;			// IMAP
	    matrix[0][0][0][65] = 3;		// IRC
	    matrix[0][0][0][119] = 3;		// Facebook
	    matrix[0][0][0][120] = 3;		// Twitter
	    matrix[0][0][0][122] = 3;		// GMail
	    matrix[0][0][0][186] = 3;		// FacebookChat
	    matrix[0][0][0][5] = 3;			// DNS
	    matrix[0][0][0][14] = 3;		// SNMP
	    matrix[0][0][0][17] = 3;		// Syslog
	    matrix[0][0][0][18] = 3;		// DHCP
	    matrix[0][0][0][81] = 3;	    // ICMP
	    matrix[0][0][0][112] = 3;		// LDAP
	    matrix[0][0][0][7] = 3;			// HTTP
	    matrix[0][0][0][70] = 3;		// Yahoo
	    matrix[0][0][0][123] = 3;		// GoogleMaps
	    matrix[0][0][0][126] = 3;		// Google
	    matrix[0][0][0][178] = 3;		// Amazon
	    matrix[0][0][0][179] = 3;		// eBay
	    matrix[0][0][0][180] = 3;		// CNN
	    matrix[0][0][0][37] = 3;		// BitTorrent
	    matrix[0][0][0][39] = 3;		// AVI
	    matrix[0][0][0][40] = 3;		// Flash
	    matrix[0][0][0][43] = 3;		// QuickTime
	    matrix[0][0][0][45] = 3;		// WindowsMwdia
	    matrix[0][0][0][100] = 3;		// SIP
	    matrix[0][0][0][121] = 3;		// DropBox
	    matrix[0][0][0][124] = 3;		// Youtube
	    matrix[0][0][0][125] = 3;		// Skype
	    matrix[0][0][0][92] = 3;		// SSH
	    matrix[0][0][0][159] = 3;		// OpenVPN
	    matrix[0][0][0][163] = 3;		// TOR
		
	    // Class B
	    matrix[0][1][0][2] = 4;			// POP3
	    matrix[0][1][0][3] = 4;			// SMTP
	    matrix[0][1][0][4] = 4;			// IMAP
	    matrix[0][1][0][65] = 4;		// IRC
	    matrix[0][1][0][119] = 4;		// Facebook
	    matrix[0][1][0][120] = 4;		// Twitter
	    matrix[0][1][0][122] = 4;		// GMail
	    matrix[0][1][0][186] = 4;		// FacebookChat
	    matrix[0][1][0][5] = 4;			// DNS
	    matrix[0][1][0][14] = 4;		// SNMP
	    matrix[0][1][0][17] = 4;		// Syslog
	    matrix[0][1][0][18] = 4;		// DHCP
	    matrix[0][1][0][81] = 4;	        // ICMP
	    matrix[0][1][0][112] = 4;		// LDAP
	    matrix[0][1][0][7] = 4;			// HTTP
	    matrix[0][1][0][70] = 4;		// Yahoo
	    matrix[0][1][0][123] = 4;		// GoogleMaps
	    matrix[0][1][0][126] = 4;		// Google
	    matrix[0][1][0][178] = 4;		// Amazon
	    matrix[0][1][0][179] = 4;		// eBay
	    matrix[0][1][0][180] = 4;		// CNN
	    matrix[0][1][0][37] = 4;		// BitTorrent
	    matrix[0][1][0][39] = 4;		// AVI
	    matrix[0][1][0][40] = 4;		// Flash
	    matrix[0][1][0][43] = 4;		// QuickTime
	    matrix[0][1][0][45] = 4;		// WindowsMwdia
	    matrix[0][1][0][100] = 4;		// SIP
	    matrix[0][1][0][121] = 4;		// DropBox
	    matrix[0][1][0][124] = 4;		// Youtube
	    matrix[0][1][0][125] = 4;		// Skype
	    matrix[0][1][0][92] = 4;		// SSH
	    matrix[0][1][0][159] = 4;		// OpenVPN
	    matrix[0][1][0][163] = 4;		// TOR
	    
	    
	   
	    
	    //----------------------------------------------------------------------------
//		Second Switch --H3-- destination
	    
//		Class A
	    matrix[1][0][0][2] = 1;			// POP3
	    matrix[1][0][0][3] = 1;			// SMTP
	    matrix[1][0][0][4] = 1;			// IMAP
	    matrix[1][0][0][65] = 1;		// IRC
	    matrix[1][0][0][119] = 1;		// Facebook
	    matrix[1][0][0][120] = 1;		// Twitter
	    matrix[1][0][0][122] = 1;		// GMail
	    matrix[1][0][0][186] = 1;		// FacebookChat
	    matrix[1][0][0][5] = 1;			// DNS
	    matrix[1][0][0][14] = 1;		// SNMP
	    matrix[1][0][0][17] = 1;		// Syslog
	    matrix[1][0][0][18] = 1;		// DHCP
	    matrix[1][0][0][81] = 1;	    // ICMP
	    matrix[1][0][0][112] = 1;		// LDAP
	    matrix[1][0][0][7] = 1;			// HTTP
	    matrix[1][0][0][70] = 1;		// Yahoo
	    matrix[1][0][0][123] = 1;		// GoogleMaps
	    matrix[1][0][0][126] = 1;		// Google
	    matrix[1][0][0][178] = 1;		// Amazon
	    matrix[1][0][0][179] = 1;		// eBay
	    matrix[1][0][0][180] = 1;		// CNN
	    matrix[1][0][0][37] = 1;		// BitTorrent
	    matrix[1][0][0][39] = 1;		// AVI
	    matrix[1][0][0][40] = 1;		// Flash
	    matrix[1][0][0][43] = 1;		// QuickTime
	    matrix[1][0][0][45] = 1;		// WindowsMwdia
	    matrix[1][0][0][100] = 1;		// SIP
	    matrix[1][0][0][121] = 1;		// DropBox
	    matrix[1][0][0][124] = 1;		// Youtube
	    matrix[1][0][0][125] = 1;		// Skype
	    matrix[1][0][0][92] = 1;		// SSH
	    matrix[1][0][0][159] = 1;		// OpenVPN
	    matrix[1][0][0][163] = 1;		// TOR
		
	    // Class B
	    matrix[1][1][0][2] = 1;			// POP3
	    matrix[1][1][0][3] = 1;			// SMTP
	    matrix[1][1][0][4] = 1;			// IMAP
	    matrix[1][1][0][65] = 1;		// IRC
	    matrix[1][1][0][119] = 1;		// Facebook
	    matrix[1][1][0][120] = 1;		// Twitter
	    matrix[1][1][0][122] = 1;		// GMail
	    matrix[1][1][0][186] = 1;		// FacebookChat
	    matrix[1][1][0][5] = 1;			// DNS
	    matrix[1][1][0][14] = 1;		// SNMP
	    matrix[1][1][0][17] = 1;		// Syslog
	    matrix[1][1][0][18] = 1;		// DHCP
	    matrix[1][1][0][81] = 1;	    // ICMP
	    matrix[1][1][0][112] = 1;		// LDAP
	    matrix[1][1][0][7] = 1;			// HTTP
	    matrix[1][1][0][70] = 1;		// Yahoo
	    matrix[1][1][0][123] = 1;		// GoogleMaps
	    matrix[1][1][0][126] = 1;		// Google
	    matrix[1][1][0][178] = 1;		// Amazon
	    matrix[1][1][0][179] = 1;		// eBay
	    matrix[1][1][0][180] = 1;		// CNN
	    matrix[1][1][0][37] = 1;		// BitTorrent
	    matrix[1][1][0][39] = 1;		// AVI
	    matrix[1][1][0][40] = 1;		// Flash
	    matrix[1][1][0][43] = 1;		// QuickTime
	    matrix[1][1][0][45] = 1;		// WindowsMwdia
	    matrix[1][1][0][100] = 1;		// SIP
	    matrix[1][1][0][121] = 1;		// DropBox
	    matrix[1][1][0][124] = 1;		// Youtube
	    matrix[1][1][0][125] = 1;		// Skype
	    matrix[1][1][0][92] = 1;		// SSH
	    matrix[1][1][0][159] = 1;		// OpenVPN
	    matrix[1][1][0][163] = 1;		// TOR
	    
	    
	  //----------------------------------------------------------------------------
//		Second Switch --H4-- destination
	    
//		Class A
	    matrix[1][0][1][2] = 2;			// POP3
	    matrix[1][0][1][3] = 2;			// SMTP
	    matrix[1][0][1][4] = 2;			// IMAP
	    matrix[1][0][1][65] = 2;		// IRC
	    matrix[1][0][1][119] = 2;		// Facebook
	    matrix[1][0][1][120] = 2;		// Twitter
	    matrix[1][0][1][122] = 2;		// GMail
	    matrix[1][0][1][186] = 2;		// FacebookChat
	    matrix[1][0][1][5] = 2;			// DNS
	    matrix[1][0][1][14] = 2;		// SNMP
	    matrix[1][0][1][17] = 2;		// Syslog
	    matrix[1][0][1][18] = 2;		// DHCP
	    matrix[1][0][1][81] = 2;	    // ICMP
	    matrix[1][0][1][112] = 2;		// LDAP
	    matrix[1][0][1][7] = 2;			// HTTP
	    matrix[1][0][1][70] = 2;		// Yahoo
	    matrix[1][0][1][123] = 2;		// GoogleMaps
	    matrix[1][0][1][126] = 2;		// Google
	    matrix[1][0][1][178] = 2;		// Amazon
	    matrix[1][0][1][179] = 2;		// eBay
	    matrix[1][0][1][180] = 2;		// CNN
	    matrix[1][0][1][37] = 2;		// BitTorrent
	    matrix[1][0][1][39] = 2;		// AVI
	    matrix[1][0][1][40] = 2;		// Flash
	    matrix[1][0][1][43] = 2;		// QuickTime
	    matrix[1][0][1][45] = 2;		// WindowsMwdia
	    matrix[1][0][1][100] = 2;		// SIP
	    matrix[1][0][1][121] = 2;		// DropBox
	    matrix[1][0][1][124] = 2;		// Youtube
	    matrix[1][0][1][125] = 2;		// Skype
	    matrix[1][0][1][92] = 2;		// SSH
	    matrix[1][0][1][159] = 2;		// OpenVPN
	    matrix[1][0][1][163] = 2;		// TOR
		
	    // Class B
	    matrix[1][1][1][2] = 2;			// POP3
	    matrix[1][1][1][3] = 2;			// SMTP
	    matrix[1][1][1][4] = 2;			// IMAP
	    matrix[1][1][1][65] = 2;		// IRC
	    matrix[1][1][1][119] = 2;		// Facebook
	    matrix[1][1][1][120] = 2;		// Twitter
	    matrix[1][1][1][122] = 2;		// GMail
	    matrix[1][1][1][186] = 2;		// FacebookChat
	    matrix[1][1][1][5] = 2;			// DNS
	    matrix[1][1][1][14] = 2;		// SNMP
	    matrix[1][1][1][17] = 2;		// Syslog
	    matrix[1][1][1][18] = 2;		// DHCP
	    matrix[1][1][1][81] = 2;	    // ICMP
	    matrix[1][1][1][112] = 2;		// LDAP
	    matrix[1][1][1][7] = 2;			// HTTP
	    matrix[1][1][1][70] = 2;		// Yahoo
	    matrix[1][1][1][123] = 2;		// GoogleMaps
	    matrix[1][1][1][126] = 2;		// Google
	    matrix[1][1][1][178] = 2;		// Amazon
	    matrix[1][1][1][179] = 2;		// eBay
	    matrix[1][1][1][180] = 2;		// CNN
	    matrix[1][1][1][37] = 2;		// BitTorrent
	    matrix[1][1][1][39] = 2;		// AVI
	    matrix[1][1][1][40] = 2;		// Flash
	    matrix[1][1][1][43] = 2;		// QuickTime
	    matrix[1][1][1][45] = 2;		// WindowsMwdia
	    matrix[1][1][1][100] = 2;		// SIP
	    matrix[1][1][1][121] = 2;		// DropBox
	    matrix[1][1][1][124] = 2;		// Youtube
	    matrix[1][1][1][125] = 2;		// Skype
	    matrix[1][1][1][92] = 2;		// SSH
	    matrix[1][1][1][159] = 2;		// OpenVPN
	    matrix[1][1][1][163] = 2;		// TOR
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    //-------------------------------------------------------------------------
//		Third Switch
//		Class A	    
	    matrix[2][0][0][2] = 2;		// POP3
	    matrix[2][0][0][3] = 2;		// SMTP
	    matrix[2][0][0][4] = 2;		// IMAP
	    matrix[2][0][0][65] = 2;		// IRC
	    matrix[2][0][0][119] = 2;		// Facebook
	    matrix[2][0][0][120] = 2;		// Twitter
	    matrix[2][0][0][122] = 2;		// GMail
	    matrix[2][0][0][186] = 2;		// FacebookChat
	    matrix[2][0][0][5] = 2;		// DNS
	    matrix[2][0][0][14] = 2;		// SNMP
	    matrix[2][0][0][17] = 2;		// Syslog
	    matrix[2][0][0][18] = 2;		// DHCP
	    matrix[2][0][0][81] = 2;	    // ICMP
	    matrix[2][0][0][112] = 2;		// LDAP
	    matrix[2][0][0][7] = 2;		// HTTP
	    matrix[2][0][0][70] = 2;		// Yahoo
	    matrix[2][0][0][123] = 2;		// GoogleMaps
	    matrix[2][0][0][126] = 2;		// Google
	    matrix[2][0][0][178] = 2;		// Amazon
	    matrix[2][0][0][179] = 2;		// eBay
	    matrix[2][0][0][180] = 2;		// CNN
	    matrix[2][0][0][37] = 2;		// BitTorrent
	    matrix[2][0][0][39] = 2;		// AVI
	    matrix[2][0][0][40] = 2;		// Flash
	    matrix[2][0][0][43] = 2;		// QuickTime
	    matrix[2][0][0][45] = 2;		// WindowsMwdia
	    matrix[2][0][0][100] = 2;		// SIP
	    matrix[2][0][0][121] = 2;		// DropBox
	    matrix[2][0][0][124] = 2;		// Youtube
	    matrix[2][0][0][125] = 2;		// Skype
	    matrix[2][0][0][92] = 2;		// SSH
	    matrix[2][0][0][159] = 2;		// OpenVPN
	    matrix[2][0][0][163] = 2;		// TOR
		
	    // Class B
	    matrix[2][1][0][2] = 2;		// POP3
	    matrix[2][1][0][3] = 2;		// SMTP
	    matrix[2][1][0][4] = 2;		// IMAP
	    matrix[2][1][0][65] = 2;		// IRC
	    matrix[2][1][0][119] = 2;		// Facebook
	    matrix[2][1][0][120] = 2;		// Twitter
	    matrix[2][1][0][122] = 2;		// GMail
	    matrix[2][1][0][186] = 2;		// FacebookChat
	    matrix[2][1][0][5] = 2;		// DNS
	    matrix[2][1][0][14] = 2;		// SNMP
	    matrix[2][1][0][17] = 2;		// Syslog
	    matrix[2][1][0][18] = 2;		// DHCP
	    matrix[2][1][0][81] = 2;	        // ICMP
	    matrix[2][1][0][112] = 2;		// LDAP
	    matrix[2][1][0][7] = 2;		// HTTP
	    matrix[2][1][0][70] = 2;		// Yahoo
	    matrix[2][1][0][123] = 2;		// GoogleMaps
	    matrix[2][1][0][126] = 2;		// Google
	    matrix[2][1][0][178] = 2;		// Amazon
	    matrix[2][1][0][179] = 2;		// eBay
	    matrix[2][1][0][180] = 2;		// CNN
	    matrix[2][1][0][37] = 2;		// BitTorrent
	    matrix[2][1][0][39] = 2;		// AVI
	    matrix[2][1][0][40] = 2;		// Flash
	    matrix[2][1][0][43] = 2;		// QuickTime
	    matrix[2][1][0][45] = 2;		// WindowsMwdia
	    matrix[2][1][0][100] = 2;		// SIP
	    matrix[2][1][0][121] = 2;		// DropBox
	    matrix[2][1][0][124] = 2;		// Youtube
	    matrix[2][1][0][125] = 2;		// Skype
	    matrix[2][1][0][92] = 2;		// SSH
	    matrix[2][1][0][159] = 2;		// OpenVPN
	    matrix[2][1][0][163] = 2;		// TOR
	    
	  //-------------------------------------------------------------------------
//		Third Switch Come Back way
//		Class A	    
	    matrix[3][0][0][2] = 1;		// POP3
	    matrix[3][0][0][3] = 1;		// SMTP
	    matrix[3][0][0][4] = 1;		// IMAP
	    matrix[3][0][0][65] = 1;		// IRC
	    matrix[3][0][0][119] = 1;		// Facebook
	    matrix[3][0][0][120] = 1;		// Twitter
	    matrix[3][0][0][122] = 1;		// GMail
	    matrix[3][0][0][186] = 1;		// FacebookChat
	    matrix[3][0][0][5] = 1;		// DNS
	    matrix[3][0][0][14] = 1;		// SNMP
	    matrix[3][0][0][17] = 1;		// Syslog
	    matrix[3][0][0][18] = 1;		// DHCP
	    matrix[3][0][0][81] = 1;	    // ICMP
	    matrix[3][0][0][112] = 1;		// LDAP
	    matrix[3][0][0][7] = 1;		// HTTP
	    matrix[3][0][0][70] = 1;		// Yahoo
	    matrix[3][0][0][123] = 1;		// GoogleMaps
	    matrix[3][0][0][126] = 1;		// Google
	    matrix[3][0][0][178] = 1;		// Amazon
	    matrix[3][0][0][179] = 1;		// eBay
	    matrix[3][0][0][180] = 1;		// CNN
	    matrix[3][0][0][37] = 1;		// BitTorrent
	    matrix[3][0][0][39] = 1;		// AVI
	    matrix[3][0][0][40] = 1;		// Flash
	    matrix[3][0][0][43] = 1;		// QuickTime
	    matrix[3][0][0][45] = 1;		// WindowsMwdia
	    matrix[3][0][0][100] = 1;		// SIP
	    matrix[3][0][0][121] = 1;		// DropBox
	    matrix[3][0][0][124] = 1;		// Youtube
	    matrix[3][0][0][125] = 1;		// Skype
	    matrix[3][0][0][92] = 1;		// SSH
	    matrix[3][0][0][159] = 1;		// OpenVPN
	    matrix[3][0][0][163] = 1;		// TOR
		
	    // Class B
	    matrix[3][1][0][2] = 1;		// POP3
	    matrix[3][1][0][3] = 1;		// SMTP
	    matrix[3][1][0][4] = 1;		// IMAP
	    matrix[3][1][0][65] = 1;		// IRC
	    matrix[3][1][0][119] = 1;		// Facebook
	    matrix[3][1][0][120] = 1;		// Twitter
	    matrix[3][1][0][122] = 1;		// GMail
	    matrix[3][1][0][186] = 1;		// FacebookChat
	    matrix[3][1][0][5] = 1;		// DNS
	    matrix[3][1][0][14] = 1;		// SNMP
	    matrix[3][1][0][17] = 1;		// Syslog
	    matrix[3][1][0][18] = 1;		// DHCP
	    matrix[3][1][0][81] = 1;	        // ICMP
	    matrix[3][1][0][112] = 1;		// LDAP
	    matrix[3][1][0][7] = 1;		// HTTP
	    matrix[3][1][0][70] = 1;		// Yahoo
	    matrix[3][1][0][123] = 1;		// GoogleMaps
	    matrix[3][1][0][126] = 1;		// Google
	    matrix[3][1][0][178] = 1;		// Amazon
	    matrix[3][1][0][179] = 1;		// eBay
	    matrix[3][1][0][180] = 1;		// CNN
	    matrix[3][1][0][37] = 1;		// BitTorrent
	    matrix[3][1][0][39] = 1;		// AVI
	    matrix[3][1][0][40] = 1;		// Flash
	    matrix[3][1][0][43] = 1;		// QuickTime
	    matrix[3][1][0][45] = 1;		// WindowsMwdia
	    matrix[3][1][0][100] = 1;		// SIP
	    matrix[3][1][0][121] = 1;		// DropBox
	    matrix[3][1][0][124] = 1;		// Youtube
	    matrix[3][1][0][125] = 1;		// Skype
	    matrix[3][1][0][92] = 1;		// SSH
	    matrix[3][1][0][159] = 1;		// OpenVPN
	    matrix[3][1][0][163] = 1;		// TOR
	    
	    
	}
	
	

	// Getter of the Output port for the first Switch. Return the Output port of the first Switch.
	public int getOutputPortSwitch1(int IPs, int ProtocolID){
		return matrix[0][IPs][0][ProtocolID];
	}
	
	
	// Getter of the Output port for the second Switch. Return the Output port of the third Switch.
	public int getOutputPortSwitch2(int IPs, int IPd, int ProtocolID){
		return matrix[1][0][IPd][ProtocolID];
	}
	
	
	// Getter of the Output port for the third Switch. Return the Output port of the second Switch.
	public int getOutputPortSwitch3(int IPs, int ProtocolID, int retorno ){
		if (retorno == 0){
			return matrix[2][IPs][0][ProtocolID];
		}
		else{
			return matrix[3][IPs][0][ProtocolID];
		}
	}
	
}
