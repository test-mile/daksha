package arjuna.lib.setu.databroker.requester;

public interface DataContainerBuilder<T> {

	DataContainerBuilder<T> record(Object...objects) throws Exception;

	T build();

}
