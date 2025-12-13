package com.github.relativobr.supreme.machine;

import com.github.relativobr.supreme.generic.machine.SimpleItemWithLargeContainerMachine;
import com.github.relativobr.supreme.machine.recipe.VirtualAquariumMachineRecipe;
import com.github.relativobr.supreme.resource.SupremeComponents;
import com.github.relativobr.supreme.resource.magical.SupremeAttribute;
import com.github.relativobr.supreme.resource.magical.SupremeCetrus;
import com.github.relativobr.supreme.util.SupremeItemStack;
import com.github.relativobr.supreme.util.UtilMachine;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.inventory.InvUtils;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import io.github.thebusybiscuit.slimefun4.libraries.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.springframework.scheduling.annotation.Async;

@Async
public class VirtualAquarium extends SimpleItemWithLargeContainerMachine {

  public static final SlimefunItemStack VIRTUAL_AQUARIUM_MACHINE = new SupremeItemStack("SUPREME_VIRTUAL_AQUARIUM_I",
      Material.DARK_PRISMARINE, "&bVirtual Aquarium", "", "&fThis machine allows you to collect ",
      "&f items that are collected at sea.", "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE),
      LoreBuilder.speed(1), LoreBuilder.powerBuffer(1000), LoreBuilder.powerPerSecond(20), "", "&3Supreme Machine");
  public static final ItemStack[] RECIPE_VIRTUAL_AQUARIUM_MACHINE = new ItemStack[]{SupremeComponents.SYNTHETIC_RUBY.item(),
      new ItemStack(Material.FISHING_ROD), SupremeComponents.SYNTHETIC_RUBY.item(), SupremeComponents.INDUCTIVE_MACHINE.item(),
      SupremeComponents.PETRIFIER_MACHINE.item(), SupremeComponents.INDUCTIVE_MACHINE.item(), SupremeComponents.ADAMANTIUM_PLATE.item(),
      SlimefunItems.PROGRAMMABLE_ANDROID_2_FISHERMAN.item(), SupremeComponents.ADAMANTIUM_PLATE.item()};

  public static final SlimefunItemStack VIRTUAL_AQUARIUM_MACHINE_II = new SupremeItemStack(
      "SUPREME_VIRTUAL_AQUARIUM_II", Material.DARK_PRISMARINE, "&bVirtual Aquarium II", "",
      "&fThis machine allows you to collect", "&f items that are collected at sea.", "",
      LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(5),
      LoreBuilder.powerBuffer(5000), LoreBuilder.powerPerSecond(100), "", "&3Supreme Machine");
  public static final ItemStack[] RECIPE_VIRTUAL_AQUARIUM_MACHINE_II = new ItemStack[]{
      SupremeComponents.CONVEYANCE_MACHINE.item(), SupremeCetrus.CETRUS_LUMIUM.item(), SupremeComponents.CONVEYANCE_MACHINE.item(),
      SupremeComponents.INDUCTOR_MACHINE.item(), VirtualAquarium.VIRTUAL_AQUARIUM_MACHINE.item(), SupremeComponents.INDUCTOR_MACHINE.item(),
      SupremeComponents.THORNERITE.item(), SupremeCetrus.CETRUS_IGNIS.item(), SupremeComponents.THORNERITE.item()};

  public static final SlimefunItemStack VIRTUAL_AQUARIUM_MACHINE_III = new SupremeItemStack(
      "SUPREME_VIRTUAL_AQUARIUM_III", Material.DARK_PRISMARINE, "&bVirtual Aquarium III", "",
      "&fThis machine allows you to collect", "&f items that are collected at sea.", "",
      LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(15),
      LoreBuilder.powerBuffer(15000), LoreBuilder.powerPerSecond(300), "", "&3Supreme Machine");
  public static final ItemStack[] RECIPE_VIRTUAL_AQUARIUM_MACHINE_III = new ItemStack[]{SupremeComponents.THORNERITE.item(),
      SupremeAttribute.getMagic().item(), SupremeComponents.THORNERITE.item(), SupremeComponents.SUPREME.item(),
      VirtualAquarium.VIRTUAL_AQUARIUM_MACHINE_II.item(), SupremeComponents.SUPREME.item(), SupremeComponents.CRYSTALLIZER_MACHINE.item(),
      SupremeCetrus.CETRUS_LUMIUM.item(), SupremeComponents.CRYSTALLIZER_MACHINE.item()};

