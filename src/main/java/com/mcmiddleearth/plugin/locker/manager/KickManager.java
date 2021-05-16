package com.mcmiddleearth.plugin.locker.manager;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.mcmiddleearth.plugin.locker.LockerPlugin;
import org.bukkit.entity.Player;

import static com.mcmiddleearth.plugin.locker.Messages.KICK_MESSAGE_LOCKED;

public class KickManager {

    private final String moveWorldName;

    public KickManager() {
        this.moveWorldName = LockerPlugin.getInstance().getConfig().getString("move_to_world");
    }

    public void kick(Player onlinePlayer) {
        if (moveWorldName == null || moveWorldName.isEmpty()) {
            onlinePlayer.kickPlayer(KICK_MESSAGE_LOCKED);
        } else {
            //noinspection UnstableApiUsage
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("ConnectOther");
            out.writeUTF(onlinePlayer.getName());
            out.writeUTF(moveWorldName);
            onlinePlayer.sendPluginMessage(LockerPlugin.getInstance(), "BungeeCord", out.toByteArray());
        }
    }
}
