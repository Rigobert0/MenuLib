package de.rigobert0.menulib.area;

import java.util.Comparator;

import de.rigobert0.menulib.util.Constants;

public record Pos2D(int xPos, int yPos) {

	private static final int ROW_LENGTH = Constants.ROW_LENGTH.value();

	public static final Pos2D ZERO = new Pos2D(0, 0);

	public Pos2D add(Pos2D other) {
		return new Pos2D(this.xPos + other.xPos, this.yPos + other.yPos);
	}

	public Comparator<Pos2D> inventoryBuilder() {
		return (p1, p2) -> {
			int rowRelation = Integer.compare(p1.yPos, p2.yPos);
			if (rowRelation == 0)
				return Integer.compare(p1.xPos, p2.xPos);
			return rowRelation;
		};

	}

	public int toInventoryPosition() {
		return yPos * Constants.ROW_LENGTH.value() + xPos;
	}

	public static Pos2D of(int menuIndex) {
		return new Pos2D(menuIndex % ROW_LENGTH, menuIndex / ROW_LENGTH);
	}

}
