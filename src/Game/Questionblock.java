package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Questionblock extends GameObject{
	
	private BufferedImage block_image;
	private Animation blink;
	public String powerup = "COIN";
	
	public Questionblock(int x, int y, ID id, Spritesheet ss) {
		super(x, y, id, ss);
		
		block_image = ss.grabImage(25, 1, 16, 16, 16);
		
		blink = new Animation(7, 
				ss.grabImage(25, 1, 16, 16, 16),
				ss.grabImage(25, 1, 16, 16, 16),
				ss.grabImage(26, 1, 16, 16, 16),
				ss.grabImage(27, 1, 16, 16, 16),
				ss.grabImage(26, 1, 16, 16, 16)
				);
	}

	@Override
	public void tick() {
		blink.runAnimation();
	}

	@Override
	public void render(Graphics g) {
		
		blink.drawAnimation(g, (int)x, (int)y);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}
