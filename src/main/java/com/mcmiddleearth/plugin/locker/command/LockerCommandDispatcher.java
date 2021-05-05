package com.mcmiddleearth.plugin.locker.command;

import com.google.common.base.Joiner;
import com.mcmiddleearth.command.McmeCommandSender;
import com.mcmiddleearth.command.builder.HelpfulLiteralBuilder;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import java.util.List;

import static com.mcmiddleearth.plugin.locker.command.action.LockAction.LOCK_ACTION;
import static com.mcmiddleearth.plugin.locker.command.action.PermissionCheckAction.PERMISSION_CHECK_ACTION;
import static com.mcmiddleearth.plugin.locker.command.action.ToggleAction.TOGGLE_ACTION;
import static com.mcmiddleearth.plugin.locker.command.action.UnlockAction.UNLOCK_ACTION;

public class LockerCommandDispatcher extends CommandDispatcher<McmeCommandSender> {

    public LockerCommandDispatcher() {
        super();
        register(literal("locker").requires(PERMISSION_CHECK_ACTION)
                .then(literal("lock").executes(LOCK_ACTION))
                .then(literal("unlock").executes(UNLOCK_ACTION))
                .then(literal("toggle").executes(TOGGLE_ACTION))
        );
    }

    public boolean execute(McmeCommandSender sender, String name, List<String> arguments) {
        String command = name;

        if (arguments.size() > 0) {
            command = String.format("%s %s", name, Joiner.on(" ").join(arguments));
        }
        try {
            return execute(command, sender) > 0;
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
            return false;
        }
    }

    private HelpfulLiteralBuilder literal(String command) {
        return HelpfulLiteralBuilder.literal(command);
    }
}
