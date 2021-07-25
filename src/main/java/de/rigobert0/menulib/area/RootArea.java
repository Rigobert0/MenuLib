package de.rigobert0.menulib.area;

import java.util.Map;
import java.util.stream.Collectors;

import de.rigobert0.menulib.menu.menucomponent.MenuComponent;

/**
 * Represents the highest level of an Area. Is capable of mapping all its children to Inventory slots
 */
public class RootArea extends NodeArea {

	/**
	 * Uses the result of {@link #build(Pos2D)} to create a mapping to Inventory positions that can be used by
	 * {@link de.rigobert0.menulib.menu.Menu#buildContentsFrom(Map, int)}
	 *
	 * @return The Mapping
	 */
	public Map<Integer, MenuComponent<?>> toInventoryPositions() {
		return build(Pos2D.ZERO).entrySet().stream()
				.collect(Collectors.toMap(e -> e.getKey().toInventoryPosition(), Map.Entry::getValue));
	}

}
