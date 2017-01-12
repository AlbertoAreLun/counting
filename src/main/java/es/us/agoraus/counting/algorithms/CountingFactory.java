package es.us.agoraus.counting.algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import es.us.agoraus.counting.dto.AgeMayorityCount;
import es.us.agoraus.counting.dto.AgeMayorityVote;
import es.us.agoraus.counting.dto.BasicCount;
import es.us.agoraus.counting.dto.BasicVote;
import es.us.agoraus.counting.dto.GenderCount;
import es.us.agoraus.counting.dto.GenderVote;
import es.us.agoraus.counting.dto.RangeCount;
import es.us.agoraus.counting.dto.RangeVote;
import es.us.agoraus.counting.dto.Status;
import es.us.agoraus.counting.dto.StoredVote;
import es.us.agoraus.counting.dto.UsualAgeRangesCount;
import es.us.agoraus.counting.dto.UsualAgeRangesVote;
import es.us.agoraus.counting.integration.StorageService;
import es.us.agoraus.counting.integration.VerificationService;

public class CountingFactory {

	/**
	 * It decodes the votes given as Strings to Vote objects.
	 * 
	 * @param pollVotes
	 *            as a List of String as the first element of a Collection
	 * @return A List of Vote objects
	 */
//	@SuppressWarnings("unchecked")
//	private static List<StoredVote> decodeVotes(Collection<Object> pollVotes) {
//		List<StoredVote> votes = new ArrayList<StoredVote>();
//		List<String> stringVotes = new ArrayList<String>();
//
//		stringVotes = (List<String>) pollVotes.iterator().next();
//
//		for (String stringVote : stringVotes) {
//
//			String id = stringVote.split(";")[0].split("=")[1];
//			String age = stringVote.split(";")[1].split("=")[1];
//			String gender = stringVote.split(";")[2].split("=")[1];
//			String community = stringVote.split(";")[3].split("=")[1];
//
//			StoredVote vote = new StoredVote(id, age, gender, community);
//
//			votes.add(vote);
//
//		}
//
//		return votes;
//	}
	
	/**
	 * It decodes the votes given as Json to StoredVote objects.
	 * 
	 * @param storageDecryptedAnswer
	 *            as a Json
	 * @return A List of StoredVote objects
	 */
	private static List<StoredVote> fromJsonToStoredVotes(JSONObject storageDecryptedAnswer) {
		List<StoredVote> storedVotes = new ArrayList<StoredVote>();
		
		JSONArray resultsList = (JSONArray) storageDecryptedAnswer.get("results");
		
		Iterator<Object> iterator = resultsList.iterator();
        while (iterator.hasNext()) {
            String vote = iterator.next().toString();
            JSONObject jsonVote = new JSONObject(vote);
            
            int voteId = jsonVote.getInt("option_id");
            
            String user = jsonVote.get("user").toString();
            JSONObject jsonUser = new JSONObject(user);
            
            int userAge = jsonUser.getInt("age");
            String userGender = jsonUser.getString("gender");
            String userCommunity = jsonUser.getString("state");
            
            storedVotes.add(new StoredVote(voteId, userAge, userGender, userCommunity));
        }

		return storedVotes;
	}

	/**
	 * It makes a basic count of a poll
	 * 
	 * @param pollId
	 *            as the Id of the poll
	 * @return A BasicCount that includes the status, the pollId and the number of votes for each vote.
	 */
	public static BasicCount basicCount(int pollId) {
		BasicCount count;
		JSONObject storageEncryptedAnswer;
		JSONObject storageDecryptedAnswer;
		List<StoredVote> storageVotes;
		List<BasicVote> countedVotes;
		List<Integer> countedVotesIds;

		count = new BasicCount();
		storageVotes = new ArrayList<StoredVote>();
		countedVotes = new ArrayList<BasicVote>();
		countedVotesIds = new ArrayList<Integer>();
		storageEncryptedAnswer = null;
		storageDecryptedAnswer = null;
		
		count.setStatus(Status.SUCCESS.getMessage());
		count.setPollId(pollId);
		
		// Geting Json answer from Storage subsystem.
		try {
			storageEncryptedAnswer = StorageService.getVotesByPollId(pollId);
		} catch (Exception e) {
			e.printStackTrace();
			count.setStatus(Status.STORAGE_CONNECTION_ERROR.getMessage());
			return count;
		}
		
		// Decrypting storaged answer by Verification subsystem
		try {
			storageDecryptedAnswer = VerificationService.decryptStorageAnswer(storageEncryptedAnswer);
		} catch (Exception e) {
			e.printStackTrace();
			count.setStatus(Status.VERIFICATION_CONNECTION_ERROR.getMessage());
			return count;
		}
		
		// Decoding StoredVotes from Json answer
		try {
			storageVotes = fromJsonToStoredVotes(storageDecryptedAnswer);
		} catch (Exception e) {
			e.printStackTrace();
			count.setStatus(Status.INVALID_JSON_FORMAT.getMessage());
			return count;
		}
		
		// Counting votes
		for (StoredVote storedVote : storageVotes) {
			if (countedVotesIds.contains(storedVote.getId())){
				for (BasicVote vote : countedVotes) {
					if (vote.getId() == storedVote.getId()) {
						vote.increaseCount(1);
						break;
					}
				}
			} else {
				countedVotes.add(new BasicVote(storedVote.getId(), 1));
				countedVotesIds.add(storedVote.getId());
			}
		}
		
		count.setVotes(countedVotes);

		return count;
	}

