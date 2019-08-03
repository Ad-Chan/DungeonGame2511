package unsw.dungeon;


public class Sword extends Weapon{
		
	public Sword(int x, int y, int id) {
		super(x, y, id);
	}
	
	@Override
    public boolean playerWalksInto(Player player) {
		boolean found = false;
		/*for (Collectable c: player.getInventory()) {
			if (c instanceof Sword) {
				found = true;
			}
		}*/
		if (player.getWeapon() != null) {
			found = true;
		}
		if (found == false) {
			//player.addCollectable(this);
			player.setWeapon(this);
			return true;
		} else {
			return false;
		}
    }	
}
