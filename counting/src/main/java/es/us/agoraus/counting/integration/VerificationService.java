package es.us.agoraus.counting.integration;

import org.json.JSONObject;

public class VerificationService {
	
	/**
	 * It decrypt the votes
	 * 
	 * @param encryptedAnswer
	 *            as the encrypted JSONObject from Storage subsystem
	 * @return The JSONObject decrypted.
	 */
	public static JSONObject decryptStorageAnswer(JSONObject encryptedAnswer) {
		JSONObject decryptedAnswer;
		
		decryptedAnswer = encryptedAnswer;
		
		return decryptedAnswer;
	}

}
