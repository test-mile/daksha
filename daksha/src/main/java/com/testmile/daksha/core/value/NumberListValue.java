package com.testmile.daksha.core.value;

import java.util.List;

import com.testmile.daksha.tpi.batteries.container.Value;
import com.testmile.daksha.tpi.enums.ValueType;

public class NumberListValue<T extends Number> extends AbstractValue {

	public NumberListValue(List<T> listObject) {
		super(ValueType.NUMBER_LIST, listObject);
	}

	@Override
	public Value clone() {
		return new NumberListValue<T>(getRawList());
	}

	@SuppressWarnings("unchecked")
	private List<T> getRawList() {
		return (List<T>) this.object();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Number> asNumberList() {
		return (List<Number>) this.object();
	}

}
