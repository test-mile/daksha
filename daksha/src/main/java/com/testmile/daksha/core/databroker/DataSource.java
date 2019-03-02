package com.testmile.daksha.core.databroker;

import java.util.Iterator;

public interface DataSource<T> {

	String getName();
	
	void setName(String name);

	void terminate();
	
	public boolean isTerminated();
	
	Iterator<Object[]> iterRecordsForTestNG() throws Exception;
	Iterator<T> iterRecords() throws Exception;	
}
