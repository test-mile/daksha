/*******************************************************************************
 * Copyright 2015-18 Test Mile Software Testing Pvt Ltd
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

package com.testmile.daksha.core.ddauto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.testmile.daksha.tpi.ddauto.DataSource;
import com.testmile.daksha.tpi.ddauto.DataSourceFinishedException;
import com.testmile.daksha.tpi.ddauto.ListDataRecord;
import com.testmile.daksha.tpi.ddauto.MapDataRecord;

public abstract class BaseDataSource implements DataSource {
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
	
	@Override
	public void validate() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Iterator<Object[]> iterListRecords() throws Exception {
		return new ListDataRecordIterator(this);
	}

	@Override
	public Iterator<Object[]> iterMapRecords() throws Exception {
		return new MapDataRecordIterator(this);
	}
	
	protected DataRecord nextListRecord() throws DataSourceFinishedException, Exception{
		throw new Exception("This data source does not support list record representation.");
	}
	
	protected DataRecord nextMapRecord() throws DataSourceFinishedException, Exception{
		throw new Exception("This data source does not support map record representation.");
	}
	
	public synchronized List<DataRecord> allListRecords(){
		List<DataRecord> all = new ArrayList<DataRecord>();
		while(true) {
			try {
				all.add(this.nextListRecord());
			} catch (Exception e) {
				break;
			}
		}
		return all;
	}
	
	public synchronized List<DataRecord> allMapRecords(){
		List<DataRecord> all = new ArrayList<DataRecord>();
		while(true) {
			try {
				DataRecord r = this.nextMapRecord();
				all.add(r);
			} catch (Exception e) {
				break;
			}
		}
		return all;
	}
	
}
