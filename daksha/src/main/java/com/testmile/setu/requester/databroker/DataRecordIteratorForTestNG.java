package com.testmile.setu.requester.databroker;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class DataRecordIteratorForTestNG<T> implements Iterator<Object[]>{
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
