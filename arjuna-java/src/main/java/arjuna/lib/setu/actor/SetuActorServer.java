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

package arjuna.lib.setu.actor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class SetuActorServer{
	private List<Handler> handlers = new ArrayList<Handler>();
	private Server server = null;
	private String rootContextPath;
	
	public SetuActorServer(int port) throws Exception{
		this.setServer(new Server(port));
		this.rootContextPath = "/arjuna/setu/connect";
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/arjuna/static/root");
		//context.setResourceBase(AbstractJettyServer.class.getClassLoader().getResource("com/arjunapro/pvt/resources").toExternalForm());
		context.addServlet(DefaultServlet.class, "/");
	}
	
	public void addHandler(String classQualifiedName, String servletUri) {
		ServletContextHandler guiauto = new ServletContextHandler();
		guiauto.setContextPath(servletUri);
		guiauto.addServlet(classQualifiedName, "/*");
		handlers.add(guiauto);		
	}
	
	protected String getRootContextPath() {
		return this.rootContextPath;
	}
	
//	private void addHandler(ServletContextHandler handler) throws Exception{
//		handlers.add(handler);
//	}
	
	private void registerHandlers(List<Handler> handlers){
		 ContextHandlerCollection contexts=new ContextHandlerCollection();
		 contexts.setHandlers(handlers.toArray(new Handler[handlers.size()]));
		 server.setHandler(contexts);		
	}
	
	public void start() throws Exception {
		this.registerHandlers(handlers);
        server.start();
        server.join();
	}

	public void stop() throws Exception {
        server.stop();
        server.join();
	}
	
	private Server getServer() {
		return server;
	}

	private void setServer(Server server) {
		this.server = server;
	}
}
