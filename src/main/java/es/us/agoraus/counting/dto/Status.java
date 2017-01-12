package es.us.agoraus.counting.dto;

public enum Status {

	SUCCESS(Code.gen(1), "OK."),
	STORAGE_CONNECTION_ERROR(Code.gen(2), "There was a connection error with Storage subsytem."),
	VERIFICATION_CONNECTION_ERROR(Code.gen(2), "There was a connection error with Verification subsytem."),
	INVALID_JSON_FORMAT(Code.gen(2), "There was a error with the Json format.");
	
	private final int code;
	private final String message;

	Status(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	private static class Code {

		private static final int PREFIX = 195;

		public static int gen(int code) {
			StringBuilder sb = new StringBuilder();
			sb.append(PREFIX);
			sb.append(code);
			return Integer.valueOf(sb.toString());
		}

	}
	
}
