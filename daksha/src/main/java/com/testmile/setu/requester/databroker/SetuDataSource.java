package com.testmile.setu.requester.databroker;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.testmile.daksha.tpi.test.TestSession;
import com.testmile.setu.requester.DefaultSetuObject;
import com.testmile.setu.requester.SetuSvcRequester;

public abstract class SetuDataSource<D> extends DefaultSetuObject {
	private SetuSvcRequester setuRequester;
	private String baseActionUri = "/datasource/action";

	public SetuDataSource(TestSession testSession, String setuId) {
		this.setuRequester = testSession.getSetuRequester();
		this.setTestSessionSetuId(testSession.getSetuId());
	}

	public Iterator<Object[]> iterRecordsForTestNG() throws Exception {
		return new DataRecordIteratorForTestNG<D>(this);
	}

	public Iterator<D> iterRecords() throws Exception {
		return new DataRecordIterator<D>(this);
	}	
	
	abstract protected D next() throws Exception;
	
	private abstract class AbstractDataRecordIterator<D,I> implements Iterator<I>{
		private SetuDataSource<D> source;
		private I nextValue = null;

		public AbstractDataRecordIterator(SetuDataSource<D> source) {
			this.source = source;
		}
		
		protected SetuDataSource<D> getSource(){
			return this.source;
		}

		@Override
		public boolean hasNext() {
			// This logic is done for compatibility with TestNG
			// Setu supports shared data and we should not split fetching value into a 2 stage process to avoid TCOTOU issues.
			try {
				nextValue = getNext();
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		
		protected abstract I getNext() throws Exception;
		
		@Override
		public I next() {
			return nextValue;
		}
	}
	
	private class DataRecordIteratorForTestNG<D> extends AbstractDataRecordIterator<D, Object[]>{

		public DataRecordIteratorForTestNG(SetuDataSource<D> source) {
			super(source);
		}
		
		@Override
		public Object[] getNext() throws Exception {
			return new Object[] {getSource().next()};
		}
	}
	
	private class DataRecordIterator<D> extends AbstractDataRecordIterator<D, D>{

		public DataRecordIterator(SetuDataSource<D> source) {
			super(source);
		}
		
		@Override
		public D getNext() throws Exception {
			return getSource().next();
		}
	}

}
