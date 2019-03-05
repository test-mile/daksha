package com.testmile.setu.requester.databroker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.testmile.setu.requester.BaseSetuObject;

abstract class BaseDataSource<T> extends BaseSetuObject implements DataSource<T> {
	private List<T> records = new ArrayList<T>();
	private boolean iterCreated = false;
	
	public BaseDataSource() {
		super();
	}
	
	@Override
	public void reset() throws Exception {
		this.iterCreated = false;
	}
	
	protected void addSingleRecord(T record) {
		this.records.add(record);
	}
	
	protected abstract void add(Object[] record) throws Exception;
	
	public void addAll(Object[][] records) throws Exception {
		for (Object[] record : records) {
			this.add(record);
		}
	}
	
	private List<T> allRecords(){
		return this.records;
	}
	
	@Override
	public Iterator<Object[]> iterRecordsForTestNG() throws Exception {
		if (!iterCreated) {
			this.iterCreated = true;
			return new DataRecordIteratorForTestNG<T>(this.allRecords());
		} else {
			throw new Exception("You must reset the data source before creating a new iterator.");
		}
	}
	
	@Override
	public Iterator<T> iterRecords() throws Exception {
		if (!iterCreated) {
			this.iterCreated = true;
			return this.records.iterator();
		} else {
			throw new Exception("You must reset the data source before creating a new iterator.");
		}	
	}	

}

class DataRecordIteratorForTestNG<T> implements Iterator<Object[]>{
	private List<T> records;

	public DataRecordIteratorForTestNG(List<T> records) {
		this.records = records;
	}

	@Override
	public boolean hasNext() {
		return records.size() > 0;
	}
	
	@Override
	public Object[] next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		} else {
			return new Object[] {records.remove(0)};
		}
	}
}