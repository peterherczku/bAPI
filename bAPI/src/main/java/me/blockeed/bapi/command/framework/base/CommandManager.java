package me.blockeed.bapi.command.framework.base;

import me.blockeed.bessentials.bessentials.command.framework.commandclass.Command;
import me.blockeed.bessentials.bessentials.command.framework.commandclass.CommandClass;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class CommandManager implements Listener {

    private Plugin plugin;
    private final CommandMap commandMap;

    // List of commands;
    private final Map<String, CommandHandler> commands = new HashMap<>();
    private Map<String, org.bukkit.command.Command> bukkitCommands = new HashMap<>();

    public CommandManager(Plugin plugin) {
        this.plugin=plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        this.commandMap=getCommandMap();
    }

    public void register(CommandClass command) {
        final Class<?> commandClass = command.getClass();

        if (!commandClass.isAnnotationPresent(Command.class)) {
            throw new CommandException("Class "+command.getClass().getName()+" needs to have @Command!");
        }

        final String commandName = commandClass.getAnnotation(Command.class).name();
        final String permission = commandClass.getAnnotation(Command.class).permission();
        final String[] aliases = commandClass.getAnnotation(Command.class).aliases();

        org.bukkit.command.Command oldCommand = commandMap.getCommand(commandName);

        if (oldCommand instanceof PluginIdentifiableCommand && ((PluginIdentifiableCommand) oldCommand).getPlugin()==this.plugin) {
            bukkitCommands.remove(commandName);
            oldCommand.unregister(commandMap);
        }

        try {
            final CommandHandler handler;
            handler=new CommandHandler(command, commandName, "", "", Arrays.asList(aliases), permission);
            commandMap.register(commandName, plugin.getName(), handler);
            commands.put(commandName, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @EventHandler
    public void onPluginDisable(final PluginDisableEvent event) {
        if (event.getPlugin() != plugin) return;
        unregisterAll();
    }

    /**
     * Unregisters all commands.
     */
    private void unregisterAll() {
        commands.values().forEach(command -> command.unregister(commandMap));
    }

    /**
     * Gets the Command Map to register the commands
     *
     * @return The Command Map
     */
    private CommandMap getCommandMap() {
        CommandMap commandMap = null;

        try {
            final Server server = Bukkit.getServer();
            final Method getCommandMap = server.getClass().getDeclaredMethod("getCommandMap");
            getCommandMap.setAccessible(true);

            commandMap = (CommandMap) getCommandMap.invoke(server);

            final Field bukkitCommands = SimpleCommandMap.class.getDeclaredField("knownCommands");
            bukkitCommands.setAccessible(true);

            //noinspection unchecked
            this.bukkitCommands = (Map<String, org.bukkit.command.Command>) bukkitCommands.get(commandMap);
        } catch (final Exception e) {
            plugin.getLogger().log(Level.SEVERE, "Could not get Command Map, Commands won't be registered!");
        }

        return commandMap;
    }

}
