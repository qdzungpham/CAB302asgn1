package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.LeagueException;
import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerLeague;
import asgn1SoccerCompetition.SoccerTeam;


/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerLeage class
 *
 * @author Alan Woodley
 *
 */
public class SoccerLeagueTests {
	
	private SoccerLeague test;
	private SoccerTeam team1;
	private SoccerTeam team2;
	
	@Before
	public void newSoccerLeague() throws TeamException, LeagueException{
		test = new SoccerLeague(3);
		team1 = new SoccerTeam("Metropolis", "Men of Steel");
		team2 = new SoccerTeam("Gotham City", "Dark Knights");
		test.registerTeam(team1);
		test.registerTeam(team2);
	}
	
	@Test
	public void constructSoccerLeague() {
		assertEquals(true, test.isOffSeason());
		assertEquals(3, test.getRequiredNumTeams());
	}
	
	@Test(expected = LeagueException.class)
	public void registerTeamAlreadyStartedException() throws TeamException, LeagueException {
		SoccerTeam team3 = new SoccerTeam("Paradise Island", "Wicked Wonders");
		test.registerTeam(team3);
		test.startNewSeason();
		SoccerTeam team4 = new SoccerTeam("Asgard City", "Thunderstrikes");
		test.registerTeam(team4);
	}
	
	@Test(expected = LeagueException.class)
	public void registerTeamMaxTeamReachedException() throws TeamException, LeagueException {
		SoccerTeam team3 = new SoccerTeam("Paradise Island", "Wicked Wonders");
		test.registerTeam(team3);
		SoccerTeam team4 = new SoccerTeam("Asgard City", "Thunderstrikes");
		test.registerTeam(team4);
	}
	
	@Test(expected = LeagueException.class)
	public void registerTeamAlreadyRegisteredException() throws TeamException, LeagueException {
		SoccerTeam team3 = new SoccerTeam("Metropolis", "Men of Steel");
		test.registerTeam(team3);
	}
	
	@Test
	public void registerTeamAddOne() throws TeamException, LeagueException {
		SoccerTeam team3 = new SoccerTeam("Paradise Island", "Wicked Wonders");
		test.registerTeam(team3);
		assertTrue(test.containsTeam("Paradise Island"));
	}
	
	@Test(expected = LeagueException.class)
	public void removeTeamSeasonNotEndedException() throws TeamException, LeagueException {
		SoccerTeam team3 = new SoccerTeam("Paradise Island", "Wicked Wonders");
		test.registerTeam(team3);
		test.startNewSeason();
		test.removeTeam(team3);
	}
	
	@Test(expected = LeagueException.class)
	public void removeTeamNoTeamException() throws TeamException, LeagueException {
		SoccerTeam team3 = new SoccerTeam("Paradise Island", "Wicked Wonders");
		test.removeTeam(team3);
	}
	
	@Test
	public void removeTeamRemoveOne() throws TeamException, LeagueException {
		test.removeTeam(team2);
		assertFalse(test.containsTeam("Gotham City"));
	}
	
	@Test
	public void getRegisteredNumTeams() {
		assertEquals(2, test.getRegisteredNumTeams());
	}
	
	@Test
	public void getRequiredNumTeams() {
		assertEquals(3, test.getRequiredNumTeams());
	}
	
	@Test(expected = LeagueException.class)
	public void startNewSeasonAlreadyStartedException() throws TeamException, LeagueException {
		SoccerTeam team3 = new SoccerTeam("Paradise Island", "Wicked Wonders");
		test.registerTeam(team3);
		test.startNewSeason();
		test.startNewSeason();
	}
	
	@Test(expected = LeagueException.class)
	public void startNewSeasonNotEnoughTeamException() throws LeagueException {
		test.startNewSeason();
	}
	
	@Test
	public void startNewSeasonCheckResetStatsAllTeams() throws TeamException, LeagueException {
		SoccerTeam team3 = new SoccerTeam("Paradise Island", "Wicked Wonders");
		test.registerTeam(team3);
		test.startNewSeason();
		assertEquals("-----", team1.getFormString());
		assertEquals("-----", team2.getFormString());
		assertEquals("-----", team3.getFormString());
	}
	
	@Test
	public void startNewSeasonCheckOffSeasonChangedToFalse() throws TeamException, LeagueException {
		SoccerTeam team3 = new SoccerTeam("Paradise Island", "Wicked Wonders");
		test.registerTeam(team3);
		test.startNewSeason();
		assertEquals(false, test.isOffSeason());
		
	}

	@Test(expected = LeagueException.class)
	public void endSeasonSeasonNotStartedException() throws TeamException, LeagueException {
		SoccerTeam team3 = new SoccerTeam("Paradise Island", "Wicked Wonders");
		test.registerTeam(team3);
		test.endSeason();
	}
	
