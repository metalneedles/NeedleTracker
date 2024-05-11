package com.metalneedles.manhunt;

import com.metalneedles.manhunt.commands.enableManhunt;
import com.metalneedles.manhunt.events.MyEvents;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Manhunt extends JavaPlugin {

    private static Manhunt instance;
    private static String prefix;

    // is manhunt even enabled?
    public static boolean enabled;


    public static final Set<Player> RUNNERS = new HashSet<>();
    public List<Player> hunters = new ArrayList();


    @Override
    public void onEnable(){
        instance = this;
        enabled = false;
        prefix = "[" + getLogger().getName() + "]";

        this.getCommand("manhunt").setExecutor(new enableManhunt());
        this.getCommand("runner").setExecutor(new enableManhunt());
        this.getServer().getPluginManager().registerEvents(new MyEvents(), this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "MetaNeedles' Plugin enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "MetaNeedles' Plugin disabled!");
    }

    public static Manhunt getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return prefix;
    }
}
