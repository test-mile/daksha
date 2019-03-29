package arjuna.lib.httpclient;

public class SetuHttpException extends Exception{
	private String message;
	private int statusCode;
	private String response;

	private static final long serialVersionUID = -1008054652226003943L;
	
	public SetuHttpException(String msg, int status, String response) {
		this.setStatusCode(status);
		this.setResponse(response);
		this.setMessage(msg);
	}

	public int getStatusCode() {
		return statusCode;
	}

	private void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getResponse() {
		return response;
	}

	private void setResponse(String response) {
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	private void setMessage(String message) {
		this.message = message;
	}

}
