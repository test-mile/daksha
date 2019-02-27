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
package com.testmile.daksha.core.ddauto;

import java.util.List;

import com.testmile.daksha.core.batteries.utils.ExceptionBatteries;
import com.testmile.daksha.tpi.ddauto.DataSourceFinishedException;
import com.testmile.daksha.tpi.ddauto.ListDataRecord;
import com.testmile.daksha.tpi.ddauto.MapDataRecord;
import com.testmile.daksha.tpi.sysauto.file.ExcelFileLine2ArrayReader;

public class ExcelFileDataSource extends BaseDataSource {
	ExcelFileLine2ArrayReader reader = null;
	List<String> headers = null;
	private boolean exclusionEnabled = false;
	private int exclusionIndex;

	public ExcelFileDataSource(String path) throws Exception {
		if (path.toLowerCase().endsWith("xls")) {
			reader = new ExcelFileLine2ArrayReader(path);
			headers = reader.getHeaders();
			for (int i=0; i<headers.size(); i++) {
				if (headers.get(i).toLowerCase().equals("exclude")){
					exclusionEnabled = true;
					exclusionIndex = i;
					break;
				}
			}
		} else {
			throw new Exception("Unsupported file format for Excel reading.");
		}
	}

	private synchronized List<Object> nextRaw() throws DataSourceFinishedException {
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
		} else if (exclusionEnabled) {
			String exclude = (String) dataRecord.get(exclusionIndex); 
			if (exclude.equals("y") || exclude.equals("yes") || exclude.equals("true")) {
				return this.nextRaw();
			} else {
				return dataRecord;
			}
		} else {
			return dataRecord;
		}		
	}
	
	@Override
	public synchronized DataRecord next() throws DataSourceFinishedException {
		return new MapDataRecord(this.headers, this.nextRaw());	
	}

	protected synchronized DataRecord nextListRecord() throws DataSourceFinishedException, Exception{
		return new ListDataRecord(this.nextRaw());
	}
	
	protected synchronized DataRecord nextMapRecord() throws DataSourceFinishedException, Exception{
		return this.next();
	}

}
