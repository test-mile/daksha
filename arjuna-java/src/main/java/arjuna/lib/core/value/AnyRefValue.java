package arjuna.lib.core.value;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import arjuna.lib.exceptions.UnsupportedRepresentationException;
import arjuna.tpi.value.Value;

public class AnyRefValue implements Value {
	private Object object = null;
	private String strObject = null;
	private static Set<String> trues = new HashSet<String>(Arrays.asList("YES", "TRUE", "ON", "1"));
	private static Set<String> falses = new HashSet<String>(Arrays.asList("NO", "FALSE", "OFF", "0"));
	
	public AnyRefValue(Object object) {
		this.object = object;
		if (isNull()) {
			this.strObject = "null";
		} else {
			this.strObject = object.toString().trim();
		}
	}
	
	private void throwWrongReprException(String valueType) throws Exception{
		throw new UnsupportedRepresentationException(this.toString(), valueType);
	}
	
	public Object object() {
		return this.object;
	}
	
	@Override
	public String toString() {
		return this.strObject;
	}
	
	@Override
	public String asString() {
		return this.strObject;
	}
	
	public static boolean isSet(String value) {
		return !value.toUpperCase().trim().equals("NOT_SET");
	}
	
	@Override
	public boolean isNotSet() {
		return this.asString().toUpperCase().equals("NOT_SET");
	}

	@Override
	public boolean isNull() {
		return this.object() == null;
	}

	@Override
	public boolean isNA() {
		return this.asString().toUpperCase().equals("NA");
	}

	@Override
	public <T2 extends Enum<T2>> T2 asEnum(Class<T2> enumClass) throws Exception {
		try {
			return Enum.valueOf(enumClass, this.asString().toUpperCase());
		} catch (Exception e) {
			this.throwWrongReprException("enum constant of type " + enumClass.getSimpleName());
			return null;
		}
	}
	
	@Override
	public boolean asBoolean() throws Exception {
		String uStr = this.asString().toUpperCase().trim();
		if (trues.contains(uStr)){
			return true;
		} else if (falses.contains(uStr)){
			return false;
		}
		throwWrongReprException("boolean");
		return false;
	}

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
			throwWrongReprException("number");
		}
		
		return null;
	}

	@Override
	public int asInt() throws Exception {
		try{
			return Integer.valueOf(this.asString());
		} catch (Exception e){
			try {
				return Double.valueOf(this.asString()).intValue();
			} catch (Exception f) {
				throwWrongReprException("int");
			}
		}
		
		return 0;
	}

	@Override
	public long asLong() throws Exception {
		try{
			return Long.valueOf(this.asString());
		} catch (Exception e){
			try {
				return Double.valueOf(this.asString()).longValue();
			} catch (Exception f) {
				throwWrongReprException("long");
			}
		}
		
		return 0L;
	}

	@Override
	public double asDouble() throws Exception {
		try{
			return Double.valueOf(this.asString());
		} catch (Exception e){
			throwWrongReprException("double");
		}
		
		return 0.0;
	}

	@Override
	public float asFloat() throws Exception {
		try{
			return Float.valueOf(this.asString());
		} catch (Exception e){
			throwWrongReprException("float");
		}
		
		return 0.0f;
	}

	@Override
	public <T extends Enum<T>> List<T> asEnumList(Class<T> klass) throws Exception {
		try{
			return Arrays.asList(this.asEnum(klass));
		} catch (Exception e){
			this.throwWrongReprException("enum constant list of type " + klass.getSimpleName());
			return null;
		}
	}

	@Override
	public List<Number> asNumberList() throws Exception {
		try{
			return Arrays.asList(this.asNumber());
		} catch (Exception e){
			this.throwWrongReprException("number list");
			return null;
		}
	}

	@Override
	public List<Integer> asIntList() throws Exception {
		try{
			return Arrays.asList(this.asInt());
		} catch (Exception e){
			this.throwWrongReprException("int list");
			return null;
		}
	}



	@Override
	public List<String> asStringList() throws Exception {
		try{
			return Arrays.asList(this.asString());
		} catch (Exception e){
			this.throwWrongReprException("string list");
			return null;
		}
	}

}
