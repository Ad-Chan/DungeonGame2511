package unsw.dungeon;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PotionTest {

	@Test
	void createPotion() {
		InvincibilityPotion newPotion = new InvincibilityPotion(1, 1, 1, 1, "InvincibilityPotion");
		assert(newPotion.getX() == 1 && newPotion.getY() == 1);
	}

	@Test
	void pickupPotion() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 1, 0, "Player");
		InvincibilityPotion newPotion = new InvincibilityPotion(1, 1, 1, 1, "InvincibilityPotion");
		dungeon.addEntity(newPotion);
		newP.moveDown();
		ArrayList<Collectable> inventory = newP.getInventory();
		assert(inventory.get(0).getEntityName().equals("InvincibilityPotion"));
	}
	
}
