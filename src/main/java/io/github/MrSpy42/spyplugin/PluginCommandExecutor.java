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
		else if (cmd.getName().equalsIgnoreCase("memuse")) { 
	    	sender.sendMessage("§9[SpyPlugin] §6" + String.valueOf(SpyPlugin.getUsedMemory()) + "MB/" + String.valueOf( SpyPlugin.getTotalMemory()) + "MB is used.");
	    	return true;
	    } 
	   	else if (cmd.getName().equalsIgnoreCase("aboutspy")) { 
	    	sender.sendMessage("§9[SpyPlugin] §DThis is a performance monitoring plugin.It checks stuff (e.g ram, server load) and informs the user if there's something wrong (e.g server overload, running out of ram). Settings can be changed in spysettings.json in the folder where the server is located. Made by MrSpy42");
	    	return true;
	   	}
	   	else if (cmd.getName().equalsIgnoreCase("freespace")) {
	   		sender.sendMessage("§9[SpyPlugin] §6Free space : " + String.valueOf(SpyPlugin.getUsableSpaceMB()) + "MB");
	   		return true;
	   	}
	    return false; 
	}
}
