package daksha.core.batteries.config;

import java.io.File;
import java.lang.reflect.Method;

import daksha.Daksha;
import daksha.core.value.StringListValue;
import daksha.core.value.StringValue;
import daksha.core.value.ValueFactory;
import daksha.tpi.batteries.container.Value;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.enums.ValueType;
import daksha.tpi.sysauto.utils.FileSystemUtils;

public class ConfigPropertyBatteries {

	public static String pathToCoreAbsolutePath(String path) throws Exception {
		String retPath = null;
		if (path.startsWith("*")){
			if (path.startsWith("*/") || path.startsWith("*\\")){
				retPath = FileSystemUtils.getCanonicalPath(Daksha.getRootDir() + path.substring(1));
			} else {
				retPath = FileSystemUtils.getCanonicalPath(Daksha.getRootDir() + File.separator + path.substring(1));
			}
			
		} else {
			retPath = path;
		}
//		if (FileSystemBatteries.isAbsolutePath(configuredDir)) {
//			retPath = configuredDir;
//		} else {
//			retPath = FileSystemBatteries.getCanonicalPath(Batteries.getBaseDir() + File.separator + configuredDir);
//		}
		return retPath;
	}

	public static boolean isNotSet(Value configValue) throws Exception {
		if (configValue.valueType() == ValueType.NULL) {
			return true;
		}

		if (configValue.valueType() == ValueType.STRING) {
			String val = configValue.asString();
			if (val.toLowerCase().equals("not_set")) {
				return true;
			} else if (val.toLowerCase().equals("na")) {
				return true;
			}
		}
		return false;
	}

	public static ConfigProperty createStringProperty(DakshaOption code, String propPath, Value configValue,
			String purpose, Object formatterObject, Method formatter, boolean visible) throws Exception {
		if (ConfigPropertyBatteries.isNotSet(configValue)) {
			return ConfigPropertyBatteries.createNotSetProperty(code, ValueType.STRING, propPath, purpose, visible);
		}
		StringValue sValue = ValueFactory.createStringValueFrom(configValue);
		sValue.process(formatterObject, formatter);

		return createProperty(code, ValueType.STRING, propPath, sValue, purpose, visible);
	}

	public static ConfigProperty createStringListProperty(DakshaOption code, String propPath,
			Value configValue, String purpose, Object formatterObject, Method formatter, boolean visible) throws Exception {
		if (ConfigPropertyBatteries.isNotSet(configValue)) {
			return ConfigPropertyBatteries.createNotSetProperty(code, ValueType.STRING_LIST, propPath, purpose, visible);
		}
		StringListValue sList = ValueFactory.createStringListValueFrom(configValue);
		sList.process(formatterObject, formatter);
		return createProperty(code, ValueType.STRING_LIST, propPath, sList, purpose, visible);
	}

	public static String getSame(String input) {
		return input;
	}

	public static ConfigProperty createCoreDirPath(DakshaOption code, String propPath, Value configValue,
			String purpose, boolean visible) throws Exception {
		return createStringProperty(code, propPath, configValue, purpose, ConfigPropertyBatteries.class,
				ConfigPropertyBatteries.class.getMethod("pathToCoreAbsolutePath", "abc".getClass()), visible);
	}
	
	public static ConfigProperty createProjectDirPath(DakshaOption code, String propPath, Value configValue,
			String purpose, boolean visible) throws Exception {
		return createStringProperty(code, propPath, configValue, purpose, ConfigPropertyBatteries.class,
				ConfigPropertyBatteries.class.getMethod("pathToProjectAbsolutePath", "abc".getClass()), visible);
	}

	public static ConfigProperty createStringListProperty(DakshaOption code, String propPath,
			Value configValue, String purpose, boolean visible) throws Exception {
		return createStringListProperty(code, propPath, configValue, purpose, ConfigPropertyBatteries.class,
				ConfigPropertyBatteries.class.getMethod("getSame", "abc".getClass()), visible);
	}

	public static ConfigProperty createDirectoriesProperty(DakshaOption code, String propPath,
			Value configValue, String purpose, boolean visible) throws Exception {
		return createStringListProperty(code, propPath, configValue, purpose, ConfigPropertyBatteries.class,
				ConfigPropertyBatteries.class.getMethod("pathToAbsolutePath", "abc".getClass()), visible);
	}

	public ConfigProperty createStringProperty(DakshaOption code, String propPath, String stringConfigValue,
			String purpose, boolean visible) throws Exception {
		return ConfigPropertyBatteries.createProperty(code, ValueType.STRING, propPath,
				new StringValue(stringConfigValue), purpose, visible);
	}

