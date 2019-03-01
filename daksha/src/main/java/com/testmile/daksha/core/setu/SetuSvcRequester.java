package com.testmile.daksha.core.setu;

public interface SetuSvcRequester {

	Response post(String uri, SetuRequest action) throws Exception;

}