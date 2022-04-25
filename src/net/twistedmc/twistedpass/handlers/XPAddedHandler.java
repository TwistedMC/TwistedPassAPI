package net.twistedmc.twistedpass.handlers;

import net.twistedmc.twistedpass.events.XPAddedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class XPAddedHandler implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onXPAddedEvent(XPAddedEvent event) {
        // Uncomment this line to disable this event completely.
        //    event.setCancelled(true);
        // ///////////////////////////////////// //
        // Normal Handler Below this line!


    }

}
