package unsw.dungeon;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class KeyDoorTest {

	@Test
	void createKey() {
		Key newKey = new Key(1, 1, 2, 5);
		assert(newKey.getX() == 1 && newKey.getY() == 1);
		assert(newKey.getKeycode() == 5);
	}
	
	@Test
	void createDoor() {
		Door newDoor = new Door(1, 2, 5);
		assert(newDoor.getX() == 1 && newDoor.getY() == 2);
		assert(newDoor.getKeycode() == 5);
		assert(newDoor.checkStrategy() == "Locked");
	}

	//User Story 2.3: Player can pick up a key
	@Test
	void pickupKey() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 1, 0);
		Key newKey = new Key(1, 1, 2, 5);
		dungeon.addEntity(newKey);
		newP.moveDown();
		assert(newP.getX() == 1 && newP.getY() == 1);
		ArrayList<Collectable> inventory = newP.getInventory();
		assert(inventory.get(0).getClass().equals(Key.class));	
	}	
	
	//User Story 2.3: Player can press a button to use the key
	// Player can open the door by pressing the button with the key in hand
	@Test
	void openDoor() {
		Key newKey = new Key(5, 4, 2, 5);
		Door newDoor = new Door(5, 6, 5);
		assert(newKey.getKeycode() == newDoor.getKeycode());
		newDoor.unlockDoor(newKey.getKeycode());
		assert(newDoor.checkStrategy() == "Open");
	}
	

}
