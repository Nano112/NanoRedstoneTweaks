package fr.topred.Nano.nanoredstonetweaks.signalstrenght;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Furnace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import fr.topred.Nano.nanoredstonetweaks.Main;

public class SsFurnace implements CommandExecutor
{

    public boolean isInt(String s) {
        System.out.println("converting" + s);
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String commandName, String[] strings)
    {
        if( !(commandSender instanceof Player))
        {
            commandSender.sendMessage("You must be a player to execute this command !");
        }
        Player player = (Player)commandSender;
        Location l = new Location(player.getWorld(), player.getLocation().getBlockX(),
                player.getLocation().getBlockY() - 1, player.getLocation().getBlockZ());


        int pow = 0, i = 0;
        if (strings.length != 1)
        {
            player.sendMessage("/f [0-15]");
        }
        String string = strings[0];
        player.sendMessage(string);
        if (!(isInt(string))) {
            player.sendMessage("You have to use a number");
            return false;
        }
        pow = Integer.parseInt(string);
        if (pow > 15 || pow < 0) {
            player.sendMessage("The number you used is out of bounds");
            return false;
        }
        PlayerInteractEvent event = new PlayerInteractEvent(player, Action.LEFT_CLICK_BLOCK, null, l.getBlock(), BlockFace.UP) ;
        if(Main.canBuild(event))
        {
            l.getBlock().setType(Material.AIR);
            l.getBlock().setType(Material.FURNACE);
            Furnace f = (Furnace) (player.getWorld().getBlockAt(l).getState());
            Inventory inv = f.getInventory();

            ItemStack is = new ItemStack(Material.REDSTONE, 1);
            i = 0;
            while (((float) i / (3 * 64)) < ((float) pow / 15)) {
                inv.addItem(is);
                i++;
            }

        }
        else
        {
            if (player.getName().equals("Spiikesan"))
            {
                player.sendMessage("Retourne sur ton plot fdp !!!");
            }
            else
            {
                player.sendMessage("You have to be in your plot ");
            }
        }
        return true;
    }



}
