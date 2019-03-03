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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.testmile.setu.requester.databroker.BaseDataSource;

public class MapDataRecordContainer extends BaseDataSource<MapDataRecord> implements MapDataSource {
	private String[] headers = null;
	private int refLen = -1;
	
	public MapDataRecordContainer(String[] headers) throws Exception {
		super();
		this.headers = headers;
		this.refLen = headers.length;
		validate();
	}
	
	public MapDataRecordContainer(String[] headers, Object[][] records) throws Exception {
		this(headers);
		this.addAll(records);
	}
	
	public void add(Object[] record) throws Exception {
		if (record.length != refLen){
			throw new Exception(String.format("All records must match the length of headers: %d. Current Record Length: %d. Record: [%s]", refLen, record.length, Arrays.toString(record)));					
		}
		Map<String, Object> map = new HashMap<String,Object>();
		for (int i=0; i < this.refLen; i++) {
			map.put(headers[i],record[i]);
		}
		super.addSingleRecord(new MapDataRecord(map));
	}

	private void validate() throws Exception {
		if ((this.headers == null) || (this.headers.length == 0)){
			throw new Exception("You can not use a Map Data Record container without no headers.");
		}
	}

}
