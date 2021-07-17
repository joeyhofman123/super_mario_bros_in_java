package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{
	
	public static final int WIDTH = 356;
	public static final int HEIGHT = 340;
	private Handler handler;
	
	private BufferedImage level = null;
	private BufferedImage sprite_sheet = null;
	private Spritesheet ss;
	
	private Menu menu;
	
	private BufferedImage char_sprites = null;
	private Spritesheet sschar;
	
	 Thread thread;
	public boolean running = false;
	Camera cam;
	
	public static GameState gameState = GameState.MENU;
	
	public Lives lives;
	public static String levelString = "1-1";
	public static int playerLives = 3; 
	
	
	public Game() {
		
	if(gameState == GameState.GAME) {
		handler = new Handler();
		cam = new Camera(0, 0);
		new Window(WIDTH, HEIGHT, "super mario bros", this);
		menu = new Menu();
		this.addKeyListener(new KeyInput(handler, menu));
		
//		AudioPlayer.Load();
		
//		AudioPlayer.getMusic("background_overworld").loop(1f, 0.6f);
		
		Bufferdimageloader loader = new Bufferdimageloader();
		
		level = loader.loadImage("/level.png");
		
		sprite_sheet = loader.loadImage("/tiles.png");
		ss = new Spritesheet(sprite_sheet);
		
		
		char_sprites = loader.loadImage("/chars.png");
		sschar = new Spritesheet(char_sprites);
		
		loadLevel(level);
		}else if(gameState == GameState.MENU) {
			handler = new Handler();
			cam = new Camera(0, 0);
			new Window(WIDTH, HEIGHT, "super mario bros", this);
			Bufferdimageloader loader = new Bufferdimageloader();
			menu = new Menu();
			this.addKeyListener(new KeyInput(handler, menu));

			
//			AudioPlayer.Load();
			
			level = loader.loadImage("/level.png");
			
			sprite_sheet = loader.loadImage("/tiles.png");
			ss = new Spritesheet(sprite_sheet);
			
			
			char_sprites = loader.loadImage("/chars.png");
			sschar = new Spritesheet(char_sprites);
			
			lives = new Lives(sschar);
			
			loadLevel(level);
		}else {
			//constructor lives
			
			handler = new Handler();
			Bufferdimageloader loader = new Bufferdimageloader();
			
			char_sprites = loader.loadImage("/chars.png");
			sschar = new Spritesheet(char_sprites);
			
			
			new Window(WIDTH, HEIGHT, "super mario bros", this);
			lives = new Lives(sschar);
		}
	}
	
	
	public synchronized void start() {
			thread = new Thread(this);
			thread.start();
			running = true;
	}
	
	public synchronized void stop() {
		try{
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ms = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ms;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
				frames++;
				
				if(System.currentTimeMillis() - timer > 1000) {
					timer += 1000;
					System.out.println("FPS: " + frames);
					frames = 0;
				}
			}
			stop();
		}
	
	private void tick() {
		if(gameState == GameState.GAME) {
				handler.tick();
		
			for(int i = 0; i < handler.objects.size(); i++) {
			
				if(handler.objects.get(i).getID() == ID.PLAYER) {
					cam.tick(handler.objects.get(i));
				}
			}
			
		}else if(gameState == GameState.MENU) {
			
		}else {
			//tick lives
			lives.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(new Color(93,148,251));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(gameState == GameState.GAME) {
		g2d.translate(cam.getX(), cam.getY());
		handler.render(g);
		g2d.translate(-cam.getX(), -cam.getY());
		
		}else if(gameState == gameState.MENU) {
			menu.render(g);
		}else {
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			lives.render(g);
		}
		bs.show();
		g.dispose();
	}
	
	
	public void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		
		for(int xx = 0; xx < w; xx++) {
			for(int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255 && green == 0 && blue == 0) {
					handler.addObject(new Block(xx*16, yy*16, ID.BLOCK, ss));
				}
				
				if(blue == 255 && red == 0 && green == 0) {
					handler.addObject(new Player(xx*16, yy*16, ID.PLAYER, sschar, handler));
					
				}
				
				if(blue == 0 && green == 255 && red == 255) {
					handler.addObject(new Questionblock(xx*16, yy*16, ID.QUESTIONBLOCK, ss));
				}
				
				if(blue == 255 && green == 255 && red == 0) {
					handler.addObject(new Breakblock1(xx*16, yy*16, ID.BREAKBLOCK1, ss));
				}
				
				if(blue == 0 && green == 255 && red == 0) {
					handler.addObject(new Greenpipetop(xx*16, yy*16, ID.GREENPIPETOP, ss));
				}
				
				if(blue == 0 && green == 100 && red == 0) {
					handler.addObject(new GreenpipeBottom(xx*16, yy*16, ID.GREENPIPEBOTTOM, ss));
				}
				
				if(red == 185 && green == 122 && blue == 86) {
					handler.addObject(new Goomba(xx*16, yy*16, ID.GOOMBABROWN, sschar, handler));
				}
				
				if(red == 14 && green == 209 && blue == 69) {
					handler.addObject(new koopa(xx*16, yy*16, ID.KOOPAGREEN, sschar, handler));
				}
				
				if(red == 255 && green == 127 && blue == 39) {
					handler.addObject(new Block2(xx*16, yy*16, ID.BLOCK2, ss));
				}
				
				
			}
		}
	}
	
	
	
		public static void main(String[] args) {
			new Game();
			
		}
}	