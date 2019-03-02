/*******************************************************************************
 * Copyright 2015-19 Test Mile Software Testing Pvt Ltd
 * 
 * Website: www.TestMile.com
 * Email: support [at] testmile.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.testmile.daksha.core.databroker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class BaseDataSource<T> implements DataSource<T> {
	private List<T> records = new ArrayList<T>();
	private String name = null;
	private boolean ended = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void terminate(){
		this.ended = true;
	}
	
	@Override
	public boolean isTerminated(){
		return this.ended;
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
		return new DataRecordIteratorForTestNG<T>(this.allRecords());
	}
	
	@Override
	public Iterator<T> iterRecords() throws Exception {
		return this.records.iterator();
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
