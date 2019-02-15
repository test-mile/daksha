package com.testmile.setu.agent.guiauto.tpi.handler.automator;

import java.util.Set;

public interface HybridViewHandler {
	
	void switchToViewContext(String view) throws Exception ;

	String getCurrentViewContext() throws Exception;
	
	Set<String> getAllViewContexts() throws Exception;

}