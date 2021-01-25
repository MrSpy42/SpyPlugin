package io.github.MrSpy42.spyplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;


public final class SpyPlugin extends JavaPlugin {

	public static long getUsedMemory() {
	    return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576L;
	}
	
	public static long getTotalMemory() {
	    return Runtime.getRuntime().maxMemory() / 1048576L;
	}

	@Override
    public void onEnable() {
        
		getLogger().info("SpyPlugin has been enabled!");
		BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
            	if(getUsedMemory() > (getTotalMemory() - 200L)) {
            		getServer().broadcastMessage("[SpyPlugin] Resources are low! Server restart is recommended.");
            	}
            }
        }, 0L, 20L);
    }
    
    @Override
    public void onDisable() {
        
    	getLogger().info("SpyPlugin has been disabled!");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	if (cmd.getName().equalsIgnoreCase("memuse")) { // If the player typed /basic then do the following, note: If you only registered this executor for one command, you don't need this
    		sender.sendMessage("[SpyPlugin]" + String.valueOf(getUsedMemory()) + "MB/" + String.valueOf(getTotalMemory()) + "MB is used.");
    		return true;
    	} 
    	if (cmd.getName().equalsIgnoreCase("aboutspy")) { // If the player typed /basic then do the following, note: If you only registered this executor for one command, you don't need this
    		sender.sendMessage("[SpyPlugin] This is a plugin to check if there are enough resources for the server to continue running. Made by MrSpy42");
    		return true;
    	} 
    	return false; 
    }
}
