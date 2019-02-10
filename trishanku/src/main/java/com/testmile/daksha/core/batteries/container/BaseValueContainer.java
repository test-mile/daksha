package com.testmile.daksha.core.batteries.container;

import java.util.List;
import java.util.Map;

import com.rits.cloning.Cloner;

import com.testmile.daksha.core.value.AnyRefValue;
import com.testmile.daksha.core.value.BooleanValue;
import com.testmile.daksha.core.value.EnumListValue;
import com.testmile.daksha.core.value.EnumValue;
import com.testmile.daksha.core.value.NAValue;
import com.testmile.daksha.core.value.NotSetValue;
import com.testmile.daksha.core.value.NullValue;
import com.testmile.daksha.core.value.NumberListValue;
import com.testmile.daksha.core.value.NumberValue;
import com.testmile.daksha.core.value.StringListValue;
import com.testmile.daksha.core.value.StringValue;
import com.testmile.daksha.tpi.batteries.container.Value;
import com.testmile.daksha.tpi.enums.ValueType;

public abstract class BaseValueContainer<T> extends BaseContainer<T, Value> implements ValueContainer<T> {
	public static NotSetValue notSetValue = new NotSetValue();
	public static NAValue naValue = new NAValue();

	
	protected Value getValueForNonExistentKey(T key) throws Exception {
		return notSetValue;
	}

	
	protected String getStrValueForNonExistentKey(T key) throws Exception {
		return notSetValue.asString();
	}

	public void addAsStringValue(Map<T, String> map) {
		for (T k : map.keySet()) {
			this.addAsStringValue(formatKey(k), map.get(k));
		}
	}

	public void addAsStringValue(T k, String str) {
		this.add(formatKey(k), new StringValue(str));
	}

	
	public void cloneAdd(Map<T, Value> map) {
		for (T key : map.keySet()) {
			super.add(formatKey(key), map.get(key).clone());
		}
	}

	
	public void cloneAdd(Container<T, Value> container) throws Exception {
		Map<T, Value> map = container.items();
		this.cloneAdd(map);
	}

	
	public void cloneAdd(T k, Value v) {
		super.add(formatKey(k), v.clone());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.autocognite.batteries.config.Configuration#add(java.lang.String,
	 * java.lang.Number)
	 */
	
	public void add(T k, Number value) {
		this.add(this.formatKey(k), new NumberValue(value));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.autocognite.batteries.config.Configuration#add(java.lang.String,
	 * java.lang.String)
	 */
	
	public void add(T k, String value) {
		this.add(this.formatKey(k), new StringValue(value));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.autocognite.batteries.config.Configuration#add(java.lang.String,
	 * boolean)
	 */
	
	public void add(T k, boolean value) {
		this.add(this.formatKey(k), new BooleanValue(value));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.autocognite.batteries.config.Configuration#putObject(java.lang.
	 * String, java.lang.Object)
	 */
	
	public void addObject(T k, Object value) {
		Object inVal = null;
		if (value.hashCode() == this.hashCode()){
			try {
				Cloner cloner= new Cloner();
				inVal = cloner.deepClone(value);
		    } catch (Exception f) {
		        f.printStackTrace();
		        inVal = (Object) new NullValue();
		    }
		} else {
			inVal = value;
		}
		this.add(this.formatKey(k), new AnyRefValue(inVal));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.autocognite.batteries.config.Configuration#putEnum(java.lang.String,
	 * T)
	 */
	
	public <T1 extends Enum<T1>> void add(T k, T1 value) {
		this.add(this.formatKey(k), new EnumValue<T1>(value));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.autocognite.batteries.config.Configuration#putEnumList(java.lang.
	 * String, java.util.List)
	 */
	
	public <T1 extends Enum<T1>> void addEnumList(T k, List<T1> values) {
		this.add(this.formatKey(k), new EnumListValue<T1>(values));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.autocognite.batteries.config.Configuration#putNumberList(java.lang.
	 * String, java.util.List)
	 */
	
	public <T1 extends Number> void addNumberList(T k, List<T1> values) {
		this.add(this.formatKey(k), new NumberListValue<T1>(values));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.autocognite.batteries.config.Configuration#putStringList(java.lang.
	 * String, java.util.List)
	 */
	
	public void addStringList(T k, List<String> values) {
		this.add(this.formatKey(k), new StringListValue(values));
	}

	public abstract ValueType valueType(String strKey);

	public abstract Class valueEnumType(String strKey);

	public abstract T key(String strKey);
}
