package me.blockeed.bapi.utils;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Module implements Listener {

    private JavaPlugin plugin;
    private String name;

    public Module(JavaPlugin bEssentials, String name) {
        this.name=name;
        this.plugin=bEssentials;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        onLoad();
    }

    public abstract void onLoad();

    public JavaPlugin getPlugin() {
        return plugin;
    }
}
