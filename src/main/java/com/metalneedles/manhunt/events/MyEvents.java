package com.metalneedles.manhunt.events;

import com.metalneedles.manhunt.Manhunt;
import com.metalneedles.manhunt.items.ItemManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import static com.metalneedles.manhunt.Manhunt.getPrefix;

public class MyEvents implements Listener {

    @EventHandler
    public static void onRightClick(PlayerInteractEvent event) {
        Player hunter = event.getPlayer();
        if (event.getMaterial() != Material.COMPASS) {
            return;
        }
        if (!Manhunt.playerCouple.containsKey(hunter.getUniqueId())) {
            return;
        }
        // is the player in the same dimension?
        if (event.getPlayer().getWorld() != Bukkit.getPlayer(Manhunt.playerCouple.get(hunter.getUniqueId())).getWorld()) {
            hunter.sendMessage(Component.text(getPrefix())
                    .append(Component.text(" The Player ").color(NamedTextColor.RED))
                    .append(Component.text(Bukkit.getPlayer(Manhunt.playerCouple.get(hunter.getUniqueId())).getName()).color(NamedTextColor.RED).decorate(TextDecoration.BOLD))
                    .append(Component.text(" is in a different dimension.").color(NamedTextColor.RED)));
        } else {
            event.setCancelled(false);
            // if the runner went offline
            if (Bukkit.getPlayer(Manhunt.playerCouple.get(hunter.getUniqueId())) == null) {
                hunter.sendMessage(Component.text(Manhunt.getPrefix()).append(Component.text(" The Player specified is offline!").color(NamedTextColor.RED)));
            } else {
                ItemManager.createTracker(Bukkit.getPlayer(Manhunt.playerCouple.get(hunter.getUniqueId())).getLocation());
                Integer itemslot = Integer.valueOf(hunter.getInventory().first(Material.COMPASS));
                while (hunter.getInventory().contains(Material.COMPASS)) {
                    hunter.getInventory().remove(Material.COMPASS);
                }
                hunter.getInventory().setItem(itemslot, ItemManager.tracker);
                hunter.sendMessage(Component.text(getPrefix()).append(Component.text(" SUCCESS!").color(NamedTextColor.GREEN).decorate(TextDecoration.BOLD)).append(Component.text(" The Tracker is pointing towards ").color(NamedTextColor.GREEN).append(Component.text(Bukkit.getPlayer(Manhunt.playerCouple.get(hunter.getUniqueId())).getName())).decorate(TextDecoration.BOLD)));
            }
        }

        // if (event.getItem().getItemMeta() == null) {event.setCancelled(true);}
    }

    @EventHandler
    public static void onPlayerDeathEvent(PlayerDeathEvent event) {
        Player p = event.getPlayer();
        if (Manhunt.playerCouple.containsKey(p.getUniqueId())) {
            Manhunt.onlyTrackPlayer(p, Bukkit.getPlayer(Manhunt.playerCouple.get(p.getUniqueId())));
        } else if (Manhunt.playerCouple.containsValue(p.getUniqueId())) {
            Manhunt.getInstance().getServer().sendMessage(Component.text(getPrefix()).append(Component.text(" The speedrunner has died!").color(NamedTextColor.RED).decorate(TextDecoration.BOLD)));
            // Manhunt.getInstance().getServer().getOnlinePlayers().forEach(Player -> Player.playSound(Sound.sound()));
        }
    }




}
