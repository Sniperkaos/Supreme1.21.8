package com.github.relativobr.supreme.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import com.github.relativobr.supreme.generic.machine.GenericMachine;
import com.github.relativobr.supreme.resource.SupremeComponents;
import com.github.relativobr.supreme.resource.magical.SupremeAttribute;
import com.github.relativobr.supreme.resource.magical.SupremeCetrus;
import com.github.relativobr.supreme.util.SupremeItemStack;
import com.github.relativobr.supreme.util.UtilEnergy;

import dev.cworldstar.utils.FormatUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

/**
 * Machine to combine 2 materials together.
 * <br>To create a recipe, use 
 * {@link #addCombinerRecipe(VirtualCombinerMachineRecipe)}.
 * @author cworldstar
 */
public class VirtualCombiner extends GenericMachine {
	
	public class VirtualCombinerMachineRecipe extends MachineRecipe {
		public VirtualCombinerMachineRecipe(int seconds, ItemStack[] input, ItemStack[] output) {
			super(seconds, input, output);
		}

		public VirtualCombinerMachineRecipe(ItemStack output, ItemStack[] inputs) {
			this(9600, inputs, new ItemStack[] {output});
		}

		private ItemStack makeDisplayItem(ItemStack item) {
			ItemStack display = item.clone();
			display.editMeta(meta -> {
				meta.displayName(FormatUtils.mm("<!italic>").append(display.displayName() == null ? display.effectiveName() : display.displayName()).append(FormatUtils.mm("<white>x " + Integer.toString(item.getAmount()))));
			});
			return display;
		}
		
		public ItemStack[] getInputDisplays() {
			ArrayList<ItemStack> i = new ArrayList<>();
			for(ItemStack input : getInput()) {
				i.add(makeDisplayItem(input));
			}
			return i.toArray(ItemStack[]::new);
		}
		
		public ItemStack getOutputDisplay() {
			return makeDisplayItem(getOutput()[0]);
		}

		public boolean matches(List<ItemStack> inputs) {
			inputs.removeIf(i -> i==null);
			if(inputs.size() < getInput().length) return false;
			int matches = 0;
			for(ItemStack input : getInput()) {
				for(ItemStack i2 : inputs) {
					if(i2.isSimilar(input) && i2.getAmount() >= input.getAmount()) {
						matches += 1;
						break;
					}
				}
			}
			return matches >= inputs.size();
		}
	}
	
