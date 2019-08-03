package unsw.dungeon;


import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public class EntityController {

	private DungeonControllerLoader dungeonLoader;
	private DungeonController controller;
	
	public EntityController(DungeonControllerLoader dungeonLoader, DungeonController controller) {
		this.dungeonLoader = dungeonLoader;
		this.controller = controller;
	}
	
	public void addNewEntity(Entity e) {
        this.dungeonLoader.loadNewEntity(controller.getDungeon(), e);
        this.controller.addNew(dungeonLoader.newEntities.get(0));
        this.dungeonLoader.removeAddedEntity();
	}
	
	public void removeEntity(Entity e) {
		ObservableList<Node> list = this.controller.getSquares().getChildren();
		for (Node n: list) {
			if (n.equals(e.getImage())) {
				list.remove(n);
				break;
			}
		}
		
	}
	
	public void updateImage(Entity e) {
		//removeEntity(e);
		this.dungeonLoader.updateImage(e);
	}
}