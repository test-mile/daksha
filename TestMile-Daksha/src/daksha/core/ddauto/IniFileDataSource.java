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
package daksha.core.ddauto;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import daksha.tpi.ddauto.DataSourceFinishedException;
import daksha.tpi.ddauto.ListDataRecord;
import daksha.tpi.ddauto.MapDataRecord;
import daksha.tpi.sysauto.file.IniFileReader;

public class IniFileDataSource extends BaseDataSource {
	IniFileReader reader = null;
	Set<String> sections = null;
	Iterator<String> iter = null;

	public IniFileDataSource(String path) throws Exception {
		reader = new IniFileReader(path);
		sections = this.reader.getAllSections();
		iter = sections.iterator();
	}

	@Override
	public synchronized DataRecord next() throws DataSourceFinishedException {
		if (this.isTerminated()){
			throw new DataSourceFinishedException("Records Finished.");			
		}
		if (iter.hasNext()) {
			return new MapDataRecord(this.reader.getSectionDataObjects((String) iter.next()));
		} else {
			throw new DataSourceFinishedException("Records Finished.");
		}
	}
	
	@Override
	public Iterator<Object[]> iterListRecords() throws Exception{
		throw new Exception("Ini data source does not support list record iterator. User Map Record Iterator.");
	}
	
	protected synchronized DataRecord nextMapRecord() throws DataSourceFinishedException, Exception{
		return this.next();
	}
}
