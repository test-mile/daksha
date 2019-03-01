package com.testmile.trishanku.tpi.value;

public class StringKeyValueMap extends AbstractValueMap<String>{
	
	protected String formatKey(String key) {
		return key.toLowerCase().trim();
	}

	protected String formatKeyAsStr(String key) {
		return key;
	}
}