	public static GenderCount genderCount(int pollId) {
        GenderCount count;
        JSONObject storageEncryptedAnswer;
        JSONObject storageDecryptedAnswer;
        List<StoredVote> storageVotes;
        List<GenderVote> countedVotes;
        List<Integer> countedVotesIds;

        count = new GenderCount();
        storageVotes = new ArrayList<StoredVote>();
        countedVotes = new ArrayList<GenderVote>();
        countedVotesIds = new ArrayList<Integer>();
        storageEncryptedAnswer = null;
        storageDecryptedAnswer = null;
        
        count.setStatus(Status.SUCCESS.getMessage());
        count.setPollId(pollId);
        
        // Geting Json answer from Storage subsystem.
        try {
            storageEncryptedAnswer = StorageService.getVotesByPollId(pollId);
        } catch (Exception e) {
            e.printStackTrace();
            count.setStatus(Status.STORAGE_CONNECTION_ERROR.getMessage());
            return count;
        }
        
        // Decrypting storaged answer by Verification subsystem
        try {
            storageDecryptedAnswer = VerificationService.decryptStorageAnswer(storageEncryptedAnswer);
        } catch (Exception e) {
            e.printStackTrace();
            count.setStatus(Status.VERIFICATION_CONNECTION_ERROR.getMessage());
            return count;
        }
        
        // Decoding StoredVotes from Json answer
        try {
            storageVotes = fromJsonToStoredVotes(storageDecryptedAnswer);
        } catch (Exception e) {
            e.printStackTrace();
            count.setStatus(Status.INVALID_JSON_FORMAT.getMessage());
            return count;
        }
        
        // Counting votes
        for (StoredVote storedVote : storageVotes) {
            if (countedVotesIds.contains(storedVote.getId())){
                for (GenderVote vote : countedVotes) {
                    if (vote.getId() == storedVote.getId()) {
                        if(storedVote.getGender().equals("female")){ 
                            vote.increaseFemaleCount(1);
                            break;
                        }else{
                            vote.increaseMaleCount(1);
                            break;
                        }
                    }
                }
            } else {
                if(storedVote.getGender().equals("female")){ 
                    countedVotes.add(new GenderVote(storedVote.getId(),1,0));
                    countedVotesIds.add(storedVote.getId());
                }else{
                    countedVotes.add(new GenderVote(storedVote.getId(),0,1));
                    countedVotesIds.add(storedVote.getId());
                }
            }
        }
        
        count.setVotes(countedVotes);

        return count;
    }

	/**
	 * It makes a count grouped by minors and adults (Minors: <18 years old,
	 * Adults: >=18 years old)
	 * 
	 * @param poll
	 *            as a Map with an only element with a String as the key (the id
	 *            of the poll) and a List of Strings as the values. Each value
	 *            String represents a vote with the next format:
	 *            'id=voteId;edad=voterAge;genero=voterGender;comunidad=
	 *            voterCommunity'
	 * @return Another Map with an only element with a String as the key (the id
	 *         of the poll) and a List of Strings as the values. Each value
	 *         String represents a vote id and the number of votes that it got
	 *         grouped by minor and adult users with the next format:
	 *         'id=idRespuesta;countMinors=numberOfVotesByMinorUsers;countAdults
	 *         =numberOfVotesByAdultUsers'
	 */
	public static AgeMayorityCount ageMayorityCount(int pollId) {
		AgeMayorityCount count;
		JSONObject storageEncryptedAnswer;
		JSONObject storageDecryptedAnswer;
		List<StoredVote> storageVotes;
		List<AgeMayorityVote> countedVotes;
		List<Integer> countedVotesIds;
		
		count = new AgeMayorityCount();
		storageVotes = new ArrayList<StoredVote>();
		countedVotes = new ArrayList<AgeMayorityVote>();
		countedVotesIds = new ArrayList<Integer>();
		storageEncryptedAnswer = null;
		storageDecryptedAnswer = null;
		
		count.setStatus(Status.SUCCESS.getMessage());
		count.setPollId(pollId);
		
		// Geting Json answer from Storage subsystem.
		try {
			storageEncryptedAnswer = StorageService.getVotesByPollId(pollId);
		} catch (Exception e) {
			e.printStackTrace();
			count.setStatus(Status.STORAGE_CONNECTION_ERROR.getMessage());
			return count;
		}
		
		// Decrypting storaged answer by Verification subsystem
		try {
			storageDecryptedAnswer = VerificationService.decryptStorageAnswer(storageEncryptedAnswer);
		} catch (Exception e) {
			e.printStackTrace();
			count.setStatus(Status.VERIFICATION_CONNECTION_ERROR.getMessage());
			return count;
		}
		
		// Decoding StoredVotes from Json answer
		try {
			storageVotes = fromJsonToStoredVotes(storageDecryptedAnswer);
		} catch (Exception e) {
			e.printStackTrace();
			count.setStatus(Status.INVALID_JSON_FORMAT.getMessage());
			return count;
		}
		
		// Counting votes
		for (StoredVote storedVote : storageVotes) {
			if (countedVotesIds.contains(storedVote.getId())){
				for (AgeMayorityVote vote : countedVotes) {
					if (vote.getId() == storedVote.getId()) {
						if(storedVote.getAge()>=18){
						vote.increaseMayorityCount(1);
						break;
						}
						else{
							vote.increaseMinorsCount(1);
						}
					}
				}
			} else {
				if(storedVote.getAge()>=18){
				countedVotes.add(new AgeMayorityVote(storedVote.getId(), 1,0));
				} else{
					countedVotes.add(new AgeMayorityVote(storedVote.getId(), 0,1));
				}
				countedVotesIds.add(storedVote.getId());
			}
		}
		
		count.setVotes(countedVotes);

		return count;
		
	}

