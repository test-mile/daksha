package com.testmile.setu.requester;

import com.testmile.daksha.tpi.guiauto.With;

public class SetuArg{
	private String name;
	private Object obj;
	
	private SetuArg(String name, Object obj) {
		this.name = name;
		this.obj = obj;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Object getObject() {
		return this.obj;
	}
	
	public static SetuArg arg(String name, Object obj) {
		return new SetuArg(name, obj);
	}
	
	public static SetuArg textArg(Object obj) {
		return new SetuArg("text", obj);
	}

	public static SetuArg valueArg(String value) {
		return new SetuArg("value", value);
	}
	
	public static SetuArg indexArg(int index) {
		return new SetuArg("index", index);
	}
	
	public static SetuArg configArg(String id) {
		return new SetuArg("configSetuId", id);
	}
	
	public static SetuArg[] with(With withType, String withValue) {
		return new SetuArg[] {SetuArg.arg("withType", withType), SetuArg.arg("withValue", withValue)};
	}
}