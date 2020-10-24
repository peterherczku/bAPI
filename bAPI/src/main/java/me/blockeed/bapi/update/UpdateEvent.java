package me.blockeed.bapi.update;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class UpdateEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final UpdateType type;

    public UpdateEvent(UpdateType type) {
        this.type=type;
    }

    public UpdateType getType() {
        return type;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
