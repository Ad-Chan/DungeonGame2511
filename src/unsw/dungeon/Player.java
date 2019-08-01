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
        	notifyObservers();
        }
    }

    public void moveDown() {
        //if (getY() < dungeon.getHeight() - 1 && !nextToPlayer(getX(), getY()+1))
            //y().set(getY() + 1);
        if (!findObstacles(getX(), getY()+1) == true) {
        	y().set(getY() + 1);
        	notifyObservers();      	
        }
    }

    public void moveLeft() {
        //if (getX() > 0 && !nextToPlayer(getX()-1, getY()))
            //x().set(getX() - 1);
        if (!findObstacles(getX()-1, getY()) == true) {
        	x().set(getX() - 1);
        	notifyObservers();       	
        }

    }

    public void moveRight() {
        //if (getX() < dungeon.getWidth() - 1 && !nextToPlayer(getX()+1, getY()))
            //x().set(getX() + 1);
        if (!findObstacles(getX()+1, getY()) == true) {
        	x().set(getX() + 1);
        	notifyObservers();       	
        }

    }
    
    public void addCollectable(Collectable item) {
    	this.inventory.add(item);
    	System.out.println(item);
    }
    
    public ArrayList<Collectable> getInventory() {
    	return this.inventory;
    }
    
    public boolean findObstacles(int x, int y) {
    	ArrayList<Entity> entities = dungeon.findEntity(x, y);
    	for (Entity e : entities) {
    		if (e.isObstacle(x, y) == true) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /*public void unlockDoor() {
    	ArrayList<Entity> interactions = find_interaction("Key", "Door");
    	if (interactions.size() >= 2) {
	    	int keycode = ((Key)interactions.get(0)).getKeycode();
	    	for (Entity i: interactions) {
	    		if (i.getEntityName().equals("Door")) {
	    			if (((Door)i).unlockDoor(keycode)) {
	    				this.inventory.remove(interactions.get(0));
	    				keycode = -1;
	    			}
	    		}
	    	}
    	}
    }

    public void attackEnemy() { 
    	ArrayList<Entity> interactions = find_interaction("Sword", "Enemy");
    	for (Entity i: interactions) {
    		if (i.getEntityName().equals("Enemy") && interactions.get(0).getEntityName().equals("Sword")) {
    			dungeon.removeEntity(i);
    			((Sword)interactions.get(0)).decrementHealth();
    		}
    	}
    }
    
    public void placeBomb() {
    	for (Collectable e: this.inventory) {
    		if (e.getEntityName().equals("UnlitBomb")) {
    			LitBomb newBomb = new LitBomb(this.getX(), this.getY());
    			dungeon.addEntity(newBomb);
    			this.inventory.remove(e);
    			break;
    		}
    	}
    }
    
    public void activatePotion() {
    	for (Entity e: this.inventory) {
    		if (e.getEntityName().equals("InvincibilityPotion")) {
    			//Make player invincible, change enemies to run away from player
    		}
    	}
    }
    
    //Generalised finding interaction between a collectable and specific entity (e.g. sword and enemy classes)
    public ArrayList<Entity> find_interaction(String collectable, String entity) {
    	ArrayList<Entity> interactions = new ArrayList<Entity>();
    	for (Collectable e: this.inventory) {
    		if (e.getEntityName().equals(collectable)) {
    			interactions.add(e);
    			ArrayList<String> types = new ArrayList<String>();
    			types.add(entity);
    			ArrayList<String> surrounding = dungeon.checkSurrounding(this, types);
    			for (String i: surrounding) {
    				Entity add;
    				switch(i) {
    				case "Left":
    					add = (Door)dungeon.getEntity(this.getX()-1, this.getY(), "Door");
    					if (add != null) {
    						interactions.add(add);
    					}
    					break;
    				case "Right":
						add = (Door)dungeon.getEntity(this.getX()+1, this.getY(), "Door");
    					if (add != null) {
    						interactions.add(add);
    					} 				
    					break;
    				case "Up":
    					add = (Door)dungeon.getEntity(this.getX(), this.getY()+1, "Door");
    					if (add != null) {
    						interactions.add(add);
    					}
    					break;
    				case "Down":
    					add = (Door)dungeon.getEntity(this.getX(), this.getY()-1, "Door");
    					if (add != null) {
    						interactions.add(add);
    					}
    					break;
    				}
    			}    			
    		}
    	}
    	return interactions;
    }
    
    public boolean nextToPlayer(int x, int y) {
    	ArrayList<Entity> entitiesAtLocation = dungeon.findEntity(x, y);
    	for(Entity e: entitiesAtLocation) {
    		if(e.getClass().equals(Wall.class)) {
    			return true;
    		}
    		if(e.getClass().equals(Boulder.class)) {
    			ArrayList<String> types = new ArrayList<String>();
    			types.add("Boulder");
    			types.add("Wall");
    			types.add("Door");
    			ArrayList<String> surrounding = dungeon.checkSurrounding(e, types);
    			if (((Boulder)e).checkPlayerPos(this.getX(), this.getY(), surrounding)) {
    				return true;
    			}
    		}
    		if(Collectable.class.isAssignableFrom(e.getClass())) {
    			this.addCollectable((Collectable)e);
    			if (e.getEntityName().equals("InvincibilityPotion")) {
    				this.activatePotion();
    			}
    			dungeon.removeEntity(e);
    		}
    		if (e.getEntityName().equals("Door") && ((Door)e).checkStrategy().equals("Locked")) {
    			return true;
    		}
    	}
    	return false;
    }
	*/
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
