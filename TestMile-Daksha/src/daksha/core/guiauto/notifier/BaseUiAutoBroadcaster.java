package daksha.core.guiauto.notifier;

import java.util.ArrayList;
import java.util.List;

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
