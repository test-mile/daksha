package com.testmile.daksha.core.batteries.container;

import com.testmile.daksha.tpi.enums.DakshaOption;

public class DakshaOptionContainer extends EnumKeyValueContainer<DakshaOption> {
	
	@Override
	public DakshaOption key(String strKey) {
		return DakshaOption.valueOf(strKey.toUpperCase());
	}

}
