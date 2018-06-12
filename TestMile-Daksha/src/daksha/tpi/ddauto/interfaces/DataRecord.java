package daksha.tpi.ddauto.interfaces;

import daksha.tpi.batteries.interfaces.ReadOnlyStringKeyValueContainer;
import daksha.tpi.batteries.interfaces.Value;

public interface DataRecord extends ReadOnlyStringKeyValueContainer {

	Value valueAt(int index) throws Exception;

	String stringAt(int index) throws Exception;

	Object objectAt(int index) throws Exception;

}
