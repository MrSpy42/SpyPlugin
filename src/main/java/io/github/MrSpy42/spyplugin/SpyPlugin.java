package io.github.MrSpy42.spyplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.lang.management.ManagementFactory;
import org.bukkit.scheduler.BukkitScheduler;
import com.sun.management.OperatingSystemMXBean;
import java.io.File;


public final class SpyPlugin extends JavaPlugin {

	final OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
	
	public static long getUsedMemory() {
	    return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576L;
	}
	
	public void pushLogMessage(String s) {
		getLogger().info(s);
	}
	public static long getTotalMemory() {
	    return Runtime.getRuntime().maxMemory() / 1048576L;
	}
	
	public static long getUsableSpaceMB() {
		File f = new File("C:\\"); //Windows only, Fix later
		return f.getUsableSpace() / 1048576L;
	}
	
	
	@Override
    public void onEnable() {
		this.getCommand("memuse").setExecutor(new PluginCommandExecutor(this));
		this.getCommand("serverload").setExecutor(new PluginCommandExecutor(this));
		this.getCommand("aboutspy").setExecutor(new PluginCommandExecutor(this));
		this.getCommand("freespace").setExecutor(new PluginCommandExecutor(this));
		
		BukkitScheduler scheduler = getServer().getScheduler();
	    scheduler.scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
            	double tps = Lag.getTPS();
            	double lagPercentage = Math.round((1.0D - tps / 20.0D) * 100.0D);
            	if(osBean.getProcessCpuLoad() > 0.98 || tps < 19.0D) {
            		if(JSONHandler.checkSetting()) {
            			for (Player player: Bukkit.getServer().getOnlinePlayers()) {
            			    if (player.isOp()) {
            			        player.sendMessage("§9[SpyPlugin] §4Server is overloaded! §FLag% is at " + String.valueOf(lagPercentage) + "%");
            			    }
            			}
            		} else {
            			getServer().broadcastMessage("§9[SpyPlugin] §4Server is overloaded! §FLag% is at " + String.valueOf(lagPercentage) + "%");
            		}
            	}
            	else if(getUsedMemory() > (getTotalMemory() - 200L)) {
            		if(JSONHandler.checkSetting()) {
            			for (Player player: Bukkit.getServer().getOnlinePlayers()) {
            			    if (player.isOp()) {
            			        player.sendMessage("§9[SpyPlugin] §4Server is running out of memory!");
            			    }
            			}
            		} else {
            			getServer().broadcastMessage("§9[SpyPlugin] §4Server is running out of memory!");
            		}
            	}
            	else if(getUsableSpaceMB() < 50L) {
            		if(JSONHandler.checkSetting()) {
            			for (Player player: Bukkit.getServer().getOnlinePlayers()) {
            			    if (player.isOp()) {
            			        player.sendMessage("§9[SpyPlugin] §4Server is running out of space! World corruption is possible!!");
            			    }
            			}
            		} else {
            			getServer().broadcastMessage("§9[SpyPlugin] §4Server is running out of space! World corruption is possible!!");
            		}
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
