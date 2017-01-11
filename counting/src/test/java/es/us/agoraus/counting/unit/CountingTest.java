package es.us.agoraus.counting.unit;

import org.junit.Assert;
import org.junit.Test;

import es.us.agoraus.counting.algorithms.CountingFactory;
import es.us.agoraus.counting.dto.AgeMayorityCount;
import es.us.agoraus.counting.dto.AgeMayorityVote;
import es.us.agoraus.counting.dto.BasicCount;
import es.us.agoraus.counting.dto.BasicVote;
import es.us.agoraus.counting.dto.GenderCount;
import es.us.agoraus.counting.dto.GenderVote;
import es.us.agoraus.counting.dto.UsualAgeRangesCount;
import es.us.agoraus.counting.dto.UsualAgeRangesVote;

public class CountingTest {

	/*
	 * It checks that a basicCount from an example poll is done correctly by
	 * checking all the BasicCount final parameters.
	 */
	@Test
	public void testBasicCount() {
		// Declaration
		int pollId;
		BasicCount result = new BasicCount();

		// Data charge
		pollId = 1;

		// Testing
		result = CountingFactory.basicCount(pollId);

		// Checking
		Assert.assertTrue(result.getStatus().equals("OK."));
		Assert.assertTrue(result.getPollId() == 1);
		Assert.assertTrue(result.getVotes().size() == 2);
		boolean foundVote1 = false;
		boolean foundVote2 = false;
		for (BasicVote basicVote : result.getVotes()){
			if (basicVote.getId() == 31 && basicVote.getCount() == 2) {
				foundVote1 = true;
			}
			if (basicVote.getId() == 32 && basicVote.getCount() == 1) {
				foundVote2 = true;
			}
		}
		Assert.assertTrue(foundVote1 && foundVote2);
	}

	/*
	 * It checks that a ageMayorityCount from an example poll is done correctly
	 * by reading the toString format of the result object
	 */

	
	@Test
	public void testAgeMayorityCount() {
		// Declaration
		int pollId;
		AgeMayorityCount result = new AgeMayorityCount();

		// Data charge
		pollId = 1;

		// Testing
		result = CountingFactory.ageMayorityCount(pollId);

		// Checking
		Assert.assertTrue(result.getStatus().equals("OK."));
		Assert.assertTrue(result.getPollId() == 1);
		Assert.assertTrue(result.getAgeMayorityVotes().size() == 2);
		boolean foundVote1 = false;
		boolean foundVote2 = false;
		for (AgeMayorityVote ageMayorityVote : result.getAgeMayorityVotes()){
			if (ageMayorityVote.getId() == 31 && ageMayorityVote.getMayorityCount() == 1 && ageMayorityVote.getMinorsCount()==1) {
				foundVote1 = true;
			}
			if (ageMayorityVote.getId() == 32 && ageMayorityVote.getMayorityCount() == 1 && ageMayorityVote.getMinorsCount()==0) {
				foundVote2 = true;
			}
		}
		Assert.assertTrue(foundVote1 && foundVote2);
	}

	@Test
    public void testGenderCount() {
        // Declaration
        int pollId;
        GenderCount result = new GenderCount();

        // Data charge
        pollId = 1;

        // Testing
        result = CountingFactory.genderCount(pollId);

        // Checking
        Assert.assertTrue(result.getStatus().equals("OK."));
        Assert.assertTrue(result.getPollId() == 1);
        Assert.assertTrue(result.getVotes().size() == 2);
        boolean foundVote1 = false;
        boolean foundVote2 = false;
        for (GenderVote genderVote : result.getVotes()){
            if (genderVote.getId() == 31 && genderVote.getFemaleCount() == 1 && genderVote.getMaleCount() == 1) {
                foundVote1 = true;
            }
            if (genderVote.getId() == 32 && genderVote.getFemaleCount() == 1) {
                foundVote2 = true;
            }
        }
        Assert.assertTrue(foundVote1 && foundVote2);
    }
	

