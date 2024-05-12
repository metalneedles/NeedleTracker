package com.metalneedles.manhunt.events;

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import com.metalneedles.manhunt.Manhunt;
import com.metalneedles.manhunt.items.ItemManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import static com.metalneedles.manhunt.Manhunt.RUNNERS;
import static com.metalneedles.manhunt.Manhunt.getPrefix;

public class MyEvents implements Listener {
public static Location runnerLocation;

    @EventHandler
    public static void onRunnerMove(PlayerMoveEvent e) {
            Player p = e.getPlayer();
            if (Manhunt.enabled) {
                if (RUNNERS.contains(e.getPlayer().getName())) {
                    Location runnerloc = e.getTo();
                    runnerLocation.setWorld(runnerloc.getWorld());
                    System.out.println("set loc!");
                } else {
                    return;
                }
            } else {
                return;
            }
    }

    @EventHandler
    public static void onRightClick(PlayerInteractEvent e) {
        if (e.getMaterial() == Material.COMPASS) {
            e.setCancelled(false);
            Player p = e.getPlayer();
            p.setCompassTarget(runnerLocation);
            p.sendMessage(Component.text(getPrefix())
                    .append(Component.text("The Tracker is pointing towards ").color(NamedTextColor.GREEN)
                            .append(Component.text(String.join(", ", Manhunt.RUNNERS.stream().map(Player::getName).toList()))).decorate(TextDecoration.BOLD)));
        }
        // if (e.getItem().getItemMeta() == null) {e.setCancelled(true);}
    }





    @EventHandler
    public static void onDeath(PlayerPostRespawnEvent e) {
        Player p = e.getPlayer();
        if (RUNNERS.contains(p)) {
            p.sendMessage(Component.text(getPrefix()).append(Component.text(" Hunters Win!").color(NamedTextColor.YELLOW)));
        } else {
            p.getInventory().addItem(ItemManager.tracker);
        }

    }



}
