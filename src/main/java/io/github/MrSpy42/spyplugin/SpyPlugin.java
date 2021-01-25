package io.github.MrSpy42.spyplugin;

import org.bukkit.plugin.java.JavaPlugin;
import java.lang.management.ManagementFactory;
import org.bukkit.scheduler.BukkitScheduler;
import com.sun.management.OperatingSystemMXBean;


public final class SpyPlugin extends JavaPlugin {

	final OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
	
	public static long getUsedMemory() {
	    return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576L;
	}
	
	public static long getTotalMemory() {
	    return Runtime.getRuntime().maxMemory() / 1048576L;
	}
	
	
	@Override
    public void onEnable() {
		this.getCommand("memuse").setExecutor(new PluginCommandExecutor(this));
		this.getCommand("serverload").setExecutor(new PluginCommandExecutor(this));
		this.getCommand("aboutspy").setExecutor(new PluginCommandExecutor(this));
		
		BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
            	if(osBean.getProcessCpuLoad() > 0.97) {
            		getServer().broadcastMessage("[SpyPlugin] Server is overloaded!");
            	}
            	if(getUsedMemory() > (getTotalMemory() - 200L)) {
            		getServer().broadcastMessage("[SpyPlugin] Server is running out of memory!");
            	}
            }
        }, 1L, 20L);
		getLogger().info("SpyPlugin has been enabled!");
    }
    
    @Override
    public void onDisable() {
        
    	getLogger().info("SpyPlugin has been disabled!");
    }
    
   
}
