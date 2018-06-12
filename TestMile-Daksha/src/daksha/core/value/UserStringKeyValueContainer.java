package daksha.core.value;

import daksha.tpi.batteries.console.Console;
import daksha.tpi.batteries.interfaces.Value;
import daksha.tpi.exceptions.StringKeyValueContainerLookUpException;

public class UserStringKeyValueContainer extends DefaultStringKeyValueContainer{

	public UserStringKeyValueContainer clone() {
		UserStringKeyValueContainer map = new UserStringKeyValueContainer();
		try {
			map.cloneAdd(this.items());
		} catch (Exception e) {
			Console.displayExceptionBlock(e);
		}
		return map;
	}
	
	@Override
	protected Value getValueForNonExistentKey(String key) throws Exception {
		throw new StringKeyValueContainerLookUpException(key);
	}

	@Override
	protected String getStrValueForNonExistentKey(String key) throws Exception {
		throw new StringKeyValueContainerLookUpException(key);
	}

}
