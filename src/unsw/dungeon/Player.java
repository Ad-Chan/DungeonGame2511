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
    	for (Collectable e: this.inventory) {
    		if (e.getClass().equals(Key.class)) {  			
    			ArrayList<Class<?>> types = new ArrayList<Class<?>>();
    			types.add(Door.class);
    			ArrayList<String> surrounding = dungeon.checkSurrounding(this, types);
    			for (String i: surrounding) {
    				Door door;
    				switch(i) {
    				case "Left":
    					door = (Door)dungeon.getEntity(this.getX()-1, this.getY(), Door.class);
    					if (door != null) {
    						door.unlockDoor(((Key)e).getKeycode());
    					}
    					break;
    				case "Right":
						door = (Door)dungeon.getEntity(this.getX()+1, this.getY(), Door.class);
						if (door != null) {
							door.unlockDoor(((Key)e).getKeycode());
						}    				
    					break;
    				case "Up":
    					door = (Door)dungeon.getEntity(this.getX(), this.getY()+1, Door.class);
    					if (door != null) {
    						door.unlockDoor(((Key)e).getKeycode());
    					}
    					break;
    				case "Down":
    					door = (Door)dungeon.getEntity(this.getX(), this.getY()-1, Door.class);
    					if (door != null) {
    						door.unlockDoor(((Key)e).getKeycode());
    					}
    					break;
    				}
    			}
    			
    		}
    	}
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
