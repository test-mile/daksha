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
package com.testmile.daksha.tpi.ddauto;

import java.util.List;
import java.util.Map;

import com.testmile.daksha.core.batteries.container.ReadOnlyContainer;
import com.testmile.daksha.core.ddauto.BaseDataRecord;
import com.testmile.daksha.core.ddauto.DataRecord;
import com.testmile.daksha.tpi.batteries.container.Value;

public class ListDataRecord extends BaseDataRecord implements ReadOnlyContainer<String, Value>, DataRecord {
	
	public ListDataRecord() {

	}
	
	public ListDataRecord(Object[] values) {
		this.add(values);
	}

	public ListDataRecord(List<Object> values) {
		this.add(values.toArray(new Object[] {}));
	}

	public void add(Object[] record) {
		for (Object obj : record) {
			this.addWithoutKey(obj);
		}
	}

	public Map<String, Value> items(List<String> filterKeys) throws Exception{
		throw new Exception("items() method is not supported for List Data Record.");			
	}

	public Map<String, String> strItems(List<String> filterKeys) throws Exception{
		throw new Exception("strItems() method is not supported for List Data Record.");			
	}

	public List<Value> values(List<String> keys) throws Exception{
		throw new Exception("values() method is not supported for List Data Record.");			
	}

	public List<String> strings(List<String> keys) throws Exception{
		throw new Exception("strings() method is not supported for List Data Record.");			
	}

	public boolean hasKey(String key) throws Exception{
		throw new Exception("hasKey() method is not supported for List Data Record.");			
	}
	
	
	@Override
	protected Value getValueForNonExistentKey(String key) throws Exception {
		if (maxIndex() == -1){
			throw new EmptyListDataRecordLookUpException(key);			
		} else {
			throw new ListDataRecordLookUpException(key, maxIndex());			
		}
	}

	@Override
	protected String getStrValueForNonExistentKey(String key) throws Exception {
		if (maxIndex() == -1){
			throw new EmptyListDataRecordLookUpException(key);			
		} else {
			throw new ListDataRecordLookUpException(key, maxIndex());			
		}
		
	}
}
