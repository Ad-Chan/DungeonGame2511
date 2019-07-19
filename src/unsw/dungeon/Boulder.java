package unsw.dungeon;

// Boulder Class (to be modified)
public class Boulder extends Entity {
	
	public Boulder(int x, int y) {
		super(x, y);
	}
	
	//Change position of boulder
	public void moveBoulder(int x, int y) {
		y().set(y);
		x().set(x);
	}
}
