package com.testmile.setu.requester.databroker;

import java.util.Map;

import com.testmile.daksha.tpi.ddauto.MapDataRecord;
import com.testmile.trishanku.tpi.value.StringKeyValueMap;

class DefaultMapDataRecord extends StringKeyValueMap implements MapDataRecord{
	
	public DefaultMapDataRecord(Map<String, Object> nvMap){
		super(nvMap);	
	}	


}
