package com.mcmiddleearth.plugin.locker.command.action;

import com.mcmiddleearth.command.McmeCommandSender;
import com.mcmiddleearth.plugin.locker.LockerPlugin;
import com.mcmiddleearth.plugin.locker.manager.LockerStatus;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class UnlockAction implements Command<McmeCommandSender> {
    public static UnlockAction UNLOCK_ACTION = new UnlockAction();

    @Override
    public int run(CommandContext<McmeCommandSender> context) {
        LockerPlugin.getInstance().lockManager().setLock(LockerStatus.UNLOCKED);
        context.getSource().sendMessage(new ComponentBuilder("Locker Unlocked").create());
        return 1;
    }
}
