package daksha.core.batteries.config;

import java.util.Map;

import daksha.core.batteries.container.ValueContainer;
import daksha.tpi.batteries.container.Value;
import daksha.tpi.enums.DakshaOption;

public interface Configuration {

	ValueContainer<DakshaOption> getAllOptions();

	void add(String k, String v) throws Exception;
	
	void add(DakshaOption option, String v) throws Exception;

	void add(Map<String, String> map) throws Exception;

	Value value(DakshaOption option) throws Exception;

}