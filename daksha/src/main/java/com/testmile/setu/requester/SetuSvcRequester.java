package com.testmile.setu.requester;

public interface SetuSvcRequester {

	Response post(String uri, SetuRequest action) throws Exception;

}