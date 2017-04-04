package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1SoccerCompetition.SportsTeamForm;
import asgn1SportsUtils.WLD;

/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerTeamForm class
 *
 * @author Alan Woodley
 *
 */
public class SportsTeamFormTests {
	
	private SportsTeamForm newForm;
	@Before
	public void newForm() {
		newForm = new SportsTeamForm();
	}
	
	@Test 
	public void addResultToFormAddThree() {
		newForm.addResultToForm(WLD.WIN);
		newForm.addResultToForm(WLD.WIN);
		newForm.addResultToForm(WLD.DRAW);
		assertEquals(newForm.toString(), "DWW--");
	}
	@Test
	public void toStringOne() {
		newForm.addResultToForm(WLD.WIN);
		assertEquals(newForm.toString(), "W----");
	}
	
	@Test
	public void toStringFive() {
		newForm.addResultToForm(WLD.WIN);
		newForm.addResultToForm(WLD.LOSS);
		newForm.addResultToForm(WLD.LOSS);
		newForm.addResultToForm(WLD.DRAW);
		newForm.addResultToForm(WLD.WIN);
		assertEquals(newForm.toString(), "WDLLW");
	}
	
	@Test
	public void toStringSeven() {
		newForm.addResultToForm(WLD.WIN);
		newForm.addResultToForm(WLD.LOSS);
		newForm.addResultToForm(WLD.LOSS);
		newForm.addResultToForm(WLD.DRAW);
		newForm.addResultToForm(WLD.WIN);
		newForm.addResultToForm(WLD.DRAW);
		newForm.addResultToForm(WLD.DRAW);
		assertEquals(newForm.toString(), "DDWDL");
	}
	
	@Test 
	public void toStringEmpty() {
		assertEquals(newForm.toString(), "-----");
	}
	
	@Test 
	public void getNumGamesEmpty() {
		assertEquals(newForm.getNumGames(), 0);
	}
	
	@Test 
	public void getNumGamesAddTwo() {
		newForm.addResultToForm(WLD.WIN);
		newForm.addResultToForm(WLD.LOSS);
		assertEquals(newForm.getNumGames(), 2);
	}
	
	@Test 
	public void getNumGamesAddSix() {
		newForm.addResultToForm(WLD.WIN);
		newForm.addResultToForm(WLD.LOSS);
		newForm.addResultToForm(WLD.WIN);
		newForm.addResultToForm(WLD.LOSS);
		newForm.addResultToForm(WLD.WIN);
		newForm.addResultToForm(WLD.LOSS);
		assertEquals(newForm.getNumGames(), 5);
	}
	
	@Test
	public void resetFormNormalCase() {
		newForm.addResultToForm(WLD.WIN);
		newForm.addResultToForm(WLD.LOSS);
		newForm.resetForm();
		assertEquals(newForm.toString(), "-----");
		assertEquals(newForm.getNumGames(), 0);
	}
	
	@Test
	public void resetFormEmpty() {
		newForm.resetForm();
		assertEquals(newForm.toString(), "-----");
		assertEquals(newForm.getNumGames(), 0);
	}
	
	

}
