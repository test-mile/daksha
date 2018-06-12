package daksha.tpi.batteries.config;

import org.apache.log4j.Logger;

import daksha.core.batteries.config.Batteries;
import daksha.core.lib.CentralConfiguration;
import daksha.tpi.batteries.interfaces.ReadOnlyStringKeyValueContainer;
import daksha.tpi.batteries.interfaces.StringKeyValueContainer;
import daksha.tpi.batteries.interfaces.Value;
import daksha.tpi.sysauto.utils.ThreadUtils;

public class RunConfig {

	private static String getConfigName() {
		return ThreadUtils.getCurrentThreadName();
	}

	public synchronized static boolean exists(String path) throws Exception {
		return CentralConfiguration.hasProperty(getConfigName(), path);
	}

	public synchronized static Value value(String propName) throws Exception {
		return CentralConfiguration.value(propName);
	}

	public static Logger logger() {
		return Logger.getLogger(Batteries.getCentralLogName());
	}
	
	public static StringKeyValueContainer cloneUserOptions() throws Exception {
		return Batteries.cloneCentralUserOptions();
	}
	
	public static ReadOnlyStringKeyValueContainer userOptions() throws Exception {
		return Batteries.sessionUserOptions();
	}
}
