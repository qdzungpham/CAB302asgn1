package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.CompetitionException;
import asgn1Exceptions.LeagueException;
import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerCompetition;
import asgn1SoccerCompetition.SoccerLeague;
import asgn1SoccerCompetition.SoccerTeam;

/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerCompetition class
 *
 * @author Alan Woodley
 *
 */
public class SoccerCompetitionTests {
	
	private SoccerCompetition test;
	
	@Before
	public void newSoccerCompetition() throws TeamException, LeagueException, CompetitionException {
		test = new SoccerCompetition("Test Competition", 2, 3);
		SoccerTeam team1 = new SoccerTeam("Metropolis", "Men of Steel");
		SoccerTeam team2 = new SoccerTeam("Gotham City", "Dark Knights");
		SoccerTeam team3 = new SoccerTeam("Paradise Island", "Wicked Wonders");
		SoccerTeam team4 = new SoccerTeam("Canadian", "Wolverines");
		SoccerTeam team5 = new SoccerTeam("Asgard City", "Thunderstrikes");
		SoccerTeam team6 = new SoccerTeam("Brooklyn", "Patriots");
		test.getLeague(0).registerTeam(team1);
		test.getLeague(0).registerTeam(team2);
		test.getLeague(0).registerTeam(team3);
		test.getLeague(1).registerTeam(team4);
		test.getLeague(1).registerTeam(team5);
		test.getLeague(1).registerTeam(team6);
	}
	
	@Test (expected = CompetitionException.class)
	public void getLeagueLessThan0Exception() throws CompetitionException {
		test.getLeague(-2);
	}
	
	@Test (expected = CompetitionException.class)
	public void getLeagueGreaterThanNumLeaguesException() throws CompetitionException {
		test.getLeague(5);
	}
	
	@Test (expected = CompetitionException.class)
	public void getLeagueEqualToNumLeaguesException() throws CompetitionException {
		test.getLeague(3);
	}
	
	/*
	@Test
	public void getLeagueNormalCase() throws CompetitionException {
		test.
		test.getLeague(3);
	} */
	
	@Test
	public void startSeasonTestChangedisOffSeason() throws CompetitionException {
		test.startSeason();
		assertEquals(false, test.getLeague(0).isOffSeason());
		assertEquals(false, test.getLeague(1).isOffSeason());
	}
	
	@Test
	public void endSeasonTestChangedisOffSeason() throws CompetitionException {
		test.startSeason();
		test.endSeason();
		assertEquals(true, test.getLeague(0).isOffSeason());
	}
	
	@Test
	public void endSeasonTestDoPromotionRelegation() throws CompetitionException, LeagueException {
		test.startSeason();
		test.getLeague(0).playMatch("Paradise Island", 3, "Metropolis", 0);
		test.getLeague(1).playMatch("Asgard City", 3, "Brooklyn", 0);
		test.endSeason();
		assertEquals(true, test.getLeague(0).containsTeam("Asgard City"));
		assertEquals(true, test.getLeague(1).containsTeam("Metropolis"));
		
	}
	

}

