package de.rigobert0.menulib.area;

import java.util.Map;

import de.rigobert0.menulib.menu.menucomponent.MenuComponent;
import de.rigobert0.menulib.util.Constants;

/**
 * Represents a collection of inventory slots.
 * Is defined using the composite pattern.
 *
 * @see NodeArea
 * @see LeafArea
 */
public abstract class Area {

	protected static final int ROW_LENGTH = Constants.ROW_LENGTH.value();

	protected Area() {
	}

	/**
	 * Flattens the tree structure to a map.
	 *
	 * @param pos2D The Position this Area will have in relation to its parent.
	 *              Represents the position in the inventory as Pos2D(column,row)
	 * @return The mapped MenuComponents, at their absolute positions.
	 */
	public abstract Map<Pos2D, MenuComponent<?>> build(Pos2D pos2D);

}
