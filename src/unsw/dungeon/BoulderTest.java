package unsw.dungeon;

import org.junit.jupiter.api.Test;

class BoulderTest {

	@Test
	void createBoulder() {
		Boulder newBoulder = new Boulder(5, 5);
		assert(newBoulder.getX() == 5 && newBoulder.getY() == 5);
	}
	
	@Test
	void moveBoulder() {
		Boulder newBoulder = new Boulder(5,5);
		newBoulder.moveBoulder(6, 6);
		assert(newBoulder.getX() == 6 && newBoulder.getY() == 6);
	}
	
	//User Story 2.1 A player can walk into a boulder to push it in that direction.
	// A player can move the boulder so that it is on top of a floor switch.
	@Test
	void pushBoulder() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 5, 5);
		Boulder newBoulder = new Boulder(4,5);
		dungeon.addEntity(newBoulder);
		newP.moveLeft();
		assert(newBoulder.getX() == 3);
	}
	
	//User Story 2.1: A boulder can only be moved into an adjacent empty square.
	@Test
	void pushBoulderWall() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 5, 5);
		Wall newWall = new Wall(3, 5);
		Boulder newBoulder = new Boulder(4,5);
		dungeon.addEntity(newWall);
		dungeon.addEntity(newBoulder);
		newP.moveLeft();
		assert(newBoulder.getX() == 4 && newP.getX() == 5);
	}

}
