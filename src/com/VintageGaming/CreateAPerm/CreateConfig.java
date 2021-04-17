package com.VintageGaming.CreateAPerm;

import java.io.File;

import org.bukkit.plugin.Plugin;

public class CreateConfig {

	public static void create(Plugin plugin) {
	try{
		if(!plugin.getDataFolder().exists()){
			plugin.getDataFolder().mkdirs();
		}
		PermMain.config = new File(plugin.getDataFolder(), "config.yml");
		if(!PermMain.config.exists()){
			plugin.getLogger().info("config.yml not found, creating....");
			plugin.saveDefaultConfig();
		}else{
			plugin.getLogger().info("config.yml found, loadinig.....");
		}
	} catch(Exception e){
		e.printStackTrace();
	}
	}
}
