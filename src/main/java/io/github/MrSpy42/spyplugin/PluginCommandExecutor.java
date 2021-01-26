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
	    	sender.sendMessage("§9[SpyPlugin] §6Server load is at " + String.valueOf(Math.round(osBean.getProcessCpuLoad() * 100)) + "%");
	    	return true;
	   	} 
	   	if (cmd.getName().equalsIgnoreCase("memuse")) { 
	    	sender.sendMessage("§9[SpyPlugin] §6" + String.valueOf(SpyPlugin.getUsedMemory()) + "MB/" + String.valueOf( SpyPlugin.getTotalMemory()) + "MB is used.");
	    	return true;
	    } 
	    if (cmd.getName().equalsIgnoreCase("aboutspy")) { 
	    	sender.sendMessage("§9[SpyPlugin] §DThis is a plugin to check if there are enough resources for the server to continue running. Made by MrSpy42");
	    	return true;
	    } 
	    return false; 
	}
}
