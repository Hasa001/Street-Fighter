package gaming.canvas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import gaming.sprites.KenPlayer;
import gaming.sprites.RyuPlayer;
import gaming.sprites.power;
import gaming.utils.GameConstants;



public class Board extends JPanel implements GameConstants {
	BufferedImage imageBg;
	private RyuPlayer ryuPlayer;
	private KenPlayer kenPlayer;
	private power ryuFullPower;
	private power kenFullPower;
	private Timer timer;
	private boolean gameOver;
	
	private void gameLoop() {
		timer = new Timer(GAME_LOOP,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				repaint();
				if(gameOver) {
					timer.stop();
				}
				ryuPlayer.fall();
				kenPlayer.fall();
				collision();
				isGameOver();
				
			}
		});
		timer.start();
	}
	private void loadPower() {
		ryuFullPower = new power(30,"RYU");
		kenFullPower = new power(GWIDTH -450,"KEN");
	}
	private void printFullPower(Graphics g) {
		ryuFullPower.printRectangle(g);
		kenFullPower.printRectangle(g);
	}
	private boolean isCollide() {
		int xDistance = Math.abs(ryuPlayer.getX() - kenPlayer.getX());
		int yDistance = Math.abs(ryuPlayer.getY() - kenPlayer.getY());
		int maxH =Math.max(ryuPlayer.getH(),kenPlayer.getH());
		int maxW =Math.max(ryuPlayer.getW(),kenPlayer.getW());
		return xDistance <=maxW-5  && yDistance <= maxH;
	}
	
	private void collision() {
		if(isCollide()) {
			if(ryuPlayer.isAttacking() && kenPlayer.isAttacking()) {
				ryuPlayer.setCurrentMove(DAMAGE);
				kenPlayer.setCurrentMove(DAMAGE);
			}
			else {
				if(ryuPlayer.isAttacking()) {
					kenPlayer.setCurrentMove(DAMAGE);
					kenFullPower.setHealth();
			}
				else if(kenPlayer.isAttacking()) {
						ryuPlayer.setCurrentMove(DAMAGE);
						ryuFullPower.setHealth();
					}	
			}
			ryuPlayer.setCollide(true);
			ryuPlayer.setSpeed(0);
			kenPlayer.setCollide(true);
			kenPlayer.setSpeed(0);
		}
		else {
			ryuPlayer.setCollide(false);
			ryuPlayer.setSpeed(SPEED);
			kenPlayer.setCollide(false);
			kenPlayer.setSpeed(SPEED);
		}
	}
	
	private void isGameOver() {
		if(ryuFullPower.getHealth()<=0 || kenFullPower.getHealth()<=0 ) {
			gameOver = true;
		}
	}
	private void printGameOver(Graphics pen) {
		if(gameOver) {
		pen.setColor(Color.WHITE);
		pen.setFont(new Font("Times",Font.BOLD,40));
		pen.drawString("GAME OVER", GWIDTH/2-120, GHEIGHT/2);
		}
	}
	public Board() throws IOException  {
		
		loadBackgroundImage();
		ryuPlayer = new RyuPlayer();
		kenPlayer = new KenPlayer();
		setFocusable(true);
		bindEvents();
		gameLoop();
		loadPower();
		
		
	}
	
	private void bindEvents() {
		this.addKeyListener(new KeyAdapter() {
			
			
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				ryuPlayer.setSpeed(0);
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					if(ryuPlayer.getX() <= 0) {
				      ryuPlayer.setCollide(true);
					}
					else {
					ryuPlayer.setCollide(false);
					ryuPlayer.setSpeed(-SPEED);
					ryuPlayer.move();}
					//repaint();
				}
				else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					ryuPlayer.jump();
					ryuPlayer.setCurrentMove(JUMP);
					
				}
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					ryuPlayer.setSpeed(SPEED);
					ryuPlayer.move();
					//repaint();
				}
				// Ryu Kick
				else if (e.getKeyCode()== KeyEvent.VK_K) {
					ryuPlayer.setCurrentMove(KICK);
				}
				// Ryu Punch
				else if (e.getKeyCode()== KeyEvent.VK_P) {
					ryuPlayer.setCurrentMove(PUNCH);
				}
				
				
				// Ken forward
				else if (e.getKeyCode() == KeyEvent.VK_A) {
					kenPlayer.setSpeed(-SPEED);
					kenPlayer.move();
					//repaint();
				}
				// Ken backward
				else if (e.getKeyCode() == KeyEvent.VK_D) {
					if(kenPlayer.getX() >= 1100) {
						
					      kenPlayer.setCollide(true);
						}
					else {
					kenPlayer.setCollide(false);
					kenPlayer.setSpeed(SPEED);
					kenPlayer.move();}
					//repaint();
				}
				
				else if (e.getKeyCode() == KeyEvent.VK_S) {
					kenPlayer.jump();
					kenPlayer.setCurrentMove(JUMP);
				}
				
				else if (e.getKeyCode()== KeyEvent.VK_Q) {
					kenPlayer.setCurrentMove(PUNCH);
				}
				
				else if (e.getKeyCode()== KeyEvent.VK_E) {
					kenPlayer.setCurrentMove(KICK);
				}
				
//				else if(e.getKeyCode() == KeyEvent.VK_A) {
//					kenPlayer.setCurrentMove(HADOWKEN);
//				}
			}
		});
	}
	
	
	
	@Override
	public void paintComponent(Graphics pen) {
		// Rendering / Painting
		super.paintComponent(pen);
		printBackgroundImage(pen);
		ryuPlayer.printPlayer(pen);
		kenPlayer.printPlayer(pen);
		printFullPower(pen);
		printGameOver(pen);
		
	}

	
	private void printBackgroundImage(Graphics pen) {
		pen.drawImage(imageBg,0,-50, 1300,760, null);
	}
	
	
	
	private void loadBackgroundImage() {
		try {
			imageBg = ImageIO.read(Board.class.getResource(BG_IMAGE));
			}
			catch(Exception ex) {
				System.out.println("Background Image Loading Fail...");
				System.exit(0);
			
			}
	}
}
