package net.twistedmc.twistedpass.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class XPAddedEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private boolean isCancelled;
    private final Player player;
    private final int xp;
    private final boolean isSuperCharged;

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public XPAddedEvent(Player player,int xp,boolean Supercharged) {
        this.isCancelled = false;
        this.player = player;
        this.xp = xp;
        this.isSuperCharged = Supercharged;
    }

    public Player getPlayer() {
        return this.player;
    }
    public int getXP() {
        return this.xp;
    }


    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }
}
