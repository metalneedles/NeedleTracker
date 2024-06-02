package com.metalneedles.manhunt;

import com.metalneedles.manhunt.commands.enableManhunt;
import com.metalneedles.manhunt.events.MyEvents;
import com.metalneedles.manhunt.items.ItemManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Manhunt extends JavaPlugin {

    private static Manhunt instance;
    private static String prefix;

    public static final Map<UUID, UUID> playerCouple = new HashMap<>();

    @Override
    public void onEnable(){
        instance = this;
        prefix = "[" + getLogger().getName() + "]";
        this.getCommand("track").setExecutor(new enableManhunt());
        this.getServer().getPluginManager().registerEvents(new MyEvents(), this);
        getServer().getConsoleSender().sendMessage(Component.text(getPrefix()).append(Component.text(" Plugin Enabled!").color(NamedTextColor.GREEN)));
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(Component.text(getPrefix()).append(Component.text(" Plugin Disabled!").color(NamedTextColor.RED)));
    }

    public static void trackPlayer(Player hunter, Player runner) {
        if (runner != null) {
            playerCouple.put(hunter.getUniqueId(), runner.getUniqueId());
            onlyTrackPlayer(hunter, runner);
        }
    }

    public static void onlyTrackPlayer(Player hunter, Player runner) {
        if (getInstance().getServer().getOnlinePlayers().contains(runner)) {
            if (hunter.getWorld() == runner.getWorld()) {
                ItemManager.createTracker(Bukkit.getPlayer(Manhunt.playerCouple.get(hunter.getUniqueId())).getLocation());
                Integer itemslot = Integer.valueOf(hunter.getInventory().first(Material.COMPASS));
                while (hunter.getInventory().contains(Material.COMPASS)) {
                    hunter.getInventory().remove(Material.COMPASS);
                }
                hunter.getInventory().setItem(itemslot, ItemManager.tracker);
                hunter.sendMessage(Component.text(getPrefix()).append(Component.text(" SUCCESS!").color(NamedTextColor.GREEN).decorate(TextDecoration.BOLD)).append(Component.text(" The Tracker is pointing towards ").color(NamedTextColor.GREEN).append(Component.text(Bukkit.getPlayer(Manhunt.playerCouple.get(hunter.getUniqueId())).getName())).append(Component.text("!").color(NamedTextColor.GREEN))));
            } else {
                hunter.sendMessage(Component.text(getPrefix()).append(Component.text(" FAIL!").color(NamedTextColor.RED).decorate(TextDecoration.BOLD)).append(Component.text(" The Player ").color(NamedTextColor.RED).append(Component.text(Bukkit.getPlayer(Manhunt.playerCouple.get(hunter.getUniqueId())).getName()))).append(Component.text(" is in another dimension.").color(NamedTextColor.RED)));
            }
        } else {
            hunter.sendMessage(Component.text(getPrefix()).append(Component.text("FAIL!").color(NamedTextColor.RED)).append(Component.text(" The Player specified is offline!")));
        }
    }


    public static Manhunt getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return prefix;
    }
}
