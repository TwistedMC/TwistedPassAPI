package net.twistedmc.twistedpass.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class LevelUpEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private boolean isCancelled;
    private final Player player;
    int newLevels;

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }


    /***
     * This event is exclusive to the TwistedPassAPI.
     * Property of TwistedMC.
     *
     * @param player The player that has leveled up. Uses the <code>org.bukkit.entity.Player</code> Object.
     * @param newLevels The int[] Array from after <code>API.Calculations()</code> in <code>API.addXPToPlayer()</code>
     *
     * */
    public LevelUpEvent(Player player,int newLevels) {
        this.isCancelled = false;
        this.player = player;
        this.newLevels = newLevels;
    }

    public Player getPlayer() {
        return this.player;
    }
    public int getLevels() {
        return this.newLevels;
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
