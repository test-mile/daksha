package com.testmile.daksha.core.value;

import java.util.List;

import com.testmile.daksha.tpi.batteries.container.Value;
import com.testmile.daksha.tpi.enums.ValueType;
import com.testmile.daksha.tpi.exceptions.UnsupportedRepresentationException;

public class AbstractValue implements Value {
	private Object object = null;
	private ValueType type = ValueType.NULL;

	public AbstractValue(ValueType type, Object object) {
		this.setValueType(type);
		this.setObject(object);
	}

	
	public ValueType valueType() {
		return type;
	}

	private void setValueType(ValueType type) {
		this.type = type;
	}

	
	public Object object() {
		return object;
	}

	protected void setObject(Object value) {
		this.object = value;
	}

	
	public Value clone() {
		return null;
	}

	
	public boolean isNull() {
		return this.object == null;
	}
	
	
	public boolean isNotSet() {
		return false;
	}

	
	public String asString() {
		return this.object().toString();
	}

	
	public String toString() {
		return this.asString();
	}
	
	protected void throwUnsupportedException(ValueType targetType, String callingMethodName) throws Exception{
		throw new UnsupportedRepresentationException(this.valueType().toString(), callingMethodName, this.toString(), targetType.toString());
	}
	
	protected void throwUnsupportedForEnumException(ValueType targetType, String enumClassName, String callingMethodName) throws Exception{
		throw new UnsupportedRepresentationException(this.valueType().toString(), callingMethodName, this.toString(), targetType.toString() + " of enum type " + enumClassName);
	}
	
	protected void throwUnsupportedEnumForEnumException(String sourceEnumClassName, ValueType targetType, String enumClassName, String callingMethodName) throws Exception{
		throw new UnsupportedRepresentationException(this.valueType().toString() + "of enum type " + sourceEnumClassName, callingMethodName, this.toString(), targetType.toString() + " of enum type " + enumClassName);
	}
	
	
	public boolean asBoolean() throws Exception {
		throwUnsupportedException(ValueType.BOOLEAN, "asBoolean");
		return false;
	}

	
	public Number asNumber() throws Exception {
		throwUnsupportedException(ValueType.NUMBER, "asNumber");
		return null;
	}

	
	public int asInt() throws Exception {
		throwUnsupportedException(ValueType.INTEGER, "asInt");
		return 0;
	}

	
	public long asLong() throws Exception {
		throwUnsupportedException(ValueType.LONG, "asLong");
		return 0;
	}

	
	public double asDouble() throws Exception {
		throwUnsupportedException(ValueType.DOUBLE, "asDouble");
		return 0.0;
	}

	
	public float asFloat() throws Exception {
		throwUnsupportedException(ValueType.FLOAT, "asFloat");
		return 0.0f;
	}

	
	public <T extends Enum<T>> T asEnum(Class<T> enumClass) throws Exception {
		throwUnsupportedException(ValueType.ENUM, "asEnum");
		return null;
	}

	
	public <T extends Enum<T>> List<T> asEnumList(Class<T> klass) throws Exception {
		throwUnsupportedException(ValueType.ENUM_LIST, "asEnumList");
		return null;
	}

	
	public List<Number> asNumberList() throws Exception {
		throwUnsupportedException(ValueType.NUMBER_LIST, "asNumberList");
		return null;
	}

	
	public List<Integer> asIntList() throws Exception {
		throwUnsupportedException(ValueType.INT_LIST, "asIntList");
		return null;
	}

	
	public List<String> asStringList() throws Exception {
		throwUnsupportedException(ValueType.STRING_LIST, "asStringList");
		return null;
	}

	public List<?> asList() throws Exception {
		throwUnsupportedException(ValueType.LIST, "asList");
		return null;
	}
}
