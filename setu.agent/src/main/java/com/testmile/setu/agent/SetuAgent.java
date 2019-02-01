package com.testmile.setu.agent;

/**
 * Hello world!
 *
 */
public class SetuAgent 
{
	public static void main(String[] args) throws Exception{
		
		WebSvc ws = null;
		if (args.length == 0){
			ws = new WebSvc();
		} else {
			ws = new WebSvc(Integer.parseInt(args[0].trim()));
		}
		
		ws.launch();
	}
}
