package me.blockeed.bapi.command.framework.commandclass;

import me.blockeed.bessentials.bessentials.bEssentials;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class CommandClass {

    public JavaPlugin plugin;
    public bEssentials bEssentials;

    public CommandClass(JavaPlugin plugin) {
        this.plugin=plugin;
    }

    public CommandClass(bEssentials bEssentials) {
        this.bEssentials=bEssentials;
    }

    public abstract void onCommand(CommandProvider command);

}
