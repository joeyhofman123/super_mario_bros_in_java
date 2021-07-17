package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class GreenpipeBottom extends GameObject{
	
	private BufferedImage block_image;
	
	public GreenpipeBottom(int x, int y, ID id, Spritesheet ss) {
		super(x, y, id, ss);
		
		block_image = ss.image.getSubimage(0, 175, 32, 32);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(block_image, (int)x, (int)y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
