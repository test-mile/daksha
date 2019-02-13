package com.testmile.setu.agent.guiauto.tpi.handler.element;

import java.util.List;

public interface MultiElementInquirer {

	List<String> getTextContent() throws Exception;

	List<String> getAttribute(String attr) throws Exception;

}