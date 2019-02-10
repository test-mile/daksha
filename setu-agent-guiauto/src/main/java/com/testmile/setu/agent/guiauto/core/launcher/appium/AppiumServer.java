package com.testmile.setu.agent.guiauto.core.launcher.appium;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumServer {
	private int port;
	private URL hubUrl = null;
	
	public AppiumServer(int port) throws MalformedURLException {
		this.port = port;
		hubUrl = new URL(String.format("http://127.0.0.1:%d/wd/hub", this.port));
	}

	public AppiumServer(String url) throws Exception {
		this.hubUrl = new URL(url);
		port = this.hubUrl.getPort();
	}

	public int getPort() {
		return this.port;
	}
	
	public URL getURL() {
		return this.hubUrl;
	}

}
