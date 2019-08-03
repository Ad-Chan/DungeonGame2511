package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

// Enemy Class (to be modified)
public class Enemy extends Entity {
	
	private int id;
	private Dungeon dungeon;
	private int health;
	public Enemy(int x, int y, int id, Dungeon dungeon) {
		super(x, y);
		this.id = id;
		Timer timer = new Timer();
		timer.schedule(new moveTimer(),0,1000);
		this.dungeon = dungeon;
		this.health = 1;
	}
	
    public void moveUp() {
        if (!this.dungeon.getPlayer().findObstacles(getX(), getY()-1) == true) {    
        	y().set(getY() - 1);
        }
    }

    public void moveDown() {
        if (!this.dungeon.getPlayer().findObstacles(getX(), getY()+1) == true) {    
        	y().set(getY() + 1);
        }
    }

    public void moveLeft() {
    	if (!this.dungeon.getPlayer().findObstacles(getX()-1, getY()) == true) {    
        	x().set(getX() - 1);
        }
    }

    public void moveRight() {
        if (!this.dungeon.getPlayer().findObstacles(getX()+1, getY()) == true) {    
        	x().set(getX() + 1);
        }
    }
    
    public void moveEnemy(Enemy.moveTimer timer) {
    	if (this.health <= 0) {
    		timer.cancel();
    	}
    	if (this.getPlayerX() > this.getX()) {
    		this.moveRight();
    		System.out.println("Right");
    	}
    	if (this.getPlayerY() > this.getY()) {
    		this.moveDown();
    		System.out.println("Up");
    	}
    	if (this.getPlayerX() < this.getX()) {
    		this.moveLeft();
    		System.out.println("Left");
    	}
    	if (this.getPlayerY() < this.getY()) {
    		this.moveUp();
    		System.out.println("Down");
    	}
    	
    }
    
    public void decrementHealth() {
    	this.health--;
    }
    
    public void setDungeon(Dungeon dungeon) {
    	this.dungeon = dungeon;
    }
        
    
	@Override
    public boolean playerWalksInto(Player player) {
		player.attackEnemy(this);
		return false;
    }

    class moveTimer extends TimerTask {

    	@Override
    	public void run() {
    		Enemy.this.moveEnemy(this);  		
    	}
    }
}