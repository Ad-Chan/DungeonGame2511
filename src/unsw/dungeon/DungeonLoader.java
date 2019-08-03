package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.util.ArrayList;

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
    private ArrayList<Goal> singleGoals;

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
        JSONObject jsonGoal = json.getJSONObject("goal-condition");
        this.singleGoals = new ArrayList<Goal>();

        uniqueID = 0;
        
        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        ArrayList<GoalCondition> goalList = loadGoal(jsonGoal);
        System.out.print(goalList);
        dungeon.addGoalList(goalList);
        //System.out.print(this.singleGoals);
        dungeon.addSingleGoals(this.singleGoals);
        
        System.out.print(singleGoals + "\n");
        ArrayList<GoalCondition> subGoalsList = goalList.get(0).getSubGoalsList();
        //System.out.print(subGoalsList.get(0).getEntityName() + " " + subGoalsList.get(1).getEntityName() + "\n");
        return dungeon;
    }

	private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        Boolean isNewEntity = false;

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player, isNewEntity);
            entity = player;
            break;
        case "wall":
        	Wall wall = new Wall(x, y);
            onLoad(wall, isNewEntity);
            entity = wall;
            break;
        case "exit":
        	Exit exit = new Exit(x, y);
        	onLoad(exit, isNewEntity);
        	entity = exit;
        	break;
        case "switch":
        	FloorSwitch floorSwitch = new FloorSwitch(x, y, uniqueID);
        	onLoad(floorSwitch, isNewEntity);
        	entity = floorSwitch;
        	uniqueID++;;
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(x, y);
        	onLoad(boulder, isNewEntity);
        	entity = boulder;
        	break;
        case "sword":
        	Sword sword = new Sword(x, y, uniqueID);
        	onLoad(sword, isNewEntity);
        	entity = sword;
        	uniqueID++;
        	break;
        case "invincibility":
        	InvincibilityPotion potion = new InvincibilityPotion(x, y, uniqueID, 10);
        	onLoad(potion, isNewEntity);
        	entity = potion;
        	uniqueID++;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(x, y, uniqueID);
        	onLoad(treasure, isNewEntity);
        	entity = treasure;
        	uniqueID++;
        	break;
        case "key":
        	Key key = new Key(x, y, uniqueID , 4);
        	onLoad(key, isNewEntity);
        	entity = key;
        	uniqueID++;
        	break;
        case "door":
        	Door door = new Door(x, y, 4);
        	onLoad(door, isNewEntity);
        	entity = door;
        	break;
        case "enemy":
        	Enemy enemy = new Enemy(x, y, uniqueID, dungeon);
        	onLoad(enemy, isNewEntity);
        	entity = enemy;
        	uniqueID++;
        	break;
        case "bomb":
        	UnlitBomb bomb = new UnlitBomb(x, y, uniqueID);
        	onLoad(bomb, isNewEntity);
        	entity = bomb;
        	uniqueID++;
        	break;
        }
        dungeon.addEntityLocal(entity);
    }
	
	public void loadNewEntity(Dungeon dungeon, Entity e) {
		Boolean isNewEntity = true;
        Entity entity = e;
        if (entity instanceof Wall) {
        	onLoad((Wall)entity, isNewEntity);
        } else if (entity instanceof Exit) {
        	onLoad((Exit)entity, isNewEntity);
        } else if (entity instanceof FloorSwitch) {
        	onLoad((FloorSwitch)entity, isNewEntity);
        } else if (entity instanceof Boulder) {
        	onLoad((Boulder)entity, isNewEntity);
        } else if (entity instanceof Sword) {
        	onLoad((Sword)entity, isNewEntity);
        } else if (entity instanceof InvincibilityPotion) {
        	onLoad((InvincibilityPotion)entity, isNewEntity);
        } else if (entity instanceof Treasure) {
        	onLoad((Treasure)entity, isNewEntity);
        } else if (entity instanceof Key) {
        	onLoad((Key)entity, isNewEntity);
        } else if (entity instanceof Door) {
        	onLoad((Door)entity, isNewEntity);
        } else if (entity instanceof Enemy) {
        	onLoad((Enemy)entity, isNewEntity);
        } else if (entity instanceof UnlitBomb) {
        	onLoad((UnlitBomb)entity, isNewEntity);
        } else if (entity instanceof LitBomb) {
        	onLoad((LitBomb)entity, isNewEntity);
        }
        dungeon.addEntityLocal(entity);
    }
    private ArrayList<GoalCondition> loadGoal(JSONObject jsonGoal) {
    	ArrayList<GoalCondition> goalList = new ArrayList<GoalCondition>();
        String mainGoal = jsonGoal.getString("goal");
        GoalCondition goal = null;
        JSONArray jsonSubGoals;
        ArrayList<GoalCondition> subGoalsList;

        switch(mainGoal) {
        case "exit":
        	goal = new Goal("exit");
        	addSingleGoal((Goal) goal);
        	break;
        case "boulders":
        	goal = new Goal("boulders");
        	addSingleGoal((Goal) goal);
        	break;
        case "treasure":
        	goal = new Goal("treasure");
        	addSingleGoal((Goal) goal);
        	break;
        case "enemies":
        	goal = new Goal("enemies");
        	addSingleGoal((Goal) goal);
        	break;
        case "AND":
        	goal = new SubGoals("AND");
        	jsonSubGoals = jsonGoal.getJSONArray("subgoals");
        	subGoalsList = addSubGoals(jsonSubGoals);
        	for (GoalCondition g: subGoalsList) {
        		goal.addSubGoal((Goal) g);
        	}
        case "OR":
        	goal = new SubGoals("OR");
        	jsonSubGoals = jsonGoal.getJSONArray("subgoals");
        	subGoalsList = addSubGoals(jsonSubGoals);
        	for (GoalCondition g: subGoalsList) {
        		goal.addSubGoal((Goal) g);
        	}
        	
        }
        goalList.add(goal);
        return goalList;
        
	}
    
    ArrayList<GoalCondition> addSubGoals(JSONArray jsonSubGoals) {
    	ArrayList<GoalCondition> subGoalsList = new ArrayList<GoalCondition>();
    	for (int i = 0; i < jsonSubGoals.length(); i++) {
            subGoalsList.addAll(loadGoal(jsonSubGoals.getJSONObject(i)));
        }
    	return subGoalsList;
    }
    
    public void addSingleGoal(Goal goal) {
    	for(Goal g: this.singleGoals) {
    		if(g.getEntityName().equals(goal.getEntityName())) {
    			return;
    		}
    	}
    	this.singleGoals.add(goal);
    }

    public abstract void onLoad(Entity player, Boolean isNewEntity);

    public abstract void onLoad(Wall wall, Boolean isNewEntity);
    
    public abstract void onLoad(Exit exit, Boolean isNewEntity);
    
    public abstract void onLoad(FloorSwitch floorSwitch, Boolean isNewEntity);

    public abstract void onLoad(Boulder boulder, Boolean isNewEntity);
    
    public abstract void onLoad(Sword sword, Boolean isNewEntity);

    public abstract void onLoad(InvincibilityPotion potion, Boolean isNewEntity);
    
    public abstract void onLoad(Treasure gold, Boolean isNewEntity);

    public abstract void onLoad(Key key, Boolean isNewEntity);
    
    public abstract void onLoad(Door door, Boolean isNewEntity);

    public abstract void onLoad(Enemy enemy, Boolean isNewEntity);
    
    public abstract void onLoad(UnlitBomb unlitBomb, Boolean isNewEntity);

    public abstract void onLoad(LitBomb litBomb, Boolean isNewEntity);
}