	/*
	 * It checks that a ageRangeCount from an example poll is done correctly by
	 * reading the toString format of the result object
	 */
//	@Test
//	public void testAgeRangeCount() {
//		// Declaration
//		Map<String, Object> poll = new HashMap<String, Object>();
//		List<String> votes = new ArrayList<String>();
//		Map<String, Object> result = new HashMap<String, Object>();
//
//		// Data charge
//		String pollId = "12345ABC";
//
//		votes.add("id=1;edad=16;genero=M;comunidad=And");
//		votes.add("id=2;edad=17;genero=F;comunidad=ComMad");
//		votes.add("id=1;edad=22;genero=M;comunidad=And");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=1;edad=22;genero=M;comunidad=RegMur");
//
//		poll.put(pollId, votes);
//
//		// Testing
//		result = CountingFactory.ageRangeCount(poll, 20, 23);
//
//		// Checking
//		Assert.assertTrue(result.toString().equals(
//				"{12345ABC=[id=1;countRange=2;countExcluded=1, id=2;countRange=0;countExcluded=1, id=3;countRange=1;countExcluded=0]}"));
//	}

	/*
	 * It checks that a genderCount from an example poll is done correctly by
	 * reading the toString format of the result object
	 */
//	@Test
//	public void testGenderCount() {
//		// Declaration
//		Map<String, Object> poll = new HashMap<String, Object>();
//		List<String> votes = new ArrayList<String>();
//		Map<String, Object> result = new HashMap<String, Object>();
//
//		// Data charge
//		String pollId = "12345ABC";
//
//		votes.add("id=1;edad=16;genero=M;comunidad=And");
//		votes.add("id=2;edad=17;genero=F;comunidad=ComMad");
//		votes.add("id=1;edad=22;genero=M;comunidad=And");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=1;edad=22;genero=M;comunidad=RegMur");
//
//		poll.put(pollId, votes);
//
//		// Testing
//		result = CountingFactory.genderCount(poll);
//
//		// Checking
//		Assert.assertTrue(result.toString().equals(
//				"{12345ABC=[id=1;countFemale=0;countMale=3, id=2;countFemale=1;countMale=0, id=3;countFemale=1;countMale=0]}"));
//	}

