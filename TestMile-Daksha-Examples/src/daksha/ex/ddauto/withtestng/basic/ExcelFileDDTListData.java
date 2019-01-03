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

package daksha.ex.ddauto.withtestng.basic;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import daksha.core.ddauto.DataRecord;
import daksha.tpi.ddauto.DataSourceFactory;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.testng.TestNGBaseTest;

public class ExcelFileDDTListData extends TestNGBaseTest {
	
	@DataProvider(name="dp")
	public Iterator<Object[]> linkDataSource() throws Exception {
		String fPath = this.getContext().getConfig().value(DakshaOption.DATA_SOURCES_DIR) + "input.xls";
		return DataSourceFactory.createDataSource(fPath).iterListRecords();
	}
	
	@Test(dataProvider="dp")
	public void repeat(DataRecord record) throws Exception {
		int left = record.valueAt(0).asInt();
		int right = record.valueAt(1).asInt();
		int expectedSum = record.valueAt(2).asInt();
		assertEquals(expectedSum, left+right);
	}
}

