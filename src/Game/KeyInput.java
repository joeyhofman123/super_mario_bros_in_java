package Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	
	public boolean[] keyDown = new boolean[5];
	public int jumpduration = 10;
	private Menu menu;
	
	public KeyInput(Handler handler, Menu menu) {
		this.handler = handler;
		this.menu = menu;
		keyDown[0] = false; //right
		keyDown[1] = false; //left
		keyDown[2] = false; //z = jump
		keyDown[3] = false; //x = run/shoot
		keyDown[4] = false; //space = pause
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(Game.gameState == GameState.GAME) {
		
		for(int i = 0; i < handler.objects.size(); i++) {
			if(handler.objects.get(i).getID() == ID.PLAYER) {
				
				if(key == KeyEvent.VK_LEFT) { 
					keyDown[1] = true;
					handler.objects.get(i).setvalX(-handler.objects.get(i).getWalkSpeed());
				}
				
				if(key == KeyEvent.VK_RIGHT) {
					keyDown[0] = true;
					handler.objects.get(i).setvalX(handler.objects.get(i).getWalkSpeed());
				}
				
				if(key == KeyEvent.VK_Z && !handler.objects.get(i).isJumping()) {
//					AudioPlayer.getSound("jump_small").play(1f, 0.3f);
					handler.objects.get(i).setGravity(0.25f);
					keyDown[2] = true;
					
					handler.objects.get(i).setJumping(true);
					handler.objects.get(i).setvalY(-6);
					
				}
				
				if((key == KeyEvent.VK_X) && keyDown[1]) {
					keyDown[3] = true;
					handler.objects.get(i).setvalX(-handler.objects.get(i).getMaxSpeed());
				}
				
				if((key == KeyEvent.VK_X) && keyDown[0]) {
					keyDown[3] = true;
					handler.objects.get(i).setvalX(handler.objects.get(i).getMaxSpeed());
				}
			}
		
		
		if(key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}
		
		}else if(Game.gameState == GameState.MENU) {
			if(key == KeyEvent.VK_ENTER) {
			}
		}
}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(Game.gameState == GameState.GAME) {
		for(int i = 0; i < handler.objects.size(); i++) {
			if(handler.objects.get(i).getID() == ID.PLAYER) {
				
				if(key == KeyEvent.VK_LEFT) {
					keyDown[1] = false;
					handler.objects.get(i).setvalX(0);
				}
				
				if(key == KeyEvent.VK_RIGHT) {
					keyDown[0] = false;
					handler.objects.get(i).setvalX(0);
				}
				
				if((key == KeyEvent.VK_X) && keyDown[1]) {
					keyDown[3] = false;
					handler.objects.get(i).setvalX(-handler.objects.get(i).getWalkSpeed());
				}
				
				if((key == KeyEvent.VK_X) && keyDown[0]) {
					keyDown[3] = false;
					handler.objects.get(i).setvalX(handler.objects.get(i).getWalkSpeed());
				}
				
				if((key == KeyEvent.VK_Z)) {
					keyDown[2] = false;
					handler.objects.get(i).setGravity(0.7f);
				}
				
			}
		}
		
		}else if(Game.gameState == GameState.MENU) {
			if(key == KeyEvent.VK_ENTER) {
				if(menu.menuindex == 1) {
					try{
						Game.gameState = GameState.LIVES;
						Thread.sleep(3000);
						Game.gameState = GameState.GAME;
//						AudioPlayer.getMusic("background_overworld").loop(1f, 0.6f);
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				}else if(menu.menuindex == 3) {
					System.exit(1);
				}
				
				
			}
			
			if(key == KeyEvent.VK_DOWN) {
				switch(menu.menuindex) {
				case 1:
					menu.menuindex = 2;
				break;
				
				case 2:
					menu.menuindex = 3;
				break;
				}
			}
			
			if(key == KeyEvent.VK_UP) {
				switch(menu.menuindex) {
				case 2:
					menu.menuindex = 1;
				break;
				
				case 3:
					menu.menuindex = 2;
				break;
				}
			}
		}
		
	}
	
}
