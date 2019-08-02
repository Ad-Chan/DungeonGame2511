package unsw.dungeon;

// Key Class (to be modified)
public class Key extends Collectable{
	
	private int keycode;
	
	public Key(int x, int y, int id, int keycode) {
		super(x, y, id);
		this.setKeycode(keycode);
	}

	public int getKeycode() {
		return keycode;
	}

	public void setKeycode(int keycode) {
		this.keycode = keycode;
	}
	
	@Override
    public boolean playerWalksInto(Player player) {
		boolean found = false;
		for (Collectable c: player.getInventory()) {
			if (c instanceof Key) {
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
