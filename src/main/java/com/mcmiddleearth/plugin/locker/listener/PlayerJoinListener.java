package com.mcmiddleearth.plugin.locker.listener;

import com.mcmiddleearth.plugin.locker.LockerPlugin;
import com.mcmiddleearth.plugin.locker.manager.LockerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static com.mcmiddleearth.plugin.locker.Messages.KICK_MESSAGE_LOCKED;
import static com.mcmiddleearth.plugin.locker.manager.LockerStatus.LOCKED;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void playerJoinListener(PlayerJoinEvent playerJoinEvent) {
        LockerManager manager = LockerPlugin.getInstance().manager();
        Player player = playerJoinEvent.getPlayer();
        if (LOCKED.equals(manager.getStatus()) && manager.shouldKickOnJoin(player)) {
            player.kickPlayer(KICK_MESSAGE_LOCKED);
        }
    }
}
