package fr.kewanb.vectaliaplugin.commands;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.kewanb.vectaliaplugin.Vectalia;

public class CommandCustomSword implements CommandExecutor {

	private Vectalia main;
	private ItemStack sword;
	
	public CommandCustomSword(Vectalia vectalia) {
		this.main = vectalia;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player)sender;
			
			
			if(args.length == 0) {
				Inventory inv = Bukkit.createInventory(null, 27, "§7Epées Custom");
				for(Map.Entry<String, ItemStack> customSword : main.customSwords.CustomSwords.entrySet()) {
					inv.addItem(customSword.getValue());
				}
				player.openInventory(inv);
				//String message = "Erreur, veuillez renseigner un nom d'epée: " + main.getConfig().getString("customswords.list");
				//player.sendMessage(message);

			}else {
				String name = args[0];
				if(main.getConfig().getString("customswords." + name + ".name") != null) {
					sword = main.customSwords.CustomSwords.get(name);
					if(sword == null) {
						sword = new ItemStack(Material.DIAMOND_SWORD, 1);
					}
					//sword = ItemStackHelper.buildCustomSword(main, name);
				}else {
					sword = new ItemStack(Material.DIAMOND_SWORD, 1);
				}
				
				player.getInventory().addItem(sword);
				player.updateInventory();
			}
		}
		return false;
	}

}
