package me.blockeed.bapi.command.framework.base;

import me.blockeed.bessentials.bessentials.command.framework.commandclass.CommandClass;
import me.blockeed.bessentials.bessentials.command.framework.commandclass.CommandProvider;
import me.blockeed.bessentials.bessentials.command.framework.exception.CommandException;
import me.blockeed.bessentials.bessentials.utils.F;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class CommandHandler extends Command {

    private CommandClass commandClass;
    private String permission;

    protected CommandHandler(CommandClass commandClass, String name, String description, String usageMessage, List<String> aliases, String permission) {
        super(name, description, usageMessage, aliases);
        this.commandClass=commandClass;
        this.permission=permission;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        for (final Method method : commandClass.getClass().getDeclaredMethods()) {
            if (!method.getName().equalsIgnoreCase("onCommand")) {
                continue;
            }

            if (method.getParameterCount()==0)
                throw new CommandException("Method "+method.getName()+" in class "+commandClass.getClass().getName() +" - needs to have a parameter!");
            if (!CommandProvider.class.isAssignableFrom(method.getParameterTypes()[0]))
                throw new CommandException("Method " + method.getName() + " in class " + commandClass.getClass().getName() + " - first parameter needs to be a CommandProvider!");
            CommandProvider provider = new CommandProvider(sender, args, commandLabel);

            if (commandLabel==provider.getLabel()) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Nem vagy játékos!");
                    return true;
                }
                Player player = (Player) sender;
                if (!player.hasPermission(permission)) {
                    player.sendMessage(F.fail("Nincs jogosultságod a parancs használatára."));
                    return true;
                }

                try {
                    method.invoke(commandClass, provider);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }
}
