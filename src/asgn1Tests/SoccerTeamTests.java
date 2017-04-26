package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerTeam;



/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerLeage class
 *
 * @author Alan Woodley
 *
 */
public class SoccerTeamTests {
	
	private SoccerTeam test;

	@Before
	public void newSoccerTeam() throws TeamException {
		test = new SoccerTeam("Metropolis", "Men of Steel");

	}
	@Test(expected = TeamException.class)
	public void noOfficialName() throws TeamException{
		SoccerTeam test = new SoccerTeam("", "Men of Steel");
	}
	
	@Test(expected = TeamException.class)
	public void onlySpaceOfficialName() throws TeamException{
		SoccerTeam test = new SoccerTeam("  ", "Men of Steel");
	}
	
	@Test(expected = TeamException.class)
	public void noNickName() throws TeamException{
		SoccerTeam test = new SoccerTeam("Metropolis", "");
	}
	
	@Test(expected = TeamException.class)
	public void onlySpaceNickName() throws TeamException{
		SoccerTeam test = new SoccerTeam("Metropolis", "  ");
	}
	
	@Test
	public void constructSoccerTeam() throws TeamException{
		assertEquals("Metropolis", test.getOfficialName());
		assertEquals("Men of Steel", test.getNickName());
		assertEquals(0, test.getGoalsScoredSeason());
		assertEquals(0, test.getGoalsConcededSeason());
		assertEquals(0, test.getMatchesWon());
		assertEquals(0, test.getMatchesLost());
		assertEquals(0, test.getMatchesDrawn());
		assertEquals(0, test.getCompetitionPoints());
	}
	
	@Test
	public void getOfficialNameNormalCase() throws TeamException{
		assertEquals("Metropolis", test.getOfficialName());
	}
	
	@Test
	public void getOfficialNameTwoTeams() throws TeamException{
		SoccerTeam test2 = new SoccerTeam("Gotham City", "Dark Knights");
		assertEquals("Metropolis", test.getOfficialName());
		assertTrue(test.getOfficialName() != test2.getOfficialName());
	}
	
	@Test
	public void getNickNameNormalCase() throws TeamException{
		assertEquals("Men of Steel", test.getNickName());
	}
	
	@Test
	public void getNickNameTwoTeams() throws TeamException{
		SoccerTeam test2 = new SoccerTeam("Gotham City", "Dark Knights");
		assertEquals("Men of Steel", test.getNickName());
		assertTrue(test.getNickName() != test2.getNickName());
	}
	
	@Test
	public void getGoalsScoredSeasonPlay2Matches() throws TeamException{
		test.playMatch(3, 1);
		test.playMatch(4, 2);
		assertEquals(7, test.getGoalsScoredSeason());
	}
	
	@Test
	public void getGoalsScoredSeasonPlay5Matches() throws TeamException{
		test.playMatch(3, 1);
		test.playMatch(4, 2);
		test.playMatch(1, 2);
		test.playMatch(2, 2);
		test.playMatch(4, 2);
		assertEquals(14, test.getGoalsScoredSeason());
	}
	
	@Test
	public void getGoalsConcededSeasonPlay2Matches() throws TeamException{
		test.playMatch(1, 2);
		test.playMatch(2, 4);
		assertEquals(6, test.getGoalsConcededSeason());
	}
	
	@Test
	public void getGoalsConcededSeasonPlay5Matches() throws TeamException{
		test.playMatch(1, 2);
		test.playMatch(2, 1);
		test.playMatch(2, 0);
		test.playMatch(2, 3);
		test.playMatch(2, 2);
		assertEquals(8, test.getGoalsConcededSeason());
	}
	
	@Test
	public void getMatchesWonPlay4Matches() throws TeamException{
		test.playMatch(3, 2);
		test.playMatch(2, 4);
		test.playMatch(4, 4);
		test.playMatch(6, 4);
		assertEquals(2, test.getMatchesWon());
	}
	
	@Test
	public void getMatchesLostPlay5Matches() throws TeamException{
		test.playMatch(3, 2);
		test.playMatch(2, 4);
		test.playMatch(4, 4);
		test.playMatch(6, 4);
		test.playMatch(6, 8);
		assertEquals(2, test.getMatchesLost());
	}
	
	@Test
	public void getMatchesDrawnPlay4Matches() throws TeamException{
		test.playMatch(2, 2);
		test.playMatch(2, 4);
		test.playMatch(4, 4);
		test.playMatch(6, 4);
		assertEquals(2, test.getMatchesDrawn());
	}
	
	@Test
	public void getCompetitionPointsPlay4Matches() throws TeamException{
		test.playMatch(2, 2);
		test.playMatch(2, 4);
		test.playMatch(4, 4);
		test.playMatch(6, 4);
		assertEquals(5, test.getCompetitionPoints());
	}
	
	@Test
	public void getGoalDifferencePlay4Matches() throws TeamException{
		test.playMatch(2, 0);
		test.playMatch(2, 4);
		test.playMatch(4, 4);
		test.playMatch(6, 4);
		assertEquals(2, test.getGoalDifference());
	}
	
	@Test
	public void getFormStringNoMatchesPlayed() throws TeamException{
		assertEquals("-----", test.getFormString());
	}
	
