package arjuna.lib.batteries.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceStreamBatteries {

	public static String streamToString(InputStream stream) throws Exception{
		StringBuilder builder = new StringBuilder();
		BufferedReader txtReader = new BufferedReader(new InputStreamReader(stream));
		String line = null;
		while ((line = txtReader.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}
		txtReader.close();
		return builder.toString();
	}
}
