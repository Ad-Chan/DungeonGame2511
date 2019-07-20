package unsw.dungeon;

import java.util.ArrayList;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private ArrayList<Collectable> inventory;
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.inventory = new ArrayList<Collectable>();
    }

    public void moveUp() {
        if (getY() > 0 && !nextToPlayer(getX(), getY()-1))
            y().set(getY() - 1);
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1 && !nextToPlayer(getX(), getY()+1))
            y().set(getY() + 1);
    }

    public void moveLeft() {
        if (getX() > 0 && !nextToPlayer(getX()-1, getY()))
            x().set(getX() - 1);
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1 && !nextToPlayer(getX()+1, getY()))
            x().set(getX() + 1);
    }
    
    public void addCollectable(Collectable item) {
    	this.inventory.add(item);
    }
    
    public void unlockDoor() {
    	ArrayList<Entity> interactions = find_interaction(Key.class, Door.class);
    	int keycode = ((Key)interactions.get(0)).getKeycode();
    	for (Entity i: interactions) {
    		if (i.getClass().equals(Door.class)) {
    			if (((Door)i).unlockDoor(keycode)) {
    				this.inventory.remove(interactions.get(0));
    				keycode = -1;
    			}
    		}
    	}
    }

    public void attackEnemy() { 
    	ArrayList<Entity> interactions = find_interaction(Sword.class, Enemy.class);
    	for (Entity i: interactions) {
    		if (i.getClass().equals(Enemy.class) && interactions.get(0).getClass().equals(Sword.class)) {
    			dungeon.removeEntity(i);
    			((Sword)interactions.get(0)).decrementHealth();
    		}
    	}
    }
    
    public void placeBomb() {
    	for (Collectable e: this.inventory) {
    		if (e.getClass().equals(UnlitBomb.class)) {
    			LitBomb newBomb = new LitBomb(this.getX(), this.getY());
    			dungeon.addEntity(newBomb);
    			this.inventory.remove(e);
    			break;
    		}
    	}
    }
    
    //Generalised finding interaction between a collectable and specific entity (e.g. sword and enemy classes)
    public ArrayList<Entity> find_interaction(Class<?> collectable, Class<?> entity) {
    	ArrayList<Entity> interactions = new ArrayList<Entity>();
    	for (Collectable e: this.inventory) {
    		if (e.getClass().equals(collectable)) {
    			interactions.add(e);
    			ArrayList<Class<?>> types = new ArrayList<Class<?>>();
    			types.add(entity);
    			ArrayList<String> surrounding = dungeon.checkSurrounding(this, types);
    			for (String i: surrounding) {
    				Entity add;
    				switch(i) {
    				case "Left":
    					add = (Door)dungeon.getEntity(this.getX()-1, this.getY(), Door.class);
    					if (add != null) {
    						interactions.add(add);
    					}
    					break;
    				case "Right":
						add = (Door)dungeon.getEntity(this.getX()+1, this.getY(), Door.class);
    					if (add != null) {
    						interactions.add(add);
    					} 				
    					break;
    				case "Up":
    					add = (Door)dungeon.getEntity(this.getX(), this.getY()+1, Door.class);
    					if (add != null) {
    						interactions.add(add);
    					}
    					break;
    				case "Down":
    					add = (Door)dungeon.getEntity(this.getX(), this.getY()-1, Door.class);
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
    			ArrayList<Class<?>> types = new ArrayList<Class<?>>();
    			types.add(Boulder.class);
    			types.add(Wall.class);
    			types.add(Door.class);
    			ArrayList<String> surrounding = dungeon.checkSurrounding(e, types);
    			if (((Boulder)e).checkPlayerPos(this.getX(), this.getY(), surrounding)) {
    				return true;
    			}
    		}
    		if(Collectable.class.isAssignableFrom(e.getClass())) {
    			this.addCollectable((Collectable)e);
    			dungeon.removeEntity(e);
    		}
    		if (e.getClass().equals(Door.class) && ((Door)e).checkState().equals("Locked")) {
    			return true;
    		}
    	}
    	return false;
    }
}
