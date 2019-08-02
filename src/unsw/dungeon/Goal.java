package unsw.dungeon;

public class Goal implements GoalCondition {
	
	private String goalType;
	private boolean complete;
	
	public Goal(String goalType) {
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
