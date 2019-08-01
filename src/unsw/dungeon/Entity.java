package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity implements PlayerPosObserver{

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private int playerX;
    private int playerY;
    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        //this.playerX = -999; //values to be determined
        //this.playerY = -999; //values to be determined
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }
    
    public void playerWalksInto(Player player) {
    	
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

    public int getPlayerX() {
    	return playerX;
    }
    
    public int getPlayerY() {
    	return playerY;
    }
    
	@Override
	public void update(PlayerPos obj) {
		if (obj instanceof Player) {
			update((Player) obj);
		}
		
	}
	
	public void update(Player p) {
		this.playerX = p.getX();
		this.playerY = p.getY();
		//System.out.println(this.getPlayerX());
		//System.out.println(this.getPlayerY());
	}
	
	public boolean isObstacle(int x, int y, Player p) {
		return false;
	}	
}
