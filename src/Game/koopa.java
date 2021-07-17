package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.time.Duration;

public class koopa extends GameObject{

	private BufferedImage playerImage;
	private float gravity = 0.4f;
	
	private int width = 16, height = 24;
	private Handler handler;
	private BufferedImage block_image;
	
	
	private Animation Koopawalk;
	
	public koopa(int x, int y, ID id, Spritesheet ss, Handler handler) {
		super(x, y, id, ss);
		this.handler = handler;
		
		Koopawalk = new Animation(10,
				ss.image.getSubimage(181, 206, 16, 24), 
				ss.image.getSubimage(200, 206, 16, 24));
		block_image = ss.image.getSubimage(181, 206, 16, 24);

		
	}
	
	

	@Override
	public void tick() {
		x += valX;
		y += valY;
		
		if(valX == 0) {
			valX *= -1;
		}
		valX = -1.25f;
		
		if(falling || jumping) {
			valY += gravity;
		}
		
		colission();
		Koopawalk.runAnimation();
	}
	
	

	public void render(Graphics g) {
		
		if(valX != 0) {
			Koopawalk.drawAnimation(g, (int)x, (int)y);
		}
		
		g.drawImage(block_image, (int)x, (int)y, null);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x+(width / 2) - ((width / 2) / 2), (int) (y+(height / 2)), width / 2, height / 2);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y, 5, height);
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
					(tempobject.getID() == ID.GREENPIPETOP)) {
				
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
					x = tempobject.getX() - width - 1;
				}
				
				if(getBoundsLeft().intersects(tempobject.getBounds())) {
					x = tempobject.getX() + 16;
				}
			}
		}
	}

}
