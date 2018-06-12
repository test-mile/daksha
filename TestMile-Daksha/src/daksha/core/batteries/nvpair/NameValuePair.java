package daksha.core.batteries.nvpair;

import daksha.tpi.batteries.interfaces.Value;
import daksha.tpi.enums.ValueType;

public interface NameValuePair extends Cloneable {
	String name() throws Exception;

	NameValuePair clone();

	Value value();

	ValueType valueType();
}