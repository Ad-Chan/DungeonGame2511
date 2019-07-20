package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void createNew() {
		Dungeon dungeon = new Dungeon(50, 50);
		assert(dungeon != null);
		Player newP = new Player(dungeon, 0, 0);
		assert(newP != null);
	}
	
	@Test
	void positionCheck() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 0, 0);
		assert(newP.getX() == 0 && newP.getY() == 0);
	}	
	
	@Test //THIS IS A DUNGEON TEST
	void sizeCheck() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 0, 0);
		assert(dungeon.getWidth() == 50 && dungeon.getHeight() == 50);
	}	

	
	//User Story 1.1 
	@Test
	void movePlayer() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 5, 5);
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
		Player newP = new Player(dungeon, 5, 5);		
		Key newKey = new Key(1, 2, 1, 5);
		newP.addCollectable(newKey);
		ArrayList<Collectable> inventory = newP.getInventory();
		assert(inventory.get(0).getClass().equals(Key.class));
		
	}
	
	@Test
	void nextToPlayer() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 5, 5);
		Wall newWall = new Wall(6, 5);
		Wall newWall1 = new Wall(5, 6);
		dungeon.addEntity(newWall);
		dungeon.addEntity(newWall1);
		assert(newP.nextToPlayer(6,5) == true && newP.nextToPlayer(5,6) == true);
	}
	
	//User Story 1.2
	@Test
	void nextToWall() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 5, 5);
		Wall newWall = new Wall(6, 5);
		dungeon.addEntity(newWall);
		newP.moveRight();
		assert(newP.getX() == 5);
	}
	
}
