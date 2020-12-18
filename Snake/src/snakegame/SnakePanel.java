package snakegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePanel extends JPanel implements ActionListener{
	static final int WIDTH = 600;
	static final int HEIGHT = 600;
	static final int UNIT_SIZE =  25;
	static final int GAME_UNIT = (WIDTH * HEIGHT) / UNIT_SIZE;
	int speed = 140;
	final int[] x = new int[GAME_UNIT];
	final int[] y = new int[GAME_UNIT];
	char direction = 'R';
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
		xApple = rd.nextInt((int) (WIDTH/UNIT_SIZE))*UNIT_SIZE;
		yApple = rd.nextInt((int) (HEIGHT/UNIT_SIZE))*UNIT_SIZE;
	}
	public void checkApple() {
		if((x[0] == xApple) && (y[0] == yApple)){
			generateApple();
			bodyParts++;
			score++;
			speed += 50;
		}
	}
	public void move(){
		for(int i = bodyParts;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}	
	}
	public void draw(Graphics g) {
		if(running) {
			
			g.setColor(Color.RED);
			g.fillOval(xApple, yApple, UNIT_SIZE, UNIT_SIZE);
			
			for(int i = 0; i < bodyParts; i++) {
				if(i == 0) {
					g.setColor(Color.green);
					g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}	else {
					g.setColor(Color.pink);
					g.setColor(new Color(rd.nextInt(255),rd.nextInt(255),rd.nextInt(255)));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			g.setColor(Color.red);
			g.setFont( new Font("Ink Free",Font.BOLD, 40));
			FontMetrics metrics1 = getFontMetrics(g.getFont());
			g.drawString("Score: "+score, (WIDTH - metrics1.stringWidth("Score: "+score))/2, g.getFont().getSize());
		}	else {
			gameOver(g);
		}
	}
	private void gameOver(Graphics g) {
		g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: "+score, (WIDTH - metrics1.stringWidth("Score: "+score))/2, g.getFont().getSize());

		g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (WIDTH - metrics2.stringWidth("Game Over"))/2, HEIGHT/2);

	}


	public void checkCollision() {
		for(int i = bodyParts; i > 0; i--) {
			if((x[0] == x[i]) && y[0] == y[i]) {
				running = false;
			}
		}
		if(x[0] < 0) running = false;
		if(x[0] > WIDTH) running = false;
		if(y[0] < 0) running = false;
		if(y[0] > HEIGHT) running = false;
		
		if(!running) {
			timer.stop();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(running) {
			move();
			checkApple();
			checkCollision();
		}	
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			}
		}
	}

}
