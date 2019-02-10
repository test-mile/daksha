package com.testmile.daksha.core.guiauto.namestore;

public class StringNVPair {
	private String name;
	private String value;
	
	public StringNVPair(String name, String value){
		this.setName(name);
		this.setValue(value);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name.toUpperCase();
	}

	public String getValue() {
		return value;
	}

	private void setValue(String value) {
		this.value = value;
	}

}
