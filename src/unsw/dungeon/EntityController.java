package unsw.dungeon;

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
}
