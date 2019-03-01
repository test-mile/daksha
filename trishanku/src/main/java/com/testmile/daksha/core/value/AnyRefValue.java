package com.testmile.daksha.core.value;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rits.cloning.Cloner;

import com.testmile.daksha.tpi.batteries.container.Value;
import com.testmile.daksha.tpi.exceptions.UnsupportedRepresentationException;
import com.testmile.trishanku.tpi.enums.ValueType;

public class AnyRefValue implements Value {
	private String object = null;
	private static Set<String> trues = new HashSet<String>(Arrays.asList("YES", "TRUE", "ON", "1"));
	private static Set<String> falses = new HashSet<String>(Arrays.asList("NO", "FALSE", "OFF", "0"));
	
	public AnyRefValue(Object object) {
		this.object = object.toString();
	}

	protected void throwUnsupportedException(ValueType targetType, String callingMethodName) throws Exception{
		throw new UnsupportedRepresentationException(this.valueType().toString(), callingMethodName, this.toString(), targetType.toString());
	}
	
	protected void throwUnsupportedForEnumException(ValueType targetType, String enumClassName, String callingMethodName) throws Exception{
		throw new UnsupportedRepresentationException(this.valueType().toString(), callingMethodName, this.toString(), targetType.toString() + " of enum type " + enumClassName);
	}
	

	/* (non-Javadoc)
	 * @see com.testmile.daksha.core.value.Value#asString()
	 */
	@Override
	public String asString() {
		if (this.object() != null) {
			return this.object().toString();
		} else {
			return "null";
		}
	}
	
	public static boolean isSet(String value) {
		return !value.toUpperCase().trim().equals("NOT_SET");
	}
	

	/* (non-Javadoc)
	 * @see com.testmile.daksha.core.value.Value#isNotSet()
	 */
	@Override
	public boolean isNotSet() {
		return this.asString().toUpperCase().trim().equals("NOT_SET");
	}
	

	/* (non-Javadoc)
	 * @see com.testmile.daksha.core.value.Value#isNull()
	 */
	@Override
	public boolean isNull() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.testmile.daksha.core.value.Value#isNA()
	 */
	@Override
	public boolean isNA() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.testmile.daksha.core.value.Value#process(java.lang.Object, java.lang.reflect.Method)
	 */
	@Override
	public void process(Object formatterObject, Method formatter) throws Exception {
		this.setObject((String) formatter.invoke(formatterObject, this.asString()));
	}


	/* (non-Javadoc)
	 * @see com.testmile.daksha.core.value.Value#asEnum(java.lang.Class)
	 */
	@Override
	public <T2 extends Enum<T2>> T2 asEnum(Class<T2> enumClass) throws Exception {
		try {
			return Enum.valueOf(enumClass, this.asString().toUpperCase());
		} catch (Exception e) {
			this.throwUnsupportedForEnumException(ValueType.ENUM, enumClass.getSimpleName(), "asEnum");
			return null;
		}
	}
	

	/* (non-Javadoc)
	 * @see com.testmile.daksha.core.value.Value#asBoolean()
	 */
	@Override
	public boolean asBoolean() throws Exception {
		String uStr = this.asString().toUpperCase().trim();
		if (trues.contains(uStr)){
			return true;
		} else if (falses.contains(uStr)){
			return false;
		}
		throw new Exception(String.format(">>%s<< can not be represented as a boolean.", this.asString()));
	}


	/* (non-Javadoc)
	 * @see com.testmile.daksha.core.value.Value#asNumber()
	 */
	@Override
	public Number asNumber() throws Exception {
		try{
			if (this.asString().matches("(\\-)?[0-9\\.]+")){
				try{
					return this.asFloat();
				} catch (Exception e){
					return this.asDouble();
				}
			} else if (this.asString().matches("(\\-)?[0-9]+")){
				try{
					return this.asInt();
				} catch (Exception e){
					return this.asLong();
				}				
			}
		} catch (Exception e){
			// do nothing. The subsequent exception is alright.
		}
		throw new Exception(String.format("Not supported for %s", this.getClass().getSimpleName()));
	}


	/* (non-Javadoc)
	 * @see com.testmile.daksha.core.value.Value#asInt()
	 */
	@Override
	public int asInt() throws Exception {
		try{
			return Integer.valueOf(this.asString());
		} catch (Exception e){
			throw new Exception(String.format(">>%s<< can not be represented as an integer.", this.asString()));
		}
	}


	/* (non-Javadoc)
	 * @see com.testmile.daksha.core.value.Value#asLong()
	 */
	@Override
	public long asLong() throws Exception {
		try{
			return Long.valueOf(this.asString());
		} catch (Exception e){
			throw new Exception(String.format(">>%s<< can not be represented as a long int.", this.asString()));
		}
	}


	/* (non-Javadoc)
	 * @see com.testmile.daksha.core.value.Value#asDouble()
	 */
	@Override
	public double asDouble() throws Exception {
		try{
			return Double.valueOf(this.asString());
		} catch (Exception e){
			throw new Exception(String.format(">>%s<< can not be represented as a double.", this.asString()));
		}
	}


	/* (non-Javadoc)
	 * @see com.testmile.daksha.core.value.Value#asFloat()
	 */
	@Override
	public float asFloat() throws Exception {
		try{
			return Float.valueOf(this.asString());
		} catch (Exception e){
			throw new Exception(String.format(">>%s<< can not be represented as a float.", this.asString()));
		}
	}


	/* (non-Javadoc)
	 * @see com.testmile.daksha.core.value.Value#asEnumList(java.lang.Class)
	 */
	@Override
	public <T extends Enum<T>> List<T> asEnumList(Class<T> klass) throws Exception {
		try{
			return Arrays.asList(this.asEnum(klass));
		} catch (Exception e){
			throw new Exception(String.format(">>%s<< can not be represented as a list of enum of type %s.", this.asString(), klass.getName()));
		}
	}


	/* (non-Javadoc)
	 * @see com.testmile.daksha.core.value.Value#asNumberList()
	 */
	@Override
	public List<Number> asNumberList() throws Exception {
		try{
			return Arrays.asList(this.asNumber());
		} catch (Exception e){
			throw new Exception(String.format(">>%s<< can not be represented as a list of numbers.", this.asString()));
		}
	}


	/* (non-Javadoc)
	 * @see com.testmile.daksha.core.value.Value#asIntList()
	 */
	@Override
	public List<Integer> asIntList() throws Exception {
		try{
			return Arrays.asList(this.asInt());
		} catch (Exception e){
			throw new Exception(String.format(">>%s<< can not be represented as a list of integers.", this.asString()));
		}
	}


	/* (non-Javadoc)
	 * @see com.testmile.daksha.core.value.Value#asStringList()
	 */
	@Override
	public List<String> asStringList() throws Exception {
		try{
			return Arrays.asList(this.asString());
		} catch (Exception e){
			throw new Exception(String.format(">>%s<< can not be represented as a list of strings.", this.asString()));
		}
	}

	/* (non-Javadoc)
	 * @see com.testmile.daksha.core.value.Value#asList()
	 */
	@Override
	public List<?> asList() throws Exception {
		return this.asStringList();
	}
}