	public static ConfigProperty createStringProperty(DakshaOption code, String propPath, Value configValue,
			String purpose, boolean visible) throws Exception {
		return createStringProperty(code, propPath, configValue, purpose, ConfigPropertyBatteries.class,
				ConfigPropertyBatteries.class.getMethod("getSame", "abc".getClass()), visible);
	}

	public static <T extends Enum<T>> ConfigProperty createEnumProperty(DakshaOption code, String propPath,
			Class<T> klass, Value configValue, String purpose, boolean visible) throws Exception {
		Value value = ValueFactory.creatEnumValue(klass, configValue);
		return createProperty(code, ValueType.ENUM, propPath, value, purpose, visible);
	}

	public static <T extends Enum<T>> ConfigProperty createEnumListProperty(DakshaOption code,
			String propPath, Class<T> klass, Value configValue, String purpose, boolean visible) throws Exception {
		if (ConfigPropertyBatteries.isNotSet(configValue)) {
			return ConfigPropertyBatteries.createNotSetProperty(code, ValueType.ENUM_LIST, propPath, purpose, visible);
		}
		Value value = ValueFactory.createEnumListValue(klass, configValue);
		return ConfigPropertyBatteries.createProperty(code, ValueType.ENUM_LIST, propPath, value, purpose, visible);
	}

	public static ConfigProperty createBooleanProperty(DakshaOption code, String propPath, Value configValue,
			String purpose, boolean visible) throws Exception {
		if (ConfigPropertyBatteries.isNotSet(configValue)) {
			return ConfigPropertyBatteries.createNotSetProperty(code, ValueType.BOOLEAN, propPath, purpose, visible);
		}

		Value value = ValueFactory.createBooleanValue(configValue);
		return ConfigPropertyBatteries.createProperty(code, ValueType.BOOLEAN, propPath, value, purpose, visible);
	}

	private static ConfigProperty createNumberProperty(DakshaOption code, ValueType expectedType,
			String propPath, Value configValue, String purpose, boolean visible) throws Exception {
		if (ConfigPropertyBatteries.isNotSet(configValue)) {
			return ConfigPropertyBatteries.createNotSetProperty(code, expectedType, propPath, purpose, visible);
		}

		Value value = ValueFactory.createNumberValueFrom(configValue);
		return ConfigPropertyBatteries.createProperty(code, expectedType, propPath, value, purpose, visible);
	}

	public static ConfigProperty createIntProperty(DakshaOption code, String propPath, Value configValue,
			String purpose, boolean visible) throws Exception {
		return createNumberProperty(code, ValueType.INTEGER, propPath, configValue, purpose, visible);
	}

	public static ConfigProperty createLongProperty(DakshaOption code, String propPath, Value configValue,
			String purpose, boolean visible) throws Exception {
		return createNumberProperty(code, ValueType.LONG, propPath, configValue, purpose, visible);
	}

	public static ConfigProperty createFloatProperty(DakshaOption code, String propPath, Value configValue,
			String purpose, boolean visible) throws Exception {
		return createNumberProperty(code, ValueType.FLOAT, propPath, configValue, purpose, visible);
	}

	public static ConfigProperty createDoubleProperty(DakshaOption code, String propPath, Value configValue,
			String purpose, boolean visible) throws Exception {
		return createNumberProperty(code, ValueType.DOUBLE, propPath, configValue, purpose, visible);
	}

	public static ConfigProperty createNumberProperty(DakshaOption code, String propPath, Value configValue,
			String purpose, boolean visible) throws Exception {
		return createNumberProperty(code, ValueType.NUMBER, propPath, configValue, purpose, visible);
	}

	public static ConfigProperty createProperty(DakshaOption code, ValueType expectedType, String propPath,
			Value value, String purpose, boolean visible) throws Exception {
		ConfigPropertyBuilder builder = new ConfigPropertyBuilder();
		return builder.option(code).text(purpose).value(value).visible(visible).expectedValueType(expectedType).build();
	}

	public static ConfigProperty createNotSetProperty(DakshaOption code, ValueType expectedType, String propPath,
			String purpose, boolean visible) throws Exception {
		ConfigPropertyBuilder builder = new ConfigPropertyBuilder();
		return builder.option(code).text(purpose).value(Configuration.notSetValue).visible(visible)
				.expectedValueType(expectedType).build();
	}

	public static ConfigProperty createSimpleProperty(String propPath, Value value, String purpose) throws Exception {
		ConfigPropertyBuilder builder = new ConfigPropertyBuilder();
		return builder.option(propPath).text(purpose).value(value).build();
	}

	public static ConfigProperty createSimpleProperty(String propPath, Value value) throws Exception {
		ConfigPropertyBuilder builder = new ConfigPropertyBuilder();
		return builder.option(propPath).text("NOT_SET").value(value).build();
	}

}
