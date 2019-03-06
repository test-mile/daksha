package com.testmile.setu.requester.databroker;

public interface DataContainerBuilder<T> {

	DataContainerBuilder<T> record(Object...objects) throws Exception;

	T build();

}
