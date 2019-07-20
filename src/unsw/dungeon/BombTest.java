package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class BombTest {

	@Test
	void createBomb() {
		UnlitBomb newBomb = new UnlitBomb(1, 1, 1);
		assert(newBomb.getX() == 1 && newBomb.getY() == 1);
	}
	
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
		assert(newBomb.checkState() == "LitBomb1");
		Thread.sleep(400);
		assert(newBomb.checkState() == "LitBomb2");
		Thread.sleep(400);
		assert(newBomb.checkState() == "LitBomb3");
		Thread.sleep(400);
		assert(newBomb.checkState() == "Explode");
	}
	
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
		assert(dungeon.getEntity(1, 1, LitBomb.class).getClass().equals(LitBomb.class));
	}
}
