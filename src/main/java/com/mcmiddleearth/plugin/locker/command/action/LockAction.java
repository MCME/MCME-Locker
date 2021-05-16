package com.mcmiddleearth.plugin.locker.command.action;

import com.mcmiddleearth.command.McmeCommandSender;
import com.mcmiddleearth.plugin.locker.LockerPlugin;
import com.mcmiddleearth.plugin.locker.manager.LockerStatus;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class LockAction implements Command<McmeCommandSender> {
    public static LockAction LOCK_ACTION = new LockAction();

    @Override
    public int run(CommandContext<McmeCommandSender> context) {
        LockerPlugin.getInstance().lockManager().setLock(LockerStatus.LOCKED);
        context.getSource().sendMessage(new ComponentBuilder("Locker locked").create());
        return 1;
    }
}
