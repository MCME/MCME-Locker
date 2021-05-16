package com.mcmiddleearth.plugin.locker.command.action;

import com.mcmiddleearth.command.McmeCommandSender;
import com.mcmiddleearth.plugin.locker.LockerPlugin;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class ToggleAction implements Command<McmeCommandSender> {
    public static ToggleAction TOGGLE_ACTION = new ToggleAction();

    @Override
    public int run(CommandContext<McmeCommandSender> context) {
        LockerPlugin.getInstance().lockManager().toggle();
        context.getSource().sendMessage(new ComponentBuilder("Locker Toggled").create());
        return 1;
    }
}
