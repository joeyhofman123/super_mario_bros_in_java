package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.time.Duration;

public class Player extends GameObject{

	private BufferedImage playerImage;
	
	private int width = 16, height = 16;
	private Handler handler;
	private KeyInput keyinput;
	final float MAX_SPEED = 5;
	private int jumpduration = 50;
	
	
	
	private Animation playerwalk;
	
	public Player(int x, int y, ID id, Spritesheet ss, Handler handler) {
		super(x, y, id, ss);
		this.handler = handler;
		gravity = 0.4f;
		
		playerwalk = new Animation(1,  
				ss.image.getSubimage(290, 44, 16, 16), 
				ss.image.getSubimage(304, 43, 16, 16), 
				ss.image.getSubimage(321, 44, 16, 16));
		
		
		//playerImage = ss.image.getSubimage(321, 44, 16, 16);
		playerImage = ss.image.getSubimage(276, 44, 16, 16);
		
		maxspeed = 3f;
		walkspeed = 1.5f;
	}
	
	

	@Override
	public void tick() {
		x += valX;
		y += valY;
		
		if(falling || jumping) {
			valY += gravity;
		}
		
		if(valY > MAX_SPEED) {
			valY = MAX_SPEED;
		}
		
		colission();
		playerwalk.runAnimation();
	}
	
	

	public void render(Graphics g) {
		
		if(valX > 0) {
			playerwalk.drawAnimation(g, (int)x, (int)y);
		}
		g.drawImage(playerImage, (int)x, (int)y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x+(width / 2) - ((width / 2) / 2) - 1, (int) (y+(height / 2)), width / 2, height / 2);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x + 1, (int)y, 5, height - 1);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int)x+width - 8, (int)y - 1, 5, height);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int)x+(width / 2) - ((width / 2) / 2) - 1, (int)y, width / 2, height / 2);
	}
	
	private void colission() {
		for(int i = 0; i < handler.objects.size(); i++) {
			GameObject tempobject = handler.objects.get(i);
			
			
			if(
					(tempobject.getID() == ID.BLOCK) ||
					(tempobject.getID() == ID.QUESTIONBLOCK) ||
					(tempobject.getID() == ID.BREAKBLOCK1) ||
					(tempobject.getID() == ID.GREENPIPETOP) ||
					(tempobject.getID() == ID.GREENPIPEBOTTOM) ||
					(tempobject.getID() == ID.BLOCK2)) {
				
				if(getBoundsTop().intersects(tempobject.getBounds())) {
					y = tempobject.getY() + 16;
					valY = 0;
				}
				
				if(getBounds().intersects(tempobject.getBounds())) {
					y = tempobject.getY() - height - 1;
					valY = 0;
					falling = false;
					jumping = false;
				}else {
					falling = true;
				}
				
				if(getBoundsRight().intersects(tempobject.getBounds())) {
					if(tempobject.getID() == ID.GREENPIPEBOTTOM || tempobject.getID() == ID.GREENPIPETOP) {
						x = tempobject.getX() - 15;
					}else {
						x = tempobject.getX() - width + 1;	
					}
				}
				
				if(getBoundsLeft().intersects(tempobject.getBounds())) {
					if(tempobject.getID() == ID.GREENPIPEBOTTOM || tempobject.getID() == ID.GREENPIPETOP) {
						x = tempobject.getX() + 31;
					}else {
						x = tempobject.getX() + width;	
					}
				}
			}
		}
	}

}
