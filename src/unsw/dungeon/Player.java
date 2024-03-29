package unsw.dungeon;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;



/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements PlayerPos{

    private Dungeon dungeon;
    private ArrayList<Collectable> inventory;
    ArrayList<PlayerPosObserver> observers;
    private Weapon weapon;
    private int potionTime;
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.inventory = new ArrayList<Collectable>();
        this.observers = new ArrayList<PlayerPosObserver>();
        this.potionTime = 0;
        this.weapon = null;
    }
    
	public void potionTick() {
		Timer timer = new Timer();
		timer.schedule(new potionTimer(),0,1000);	
	}
	
	public void decrementPotionTime(Runnable runnable) {
		if (this.potionTime > 0) {
			this.potionTime--;	
		} else {
	    	ArrayList<Enemy> enemies = this.dungeon.getAllEnemies();
	    	for (Enemy e: enemies) {
	    		e.setRunAway(false);
	    	}
		}
	}
	
	class potionTimer extends TimerTask {

    	@Override
    	public void run() {
    		Platform.runLater(new Runnable() {
 		       public void run() {
 		    		decrementPotionTime(this);
 		      }
 		    });
    	}
    }
	
	public void movementRunFunctions() {
    	dungeon.checkSwitches();
    	notifyObservers();
    	dungeon.updateGoals();	
    	if (dungeon.checkGoalCompletion() == true) {
    		dungeon.killWindow();
    	}
	}

    public void moveUp() {
        if (!findObstacles(getX(), getY()-1) == true) {
        	y().set(getY() - 1); 
        	pickupCollectables(this.getX(), this.getY());
        	movementRunFunctions();
        }
    }

    public void moveDown() {
        if (!findObstacles(getX(), getY()+1) == true) {
        	y().set(getY() + 1);
        	pickupCollectables(this.getX(), this.getY());
        	movementRunFunctions();
        }
    }

    public void moveLeft() {
        if (!findObstacles(getX()-1, getY()) == true) {
        	x().set(getX() - 1);
        	pickupCollectables(this.getX(), this.getY());
        	movementRunFunctions();
        }

    }

    public void moveRight() {
        if (!findObstacles(getX()+1, getY()) == true) {
        	x().set(getX() + 1);
        	pickupCollectables(this.getX(), this.getY());
        	movementRunFunctions();
        }

    }
    
    public void addCollectable(Collectable item) {
    	this.inventory.add(item);
    }
    
    public void removeCollectable(Collectable item) {
    	this.inventory.remove(item);
    }
    
    public ArrayList<Collectable> getInventory() {
    	return this.inventory;
    }
    
    public boolean findObstacles(int x, int y) {
    	ArrayList<Entity> entities = dungeon.findEntity(x, y);
    	for (Entity e : entities) {
    		if (e.isObstacle(x, y, this) == true) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean findEnemyObstacles(int x, int y) {
    	ArrayList<Entity> entities = dungeon.findEntityNoEnemy(x, y);
    	for (Entity e : entities) {
    		if (e.isObstacle(x, y, this) == true) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public ArrayList<Entity> findEntity(int x, int y) {
    	return dungeon.findEntity(x, y);
    }
    
    public void pickupCollectables(int x, int y) {
    	ArrayList<Entity> entities = dungeon.findEntity(x, y);
    	for (Entity e: entities) {
    		if (e.playerWalksInto(this)== true) {
    			dungeon.addToInventory(e);
    		}
    	}
    }
    
    
    public void attackEnemy(Enemy e) { 
    	if (this.weapon != null) {
	    	if (this.weapon.ranged() == false) {
	    		if (this.weapon.getHealth() > 1) {
	    			e.decrementHealth();
					dungeon.removeEntity(e);
	    			this.weapon.decrementHealth();   				
				} else if (this.weapon.getHealth() == 1){
	    			e.decrementHealth();
					dungeon.removeEntity(e);
					this.weapon.decrementHealth();
	    			this.removeWeapon();
				}
	    	}
    	}
    	if (this.potionTime > 0) {
			e.decrementHealth();
			dungeon.removeEntity(e);
    	}
    	if (dungeon.findSpecificEntity(e) != null) {
    		this.killPlayer();
    	}
    }
    
    public void useWand() {
    	if (this.weapon != null) {
	    	if (this.weapon.ranged() == true) {
	    		if (this.weapon.getHealth() > 1) {
	    			this.weapon.decrementHealth();  
					((Wand)this.weapon).fireProjectiles(this);
				} else if (this.weapon.getHealth() == 1){
					this.weapon.decrementHealth();
					((Wand)this.weapon).fireProjectiles(this);
	    			this.removeWeapon();
				}
	    	}
    	}
    }
    
    public void activatePotion(InvincibilityPotion i) {
    	this.potionTime += i.getTime_limit();
    	this.potionTick();
    	ArrayList<Enemy> enemies = this.dungeon.getAllEnemies();
    	for (Enemy e: enemies) {
    		e.setRunAway(true);
    	}
    }
    
    public void placeBomb() {
    	for (Collectable e: this.inventory) {
    		if (e instanceof UnlitBomb) {
    			LitBomb newBomb = new LitBomb(this.getX(), this.getY(), this);
    			dungeon.addEntity(newBomb);
    			this.inventory.remove(e);
    			break;
    		}
    	}
    }
    
	@Override
	public void attachObserver(PlayerPosObserver p) {
		if(!this.observers.contains(p)) {
			this.observers.add(p);
		}
		notifyObservers();
	}

	@Override
	public void detachObserver(PlayerPosObserver p) {
		this.observers.remove(p);
	}

	@Override
	public void notifyObservers() {
		for (PlayerPosObserver p : this.observers) {
			p.update(this);
		}
	}
	
	public ArrayList<PlayerPosObserver> getObservers() {
		return this.observers;
	}
	
	public void updateImage(Entity e) {
		this.dungeon.updateImage(e);
	}
	
	public Dungeon getDungeon() {
		return this.dungeon;
	}
	
	public void blowup(LitBomb b) {
		bombDestroyEntities(b.getX()+1, b.getY());
		bombDestroyEntities(b.getX()-1, b.getY());
		bombDestroyEntities(b.getX(), b.getY()+1);
		bombDestroyEntities(b.getX(), b.getY()-1);
		dungeon.removeEntity(b);
	}
	
	public void bombDestroyEntities(int x, int y) {
		ArrayList<Entity> entities = dungeon.findEntity(x, y);
		for (Entity e: entities) {
			if (e instanceof Boulder || e instanceof Enemy) {
				dungeon.removeEntity(e);
			} else if (e instanceof Player) {
				this.killPlayer();
			}
		}
	}
	
	public void projectileDestroyEntities(int x, int y) {
		ArrayList<Entity> entities = dungeon.findEntity(x, y);
		for (Entity e: entities) {
			if (e instanceof Enemy) {
				((Enemy)e).decrementHealth();
				dungeon.removeEntity(e);
			} else if (e instanceof Player) {
				this.killPlayer();
			}
		}
	}
	
	public void killPlayer() {
		dungeon.removeEntity(this);
		dungeon.killWindow();
	}
	
	public void createEntities(Entity e) {
		dungeon.addEntity(e);
	}
	
	public void destroyEntities(Entity e) {
		dungeon.removeEntity(e);
	}
	
	public Weapon getWeapon() {
		return this.weapon;
	}
	
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	public void removeWeapon() {
		this.weapon = null;
	}
}
