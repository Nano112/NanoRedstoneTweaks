package fr.topred.Nano.nanoredstonetweaks;

import com.github.intellectualsites.plotsquared.bukkit.util.BukkitUtil;
import com.github.intellectualsites.plotsquared.plot.object.Location;
import com.github.intellectualsites.plotsquared.plot.object.Plot;
import com.github.intellectualsites.plotsquared.plot.object.PlotArea;
import com.github.intellectualsites.plotsquared.plot.object.PlotPlayer;
import com.github.intellectualsites.plotsquared.plot.util.Permissions;
import fr.topred.Nano.nanoredstonetweaks.signalstrenght.SsFurnace;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import static com.github.intellectualsites.plotsquared.plot.config.Captions.*;


public class Main extends JavaPlugin
{

    @Override

    public void onEnable()
    {
        //Fired When server starts

        System.out.println("-------------------------------------");
        System.out.println("|        Plot Plugin loaded         |");
        System.out.println("-------------------------------------");

        registerCommands();
        registerEvents();
    }

    @Override

    public void onDisable()
    {
        //Fired when server stops and disables all plugins
        System.out.println("-------------------------------------");
        System.out.println("|       Plot Plugin unloaded        |");
        System.out.println("-------------------------------------");
    }


    public void registerEvents()
    {
        PluginManager pm = getServer().getPluginManager();

    }


    public void registerCommands()
    {
        this.getCommand("f").setExecutor(new SsFurnace());
    }

    public static boolean canBuild(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location location = BukkitUtil.getLocation(event.getClickedBlock().getLocation());
        PlotArea area = location.getPlotArea();
        if (area == null) {
            return true;
        }
        Plot plot = area.getPlot(location);
        if (plot != null) {
            PlotPlayer plotPlayer = BukkitUtil.getPlayer(player);
            if (!plot.hasOwner()) {
                return Permissions.hasPermission(plotPlayer, PERMISSION_ADMIN_DESTROY_UNOWNED);
            }
            if (plot.getOwners().contains(player.getUniqueId())) return true;
            if (!plot.isAdded(plotPlayer.getUUID())) {

                return Permissions.hasPermission(plotPlayer, PERMISSION_ADMIN_DESTROY_OTHER);
            }
            return false;
        }
        PlotPlayer pp = BukkitUtil.getPlayer(player);
        return Permissions.hasPermission(pp, PERMISSION_ADMIN_DESTROY_ROAD);
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
