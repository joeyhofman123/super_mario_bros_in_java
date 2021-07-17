package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;

public class Lives extends KeyAdapter{
	
	private String level = Game.levelString;
	private Spritesheet ss;
	private int Lives = Game.playerLives;
	
	public Lives(Spritesheet ss) {
		this.ss = ss;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setFont(new Font("arial", 1, 15));
		g.setColor(Color.white);
		g.drawString("WORLD " + level, Game.WIDTH / 2 - 60, Game.HEIGHT / 2 - 80);
		g.drawImage(ss.image.getSubimage(276, 44, 16, 16), Game.WIDTH / 2 - 60, Game.HEIGHT / 2 - 60, null);
		g.drawString("x", Game.WIDTH / 2 - 25, Game.HEIGHT / 2 - 45);
		g.drawString(String.valueOf(Lives), Game.WIDTH / 2 + 5, Game.HEIGHT / 2 - 45);
	}
}
