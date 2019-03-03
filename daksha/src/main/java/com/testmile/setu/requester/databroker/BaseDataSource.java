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

package com.testmile.setu.requester.databroker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.testmile.setu.requester.DefaultSetuObject;

public abstract class BaseDataSource<T> extends DefaultSetuObject implements DataSource<T> {
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

