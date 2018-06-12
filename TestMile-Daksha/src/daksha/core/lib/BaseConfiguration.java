package daksha.core.lib;

import daksha.core.batteries.config.Configuration;
import daksha.core.value.DefaultStringKeyValueContainer;
import daksha.tpi.batteries.console.Console;

public class BaseConfiguration extends DefaultStringKeyValueContainer implements Configuration {

	public BaseConfiguration clone() {
		BaseConfiguration map = new BaseConfiguration();
		try {
			map.cloneAdd(this.items());
		} catch (Exception e) {
			Console.displayExceptionBlock(e);
		}
		return map;
	}
}
