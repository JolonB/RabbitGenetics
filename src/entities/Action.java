package entities;

import java.util.logging.Logger;

public class Action {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Action.class.getName());

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
