package io.github.MrSpy42.spyplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;

public class PluginCommandExecutor implements CommandExecutor {
	final OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
	
	private final SpyPlugin plugin;

	public PluginCommandExecutor(SpyPlugin plugin) {
		this.plugin = plugin; // Store the plugin in situations where you need it.
	}

	 @Override
	 public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		 if (cmd.getName().equalsIgnoreCase("serverload")) {
	    	sender.sendMessage("[SpyPlugin] Server load is at " + String.valueOf(Math.round(osBean.getProcessCpuLoad() * 100)) + "%");
	    	return true;
	   	} 
	   	if (cmd.getName().equalsIgnoreCase("memuse")) { // If the player typed /basic then do the following, note: If you only registered this executor for one command, you don't need this
	    	sender.sendMessage("[SpyPlugin] " + String.valueOf(SpyPlugin.getUsedMemory()) + "MB/" + String.valueOf( SpyPlugin.getTotalMemory()) + "MB is used.");
	    	return true;
	    } 
	    if (cmd.getName().equalsIgnoreCase("aboutspy")) { // If the player typed /basic then do the following, note: If you only registered this executor for one command, you don't need this
	    	sender.sendMessage("[SpyPlugin] This is a plugin to check if there are enough resources for the server to continue running. Made by MrSpy42");
	    	return true;
	    } 
	    return false; 
	}
}