  public static Map<Block, MachineRecipe> processing = new HashMap<>();
  public static Map<Block, Integer> progress = new HashMap<>();
  private final Set<VirtualAquariumMachineRecipe> virtualAquariumMachineRecipe = new HashSet<VirtualAquariumMachineRecipe>();

  @ParametersAreNonnullByDefault
  public VirtualAquarium(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
    super(category, item, recipeType, recipe);
  }

  @Override
  protected void registerDefaultRecipes() {
    recipes.clear();
    addProduce(new VirtualAquariumMachineRecipe(new ItemStack(Material.FISHING_ROD),
        new ItemStack[]{new ItemStack(Material.SPONGE, 2), new ItemStack(Material.SEA_LANTERN, 2),
            new ItemStack(Material.COD, 20), new ItemStack(Material.SALMON, 20),
            new ItemStack(Material.TROPICAL_FISH, 20), new ItemStack(Material.INK_SAC, 20),
            new ItemStack(Material.NAUTILUS_SHELL, 6), new ItemStack(Material.STICK, 5),
            new ItemStack(Material.STRING, 5)}));
    addProduce(new VirtualAquariumMachineRecipe(new ItemStack(Material.TRIDENT),
        new ItemStack[]{new ItemStack(Material.SPONGE, 10), new ItemStack(Material.SEA_LANTERN, 10),
            new ItemStack(Material.COD, 5), new ItemStack(Material.SALMON, 5), new ItemStack(Material.TROPICAL_FISH, 5),
            new ItemStack(Material.INK_SAC, 5), new ItemStack(Material.NAUTILUS_SHELL, 20),
            new ItemStack(Material.STICK, 20), new ItemStack(Material.STRING, 20)}));
    addProduce(new VirtualAquariumMachineRecipe(new ItemStack(Material.GOLDEN_HOE),
        new ItemStack[]{new ItemStack(Material.SPONGE, 1), new ItemStack(Material.SEA_LANTERN, 1),
            new ItemStack(Material.COD, 3), new ItemStack(Material.SALMON, 3), new ItemStack(Material.TROPICAL_FISH, 2),
            new ItemStack(Material.INK_SAC, 2), new ItemStack(Material.NAUTILUS_SHELL, 5),
            new ItemStack(Material.STICK, 50), new ItemStack(Material.STRING, 33)}));
    addProduce(new VirtualAquariumMachineRecipe(new ItemStack(Material.POISONOUS_POTATO), 
    		new ItemStack[] {
    			new ItemStack(Material.PUFFERFISH, 5), new ItemStack(Material.OAK_LOG, 25), new ItemStack(Material.GLOW_BERRIES, 11),
    			new ItemStack(Material.NAUTILUS_SHELL, 5), new ItemStack(Material.MOSS_BLOCK, 15)
    		}
    	)
    );  
    
    addProduce(new VirtualAquariumMachineRecipe(SlimefunItems.MAGNET.asOne(), 
    		new ItemStack[] {
    			new ItemStack(Material.COPPER_INGOT, 50), new ItemStack(Material.MOSSY_COBBLESTONE, 50)
    		}
    	)
    );  
  }


  public void addProduce(@Nonnull VirtualAquariumMachineRecipe produce) {
    Validate.notNull(produce, "A produce cannot be null");
    virtualAquariumMachineRecipe.add(produce);
  }

  @Override
  public void preRegister() {
    addItemHandler(new BlockTicker() {
      public void tick(Block b, SlimefunItem sf, Config data) {
        VirtualAquarium.this.tick(b);
      }

      public boolean isSynchronized() {
        return true;
      }
    });
  }

  @Nonnull
  @Override
  public List<ItemStack> getDisplayRecipes() {
    return VirtualAquariumMachineRecipe.getAllRecipe();
  }

