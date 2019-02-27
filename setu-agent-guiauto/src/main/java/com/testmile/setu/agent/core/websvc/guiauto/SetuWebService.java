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

package com.testmile.setu.agent.core.websvc.guiauto;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.testmile.trishanku.tpi.webserver.AbstractJettyServer;

public class SetuWebService extends AbstractJettyServer{

	public SetuWebService(int port, String rootContextPath) throws Exception {
		super(port,rootContextPath);
	}

	protected List<Handler> getHandlers() throws Exception {
		List<Handler> handlers = new ArrayList<Handler>();
//		ServletContextHandler project = new ServletContextHandler();
//		project.setContextPath(this.getRootContextPath());
//		project.addServlet(InfoSvc.class, "/*");
//		handlers.add(project);

		ServletContextHandler guiauto = new ServletContextHandler();
		guiauto.setContextPath("/guiauto");
		guiauto.addServlet(GuiAutoSvc.class, "/*");
		handlers.add(guiauto);
		
		return handlers;
	}
}
