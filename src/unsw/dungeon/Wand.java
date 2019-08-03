package unsw.dungeon;

public class Wand extends Weapon{

	public Wand (int x, int y, int id) {
		super(x, y, id);
	}
	
	@Override
	public boolean ranged() {
		return true;
	}
	
	public void fireProjectiles(Player player) {
			WandProjectile shot1 = new WandProjectile(this.getPlayerX()+1, this.getPlayerY(), 1, 0, player);
			WandProjectile shot2 = new WandProjectile(this.getPlayerX()-1, this.getPlayerY(), -1, 0, player);
			WandProjectile shot3 = new WandProjectile(this.getPlayerX(), this.getPlayerY()+1, 0, +1, player);
			WandProjectile shot4 = new WandProjectile(this.getPlayerX(), this.getPlayerY()-1, 0, -1, player);
			player.createEntities(shot1);
			player.createEntities(shot2);
			player.createEntities(shot3);
			player.createEntities(shot4);

	}
	
	
	@Override
    public boolean playerWalksInto(Player player) {
		boolean found = false;
		if (player.getWeapon() != null) {
			found = true;
		}
		if (found == false) {
			player.setWeapon(this);
			return true;
		} else {
			return false;
		}
    }	
}
