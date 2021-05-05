package fr.kewanb.vectaliaplugin;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.kewanb.vectaliaplugin.utils.Fonctions;
import net.md_5.bungee.api.ChatColor;

public class ItemStackHelper {
	
	
	public static ItemStack buildItemStack(ItemStack is, String displayName, ArrayList<String> description) {
		final ItemMeta im = is.getItemMeta();
		
		im.setDisplayName(displayName);
		im.setLore(description);
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_UNBREAKABLE);
		
		is.setItemMeta(im);
		
		return is;
	}
	
	public static ItemStack buildCustomSword(Vectalia main, String swordName) {
		if(main.getConfig().getString("customswords." + swordName + ".name") != null) {
			String desc1 = main.getConfig().getString("customswords." + swordName + ".desc1");
			String desc2 = main.getConfig().getString("customswords." + swordName + ".desc2");
			String desc3 = main.getConfig().getString("customswords." + swordName + ".desc3");
			String desc4 = main.getConfig().getString("customswords." + swordName + ".desc4");
			String enchant1 = main.getConfig().getString("customswords." + swordName + ".enchant1");
			Integer enchant1Lvl = main.getConfig().getInt("customswords." + swordName + ".enchant1Lvl");
			String enchant2 = main.getConfig().getString("customswords." + swordName + ".enchant2");
			Integer enchant2Lvl = main.getConfig().getInt("customswords." + swordName + ".enchant2Lvl");
			String enchant3 = main.getConfig().getString("customswords." + swordName + ".enchant3");
			Integer enchant3Lvl = main.getConfig().getInt("customswords." + swordName + ".enchant3Lvl");
			Boolean flagsHide = main.getConfig().getBoolean("customswords." + swordName + ".flagsHide");
			
			ItemStack is = new ItemStack(Material.getMaterial(main.getConfig().getString("customswords." + swordName + ".material")), 1);
			final ItemMeta im = is.getItemMeta();
			
			im.setDisplayName(main.getConfig().getString("customswords." + swordName + ".name"));
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + Fonctions.upperCaseFirst(swordName) + " I");
			if(desc1 != null) lore.add(desc1);
			if(desc2 != null) lore.add(desc2);
			if(desc3 != null) lore.add(desc3);
			if(desc4 != null) lore.add(desc4);
			
			im.setLore(lore);
			if(flagsHide != null && flagsHide != false) im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_UNBREAKABLE);

			if(enchant1 != null) im.addEnchant(Enchantment.getByName(enchant1), enchant1Lvl, false);
			if(enchant2 != null) im.addEnchant(Enchantment.getByName(enchant2), enchant2Lvl, false);
			if(enchant3 != null) im.addEnchant(Enchantment.getByName(enchant3), enchant3Lvl, false);
			
			is.setItemMeta(im);
			
			return is;
		}else {
			System.out.println("Erreur, l'épee " + swordName + " n'existe pas !");
			return new ItemStack(Material.DIAMOND_SWORD);
		}
	}

}
