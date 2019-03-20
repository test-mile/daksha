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

package arjuna.ex.ddauto.basic;

import java.util.Iterator;

import com.testmile.arjuna.Arjuna;
import com.testmile.arjuna.tpi.ddauto.MapDataRecord;
import com.testmile.arjuna.tpi.ddauto.MapDataSource;

public class ExcelFileDDTMapData{
	
	private static MapDataSource createMapDataSource() throws Exception {
		MapDataSource source = 
				Arjuna
				.createDataSourceBuilder()
				.fileMapDataSource("input.xls")
				.build();
		return source;
	}
	
	private static void printDataRecord(MapDataRecord record) throws Exception {
		System.out.println(record.value("left").asInt());
		System.out.println(record.value("right").asInt());
		System.out.println(record.value("sum").asInt());	
	}
	
	public static void main(String[] args) throws Exception {
		// Initialize Daksha
		Arjuna.init();
		
		MapDataSource source = createMapDataSource();
		
		// Using default Java Iterator
		Iterator<MapDataRecord> recordIter1 = source.iterRecords();
		System.out.println("With Java iterator.");
		while(recordIter1.hasNext()) {
			MapDataRecord record = recordIter1.next();
			printDataRecord(record);			
		}
	}
}

