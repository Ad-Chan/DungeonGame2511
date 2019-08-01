package unsw.dungeon;

public class Collectable extends Entity{

	private int collectable_id;
	
	public Collectable(int x, int y, int id) {
		super(x, y);
		this.setCollectable_id(id);
	}

	public int getCollectable_id() {
		return collectable_id;
	}

	public void setCollectable_id(int collectable_id) {
		this.collectable_id = collectable_id;
	}
	
}
