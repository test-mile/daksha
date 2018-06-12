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
package daksha.core.databroker;

import java.util.List;

import daksha.core.batteries.filehandler.ExcelFileLine2ArrayReader;
import daksha.core.utils.ExceptionBatteries;
import daksha.tpi.ddauto.exceptions.DataSourceFinishedException;
import daksha.tpi.ddauto.interfaces.DataRecord;
import daksha.tpi.ddauto.lib.BaseDataSource;
import daksha.tpi.ddauto.lib.MapDataRecord;

public class ExcelFileDataSource extends BaseDataSource {
	ExcelFileLine2ArrayReader reader = null;
	List<String> headers = null;

	public ExcelFileDataSource(String path) throws Exception {
		if (path.toLowerCase().endsWith("xls")) {
			reader = new ExcelFileLine2ArrayReader(path);
			headers = reader.getHeaders();
		} else {
			throw new Exception("Unsupported file format for Excel reading.");
		}
	}

	@Override
	public synchronized DataRecord next() throws DataSourceFinishedException {
		if (this.isTerminated()){
			throw new DataSourceFinishedException("Records Finished.");			
		}
		
		List<Object> dataRecord = null;
		try {
			dataRecord = reader.next();
		} catch (Exception e) {
			ExceptionBatteries.getStackTraceAsString(e);
			throw new DataSourceFinishedException("Problem happened in reading. No further records would be provided.");
		}

		if (dataRecord == null) {
			throw new DataSourceFinishedException("Records Finished.");
		} else {
			return new MapDataRecord(this.headers, dataRecord);
		}
	}

}
