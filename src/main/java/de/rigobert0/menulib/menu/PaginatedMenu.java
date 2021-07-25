package de.rigobert0.menulib.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import de.rigobert0.menulib.menu.menucomponent.MenuComponent;
import de.rigobert0.menulib.util.ItemStackManipulator;

/**
 * Basic implementation of a paged menu.
 * Will automatically calculate the maximum page and does not go further
 * It is split up into two big areas: <br>
 * The row at the bottom will contain the buttons to swap page. <br>
 * The remaining rows will be filled up with the content from the top left to the bottom right.
 * The PaginatedMenu uses a {@code List<MenuComponent<?>>} for the content, which will be filled to the pages in the order of the List.
 */
public abstract class PaginatedMenu extends Menu {

	private final Map<Integer, MenuComponent<?>> bottomBar;
	private final List<MenuComponent<?>> menuContent;
	private final int space;
	private int page;

	/**
	 * Creates a PaginatedMenu with the given size and title
	 *
	 * @param size The number of slots the inventory will have. Must be at least 18 (2 rows) and may not exceed 54.
	 *             Also it has to be a multiple of {@link #ROW_LENGTH}
	 */
	public PaginatedMenu(final int size, final String title) {
		super(size, title);
		if (size < 18) {
			throw new IllegalArgumentException("Paged Menu needs at least 2 rows");
		}
		space = size - ROW_LENGTH;
		bottomBar = new HashMap<>();
		menuContent = new ArrayList<>();
		setupBottomBar();
	}

	/**
	 * Used to setup the {@link MenuComponent}s of the bottom bar
	 * Can be overridden to change the layout.
	 */
	protected void setupBottomBar() {
		putBottom(0, createBackwardButton());
		putBottom(8, createForwardButton());
	}

	/**
	 * Merges the content area and the bottom area to an single array
	 * Don't override unless you know what you are doing!
	 *
	 * @return An array containing the rendered content and the bottom area
	 */
	@Override
	protected ItemStack[] generateContent() {
		return Stream.concat(contentStream(), bottomButtons()).toArray(ItemStack[]::new);
	}

	/**
	 * Getter for MenuComponents in the currently displayed page.
	 * Do not override unless you know hat you are doing!
	 *
	 * @param inventoryIndex The slot index in the currently displayed view
	 * @return The MenuComponent which is assigned to the given slot in the currently displayed page or null
	 */
	@Override
	protected MenuComponent<?> get(final int inventoryIndex) {
		return inventoryIndex < space ?
				getFromContent(page * space + inventoryIndex) : getBottom(inventoryIndex - space);
	}

	/**
	 * Getter for content MenuComponents in the currently displayed view
	 * Do not override unless you know what you are doing!
	 *
	 * @param index The slot in the currently displayed view
	 * @return The MenuComponent which is assigned to the selected slot index or null.
	 */
	protected MenuComponent<?> getFromContent(int index) {
		return index >= 0 && index < menuContent.size() ? menuContent.get(index) : null;
	}

	/**
	 * Generates a Stream of all MenuComponents assigned to the content area in the currently selected {@link #page}
	 * and renders it to ItemStacks
	 *
	 * @return A Stream containing all the rendered MenuComponents
	 */
	private Stream<ItemStack> contentStream() {
		return IntStream.range(0, space)
				.mapToObj(this::get)
				.map(Optional::ofNullable)
				.map(o -> o.map(MenuComponent::getStack))
				.map(o -> o.orElse(null));
	}


	/**
	 * Packages the bottom buttons into a Stream
	 *
	 * @return the Stream with the rendered bottom bar
	 */
	private Stream<ItemStack> bottomButtons() {
		return Arrays.stream(getBottomBar());
	}

	/**
	 * Uses {@link #buildContentsFrom(Map, int)} to render the MenuComponents of the bottom bar
	 * Do not override unless you know what you are doing!
	 *
	 * @return An ItemStack array containing the rendered MenuComponents
	 */
	protected ItemStack[] getBottomBar() {
		return Menu.buildContentsFrom(bottomBar, 9);
	}

	/**
	 * Default creation method for the forward button.
	 * Override to change the outfit etc.
	 *
	 * @return A MenuComponent representing the forward button
	 */
	protected MenuComponent<?> createForwardButton() {
		ItemStack stack =
				new ItemStackManipulator(Material.GREEN_STAINED_GLASS_PANE).name("Forward").unmodifiable().manipulate();
		return MenuComponent.createButton(stack, (ClickEventAction) this::nextPage);

	}

	/**
	 * Goes to the next page, if existing
	 */
	private void nextPage() {
		if (space * (page + 1) < menuContent.size()) {
			page++;
			reload();
		}
	}

	/**
	 * Default creation method for the backward button.
	 * Override to change the outfit etc.
	 *
	 * @return A MenuComponent representing the backward button
	 */
	protected MenuComponent<?> createBackwardButton() {
		ItemStack stack = new ItemStackManipulator(Material.GREEN_STAINED_GLASS_PANE)
				.name("Backward").unmodifiable().manipulate();
		return MenuComponent.createButton(stack, (ClickEventAction) this::prevPage);
	}

	/**
	 * Goes to the previous page, if existing
	 */
	private void prevPage() {
		if (page > 0) {
			page--;
			reload();
		}
	}

	/**
	 * Adds the given MenuComponent to the given slot index of the bottom row.
	 * Do not override unless you know what you are doing!
	 *
	 * @param index The chosen index
	 * @param value The MenuComponent to fill in
	 */
	protected void putBottom(int index, MenuComponent<?> value) {
		if (index < 0 || index >= 9) {
			throw new IllegalArgumentException("Index must match row length of " + ROW_LENGTH
											   + " but was " + index + "!");
		}
		bottomBar.put(index, value);
	}

	/**
	 * Getter for MenuComponents in the bottom bar
	 *
	 * @param index the index in the bottom bar
	 * @return The MenuComponent assigned to the slot with the given index or null
	 */
	protected final MenuComponent<?> getBottom(int index) {
		return bottomBar.get(index);
	}

	/**
	 * Adds MenuComponents to the content area.
	 *
	 * @param object The MenuComponent which should be added to the content area
	 */
	protected final void addMenuContent(MenuComponent<?> object) {
		menuContent.add(object);
	}

}
