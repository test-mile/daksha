package com.testmile.setu.actor.core.guiauto.translator;

import java.util.Map;

import com.testmile.setu.actor.ActorAction;

public interface GuiAutoTranslator {

	String getSetuId();

	String takeElementAction(String elemId, ActorAction action) throws Exception;

	String launchAutomator(Map<String, Object> args) throws Exception;

	String takeAction(ActorAction action) throws Exception;

}