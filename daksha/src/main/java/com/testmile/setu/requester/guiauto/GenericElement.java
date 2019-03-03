package com.testmile.setu.requester.guiauto;

import com.testmile.setu.requester.DefaultSetuObject;
import com.testmile.setu.requester.Response;
import com.testmile.setu.requester.SetuRequest;
import com.testmile.setu.requester.SetuSvcRequester;
import com.testmile.setu.requester.guiauto.automator.AppAutomator;

public class GenericElement extends DefaultSetuObject{
	private AppAutomator automator;
	private SetuSvcRequester setuRequester;
	private String baseActionUri = "/alert/action";

	public GenericElement(AppAutomator automator, String elemSetuId, String baseUri) {
		this.automator = automator;
		this.setSetuId(elemSetuId);
		setuRequester = this.automator.getSetuClient();
		this.baseActionUri = baseUri;
		this.setTestSessionSetuId(automator.getTestSessionSetuId());
	}
	
	protected String getBaseActionUri() {
		return this.baseActionUri;
	}

	protected void setBaseActionUri(String uri) {
		this.baseActionUri = uri;
	}
	
	public AppAutomator getAutomator() {
		return this.automator;
	}
	
	public SetuSvcRequester getSetuRequester() {
		return this.setuRequester;
	}
	
	protected Response takeAction(SetuRequest request) throws Exception {
		return this.getSetuRequester().post(baseActionUri, request);
	}
	
	protected Response takeAction(String actionString) throws Exception {
		return this.getSetuRequester().post(baseActionUri, new GenericElementAction(this, actionString));
	}
	
	protected SetuRequest createRequest(String actionString) throws Exception {
		return new GenericElementAction(this, actionString);
	}
	
	public boolean isPartial() throws Exception {
		return false;
	}

	public int getIndex() {
		return 0;
	}

}
