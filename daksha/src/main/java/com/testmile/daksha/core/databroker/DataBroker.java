package com.testmile.daksha.core.databroker;

import com.testmile.daksha.tpi.ddauto.ListDataSource;
import com.testmile.daksha.tpi.ddauto.MapDataSource;

public interface DataBroker {
	
	MapDataSource createIniFileMapDataSource(String fileName) throws Exception;
	
	ListDataSource createTabDelimitedFileListDataSource(String fileName) throws Exception;
	MapDataSource createTabDelimitedFileMapDataSource(String fileName) throws Exception;
	
	ListDataSource createDelimitedFileListDataSource(String fileName, String delimiter) throws Exception;
	ListDataSource createDelimitedFileMapDataSource(String fileName, String delimiter) throws Exception;
	
	ListDataSource createExcelFileListDataSource(String fileName) throws Exception;
	MapDataSource createExcelFileMapDataSource(String fileName) throws Exception;

}