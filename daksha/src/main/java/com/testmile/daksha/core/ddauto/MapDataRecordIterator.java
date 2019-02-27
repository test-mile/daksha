package com.testmile.daksha.core.ddauto;

import com.testmile.daksha.tpi.ddauto.DataSource;

public class MapDataRecordIterator extends BaseRecordIterator{
	public MapDataRecordIterator(DataSource source) {
		super(source.allMapRecords());
	}

}