	/**
	 * It makes a count grouped by a given age range
	 * 
	 * @param poll
	 *            as a Map with an only element with a String as the key (the id
	 *            of the poll) and a List of Strings as the values. Each value
	 *            String represents a vote with the next format:
	 *            'id=voteId;edad=voterAge;genero=voterGender;comunidad=
	 *            voterCommunity'.
	 * @param minRange
	 *            as the minimum age range.
	 * @param maxRange
	 *            as the maximum age range.
	 * @return Another Map with an only element with a String as the key (the id
	 *         of the poll) and a List of Strings as the values. Each value
	 *         String represents a vote id and the number of votes that it got
	 *         grouped by minor and adult users with the next format:
	 *         'id=idRespuesta;countRange=numberOfVotesInRange;countExcluded=
	 *         numberOfVotesOutOfTheRange'
	 */
//	public static Map<String, Object> ageRangeCount(Map<String, Object> poll, Integer minRange, Integer maxRange) {
//		Map<String, Object> countedPoll = new HashMap<String, Object>();
//		List<StoredVote> votes = new ArrayList<StoredVote>();
//		Map<String, Integer> rangeGroupedVotes = new HashMap<String, Integer>();
//		Map<String, Integer> excludedGroupedVotes = new HashMap<String, Integer>();
//		List<Integer> votesId = new ArrayList<>();
//
//		votes = decodeVotes(poll.values());
//
//		for (StoredVote vote : votes) {
//			String stringVoteId = vote.getId();
//			Integer integerVoteId = Integer.parseInt(vote.getId());
//			if (!votesId.contains(integerVoteId)) {
//				votesId.add(integerVoteId);
//			}
//			if (Integer.parseInt(vote.getAge()) >= minRange && Integer.parseInt(vote.getAge()) < maxRange) {
//				if (rangeGroupedVotes.containsKey(stringVoteId)) {
//					rangeGroupedVotes.put(stringVoteId, rangeGroupedVotes.get(stringVoteId) + 1);
//				} else {
//					rangeGroupedVotes.put(stringVoteId, 1);
//				}
//			} else {
//				if (excludedGroupedVotes.containsKey(stringVoteId)) {
//					excludedGroupedVotes.put(stringVoteId, excludedGroupedVotes.get(stringVoteId) + 1);
//				} else {
//					excludedGroupedVotes.put(stringVoteId, 1);
//				}
//			}
//		}
//
//		List<String> stringGroupedVotes = new ArrayList<String>();
//
//		for (int i = 0; i < votesId.size(); i++) {
//			Integer numberRangeGroupedVotes;
//			Integer numberExcludedGroupedVotes;
//			numberRangeGroupedVotes = rangeGroupedVotes.get(votesId.get(i).toString());
//			if (numberRangeGroupedVotes == null) {
//				numberRangeGroupedVotes = 0;
//			}
//			numberExcludedGroupedVotes = excludedGroupedVotes.get(votesId.get(i).toString());
//			if (numberExcludedGroupedVotes == null) {
//				numberExcludedGroupedVotes = 0;
//			}
//			stringGroupedVotes.add("id=" + votesId.get(i) + ";countRange=" + numberRangeGroupedVotes + ";countExcluded="
//					+ numberExcludedGroupedVotes);
//		}
//
//		countedPoll.put(poll.keySet().iterator().next(), stringGroupedVotes);
//
//		return countedPoll;
//	}
	public static RangeCount rangeCount(int pollId, int minRange, int maxRange) {
		RangeCount count;
		JSONObject storageEncryptedAnswer;
		JSONObject storageDecryptedAnswer;
		List<StoredVote> storageVotes;
		List<RangeVote> countedVotes;
		List<Integer> countedVotesIds;

		count = new RangeCount();
		storageVotes = new ArrayList<StoredVote>();
		countedVotes = new ArrayList<RangeVote>();
		countedVotesIds = new ArrayList<Integer>();
		storageEncryptedAnswer = null;
		storageDecryptedAnswer = null;
		
		count.setStatus(Status.SUCCESS.getMessage());
		count.setPollId(pollId);
		
		// Geting Json answer from Storage subsystem.
		try {
			storageEncryptedAnswer = StorageService.getVotesByPollId(pollId);
		} catch (Exception e) {
			e.printStackTrace();
			count.setStatus(Status.STORAGE_CONNECTION_ERROR.getMessage());
			return count;
		}
		
		// Decrypting storaged answer by Verification subsystem
		try {
			storageDecryptedAnswer = VerificationService.decryptStorageAnswer(storageEncryptedAnswer);
		} catch (Exception e) {
			e.printStackTrace();
			count.setStatus(Status.VERIFICATION_CONNECTION_ERROR.getMessage());
			return count;
		}
		
		// Decoding StoredVotes from Json answer
		try {
			storageVotes = fromJsonToStoredVotes(storageDecryptedAnswer);
		} catch (Exception e) {
			e.printStackTrace();
			count.setStatus(Status.INVALID_JSON_FORMAT.getMessage());
			return count;
		}
		
		// Counting votes
		for (StoredVote storedVote : storageVotes) {
			if (countedVotesIds.contains(storedVote.getId())){
				for (RangeVote vote : countedVotes) {
					if (vote.getId() == storedVote.getId()) {
						if(storedVote.getAge() >= minRange && storedVote.getAge() <= maxRange){
							vote.increaseCount(1);
							break;
						}else{
							vote.increaseNoCount(1);
							break;
						}
					}
				}
			} else {
				if(storedVote.getAge() >= minRange && storedVote.getAge() <= maxRange){
					countedVotes.add(new RangeVote(storedVote.getId(),1,0));
					countedVotesIds.add(storedVote.getId());
				}else{
					countedVotes.add(new RangeVote(storedVote.getId(),0,1));
					countedVotesIds.add(storedVote.getId());
				}
			}
		}
		
		count.setVotes(countedVotes);

		return count;
	}

