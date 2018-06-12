package daksha.core.value;

import daksha.tpi.batteries.interfaces.Value;
import daksha.tpi.enums.ValueType;

public class IntValue extends NumberValue {

	public IntValue(Integer number) {
		super(ValueType.INTEGER, number);
	}

	@Override
	public Value clone() {
		return new IntValue(this.getRawObject());
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private int getRawObject() {
		return (Integer) this.object();
	}

	@Override
	public int asInt() {
		return (Integer) this.object();
	}
}
