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

package com.testmile.daksha.tpi.ddauto;

import com.testmile.daksha.core.ddauto.BaseDataRecordContainer;
import com.testmile.daksha.core.ddauto.DataRecord;

public class MapDataRecordContainer extends BaseDataRecordContainer {
	
	@Override
	public void validate() throws Exception {
		if ((this.getHeaders() == null) || (this.getHeaders().length == 0)){
			throw new Exception("You can not use a Map Data Record container without defining appropriate headers.");
		}
	}
	
	protected synchronized DataRecord nextListRecord() throws DataSourceFinishedException, Exception{
		throw new Exception("ListDataRecordContainer does not support list records. Use ListDataRecordContainer.");
	}
	
}
