package Game;

import java.awt.image.BufferedImage;

public class Spritesheet {
	
	public BufferedImage image;
	
	public Spritesheet(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height, int spriteSet) {
		return image.getSubimage(col* spriteSet - spriteSet, row * spriteSet - spriteSet, width, height);
	}
}
