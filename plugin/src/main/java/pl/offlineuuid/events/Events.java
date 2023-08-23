package pl.offlineuuid.events;

import io.github.beefdev.uuidswitcher.common.event.AsyncPlayerProfileCreationEvent;
import io.github.beefdev.uuidswitcher.common.wrapper.WrappedGameProfile;
import io.github.beefdev.uuidswitcher.common.wrapper.WrappedSignedPropertyMap;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import pl.offlineuuid.OfflineUUID;

import java.util.UUID;

public class Events implements Listener {

    private final OfflineUUID plugin = OfflineUUID.getInstance();

    @EventHandler(priority = EventPriority.LOWEST)
    public void onProfileCreation(AsyncPlayerProfileCreationEvent event) {
        UUID originalUUID = event.getGameProfile().getUuid();
        String originalName = event.getGameProfile().getName();
        WrappedSignedPropertyMap originalProperties = event.getGameProfile().getProperties();
        WrappedGameProfile newProfile = new WrappedGameProfile(getOfflineUUID(originalName), originalName, originalProperties);
        event.setGameProfile(newProfile);
        event.getAddress().ifPresent(address -> {});
        ConsoleCommandSender sender = plugin.getServer().getConsoleSender();
        sender.sendMessage("§aBinding new UUID for player " + originalName);
        sender.sendMessage("§c   => Old: " + originalUUID);
        sender.sendMessage("§6   => New: " + getOfflineUUID(originalName));
    }

    public UUID getOfflineUUID(String playerName) {
        return UUID.nameUUIDFromBytes(("OfflinePlayer:" + playerName).getBytes());
    }

}
