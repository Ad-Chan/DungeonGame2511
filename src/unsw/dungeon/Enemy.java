package unsw.dungeon;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

public class Enemy extends Entity {
	
	private Dungeon dungeon;
	private int health;
	private boolean runAway;
	public Enemy(int x, int y, Dungeon dungeon) {
		super(x, y);
		Timer timer = new Timer();
		timer.schedule(new moveTimer(),0,1000);
		this.setDungeon(dungeon);
		this.health = 1;
		this.runAway = false;
	}
	
	@Override
	public boolean objective() {
		return true;
	}
	
    public void moveUp() {
        if (!this.getDungeon().getPlayer().findObstacles(getX(), getY()-1) == true) {    
        	y().set(getY() - 1);
        	this.findPlayer(this.getDungeon());
        }
    }

    public void moveDown() {
        if (!this.getDungeon().getPlayer().findObstacles(getX(), getY()+1) == true) {    
        	y().set(getY() + 1);
        	this.findPlayer(this.getDungeon());
        }
    }

    public void moveLeft() {
    	if (!this.getDungeon().getPlayer().findObstacles(getX()-1, getY()) == true) {    
        	x().set(getX() - 1);
        	this.findPlayer(this.getDungeon());
        }
    }

    public void moveRight() {
        if (!this.getDungeon().getPlayer().findObstacles(getX()+1, getY()) == true) {    
        	x().set(getX() + 1);
        	this.findPlayer(this.getDungeon());
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
    
    public int getHealth() {
    	return this.health;
    }
    
    public void setDungeon(Dungeon dungeon) {
    	this.dungeon = dungeon;
    }
    
    public void setRunAway(boolean b) {
    	this.runAway = b;
    }
    
    public boolean getRunAway() {
    	return this.runAway;
    }
        
    
	@Override
    public boolean playerWalksInto(Player player) {
		player.attackEnemy(this);
		return false;
    }
	
	@Override
	public boolean isObstacle(int x, int y, Player p) {
		return true;
	}	

    public Dungeon getDungeon() {
		return dungeon;
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