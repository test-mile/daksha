package com.testmile.daksha.tpi.batteries.container;

import java.util.List;

import com.testmile.daksha.tpi.enums.ValueType;

public interface Value extends Cloneable {
	
	ValueType valueType();

	Object object();

	boolean isNull();

	boolean asBoolean() throws Exception;

	Number asNumber() throws Exception;

	int asInt() throws Exception;

	long asLong() throws Exception;

	double asDouble() throws Exception;

	String asString() throws Exception;

	<T extends Enum<T>> T asEnum(Class<T> enumClass) throws Exception;

	<T extends Enum<T>> List<T> asEnumList(Class<T> klass) throws Exception;

	List<Number> asNumberList() throws Exception;

	List<Integer> asIntList() throws Exception;

	List<String> asStringList() throws Exception;

	public List<?> asList() throws Exception;

	float asFloat() throws Exception;

	Value clone();

	boolean isNotSet();

}