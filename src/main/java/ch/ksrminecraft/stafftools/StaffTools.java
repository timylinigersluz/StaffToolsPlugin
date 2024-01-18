package ch.ksrminecraft.stafftools;

import ch.ksrminecraft.stafftools.commands.OpenMenuCommand;
import ch.ksrminecraft.stafftools.listeners.MenuInventoryListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class StaffTools extends JavaPlugin {

    private static StaffTools plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("stafftools").setExecutor(new OpenMenuCommand());
        getServer().getPluginManager().registerEvents(new MenuInventoryListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static StaffTools getPlugin() {
        return plugin;
    }
}
