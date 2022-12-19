package com.mountaint144.autocommand.util;

import com.mountaint144.autocommand.AutoCommand;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class DelayedTask implements Listener {
    Plugin plugin = AutoCommand.plugin;
    private int id = -1;

    public DelayedTask(Runnable runnable, long delay) {
        if (plugin.isEnabled()) {
            id = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, runnable, delay*20);
        } else {
            runnable.run();
        }
    }
}
