package es.us.agoraus.counting.controllers;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.us.agoraus.counting.algorithms.CountingFactory;

@RestController
@RequestMapping(value = "/count")
public class ApiController{

	
//	@RequestMapping("/testVotes")
//	public void votes() {
//		final ModelAndView model = new ModelAndView("non-segmented-charts");
//		
//		Map<String, Object> poll = new HashMap<String, Object>();
//		
//		String pollId = "12345ABC";
//		List<String> votes = new ArrayList<String>();
//		
//		votes.add("id=1;edad=20;genero=M;comunidad=Andalucía");
//		votes.add("id=2;edad=21;genero=F;comunidad=Madrid");
//		votes.add("id=1;edad=22;genero=M;comunidad=Andalucía");
//		votes.add("id=3;edad=20;genero=F;comunidad=Andalucía");
//		votes.add("id=1;edad=22;genero=M;comunidad=Andalucía");
//		
//		poll.put(pollId, votes);
//		
//		
//		
//	}
	
	@RequestMapping("/test")
	public void votes() {
		
		Map<String, Object> poll = new HashMap<String, Object>();
		
		String pollId = "12345ABC";
		List<String> votes = new ArrayList<String>();
		
		votes.add("id=1;edad=16;genero=M;comunidad=And");
		votes.add("id=2;edad=17;genero=F;comunidad=ComMad");
		votes.add("id=1;edad=22;genero=M;comunidad=And");
		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
		votes.add("id=1;edad=22;genero=M;comunidad=RegMur");
		
		poll.put(pollId, votes);
		
		System.out.println( CountingFactory.basicCount(poll) );
		System.out.println( CountingFactory.ageMayorityCount(poll) );
		System.out.println( CountingFactory.ageRangeCount(poll, 20, 23) );
		System.out.println( CountingFactory.genderCount(poll) );
		System.out.println( CountingFactory.communityCount(poll) );
		
		
	}
	
	
	@RequestMapping("/test2")
	public void votes2() {
		
		Map<String, Object> poll = new HashMap<String, Object>();
		
		String pollId = "12345ABC";
		List<String> votes = new ArrayList<String>();
		votes.add("id=1;edad=26;genero=M;comunidad=And");
		votes.add("id=1;edad=26;genero=M;comunidad=And");
		votes.add("id=1;edad=46;genero=M;comunidad=And");
		votes.add("id=1;edad=66;genero=M;comunidad=And");
		votes.add("id=1;edad=86;genero=M;comunidad=And");
		votes.add("id=1;edad=76;genero=M;comunidad=And");
		votes.add("id=1;edad=66;genero=M;comunidad=And");
		votes.add("id=1;edad=46;genero=M;comunidad=And");
		votes.add("id=1;edad=36;genero=M;comunidad=And");
		votes.add("id=1;edad=26;genero=M;comunidad=And");
		votes.add("id=1;edad=26;genero=M;comunidad=And");
		votes.add("id=1;edad=36;genero=M;comunidad=And");
		votes.add("id=1;edad=26;genero=M;comunidad=And");
		votes.add("id=1;edad=56;genero=M;comunidad=And");
		votes.add("id=1;edad=46;genero=M;comunidad=And");
		votes.add("id=1;edad=76;genero=M;comunidad=And");
		votes.add("id=1;edad=36;genero=M;comunidad=And");
		votes.add("id=1;edad=26;genero=M;comunidad=And");
		votes.add("id=1;edad=26;genero=M;comunidad=And");
		votes.add("id=1;edad=56;genero=M;comunidad=And");
		votes.add("id=1;edad=46;genero=M;comunidad=And");
		votes.add("id=1;edad=36;genero=M;comunidad=And");
		votes.add("id=1;edad=56;genero=M;comunidad=And");
		votes.add("id=1;edad=46;genero=M;comunidad=And");
		votes.add("id=1;edad=36;genero=M;comunidad=And");
		votes.add("id=1;edad=56;genero=M;comunidad=And");
		votes.add("id=1;edad=36;genero=M;comunidad=And");
		votes.add("id=1;edad=36;genero=M;comunidad=And");
		votes.add("id=1;edad=46;genero=M;comunidad=And");
		votes.add("id=1;edad=56;genero=M;comunidad=And");
		votes.add("id=2;edad=27;genero=F;comunidad=ComMad");
		votes.add("id=2;edad=37;genero=F;comunidad=ComMad");
		votes.add("id=2;edad=27;genero=F;comunidad=ComMad");
		votes.add("id=2;edad=77;genero=F;comunidad=ComMad");
		votes.add("id=2;edad=67;genero=F;comunidad=ComMad");
		votes.add("id=2;edad=57;genero=F;comunidad=ComMad");
		votes.add("id=2;edad=47;genero=F;comunidad=ComMad");
		votes.add("id=2;edad=37;genero=F;comunidad=ComMad");
		votes.add("id=2;edad=27;genero=F;comunidad=ComMad");
		votes.add("id=2;edad=77;genero=F;comunidad=ComMad");
		votes.add("id=2;edad=87;genero=F;comunidad=ComMad");
		votes.add("id=2;edad=37;genero=F;comunidad=ComMad");
		votes.add("id=2;edad=57;genero=F;comunidad=ComMad");
		votes.add("id=2;edad=47;genero=F;comunidad=ComMad");
		votes.add("id=2;edad=37;genero=F;comunidad=ComMad");
		votes.add("id=2;edad=27;genero=F;comunidad=ComMad");
		votes.add("id=2;edad=57;genero=F;comunidad=ComMad");
		votes.add("id=2;edad=67;genero=F;comunidad=ComMad");
		votes.add("id=2;edad=47;genero=F;comunidad=ComMad");
		votes.add("id=2;edad=37;genero=F;comunidad=ComMad");
		votes.add("id=2;edad=27;genero=F;comunidad=ComMad");
		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
		votes.add("id=3;edad=20;genero=F;comunidad=Cat");
		votes.add("id=4;edad=20;genero=F;comunidad=Cat");
		poll.put(pollId, votes);
		
		try{
			System.out.println(CountingFactory.usualAgeRanges(poll));
			votes.add("3");
			poll.put(pollId, votes);
			System.out.println(CountingFactory.dhontCount(poll));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	/**
	 * It makes a basic count of a poll returning the number of votes for each poll answer (and ignoring age, gender and community)
	 */
	@RequestMapping("/basic")
	public void votes(HttpServletRequest re) {
		
		try {
			ObjectInputStream ois = new ObjectInputStream(re.getInputStream());
			Object o = ois.readObject();
			Map<String, Object> op = (Map<String, Object>) o;
			
			//
			
			Map<String, Object> poll = new HashMap<String, Object>();
			
			String pollId = "12345ABC";
			List<String> votes = new ArrayList<String>();
			
			votes.add("id=1;edad=20;genero=M;comunidad=Andalucía");
			votes.add("id=2;edad=21;genero=F;comunidad=Madrid");
			votes.add("id=1;edad=22;genero=M;comunidad=Andalucía");
			votes.add("id=3;edad=20;genero=F;comunidad=Andalucía");
			votes.add("id=1;edad=22;genero=M;comunidad=Andalucía");
			
			poll.put(pollId, votes);
			
			//
			
			Map<String, Object> countedPoll = new HashMap<String, Object>();
			
			CountingFactory.basicCount(poll);
			
			ois.close();
			re.getInputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * It makes a count of a poll returning the number of votes for each poll answer grouped by minors and adults users (Ignoring gender and community)
	 */
	@RequestMapping("/ageMayority")
	public void votesByAgeMajority(HttpServletRequest re) {
		
		try {
			ObjectInputStream ois = new ObjectInputStream(re.getInputStream());
			Object o = ois.readObject();
			Map<String, Object> op = (Map<String, Object>) o;
			
			//
			
			Map<String, Object> poll = new HashMap<String, Object>();
			
			String pollId = "12345ABC";
			List<String> votes = new ArrayList<String>();
			
			votes.add("id=1;edad=16;genero=M;comunidad=Andalucía");
			votes.add("id=2;edad=17;genero=F;comunidad=Madrid");
			votes.add("id=1;edad=22;genero=M;comunidad=Andalucía");
			votes.add("id=3;edad=20;genero=F;comunidad=Andalucía");
			votes.add("id=1;edad=22;genero=M;comunidad=Andalucía");
			
			poll.put(pollId, votes);
			
			//
			
			Map<String, Object> countedPoll = new HashMap<String, Object>();
			
			CountingFactory.ageMayorityCount(poll);
			
			ois.close();
			re.getInputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * It makes a count of a poll returning the number of votes for each poll answer grouped by a given Range (Ignoring gender and community)
	 */
	@RequestMapping("/ageRange")
	public void votesByAgeRange(HttpServletRequest re) {
		
		try {
			ObjectInputStream ois = new ObjectInputStream(re.getInputStream());
			Object o = ois.readObject();
			Map<String, Object> op = (Map<String, Object>) o;
			
			//
			
			Map<String, Object> poll = new HashMap<String, Object>();
			
			String pollId = "12345ABC";
			List<String> votes = new ArrayList<String>();
			
			votes.add("id=1;edad=16;genero=M;comunidad=Andalucía");
			votes.add("id=2;edad=17;genero=F;comunidad=Madrid");
			votes.add("id=1;edad=22;genero=M;comunidad=Andalucía");
			votes.add("id=3;edad=20;genero=F;comunidad=Andalucía");
			votes.add("id=1;edad=22;genero=M;comunidad=Andalucía");
			
			poll.put(pollId, votes);
			
			//
			
			Map<String, Object> countedPoll = new HashMap<String, Object>();
			
			Integer minRange = 20;
			Integer maxRange = 23;
			
			CountingFactory.ageRangeCount(countedPoll, minRange, maxRange);
			
			ois.close();
			re.getInputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * It makes a count of a poll returning the number of votes for each poll answer grouped by users gender (Ignoring age and community)
	 */
	@RequestMapping("/gender")
	public void votesByGender(HttpServletRequest re) {
		
		try {
			ObjectInputStream ois = new ObjectInputStream(re.getInputStream());
			Object o = ois.readObject();
			Map<String, Object> op = (Map<String, Object>) o;
			
			//
			
			Map<String, Object> poll = new HashMap<String, Object>();
			
			String pollId = "12345ABC";
			List<String> votes = new ArrayList<String>();
			
			votes.add("id=1;edad=16;genero=M;comunidad=Andalucía");
			votes.add("id=2;edad=17;genero=F;comunidad=Madrid");
			votes.add("id=1;edad=22;genero=M;comunidad=Andalucía");
			votes.add("id=3;edad=20;genero=F;comunidad=Andalucía");
			votes.add("id=1;edad=22;genero=M;comunidad=Andalucía");
			
			poll.put(pollId, votes);
			
			//
			
			Map<String, Object> countedPoll = new HashMap<String, Object>();
			
			CountingFactory.genderCount(poll);
			
			ois.close();
			re.getInputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * It makes a count of a poll returning the number of votes for each poll answer grouped by users autonomous community (Ignoring gender and age)
	 */
	@RequestMapping("/community")
	public void votesByCommunity(HttpServletRequest re) {
		
		try {
			ObjectInputStream ois = new ObjectInputStream(re.getInputStream());
			Object o = ois.readObject();
			Map<String, Object> op = (Map<String, Object>) o;
			
			//
			
			Map<String, Object> poll = new HashMap<String, Object>();
			
			String pollId = "12345ABC";
			List<String> votes = new ArrayList<String>();
			
			votes.add("id=1;edad=16;genero=M;comunidad=Andalucía");
			votes.add("id=2;edad=17;genero=F;comunidad=Madrid");
			votes.add("id=1;edad=22;genero=M;comunidad=Andalucía");
			votes.add("id=3;edad=20;genero=F;comunidad=Andalucía");
			votes.add("id=1;edad=22;genero=M;comunidad=Andalucía");
			
			poll.put(pollId, votes);
			
			//
			
			Map<String, Object> countedPoll = new HashMap<String, Object>();
			
			CountingFactory.communityCount(poll);
			
			ois.close();
			re.getInputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@RequestMapping("/hont")
	public void votesByDHontSystem(HttpServletRequest re) {
	try{
		ObjectInputStream ois = new ObjectInputStream(re.getInputStream());
		Object o = ois.readObject();
		Map<String, Object> op = (Map<String, Object>) o;
		
		CountingFactory.dhontCount(op);
		
		ois.close();
		re.getInputStream().close();
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
	@RequestMapping("/usualAgeRanges")
	public void votesByUsuallyRangeAge(HttpServletRequest re) {
	try{
		ObjectInputStream ois = new ObjectInputStream(re.getInputStream());
		Object o = ois.readObject();
		Map<String, Object> op = (Map<String, Object>) o;
		
		CountingFactory.usualAgeRanges(op);
		
		ois.close();
		re.getInputStream().close();
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	}

}