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

package daksha.ex.ddauto.basic;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import daksha.core.ddauto.DataRecord;
import daksha.tpi.ddauto.DataRecordContainer;
import daksha.tpi.ddauto.DataSource;
import daksha.tpi.ddauto.DataSourceFactory;
import daksha.tpi.ddauto.ListDataRecordContainer;
import daksha.tpi.ddauto.MapDataRecordContainer;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.testng.TestNGBaseTest;

public class MapDataRecordContainerExample{
	
	private static DataSource createMapDataSource() throws Exception {
		DataRecordContainer container = new MapDataRecordContainer();
		// Create headers and assign to container
		String[] names = {"left", "right", "sum"};
		container.setHeaders(names);
		
		// Adding single record
		Object[] record = {1,2,3};	
		container.add(record);
		
		// Adding multiple records
		Object[][] records = {
				{4,5,9},
				{7,8,12},
		};
		container.addAll(records);
		return container;
	}
	
	private static void printMapDataRecord(DataRecord record) throws Exception {
		System.out.println(record.value("left").asInt());
		System.out.println(record.value("right").asInt());
		System.out.println(record.value("sum").asInt());	
	}
	
	public static void main(String[] args) throws Exception {
		DataSource container1 =  createMapDataSource();
		
		// List of MapDataRecord
		List<DataRecord> recordList = container1.allMapRecords();
		System.out.println("Printing using a list");
		for(DataRecord mapRecord1: recordList) {
			printMapDataRecord(mapRecord1);
		}
		
		DataSource container2 =  createMapDataSource();
		// Iterator of MapDataRecord: Created for TestNG compatibility
		Iterator<Object[]> recordIter = container2.iterMapRecords();
		System.out.println("Printing using an iterator");
		while(recordIter.hasNext()) {
			Object[] recordArray = recordIter.next();
			DataRecord mapRecord2 = (DataRecord) recordArray[0];
			printMapDataRecord(mapRecord2);
		}
	}
}

