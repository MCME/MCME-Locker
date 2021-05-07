package com.mcmiddleearth.plugin.locker;

import com.mcmiddleearth.plugin.locker.command.LockerCommandDispatcher;
import com.mcmiddleearth.plugin.locker.command.McmePlayerCommandSender;
import com.mcmiddleearth.plugin.locker.listener.PlayerJoinListener;
import com.mcmiddleearth.plugin.locker.manager.LockerManager;
import com.mcmiddleearth.plugin.locker.manager.LockerStatus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

import static com.mcmiddleearth.plugin.locker.manager.LockerStatus.UNLOCKED;

public final class LockerPlugin extends JavaPlugin {

    private static LockerPlugin pluginInstance;

    public static LockerPlugin getInstance() {
        return pluginInstance;
    }

    private LockerManager lockerManager;
    private LockerCommandDispatcher commandDispatcher;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return commandDispatcher.execute(new McmePlayerCommandSender((Player) sender), command.getName(), Arrays.asList(args));
    }

    @Override
    public void onEnable() {
        LockerPlugin.pluginInstance = this;
        // Config
        this.saveDefaultConfig();
        this.reloadConfig();

        // Plugin startup logic
        this.lockerManager = new LockerManager();
        this.commandDispatcher = new LockerCommandDispatcher();

        try {
            LockerStatus configLockerStatus = LockerStatus.valueOf(this.getConfig().getString("default_status"));
            this.lockerManager.setLock(configLockerStatus);
        } catch (IllegalArgumentException | NullPointerException exception) {
            this.lockerManager.setLock(UNLOCKED);
        }

        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public LockerManager manager() {
        return lockerManager;
    }

}
