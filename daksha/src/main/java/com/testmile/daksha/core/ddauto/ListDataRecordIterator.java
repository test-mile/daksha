package com.testmile.daksha.core.ddauto;

import java.util.Iterator;
import java.util.List;

import com.testmile.daksha.tpi.ddauto.DataSource;
import com.testmile.daksha.tpi.ddauto.ListDataRecord;

public class ListDataRecordIterator extends BaseRecordIterator{
	public ListDataRecordIterator(DataSource source) {
		super(source.allListRecords());
	}

}