	/**
	 * It makes a count grouped by gender (Female: F, Male: M)
	 * 
	 * @param poll
	 *            as a Map with an only element with a String as the key (the id
	 *            of the poll) and a List of Strings as the values. Each value
	 *            String represents a vote with the next format:
	 *            'id=voteId;edad=voterAge;genero=voterGender;comunidad=
	 *            voterCommunity'
	 * @return Another Map with an only element with a String as the key (the id
	 *         of the poll) and a List of Strings as the values. Each value
	 *         String represents a vote id and the number of votes that it got
	 *         grouped by users gender with the next format:
	 *         'id=idRespuesta;countFemale=numberOfVotesByFemaleUsers;countMale=
	 *         numberOfVotesByMaleUsers'
	 */
//	public static Map<String, Object> genderCount(Map<String, Object> poll) {
//		Map<String, Object> countedPoll = new HashMap<String, Object>();
//		List<StoredVote> votes = new ArrayList<StoredVote>();
//		Map<String, Integer> femaleGroupedVotes = new HashMap<String, Integer>();
//		Map<String, Integer> maleGroupedVotes = new HashMap<String, Integer>();
//		List<Integer> votesId = new ArrayList<>();
//
//		votes = decodeVotes(poll.values());
//
//		for (StoredVote vote : votes) {
//			String stringVoteId = vote.getId();
//			Integer integerVoteId = Integer.parseInt(vote.getId());
//			if (!votesId.contains(integerVoteId)) {
//				votesId.add(integerVoteId);
//			}
//			if (vote.getGender().equals("F")) {
//				if (femaleGroupedVotes.containsKey(stringVoteId)) {
//					femaleGroupedVotes.put(stringVoteId, femaleGroupedVotes.get(stringVoteId) + 1);
//				} else {
//					femaleGroupedVotes.put(stringVoteId, 1);
//				}
//			} else {
//				if (maleGroupedVotes.containsKey(stringVoteId)) {
//					maleGroupedVotes.put(stringVoteId, maleGroupedVotes.get(stringVoteId) + 1);
//				} else {
//					maleGroupedVotes.put(stringVoteId, 1);
//				}
//			}
//		}
//
//		List<String> stringGroupedVotes = new ArrayList<String>();
//
//		for (int i = 0; i < votesId.size(); i++) {
//			Integer numberFemaleGroupedVotes;
//			Integer numberMaleGroupedVotes;
//			numberFemaleGroupedVotes = femaleGroupedVotes.get(votesId.get(i).toString());
//			if (numberFemaleGroupedVotes == null) {
//				numberFemaleGroupedVotes = 0;
//			}
//			numberMaleGroupedVotes = maleGroupedVotes.get(votesId.get(i).toString());
//			if (numberMaleGroupedVotes == null) {
//				numberMaleGroupedVotes = 0;
//			}
//			stringGroupedVotes.add("id=" + votesId.get(i) + ";countFemale=" + numberFemaleGroupedVotes + ";countMale="
//					+ numberMaleGroupedVotes);
//		}
//
//		countedPoll.put(poll.keySet().iterator().next(), stringGroupedVotes);
//
//		return countedPoll;
//	}

