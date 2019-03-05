package com.testmile.setu.requester.databroker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.testmile.daksha.tpi.ddauto.MapDataRecord;
import com.testmile.daksha.tpi.ddauto.MapDataSource;

public class MapDataContainerBuilder {
	private MapDataRecordContainer container;
	
	public MapDataContainerBuilder(String... headers) throws Exception {
		container = new MapDataRecordContainer(headers);
	}
	
	public MapDataContainerBuilder record(Object... record) throws Exception {
		container.add(record);
		return this;
	}
	
	public MapDataSource build() {
		return this.container;
	}
}


class MapDataRecordContainer extends BaseDataSource<MapDataRecord> implements MapDataSource {
	private String[] headers = null;
	private int refLen = -1;
	
	public MapDataRecordContainer(String[] headers) throws Exception{
		super();
		this.headers = headers;
		this.refLen = headers.length;
		validate();
	}
	
	public MapDataRecordContainer(String[] headers, Object[][] records) throws Exception {
		this(headers);
		this.addAll(records);
	}
	
	public void add(Object[] record) throws Exception {
		if (record.length != refLen){
			throw new Exception(String.format("All records must match the length of headers: %d. Current Record Length: %d. Record: [%s]", refLen, record.length, Arrays.toString(record)));					
		}
		Map<String, Object> map = new HashMap<String,Object>();
		for (int i=0; i < this.refLen; i++) {
			map.put(headers[i],record[i]);
		}
		super.addSingleRecord(new DefaultMapDataRecord(map));
	}

	private void validate() throws Exception {
		if ((this.headers == null) || (this.headers.length == 0)){
			throw new Exception("You can not use a Map Data Record container without no headers.");
		}
	}

}