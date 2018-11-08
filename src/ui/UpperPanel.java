package ui;
import javax.swing.*;

import java.awt.*;

public class UpperPanel extends JPanel {
	public static int HEIGHT = 100;
	
	public UpperPanel() {
		setPreferredSize(new Dimension(GameView.WIDTH, HEIGHT));
	}
}
