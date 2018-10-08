package daksha.core.batteries.config;

import daksha.tpi.batteries.container.Value;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.enums.ValueType;

public class ConfigPropertyBuilder {
	private String definer = "Daksha";
	private String propCodeName = null;
	private String propPath = null;
	private ConfigPropertyLevel propLevel = ConfigPropertyLevel.CENTRAL;
	private String readableName = null;
	private boolean propUserOverride = true;
	private boolean propVisible = true;
	private Value propValue = null;
	private ValueType expectedValueType = ValueType.STRING;

	private void reset() {
		definer = "Daksha";
		propCodeName = null;
		propPath = null;
		propLevel = ConfigPropertyLevel.CENTRAL;
		readableName = null;
		propUserOverride = true;
		propVisible = true;
		propValue = null;
		expectedValueType = ValueType.STRING;
	}

	public ConfigPropertyBuilder option(DakshaOption option) {
		definer = "Daksha";
		propCodeName = option.toString();
		propPath = propCodeName.toLowerCase().replace("_",".");
		if (readableName == null) {
			this.readableName = propCodeName;
		}
		return this;
	}
	
	public ConfigPropertyBuilder option(String option) {
		definer = "User Defined";
		propCodeName = option.toUpperCase().replace("\\.", "_");
		propPath = propCodeName.toLowerCase().replace("_",".");
		if (readableName == null) {
			this.readableName = propCodeName;
		}
		return this;
	}

	public ConfigPropertyBuilder level(ConfigPropertyLevel level) {
		propLevel = level;
		return this;
	}

	public ConfigPropertyBuilder text(String text) {
		this.readableName = text;
		return this;
	}

	public ConfigPropertyBuilder overridable(boolean flag) {
		this.propUserOverride = flag;
		return this;
	}

	public ConfigPropertyBuilder visible(boolean flag) {
		this.propVisible = flag;
		return this;
	}

	public ConfigPropertyBuilder value(Value val) {
		this.propValue = val;
		return this;
	}

	public ConfigPropertyBuilder expectedValueType(ValueType vType) {
		this.expectedValueType = vType;
		return this;
	}

	public ConfigProperty build() {
		ConfigProperty prop = new ConfigProperty(this.definer, this.propCodeName, this.propPath, this.expectedValueType,
				this.propValue, this.propLevel, this.readableName, this.propUserOverride, this.propVisible);
		this.reset();
		return prop;
	}
}
