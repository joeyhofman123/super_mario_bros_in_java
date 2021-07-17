package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Menu {
	
	public int menuindex = 1;
	
	public void render(Graphics g) {
		g.setFont(new Font("arial", 2, 30));
		g.setColor(Color.white);
		g.drawString("Super mario bros", Game.WIDTH / 2 - 120, Game.HEIGHT / 2 - 100);
		g.setFont(new Font("arial", 1, 10));
		g.drawString("1 PLAYER GAME", Game.WIDTH / 2 - 50, Game.HEIGHT / 2);
		g.drawString("2 PLAYER GAME", Game.WIDTH / 2 - 50, Game.HEIGHT / 2 + 25);
		g.drawString("EXIT", Game.WIDTH / 2 - 50, Game.HEIGHT / 2 + 50);
		
		if(menuindex == 1) {
			g.fillRect(Game.WIDTH / 2 - 70, Game.HEIGHT / 2 - 10, 16, 16);	
		}else if(menuindex == 2){
			g.fillRect(Game.WIDTH / 2 - 70, Game.HEIGHT / 2 + 15, 16, 16);
		}else if(menuindex == 3){
			g.fillRect(Game.WIDTH / 2 - 70, Game.HEIGHT / 2 + 37, 16, 16);
		}
	}
}