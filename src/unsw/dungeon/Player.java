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
    		if(e.getClass().equals(Boulder.class)) {
    			ArrayList<String> surrounding = dungeon.checkSurrounding(e);
    			if (((Boulder)e).checkPlayerPos(this.getX(), this.getY(), surrounding)) {
    				return true;
    			}
    		}
    		if(e.getClass().equals(Sword.class)) { //Code for picking up different collectables, need to unload entity
    			this.addCollectable((Sword)e);
    			dungeon.removeCollectable(((Sword)e).getCollectable_id());
    		}
    		if(e.getClass().equals(Key.class)) { 
    			this.addCollectable((Key)e);
    			dungeon.removeCollectable(((Sword)e).getCollectable_id());
    		}
    		if(e.getClass().equals(Treasure.class)) { 
    			this.addCollectable((Treasure)e);
    			dungeon.removeCollectable(((Treasure)e).getCollectable_id());
    		}
    		if(e.getClass().equals(UnlitBomb.class)) { 
    			this.addCollectable((UnlitBomb)e);
    			dungeon.removeCollectable(((UnlitBomb)e).getCollectable_id());
    		}
    	}
    	return false;
    }
}
