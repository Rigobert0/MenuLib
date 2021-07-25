package de.rigobert0.menulib.menu;

import org.bukkit.inventory.ItemStack;

/**
 * Functional interface to render objects to ItemStacks
 *
 * @param <T> The type of the objects this renderer should be capable of rendering
 */
@FunctionalInterface
public interface ItemRenderer<T> {
	/**
	 * Renders an Object to an ItemStack
	 *
	 * @param t The object to be rendered
	 * @return The ItemStack representation of the passed object
	 */
	ItemStack render(T t);

}
