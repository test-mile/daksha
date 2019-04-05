package utils;

import java.util.UUID;

public class UUIDProvider {

	private UUIDProvider() {
	}
	
	public static synchronized String genUUID() {
		return UUID.randomUUID().toString();
	}

}
