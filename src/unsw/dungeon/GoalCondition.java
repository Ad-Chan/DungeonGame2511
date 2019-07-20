package unsw.dungeon;

public class GoalCondition {

	private String goalType;
	private boolean complete;
	
	public GoalCondition(String goalType) {
		this.goalType = goalType;
		this.complete = false;
	}
	
	public String getEntityName() {
		return this.goalType;
	}
	
	public boolean getGoalStatus() {
		return this.complete;
	}
	
}
