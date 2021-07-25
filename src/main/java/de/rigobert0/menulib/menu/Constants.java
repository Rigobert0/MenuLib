package de.rigobert0.menulib.menu;

public enum Constants {
	ROW_LENGTH(9);

	private final int constant;

	Constants(final int constant) {
		this.constant = constant;
	}

	public int value() {
		return constant;
	}
}
