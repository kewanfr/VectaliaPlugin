package fr.kewanb.vectaliaplugin;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.inventory.ItemStack;

public class CustomSwords {
	
	private Vectalia main;
	public Map<String, ItemStack> CustomSwords = new HashMap<>();


	
	
	public CustomSwords(Vectalia vectalia) {
		this.main = vectalia;
		CustomSwords.put("corruption", ItemStackHelper.buildCustomSword(main, "corruption"));
		CustomSwords.put("servitude", ItemStackHelper.buildCustomSword(main, "servitude"));
		CustomSwords.put("dilitro", ItemStackHelper.buildCustomSword(main, "dilitro"));
		CustomSwords.put("fulminante", ItemStackHelper.buildCustomSword(main, "fulminante"));
		CustomSwords.put("foudroyante", ItemStackHelper.buildCustomSword(main, "foudroyante"));
		CustomSwords.put("misericorde", ItemStackHelper.buildCustomSword(main, "misericorde"));
		//CustomSwords.put("demence", ItemStackHelper.buildCustomSword(main, "demence"));
		//CustomSwords.put("notung", ItemStackHelper.buildCustomSword(main, "notung"));
		CustomSwords.put("damnation", ItemStackHelper.buildCustomSword(main, "damnation"));

	}

	
	
}
