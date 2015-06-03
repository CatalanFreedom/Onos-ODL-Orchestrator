package com.northbound.flowconstructor;

public class FlowModel {

	private String name;
	private String nwSrc;
	private String nwDst;
//	private String installInHw;
//	private String priority;
//	private String etherType;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public String getNwSrc() {
		return nwSrc;
	}
	public void setNwSrc(String nwSrc) {
		this.nwSrc = nwSrc;
	}

	
	public String getNwDst() {
		return nwDst;
	}
	public void setNwDst(String nwDst) {
		this.nwDst = nwDst;
	}

	
	
}
