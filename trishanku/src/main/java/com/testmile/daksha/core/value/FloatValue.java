package com.testmile.daksha.core.value;

import com.testmile.daksha.tpi.batteries.container.Value;
import com.testmile.daksha.tpi.enums.ValueType;

public class FloatValue extends NumberValue {

	public FloatValue(Float number) {
		super(ValueType.FLOAT, number);
	}

	@Override
	public Value clone() {
		return new FloatValue(this.getRawObject());
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private float getRawObject() {
		return (Float) this.object();
	}

	@Override
	public float asFloat() {
		return (Float) this.object();
	}
}
