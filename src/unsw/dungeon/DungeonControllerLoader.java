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
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }
    
    @Override
    public void onLoad(Exit exit) {
    	ImageView view = new ImageView(exitImage);
    	addEntity(exit, view);
    }
    
    @Override
    public void onLoad(FloorSwitch floorSwitch) {
    	ImageView view = new ImageView(switchImage);
    	addEntity(floorSwitch, view);
    }
    
    @Override
    public void onLoad(Boulder boulder) {
    	ImageView view = new ImageView(boulderImage);
    	addEntity(boulder, view);
    }
    
    @Override
    public void onLoad(Sword sword) {
    	ImageView view = new ImageView(swordImage);
    	addEntity(sword, view);
    }
    
    @Override
    public void onLoad(InvincibilityPotion potion) {
    	ImageView view = new ImageView(potionImage);
    	addEntity(potion, view);
    }
    
    @Override
    public void onLoad(Treasure gold) {
    	ImageView view = new ImageView(goldImage);
    	addEntity(gold, view);
    }
    
    @Override
    public void onLoad(Key key) {
    	ImageView view = new ImageView(keyImage);
    	addEntity(key, view);
    }

    @Override
    public void onLoad(Door door) {
    	ImageView view = new ImageView(doorClosedImage);
    	addEntity(door, view);
    }
    
    @Override
    public void onLoad(Enemy enemy) {
    	ImageView view = new ImageView(enemyImage);
    	addEntity(enemy, view);
    }
    
    @Override
    public void onLoad(UnlitBomb unlitBomb) {
    	ImageView view = new ImageView(unlitBombImage);
    	addEntity(unlitBomb, view);
    }
    
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
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
