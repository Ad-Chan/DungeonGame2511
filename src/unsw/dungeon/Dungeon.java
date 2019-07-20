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
    //private ArrayList<GoalCondition> goalList;

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
    
    public void removeEntity(Entity entity) {
    	entities.remove(entity);
    }
    
    /*public void addGoal(GoalCondition goal) {
    	if(goalList.contains(goal)) {
    		return;
    	}
    	goalList.add(goal);
    	return;
    }*/
    
    public ArrayList<Entity> findEntity(int x, int y) {
    	ArrayList<Entity> foundEntities = new ArrayList<Entity>();
    	for(Entity e: entities){
    		if(e.getX()==x && e.getY()==y) {
    			foundEntities.add(e);
    		}
    	}
    	return foundEntities;
    }
    
    public Entity getEntity(int x, int y, String t) {
    	ArrayList<Entity> entities = findEntity(x, y);
    	for (Entity e: entities) {
    		if (e.getEntityName().equals(t)) {
    			return e;
    		}
    	}
    	return null;
    }
    
    public ArrayList<String> checkSurrounding(Entity e, ArrayList<String> types) {
    	ArrayList<String> surrounding = new ArrayList<String>();
    	ArrayList<Entity> left = findEntity(e.getX()-1, e.getY());
    	ArrayList<Entity> right = findEntity(e.getX()+1, e.getY());
    	ArrayList<Entity> up = findEntity(e.getX(), e.getY()-1);
    	ArrayList<Entity> down = findEntity(e.getX(), e.getY()+1);

    	if (checkNeighbour(left, types) == true) {
    		surrounding.add("Left");
    	}
    	if (checkNeighbour(right, types) == true) {
    		surrounding.add("Right");    		
    	}
    	if (checkNeighbour(up, types) == true) {
    		surrounding.add("Up");    		
    	}
    	if (checkNeighbour(down, types) == true) {
    		surrounding.add("Down");   		
    	}
    	return surrounding;
    	
    }
    
    public boolean checkNeighbour(ArrayList<Entity> next, ArrayList<String> types) {
    	for (Entity e:next) {
    		for (String i: types) {
	    		if (e.getEntityName().equals(i)) {
	    			return true;
	    		}
    		}
    	}
    	return false;
    }
    
}
