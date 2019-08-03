package unsw.dungeon;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class SwordTreasureTest {

	/*@Test
	void createSword() {
		Sword newSword = new Sword(1, 1, 1, "Sword");
		assert(newSword.getX() == 1 && newSword.getY() == 1);
	}
	
	@Test
	void createTreasure() {
		Treasure newTreasure = new Treasure(1, 1, 1, "Treasure");
		assert(newTreasure.getX() == 1 && newTreasure.getY() == 1);
	}

	//User Story 3.1: Player will be able to pick up items from the ground by walking over them.
	@Test
	void pickupSword() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 1, 0, "Player");
		Sword newSword = new Sword(1, 1, 1, "Sword");
		assert(newSword.getX() == 1 && newSword.getY() == 1);
		dungeon.addEntity(newSword);
		newP.moveDown();
		ArrayList<Collectable> inventory = newP.getInventory();
		assert(inventory.get(0).getEntityName().equals("Sword"));
	}
	
	//User Story 3.5: Player can pick up treasure from floor.
	@Test
	void pickupTreasure() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 1, 0, "Player");
		Treasure newTreasure = new Treasure(1, 1, 1, "Treasure");
		assert(newTreasure.getX() == 1 && newTreasure.getY() == 1);
		dungeon.addEntity(newTreasure);
		newP.moveDown();
		ArrayList<Collectable> inventory = newP.getInventory();
		assert(inventory.get(0).getEntityName().equals("Treasure"));
	}
	
	@Test
	void attackEnemy() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 1, 0, "Player");
		Enemy newEnemy = new Enemy(1, 2, 1, "Enemy");
		Sword newSword = new Sword(1, 1, 1, "Sword");
		dungeon.addEntity(newSword);
		newP.moveDown();
		ArrayList<Collectable> inventory = newP.getInventory();
		assert(inventory.get(0).getEntityName().equals("Sword"));
	}*/
}
