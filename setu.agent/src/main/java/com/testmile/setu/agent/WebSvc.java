package com.testmile.setu.agent;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class WebSvc extends AbstractJettyServer{

	public WebSvc(int port) throws Exception {
		super(port);
	}

	public WebSvc() throws Exception {
		super(9898); // Move it to config
	}

	protected List<Handler> getHandlers() throws Exception {
		List<Handler> handlers = new ArrayList<Handler>();
		
		ServletContextHandler project = new ServletContextHandler();
		project.setContextPath("/setu/connect");
		project.addServlet(ConnectSvc.class, "/*");
		handlers.add(project);

		return handlers;
	}
}
