package unsw.dungeon;


public class Sword extends Collectable {
	
	private int health;
	
	public Sword(int x, int y, int id) {
		super(x, y, id);
		this.setHealth(5);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public void decrementHealth() {
		this.health--;
	}
	
	@Override
    public boolean playerWalksInto(Player player) {
		boolean found = false;
		for (Collectable c: player.getInventory()) {
			if (c instanceof Sword) {
				found = true;
			}
		}
		if (found == false) {
			player.addCollectable(this);
			return true;
		} else {
			return false;
		}
    }	
}
