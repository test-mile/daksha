package daksha.core.ddauto;

import java.util.Iterator;
import java.util.List;

import daksha.tpi.ddauto.DataSource;
import daksha.tpi.ddauto.ListDataRecord;

public class ListDataRecordIterator extends BaseRecordIterator{
	public ListDataRecordIterator(DataSource source) {
		super(source.allListRecords());
	}

}
