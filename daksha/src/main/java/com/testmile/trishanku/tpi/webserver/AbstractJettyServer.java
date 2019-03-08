package com.testmile.trishanku.tpi.webserver;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

public abstract class AbstractJettyServer{
	private List<Handler> handlers = new ArrayList<Handler>();
	private Server server = null;
	private String rootContextPath;
	
	public AbstractJettyServer(int port, String rootContextPath) throws Exception{
		this.setServer(new Server(port));
		this.rootContextPath = rootContextPath;
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/static/root");
		//context.setResourceBase(AbstractJettyServer.class.getClassLoader().getResource("com/arjunapro/pvt/resources").toExternalForm());
		context.addServlet(DefaultServlet.class, "/");
		this.handlers.addAll(this.getServiceHandlers());
		this.registerHandlers(handlers);
	}
	
	protected abstract List<ServletContextHandler> getServiceHandlers() throws Exception;
	
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
	
	public void launch() throws Exception {
        server.start();
        server.join();
	}

	private Server getServer() {
		return server;
	}

	private void setServer(Server server) {
		this.server = server;
	}
}
