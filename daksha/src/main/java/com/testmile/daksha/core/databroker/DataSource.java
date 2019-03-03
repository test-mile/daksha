package com.testmile.daksha.core.databroker;

import java.util.Iterator;

public interface DataSource<T> {	
	Iterator<Object[]> iterRecordsForTestNG() throws Exception;
	Iterator<T> iterRecords() throws Exception;	
}
