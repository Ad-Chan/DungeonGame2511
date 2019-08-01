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
        if (this.player != null) {
        	addPlayerPosObserver();
        }
    }
    
    public void removeEntity(Entity entity) {
    	entities.remove(entity);
    	this.player.detachObserver(entity);
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
    
    public Entity findSpecificEntity(Entity e) {
    	if (entities.contains(e)) {
    		return e;
    	} else {
    		return null;
    	}
    }
    
    public void addPlayerPosObserver() {
    	ArrayList<PlayerPosObserver> observers = player.getObservers();
    	for (Entity e: this.entities) {
    		if (!observers.contains(e)) {
    			player.attachObserver(e);
    		}
    	}
    }
    
    public void removePlayerPosObserver(PlayerPosObserver p) {
    	this.player.detachObserver(p);
    }
    
}
