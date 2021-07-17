package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Block extends GameObject{
	
	private BufferedImage block_image;
	
	public Block(int x, int y, ID id, Spritesheet ss) {
		super(x, y, id, ss);
		
		block_image = ss.grabImage(1, 1, 16, 16, 16);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(block_image, (int)x, (int)y, null);
		
		Graphics2D g2d = (Graphics2D)g;
		
		//g.setColor(Color.blue);
		//g2d.drawRect((int)x, (int)y, 16, 16);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}
