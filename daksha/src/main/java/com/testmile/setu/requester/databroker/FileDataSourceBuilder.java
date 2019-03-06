package com.testmile.setu.requester.databroker;

public interface FileDataSourceBuilder<T> {

	FileDataSourceBuilder<T> delimiter(String delimiter);

	T build() throws Exception;

}