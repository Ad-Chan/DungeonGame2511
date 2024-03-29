package unsw.dungeon;


public class InvincibilityPotion extends Collectable {
	
	private int time_limit;
	
	public InvincibilityPotion(int x, int y, int id, int time_limit) {
		super(x, y, id);
		this.setTime_limit(time_limit);
	}

	public int getTime_limit() {
		return time_limit;
	}

	public void setTime_limit(int time_limit) {
		this.time_limit = time_limit;
	}
	
	public void decrementTime() {
		this.time_limit = this.time_limit-1;
	}
	
	@Override
    public boolean playerWalksInto(Player player) {
			player.addCollectable(this);
			this.setTime_limit(5);
			player.activatePotion(this);
			return true;
    }
	
}
