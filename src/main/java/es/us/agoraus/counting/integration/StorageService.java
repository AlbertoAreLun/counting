package es.us.agoraus.counting.integration;

import org.json.JSONObject;

public interface StorageService {

//	@GET("/get_votes.php")
//	Call<EncryptedVotes> getVotesForPoll(@Query("votation_id") String pollId);
	
	/**
	 * It gets the votes of a poll from the Storage subsystem
	 * 
	 * @param pollId
	 *            as the Id of the poll
	 * @return A JSONObject that includes if there was an error and the results of votes with their user information.
	 */
	public static JSONObject getVotesByPollId(int pollId) {
		String storageAnswer;
		
		storageAnswer = "{"
			    + "\"error\": false,"
			    + "\"results\": ["
			    + "    {"
			    + "        \"id\": 1,"
			    + "        \"created_at\": \"2017-01-09 20:21:45\","
			    + "        \"updated_at\": \"2017-01-09 20:21:45\","
			    + "        \"option_id\": 31,"
			    + "        \"user\": {"
			    + "           \"id\": 1,"
			    + "            \"name\": \"Michaela Monahan\","
			    + "            \"email\": \"fhickle@example.net\","
			    + "            \"created_at\": \"2017-01-09 20:21:45\","
			    + "            \"updated_at\": \"2017-01-09 20:21:45\","
			    + "            \"age\": 16,"
			    + "            \"gender\": \"male\","
			    + "            \"state\": \"País Vasco\""
			    + "        }"
			    + "    },"
			    + "    {"
			    + "        \"id\": 2,"
			    + "        \"created_at\": \"2017-01-09 20:21:45\","
			    + "        \"updated_at\": \"2017-01-09 20:21:45\","
			    + "        \"option_id\": 32,"
			    + "        \"user\": {"
			    + "            \"id\": 2,"
			    + "            \"name\": \"Dr. Gregory Glover\","
			    + "            \"email\": \"cruz65@example.net\","
			    + "            \"created_at\": \"2017-01-09 20:21:45\","
			    + "            \"updated_at\": \"2017-01-09 20:21:45\","
			    + "            \"age\": 35,"
			    + "            \"gender\": \"male\","
			    + "            \"state\": \"Andalucía\""
			    + "        }"
			    + "    },"
			    + "    {"
			    + "        \"id\": 3,"
			    + "        \"created_at\": \"2017-01-09 20:21:45\","
			    + "        \"updated_at\": \"2017-01-09 20:21:45\","
			    + "        \"option_id\": 31,"
			    + "        \"user\": {"
			    + "            \"id\": 3,"
			    + "            \"name\": \"Isabell Crooks\","
			    + "            \"email\": \"hcormier@example.net\","
			    + "            \"created_at\": \"2017-01-09 20:21:45\","
			    + "            \"updated_at\": \"2017-01-09 20:21:45\","
			    + "            \"age\": 51,"
			    + "            \"gender\": \"female\","
			    + "            \"state\": \"Andalucía\""
			    + "        }"
			    + "    }"
			    + "    ]"
			    + "	}";
		
		JSONObject jsonStorageAnswer = new JSONObject(storageAnswer);
				
		return jsonStorageAnswer;
	}

}
