/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    /*public void removeCollectable(int id) {
    	for (Entity e: entities) {
    		if (e.getClass().equals(Collectable.class)) {
    			if(((Collectable) e).getCollectable_id() == id) {
    				entities.remove(e);
    			}
    		}
    	}
    }*/
    
    public void removeEntity(Entity entity) {
    	entities.remove(entity);
    }
    
    public ArrayList<Entity> findEntity(int x, int y) {
    	ArrayList<Entity> foundEntities = new ArrayList<Entity>();
    	for(Entity e: entities){
    		if(e.getX()==x && e.getY()==y) {
    			foundEntities.add(e);
    		}
    	}
    	return foundEntities;
    }
    
    public ArrayList<String> checkSurrounding(Entity e) {
    	ArrayList<String> surrounding = new ArrayList<String>();
    	ArrayList<Entity> left = findEntity(e.getX()-1, e.getY());
    	ArrayList<Entity> right = findEntity(e.getX()+1, e.getY());
    	ArrayList<Entity> up = findEntity(e.getX(), e.getY()-1);
    	ArrayList<Entity> down = findEntity(e.getX(), e.getY()+1);

    	if (checkNeighbour(left) == true) {
    		surrounding.add("Left");
    	}
    	if (checkNeighbour(right) == true) {
    		surrounding.add("Right");    		
    	}
    	if (checkNeighbour(up) == true) {
    		surrounding.add("Up");    		
    	}
    	if (checkNeighbour(down) == true) {
    		surrounding.add("Down");   		
    	}
    	return surrounding;
    	
    }
    
    public boolean checkNeighbour(ArrayList<Entity> next) {
    	for (Entity e:next) {
    		if (e.getClass().equals(Boulder.class)) {
    			return true;
    		}
    		if (e.getClass().equals(Wall.class)) {
    			return true;
    		}
    	}
    	return false;
    }
}
