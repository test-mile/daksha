package com.testmile.setu.requester.databroker;

import java.util.List;

import com.testmile.daksha.tpi.ddauto.ListDataRecord;
import com.testmile.trishanku.tpi.value.AbstractValueList;

class DefaultListDataRecord extends AbstractValueList implements ListDataRecord{
	
	public DefaultListDataRecord(Object[] values) {
		super(values);
	}

	public DefaultListDataRecord(List<Object> values) {
		super(values);
	}
}