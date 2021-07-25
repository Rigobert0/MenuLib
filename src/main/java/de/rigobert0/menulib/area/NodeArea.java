package de.rigobert0.menulib.area;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import de.rigobert0.menulib.menu.menucomponent.MenuComponent;

/**
 * Inner node of the tree structure.
 * Provides a default, recursively defined {@link #build(Pos2D)} method
 * Also checks if a position is already blocked before adding more children.
 * Does NOT check if any other child Area of the parent blocks the desired Pos2D
 */
public class NodeArea extends Area {

	private final Map<Pos2D, Area> children;

	public NodeArea(final Map<Pos2D, Area> children) {
		this.children = children;
	}

	public NodeArea() {
		this(new HashMap<>());
	}

	@Override
	public Map<Pos2D, MenuComponent<?>> build(final Pos2D pos2D) {
		return children.entrySet()
				.stream()
				.map(e -> e.getValue().build(e.getKey()))
				.map(Map::entrySet)
				.flatMap(Collection::stream)
				.collect(Collectors.toMap(e -> e.getKey().add(pos2D), Map.Entry::getValue));
	}

	/**
	 * @param pos2D The desired Pos2D of the child
	 * @param child The child to be added
	 * @return true if the child could be added successfully, false otherwise
	 */
	public boolean setChild(Pos2D pos2D, Area child) {
		if (children.containsKey(pos2D))
			children.put(pos2D, child);
		if (canAdd(pos2D, child)) {
			children.put(pos2D, child);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if the given child can be added to this node.
	 * Traverses child nodes recursively. Does NOT check if any of the parent nodes already claims that Pos2D
	 *
	 * @param pos2D The Position where the area will be in relation to this Areas root.
	 * @param child The Child to be checked. All it's child Areas will be traversed and checked if this node has childs.
	 * @return true if no conflicts happened and the Area could be successfully be added to the children.
	 */
	protected boolean canAdd(Pos2D pos2D, Area child) {
		Set<Pos2D> blocked = build(Pos2D.ZERO).keySet();
		return child.build(pos2D).keySet().stream().noneMatch(blocked::contains);
	}
}
