package com.VintageGaming.CreateAPerm;

import java.io.File;

import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.VintageGaming.CreateAPerm.CreateConfig;

public class PermMain extends JavaPlugin {

	PluginManager pm;
	static Plugin plugin;
	static File config;
	
	@Override
	public void onEnable() {
		pm = getServer().getPluginManager();
		plugin = this;
		CreateConfig.create(this);
		pm.registerEvents(new BeforeCommand(), this);
		getCommand("cap").setExecutor(new PermCommand());
		registerAllPerms();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public void registerAllPerms() {
		for (String cmd : getConfig().getKeys(false)) {
			try {
			pm.addPermission(new Permission(getConfig().getString(cmd + ".custom")));
			}
			catch (Exception e) {
				
			}
		}
	}
	
	public static Plugin getInstance() {
		return plugin;
	}
}
