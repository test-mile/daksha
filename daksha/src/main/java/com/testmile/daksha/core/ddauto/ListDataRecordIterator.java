package com.testmile.daksha.core.ddauto;

import com.testmile.daksha.tpi.ddauto.DataSource;

public class ListDataRecordIterator extends BaseRecordIterator{
	public ListDataRecordIterator(DataSource source) {
		super(source.allListRecords());
	}

}
