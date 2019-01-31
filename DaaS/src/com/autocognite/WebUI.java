package com.autocognite;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import pvt.arjunapro.web.utils.CentralDB;
import pvt.arjunapro.webui.servlet.CentralConf;
import pvt.arjunapro.webui.servlet.DashBoard;
import pvt.arjunapro.webui.servlet.Project;
import pvt.arjunapro.webui.servlet.Projects;
import pvt.batteries.webserver.BaseWebServer;

public class WebUI extends BaseWebServer{

	public WebUI(int port) throws Exception {
		super(port);
	}

	public WebUI() throws Exception {
		super(9898);
		CentralDB.INSTANCE.init();
	}

	protected List<Handler> getHandlers() throws Exception {
		List<Handler> handlers = new ArrayList<Handler>();

		ServletContextHandler staticRes = new ServletContextHandler(ServletContextHandler.SESSIONS);
		staticRes.setContextPath("/static");
		staticRes.setResourceBase(WebUI.class.getClassLoader().getResource("resources/static").toExternalForm());
		staticRes.addServlet(DefaultServlet.class, "/");
		handlers.add(staticRes);
		
		ServletContextHandler dashboard = new ServletContextHandler();
		dashboard.setContextPath("/dashboard");
		dashboard.addServlet(DashBoard.class, "/*");
		handlers.add(dashboard);
		
		ServletContextHandler centralConf = new ServletContextHandler();
		centralConf.setContextPath("/centralconf");
		centralConf.addServlet(CentralConf.class, "/*");
		handlers.add(centralConf);
		
		ServletContextHandler projects = new ServletContextHandler();
		projects.setContextPath("/projects");
		projects.addServlet(Projects.class, "/*");
		handlers.add(projects);
		
		ServletContextHandler project = new ServletContextHandler();
		project.setContextPath("/project");
		project.addServlet(Project.class, "/*");
		handlers.add(project);

		return handlers;
	}
}
