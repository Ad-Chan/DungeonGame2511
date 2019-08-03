/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;

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
    private ArrayList<Entity> entities;
    private Player player;
    private EntityController controller;
    private ArrayList<GoalCondition> goalList;
    private ArrayList<Goal> singleGoals;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.controller = null;
        this.singleGoals = new ArrayList<Goal>();
        this.goalList = new ArrayList<GoalCondition>();
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

    public void addEntityLocal(Entity entity) {
        entities.add(entity);
        if (this.player != null) {
        	addPlayerPosObserver();
        }
    }
    
    public void addEntity(Entity entity) {
    	this.controller.addNewEntity(entity);
    }
    
    public void removeEntity(Entity entity) {
    	entities.remove(entity);
    	this.controller.removeEntity(entity);
    }
    
    public void removeEntityLocal(Entity entity) {
    	entities.remove(entity);
    }
    
    public void addToInventory(Entity entity) {
    	this.controller.removeEntity(entity);
    	this.removeEntityLocal(entity);
    }
    
    public void addGoalList(ArrayList<GoalCondition> goalList) {
    	this.goalList.addAll(goalList);
    	return;
    }
    
    public ArrayList<Entity> getAllEntities() {
    	return this.entities;
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
    
    public ArrayList<Entity> findEntityNoEnemy(int x, int y) {
    	ArrayList<Entity> foundEntities = new ArrayList<Entity>();
    	for(Entity e: entities){
    		if(e.getX()==x && e.getY()==y && !(e instanceof Enemy)) {
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
    
    public void setEntityController(EntityController e) {
    	this.controller = e;
    }
    
    public void updateImage(Entity e) {
    	this.controller.updateImage(e);
    }
    
    public void checkSwitches() {
    	ArrayList<Entity> switches;
    	for (Entity e: entities) {
    		if (e instanceof FloorSwitch) {
    			switches = this.findEntity(e.getX(), e.getY());
    			boolean onSwitch = false;
    			for (Entity f: switches) {    				
    				if (f instanceof Boulder) {
    					onSwitch = true;
    				}
    			}
    			if (onSwitch == true) {
    				((FloorSwitch)e).updateSwitch(true);
    			} else {
    				((FloorSwitch)e).updateSwitch(false);
    			}
    		}
    	}
    }
            
    public void addSingleGoals(ArrayList<Goal> singleGoals) {
    	this.singleGoals.addAll(singleGoals);
    }
    
    public boolean checkGoalCompletion() {
    	for(GoalCondition g: this.goalList) {
    		if(g.getGoalStatus() == false) {
    			return false;
    		}
    	}
    	return true;
    }
    
    public void killPlayer(Enemy e) {
    	this.player.attackEnemy(e);
    }
    
    public ArrayList<Enemy> getAllEnemies() {
    	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    	for (Entity e: entities) {
    		if (e instanceof Enemy) {
    			enemies.add((Enemy)e);
    		}
    	}
    	return enemies;
    }
    
    public void updateGoals() {
    	boolean complete = true;
    	for (Entity e: this.entities) {
			if (e.objective() == true) {
				complete = false;
			}
    	}
    	if (complete == true) {    	
	    	for (Goal g: this.singleGoals) {
		    		g.setGoalComplete();
	    	}
	    }
    }
    
    public void killWindow() {
    	this.controller.killWindow();
    }
}
