package daksha.core.leaping.actions.automator;

public interface MouseActionHandler {
	void scrollDown(int downCount) throws Exception;
	void scrollDown() throws Exception;
	public void scrollUp(int upCount)  throws Exception;
	void scrollUp()  throws Exception;
}
