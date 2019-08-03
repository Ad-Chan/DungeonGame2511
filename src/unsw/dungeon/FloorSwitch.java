package unsw.dungeon;

import java.util.ArrayList;

// Floor Switch Class (to be modified)
public class FloorSwitch extends Entity {
	
	private int id;
	private boolean state;
	public FloorSwitch(int x, int y, int id) {
		super(x, y);
		this.id = id;
		this.state = false;
	}
	
	@Override
	public boolean isObstacle(int x, int y, Player p) {
		return false;
	}
	
	@Override
    public boolean playerWalksInto(Player player) {
		ArrayList<Entity> findBoulder = player.findEntity(this.getX(), this.getY());
		for (Entity e: findBoulder) {
			if (e instanceof Boulder) {
				this.state = true;
				System.out.println("true");
			}		
		
		}
		return false;
	}
}
