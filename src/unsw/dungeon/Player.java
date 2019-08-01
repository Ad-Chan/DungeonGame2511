package unsw.dungeon;

import java.util.ArrayList;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements PlayerPos{

    private Dungeon dungeon;
    private ArrayList<Collectable> inventory;
    ArrayList<PlayerPosObserver> observers;
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
    }

    public void moveUp() {
        //if (getY() > 0 && !nextToPlayer(getX(), getY()-1))
            //y().set(getY() - 1);
        if (!findObstacles(getX(), getY()-1) == true) {
        	y().set(getY() - 1); 
        	pickupCollectables(this.getX(), this.getY());
        	notifyObservers();
        }
    }

    public void moveDown() {
        //if (getY() < dungeon.getHeight() - 1 && !nextToPlayer(getX(), getY()+1))
            //y().set(getY() + 1);
        if (!findObstacles(getX(), getY()+1) == true) {
        	y().set(getY() + 1);
        	pickupCollectables(this.getX(), this.getY());
        	notifyObservers();      	
        }
    }

    public void moveLeft() {
        //if (getX() > 0 && !nextToPlayer(getX()-1, getY()))
            //x().set(getX() - 1);
        if (!findObstacles(getX()-1, getY()) == true) {
        	x().set(getX() - 1);
        	pickupCollectables(this.getX(), this.getY());
        	notifyObservers();       	
        }

    }

    public void moveRight() {
        //if (getX() < dungeon.getWidth() - 1 && !nextToPlayer(getX()+1, getY()))
            //x().set(getX() + 1);
        if (!findObstacles(getX()+1, getY()) == true) {
        	x().set(getX() + 1);
        	pickupCollectables(this.getX(), this.getY());
        	notifyObservers();       	
        }

    }
    
    public void addCollectable(Collectable item) {
    	this.inventory.add(item);
    	System.out.println(item);
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
    
    public void pickupCollectables(int x, int y) {
    	ArrayList<Entity> entities = dungeon.findEntity(x, y);
    	for (Entity e: entities) {
    		e.playerWalksInto(this);
    	}
    }
    
    public void attackEnemy(Enemy e) { 
    	for (Collectable c: inventory) {
    		if (c instanceof Sword) {
    			if (((Sword)c).getHealth() > 1) {
        			dungeon.removeEntity(e);
        			((Sword)c).decrementHealth();   				
    			} else if (((Sword)c).getHealth() == 1){
        			dungeon.removeEntity(e);
        			((Sword)c).decrementHealth();
        			this.removeCollectable(c);
    			}
    		}
    	}
    	if (dungeon.findSpecificEntity(e) != null) {
    		//player should die
    	}
    }
    
    public void activatePotion() {
    	
    }
    
    /*public void placeBomb() {
    	for (Collectable e: this.inventory) {
    		if (e.getEntityName().equals("UnlitBomb")) {
    			LitBomb newBomb = new LitBomb(this.getX(), this.getY());
    			dungeon.addEntity(newBomb);
    			this.inventory.remove(e);
    			break;
    		}
    	}
    }*/
    
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
}
