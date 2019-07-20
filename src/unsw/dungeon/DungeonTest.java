package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DungeonTest {

	@Test
	void createDungeon() {
		Dungeon dungeon = new Dungeon(50,50);
		Player newP = new Player(dungeon, 0, 0);
		dungeon.setPlayer(newP);
		assert(dungeon.getPlayer().equals(newP));
	}
	
	@Test
	void sizeCheck() {
		Dungeon dungeon = new Dungeon(50, 50);
		Player newP = new Player(dungeon, 0, 0);
		assert(dungeon.getWidth() == 50 && dungeon.getHeight() == 50);
	}	
	
	@Test
	void addEntity() {
		Dungeon dungeon = new Dungeon(50, 50);
		Boulder newBoulder = new Boulder(1,1);
		dungeon.addEntity(newBoulder);
		assert(dungeon.getEntity(1, 1, Boulder.class).equals(newBoulder));
		
	}	
	
	@Test
	void removeEntity() {
		Dungeon dungeon = new Dungeon(50, 50);
		Boulder newBoulder = new Boulder(1,1);
		dungeon.addEntity(newBoulder);
		dungeon.removeEntity(newBoulder);
		assert(dungeon.getEntity(1, 1, Boulder.class) == null);
		
	}	
	
	

}
