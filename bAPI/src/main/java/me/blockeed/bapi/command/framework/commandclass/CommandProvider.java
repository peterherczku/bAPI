package me.blockeed.bapi.command.framework.commandclass;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandProvider {

    private final CommandSender sender;
    private final String[] args;
    private final String label;

    public CommandProvider(CommandSender sender, String[] args, String label) {
        this.sender=sender;
        this.args=args;
        this.label=label;
    }

    public CommandSender getSender() {
        return sender;
    }

    public Player getPlayer() {
        if (sender instanceof Player) {
            return (Player) sender;
        } else {
            return null;
        }
    }

    public String getLabel() {
        return label;
    }

    public String[] getArgs() {
        return args;
    }
}
