package com.testmile.setu.requester.databroker;

import java.util.Iterator;

public interface DataSource<T> {	
	Iterator<Object[]> iterRecordsForTestNG() throws Exception;
	Iterator<T> iterRecords() throws Exception;	
}
