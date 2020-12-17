package snakegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePanel extends JPanel implements ActionListener{
	static final int WIDTH = 600;
	static final int HEIGHT = 600;
	static final int UNIT_SIZE =  25;
	static final int GAME_UNIT = (WIDTH * HEIGHT) / UNIT_SIZE;
	int speed = 50;
	final int[] x = new int[GAME_UNIT];
	final int[] y = new int[GAME_UNIT];
	char direction = 'U';
	int bodyParts = 4;
	boolean running = false;
	int xApple;
	int yApple;
	int score = 0;
	
	Timer timer;
	Random rd;
	
	public SnakePanel() {
		rd = new Random();
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		gameStart();
	}
	
	
	public void gameStart() {
		generateApple();
		running = true;
		timer = new Timer(speed, this);
		timer.start();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void generateApple() {
		
	}
	public void checkApple() {
		
	}
	public void draw(Graphics g) {
		
	}
	public void checkCollision() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		
	}

}
