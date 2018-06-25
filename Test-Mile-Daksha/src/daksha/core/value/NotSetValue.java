package daksha.core.value;

import daksha.tpi.batteries.container.Value;
import daksha.tpi.enums.ValueType;

public class NotSetValue extends AbstractValue {
	private static String val = "NOT_SET";

	public NotSetValue() {
		super(ValueType.NOT_SET, val);
	}

	@Override
	public Value clone() {
		return this;
	}

	@Override
	public String asString() {
		return val;
	}

	@Override
	public boolean isNull() {
		return true;
	}

}
