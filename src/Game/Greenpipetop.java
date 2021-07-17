package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Greenpipetop extends GameObject{
	
	private BufferedImage block_image;
	
	
	public Greenpipetop(int x, int y, ID id, Spritesheet ss) {
		super(x, y, id, ss);
		
		block_image = ss.image.getSubimage(1, 128, 31, 64);
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
