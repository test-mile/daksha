package com.testmile.setu.requester.databroker;

import com.testmile.daksha.tpi.ddauto.ListDataRecord;
import com.testmile.daksha.tpi.ddauto.ListDataSource;

public class ListDataContainerBuilder{
	private ListDataRecordContainer container;
	
	public ListDataContainerBuilder() {
		container = new ListDataRecordContainer();
	}
	
	public ListDataContainerBuilder record(Object... record) throws Exception {
		container.add(record);
		return this;
	}
	
	public ListDataSource build() {
		return this.container;
	}
	
}

class ListDataRecordContainer extends BaseDataSource<ListDataRecord> implements ListDataSource{
	
	public ListDataRecordContainer(){
		super();
	}
	
	public ListDataRecordContainer(Object[][] records) throws Exception {
		super();
		this.addAll(records);
	}
	
	public void add(Object[] record) throws Exception {
		if (record.length == 0){
			throw new Exception("Empty record can not be added.");					
		}
		super.addSingleRecord(new DefaultListDataRecord(record));
	}

}