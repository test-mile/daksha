package arjuna.lib.setu.databroker.requester;

public interface FileDataSourceBuilder<T> {

	FileDataSourceBuilder<T> delimiter(String delimiter);

	T build() throws Exception;

}