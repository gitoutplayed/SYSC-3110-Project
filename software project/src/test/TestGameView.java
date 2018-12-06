package test;

import junit.framework.TestCase;
import ui.GameView;
import ui.UpperPanel;

/**
 * A test class for class GameView
 * 
 * @author Hoang Bui, 101029049
 * @version 1
 */

public class TestGameView extends TestCase{
	GameView view;
	UpperPanel panel;
	
	protected void setUp() {
		view = new GameView();
		panel = new UpperPanel();
	}
	
	public void testMenu() {
		assertEquals("The text of this JMenuItem should be Load Level", "Load Level", view.getLoadLevel().getText());
		assertEquals("The text of this JMenuItem should be Load Next Level", "Load Next Level", view.getLoadNextLevel().getText());
		assertEquals("The text of this JMenuItem should be Load Previous Level", "Load Previous Level", view.getLoadPreviousLevel().getText());
		assertEquals("The text of this JMenuItem should be Undo", "Undo", view.getUndo().getText());
		assertEquals("The text of this JMenuItem should be Redo", "Redo", view.getRedo().getText());
		assertEquals("The text of this JMenuItem should be Restart", "Restart", view.getRestart().getText());
		assertEquals("The text of this JMenuItem should be Load Save", "Load Save", view.getLoadSave().getText());
		assertEquals("The text of this JMenuItem should be Save", "Save", view.getSave().getText());
		assertEquals("The text of this JMenuItem should be Build Level", "Build Level", view.getBuildLevel().getText());
		assertEquals("The text of this JMenuItem should be Load Custom Levels Into Game", "Load Custom Levels Into Game", view.getLoadCustomLevel().getText());
	}
	
	public void testInitializationOfInGameButtons() {
		assertEquals("The text of this JButton should be End Turn", "End Turn", view.getEndTurn().getText());
		assertEquals("The text of this JButton from the shop should be blank", "", view.getShopButtons().get(0).getText());
		assertEquals("The text of this JButton from the shop should be blank", "", view.getShopButtons().get(2).getText());
		assertEquals("The text of this JButton from the grid position [0,0] should be blank", "", view.getButtonGrid()[0][0].getText());
		assertEquals("The text of this JButton from the grid position [1,1] should be blank", "", view.getButtonGrid()[1][1].getText());
		assertEquals("The shovel button should be blank", "", panel.getShovel().getText());
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestGameState.class);
	}
}
