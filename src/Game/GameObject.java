package Game;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected float x, y;
	protected ID id;
	protected float valX, valY;
	protected Spritesheet ss;
	protected boolean falling = true;
	protected boolean jumping = false;
	protected float gravity = 0.4f;
	protected float maxspeed;
	protected float walkspeed;
	
	
	public float getMaxSpeed() {
		return maxspeed;
	}
	
	public void setMaxSpeed(float maxspeed) {
		this.maxspeed = maxspeed;
	}
	
	public float getWalkSpeed() {
		return walkspeed;
	}
	
	public void setWalkspeed(float walkspeed) {
		this.walkspeed = walkspeed;
	}
	
	public boolean isFalling() {
		return falling;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}
	
	public float getGravity() {
		return gravity;
	}
	
	
	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public GameObject(float x, float y, ID id, Spritesheet ss) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.ss = ss;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setvalY(float valY) {
		this.valY = valY;
	}
	
	public void setvalX(float valX) {
		this.valX = valX;
	}
	
	public float getvalX() {
		return valX;
	}
	
	public float getvalY() {
		return valY;
	}
	
	public ID getID() {
		return id;
	}
	
	public void setID(ID id) {
		this.id = id;
	}
}
