package daksha.core.value;

import daksha.core.batteries.container.BaseValueContainer;
import daksha.core.batteries.container.ValueContainer;
import daksha.tpi.batteries.console.Console;
import daksha.tpi.batteries.interfaces.StringKeyValueContainer;
import daksha.tpi.enums.ValueType;

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
