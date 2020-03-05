package com.rabbit.entities;

import java.util.logging.Logger;

public class Action {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Action.class.getName());

	public enum Act {
		EAT, BREED, MOVE, DIE, NOTHING
	}

	private transient final Entity entity;
	private transient final Entity actUpon;
	private transient final Act nextAction;
	private transient final int newX;
	private transient final int newY;

	public Action(final Entity entity, final Entity actUpon, final Act nextAction, final int newX, final int newY) {
		this.entity = entity;
		this.actUpon = actUpon;
		this.nextAction = nextAction;
		this.newX = newX;
		this.newY = newY;
	}

	public Entity getEntity() {
		return this.entity;
	}

	public Entity getActUpon() {
		return this.actUpon;
	}

	public Act getAction() {
		return this.nextAction;
	}

	public int getX() {
		return this.newX;
	}

	public int getY() {
		return this.newY;
	}

	@Override
	public String toString() {
		return "[ entity : " + entity.toString() + ", nextAction : " + nextAction.toString() + ", x : " + newX
				+ ", y : " + newY + " ]";
	}
}
