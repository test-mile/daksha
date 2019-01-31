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

import java.util.Iterator;
import java.util.List;

import com.testmile.daksha.core.ddauto.DataRecord;
import com.testmile.daksha.tpi.ddauto.DataRecordContainer;
import com.testmile.daksha.tpi.ddauto.DataSource;
import com.testmile.daksha.tpi.ddauto.ListDataRecordContainer;

public class ListDataRecordContainerExample{
	
	private static DataSource createListDataSource() throws Exception {
		DataRecordContainer container = new ListDataRecordContainer();

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
	
	private static void printListDataRecord(DataRecord record) throws Exception {
		System.out.println(record.valueAt(0).asInt());
		System.out.println(record.valueAt(1).asInt());
		System.out.println(record.valueAt(2).asInt());		
	}
	
	public static void main(String[] args) throws Exception {
		DataSource container1 = createListDataSource();
		// List of ListDataRecord
		List<DataRecord> recordList = container1.allListRecords();
		System.out.println("Printing using a list");
		for(DataRecord listDataRec1: recordList) {
			printListDataRecord(listDataRec1);
		}
		
		DataSource container2 = createListDataSource();
		// Iterator of ListDataRecord: Created for TestNG compatibility
		Iterator<Object[]> recordIter = container2.iterListRecords();
		System.out.println("Printing using an iterator");
		while(recordIter.hasNext()) {
			Object[] recordArray = recordIter.next();
			DataRecord listDataRec2 = (DataRecord) recordArray[0];
			printListDataRecord(listDataRec2);
		}
	}
}

