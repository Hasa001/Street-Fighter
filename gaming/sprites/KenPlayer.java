package gaming.sprites;

//import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//import com.skillrisers.gaming.utils.GameConstants;

public class KenPlayer extends Player {
	private BufferedImage walkImages [] = new BufferedImage[8];
	private BufferedImage kickImages[] = new BufferedImage[7]; 
	private BufferedImage punchImages[] = new BufferedImage[11];
	private BufferedImage jumpImages[] = new BufferedImage[8];
	private BufferedImage damageEffect[] = new BufferedImage[4];
	public KenPlayer() throws IOException {
		x = GWIDTH - 350;
		h = 200;
		w = 200;
		y = FLOOR - h;
		speed = 0;
		image = ImageIO.read(RyuPlayer.class.getResource(KEN_IMAGE));
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
		//walkImages[0]  = image.getSubimage(370, 40,55,106);
		//walkImages[0]  = image.getSubimage(447, 41,58,105);
		//walkImages[0]  = image.getSubimage(368,38,56,107);
		//walkImages[1]  = image.getSubimage(448, 40,55,105);
		//walkImages[3]  = image.getSubimage(529, 41,53,104);
		walkImages[0]  = image.getSubimage(1118, 38,57,106);
		walkImages[1]  = image.getSubimage(1037, 40,58,103);
		walkImages[2]  = image.getSubimage(966, 36,52,108);
		walkImages[3]  = image.getSubimage(892, 37,52,111);
		walkImages[4]  = image.getSubimage(819, 38,53,108);
		walkImages[5]  = image.getSubimage(742, 38,55,108);
		walkImages[6]  = image.getSubimage(666, 37,60,110);
		walkImages[7]  = image.getSubimage(592, 41,68,108);
//		walkImages[8]  = image.getSubimage(1485, 868,60,90);
//		walkImages[9]  = image.getSubimage(1414, 864,58,93);
//		walkImages[10]  = image.getSubimage(1340, 868,59,90);
//		walkImages[11]  = image.getSubimage(1269, 867,61,90);
	}
	
	private void loadKickImages() {
		kickImages[0] = image.getSubimage(636, 1994,59,105);
		kickImages[1] = image.getSubimage(544, 1996,56,105);
		kickImages[2] = image.getSubimage(451, 1995,65,106);
		kickImages[3] = image.getSubimage(315, 1991,102,109);
		kickImages[4] = image.getSubimage(194,1993,104,109);
		kickImages[5] = image.getSubimage(109, 1995,67,106);
		kickImages[6] = image.getSubimage(31, 1999,52,106);
//		kickImages[7] = image.getSubimage(403, 2642,95,98);
//		kickImages[8] = image.getSubimage(288, 2641,73,101);
//		kickImages[9] = image.getSubimage(193, 2639,64,103);
//		kickImages[10] = image.getSubimage(116, 2637,52,104);
	}
	
	private void loadPunchImages() {
		punchImages[0] = image.getSubimage(470,1548,58,102);
		punchImages[1] = image.getSubimage(561,1548,87,101);
		punchImages[2] = image.getSubimage(671,1547,87,102);
		punchImages[3] = image.getSubimage(772,1547,95,100);
		punchImages[4] = image.getSubimage(882,1547,104,98);
		punchImages[5] = image.getSubimage(1004,1547,87,99);
		punchImages[6] = image.getSubimage(1112,1543,84,104);
		punchImages[7] = image.getSubimage(1219,1543,83,102);
		punchImages[8] = image.getSubimage(1329,1541,83,103);
		punchImages[9] = image.getSubimage(1439,1538,70,104);
		punchImages[10] = image.getSubimage(1535,1539,56,103);
		
	}
	
	private void loadJumpImages() {
		jumpImages[0] = image.getSubimage(877,192,51,127);
		jumpImages[1] = image.getSubimage(801,192,52,110);
		jumpImages[2] = image.getSubimage(694,200,78,54);
		jumpImages[3] = image.getSubimage(627,184,54,67);
		jumpImages[4] = image.getSubimage(533,183,70,67);
		jumpImages[5] = image.getSubimage(438,189,70,62);
		jumpImages[6] = image.getSubimage(359,181,57,100);
		jumpImages[7] = image.getSubimage(274,180,60,115);
//		punchImages[4] = image.getSubimage(1000,1261,89,95);
//		punchImages[5] = image.getSubimage(932,1265,60,93);
		
	}
	
public void loaddamageEffect() {
	damageEffect[0] = image.getSubimage(543,4954,69,105);
	damageEffect[1] = image.getSubimage(633,4959,80,102);
	damageEffect[2] = image.getSubimage(736,4959,91,103);
	damageEffect[3] = image.getSubimage(856,4963,58,102);
//	damageEffect[4] = image.getSubimage(533,183,70,67);
//	damageEffect[5] = image.getSubimage(438,189,70,62);
//	damageEffect[6] = image.getSubimage(359,181,57,100);
//	damageEffect[7] = image.getSubimage(274,180,60,115);
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
		if(imageIndex>6) {
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
		if(imageIndex>10) {
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
	
	private BufferedImage printDamage() {
		if(imageIndex>3) {
			imageIndex = 0;
			currentMove = WALK;
		}
		BufferedImage img =damageEffect[imageIndex];
		imageIndex++;
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
		 else if(currentMove == JUMP) {
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
