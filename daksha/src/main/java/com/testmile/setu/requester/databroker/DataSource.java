package com.testmile.setu.requester.databroker;

import java.util.Iterator;

public interface DataSource<T> {	
	void reset() throws Exception;
	Iterator<Object[]> iterRecordsForTestNG() throws Exception;
	Iterator<T> iterRecords() throws Exception;	
}
