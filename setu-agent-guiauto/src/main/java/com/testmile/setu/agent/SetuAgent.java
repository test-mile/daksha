package com.testmile.setu.agent;

/**
 * Hello world!
 *
 */
public class SetuAgent 
{
	public static void main(String[] args) throws Exception{
		String contextPath = "/setu/connect";
		SetuWebService ws = null;
		if (args.length == 0){
			ws = new SetuWebService(9898, contextPath);
		} else {
			ws = new SetuWebService(Integer.parseInt(args[0].trim()), contextPath);
		}
		
		ws.launch();
	}
}
