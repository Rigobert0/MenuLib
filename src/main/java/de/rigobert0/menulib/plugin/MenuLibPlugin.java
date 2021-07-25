package de.rigobert0.menulib.plugin;

import org.bukkit.plugin.java.JavaPlugin;

import de.rigobert0.menulib.listeners.InventoryClickListener;

public class MenuLibPlugin extends JavaPlugin {

	private static MenuLibPlugin instance;

	@Override
	public void onLoad() {
		instance = this;
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(InventoryClickListener.INSTANCE, this);
	}

	public static MenuLibPlugin getInstance() {
		return instance;
	}
}
