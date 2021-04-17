package com.VintageGaming.CreateAPerm;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.permissions.PermissionAttachment;

public class BeforeCommand implements Listener {

	@EventHandler
	public void commandStop(PlayerCommandPreprocessEvent e) {
		String command = e.getMessage().replace("/", "");
		command = command.split(" ")[0];
		String com = "";
		boolean hasCom = false;
		for (String cmd : PermMain.getInstance().getConfig().getKeys(false)) {
			if (cmd.toLowerCase().equalsIgnoreCase(command)) {
				if (!e.getPlayer().hasPermission(PermMain.getInstance().getConfig().getString(cmd + ".custom"))) {
					e.getPlayer().sendMessage(ChatColor.RED + "You Don't Have Permission!");
					e.setCancelled(true);
					return;
				}
				hasCom = true;
				com = cmd;
			}
		}
		if (hasCom) {
			if (PermMain.getInstance().getConfig().getString(com + ".permission").equalsIgnoreCase("none")) {
				//There was no permission for the command, and that's why they probably have this plugin.
			}
			else {
				PermissionAttachment attachment = e.getPlayer().addAttachment(PermMain.getInstance(), 40);
				attachment.setPermission(PermMain.getInstance().getConfig().getString(com + ".permission"), true);
			}
		}
	}
}
