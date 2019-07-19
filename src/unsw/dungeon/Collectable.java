package unsw.dungeon;

public class Collectable extends Entity{

	private int collectable_id;
	
	public Collectable(int x, int y, int id) {
		super(x, y);
		this.collectable_id = id;
	}
	
}