  @Nonnull
  @Override
  public String getRecipeSectionLabel(@Nonnull Player p) {
    return "&7Collects:";
  }


  @Override
  protected MachineRecipe findNextRecipe(@Nonnull BlockMenu inv) {
    int[] inputSlots = this.getInputSlots();

    for (int i = 0; i < inputSlots.length; ++i) {
      int slot = inputSlots[i];
      Iterator<VirtualAquariumMachineRecipe> iterator = this.virtualAquariumMachineRecipe.iterator();

      while (iterator.hasNext()) {
        VirtualAquariumMachineRecipe produce = (VirtualAquariumMachineRecipe) iterator.next();
        ItemStack itemInSlot = inv.getItemInSlot(slot);
        final ItemStack itemInInput = produce.getInput()[0];
        if (itemInSlot != null && itemInInput != null && (itemInSlot.getType() == itemInInput.getType())
            && InvUtils.fits(inv.toInventory(), produce.getOutput()[0], this.getOutputSlots())) {

          ItemMeta itemMeta = itemInSlot.getItemMeta();

          final SlimefunItem item = SlimefunItem.getByItem(itemInInput);
          if(item != null && item.equals(SlimefunItems.MAGNET.getItem()) ) {
        	  
        	  boolean consume = false;
        	  if(ThreadLocalRandom.current().nextInt(100) >= 85) {
        		  consume = true;
        	  }
        	  
        	  if(consume) {
        		  itemInSlot.subtract();
        	  }
        	  
        	  return produce;
          }
          
          if(itemMeta != null && !itemMeta.isUnbreakable()){
        	if(!(itemMeta instanceof Damageable)) {
        		itemInSlot.subtract();
        		return produce;
        	}
            Damageable durability = (Damageable) itemMeta;
            int current = durability.getDamage();
            if (current + 2 >= itemInSlot.getType().getMaxDurability()) {
              inv.consumeItem(slot);
            } else {
              ((Damageable) itemMeta).setDamage(current + 2);
              itemInSlot.setItemMeta(itemMeta);
              inv.replaceExistingItem(slot, itemInSlot);
            }
          }
          return produce;
        }
      }
    }

    return null;
  }

  @Override
  protected void tick(Block b) {
    BlockMenu inv = BlockStorage.getInventory(b);
    if (inv == null) {
      return;
    }

    if (isProcessing(b)) {

      var recipeOutput = processing.get(b).getOutput();
      if (notHasSpaceOutput(inv, recipeOutput)) {
        updateStatusOutputFull(inv);
        return;
      }

      if (getCharge(b.getLocation()) < getEnergyConsumption()) {
        updateStatusConnectEnergy(inv, recipeOutput[0]);
        return;
      }

      if (takeCharge(b.getLocation())) {
        int timeleft = progress.get(b);
        if (timeleft > 0) {
          ChestMenuUtils.updateProgressbar(inv, this.getStatusSlot(), timeleft, processing.get(b).getTicks(), getProgressBar());
          int time = timeleft - getSpeed();
          if (time < 0) {
            time = 0;
          }
          progress.put(b, time);
        } else {
          ItemStack material = UtilMachine.getMaterial(recipeOutput, UtilMachine.getRandomInt());
          if (material != null) {
            final ItemStack itemStack = material.clone();
            itemStack.setAmount(1);
            inv.pushItem(itemStack, getOutputSlots());
          }
          progress.remove(b);
          processing.remove(b);
          updateStatusReset(inv);
        }
      }
    } else {
      MachineRecipe next = findNextRecipe(inv);
      if (next != null) {
        processing.put(b, next);
        progress.put(b, next.getTicks());
      } else {
        updateStatusReset(inv);
      }
    }
  }

  @Nonnull
  @Override
  public String getMachineIdentifier() {
    return "VIRTUAL_AQUARIUM";
  }

  @Override
  public ItemStack getProgressBar() {
    return new ItemStack(Material.FISHING_ROD);
  }


  public MachineRecipe getProcessing(Block b) {
    return processing.get(b);
  }

  public boolean isProcessing(Block b) {
    return getProcessing(b) != null;
  }


}
