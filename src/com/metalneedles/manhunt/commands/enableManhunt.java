package com.metalneedles.manhunt.commands;

import com.metalneedles.manhunt.Manhunt;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class enableManhunt implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("manhunt")) {
            if (args.length == 0) {
                if (Manhunt.ManhuntEnabled) {
                    sender.sendMessage("[Manhunt]" + ChatColor.YELLOW + " Manhunt is currently " + ChatColor.GREEN + "enabled!");
                } else {
                    sender.sendMessage("[Manhunt]" + ChatColor.YELLOW + " Manhunt is currently " + ChatColor.RED + "disabled!");
                }
                return true;
            }
            else if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("enable") && args.length == 1) {
                    Manhunt.ManhuntEnabled = true;
                    sender.sendMessage("[Manhunt]" + ChatColor.GREEN + " Manhunt has been enabled successfully!");
                } else if (args[0].equalsIgnoreCase("disable") && args.length == 1) {
                    Manhunt.ManhuntEnabled = false;
                    sender.sendMessage("[Manhunt]" + ChatColor.RED + " Manhunt has been disabled successfully!");
                } else {
                    sender.sendMessage("[Manhunt]" + ChatColor.RED + " Wrong usage!");
                    sender.sendMessage("[Manhunt]" + ChatColor.RED + " /manhunt");
                    sender.sendMessage("[Manhunt]" + ChatColor.RED + " /manhunt enable");
                    sender.sendMessage("[Manhunt]" + ChatColor.RED + " /manhunt disable");
                }
            }
            return true;
        } else if (cmd.getName().equalsIgnoreCase("runner")) {
            if (args.length == 0) {
                sender.sendMessage(String.join(", ", ));
            } else if (args[0].equalsIgnoreCase("add") && args.length == 2) {
                String playername = args[1];
                Player target = Bukkit.getServer().getPlayerExact(playername);
                if (target == null) {
                    sender.sendMessage("[Manhunt]" + ChatColor.RED + " The player specified does not exist or is not online!");
                } else {
                    Manhunt.RUNNERS.add(target);
                    sender.sendMessage("[Manhunt]" + ChatColor.YELLOW + " Player " + target + " has been added successfully");
                }

            } else if (args[0].equalsIgnoreCase("remove") && args.length == 2) {
                String playername = args[1];
                Player target = Bukkit.getServer().getPlayerExact(playername);
                if (target == null) {
                    sender.sendMessage("[Manhunt]" + ChatColor.RED + " The player specified does not exist or is not online!");
                } else {
                    Manhunt.instance.runners.remove(target);
                    sender.sendMessage("[Manhunt]" + ChatColor.YELLOW + " Player " + target + " has been removed successfully");
                }

            }
            else if (args[0].equalsIgnoreCase("clear")) {

            }
        }


        return true;
    }
}
