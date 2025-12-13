package com.github.relativobr.supreme.machine.recipe;

import com.github.relativobr.supreme.Supreme;

import dev.cworldstar.utils.FormatUtils;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class VirtualAquariumMachineRecipe extends MachineRecipe {

  @ParametersAreNonnullByDefault
  public VirtualAquariumMachineRecipe(ItemStack input, ItemStack[] result) {
    super(Supreme.getSupremeOptions().getBaseTimeVirtualAquarium(), new ItemStack[]{input}, result);
  }
  
  private static ItemStack createMagnetDisplay(ItemStack display, String name, int chance) {
	    display.editMeta(meta -> {
	    	meta.displayName(FormatUtils.mm("<white>Gives <red>" + name + "<white> at a " + Integer.toString(chance) + "% chance."));
	    });
	    return display;
  }

  public static List<ItemStack> getAllRecipe() {
    List<ItemStack> displayRecipes = new ArrayList<ItemStack>();
    displayRecipes.add(CustomItemStack.create(Material.FISHING_ROD, null, "&fGive &bSponge &f2%"));
    displayRecipes.add(new ItemStack(Material.SPONGE));
    displayRecipes.add(CustomItemStack.create(Material.FISHING_ROD, null, "&fGive &bSea Lantern &f2%"));
    displayRecipes.add(new ItemStack(Material.SEA_LANTERN));
    displayRecipes.add(CustomItemStack.create(Material.FISHING_ROD, null, "&fGive &bCod &f20%"));
    displayRecipes.add(new ItemStack(Material.COD));
    displayRecipes.add(CustomItemStack.create(Material.FISHING_ROD, null, "&fGive &bSalmon &f20%"));
    displayRecipes.add(new ItemStack(Material.SALMON));
    displayRecipes.add(CustomItemStack.create(Material.FISHING_ROD, null, "&fGive &bTropical Fish &f20%"));
    displayRecipes.add(new ItemStack(Material.TROPICAL_FISH));
    displayRecipes.add(CustomItemStack.create(Material.FISHING_ROD, null, "&fGive &bInk Sac &f20%"));
    displayRecipes.add(new ItemStack(Material.INK_SAC));
    displayRecipes.add(CustomItemStack.create(Material.FISHING_ROD, null, "&fGive &bNautilus &f6%"));
    displayRecipes.add(new ItemStack(Material.NAUTILUS_SHELL));
    displayRecipes.add(CustomItemStack.create(Material.FISHING_ROD, null, "&fGive &bStick &f5%"));
    displayRecipes.add(new ItemStack(Material.STICK));
    displayRecipes.add(CustomItemStack.create(Material.FISHING_ROD, null, "&fGive &bString &f5%"));
    displayRecipes.add(new ItemStack(Material.STRING));
    displayRecipes.add(CustomItemStack.create(Material.TRIDENT, null, "&fGive &bSponge &f10%"));
    displayRecipes.add(new ItemStack(Material.SPONGE));
    displayRecipes.add(CustomItemStack.create(Material.TRIDENT, null, "&fGive &bSea Lantern &f10%"));
    displayRecipes.add(new ItemStack(Material.SEA_LANTERN));
    displayRecipes.add(CustomItemStack.create(Material.TRIDENT, null, "&fGive &bCod &f5%"));
    displayRecipes.add(new ItemStack(Material.COD));
    displayRecipes.add(CustomItemStack.create(Material.TRIDENT, null, "&fGive &bSalmon &f5%"));
    displayRecipes.add(new ItemStack(Material.SALMON));
    displayRecipes.add(CustomItemStack.create(Material.TRIDENT, null, "&fGive &bTropical Fish &f5%"));
    displayRecipes.add(new ItemStack(Material.TROPICAL_FISH));
    displayRecipes.add(CustomItemStack.create(Material.TRIDENT, null, "&fGive &bInk Sac &f5%"));
    displayRecipes.add(new ItemStack(Material.INK_SAC));
    displayRecipes.add(CustomItemStack.create(Material.TRIDENT, null, "&fGive &bNautilus &f20%"));
    displayRecipes.add(new ItemStack(Material.NAUTILUS_SHELL));
    displayRecipes.add(CustomItemStack.create(Material.TRIDENT, null, "&fGive &bStick &f20%"));
    displayRecipes.add(new ItemStack(Material.STICK));
    displayRecipes.add(CustomItemStack.create(Material.TRIDENT, null, "&fGive &bString &f20%"));
    displayRecipes.add(new ItemStack(Material.STRING));
    displayRecipes.add(CustomItemStack.create(Material.GOLDEN_HOE, null, "&fGive &bSponge &f1%"));
    displayRecipes.add(new ItemStack(Material.SPONGE));
    displayRecipes.add(CustomItemStack.create(Material.GOLDEN_HOE, null, "&fGive &bSea Lantern &f1%"));
    displayRecipes.add(new ItemStack(Material.SEA_LANTERN));
    displayRecipes.add(CustomItemStack.create(Material.GOLDEN_HOE, null, "&fGive &bCod &f3%"));
    displayRecipes.add(new ItemStack(Material.COD));
    displayRecipes.add(CustomItemStack.create(Material.GOLDEN_HOE, null, "&fGive &bSalmon &f3%"));
    displayRecipes.add(new ItemStack(Material.SALMON));
    displayRecipes.add(CustomItemStack.create(Material.GOLDEN_HOE, null, "&fGive &bTropical Fish &f2%"));
    displayRecipes.add(new ItemStack(Material.TROPICAL_FISH));
    displayRecipes.add(CustomItemStack.create(Material.GOLDEN_HOE, null, "&fGive &bInk Sac &f2%"));
    displayRecipes.add(new ItemStack(Material.INK_SAC));
    displayRecipes.add(CustomItemStack.create(Material.GOLDEN_HOE, null, "&fGive &bNautilus &f5%"));
    displayRecipes.add(new ItemStack(Material.NAUTILUS_SHELL));
    displayRecipes.add(CustomItemStack.create(Material.GOLDEN_HOE, null, "&fGive &bStick &f50%"));
    displayRecipes.add(new ItemStack(Material.STICK));
    displayRecipes.add(CustomItemStack.create(Material.GOLDEN_HOE, null, "&fGive &bString &f33%"));
    displayRecipes.add(new ItemStack(Material.STRING));
    
    // poisonous potato recipes for the virtual aquarium
    displayRecipes.add(CustomItemStack.create(Material.POISONOUS_POTATO, null, "&fGive &aPufferfish &f5%"));
    displayRecipes.add(new ItemStack(Material.PUFFERFISH));
    
    displayRecipes.add(CustomItemStack.create(Material.POISONOUS_POTATO, null, "&fGive &bNautilus &f5%"));
    displayRecipes.add(new ItemStack(Material.NAUTILUS_SHELL));
    
    displayRecipes.add(CustomItemStack.create(Material.POISONOUS_POTATO, null, "&fGive &bMoss &f15%"));
    displayRecipes.add(new ItemStack(Material.MOSS_BLOCK));
    
    displayRecipes.add(CustomItemStack.create(Material.POISONOUS_POTATO, null, "&fGive &bGlow Berries &f11%"));
    displayRecipes.add(new ItemStack(Material.GLOW_BERRIES));
    
    displayRecipes.add(CustomItemStack.create(Material.POISONOUS_POTATO, null, "&fGive &bOak Log &f25%"));
    displayRecipes.add(new ItemStack(Material.OAK_LOG));
    
    // magnet fishing
    displayRecipes.add(createMagnetDisplay(SlimefunItems.MAGNET.asOne(), "Copper Ingot", 50));
    displayRecipes.add(new ItemStack(Material.COPPER_INGOT));
    
    displayRecipes.add(createMagnetDisplay(SlimefunItems.MAGNET.asOne(), "Mossy Cobblestone", 50));
    displayRecipes.add(new ItemStack(Material.MOSSY_COBBLESTONE));

    return displayRecipes;
  }

}
