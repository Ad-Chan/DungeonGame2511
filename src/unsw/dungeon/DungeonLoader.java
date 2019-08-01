package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;
    private int uniqueID;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");
        //JSONObject jsonGoal = json.getJSONObject("goal-condition");

        uniqueID = 0;
        
        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        //loadGoal(dungeon, jsonGoal);
        return dungeon;
    }

	private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "exit":
        	Exit exit = new Exit(x, y);
        	onLoad(exit);
        	entity = exit;
        	break;
        case "switch":
        	FloorSwitch floorSwitch = new FloorSwitch(x, y, uniqueID);
        	onLoad(floorSwitch);
        	entity = floorSwitch;
        	uniqueID++;
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(x, y);
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "sword":
        	Sword sword = new Sword(x, y, uniqueID);
        	onLoad(sword);
        	entity = sword;
        	uniqueID++;
        	break;
        case "invincibility":
        	InvincibilityPotion potion = new InvincibilityPotion(x, y, uniqueID, 10);
        	onLoad(potion);
        	entity = potion;
        	uniqueID++;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(x, y, uniqueID);
        	onLoad(treasure);
        	entity = treasure;
        	uniqueID++;
        	break;
        case "key":
        	Key key = new Key(x, y, uniqueID , 4);
        	onLoad(key);
        	entity = key;
        	uniqueID++;
        	break;
        case "door":
        	Door door = new Door(x, y, 4);
        	onLoad(door);
        	entity = door;
        	break;
        case "enemy":
        	Enemy enemy = new Enemy(x, y, uniqueID);
        	onLoad(enemy);
        	entity = enemy;
        	uniqueID++;
        	break;
        case "bomb":
        	UnlitBomb bomb = new UnlitBomb(x, y, uniqueID);
        	onLoad(bomb);
        	entity = bomb;
        	uniqueID++;
        	break;
        }
        dungeon.addEntity(entity);
    }
	

    /*private void loadGoal(Dungeon dungeon, JSONObject jsonGoal) {
        String mainGoal = json.getString("goal");
        
        switch(mainGoal) {
        case "exit":
        	GoalCondition exitGoal = new GoalCondition("exit");
        	dungeon.addGoal(exitGoal);
        	break;
        case "boulders":
        	GoalCondition bouldersGoal = new GoalCondition("boulders");
        	dungeon.addGoal(bouldersGoal);
        	break;
        case "treasure":
        	GoalCondition treasureGoal = new GoalCondition("treasure");
        	dungeon.addGoal(treasureGoal);
        	break;
        case "enemies":
        	GoalCondition enemiesGoal = new GoalCondition("enemies");
        	dungeon.addGoal(enemiesGoal);
        	break;
        //case "AND":
        	
        //case "OR":
        	
        }

	}*/

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);
    
    public abstract void onLoad(Exit exit);
    
    public abstract void onLoad(FloorSwitch floorSwitch);

    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(Sword sword);

    public abstract void onLoad(InvincibilityPotion potion);
    
    public abstract void onLoad(Treasure gold);

    public abstract void onLoad(Key key);
    
    public abstract void onLoad(Door door);

    public abstract void onLoad(Enemy enemy);
    
    public abstract void onLoad(UnlitBomb unlitBomb);

}
