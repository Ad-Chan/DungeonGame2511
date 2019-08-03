package unsw.dungeon;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

// Enemy Class (to be modified)
public class Enemy extends Entity {
	
	private int id;
	private Dungeon dungeon;
	private int health;
	private boolean runAway;
	public Enemy(int x, int y, int id, Dungeon dungeon) {
		super(x, y);
		this.id = id;
		Timer timer = new Timer();
		timer.schedule(new moveTimer(),0,500);
		this.dungeon = dungeon;
		this.health = 1;
		this.runAway = false;
	}
	
	@Override
	public boolean objective() {
		return true;
	}
	
    public void moveUp() {
        if (!this.dungeon.getPlayer().findObstacles(getX(), getY()-1) == true) {    
        	y().set(getY() - 1);
        	this.findPlayer(this.dungeon);
        }
    }

    public void moveDown() {
        if (!this.dungeon.getPlayer().findObstacles(getX(), getY()+1) == true) {    
        	y().set(getY() + 1);
        	this.findPlayer(this.dungeon);
        }
    }

    public void moveLeft() {
    	if (!this.dungeon.getPlayer().findObstacles(getX()-1, getY()) == true) {    
        	x().set(getX() - 1);
        	this.findPlayer(this.dungeon);
        }
    }

    public void moveRight() {
        if (!this.dungeon.getPlayer().findObstacles(getX()+1, getY()) == true) {    
        	x().set(getX() + 1);
        	this.findPlayer(this.dungeon);
        }
    }
    
    public void moveEnemy(Runnable runnable) {
    	if (this.runAway == false && this.health == 1) {
	    	if (this.getPlayerX() > this.getX()) {
	    		this.moveRight();
	    	}
	    	if (this.getPlayerY() > this.getY()) {
	    		this.moveDown();
	    	}
	    	if (this.getPlayerX() < this.getX()) {
	    		this.moveLeft();
	    	}
	    	if (this.getPlayerY() < this.getY()) {
	    		this.moveUp();
	    	}
    	} else if (this.runAway == true && this.health == 1){
	    	if (this.getPlayerX() > this.getX()) {
	    		this.moveLeft();
	    	}
	    	if (this.getPlayerY() > this.getY()) {
	    		this.moveUp();
	    	}
	    	if (this.getPlayerX() < this.getX()) {
	    		this.moveRight();
	    	}
	    	if (this.getPlayerY() < this.getY()) {
	    		this.moveDown();
	    	}
    	}
    }
    
    public void findPlayer(Dungeon dungeon) {
    	ArrayList<Entity> entities = dungeon.findEntity(this.getX(), this.getY());
    	for (Entity e: entities) {
    		if (e.equals(dungeon.getPlayer())) {
    			dungeon.killPlayer(this);
    		}
    	}
    }
    
    public void decrementHealth() {
    	this.health--;
    }
    
    public void setDungeon(Dungeon dungeon) {
    	this.dungeon = dungeon;
    }
    
    public void setRunAway(boolean b) {
    	this.runAway = b;
    }
        
    
	@Override
    public boolean playerWalksInto(Player player) {
		player.attackEnemy(this);
		return false;
    }

    class moveTimer extends TimerTask {

    	@Override
    	public void run() {
    		Platform.runLater(new Runnable() {
  		       public void run() {
  		    		Enemy.this.moveEnemy(this); 
  		      }
  		    });
 		
    	}
    	
    	
    }
}