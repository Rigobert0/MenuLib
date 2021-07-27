package de.rigobert0.menulib.plugin;

import java.util.Optional;

import org.bukkit.plugin.java.JavaPlugin;

import de.rigobert0.menulib.demoimpl.shopexample.OpenCommand;
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
		Optional.ofNullable(getCommand("openmenu")).ifPresent(c -> c.setExecutor(OpenCommand.INSTANCE));
	}

	public static MenuLibPlugin getInstance() {
		return instance;
	}
}
