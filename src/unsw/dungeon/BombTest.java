package unsw.dungeon;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class BombTest {

	/*@Test
	void createBomb() {
		UnlitBomb newBomb = new UnlitBomb(1, 1, 1);
		assert(newBomb.getX() == 1 && newBomb.getY() == 1);
	}
	
	//User Story 2.4: Player can pick up bomb from floor.
	@Test
	void pickupBomb() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 1, 0);
		UnlitBomb newBomb = new UnlitBomb(1, 1, 1);
		assert(newBomb.getX() == 1 && newBomb.getY() == 1);
		dungeon.addEntity(newBomb);
		newP.moveDown();
		ArrayList<Collectable> inventory = newP.getInventory();
		assert(inventory.get(0).getClass().equals(UnlitBomb.class));
	}

	@Test
	void litBombStages() throws InterruptedException {
		LitBomb newBomb = new LitBomb(1, 1);
		assert(newBomb.checkStrategy() == "LitBomb1");
		Thread.sleep(400);
		assert(newBomb.checkStrategy() == "LitBomb2");
		Thread.sleep(400);
		assert(newBomb.checkStrategy() == "LitBomb3");
		Thread.sleep(400);
		assert(newBomb.checkStrategy() == "Explode");
	}
	
	//User Story 2.4: Bomb will explode when player drops it onto the ground.
	@Test
	void dropBomb() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 1, 0);
		UnlitBomb newBomb = new UnlitBomb(1, 1, 1);
		assert(newBomb.getX() == 1 && newBomb.getY() == 1);
		dungeon.addEntity(newBomb);
		newP.moveDown();
		ArrayList<Collectable> inventory = newP.getInventory();
		assert(inventory.get(0).getClass().equals(UnlitBomb.class));
		newP.placeBomb();
		boolean bombPlaced = false;
		for (Entity e: dungeon.findEntity(newP.getX(), newP.getY())) {
			if (e instanceof LitBomb) {
			bombPlaced = true;
		}
		}
		assert(bombPlaced == true);
	}*/
}
