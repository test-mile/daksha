package com.autocognite;

import pvt.batteries.webserver.ArjunaWebServer;

public class ArjunaProUI {

	public static void main(String[] args) throws Exception{
		
		ArjunaWebServer ws = null;
		if (args.length == 0){
			ws = new WebUI();
		} else {
			ws = new WebUI(Integer.parseInt(args[0].trim()));
		}
		
		ws.launch();
	}

}
