package unsw.dungeon;


public class Treasure extends Collectable {
	
	public Treasure(int x, int y, int id) {
		super(x, y, id);
	}
	
	@Override
	public boolean objective() {
		return true;
	}	
}
