package unsw.dungeon;

// Sword Class (to be modified)
public class Sword extends Collectable {
	
	private int health;
	
	public Sword(int x, int y, int id) {
		super(x, y, id);
		this.setHealth(5); //Sword is 5 health because only 5 hits before breaking
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
	
	
}
