package com.testmile.setu.agent.guiauto.tpi.handler.element;

public interface ElementInquirer {

	String getText() throws Exception;

	String getAttribute(String attr) throws Exception;

	String getTagName() throws Exception;

}