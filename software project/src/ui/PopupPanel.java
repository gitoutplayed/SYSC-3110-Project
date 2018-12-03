package ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import tile.TileTypes;
import zombie.Zombie;

/**
 * This class represent the PopupPanel. The PopupPanel contains images of zombies that
 * are on a certain tile.
 * 
 * @author Michael Fan 101029934
 * @version Nov 19, 2018
 */
public class PopupPanel extends JPanel {
	private JPanel displayPane;
	private JButton popupButton;
	
	private static int WIDTH = 4 * GameView.SQUARE_SIZE;
	private static int HEIGHT = GameView.SQUARE_SIZE;
	private static int DISPLAY_PANEL_HEIGHT = 10 * GameView.SQUARE_SIZE;
	
	public PopupPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(WIDTH + 18, HEIGHT + 45));
		
		displayPane = new JPanel();
		displayPane.setPreferredSize(new Dimension(WIDTH, DISPLAY_PANEL_HEIGHT));
		displayPane.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane(displayPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(WIDTH + 18, HEIGHT));
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.LEADING));
		popupButton = new JButton("Close");
		buttonPane.add(popupButton);
		
		add(scrollPane);
		add(buttonPane);
	}

	/**
	 * Returns the popup button
	 * 
	 * @return the popup button
	 */
	public JButton getPopupButton() {
		return popupButton;
	}

	/**
	 * Add zombies to the popup.
	 * 
	 * @param zombies the zombies to add to the popup
	 * @param tileType the type of the tile
	 */
	public void addZombiesToPopup(List<Zombie> zombies, TileTypes tileType) {
		for(Zombie zombie : zombies) {
			displayPane.add(new JLabel(zombie.getIcon(tileType)));
		}
	}
	
	/**
	 * Clear the content in the PopupPanel
	 */
	public void clearPopupPanel() {
		displayPane.removeAll();
	}
}
