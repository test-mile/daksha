package com.testmile.daksha.core.batteries.container;

import com.testmile.daksha.tpi.enums.ValueType;

public abstract class EnumKeyValueContainer<T extends Enum<T>> extends BaseValueContainer<T> {
	
	@Override
	public ValueType valueType(String strKey) {
		return ValueType.STRING;
	}

	@Override
	public Class valueEnumType(String strKey) {
		return null;
	}

	public T formatKey(T k) {
		return k;
	}
}
