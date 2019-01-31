package pvt.arjunapro.webui.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;

import com.arjunapro.testauto.interfaces.Value;
import com.google.gson.JsonObject;

import pvt.arjunapro.web.utils.CentralDB;
import pvt.arjunapro.web.utils.HTMLUtils;

public class Project extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fullPath = request.getPathInfo();
		System.out.println(fullPath);
		String patternString1 = "/(.*?)(/.*)";
        Pattern pattern = Pattern.compile(patternString1);
        Matcher matcher = pattern.matcher(fullPath);
        matcher.matches();
        String projName = matcher.group(1);
        String path = matcher.group(2);
		String retContent =  null;
		System.out.println(path);
		switch (path){
		case "/home":
			retContent = home(projName, request, response);
			break;
		case "/menu":
			retContent = menu(projName, request, response);
			break;
		case "/home/new":
			retContent = homeNew(projName, request, response);
			break;
		case "/settings":
			retContent = projSettings(projName, request, response);
			break;
		case "/settings/panel":
			retContent = projSettingsPanel(projName, request, response);
			break;
		case "/settings/view":
			retContent = viewProjSettings(projName, request, response);
			break;
		case "/settings/edit":
			retContent = editProjSettings(projName, request, response);
			break;
		case "/configure":
			retContent = configure(projName, request, response);
			break;
		case "/conf/overview":
			retContent = overview(projName, request, response);
			break;
		case "/conf/arjuna":
			retContent = arjunaSettings(projName, request, response);
			break;
		case "/conf/arjuna/view":
			retContent = viewArjunaSettings(projName, request, response);
			break;
		case "/conf/arjuna/edit":
			retContent = editArjunaSettings(projName, request, response);
			break;
		case "/conf/uo":
			retContent = userConf(projName, request, response);
			break;
		case "/conf/tv":
			retContent = utv(projName, request, response);
			break;
		case "/execute":
			retContent = execute(projName, request, response);
			break;
		default:
			retContent = String.format("Requested link: <font color='red'>%s</font> is invalid.", request.getRequestURI());
		}
		
		response.getWriter().println(retContent);
		// This is very important, else JavaFX keeps waiting with zero progress.
		response.flushBuffer();	
	}
	