	@Test
	public void getFormStringAfter4Matches() throws TeamException{
		test.playMatch(2, 0);
		test.playMatch(2, 4);
		test.playMatch(4, 4);
		test.playMatch(6, 4);
		assertEquals("WDLW-", test.getFormString());
	}
	
	@Test
	public void getFormStringAfter6Matches() throws TeamException{
		test.playMatch(2, 0);
		test.playMatch(2, 4);
		test.playMatch(4, 4);
		test.playMatch(6, 4);
		test.playMatch(4, 4);
		test.playMatch(4, 4);
		assertEquals("DDWDL", test.getFormString());
	}
	
	@Test(expected = TeamException.class)
	public void playMatchGoalsForLessThan0Exception() throws TeamException{
		test.playMatch(-2, 1);
	}
	
	@Test(expected = TeamException.class)
	public void playMatchGoalsForGreaterThan20Exception() throws TeamException{
		test.playMatch(21, 1);
	}
	
	@Test(expected = TeamException.class)
	public void playMatchGoalsAgainstLessThan0Exception() throws TeamException{
		test.playMatch(2, -11);
	}
	
	@Test(expected = TeamException.class)
	public void playMatchGoalsAgainstGreaterThan20Exception() throws TeamException{
		test.playMatch(2, 23);
	}
	
	@Test
	public void playMatchPlay7Matches() throws TeamException{
		test.playMatch(2, 1);
		test.playMatch(2, 2);
		test.playMatch(2, 3);
		test.playMatch(2, 1);
		test.playMatch(2, 2);
		test.playMatch(4, 3);
		test.playMatch(2, 6);
		assertEquals(16, test.getGoalsScoredSeason());
		assertEquals(18, test.getGoalsConcededSeason());
		assertEquals(3, test.getMatchesWon());
		assertEquals(2, test.getMatchesLost());
		assertEquals(2, test.getMatchesDrawn());
		assertEquals(11, test.getCompetitionPoints());
		assertEquals("LWDWL", test.getFormString());
	}
	
	@Test
	public void playMatchPlay4Matches() throws TeamException{
		test.playMatch(2, 1);
		test.playMatch(2, 2);
		test.playMatch(2, 3);
		test.playMatch(2, 1);
		assertEquals(8, test.getGoalsScoredSeason());
		assertEquals(7, test.getGoalsConcededSeason());
		assertEquals(2, test.getMatchesWon());
		assertEquals(1, test.getMatchesLost());
		assertEquals(1, test.getMatchesDrawn());
		assertEquals(7, test.getCompetitionPoints());
		assertEquals("WLDW-", test.getFormString());
	}
	
	@Test
	public void resetStatsAfterSeveralMatches() throws TeamException{
		test.playMatch(2, 1);
		test.playMatch(2, 2);
		test.playMatch(2, 3);
		test.playMatch(2, 1);
		test.resetStats();
		assertEquals(0, test.getGoalsScoredSeason());
		assertEquals(0, test.getGoalsConcededSeason());
		assertEquals(0, test.getMatchesWon());
		assertEquals(0, test.getMatchesLost());
		assertEquals(0, test.getMatchesDrawn());
		assertEquals(0, test.getCompetitionPoints());
		assertEquals("-----", test.getFormString());
	}
	
	@Test
	public void compareToSamePointsSameDifSameName() throws TeamException {
		SoccerTeam test2 = new SoccerTeam("Metropolis", "Men of Steel");
		assertEquals(0, test.compareTo(test2));
	}
	
	@Test
	public void compareToOtherMorePoints() throws TeamException {
		SoccerTeam test2 = new SoccerTeam("Gotham City", "Dark Knights");
		test.playMatch(0, 1);
		test2.playMatch(3, 2);
		assertTrue(test.compareTo(test2) > 0);
	}
	
	@Test
	public void compareToThisTeamMorePoints() throws TeamException {
		SoccerTeam test2 = new SoccerTeam("Gotham City", "Dark Knights");
		test.playMatch(2, 1);
		test2.playMatch(0, 2);
		assertTrue(test.compareTo(test2) < 0);
	}
	
	@Test
	public void compareToSamePointOtherMoreGoalsDiff() throws TeamException {
		SoccerTeam test2 = new SoccerTeam("Gotham City", "Dark Knights");
		test.playMatch(3, 1);
		test2.playMatch(5, 2);
		assertTrue(test.compareTo(test2) > 0);
	}
	
	@Test
	public void compareToSamePointThisTeamMoreGoalsDiff() throws TeamException {
		SoccerTeam test2 = new SoccerTeam("Gotham City", "Dark Knights");
		test.playMatch(3, 1);
		test2.playMatch(3, 2);
		assertTrue(test.compareTo(test2) < 0);
	}
	
	@Test
	public void compareToSamePointSameGoalsDiffOtherNameBefore() throws TeamException {
		SoccerTeam test2 = new SoccerTeam("Gotham City", "Dark Knights");
		assertTrue(test.compareTo(test2) > 0);
	}
	
	@Test
	public void compareToSamePointSameGoalsDiffThisTeamNameBefore() throws TeamException {
		SoccerTeam test2 = new SoccerTeam("Potham City", "Dark Knights");
		assertTrue(test.compareTo(test2) < 0);
	}

}
