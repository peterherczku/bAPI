package me.blockeed.bapi.update;

import me.blockeed.bapi.utils.Module;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class UpdateManager extends Module {

    public UpdateManager(JavaPlugin plugin) {
        super(plugin,"Update Manager");
    }

    @Override
    public void onLoad() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(getPlugin(), new Runnable() {
            int i = 0;
            @Override
            public void run() {
                i++;
                Bukkit.getPluginManager().callEvent(new UpdateEvent(UpdateType.SEC));
                double tensecDivided = i/10;
                double fifteensecDivided = i/15;
                double thirtysecDivided = i/30;
                double minuteDivided = i/60;
                double fiveminuteDivided = i/(5*60);
                double tenminuteDivided = i/(10*60);
                double fifteenminuteDivided = i/(15*60);
                double thirtyminuteDivided = i/(30*60);
                double fourtyfiveminuteDivided = i/(45*60);
                double sixtyminuteDivided = i/(60*60);
                if (i==(int) tensecDivided) {
                    Bukkit.getPluginManager().callEvent(new UpdateEvent(UpdateType.SEC_10));
                }
                if (i==(int) fifteensecDivided) {
                    Bukkit.getPluginManager().callEvent(new UpdateEvent(UpdateType.SEC_15));
                }
                if (i==(int) thirtysecDivided) {
                    Bukkit.getPluginManager().callEvent(new UpdateEvent(UpdateType.SEC_30));
                }
                if (i==(int) minuteDivided) {
                    Bukkit.getPluginManager().callEvent(new UpdateEvent(UpdateType.MIN));
                }
                if (i==(int) fiveminuteDivided) {
                    Bukkit.getPluginManager().callEvent(new UpdateEvent(UpdateType.MIN_5));
                }
                if (i==(int) tenminuteDivided) {
                    Bukkit.getPluginManager().callEvent(new UpdateEvent(UpdateType.MIN_10));
                }
                if (i==(int) fifteenminuteDivided) {
                    Bukkit.getPluginManager().callEvent(new UpdateEvent(UpdateType.MIN_15));
                }
                if (i==(int) thirtyminuteDivided) {
                    Bukkit.getPluginManager().callEvent(new UpdateEvent(UpdateType.MIN_30));
                }

                if (i==(int) fourtyfiveminuteDivided) {
                    Bukkit.getPluginManager().callEvent(new UpdateEvent(UpdateType.MIN_45));
                }
                if (i==(int) sixtyminuteDivided) {
                    Bukkit.getPluginManager().callEvent(new UpdateEvent(UpdateType.MIN_60));
                }
            }
        }, 0, 20);
    }

}
