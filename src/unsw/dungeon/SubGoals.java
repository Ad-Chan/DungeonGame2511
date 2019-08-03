package unsw.dungeon;

import java.util.ArrayList;

public class SubGoals implements GoalCondition {
	
	private String goalType;
	private boolean complete;
	
	private ArrayList<GoalCondition> subGoalsList;
	
	public SubGoals(String goalType) {
		this.goalType = goalType;
		this.complete = false;
		this.subGoalsList = new ArrayList<GoalCondition>();
	}
	
	public String getEntityName() {
		return this.goalType;
	}
	
	public boolean getGoalStatus() {
		return this.complete;
	}
	
	@Override
	public void addSubGoal(Goal goal) {
		subGoalsList.add(goal);
	}
	
	@Override
	public ArrayList<GoalCondition> getSubGoalsList() {
		return this.subGoalsList;
	}
}
