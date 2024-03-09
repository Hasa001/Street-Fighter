package gaming.sprites;

//import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//import com.skillrisers.gaming.utils.GameConstants;

public class RyuPlayer extends Player  {
	private BufferedImage walkImages [] = new BufferedImage[8];
	private BufferedImage kickImages[] = new BufferedImage[5]; 
	private BufferedImage punchImages[] = new BufferedImage[10];
	private BufferedImage jumpImages[] = new BufferedImage[8];
	private BufferedImage damageEffect[] = new BufferedImage[4];
	public RyuPlayer() throws IOException {
		x = 150;
		h = 200;
		w = 200;
		y = FLOOR - h;
		speed = 0;
		image = ImageIO.read(RyuPlayer.class.getResource(RYU_IMAGE));
		loadWalkImages();
		loadKickImages();
		loadPunchImages();
		loadJumpImages();
		loaddamageEffect();
	}
	
	public void jump() {
		if(!isJump) {
		isJump = true;
		force = -30;
		y = y + force;
		}
	}
	
	public void fall() {
		if(y>=(FLOOR-h)) {
			isJump = false;
			return ;
		}
		y = y + force;
		force = force + GRAVITY+2;
	}
	
	private void loadWalkImages() {
		//walkImages[]  = image.getSubimage(4, 640,64,90);
		walkImages[0]  = image.getSubimage(69, 635,71,95);
		walkImages[1]  = image.getSubimage(143,634,69,96);
		walkImages[2]  = image.getSubimage(220, 633,60,96);
		walkImages[3]  = image.getSubimage(294, 635,55,94);
		walkImages[4]  = image.getSubimage(363, 636,59,93);
		walkImages[5]  = image.getSubimage(427, 640,65,90);
		walkImages[6]  = image.getSubimage(496, 635,62,95);
		walkImages[7]  = image.getSubimage(564, 633,56,97);
		//walkImages[9]  = image.getSubimage(632, 633,54,96);
	}
	
	private void loadKickImages() {
		kickImages[0] = image.getSubimage(429, 2558,65,96);
		kickImages[1] = image.getSubimage(497, 2558,67,96);
		kickImages[2] = image.getSubimage(565, 2557,67,96);
		kickImages[3] = image.getSubimage(655, 2561,120,93);
		kickImages[4] = image.getSubimage(777,2558,61,96);
//		kickImages[5] = image.getSubimage(441, 2450,54,93);
//		kickImages[6] = image.getSubimage(488, 2426,56,116);
//		kickImages[7] = image.getSubimage(574, 2447,92,97);
//		kickImages[8] = image.getSubimage(683, 2451,54,91);
//		kickImages[9] = image.getSubimage(738, 2443,81,102);
	}
	
	private void loadPunchImages() {
		punchImages[0] = image.getSubimage(4,2324,80,93);
		punchImages[1] = image.getSubimage(92,2324,83,92);
		punchImages[2] = image.getSubimage(262,2317,91,100);
		punchImages[3] = image.getSubimage(361,2328,95,89);
		punchImages[4] = image.getSubimage(460,2326,103,91);
		punchImages[5] = image.getSubimage(676,2332,103,85);
		punchImages[6] = image.getSubimage(676,2332,103,85);
		punchImages[7] = image.getSubimage(676,2332,103,85);
		punchImages[8] = image.getSubimage(999,2315,84,102);
		punchImages[9] = image.getSubimage(1087,2322,65,95);
	}
	
	private void loadJumpImages() {
		jumpImages[0]  = image.getSubimage(6, 773,66,91);
		jumpImages[1]  = image.getSubimage(71, 745,71,96);
		jumpImages[2]  = image.getSubimage(161,765,116,55);
		jumpImages[3]  = image.getSubimage(282, 734,57,81);
		jumpImages[4]  = image.getSubimage(345, 758,95,50);
		jumpImages[5]  = image.getSubimage(446, 792,62,107);
		jumpImages[6]  = image.getSubimage(515, 804,63,119);
		jumpImages[7]  = image.getSubimage(582, 828,62,90);
		//jumpImages[8]  = image.getSubimage(656, 1204,58,91);
	}
	
	public void loaddamageEffect() {
		damageEffect[0] = image.getSubimage(574,4757,68,92);
		damageEffect[1] = image.getSubimage(483,4759,85,90);
		damageEffect[2] = image.getSubimage(401,4757,75,93);
		damageEffect[3] = image.getSubimage(325,4755,74,93);
//		damageEffect[4] = image.getSubimage(533,183,70,67);
//		damageEffect[5] = image.getSubimage(438,189,70,62);
//		damageEffect[6] = image.getSubimage(359,181,57,100);
//		damageEffect[7] = image.getSubimage(274,180,60,115);
		}
	private BufferedImage printDamage() {
		if(imageIndex>3) {
			imageIndex = 0;
			currentMove = WALK;
		}
		BufferedImage img =damageEffect[imageIndex];
		imageIndex++;
		return img;
	}
	private BufferedImage printWalk() {
		isAttacking=false;
		if(imageIndex>7) {
			imageIndex=0;
		}
		BufferedImage img = walkImages[imageIndex];
		imageIndex++; // Change Image Frames
		return img;
	}
	private BufferedImage printKick() {
		if(imageIndex>4) {
			imageIndex=0;
			currentMove = WALK;
			isAttacking =false;
		}
		isAttacking =true;
		BufferedImage img = kickImages[imageIndex];
		imageIndex++; // Change Image Frames
		return img;
	}
	private BufferedImage printPunch() {
		if(imageIndex>9) {
			imageIndex=0;
			currentMove = WALK;
			isAttacking =false;
		}
		isAttacking =true;
		BufferedImage img = punchImages[imageIndex];
		imageIndex++; // Change Image Frames
		return img;
	}
	private BufferedImage printJump() {
		if(imageIndex>7) {
			imageIndex=0;
			currentMove = WALK;
		}
		BufferedImage img = jumpImages[imageIndex];
		imageIndex++; // Change Image Frames
		return img;
	}
	
	
	@Override
	public BufferedImage defaultImage() {
		 if(currentMove == KICK) {
			return printKick();
		}
		 else if (currentMove == PUNCH) {
			 return printPunch();
		 }
		 else if (currentMove == JUMP) {
			 return printJump();
		 }
		 else if(currentMove == DAMAGE) {
			 return printDamage();
		 }
		 else {
			 return printWalk();
		 }
	}
	
	

}
