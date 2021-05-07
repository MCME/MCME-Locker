package com.mcmiddleearth.plugin.locker.command;

import com.mcmiddleearth.command.McmeCommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;

public class McmePlayerCommandSender implements McmeCommandSender {

    private final Player sender;

    public McmePlayerCommandSender(Player sender) {
        this.sender = sender;
    }

    @Override
    public void sendMessage(BaseComponent[] baseComponents) {
        sender.sendMessage(baseComponents);
    }

    public Player getPlayer() {
        return sender;
    }
}
