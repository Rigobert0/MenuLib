package de.rigobert0.menulib.area;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import de.rigobert0.menulib.menu.menucomponent.MenuComponent;

/**
 * Represents the highest level of an Area. Is capable of mapping all its children to Inventory slots
 */
public class RootArea extends NodeArea {

	private Map<Integer, MenuComponent<?>> lastBuild = Collections.emptyMap();

	/**
	 * Uses the result of {@link #build(Pos2D)} to create a mapping to Inventory positions that can be used by the
	 * building method used in the {@link de.rigobert0.menulib.menu.Menu}
	 *
	 * @return The mapping
	 */
	public Map<Integer, MenuComponent<?>> toInventoryPositions() {
		return lastBuild = build(Pos2D.ZERO).entrySet().stream()
				.collect(Collectors.toMap(e -> e.getKey().toInventoryPosition(), Map.Entry::getValue));
	}

	/**
	 * Getter for MenuComponents in the active build of this Area
	 *
	 * @param index The slot index of the currently wanted MenuComponent
	 * @return The MenuComponent at this slot, if absent null
	 */
	public MenuComponent<?> get(int index) {
		return lastBuild.get(index);
	}

}
