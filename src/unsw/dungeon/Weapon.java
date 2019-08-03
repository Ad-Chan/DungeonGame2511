package unsw.dungeon;

public class Weapon extends Collectable{

	private int health;
	public Weapon(int x, int y, int id) {
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
	
	public boolean ranged() {
		return false;
	}
}
