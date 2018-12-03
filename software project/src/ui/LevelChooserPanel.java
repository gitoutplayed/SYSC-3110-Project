package ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

/**
 * This class represents the LevelChooserPanel.
 * 
 * @author Michael Fan 101029934
 * @version Nov 20, 2018
 */
public class LevelChooserPanel extends JPanel {
	private JList<Integer> predefinedLevels;
	private JList<String> customLevels;
	private JButton loadButton;
	private JButton closeButton;

	private static int WIDTH = 4 * GameView.SQUARE_SIZE;
	private static int HEIGHT = 3 * GameView.SQUARE_SIZE;
	private static int BUTTON_HEIGHT = GameView.SQUARE_SIZE / 3;
	private static int LABEL_HEIGHT = GameView.SQUARE_SIZE / 4;

	/**
	 * Constructs a new LevelChooserPanel.
	 */
	public LevelChooserPanel() {
		setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		loadButton = new JButton("Load");
		loadButton.setPreferredSize(new Dimension(WIDTH / 2, BUTTON_HEIGHT));
		closeButton = new JButton("Close");
		closeButton.setPreferredSize(new Dimension(WIDTH / 2, BUTTON_HEIGHT));

		JLabel predefinedLevelsLabel = new JLabel("Predefined Levels", JLabel.CENTER);
		predefinedLevelsLabel.setPreferredSize(new Dimension(WIDTH / 2, LABEL_HEIGHT));
		JLabel customLevelsLabel = new JLabel("Custom Levels", JLabel.CENTER);
		customLevelsLabel.setPreferredSize(new Dimension(WIDTH / 2, LABEL_HEIGHT));

		// Predefined levels list setup
		predefinedLevels = new JList<Integer>();
		predefinedLevels.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		predefinedLevels.setLayoutOrientation(JList.VERTICAL);
		predefinedLevels.setVisibleRowCount(-1);
		JScrollPane scrollPanePredefined = new JScrollPane(predefinedLevels, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPanePredefined.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT - BUTTON_HEIGHT - LABEL_HEIGHT));

		// Custom levels list setup
		customLevels = new JList<String>();
		customLevels.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		customLevels.setLayoutOrientation(JList.VERTICAL);
		customLevels.setVisibleRowCount(-1);
		JScrollPane scrollPaneCustom = new JScrollPane(customLevels, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneCustom.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT - BUTTON_HEIGHT - LABEL_HEIGHT));

		add(loadButton);
		add(closeButton);
		add(predefinedLevelsLabel);
		add(customLevelsLabel);
		add(scrollPanePredefined);
		add(scrollPaneCustom);
	}

	/**
	 * Adds a list of predefined levels.
	 * 
	 * @param predefinedLevels the predefined levels
	 */
	public void addPredefinedLevels(List<Integer> predefinedLevels) {
		DefaultListModel<Integer> list = new DefaultListModel<Integer>();

		for(Integer i : predefinedLevels) {
			list.addElement(i);
		}

		this.predefinedLevels.setModel(list);
	}

	/**
	 * Adds a list of custom levels.
	 * 
	 * @param customLevels
	 */
	public void addCustomLevels(List<String> customLevels) {
		DefaultListModel<String> list = new DefaultListModel<String>();

		for(String i : customLevels) {
			list.addElement(i);
		}

		this.customLevels.setModel(list);
	}

	/**
	 * Returns the load button.
	 * 
	 * @return the close button
	 */
	public JButton getLoadButton() {
		return loadButton;
	}

	/**
	 * Returns the close button.
	 * 
	 * @return the close button
	 */
	public JButton getCloseButton() {
		return closeButton;
	}

	/**
	 * Returns the predefined levels list.
	 * 
	 * @return the predefined levels list
	 */
	public JList<Integer> getPredefinedLevelsList() {
		return predefinedLevels;
	}

	/**
	 * Returns the custom levels list.
	 * 
	 * @return the custom levels list
	 */
	public JList<String> getCustomLevelsList() {
		return customLevels;
	}
}