	/*
	 * It checks that a communityCount from an example poll is done correctly by
	 * reading the toString format of the result object
	 */
//	@Test
//	public void testCommunityCount() {
//		// Declaration
//		Map<String, Object> poll = new HashMap<String, Object>();
//		List<String> votes = new ArrayList<String>();
//		Map<String, Object> result = new HashMap<String, Object>();
//
//		// Data charge
//		String pollId = "12345ABC";
//
//		votes.add("id=1;edad=16;genero=M;comunidad=And");
//		votes.add("id=2;edad=17;genero=F;comunidad=ComMad");
//		votes.add("id=1;edad=22;genero=M;comunidad=And");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=1;edad=22;genero=M;comunidad=RegMur");
//
//		poll.put(pollId, votes);
//
//		// Testing
//		result = CountingFactory.communityCount(poll);
//
//		// Checking
//		Assert.assertTrue(result.toString().equals(
//				"{12345ABC=[id=1;countAnd=2;countCat=0;countComMad=0;countComVal=0;countGal=0;countCasLeo=0;countPaiVas=0;countCan=0;countCasMan=0;countRegMur=1;countAra=0;countIslBal=0;countExt=0;countAst=0;countNav=0;countRio=0;countCeu=0;countMel=0, id=2;countAnd=0;countCat=0;countComMad=1;countComVal=0;countGal=0;countCasLeo=0;countPaiVas=0;countCan=0;countCasMan=0;countRegMur=0;countAra=0;countIslBal=0;countExt=0;countAst=0;countNav=0;countRio=0;countCeu=0;countMel=0, id=3;countAnd=0;countCat=1;countComMad=0;countComVal=0;countGal=0;countCasLeo=0;countPaiVas=0;countCan=0;countCasMan=0;countRegMur=0;countAra=0;countIslBal=0;countExt=0;countAst=0;countNav=0;countRio=0;countCeu=0;countMel=0]}"));
//	}
//
//	@Test
//	public void testCocienteDroop() {
//
//		Map<String, Object> poll = new HashMap<String, Object>();
//		String pollId = "12345ABC";
//		List<String> votes = new ArrayList<String>();
//		votes.add("id=1;edad=26;genero=M;comunidad=And");
//		votes.add("id=1;edad=26;genero=M;comunidad=And");
//		votes.add("id=1;edad=46;genero=M;comunidad=And");
//		votes.add("id=1;edad=66;genero=M;comunidad=And");
//		votes.add("id=1;edad=86;genero=M;comunidad=And");
//		votes.add("id=1;edad=76;genero=M;comunidad=And");
//		votes.add("id=1;edad=66;genero=M;comunidad=And");
//		votes.add("id=1;edad=46;genero=M;comunidad=And");
//		votes.add("id=1;edad=36;genero=M;comunidad=And");
//		votes.add("id=1;edad=26;genero=M;comunidad=And");
//		votes.add("id=1;edad=26;genero=M;comunidad=And");
//		votes.add("id=1;edad=36;genero=M;comunidad=And");
//		votes.add("id=1;edad=26;genero=M;comunidad=And");
//		votes.add("id=1;edad=56;genero=M;comunidad=And");
//		votes.add("id=1;edad=46;genero=M;comunidad=And");
//		votes.add("id=1;edad=76;genero=M;comunidad=And");
//		votes.add("id=1;edad=36;genero=M;comunidad=And");
//		votes.add("id=1;edad=26;genero=M;comunidad=And");
//		votes.add("id=1;edad=26;genero=M;comunidad=And");
//		votes.add("id=1;edad=56;genero=M;comunidad=And");
//		votes.add("id=1;edad=46;genero=M;comunidad=And");
//		votes.add("id=1;edad=36;genero=M;comunidad=And");
//		votes.add("id=1;edad=56;genero=M;comunidad=And");
//		votes.add("id=1;edad=46;genero=M;comunidad=And");
//		votes.add("id=1;edad=36;genero=M;comunidad=And");
//		votes.add("id=1;edad=56;genero=M;comunidad=And");
//		votes.add("id=1;edad=36;genero=M;comunidad=And");
//		votes.add("id=1;edad=36;genero=M;comunidad=And");
//		votes.add("id=1;edad=46;genero=M;comunidad=And");
//		votes.add("id=1;edad=56;genero=M;comunidad=And");
//		votes.add("id=2;edad=27;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=37;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=27;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=77;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=67;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=57;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=47;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=37;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=27;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=77;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=87;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=37;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=57;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=47;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=37;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=27;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=57;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=67;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=47;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=37;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=27;genero=F;comunidad=ComMad");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//
//		votes.add("5");
//		poll.put(pollId, votes);
//		System.out.println("Cociente Droop: "+ CountingFactory.cocienteDroopCount(poll));
//	}
//	
//	@Test
//	public void testMetodoSainteLague() {
//		Map<String, Object> poll = new HashMap<String, Object>();
//		String pollId = "12345ABC";
//		List<String> votes = new ArrayList<String>();
//		votes.add("id=1;edad=26;genero=M;comunidad=And");
//		votes.add("id=1;edad=26;genero=M;comunidad=And");
//		votes.add("id=1;edad=46;genero=M;comunidad=And");
//		votes.add("id=1;edad=66;genero=M;comunidad=And");
//		votes.add("id=1;edad=86;genero=M;comunidad=And");
//		votes.add("id=1;edad=76;genero=M;comunidad=And");
//		votes.add("id=1;edad=66;genero=M;comunidad=And");
//		votes.add("id=1;edad=46;genero=M;comunidad=And");
//		votes.add("id=1;edad=36;genero=M;comunidad=And");
//		votes.add("id=1;edad=26;genero=M;comunidad=And");
//		votes.add("id=1;edad=26;genero=M;comunidad=And");
//		votes.add("id=1;edad=36;genero=M;comunidad=And");
//		votes.add("id=1;edad=26;genero=M;comunidad=And");
//		votes.add("id=1;edad=56;genero=M;comunidad=And");
//		votes.add("id=1;edad=46;genero=M;comunidad=And");
//		votes.add("id=1;edad=76;genero=M;comunidad=And");
//		votes.add("id=1;edad=36;genero=M;comunidad=And");
//		votes.add("id=1;edad=26;genero=M;comunidad=And");
//		votes.add("id=1;edad=26;genero=M;comunidad=And");
//		votes.add("id=1;edad=56;genero=M;comunidad=And");
//		votes.add("id=1;edad=46;genero=M;comunidad=And");
//		votes.add("id=1;edad=36;genero=M;comunidad=And");
//		votes.add("id=1;edad=56;genero=M;comunidad=And");
//		votes.add("id=1;edad=46;genero=M;comunidad=And");
//		votes.add("id=1;edad=36;genero=M;comunidad=And");
//		votes.add("id=1;edad=56;genero=M;comunidad=And");
//		votes.add("id=1;edad=36;genero=M;comunidad=And");
//		votes.add("id=1;edad=36;genero=M;comunidad=And");
//		votes.add("id=1;edad=46;genero=M;comunidad=And");
//		votes.add("id=1;edad=56;genero=M;comunidad=And");
//		votes.add("id=2;edad=27;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=37;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=27;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=77;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=67;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=57;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=47;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=37;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=27;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=77;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=87;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=37;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=57;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=47;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=37;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=27;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=57;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=67;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=47;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=37;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=27;genero=F;comunidad=ComMad");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//
//		votes.add("5");
//		poll.put(pollId, votes);
//		System.out.println("Método sainteLague: "+ CountingFactory.metodoSainteLague(poll));
//	}
//	@Test
//	public void testMetodoDHont() {
//		Map<String, Object> poll = new HashMap<String, Object>();
//		String pollId = "12345ABC";
//		List<String> votes = new ArrayList<String>();
//		votes.add("id=1;edad=26;genero=M;comunidad=And");
//		votes.add("id=1;edad=26;genero=M;comunidad=And");
//		votes.add("id=1;edad=46;genero=M;comunidad=And");
//		votes.add("id=1;edad=66;genero=M;comunidad=And");
//		votes.add("id=1;edad=86;genero=M;comunidad=And");
//		votes.add("id=1;edad=76;genero=M;comunidad=And");
//		votes.add("id=1;edad=66;genero=M;comunidad=And");
//		votes.add("id=1;edad=46;genero=M;comunidad=And");
//		votes.add("id=1;edad=36;genero=M;comunidad=And");
//		votes.add("id=1;edad=26;genero=M;comunidad=And");
//		votes.add("id=1;edad=26;genero=M;comunidad=And");
//		votes.add("id=1;edad=36;genero=M;comunidad=And");
//		votes.add("id=1;edad=26;genero=M;comunidad=And");
//		votes.add("id=1;edad=56;genero=M;comunidad=And");
//		votes.add("id=1;edad=46;genero=M;comunidad=And");
//		votes.add("id=1;edad=76;genero=M;comunidad=And");
//		votes.add("id=1;edad=36;genero=M;comunidad=And");
//		votes.add("id=1;edad=26;genero=M;comunidad=And");
//		votes.add("id=1;edad=26;genero=M;comunidad=And");
//		votes.add("id=1;edad=56;genero=M;comunidad=And");
//		votes.add("id=1;edad=46;genero=M;comunidad=And");
//		votes.add("id=1;edad=36;genero=M;comunidad=And");
//		votes.add("id=1;edad=56;genero=M;comunidad=And");
//		votes.add("id=1;edad=46;genero=M;comunidad=And");
//		votes.add("id=1;edad=36;genero=M;comunidad=And");
//		votes.add("id=1;edad=56;genero=M;comunidad=And");
//		votes.add("id=1;edad=36;genero=M;comunidad=And");
//		votes.add("id=1;edad=36;genero=M;comunidad=And");
//		votes.add("id=1;edad=46;genero=M;comunidad=And");
//		votes.add("id=1;edad=56;genero=M;comunidad=And");
//		votes.add("id=2;edad=27;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=37;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=27;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=77;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=67;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=57;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=47;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=37;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=27;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=77;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=87;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=37;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=57;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=47;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=37;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=27;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=57;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=67;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=47;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=37;genero=F;comunidad=ComMad");
//		votes.add("id=2;edad=27;genero=F;comunidad=ComMad");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
//
//		votes.add("5");
//		poll.put(pollId, votes);
//		System.out.println("Método d'Hont : "+ CountingFactory.dhontCount(poll));
//	}
	
