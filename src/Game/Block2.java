package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Block2 extends GameObject{
	
	private BufferedImage block_image;
	
	public Block2(int x, int y, ID id, Spritesheet ss) {
		super(x, y, id, ss);
		
		block_image = ss.grabImage(1, 2, 16, 16, 16);
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
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}
