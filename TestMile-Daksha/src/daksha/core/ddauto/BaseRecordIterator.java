package daksha.core.ddauto;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import daksha.tpi.ddauto.DataSource;
import daksha.tpi.ddauto.ListDataRecord;

public abstract class BaseRecordIterator implements Iterator<Object[]>{
	private List<DataRecord> records;

	public BaseRecordIterator(List<DataRecord> records) {
		this.records = records;
	}

	@Override
	public boolean hasNext() {
		return records.size() > 0;
	}
	
	@Override
	public Object[] next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		} else {
			return new Object[] {records.remove(0)};
		}
	}



}