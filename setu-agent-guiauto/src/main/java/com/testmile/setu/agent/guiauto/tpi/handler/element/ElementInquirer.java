package com.testmile.setu.agent.guiauto.tpi.handler.element;

public interface ElementInquirer {

	String getTextContent() throws Exception;

	String getAttribute(String attr) throws Exception;

	String getTagName() throws Exception;

}