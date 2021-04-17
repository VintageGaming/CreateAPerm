package com.VintageGaming.CreateAPerm;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PermCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String flag, String[] args) {
		boolean createPerm = false;
		boolean removePerm = false;
		if (sender instanceof Player && sender.hasPermission("createaperm.create")) createPerm = true;
		if (sender instanceof Player && sender.hasPermission("createaperm.remove")) removePerm = true;
		if (!(sender instanceof Player)) {
			createPerm = true;
			removePerm = true;
		}
		
		
		if (args.length == 2 && args[0].equalsIgnoreCase("remove") && removePerm) {
			PermMain.getInstance().getConfig().set(args[1] + ".custom", null);
			PermMain.getInstance().getConfig().set(args[1] + ".permission", null);
			PermMain.getInstance().getConfig().set(args[1], null);
			
			PermMain.getInstance().saveConfig();
			PermMain.getInstance().reloadConfig();
			sender.sendMessage(ChatColor.GREEN + "Permission Removed!");
			return true;
		}
		if ((args.length == 4 || args.length == 3) && args[0].equalsIgnoreCase("add") && createPerm) {
			if (args.length == 3) {
				PermMain.getInstance().getConfig().set(args[1] + ".custom", args[2]);
				PermMain.getInstance().getConfig().set(args[1] + ".permission", "none");
			}
			else {
				PermMain.getInstance().getConfig().set(args[1] + ".custom", args[3]);
				PermMain.getInstance().getConfig().set(args[1] + ".permission", args[2]);
			}
			
			PermMain.getInstance().saveConfig();
			PermMain.getInstance().reloadConfig();
			sender.sendMessage(ChatColor.GREEN + "Permission Added!");
			return true;
		}
		
		if (createPerm || removePerm) {
			sender.sendMessage(ChatColor.RED + "Invalid Usage: /cap <add | remove> <Command_Name> <Regular_Permission> <New_Permission>");
			return true;
		}
		
		sender.sendMessage(ChatColor.RED + "You Don't Have Permission!");
		return true;
	}
}
