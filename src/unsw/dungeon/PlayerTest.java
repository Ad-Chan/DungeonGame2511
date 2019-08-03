package unsw.dungeon;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PlayerTest {

	/*@Test
	void createNew() {
		Dungeon dungeon = new Dungeon(50, 50);
		assert(dungeon != null);
		Player newP = new Player(dungeon, 0, 0, "Player");
		assert(newP != null);
	}
	
	@Test
	void positionCheck() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 0, 0, "Player");
		assert(newP.getX() == 0 && newP.getY() == 0);
	}	
		
	//User Story 1.1 
	@Test
	void movePlayer() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 5, 5, "Player");
		newP.moveDown();
		assert(newP.getY() == 6);
		newP.moveUp();
		assert(newP.getY() == 5);
		newP.moveLeft();
		assert(newP.getX() == 4);
		newP.moveRight();
		assert(newP.getX() == 5);
	}
	
	//User Story 3.1: Player will be able to pick up items from the ground by walking over them.
	@Test
	void addCollectable() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 5, 5, "Player");		
		Key newKey = new Key(1, 2, 1, 5, "Key");
		newP.addCollectable(newKey);
		ArrayList<Collectable> inventory = newP.getInventory();
		assert(inventory.get(0).getEntityName().equals("Key"));
		
	}
	
	@Test
	void nextToPlayer() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 5, 5, "Player");
		Wall newWall = new Wall(6, 5, "Wall");
		Wall newWall1 = new Wall(5, 6, "Wall");
		dungeon.addEntity(newWall);
		dungeon.addEntity(newWall1);
		assert(newP.nextToPlayer(6,5) == true && newP.nextToPlayer(5,6) == true);
	}
	
	//User Story 1.2
	@Test
	void nextToWall() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 5, 5, "Player");
		Wall newWall = new Wall(6, 5, "Wall");
		dungeon.addEntity(newWall);
		newP.moveRight();
		assert(newP.getX() == 5);
	}
	*/
}