	/**
	 * It makes a count grouped by autonomous community ('Ceuta' and 'Melilla'
	 * included. Total: 18 communities)
	 * 
	 * @param poll
	 *            as a Map with an only element with a String as the key (the id
	 *            of the poll) and a List of Strings as the values. Each value
	 *            String represents a vote with the next format:
	 *            'id=voteId;edad=voterAge;genero=voterGender;comunidad=
	 *            voterCommunity'
	 * @return Another Map with an only element with a String as the key (the id
	 *         of the poll) and a List of Strings as the values. Each value
	 *         String represents a vote id and the number of votes that it got
	 *         grouped by users autonomous community with the next format:
	 *         'id=idRespuesta;countAnd=numberOfVotesFromAndalucía;countCat=
	 *         numberOfVotesFromCataluña;countComMad=
	 *         numberOfVotesFromComunidadDeMadrid;countComVal=
	 *         numberOfVotesFromComunidadValenciana;countGal=
	 *         numberOfVotesFromGalicia;countCasLeo=
	 *         numberOfVotesFromCastillaYLeón;countPaiVas=
	 *         numberOfVotesFromPaísVasco;countCan=numberOfVotesFromCanarias;
	 *         countCasMan=numberOfVotesFromCastillaLaMancha;countRegMur=
	 *         numberOfVotesFromRegiónDeMurcia;countAra=numberOfVotesFromAragón;
	 *         countIslBal=numberOfVotesFromIslasBaleares;countExt=
	 *         numberOfVotesFromExtremadura;countAst=numberOfVotesFromAsturias;
	 *         countNav=numberOfVotesFromNavarra;countRio=
	 *         numberOfVotesFromLaRioja;countCeu=numberOfVotesFromCeuta;countMel
	 *         =numberOfVotesFromMelilla'
	 */
//	public static Map<String, Object> communityCount(Map<String, Object> poll) {
//		Map<String, Object> countedPoll = new HashMap<String, Object>();
//		List<StoredVote> votes = new ArrayList<StoredVote>();
//		Map<String, Integer> andGroupedVotes = new HashMap<String, Integer>();
//		Map<String, Integer> catGroupedVotes = new HashMap<String, Integer>();
//		Map<String, Integer> comMadGroupedVotes = new HashMap<String, Integer>();
//		Map<String, Integer> comValGroupedVotes = new HashMap<String, Integer>();
//		Map<String, Integer> galGroupedVotes = new HashMap<String, Integer>();
//		Map<String, Integer> casLeoGroupedVotes = new HashMap<String, Integer>();
//		Map<String, Integer> paiVasGroupedVotes = new HashMap<String, Integer>();
//		Map<String, Integer> canGroupedVotes = new HashMap<String, Integer>();
//		Map<String, Integer> casManGroupedVotes = new HashMap<String, Integer>();
//		Map<String, Integer> regMurGroupedVotes = new HashMap<String, Integer>();
//		Map<String, Integer> araGroupedVotes = new HashMap<String, Integer>();
//		Map<String, Integer> islBarGroupedVotes = new HashMap<String, Integer>();
//		Map<String, Integer> extGroupedVotes = new HashMap<String, Integer>();
//		Map<String, Integer> astGroupedVotes = new HashMap<String, Integer>();
//		Map<String, Integer> navGroupedVotes = new HashMap<String, Integer>();
//		Map<String, Integer> rioGroupedVotes = new HashMap<String, Integer>();
//		Map<String, Integer> ceuGroupedVotes = new HashMap<String, Integer>();
//		Map<String, Integer> melGroupedVotes = new HashMap<String, Integer>();
//
//		List<Integer> votesId = new ArrayList<>();
//
//		votes = decodeVotes(poll.values());
//
//		for (StoredVote vote : votes) {
//			String stringVoteId = vote.getId();
//			Integer integerVoteId = Integer.parseInt(vote.getId());
//			if (!votesId.contains(integerVoteId)) {
//				votesId.add(integerVoteId);
//			}
//			switch (vote.getCommunity()) {
//			case "And":
//				if (andGroupedVotes.containsKey(stringVoteId)) {
//					andGroupedVotes.put(stringVoteId, andGroupedVotes.get(stringVoteId) + 1);
//				} else {
//					andGroupedVotes.put(stringVoteId, 1);
//				}
//				break;
//			case "Cat":
//				if (catGroupedVotes.containsKey(stringVoteId)) {
//					catGroupedVotes.put(stringVoteId, catGroupedVotes.get(stringVoteId) + 1);
//				} else {
//					catGroupedVotes.put(stringVoteId, 1);
//				}
//				break;
//			case "ComMad":
//				if (comMadGroupedVotes.containsKey(stringVoteId)) {
//					comMadGroupedVotes.put(stringVoteId, comMadGroupedVotes.get(stringVoteId) + 1);
//				} else {
//					comMadGroupedVotes.put(stringVoteId, 1);
//				}
//				break;
//			case "ComVal":
//				if (comValGroupedVotes.containsKey(stringVoteId)) {
//					comValGroupedVotes.put(stringVoteId, comValGroupedVotes.get(stringVoteId) + 1);
//				} else {
//					comValGroupedVotes.put(stringVoteId, 1);
//				}
//				break;
//			case "Gal":
//				if (galGroupedVotes.containsKey(stringVoteId)) {
//					galGroupedVotes.put(stringVoteId, galGroupedVotes.get(stringVoteId) + 1);
//				} else {
//					galGroupedVotes.put(stringVoteId, 1);
//				}
//				break;
//			case "CasLeo":
//				if (casLeoGroupedVotes.containsKey(stringVoteId)) {
//					casLeoGroupedVotes.put(stringVoteId, casLeoGroupedVotes.get(stringVoteId) + 1);
//				} else {
//					casLeoGroupedVotes.put(stringVoteId, 1);
//				}
//				break;
//			case "PaiVas":
//				if (paiVasGroupedVotes.containsKey(stringVoteId)) {
//					paiVasGroupedVotes.put(stringVoteId, paiVasGroupedVotes.get(stringVoteId) + 1);
//				} else {
//					paiVasGroupedVotes.put(stringVoteId, 1);
//				}
//				break;
//			case "Can":
//				if (canGroupedVotes.containsKey(stringVoteId)) {
//					canGroupedVotes.put(stringVoteId, canGroupedVotes.get(stringVoteId) + 1);
//				} else {
//					canGroupedVotes.put(stringVoteId, 1);
//				}
//				break;
//			case "CasMan":
//				if (casManGroupedVotes.containsKey(stringVoteId)) {
//					casManGroupedVotes.put(stringVoteId, casManGroupedVotes.get(stringVoteId) + 1);
//				} else {
//					casManGroupedVotes.put(stringVoteId, 1);
//				}
//				break;
//			case "RegMur":
//				if (regMurGroupedVotes.containsKey(stringVoteId)) {
//					regMurGroupedVotes.put(stringVoteId, regMurGroupedVotes.get(stringVoteId) + 1);
//				} else {
//					regMurGroupedVotes.put(stringVoteId, 1);
//				}
//				break;
//			case "Ara":
//				if (araGroupedVotes.containsKey(stringVoteId)) {
//					araGroupedVotes.put(stringVoteId, araGroupedVotes.get(stringVoteId) + 1);
//				} else {
//					araGroupedVotes.put(stringVoteId, 1);
//				}
//				break;
//			case "IslBal":
//				if (islBarGroupedVotes.containsKey(stringVoteId)) {
//					islBarGroupedVotes.put(stringVoteId, islBarGroupedVotes.get(stringVoteId) + 1);
//				} else {
//					islBarGroupedVotes.put(stringVoteId, 1);
//				}
//				break;
//			case "Ext":
//				if (extGroupedVotes.containsKey(stringVoteId)) {
//					extGroupedVotes.put(stringVoteId, extGroupedVotes.get(stringVoteId) + 1);
//				} else {
//					extGroupedVotes.put(stringVoteId, 1);
//				}
//				break;
//			case "Ast":
//				if (astGroupedVotes.containsKey(stringVoteId)) {
//					astGroupedVotes.put(stringVoteId, astGroupedVotes.get(stringVoteId) + 1);
//				} else {
//					astGroupedVotes.put(stringVoteId, 1);
//				}
//				break;
//			case "Nav":
//				if (navGroupedVotes.containsKey(stringVoteId)) {
//					navGroupedVotes.put(stringVoteId, navGroupedVotes.get(stringVoteId) + 1);
//				} else {
//					navGroupedVotes.put(stringVoteId, 1);
//				}
//				break;
//			case "Rio":
//				if (rioGroupedVotes.containsKey(stringVoteId)) {
//					rioGroupedVotes.put(stringVoteId, rioGroupedVotes.get(stringVoteId) + 1);
//				} else {
//					rioGroupedVotes.put(stringVoteId, 1);
//				}
//				break;
//			case "Ceu":
//				if (ceuGroupedVotes.containsKey(stringVoteId)) {
//					ceuGroupedVotes.put(stringVoteId, ceuGroupedVotes.get(stringVoteId) + 1);
//				} else {
//					ceuGroupedVotes.put(stringVoteId, 1);
//				}
//				break;
//			default: // "Mel":
//				if (melGroupedVotes.containsKey(stringVoteId)) {
//					melGroupedVotes.put(stringVoteId, melGroupedVotes.get(stringVoteId) + 1);
//				} else {
//					melGroupedVotes.put(stringVoteId, 1);
//				}
//			}
//		}
//
//		List<String> stringGroupedVotes = new ArrayList<String>();
//
//		for (int i = 0; i < votesId.size(); i++) {
//			Integer numberAndGroupedVotes;
//			Integer numberCatGroupedVotes;
//			Integer numberComMadGroupedVotes;
//			Integer numberComValGroupedVotes;
//			Integer numberGalGroupedVotes;
//			Integer numberCasLeoGroupedVotes;
//			Integer numberPaiVasGroupedVotes;
//			Integer numberCanGroupedVotes;
//			Integer numberCasManGroupedVotes;
//			Integer numberRegMurGroupedVotes;
//			Integer numberAraGroupedVotes;
//			Integer numberIslBarGroupedVotes;
//			Integer numberExtGroupedVotes;
//			Integer numberAstGroupedVotes;
//			Integer numberNavGroupedVotes;
//			Integer numberRioGroupedVotes;
//			Integer numberCeuGroupedVotes;
//			Integer numberMelGroupedVotes;
//
//			numberAndGroupedVotes = andGroupedVotes.get(votesId.get(i).toString());
//			if (numberAndGroupedVotes == null) {
//				numberAndGroupedVotes = 0;
//			}
//			numberCatGroupedVotes = catGroupedVotes.get(votesId.get(i).toString());
//			if (numberCatGroupedVotes == null) {
//				numberCatGroupedVotes = 0;
//			}
//			numberComMadGroupedVotes = comMadGroupedVotes.get(votesId.get(i).toString());
//			if (numberComMadGroupedVotes == null) {
//				numberComMadGroupedVotes = 0;
//			}
//			numberComValGroupedVotes = comValGroupedVotes.get(votesId.get(i).toString());
//			if (numberComValGroupedVotes == null) {
//				numberComValGroupedVotes = 0;
//			}
//			numberGalGroupedVotes = galGroupedVotes.get(votesId.get(i).toString());
//			if (numberGalGroupedVotes == null) {
//				numberGalGroupedVotes = 0;
//			}
//			numberCasLeoGroupedVotes = casLeoGroupedVotes.get(votesId.get(i).toString());
//			if (numberCasLeoGroupedVotes == null) {
//				numberCasLeoGroupedVotes = 0;
//			}
//			numberPaiVasGroupedVotes = paiVasGroupedVotes.get(votesId.get(i).toString());
//			if (numberPaiVasGroupedVotes == null) {
//				numberPaiVasGroupedVotes = 0;
//			}
//			numberCanGroupedVotes = canGroupedVotes.get(votesId.get(i).toString());
//			if (numberCanGroupedVotes == null) {
//				numberCanGroupedVotes = 0;
//			}
//			numberCasManGroupedVotes = casManGroupedVotes.get(votesId.get(i).toString());
//			if (numberCasManGroupedVotes == null) {
//				numberCasManGroupedVotes = 0;
//			}
//			numberRegMurGroupedVotes = regMurGroupedVotes.get(votesId.get(i).toString());
//			if (numberRegMurGroupedVotes == null) {
//				numberRegMurGroupedVotes = 0;
//			}
//			numberAraGroupedVotes = araGroupedVotes.get(votesId.get(i).toString());
//			if (numberAraGroupedVotes == null) {
//				numberAraGroupedVotes = 0;
//			}
//			numberIslBarGroupedVotes = islBarGroupedVotes.get(votesId.get(i).toString());
//			if (numberIslBarGroupedVotes == null) {
//				numberIslBarGroupedVotes = 0;
//			}
//			numberExtGroupedVotes = extGroupedVotes.get(votesId.get(i).toString());
//			if (numberExtGroupedVotes == null) {
//				numberExtGroupedVotes = 0;
//			}
//			numberAstGroupedVotes = astGroupedVotes.get(votesId.get(i).toString());
//			if (numberAstGroupedVotes == null) {
//				numberAstGroupedVotes = 0;
//			}
//			numberNavGroupedVotes = navGroupedVotes.get(votesId.get(i).toString());
//			if (numberNavGroupedVotes == null) {
//				numberNavGroupedVotes = 0;
//			}
//			numberRioGroupedVotes = rioGroupedVotes.get(votesId.get(i).toString());
//			if (numberRioGroupedVotes == null) {
//				numberRioGroupedVotes = 0;
//			}
//			numberCeuGroupedVotes = ceuGroupedVotes.get(votesId.get(i).toString());
//			if (numberCeuGroupedVotes == null) {
//				numberCeuGroupedVotes = 0;
//			}
//			numberMelGroupedVotes = melGroupedVotes.get(votesId.get(i).toString());
//			if (numberMelGroupedVotes == null) {
//				numberMelGroupedVotes = 0;
//			}
//			stringGroupedVotes.add(
//					"id=" + votesId.get(i) + ";countAnd=" + numberAndGroupedVotes + ";countCat=" + numberCatGroupedVotes
//							+ ";countComMad=" + numberComMadGroupedVotes + ";countComVal=" + numberComValGroupedVotes
//							+ ";countGal=" + numberGalGroupedVotes + ";countCasLeo=" + numberCasLeoGroupedVotes
//							+ ";countPaiVas=" + numberPaiVasGroupedVotes + ";countCan=" + numberCanGroupedVotes
//							+ ";countCasMan=" + numberCasManGroupedVotes + ";countRegMur=" + numberRegMurGroupedVotes
//							+ ";countAra=" + numberAraGroupedVotes + ";countIslBal=" + numberIslBarGroupedVotes
//							+ ";countExt=" + numberExtGroupedVotes + ";countAst=" + numberAstGroupedVotes + ";countNav="
//							+ numberNavGroupedVotes + ";countRio=" + numberRioGroupedVotes + ";countCeu="
//							+ numberCeuGroupedVotes + ";countMel=" + numberMelGroupedVotes);
//		}
//
//		countedPoll.put(poll.keySet().iterator().next(), stringGroupedVotes);
//
//		return countedPoll;
//	}

//	@SuppressWarnings("unchecked")
//	public static Map<String, Object> dhontCount(Map<String, Object> poll) {
//		List<String> stringVotes = new ArrayList<String>();
//		stringVotes = (List<String>) poll.values().iterator().next();
//		Integer escaños = Integer.parseInt(stringVotes.get(stringVotes.size() - 1));
//		stringVotes.remove(stringVotes.size() - 1);
//		poll.put(poll.keySet().iterator().next(), stringVotes);
//		List<StoredVote> votes = decodeVotes(poll.values());
//		Map<String, Integer> groupedVotes = new HashMap<String, Integer>();
//		votes = decodeVotes(poll.values());
//		Integer totalVotos = votes.size();
//		for (StoredVote vote : votes) {
//			if (groupedVotes.containsKey(vote.getId())) {
//				groupedVotes.put(vote.getId(), groupedVotes.get(vote.getId()) + 1);
//			} else {
//				groupedVotes.put(vote.getId(), 1);
//			}
//		}
//		for (String s : groupedVotes.keySet()) {
//			if (groupedVotes.get(s) < totalVotos * 0.03) {
//				groupedVotes.remove(s);
//			}
//		}
//		Map<String, Collection<Integer>> tablaCocientes = new HashMap<String, Collection<Integer>>();
//
//		for (int i = 1; i <= escaños; i++) {
//			for (String s : groupedVotes.keySet()) {
//				if (tablaCocientes.containsKey(s)) {
//					Collection<Integer> cocientes = tablaCocientes.get(s);
//					cocientes.add(groupedVotes.get(s) / i);
//					tablaCocientes.put(s, cocientes);
//				} else {
//					Collection<Integer> cocientes = new TreeSet<Integer>();
//					cocientes.add(groupedVotes.get(s) / i);
//					tablaCocientes.put(s, cocientes);
//				}
//			}
//		}
//		Map<String, Integer> escañoPorPartido = new HashMap<String, Integer>();
//		SortedSet<Integer> sortedSet = new TreeSet<Integer>();
//		for (Collection<Integer> c : tablaCocientes.values()) {
//			sortedSet.addAll(c);
//		}
//
//		List<Integer> l = new ArrayList<Integer>();
//		for (Integer i : sortedSet) {
//			l.add(i);
//		}
//		int a = l.size() - 1;
//		for (Integer i = escaños; i > 0; i--) {
//			Integer alto = l.get(a);
//			a--;
//			for (String s : tablaCocientes.keySet()) {
//				if (tablaCocientes.get(s).contains(alto)) {
//					if (escañoPorPartido.containsKey(s)) {
//						escañoPorPartido.put(s, escañoPorPartido.get(s) + 1);
//					} else {
//						escañoPorPartido.put(s, 1);
//					}
//				}
//			}
//		}
//		List<String> stringResList = new ArrayList<String>();
//		for (String id : escañoPorPartido.keySet()) {
//			stringResList.add("id=" + id + ";count=" + escañoPorPartido.get(id));
//		}
//		Map<String, Object> res = new HashMap<String, Object>();
//		res.put(poll.keySet().iterator().next(), stringResList);
//		return res;
//
//	}
	public static UsualAgeRangesCount usualAgeRangesCount(int pollId) {
		UsualAgeRangesCount count;
		JSONObject storageEncryptedAnswer;
		JSONObject storageDecryptedAnswer;
		List<StoredVote> storageVotes;
		List<UsualAgeRangesVote> countedVotes;
		List<Integer> countedVotesIds;
		
		count = new UsualAgeRangesCount();
		storageVotes = new ArrayList<StoredVote>();
		countedVotes = new ArrayList<UsualAgeRangesVote>();
		countedVotesIds = new ArrayList<Integer>();
		storageEncryptedAnswer = null;
		storageDecryptedAnswer = null;
		
		count.setStatus(Status.SUCCESS.getMessage());
		count.setPollId(pollId);
		
		// Geting Json answer from Storage subsystem.
		try {
			storageEncryptedAnswer = StorageService.getVotesByPollId(pollId);
		} catch (Exception e) {
			e.printStackTrace();
			count.setStatus(Status.STORAGE_CONNECTION_ERROR.getMessage());
			return count;
		}
		
		// Decrypting storaged answer by Verification subsystem
		try {
			storageDecryptedAnswer = VerificationService.decryptStorageAnswer(storageEncryptedAnswer);
		} catch (Exception e) {
			e.printStackTrace();
			count.setStatus(Status.VERIFICATION_CONNECTION_ERROR.getMessage());
			return count;
		}
		
		// Decoding StoredVotes from Json answer
		try {
			storageVotes = fromJsonToStoredVotes(storageDecryptedAnswer);
		} catch (Exception e) {
			e.printStackTrace();
			count.setStatus(Status.INVALID_JSON_FORMAT.getMessage());
			return count;
		}
		
		// Counting votes
		for (StoredVote storedVote : storageVotes) {
			if (countedVotesIds.contains(storedVote.getId())){
				for (UsualAgeRangesVote vote : countedVotes) {
					if (vote.getId() == storedVote.getId()) {
						if(storedVote.getAge()<30){
						vote.increaseCountUnder30(1);
						break;
						}else if(storedVote.getAge()<=60){
						vote.increaseCount30To60(1);
						break;
						}
						else{
							vote.increaseCountOver60(1);
							break;
						}
					}
				}
			} else {
				if(storedVote.getAge()<30){
				countedVotes.add(new UsualAgeRangesVote(storedVote.getId(), 1,0,0));
				}else if(storedVote.getAge()<=60){
					countedVotes.add(new UsualAgeRangesVote(storedVote.getId(), 0,1,0));
				}else{
					countedVotes.add(new UsualAgeRangesVote(storedVote.getId(), 0,0,1));
				}
				countedVotesIds.add(storedVote.getId());
			}
		}
		
		count.setVotes(countedVotes);

		return count;
		
	}
//	@SuppressWarnings("unchecked")
//	public static Map<String, Object> cocienteDroopCount(Map<String, Object> poll) {
//		List<String> stringVotes = new ArrayList<String>();
//		stringVotes = (List<String>) poll.values().iterator().next();
//		Integer escaños = Integer.parseInt(stringVotes.get(stringVotes.size() - 1));
//		stringVotes.remove(stringVotes.size() - 1);
//		poll.put(poll.keySet().iterator().next(), stringVotes);
//		List<StoredVote> votes = decodeVotes(poll.values());
//		Map<String, Integer> groupedVotes = new HashMap<String, Integer>();
//		Map<String, Integer> cocientes = new HashMap<String, Integer>();
//		Map<Integer, String> restos = new HashMap<Integer, String>();
//		votes = decodeVotes(poll.values());
//		Integer totalVotos = votes.size();
//
//		Integer cuota = 1 + (totalVotos / (escaños + 1));
//
//		for (StoredVote vote : votes) {
//			if (groupedVotes.containsKey(vote.getId())) {
//				groupedVotes.put(vote.getId(), groupedVotes.get(vote.getId()) + 1);
//			} else {
//				groupedVotes.put(vote.getId(), 1);
//			}
//		}
//		Integer escañosAsignados = 0;
//
//		for (String s : groupedVotes.keySet()) {
//			Integer cociente = groupedVotes.get(s) / cuota;
//			escañosAsignados += cociente;
//			cocientes.put(s, cociente);
//			restos.put(groupedVotes.get(s) % cuota, s);
//		}
//		if (escañosAsignados < escaños) {
//			SortedSet<Integer> sortedSet = new TreeSet<Integer>();
//			sortedSet.addAll(restos.keySet());
//
//			List<Integer> l = new ArrayList<Integer>();
//			for (Integer i : sortedSet) {
//				l.add(i);
//			}
//
//			Integer puntero = l.size() - 1;
//			while (escañosAsignados < escaños) {
//				Integer alto = l.get(puntero);
//				String id = restos.get(alto);
//				cocientes.put(id, cocientes.get(id) + 1);
//				escañosAsignados++;
//				puntero--;
//			}
//		}
//
//		Map<String, Object> res = new HashMap<String, Object>();
//		List<String> stringResList = new ArrayList<String>();
//		for (String s : cocientes.keySet()) {
//			stringResList.add("id=" + s + ";count=" + cocientes.get(s));
//		}
//		res.put(poll.keySet().iterator().next(), stringResList);
//		return res;
//	}
//
//	@SuppressWarnings("unchecked")
//	public static Map<String, Object> metodoSainteLague(Map<String, Object> poll) {
//		List<String> stringVotes = new ArrayList<String>();
//		stringVotes = (List<String>) poll.values().iterator().next();
//		Integer escaños = Integer.parseInt(stringVotes.get(stringVotes.size() - 1));
//		stringVotes.remove(stringVotes.size() - 1);
//		poll.put(poll.keySet().iterator().next(), stringVotes);
//		List<StoredVote> votes = decodeVotes(poll.values());
//		Map<String, Integer> groupedVotes = new HashMap<String, Integer>();
//		votes = decodeVotes(poll.values());
//		Integer totalVotos = votes.size();
//		for (StoredVote vote : votes) {
//			if (groupedVotes.containsKey(vote.getId())) {
//				groupedVotes.put(vote.getId(), groupedVotes.get(vote.getId()) + 1);
//			} else {
//				groupedVotes.put(vote.getId(), 1);
//			}
//		}
//		for (String s : groupedVotes.keySet()) {
//			if (groupedVotes.get(s) < totalVotos * 0.03) {
//				groupedVotes.remove(s);
//			}
//		}
//		Map<String, Collection<Integer>> tablaCocientes = new HashMap<String, Collection<Integer>>();
//		List<Integer> impares = new ArrayList<Integer>();
//		impares.add(1);
//		impares.add(3);
//		impares.add(5);
//		impares.add(7);
//		impares.add(9);
//		impares.add(11);
//		impares.add(13);
//		
//		for (Integer i:impares) {
//			for (String s : groupedVotes.keySet()) {
//				if (tablaCocientes.containsKey(s)) {
//					Collection<Integer> cocientes = tablaCocientes.get(s);
//					cocientes.add(groupedVotes.get(s) / i);
//					tablaCocientes.put(s, cocientes);
//				} else {
//					Collection<Integer> cocientes = new TreeSet<Integer>();
//					cocientes.add(groupedVotes.get(s) / i);
//					tablaCocientes.put(s, cocientes);
//				}
//			}
//		}
//		Map<String, Integer> escañoPorPartido = new HashMap<String, Integer>();
//		SortedSet<Integer> sortedSet = new TreeSet<Integer>();
//		for (Collection<Integer> c : tablaCocientes.values()) {
//			sortedSet.addAll(c);
//		}
//
//		List<Integer> l = new ArrayList<Integer>();
//		for (Integer i : sortedSet) {
//			l.add(i);
//		}
//		int a = l.size() - 1;
//		for (Integer i = escaños; i > 0; i--) {
//			Integer alto = l.get(a);
//			a--;
//			for (String s : tablaCocientes.keySet()) {
//				if (tablaCocientes.get(s).contains(alto)) {
//					if (escañoPorPartido.containsKey(s)) {
//						escañoPorPartido.put(s, escañoPorPartido.get(s) + 1);
//					} else {
//						escañoPorPartido.put(s, 1);
//					}
//				}
//			}
//		}
//		List<String> stringResList = new ArrayList<String>();
//		for (String id : escañoPorPartido.keySet()) {
//			stringResList.add("id=" + id + ";count=" + escañoPorPartido.get(id));
//		}
//		Map<String, Object> res = new HashMap<String, Object>();
//		res.put(poll.keySet().iterator().next(), stringResList);
//		return res;
//
//	}
}
