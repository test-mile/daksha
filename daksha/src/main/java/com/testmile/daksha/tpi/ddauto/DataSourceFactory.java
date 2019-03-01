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

import org.apache.commons.io.FilenameUtils;

import com.testmile.daksha.core.ddauto.DsvFileDataSource;
import com.testmile.daksha.core.ddauto.ExcelFileDataSource;
import com.testmile.daksha.core.ddauto.IniFileDataSource;
import com.testmile.trishanku.tpi.enums.DataFileFormat;

public class DataSourceFactory {
	
	private static DataFileFormat getFileFormat(String srcFilePath) throws Exception {
		try {
			return DataFileFormat.valueOf(FilenameUtils.getExtension(srcFilePath).toUpperCase());
		} catch (Exception e) {
			throw new Exception("DataSourceFactory can handle only TXT/CSV/XLS/INI files. Provided: " + srcFilePath);
		}
	}
	
	public static DataSource createDataSource(String srcFilePath) throws Exception{
		DataFileFormat format = getFileFormat(srcFilePath);
		switch(format) {
		case CSV:
			return createDataSource(srcFilePath, ",");
		case INI:
			return new IniFileDataSource(srcFilePath);
		case TXT:
			return createDataSource(srcFilePath, "\\t");
		case XLS:
			return new ExcelFileDataSource(srcFilePath);
		}
		
		throw new Exception("Unexpected issue in DataSourceFactory for file: " + srcFilePath);
	}
	
	public static DataSource createDataSource(String srcFilePath, String delimiter) throws Exception {
		DataFileFormat format = getFileFormat(srcFilePath);
		if ((format == DataFileFormat.CSV) || (format == DataFileFormat.TXT)){
			return new DsvFileDataSource(srcFilePath, delimiter);
		} else {
			return createDataSource(srcFilePath);
		}
	}
}
