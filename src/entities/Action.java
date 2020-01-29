package entities;

public class Action {
	public enum Act {
		EAT, BREED, MOVE, DIE, NOTHING
	}

	public final Entity entity;
	public Act nextAction;
	public int x;
	int oldX;
	public int y;
	int oldY;

	public Action(Entity entity) {
		this.entity = entity;
	}

	public String toString() {
		return "[ entity : " + entity.toString() + ", nextAction : " + nextAction.toString() + ", x : " + x + ", y : "
				+ y + " ]";
	}
}
