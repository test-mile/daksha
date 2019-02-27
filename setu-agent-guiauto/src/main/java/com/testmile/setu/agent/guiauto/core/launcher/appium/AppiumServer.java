/*******************************************************************************
 * Copyright 2015-19 Test Mile Software Testing Pvt Ltd
 * 
 * Website: www.TestMile.com
 * Email: support [at] testmile.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

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
