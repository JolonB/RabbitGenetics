package entities;

import java.awt.Image;
import java.util.logging.Logger;

import entities.Action.Act;
import terrain.Terrain;

public class Null extends Entity {
	public Null(int x, int y) {
		super(x, y);
	}

	private static final Logger LOGGER = Logger.getLogger(Null.class.getName());

	private static final String IMG_NAME = "img/null.png";
	private static Image image = null;

	@Override
	public Image getImage(int dim) {
		if (Null.image == null) {
			Null.image = setImage(IMG_NAME, dim);
		}
		return Null.image;
	}

	@Override
	public char toChar() {
		return 'n';
	}

	public String toString() {
		return "Null";
	}

	@Override
	public Action calculateAction(Terrain[][] terrain) {
		// TODO Auto-generated method stub
		this.act.nextAction = Act.NOTHING;
		this.act.x = this.act.entity.getX();
		this.act.y = this.act.entity.getY();
		return this.act;
	}

}
