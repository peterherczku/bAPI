package me.blockeed.bapi.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/*
 * Copyright (c) Blockeed | All rights reserved
 *
 * Do not change the code!
 *
 */

public abstract class ConfigurationManager {

    private JavaPlugin plugin;
    private File file;
    private FileConfiguration configuration;

    public ConfigurationManager(JavaPlugin plugin, String fileNameWithExtension) {
        this.plugin=plugin;
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        this.file=new File(plugin.getDataFolder(), fileNameWithExtension);
        this.configuration= YamlConfiguration.loadConfiguration(file);
    }

    public void writeLocation(Location location, ConfigurationSection section) {
        section.set("world", location.getWorld().getName());
        section.set("x", location.getX());
        section.set("y", location.getY());
        section.set("z", location.getZ());
        section.set("yaw", location.getYaw());
        section.set("pitch", location.getPitch());
        saveConfiguration();
    }

    public Location readLocation(ConfigurationSection section) {
        World world = Bukkit.getWorld(section.getString("world"));
        double x = section.getDouble("x");
        double y = section.getDouble("y");
        double z = section.getDouble("z");
        Location location = new Location(world, x, y, z);
        int yaw = section.getInt("yaw");
        int pitch = section.getInt("pitch");
        location.setYaw(yaw);
        location.setPitch(pitch);

        return location;
    }

    public void writeLocation(Location location, String path) {
        configuration.set(path+".world", location.getWorld().getName());
        configuration.set(path+".x", location.getX());
        configuration.set(path+".y", location.getY());
        configuration.set(path+".z", location.getZ());
        configuration.set(path+".yaw", location.getYaw());
        configuration.set(path+".pitch", location.getPitch());
        saveConfiguration();
    }

    public Location readLocation(String path) {
        World world = Bukkit.getWorld(configuration.getString(path+".world"));
        double x = configuration.getDouble(path+".x");
        double y = configuration.getDouble(path+".y");
        double z = configuration.getDouble(path+".z");
        Location location = new Location(world, x, y, z);
        int yaw = configuration.getInt(path+".yaw");
        int pitch = configuration.getInt(path+".pitch");
        location.setYaw(yaw);
        location.setPitch(pitch);

        return location;
    }

    public boolean objectExists(String path ){
        try {
            return getConfiguration().get(path)!=null;
        } catch (Exception throwables) {
            return false;
        }
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }

    public File getFile() {
        return file;
    }

    public void saveConfiguration() {
        try {
            this.configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
