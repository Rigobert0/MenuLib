package de.rigobert0.menulib.menu.menucomponent;

import java.util.function.Supplier;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import de.rigobert0.menulib.area.MenuComponentArea;
import de.rigobert0.menulib.menu.ItemRenderer;
import de.rigobert0.menulib.menu.clickhandler.ClickEventAction;
import de.rigobert0.menulib.menu.clickhandler.ClickEventHandler;
import de.rigobert0.menulib.menu.clickhandler.DataProcessor;
import de.rigobert0.menulib.util.ItemStackManipulator;

/**
 * A wrapper class for any menu content.
 * Used in the implementations of {@link de.rigobert0.menulib.menu.Menu}
 * Every MenuComponent contains the data.
 * You may assign a MenuComponent to each slot of a menu
 *
 * @param <T> The type of data this MenuComponent will represent
 */
public interface MenuComponent<T> {

	/**
	 * Getter for a represented Object of type {@link T}
	 *
	 * @return the data this MenuComponent is representing, Might be null
	 */
	T getData();

	/**
	 * Getter for the ItemStack this MenuComponent will display
	 * Notice that the ItemStack still can be dragged around when not set unmodifiable by {@link ItemStackManipulator#unmodifiable()}
	 *
	 * @return An ItemStack representing the {@link T} this MenuComponent is holding
	 */
	ItemStack getStack();

	/**
	 * Handles the given event. Will be called on click.
	 *
	 * @param event The InventoryClickEvent that clicked an ItemStack created by {@link #getStack()}
	 */
	void handleClick(InventoryClickEvent event);

	/**
	 * Creates an 1x1 Area consisting of this MenuComponent to be used in other Areas.
	 * This area can't have Children and will throw an UnsupportedOperation instead
	 *
	 * @return The Area
	 */
	default MenuComponentArea asArea() {
		return new MenuComponentArea(this);
	}

	/**
	 * @param menuComponent The MenuComponent which shall handle the event
	 * @param event         The Event that should be handled by the clickHandler of the menuComponent
	 */
	static void performAction(MenuComponent<?> menuComponent, InventoryClickEvent event) {
		menuComponent.handleClick(event);
	}

	/**
	 * Builds a MenuComponent out of the given ItemStack <br>
	 * {@link #getStack()} will return an exact copy of this stack when building menus.
	 *
	 * @param itemStack The ItemStack this MenuComponent will display
	 * @return The built MenuComponent
	 */
	static MenuComponent<?> ofStack(ItemStack itemStack) {
		return new ItemStackMenuComponent(itemStack);
	}

	/**
	 * Builds a Button-like MenuComponent.
	 *
	 * @param stack             The ItemStack this MenuComponent will display
	 * @param clickEventHandler The action that will be run when clicking on the ItemStack
	 * @return The built MenuComponent
	 * @see ClickEventHandler
	 * @see ClickEventAction
	 */
	static MenuComponent<?> createButton(ItemStack stack, ClickEventHandler clickEventHandler) {
		return new ActionMenuComponent(stack, clickEventHandler);
	}

	/**
	 * Builds a completely dynamic MenuComponent which is capable of reloading and re-rendering a data Object
	 * of the given Type and performing a specific action with the actual value of the data.
	 *
	 * @param dataSource    A data supplier for the given type. Don't use ()->oneFixDatum as parameter,
	 *                      in this case you should use {@link #ofFixedData(Object, ItemRenderer, DataProcessor)}
	 * @param dataRenderer  A rendering function for objects of the data type.
	 * @param dataProcessor A function which processes the ClickEvent and the current Data of the supplier.
	 *                      Notice that data will be re-evaluated once more for this.
	 * @param <T>           The type of the data
	 * @return The built MenuComponent
	 */
	static <T> MenuComponent<T> ofSuppliedData(Supplier<T> dataSource, ItemRenderer<T> dataRenderer,
											   DataProcessor<T> dataProcessor) {
		return new DynamicMenuComponent<>(dataSource, dataRenderer, dataProcessor);
	}

	/**
	 * @param datum         The fixed datum you want this MenuComponent to represent.
	 * @param dataRenderer  A renderer for this datum, for being capable of re-rendering if some data
	 *                      in the datum is dynamic. If there is no dynamic data, you may prefer
	 *                      to use {@link #createButton(ItemStack, ClickEventHandler)}
	 * @param dataProcessor A function which processes the ClickEvent and the current state of the datum.
	 * @param <T>           The data type of the datum.
	 * @return The built MenuComponent
	 */
	static <T> MenuComponent<T> ofFixedData(T datum, ItemRenderer<T> dataRenderer, DataProcessor<T> dataProcessor) {
		return new FixedDatumMenuComponent<>(datum, dataRenderer, dataProcessor);
	}

}
