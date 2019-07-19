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
    
    public boolean nextToPlayer(int x, int y) {
    	ArrayList<Entity> entitiesAtLocation = dungeon.findEntity(x, y);
    	for(Entity e: entitiesAtLocation) {
    		if(e.getClass().equals(Wall.class)) {
    			return true;
    		}
    		if(e.getClass().equals(Collectable.class)) {
    			int id = ((Collectable) e).getCollectable_id();
    			this.addCollectable((Collectable) e);
    			dungeon.removeCollectable(id);
    		}
    	}
    	return false;
    }
}
