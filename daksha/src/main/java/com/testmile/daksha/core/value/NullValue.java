package com.testmile.daksha.core.value;

import com.testmile.daksha.tpi.batteries.container.Value;
import com.testmile.daksha.tpi.enums.ValueType;

public class NullValue extends AbstractValue {

	public NullValue() {
		super(ValueType.NULL, null);
	}

	@Override
	public Value clone() {
		return this;
	}

	@Override
	public String asString() {
		return "null";
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
