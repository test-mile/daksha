package com.testmile.setu.agent.guiauto.core.handler.automator;

public class HandlerUtils {
	

	
	public static void throwUnsupportedComponentExceptionForNullObject(Object obj, String name, String componentName) throws Exception {
		if (obj == null) {
			throw new Exception(String.format("%s does not contain %s component.", name, componentName));
		}
	}
	
}
