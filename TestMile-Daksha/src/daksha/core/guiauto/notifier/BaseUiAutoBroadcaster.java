package daksha.core.guiauto.notifier;

import java.util.ArrayList;
import java.util.List;

import daksha.core.guiauto.identifier.GuiElementIdentifier;
import daksha.tpi.guiauto.automator.GuiAutomator;
import daksha.tpi.guiauto.element.GuiElement;
import daksha.tpi.guiauto.element.GuiMultiElement;
import daksha.tpi.guiauto.gui.Gui;

public class BaseUiAutoBroadcaster implements UiAutoEventHandler{
	private List<UiAutoEventSubscriber> subscribers = new  ArrayList<UiAutoEventSubscriber>();
	
	public synchronized void register(UiAutoEventSubscriber subcscriber) {
		this.subscribers.add(subcscriber);
	}

//	@Override
//	public synchronized void onMakerEvent(GuiAutoMakerEvent event, String... args) {
//		for (UiAutoEventSubscriber subscriber: subscribers) {
//			subscriber.onMakerEvent(event, args);
//		}
//	}


}
