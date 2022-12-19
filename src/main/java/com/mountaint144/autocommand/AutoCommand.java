package com.mountaint144.autocommand;

import com.mountaint144.autocommand.util.DelayedTask;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class AutoCommand extends JavaPlugin implements Listener {
    public static FileConfiguration config;
    public static AutoCommand plugin;

    public AutoCommand() {
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        this.saveDefaultConfig();
        config = this.getConfig();

        // If startup module is enabled
        if(config.getBoolean("startup.enabled")) {
            // If wait length is above 0
            if (config.getInt("startup.wait-length") >= 0) {
                int seconds = config.getInt("startup.wait-length");
                // Wait specified time
                new DelayedTask(() -> {
                    // For command in commands
                    List<String> list = config.getStringList("startup.commands");
                    for(int index = 0; index < list.size(); index++) {
                        String command = list.get(index);
                        new DelayedTask(() -> {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                        }, (index) * config.getInt("startup.between-length"));
                    }
                }, seconds);
            } else {
                getLogger().severe("Couldn't send command as config.yml is invalid!");
            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
