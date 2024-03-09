package gaming.sprites;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class power extends Player{
	String playerName;
	public power(int x, String playerName) {
		
      	  this.x = x;
      	  this.playerName = playerName;
      	  y=50;
      	  h=30;
      	  w=400;
      	  
        }// TODO Auto-generated constructor stub
		
	@Override
	public BufferedImage defaultImage() {
		// TODO Auto-generated method stub
		return null;
	}
    public void printRectangle(Graphics pen) {
    	pen.setColor(Color.RED);
    	pen.fillRoundRect(x,y,w,h,30,30);
    	pen.setColor(Color.GREEN);
    	pen.fillRoundRect(x,y,health,h,30,30);
    	pen.setColor(Color.WHITE);
    	pen.setFont(new Font("Times", 10, 20));
    	
    	pen.drawString(playerName,x+20,y+h+20);
    }
}