	@Test
	public void endSeasonCheckOffSeasonChangedToTrue() throws TeamException, LeagueException {
		SoccerTeam team3 = new SoccerTeam("Paradise Island", "Wicked Wonders");
		test.registerTeam(team3);
		test.startNewSeason();
		test.endSeason();
		assertEquals(true, test.isOffSeason());		
	}
	
	@Test(expected = LeagueException.class)
	public void getTeamByOfficalNameNoTeamFoundException() throws TeamException, LeagueException {
		SoccerTeam team3 = new SoccerTeam("Paradise Island", "Wicked Wonders");
		test.registerTeam(team3);
		test.getTeamByOfficalName("Hello");
	}
	
	@Test
	public void getTeamByOfficalNameNormalCase() throws TeamException, LeagueException {
		SoccerTeam team3 = new SoccerTeam("Paradise Island", "Wicked Wonders");
		test.registerTeam(team3);
		assertEquals(team3, test.getTeamByOfficalName("Paradise Island"));
	}
	
	@Test(expected = LeagueException.class)
	public void playMatchSeasonNotStartedException() throws TeamException, LeagueException {
		SoccerTeam team3 = new SoccerTeam("Paradise Island", "Wicked Wonders");
		test.registerTeam(team3);
		test.playMatch("Metropolis", 1, "Paradise Island", 0);
	}
	
	@Test(expected = LeagueException.class)
	public void playMatchSameNameException() throws TeamException, LeagueException {
		SoccerTeam team3 = new SoccerTeam("Paradise Island", "Wicked Wonders");
		test.registerTeam(team3);
		test.startNewSeason();
		test.playMatch("Metropolis", 0, "Metropolis", 0);
	}
	
	@Test
	public void playMatchPlaySeveralMatch() throws TeamException, LeagueException {
		SoccerTeam team3 = new SoccerTeam("Paradise Island", "Wicked Wonders");
		test.registerTeam(team3);
		test.startNewSeason();
		test.playMatch("Metropolis", 4, "Paradise Island", 2);
		test.playMatch("Paradise Island", 1, "Metropolis", 3);
		assertEquals(3, team3.getGoalsScoredSeason());
		assertEquals(7, team1.getGoalsScoredSeason());
	}
	
	@Test
	public void playMatchPlayCheckSortTeamsAfterMatch() throws TeamException, LeagueException {
		SoccerTeam team3 = new SoccerTeam("Paradise Island", "Wicked Wonders");
		test.registerTeam(team3);
		test.startNewSeason();
		test.playMatch("Metropolis", 4, "Paradise Island", 2);
		assertEquals(team1, test.getTopTeam());	
		assertEquals(team3, test.getBottomTeam());
	}
	
	
	@Test
	public void getBottomTeamAfterSeveralMatches() throws TeamException, LeagueException {
		SoccerTeam team3 = new SoccerTeam("Paradise Island", "Wicked Wonders");
		test.registerTeam(team3);
		test.startNewSeason();
		test.playMatch("Metropolis", 4, "Paradise Island", 2);
		test.playMatch("Gotham City", 1, "Metropolis", 3);
		test.playMatch("Paradise Island", 1, "Gotham City", 2);
		assertEquals(team3, test.getBottomTeam());		
	}
	
	@Test
	public void getTopTeamNormalCase() throws TeamException, LeagueException {
		SoccerTeam team3 = new SoccerTeam("Paradise Island", "Wicked Wonders");
		test.registerTeam(team3);
		test.startNewSeason();
		test.playMatch("Metropolis", 4, "Paradise Island", 2);
		test.playMatch("Gotham City", 1, "Metropolis", 3);
		test.playMatch("Paradise Island", 1, "Gotham City", 2);
		assertEquals(team1, test.getTopTeam());		
	}
	
	@Test
	public void sortTeamsInitialSort() throws TeamException, LeagueException {
		SoccerTeam team3 = new SoccerTeam("Paradise Island", "Wicked Wonders");
		test.registerTeam(team3);
		test.sortTeams();
		assertEquals(team2, test.getTopTeam());	
		assertEquals(team3, test.getBottomTeam());
	}
	
	@Test
	public void sortTeamsAfterSeveralMatches() throws TeamException, LeagueException {
		SoccerTeam team3 = new SoccerTeam("Paradise Island", "Wicked Wonders");
		test.registerTeam(team3);
		test.startNewSeason();
		test.playMatch("Metropolis", 4, "Paradise Island", 2);
		test.playMatch("Gotham City", 1, "Metropolis", 3);
		test.playMatch("Paradise Island", 1, "Gotham City", 2);
		test.sortTeams();
		assertEquals(team1, test.getTopTeam());	
		assertEquals(team3, test.getBottomTeam());
	}
	
	@Test
	public void containsTeamTestTeamInLeague() throws TeamException, LeagueException {
		assertEquals(true, test.containsTeam("Metropolis"));
	}
	
	@Test
	public void containsTeamTestTeamNotInLeague() throws TeamException, LeagueException {
		assertEquals(false, test.containsTeam("Paradise Island"));
	}

}