//	private String execute(String projName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		String body = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/execProjForm.html");
//		String common = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/execProjFormCommon.html");
//		String runid = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/runid.html");
//		String simple = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/execProjFormCommonSimple.html");
//		String session = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/session.html");
//		String picker = "";
//		
//		String retHtml = body
//				.replace("{{COMMON_EXEC_FORM}}", common)
//				.replace("{{COMMON_SIMPLE_EXEC_FORM}}", simple)
//				.replace("{{RUNID_FORM}}", runid)
//				.replace("{{SESSION_FORM}}", session)
//				.replace("{{PICKER_FORM}}", "")
//				.replace("{{PROJECT_NAME}}", projName)
//				.replace("{{SOURCE_CONFIG_TYPE}}", "Session");
//		response.setStatus(HttpStatus.OK_200);
//		return retHtml;
//	}
	
	private String execute(String projName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String body = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/exec/execProjForm.html");
		String common = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/exec/execProjFormCommon.html");
		String runid = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/exec/runid.html");
		String simple = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/exec/execProjFormCommonSimple.html");
		//String session = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/exec/sessionm.html");;
		String pickers = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/exec/pickers.html");
		String pickSingle = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/exec/pickersingle.html");
		String pickMulti = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/exec/pickermulti.html");
					
		String retHtml = body
				.replace("{{COMMON_EXEC_FORM}}", common)
				.replace("{{COMMON_SIMPLE_EXEC_FORM}}", simple)
				.replace("{{RUNID_FORM}}", runid)
				//.replace("{{SESSION_FORM}}", session)
				.replace("{{SESSION_FORM}}", "")
				.replace("{{PICKER_FORM}}", pickers)
				.replace("{{PICKER_PACKAGE_NAME}}", pickSingle.replace("{{OBJECT}}", "Package"))
				.replace("{{PICKER_MULTI_PACKAGE}}", pickMulti.replace("{{OBJECT}}", "Package").replace("{{OBJECTS}}", "Packages"))
				.replace("{{PICKER_CLASS_NAME}}", pickSingle.replace("{{OBJECT}}", "Class"))
				.replace("{{PICKER_MULTI_CLASS}}", pickMulti.replace("{{OBJECT}}", "Class").replace("{{OBJECTS}}", "Classes"))
				.replace("{{PICKER_METHODS}}", pickMulti.replace("{{OBJECT}}", "Method").replace("{{OBJECTS}}", "Methods"))
				.replace("{{PROJECT_NAME}}", projName)
				.replace("{{SOURCE_CONFIG_TYPE}}", "Project");
		response.setStatus(HttpStatus.OK_200);
		return retHtml;
	}
	
	private String home(String projName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String body = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/project/projectSection.html");

		String retHtml = body
				.replace("{{PROJECT_NAME}}", projName);
		response.setStatus(HttpStatus.OK_200);
		return retHtml;
	}
	
	private String menu(String projName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String body = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/project/projectMenu.html");

		String retHtml = body
				.replace("{{PROJECT_NAME}}", projName);
		response.setStatus(HttpStatus.OK_200);
		return retHtml;
	}
	
	private String homeNew(String projName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String body = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/project/project.html");

		String retHtml = body
				.replace("{{PROJ_TYPE}}", "new")
				.replace("{{PROJECT_NAME}}", projName);
		response.setStatus(HttpStatus.OK_200);
		return retHtml;
	}
	
	private String projSettingsPanel(String projName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String body = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/project/projSettingsPanel.html");

		String retHtml = body.replace("{{PROJECT_NAME}}", projName);
		response.setStatus(HttpStatus.OK_200);
		return retHtml;
	}
	
	private String projSettings(String projName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String body = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/project/projSettings.html");

		String retHtml = body.replace("{{PROJECT_NAME}}", projName);
		response.setStatus(HttpStatus.OK_200);
		return retHtml;
	}
	
	private String viewProjSettings(String projName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String body = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/project/projSettingsView.html");

		String retHtml = body.replace("{{PROJECT_NAME}}", projName);
		response.setStatus(HttpStatus.OK_200);
		return retHtml;
	}
	
	private String editProjSettings(String projName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String body = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/project/projSettingsEdit.html");

		String retHtml = body.replace("{{PROJECT_NAME}}", projName);
		response.setStatus(HttpStatus.OK_200);
		return retHtml;
	}
	
	private String configure(String projName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String retHtml = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/conf/allconf.html");
		retHtml = retHtml.replace("{{CONF_URI}}", "/project/" + projName + "/conf");
		response.setStatus(HttpStatus.OK_200);
		return retHtml;
	}
	
	private String overview(String projName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String body = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/conf/confoverview.html");
		response.setStatus(HttpStatus.OK_200);
		return body;
	}
	
	private String arjunaSettings(String projName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String body = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/conf/arjunaSettings.html")
				.replace("{{VIEW_LINK}}", String.format("/project/%s/conf/arjuna/view", projName))
				.replace("{{EDIT_LINK}}", String.format("/project/%s/conf/arjuna/edit", projName))
				.replace("{{SAVE_LINK}}", String.format("/project/%s/conf/arjuna/save", projName));	

		response.setStatus(HttpStatus.OK_200);
		return body;
	}
	
	private String viewArjunaSettings(String projName, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String body = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/conf/arjunaSettingsView.html");
		try{
			HashMap<String, Value> vals = CentralDB.INSTANCE.getCentralArjunaConfig();
			for(String k: vals.keySet()){
				body = body.replace(String.format("{{%s}}", k), vals.get(k).asString());
			}
		} catch (Exception e){
			e.printStackTrace();
		}

		response.setStatus(HttpStatus.OK_200);
		return body;
	}
	
	private String editArjunaSettings(String projName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String body = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/conf/arjunaSettingsEdit.html");
		try{
			body = body.replace("{{JSON_CONF}}", CentralDB.INSTANCE.getCentralArjunaConfigAsJsonString());
		} catch (Exception e){
			e.printStackTrace();
		}

		response.setStatus(HttpStatus.OK_200);
		return body;
	}
	
	private String userConf(String projName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String retHtml = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/conf/confFormTemplate.html")
				.replace("{{CONF_TYPE}}", "uo")
				.replace("{{CONF_TITLE}}", "User Options")
				.replace("{{CONF_SAVE_LINK}}", String.format("/project/%s/conf/userConf/save", projName));
		response.setStatus(HttpStatus.OK_200);
		return retHtml;
	}
	
	private String utv(String projName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String retHtml = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/conf/confFormTemplate.html")
				.replace("{{CONF_TYPE}}", "tv")
				.replace("{{CONF_TITLE}}", "Test Variables")
				.replace("{{CONF_SAVE_LINK}}", String.format("/project/%s/conf/tv/save", projName));
		response.setStatus(HttpStatus.OK_200);
		return retHtml;
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("POST");
		String fullPath = request.getPathInfo();
		System.out.println(fullPath);
		String patternString1 = "/(.*?)(/.*)";
        Pattern pattern = Pattern.compile(patternString1);
        Matcher matcher = pattern.matcher(fullPath);
        matcher.matches();
        String projName = matcher.group(1);
        String path = matcher.group(2);
		String retContent =  null;
		
		JsonObject jObj = HTMLUtils.getJson(request.getInputStream());
		System.out.println("here" + jObj.toString());
		System.out.println(path);
		switch (path){
		case "/settings/save":
			// Here we need to create the DB entry and dir structure in projects directory.
			//Then redirect to project/<project_name>
			response.sendRedirect(String.format("/project/%s/settings/view", projName));
			return;
		case "/conf/arjuna/save":
			response.sendRedirect(String.format("/project/%s/conf/arjuna", projName));
			return;
		case "/conf/userConf/save":
			response.sendRedirect(String.format("/project/%s/conf/uo", projName));
			return;
		case "/conf/utv/save":
			response.sendRedirect(String.format("/project/%s/conf/tv", projName));
			return;
		case "/execute":
			retContent = "Executing...";
			break;
		default:
			retContent = String.format("Requested link: <font color='red'>%s</font> is invalid.", request.getRequestURI());
		}
		
		response.getWriter().println(retContent);
		// This is very important, else JavaFX keeps waiting with zero progress.
		response.flushBuffer();	
	}
		
		
}


