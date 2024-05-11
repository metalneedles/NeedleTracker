package com.metalneedles.plugin;

import com.metalneedles.plugin.commands.enableManhunt;
import com.metalneedles.plugin.events.MyEvents;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main extends JavaPlugin {

    public static Main instance = null;

    // is manhunt even enabled?
    public static boolean ManhuntEnabled;


    public static final Set<Player> RUNNERS = new HashSet<>();
    public List<Player> hunters = new ArrayList();



    @Override
    public void onEnable(){
        instance = this;
        ManhuntEnabled = false;
        this.getCommand("manhunt").setExecutor(new enableManhunt());
        this.getCommand("runner").setExecutor(new enableManhunt());
        this.getServer().getPluginManager().registerEvents(new MyEvents(), this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "MetaNeedles' Plugin enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "MetaNeedles' Plugin disabled!");
    }
}
