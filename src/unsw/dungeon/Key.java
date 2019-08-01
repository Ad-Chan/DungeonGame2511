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
    public void playerWalksInto(Player player) {
		if (this.getPlayerX() == this.getX() && this.getPlayerY() == this.getY()) {
			player.addCollectable(this);
		}
    }
	
}
