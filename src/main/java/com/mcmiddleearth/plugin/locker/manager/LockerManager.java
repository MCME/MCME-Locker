package com.mcmiddleearth.plugin.locker.manager;

import com.google.common.base.Joiner;
import com.mcmiddleearth.plugin.locker.LockerPlugin;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;

import static com.mcmiddleearth.plugin.locker.manager.LockerStatus.LOCKED;
import static com.mcmiddleearth.plugin.locker.manager.LockerStatus.UNLOCKED;

public class LockerManager {

    private LockerStatus lockerStatus;

    private final List<String> executePermissions;
    private final List<String> kickPermissions;
    private final List<String> remainPermissions;
    private final List<String> excludePermissions;

    public LockerManager() {
        this.executePermissions = LockerPlugin.getInstance().getConfig().getStringList("execute_permissions");
        this.kickPermissions = LockerPlugin.getInstance().getConfig().getStringList("kick_permissions");
        this.remainPermissions = LockerPlugin.getInstance().getConfig().getStringList("remain_permissions");
        this.excludePermissions = LockerPlugin.getInstance().getConfig().getStringList("exclude_permissions");

        if (kickPermissions.size() != 0 && remainPermissions.size() != 0) {
            throw new RuntimeException("Either define kick-permissions or remain-permissions. Both are not allowed.");
        }

        System.out.println("Started LockerManager:");
        System.out.println("Execute Permissions: " + Joiner.on(", ").join(executePermissions));
        System.out.println("Kick Permissions: " + Joiner.on(", ").join(kickPermissions));
        System.out.println("Remain Permissions: " + Joiner.on(", ").join(remainPermissions));
        System.out.println("Exclude Permissions: " + Joiner.on(", ").join(excludePermissions));
    }

    public void setLock(LockerStatus configLockerStatus) {
        this.lockerStatus = configLockerStatus;
        if (LOCKED.equals(configLockerStatus)) {
            kickPlayersWithoutPermission();
        }
    }

    public void toggle() {
        setLock(lockerStatus == LOCKED ? UNLOCKED : LOCKED);
    }

    private void kickPlayersWithoutPermission() {
        Collection<? extends Player> onlinePlayers = LockerPlugin.getInstance().getServer().getOnlinePlayers();
        for (Player onlinePlayer : onlinePlayers) {
            if (shouldKickOnLocked(onlinePlayer)) {
                LockerPlugin.getInstance().kickManager().kick(onlinePlayer);
            }
        }
    }

    public LockerStatus getStatus() {
        return lockerStatus;
    }

    public boolean shouldKickOnJoin(Player player) {
        // We should kick everyone on join unless they are in exclude list.
        return !containsExcludePermission(player);
    }

    public boolean shouldKickOnLocked(Player player) {
        if (containsExcludePermission(player)) {
            return false;
        }
        // If a remain list is defined, these will stay, rest will be removed
        if (remainPermissions.size() != 0) {
            return !containsRemainPermission(player);
        }
        // if a kick list is defined, these will be kicked, the rest can stay
        if (kickPermissions.size() != 0) {
            return containsKickPermission(player);
        }
        return true;
    }

    public boolean canExecute(Player player) {
        return executePermissions.stream().anyMatch(player::hasPermission);
    }

    private boolean containsExcludePermission(Player player) {
        return excludePermissions.stream().anyMatch(player::hasPermission);
    }

    private boolean containsRemainPermission(Player player) {
        return remainPermissions.stream().anyMatch(player::hasPermission);
    }

    private boolean containsKickPermission(Player player) {
        return kickPermissions.stream().anyMatch(player::hasPermission);
    }
}
