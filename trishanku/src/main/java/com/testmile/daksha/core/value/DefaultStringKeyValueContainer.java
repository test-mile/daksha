package com.testmile.daksha.core.value;

import com.testmile.daksha.core.batteries.container.BaseValueContainer;
import com.testmile.daksha.core.batteries.container.ValueContainer;
import com.testmile.daksha.tpi.batteries.console.Console;
import com.testmile.daksha.tpi.batteries.container.StringKeyValueContainer;
import com.testmile.daksha.tpi.enums.ValueType;

public class DefaultStringKeyValueContainer extends BaseValueContainer<String> implements ValueContainer<String>, StringKeyValueContainer{

	@Override
	public ValueType valueType(String strKey) {
		return ValueType.STRING;
	}

	@Override
	public Class valueEnumType(String strKey) {
		return null;
	}

	@Override
	public String key(String strKey) {
		return strKey.toUpperCase();
	}

	public String formatKey(String k) {
		return key(k);
	}
	//
	// public void add(String propName, String propValue) {
	// this.add(this.key(propName), new StringValue(propValue));
	// }

	public DefaultStringKeyValueContainer clone() {
		DefaultStringKeyValueContainer map = new DefaultStringKeyValueContainer();
		try {
			map.cloneAdd(this.items());
		} catch (Exception e) {
			Console.displayExceptionBlock(e);
		}
		return map;
	}

}
