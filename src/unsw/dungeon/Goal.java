package unsw.dungeon;

import java.util.ArrayList;

public class Goal implements GoalCondition {
	
	private String goalType;
	private boolean complete;
	
	public Goal(String goalType) {
		this.goalType = goalType;
		this.complete = false;
	}
	
	@Override
	public String getEntityName() {
		return this.goalType;
	}
	
	public boolean getGoalStatus() {
		return this.complete;
	}
	
	public void setGoalComplete() {
		this.complete = true;
	}

	@Override
	public void addSubGoal(Goal goal) {

	}

	@Override
	public ArrayList<GoalCondition> getSubGoalsList() {
		return null;
	}
}
