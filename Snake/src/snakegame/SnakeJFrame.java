package snakegame;

import javax.swing.JFrame;

public class SnakeJFrame extends JFrame{
	public SnakeJFrame() {
		this.add(new SnakePanel());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Snake Game");
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
