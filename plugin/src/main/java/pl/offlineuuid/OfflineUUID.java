package pl.offlineuuid;

import io.github.beefdev.uuidswitcher.core.UUIDSwitcher;
import org.bukkit.plugin.java.JavaPlugin;
import pl.offlineuuid.events.Events;

public final class OfflineUUID extends JavaPlugin {

    private static OfflineUUID main;

    @Override
    public void onEnable() {
        main = this;
        UUIDSwitcher.onEnable();
        getServer().getPluginManager().registerEvents(new Events(), this);
        getLogger().info("Loaded. Listening for players...");
    }

    @Override
    public void onDisable() {
        UUIDSwitcher.onDisable();
        getLogger().info("Disabled.");
    }

    public static OfflineUUID getInstance() {
        return main;
    }

}
