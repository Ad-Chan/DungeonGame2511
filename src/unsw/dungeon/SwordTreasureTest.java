package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class SwordTreasureTest {

	@Test
	void createSword() {
		Sword newSword = new Sword(1, 1, 1);
		assert(newSword.getX() == 1 && newSword.getY() == 1);
	}
	
	@Test
	void createTreasure() {
		Treasure newTreasure = new Treasure(1, 1, 1);
		assert(newTreasure.getX() == 1 && newTreasure.getY() == 1);
	}

	//User Story 3.1: Player will be able to pick up items from the ground by walking over them.
	@Test
	void pickupSword() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 1, 0);
		Sword newSword = new Sword(1, 1, 1);
		assert(newSword.getX() == 1 && newSword.getY() == 1);
		dungeon.addEntity(newSword);
		newP.moveDown();
		ArrayList<Collectable> inventory = newP.getInventory();
		assert(inventory.get(0).getClass().equals(Sword.class));
	}
	
	@Test
	void pickupTreasure() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 1, 0);
		Treasure newTreasure = new Treasure(1, 1, 1);
		assert(newTreasure.getX() == 1 && newTreasure.getY() == 1);
		dungeon.addEntity(newTreasure);
		newP.moveDown();
		ArrayList<Collectable> inventory = newP.getInventory();
		assert(inventory.get(0).getClass().equals(Sword.class));
	}
	
	@Test
	void attackEnemy() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 1, 0);
		Enemy newEnemy = new Enemy(1, 2, 1);
		Sword newSword = new Sword(1, 1, 1);
		dungeon.addEntity(newSword);
		newP.moveDown();
		ArrayList<Collectable> inventory = newP.getInventory();
		assert(inventory.get(0).getClass().equals(Sword.class));
	}
}
