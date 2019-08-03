package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;
    public List<ImageView> newEntities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image exitImage;
    private Image switchImage;
    private Image boulderImage;
    private Image swordImage;
    private Image potionImage;
    private Image goldImage;
    private Image keyImage;
    private Image doorOpenImage;
    private Image doorClosedImage;
    private Image enemyImage;
    private Image unlitBombImage;
    private Image litBomb1Image;
    private Image litBomb2Image;
    private Image litBomb3Image;
    private Image litBomb4Image;
    

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        newEntities = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
        exitImage = new Image("/exit.png");
        switchImage = new Image("/pressure_plate.png");
        boulderImage = new Image("/boulder.png");
        swordImage = new Image("/greatsword_1_new.png");
        potionImage = new Image("/brilliant_blue_new.png");
        goldImage = new Image("/gold_pile.png");
        keyImage = new Image("/key.png");
        doorOpenImage = new Image("/open_door.png");
        doorClosedImage = new Image("/closed_door.png");
        enemyImage = new Image("/deep_elf_master_archer.png");
        unlitBombImage = new Image("/bomb_unlit.png");
        litBomb1Image = new Image("/bomb_lit_1.png");
        litBomb2Image = new Image("/bomb_lit_2.png");
        litBomb3Image = new Image("/bomb_lit_3.png");
        litBomb4Image = new Image("/bomb_lit_4.png");
        
        
    }

    @Override
    public void onLoad(Entity player, Boolean isNewEntity) {
        ImageView view = new ImageView(playerImage);
    	if (isNewEntity == false) {
        	addEntity(player, view);    		
    	} else {
    		addNewEntity(player, view);
    	}
    }

    @Override
    public void onLoad(Wall wall, Boolean isNewEntity) {
        ImageView view = new ImageView(wallImage);
    	if (isNewEntity == false) {
        	addEntity(wall, view);    		
    	} else {
    		addNewEntity(wall, view);
    	}
    }
    
    @Override
    public void onLoad(Exit exit, Boolean isNewEntity) {
    	ImageView view = new ImageView(exitImage);
    	if (isNewEntity == false) {
        	addEntity(exit, view);    		
    	} else {
    		addNewEntity(exit, view);
    	}
    }
    
    @Override
    public void onLoad(FloorSwitch floorSwitch, Boolean isNewEntity) {
    	ImageView view = new ImageView(switchImage);
    	if (isNewEntity == false) {
        	addEntity(floorSwitch, view);    		
    	} else {
    		addNewEntity(floorSwitch, view);
    	}
    }
    
    @Override
    public void onLoad(Boulder boulder, Boolean isNewEntity) {
    	ImageView view = new ImageView(boulderImage);
    	if (isNewEntity == false) {
        	addEntity(boulder, view);    		
    	} else {
    		addNewEntity(boulder, view);
    	}
    }
    
    @Override
    public void onLoad(Sword sword, Boolean isNewEntity) {
    	ImageView view = new ImageView(swordImage);
    	if (isNewEntity == false) {
        	addEntity(sword, view);    		
    	} else {
    		addNewEntity(sword, view);
    	}
    }
    
    @Override
    public void onLoad(InvincibilityPotion potion, Boolean isNewEntity) {
    	ImageView view = new ImageView(potionImage);
    	if (isNewEntity == false) {
        	addEntity(potion, view);    		
    	} else {
    		addNewEntity(potion, view);
    	}
    }
    
    @Override
    public void onLoad(Treasure gold, Boolean isNewEntity) {
    	ImageView view = new ImageView(goldImage);
    	if (isNewEntity == false) {
        	addEntity(gold, view);    		
    	} else {
    		addNewEntity(gold, view);
    	}
    }
    
    @Override
    public void onLoad(Key key, Boolean isNewEntity) {
    	ImageView view = new ImageView(keyImage);
    	if (isNewEntity == false) {
        	addEntity(key, view);    		
    	} else {
    		addNewEntity(key, view);
    	}
    }

    @Override
    public void onLoad(Door door, Boolean isNewEntity) {
    	ImageView view = new ImageView(doorClosedImage);
    	if (isNewEntity == false) {
        	addEntity(door, view);    		
    	} else {
    		addNewEntity(door, view);
    	}
    }
    
    @Override
    public void onLoad(Enemy enemy, Boolean isNewEntity) {
    	ImageView view = new ImageView(enemyImage);
    	if (isNewEntity == false) {
        	addEntity(enemy, view);    		
    	} else {
    		addNewEntity(enemy, view);
    	};
    }
    
    @Override
    public void onLoad(UnlitBomb unlitBomb, Boolean isNewEntity) {
    	ImageView view = new ImageView(unlitBombImage);
    	if (isNewEntity == false) {
        	addEntity(unlitBomb, view);    		
    	} else {
    		addNewEntity(unlitBomb, view);
    	}
    }
    
    @Override
    public void onLoad(LitBomb litBomb, Boolean isNewEntity) {
    	ImageView view = new ImageView(litBomb1Image);
    	if (isNewEntity == false) {
        	addEntity(litBomb, view);    		
    	} else {
    		addNewEntity(litBomb, view);
    	}
    }
    
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entity.setImage(view);
        entities.add(view);
    }
    
    private void addNewEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entity.setImage(view);
        entities.add(view);
        newEntities.add(view);
    }
    
    public void removeAddedEntity() {
    	newEntities.remove(0);
    }
    
    public ImageView updateImage(Entity e) {
    	ImageView view = null;
    	if (e instanceof LitBomb) {
    		if (((LitBomb)e).checkStrategy() == 2) {
    			//System.out.println("2");
    			view = new ImageView(litBomb2Image);
    		} else if (((LitBomb)e).checkStrategy() == 3) {
    			view = new ImageView(litBomb3Image);
    			//System.out.println("3");
    		} else if (((LitBomb)e).checkStrategy() == 4) {
    			view = new ImageView(litBomb4Image);
    			//System.out.println("4");
    		}
    	} else if (e instanceof Door) {
    		if (((Door)e).isUnlocked() == false) {
    			view = new ImageView(doorOpenImage);
    		}
    	}
    	
    	trackPosition(e, view);
    	return view;
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {

        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }


}
