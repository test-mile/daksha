package com.testmile.setu.agent;

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
		ServletContextHandler project = new ServletContextHandler();
		project.setContextPath(this.getRootContextPath());
		project.addServlet(ConnectSvc.class, "/*");
		handlers.add(project);

		return handlers;
	}
}
