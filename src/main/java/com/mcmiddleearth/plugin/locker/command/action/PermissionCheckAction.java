package com.mcmiddleearth.plugin.locker.command.action;

import com.mcmiddleearth.command.McmeCommandSender;
import com.mcmiddleearth.plugin.locker.LockerPlugin;
import com.mcmiddleearth.plugin.locker.command.McmePlayerCommandSender;

import java.util.function.Predicate;

public class PermissionCheckAction implements Predicate<McmeCommandSender> {
    public static PermissionCheckAction PERMISSION_CHECK_ACTION = new PermissionCheckAction();

    @Override
    public boolean test(McmeCommandSender sender) {
        McmePlayerCommandSender mcmePlayerCommandSender = (McmePlayerCommandSender) sender;
        return LockerPlugin.getInstance().lockManager().canExecute(mcmePlayerCommandSender.getPlayer());
    }
}
