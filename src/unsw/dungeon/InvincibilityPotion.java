package unsw.dungeon;

// Invincibility Potion Class (to be modified)
public class InvincibilityPotion extends Collectable {
	
	private int time_limit;
	
	public InvincibilityPotion(int x, int y, int id, int time_limit) {
		super(x, y, id);
		this.setTime_limit(time_limit); //Time the potion lasts
	}

	public int getTime_limit() {
		return time_limit;
	}

	public void setTime_limit(int time_limit) {
		this.time_limit = time_limit;
	}
	
}
