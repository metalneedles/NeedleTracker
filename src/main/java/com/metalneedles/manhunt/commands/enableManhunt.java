package com.metalneedles.manhunt.commands;

import com.metalneedles.manhunt.Manhunt;
import com.metalneedles.manhunt.items.ItemManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.jetbrains.annotations.NotNull;

import static com.metalneedles.manhunt.Manhunt.getPrefix;

public class enableManhunt implements CommandExecutor {




    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, String[] args) {
        // /track command
        if (cmd.getName().equalsIgnoreCase("track")) {
            // checks if the sender is a player that is online
            if (!(sender instanceof Player)) {
                sender.sendMessage(Component.text(Manhunt.getPrefix()).append(Component.text(" This command can only be executed by a Player!").color(NamedTextColor.RED)));
                return true;
            }


            else {
                Player pHunter = (Player) sender;
                if (args.length == 0) {
                    sender.sendMessage(Component.text(Manhunt.getPrefix()).append(Component.text(" Usage: /track <player> OR /track clear").color(NamedTextColor.RED)));
                    return true;


                    // if the command typed was /track <something>
                } else if (args.length == 1) {
                    String playername = args[0];
                    Player target = Bukkit.getServer().getPlayerExact(playername);
                    // player wants to track nobody or someone thats not online
                    if (target == null) {

                        // /track clear
                        if (args[1].equalsIgnoreCase("clear")) {
                            if (Manhunt.playerCouple.containsKey(pHunter.getUniqueId())) {
                                Manhunt.playerCouple.remove(pHunter.getUniqueId());
                                sender.sendMessage(Component.text(Manhunt.getPrefix()).append(Component.text(" Cleared tracker!").color(NamedTextColor.GREEN)));
                            } else {
                                sender.sendMessage(Component.text(Manhunt.getPrefix()).append(Component.text(" You are not tracking anyone!").color(NamedTextColor.RED)));
                            }
                            return true;
                        }
                        sender.sendMessage(Component.text(Manhunt.getPrefix()).append(Component.text(" The Player specified does not exist!").color(NamedTextColor.RED)));
                        return true;


                    }
                    // if the target is a valid player
                    else {
                        // does the player have a compass
                        if (pHunter.getInventory().containsAtLeast(ItemManager.tracker, 1)) {
                            Manhunt.playerCouple.put(pHunter.getUniqueId(), target.getUniqueId());
                            pHunter.setCompassTarget(target.getLocation());
                            pHunter.sendMessage(Component.text(getPrefix()).append(Component.text(" The Tracker is pointing towards ").color(NamedTextColor.GREEN).append(Component.text(target.getName())).decorate(TextDecoration.BOLD)));
                        } else {
                            Manhunt.playerCouple.put(pHunter.getUniqueId(), target.getUniqueId());
                            ItemManager.createTracker(Bukkit.getPlayer(Manhunt.playerCouple.get(pHunter.getUniqueId())).getLocation());
                            pHunter.getInventory().addItem(ItemManager.tracker);
                            pHunter.sendMessage(Component.text(getPrefix()).append(Component.text(" You have successfully acquired the Tracker!").color(NamedTextColor.GREEN)));
                            Manhunt.onlyTrackPlayer(pHunter, target);
                        }
                    }
                    return true;
                }
            }

        }
        return true;
    }
}
