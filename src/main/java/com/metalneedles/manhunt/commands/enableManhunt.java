package com.metalneedles.manhunt.commands;

import com.metalneedles.manhunt.Manhunt;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class enableManhunt implements CommandExecutor {




    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("manhunt")) {
            if (args.length == 0) {
                if (Manhunt.enabled) {
                    // Example on how to do the same with components (ChatColor is deprecated)
                    sender.sendMessage(
                            Component.text(Manhunt.getPrefix())
                                    .append(Component.text(" Manhunt is currently ").color(NamedTextColor.YELLOW))
                                    .append(Component.text("enabled!").color(NamedTextColor.GREEN).decorate(TextDecoration.BOLD))
                    );

                    sender.sendMessage("[Manhunt]" + ChatColor.YELLOW + " Manhunt is currently " + ChatColor.GREEN + "enabled!");
                } else {
                    sender.sendMessage("[Manhunt]" + ChatColor.YELLOW + " Manhunt is currently " + ChatColor.RED + "disabled!");
                }
                return true;
            }

            else {
                if (args[0].equalsIgnoreCase("enable") && args.length == 1) {
                    Manhunt.enabled = true;
                    sender.sendMessage(Component.text(Manhunt.getPrefix()).append(Component.text(" Manhunt has been enabled successfully!").color(NamedTextColor.YELLOW)));
                } else if (args[0].equalsIgnoreCase("disable") && args.length == 1) {
                    Manhunt.enabled = false;
                    sender.sendMessage(Component.text(Manhunt.getPrefix()).append(Component.text(" Manhunt has been disabled successfully!").color(NamedTextColor.YELLOW)));
                } else {
                    // if the player typed some nonsense

                    sender.sendMessage(Component.text(Manhunt.getPrefix())
                            .append(Component.text(" Wrong usage!").color(NamedTextColor.RED))
                            .appendNewline().append(Component.text("/manhunt").color(NamedTextColor.RED))
                            .appendNewline().append(Component.text("/manhunt enable OR /manhunt disable").color(NamedTextColor.RED))
                    );
                }
            }
            return true;
        } else if (cmd.getName().equalsIgnoreCase("runner")) {

            // String.join(", ", Manhunt.RUNNERS.stream().map(Player::getName).toList())

            if (args.length == 0) {
                sender.sendMessage(Component.text(Manhunt.getPrefix())
                        .append(Component.text(" The following Players are runners: "))
                        .append(Component.text(String.join(", ", Manhunt.RUNNERS.stream().map(Player::getName).toList())))
                );
            } else if (args[0].equalsIgnoreCase("add") && args.length == 2) {
                String playername = args[1];
                Player target = Bukkit.getServer().getPlayerExact(playername);
                if (target == null) {
                    sender.sendMessage(Component.text(Manhunt.getPrefix())
                            .append(Component.text(" The Player specified does not exist!").color(NamedTextColor.RED))
                    );
                } else {
                    Manhunt.RUNNERS.add(target);
                    sender.sendMessage(Component.text(Manhunt.getPrefix())
                            .append(Component.text(" The Player ").color(NamedTextColor.YELLOW))
                            .append(Component.text(target.getName()).decorate(TextDecoration.BOLD))
                            .append(Component.text(" has been added successfully!").color(NamedTextColor.YELLOW))
                    );
                }

            } else if (args[0].equalsIgnoreCase("remove") && args.length == 2) {
                String playername = args[1];
                Player target = Bukkit.getServer().getPlayerExact(playername);
                if (target == null) {
                    sender.sendMessage(Component.text(Manhunt.getPrefix())
                            .append(Component.text(" The Player specified does not exist!").color(NamedTextColor.RED))
                    );
                } else {
                    Manhunt.RUNNERS.remove(target);
                    sender.sendMessage(Component.text(Manhunt.getPrefix())
                            .append(Component.text(" The Player ").color(NamedTextColor.YELLOW))
                            .append(Component.text(target.getName()).decorate(TextDecoration.BOLD))
                            .append(Component.text(" has been removed successfully!").color(NamedTextColor.YELLOW))
                    );
                }

            }
            else if (args[0].equalsIgnoreCase("clear")) {
                Manhunt.RUNNERS.clear();
                sender.sendMessage(Component.text(Manhunt.getPrefix())
                        .append(Component.text(" The List of Runners has been cleared successfully!").color(NamedTextColor.YELLOW))
                );

            }
        } else if (cmd.getName().equalsIgnoreCase("hunter")) {

            if (args.length == 0) {
                sender.sendMessage(Component.text(Manhunt.getPrefix())
                        .append(Component.text(" The following Players are hunters: "))
                        .append(Component.text(String.join(", ", Manhunt.HUNTERS.stream().map(Player::getName).toList())))
                );
            } else if (args[0].equalsIgnoreCase("add") && args.length == 2) {
                String playername = args[1];
                Player target = Bukkit.getServer().getPlayerExact(playername);
                if (target == null) {
                    sender.sendMessage(Component.text(Manhunt.getPrefix())
                            .append(Component.text(" The Player specified does not exist!").color(NamedTextColor.RED))
                    );
                } else {
                    Manhunt.HUNTERS.add(target);
                    sender.sendMessage(Component.text(Manhunt.getPrefix())
                            .append(Component.text(" The Player ").color(NamedTextColor.YELLOW))
                            .append(Component.text(target.getName()).decorate(TextDecoration.BOLD))
                            .append(Component.text(" has been added successfully!").color(NamedTextColor.YELLOW))
                    );
                }

            } else if (args[0].equalsIgnoreCase("remove") && args.length == 2) {
                String playername = args[1];
                Player target = Bukkit.getServer().getPlayerExact(playername);
                if (target == null) {
                    sender.sendMessage(Component.text(Manhunt.getPrefix())
                            .append(Component.text(" The Player specified does not exist!").color(NamedTextColor.RED))
                    );
                } else {
                    Manhunt.HUNTERS.remove(target);
                    sender.sendMessage(Component.text(Manhunt.getPrefix())
                            .append(Component.text(" The Player ").color(NamedTextColor.YELLOW))
                            .append(Component.text(target.getName()).decorate(TextDecoration.BOLD))
                            .append(Component.text(" has been removed successfully!").color(NamedTextColor.YELLOW))
                    );
                }

            }
            else if (args[0].equalsIgnoreCase("clear")) {
                Manhunt.HUNTERS.clear();
                sender.sendMessage(Component.text(Manhunt.getPrefix())
                        .append(Component.text(" The List of Hunters has been cleared successfully!").color(NamedTextColor.YELLOW))
                );

            }

        }


        return true;
    }
}
