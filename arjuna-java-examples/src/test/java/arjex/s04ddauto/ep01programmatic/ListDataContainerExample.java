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

package arjex.s04ddauto.ep01programmatic;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import arjuna.tpi.Arjuna;
import arjuna.tpi.ddauto.ListDataRecord;
import arjuna.tpi.ddauto.ListDataSource;

public class ListDataContainerExample{
	
	@DataProvider(name="dp")
	public Iterator<Object[]> linkDataSource() throws Exception {
		ListDataSource source = 
				Arjuna
				.createDataSourceBuilder()
				.listDataContainer()
				.record(1,2,3)
				.record(4,5,9)
				.record(7,8,12)
				.build();
		return source.iterRecordsForTestNG();
	}
	
	@Test(dataProvider="dp")
	public void repeat(ListDataRecord record) throws Exception {
		int left = record.value(0).asInt();
		int right = record.value(1).asInt();
		int expectedSum = record.value(2).asInt();
		assertEquals(expectedSum, left+right);
	}
}

