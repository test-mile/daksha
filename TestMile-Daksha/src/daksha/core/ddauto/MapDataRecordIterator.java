package daksha.core.ddauto;

import java.util.Iterator;

import daksha.tpi.ddauto.DataSource;

public class MapDataRecordIterator extends BaseRecordIterator{
	public MapDataRecordIterator(DataSource source) {
		super(source.allMapRecords());
	}

}
