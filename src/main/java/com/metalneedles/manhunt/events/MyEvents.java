package com.metalneedles.manhunt.events;

import com.metalneedles.manhunt.Manhunt;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MyEvents implements Listener {

    @EventHandler
    public static void onRunnerMove(PlayerMoveEvent e) {
            Player p = e.getPlayer();

    }
}
