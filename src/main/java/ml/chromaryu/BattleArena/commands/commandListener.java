package ml.chromaryu.BattleArena.commands;

import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

/**
 * Created by midgard on 16/11/23.
 */
public class commandListener extends ListenerAdapter {
    public void onMessage(MessageEvent event) {
        if(event.getMessage().equalsIgnoreCase("!new")) {
            event.getChannel().send().message("!new called.");
        }
    }
}