	@Test
	public void testUsualAgeRangesCount() {
		// Declaration
		int pollId;
		UsualAgeRangesCount result = new UsualAgeRangesCount();

		// Data charge
		pollId = 1;

		// Testing
		result = CountingFactory.usualAgeRangesCount(pollId);

		// Checking
		Assert.assertTrue(result.getStatus().equals("OK."));
		Assert.assertTrue(result.getPollId() == 1);
		Assert.assertTrue(result.getUsualAgeRangesVotes().size() == 2);
		boolean foundVote1 = false;
		boolean foundVote2 = false;
		for (UsualAgeRangesVote usualAgeRangesVote : result.getUsualAgeRangesVotes()){
			if (usualAgeRangesVote.getId() == 31 && usualAgeRangesVote.getCountUnder30() == 1 && usualAgeRangesVote.getCount30To60() == 1 && usualAgeRangesVote.getCountOver60() == 0) {
				foundVote1 = true;
			}
			if (usualAgeRangesVote.getId() == 32 && usualAgeRangesVote.getCountUnder30() == 0 && usualAgeRangesVote.getCount30To60() == 1 && usualAgeRangesVote.getCountOver60() == 0) {
				foundVote2 = true;
			}
			
		}
		Assert.assertTrue(foundVote1 && foundVote2);
	}
}
