package unsw.dungeon;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements PlayerPos{

    private Dungeon dungeon;
    private ArrayList<Collectable> inventory;
    ArrayList<PlayerPosObserver> observers;
    private int potionTime;
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.inventory = new ArrayList<Collectable>();
        this.observers = new ArrayList<PlayerPosObserver>();
        this.potionTime = 0;
    }
    
	public void potionTick() {
		Timer timer = new Timer();
		timer.schedule(new potionTimer(),0,1000);	
	}
	
	public void decrementPotionTime(Player.potionTimer timer) {
		if (this.potionTime > 0) {
			this.potionTime--;	
		} else {
			timer.cancel();
		}
	}
	
	class potionTimer extends TimerTask {

    	@Override
    	public void run() {
    		decrementPotionTime(this);
    	}
    }

    public void moveUp() {
        if (!findObstacles(getX(), getY()-1) == true) {
        	y().set(getY() - 1); 
        	pickupCollectables(this.getX(), this.getY());
        	dungeon.checkSwitches();
        	notifyObservers();
        }
    }

    public void moveDown() {
        if (!findObstacles(getX(), getY()+1) == true) {
        	y().set(getY() + 1);
        	pickupCollectables(this.getX(), this.getY());
        	dungeon.checkSwitches();
        	notifyObservers();      	
        }
    }

    public void moveLeft() {
        if (!findObstacles(getX()-1, getY()) == true) {
        	x().set(getX() - 1);
        	pickupCollectables(this.getX(), this.getY());
        	dungeon.checkSwitches();
        	notifyObservers();       	
        }

    }

    public void moveRight() {
        if (!findObstacles(getX()+1, getY()) == true) {
        	x().set(getX() + 1);
        	pickupCollectables(this.getX(), this.getY());
        	dungeon.checkSwitches();
        	notifyObservers();       	
        }

    }
    
    public void addCollectable(Collectable item) {
    	//dungeon.removeEntity(item);
    	this.inventory.add(item);
    }
    
    public void removeCollectable(Collectable item) {
    	this.inventory.remove(item);
    }
    
    public ArrayList<Collectable> getInventory() {
    	return this.inventory;
    }
    
    public boolean findObstacles(int x, int y) {
    	ArrayList<Entity> entities = dungeon.findEntity(x, y);
    	for (Entity e : entities) {
    		if (e.isObstacle(x, y, this) == true) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean findEnemyObstacles(int x, int y) {
    	ArrayList<Entity> entities = dungeon.findEntityNoEnemy(x, y);
    	for (Entity e : entities) {
    		if (e.isObstacle(x, y, this) == true) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public ArrayList<Entity> findEntity(int x, int y) {
    	return dungeon.findEntity(x, y);
    }
    
    public void pickupCollectables(int x, int y) {
    	ArrayList<Entity> entities = dungeon.findEntity(x, y);
    	for (Entity e: entities) {
    		if (e.playerWalksInto(this)== true) {
    			dungeon.addToInventory(e);
    		}
    	}
    }
    
    
    public void attackEnemy(Enemy e) { 
    	for (Collectable c: inventory) {  		
    		if (c instanceof Sword) {
    			if (((Sword)c).getHealth() > 1) {
        			e.decrementHealth();
    				dungeon.removeEntity(e);
        			((Sword)c).decrementHealth();   				
    			} else if (((Sword)c).getHealth() == 1){
        			e.decrementHealth();
    				dungeon.removeEntity(e);
        			((Sword)c).decrementHealth();
        			this.removeCollectable(c);
    			}
    		}
    	}
    	if (this.potionTime > 0) {
			e.decrementHealth();
			dungeon.removeEntity(e);
    	}
    	if (dungeon.findSpecificEntity(e) != null) {
    		dungeon.removeEntity(this);
    	}
    }
    
    public void activatePotion(InvincibilityPotion i) {
    	this.potionTime += i.getTime_limit();
    	this.potionTick();
    }
    
    public void placeBomb() {
    	for (Collectable e: this.inventory) {
    		if (e instanceof UnlitBomb) {
    			LitBomb newBomb = new LitBomb(this.getX(), this.getY(), this);
    			dungeon.addEntity(newBomb);
    			this.inventory.remove(e);
    			//newBomb.updateBomb();
    			break;
    		}
    	}
    }
    
	@Override
	public void attachObserver(PlayerPosObserver p) {
		if(!this.observers.contains(p)) {
			this.observers.add(p);
		}
		notifyObservers();
	}

	@Override
	public void detachObserver(PlayerPosObserver p) {
		this.observers.remove(p);
	}

	@Override
	public void notifyObservers() {
		for (PlayerPosObserver p : this.observers) {
			p.update(this);
		}
	}
	
	public ArrayList<PlayerPosObserver> getObservers() {
		return this.observers;
	}
	
	public void updateImage(Entity e) {
		this.dungeon.updateImage(e);
	}
	
	public Dungeon getDungeon() {
		return this.dungeon;
	}
}