	public static final SlimefunItemStack COMBINER_MACHINE = 
		new SupremeItemStack(
			"SUPREME_VIRTUAL_COMBINER",
			Material.PALE_MOSS_BLOCK,
			"&bCombiner", "", "&fCombines items together", "",
			LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), LoreBuilder.speed(10),
			UtilEnergy.energyPowerPerSecond(1000), "", "&3Supreme Machine"
		);
	
	public static final SlimefunItemStack COMBINER_MACHINE_2 = 
			new SupremeItemStack(
				"SUPREME_VIRTUAL_COMBINER_II",
				Material.PALE_MOSS_BLOCK,
				"&bCombiner II", "", "&fCombines items together", "",
				LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), LoreBuilder.speed(20),
				UtilEnergy.energyPowerPerSecond(10000), "", "&3Supreme Machine"
			);
	
	public static final SlimefunItemStack COMBINER_MACHINE_3 = 
			new SupremeItemStack(
				"SUPREME_VIRTUAL_COMBINER_III",
				Material.PALE_MOSS_BLOCK,
				"&bCombiner III", "", "&fCombines items together", "",
				LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), LoreBuilder.speed(40),
				UtilEnergy.energyPowerPerSecond(35000), "", "&3Supreme Machine"
			);
	
	public static final ItemStack[] RECIPE_VIRTUAL_COMBINER_MACHINE = new ItemStack[] {
			SupremeComponents.RUSTLESS_MACHINE.item(), SlimefunItems.AUTO_ANVIL.item(), SupremeComponents.RUSTLESS_MACHINE.item(),
			SupremeComponents.PETRIFIER_MACHINE.item(), SupremeComponents.SYNTHETIC_RUBY.item(), SupremeComponents.PETRIFIER_MACHINE.item(),
			SupremeComponents.ADAMANTIUM_PLATE.item(), SlimefunItems.PROGRAMMABLE_ANDROID_2.item(),SupremeComponents.ADAMANTIUM_PLATE.item()
	};
	
	public static final ItemStack[] RECIPE_VIRTUAL_COMBINER_MACHINE_2 = new ItemStack[] {
			SupremeComponents.CONVEYANCE_MACHINE.item(), SlimefunItems.AUTO_ANVIL_2.item(), SupremeComponents.CONVEYANCE_MACHINE.item(),
			SupremeComponents.CRYSTALLIZER_MACHINE.item(), COMBINER_MACHINE.item(), SupremeComponents.CRYSTALLIZER_MACHINE.item(),
			SupremeComponents.THORNIUM_ENERGIZED.item(), SupremeCetrus.CETRUS_VENTUS.item(),SupremeComponents.THORNIUM_ENERGIZED.item()
	};
	
	public static final ItemStack[] RECIPE_VIRTUAL_COMBINER_MACHINE_3 = new ItemStack[] {
			SupremeCetrus.CETRUS_LUMIUM.item(), SupremeComponents.THORNIUM_ENERGIZED.item(), SupremeCetrus.CETRUS_LUMIUM.item(),
			SupremeComponents.BLEND_MACHINE.item(), COMBINER_MACHINE_2.item(), SupremeComponents.BLEND_MACHINE.item(),
			SupremeComponents.SUPREME.item(), SupremeAttribute.ATTRIBUTE_BOMB.item(), SupremeComponents.SUPREME.item()
	};
	
	  public static Map<Block, MachineRecipe> processing = new HashMap<>();
	  public static Map<Block, Integer> progress = new HashMap<>();
	  private final Set<VirtualCombinerMachineRecipe> recipes = new HashSet<VirtualCombinerMachineRecipe>();

	
	public VirtualCombiner(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item, recipeType, recipe);
	}
	
	public void addRecipe(VirtualCombinerMachineRecipe recipe) {
		recipes.add(recipe);
	}
	
	@Override
	public int[] getInputSlots() {
		return new int[] {
				11, 15
		};
	}
	
	@Override
	public int[] getOutputSlots() {
		return new int[] {
				37,38,39,40,41,42,43,46,47,48,49,50,51,52
		};
	}
	
	@Override
	public int[] getInputBorderSlots() {
		return new int[] {
				1,2,3,5,6,7,10,12,14,16,19,20,21,23,24,25
		};
	}
	
	@Override
	public int[] getOutputBorderSlots() {
		return new int[] {
				36,44,45,53
		};
	}
	
	@Override
	public int[] getBorderSlots() {
		return new int[] {
				0,4,8,9,17,18,22,26,27,28,29,30,31,32,33,34,35
		};
	}
	
	@Override
	public int getStatusSlot() {
		return 13;
	}
	
	@Override
	public void preRegister() {
		addItemHandler(new BlockTicker() {
			@Override
			public boolean isSynchronized() {
				return true;
			}

			@Override
			public void tick(Block b, SlimefunItem item, Config data) {
				VirtualCombiner.this.tick(b);
			}
		});
	}
	
	@Override
	public String getRecipeSectionLabel(@NotNull Player player) {
		return "&7Combination:";
	}
	
	public MachineRecipe findNextRecipe(@NotNull BlockMenu menu) {
		for(VirtualCombinerMachineRecipe recipe : recipes) {
			List<ItemStack> inputs = new ArrayList<>();
			for(int slot : getInputSlots()) {
				inputs.add(menu.getItemInSlot(slot));
			}
			if(recipe.matches(inputs)) {
				for(ItemStack item : inputs) {
					for(ItemStack i2 : recipe.getInput()) {
						if(item.isSimilar(i2) && item.getAmount() >= i2.getAmount()) {
							item.subtract(i2.getAmount());
						}
					}
				}
				return recipe;
			}
		}
		return null;
	}
	
	  @Override
	  protected void registerDefaultRecipes() {
	    recipes.clear();
	    addCombinerRecipe(new VirtualCombinerMachineRecipe(new ItemStack(Material.GRASS_BLOCK, 64), new ItemStack[] {
	    		new ItemStack(Material.DIRT, 64),
	    		SupremeComponents.MOSS_BALL.item()
	    }));
	    addCombinerRecipe(new VirtualCombinerMachineRecipe(new ItemStack(Material.PODZOL, 1), new ItemStack[] {
	    		new ItemStack(Material.DIRT, 1),
	    		new ItemStack(Material.BEETROOT, 1),
	    }));
	    addCombinerRecipe(new VirtualCombinerMachineRecipe(new ItemStack(Material.MYCELIUM, 1), new ItemStack[] {
	    		new ItemStack(Material.DIRT, 1),
	    		new ItemStack(Material.BROWN_MUSHROOM, 1),
	    }));
	    addCombinerRecipe(new VirtualCombinerMachineRecipe(new ItemStack(Material.MYCELIUM, 1), new ItemStack[] {
	    		new ItemStack(Material.DIRT, 1),
	    		new ItemStack(Material.RED_MUSHROOM, 1),
	    }));
	    addCombinerRecipe(new VirtualCombinerMachineRecipe(new ItemStack(Material.COBWEB, 1), new ItemStack[] {
	    		new ItemStack(Material.STRING, 8),
	    		SlimefunItems.DUCT_TAPE.asOne(),
	    }));
	  }
	  
	  public void addCombinerRecipe(VirtualCombinerMachineRecipe recipe) {
		  recipes.add(recipe);
	  }
	  
	  @Override
	  public List<ItemStack> getDisplayRecipes() {
		  ArrayList<ItemStack> displayRecipes = new ArrayList<ItemStack>();
		    for(VirtualCombinerMachineRecipe recipe : recipes) {
		    	ItemStack[] displayItems = recipe.getInputDisplays();
		    	
		    	displayRecipes.add(displayItems[0]);
		    	displayRecipes.add(new SupremeItemStack("DISPLAY", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDA2NzJiODJmMGQxZjhjNDBjNTZiNDJkMzY5YWMyOTk0Yzk0ZGE0NzQ5MTAxMGMyY2U0MzAzZTM0NjViOTJhNyJ9fX0=", "Output Item ->").item());
		    	displayRecipes.add(displayItems[1]);
		    	displayRecipes.add(recipe.getOutputDisplay());
		    	displayRecipes.add(new SupremeItemStack("WALL", Material.GRAY_STAINED_GLASS_PANE, "", "").item());
		    	displayRecipes.add(new SupremeItemStack("WALL", Material.GRAY_STAINED_GLASS_PANE, "", "").item());

		    }
		    return displayRecipes;
	  }
	  
	  
	  
	  @Override
	  public void tick(Block b) {
		  BlockMenu menu = BlockStorage.getInventory(b);
		  if(menu == null) return;
		  if(isProcessing(b)) {
			  ItemStack recipeOutput = processing.get(b).getOutput()[0];
			  if(!menu.fits(recipeOutput, getOutputSlots())) {
				  updateStatusOutputFull(menu);
				  return;
			  }
			  if(getCharge(b.getLocation()) < getEnergyConsumption()) {
			        updateStatusConnectEnergy(menu, recipeOutput);
			  }
			  
			  if(takeCharge(b.getLocation())) {
				  int timeLeft = progress.get(b);
				  if(timeLeft > 0) {
					  ChestMenuUtils.updateProgressbar(menu, getStatusSlot(), timeLeft, processing.get(b).getTicks(), getProgressBar());
					  int time = Math.max(timeLeft - getSpeed(), 0);
					  progress.put(b, time);
				  } else {
					  menu.pushItem(recipeOutput.clone(), getOutputSlots());
					  progress.remove(b);
					  processing.remove(b);
					  updateStatusReset(menu);
				  }
			  }
		  } else {
			  MachineRecipe recipe = findNextRecipe(menu);
			  if(recipe != null) {
				  processing.put(b, recipe);
				  progress.put(b, 0);
			  } else {
				  updateStatusInvalidInput(menu);
			  }
		  }
	  }
	  
	  @Override
	  public String getMachineIdentifier() {
		  return "VIRTUAL_COMBINER";
	  }
	  
	  @Override
	  public ItemStack getProgressBar() {
		  return new ItemStack(Material.ANVIL);
	  }
	  
	  @Override
	  public MachineRecipe getProcessing(Block b) {
		  return processing.getOrDefault(b, null);
	  }
	  
	  public boolean isProcessing(Block b) {
		  return getProcessing(b) != null;
	  }


}
