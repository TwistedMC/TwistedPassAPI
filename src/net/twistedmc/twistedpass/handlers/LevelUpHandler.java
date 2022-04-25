package net.twistedmc.twistedpass.handlers;

import net.twistedmc.twistedpass.events.LevelUpEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class LevelUpHandler implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onLevelUpEventC(LevelUpEvent event) {
    // Uncomment this line to disable this event completely.
    //    event.setCancelled(true);
    // ///////////////////////////////////// //
    // Normal Handler Below this line!

    }

}
