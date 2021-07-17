package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.time.Duration;

public class Mushroom extends GameObject{

	private BufferedImage playerImage;
	private float gravity = 0.4f;
	Bufferdimageloader bufferedimageloader;
	
	private int width = 16, height = 16;
	private Handler handler;
	private BufferedImage block_image;
	
	
	
	public Mushroom(int x, int y, ID id, Handler handler, Spritesheet ss) {
		super(x, y, id, ss);
		this.handler = handler;
		ss = null;
		this.bufferedimageloader = new Bufferdimageloader();
		
		
	}
	
	

	@Override
	public void tick() {
		x += valX;
		y += valY;
		
		valX = -1.25f;
		
		if(falling || jumping) {
			valY += gravity;
		}
		
		colission();
	}
	
	

	public void render(Graphics g) {
		
		g.drawImage(block_image, (int)x, (int)y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x+(width / 2) - ((width / 2) / 2), (int) (y+(height / 2)), width / 2, height / 2);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y, 5, height - 1);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int)x+width - 5, (int)y, 5, height);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int)x+(width / 2) - ((width / 2) / 2), (int)y, width / 2, height / 2);
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
					valX = 1.25f;
					x = tempobject.getX() - width - 1;
					
				}
				
				if(getBoundsLeft().intersects(tempobject.getBounds())) {
					valX = -1.25f;
					x = tempobject.getX() + 32;
				}
			}
		}
	}

}
