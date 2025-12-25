package dev.cworldstar.extreme.machines;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.github.relativobr.supreme.generic.machine.GenericMachine;
import com.github.relativobr.supreme.util.UtilEnergy;

import dev.cworldstar.extreme.ExtremeResource;
import dev.cworldstar.utils.FormatUtils;
import dev.cworldstar.utils.ItemStackBuilder;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.MenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import net.guizhanss.infinityexpansion2.implementation.IEItems;

public class ExtremeCraftingTable extends GenericMachine {
	
	@AllArgsConstructor(access = AccessLevel.PROTECTED)
	public static class RecipeLookupAction {
		public static enum RecipeLookupResult {
			COMPLETED,
			NO_RESULT
		}
		private @Nullable ExtremeCraftingTableRecipe result;
		@Getter
		private @NotNull RecipeLookupResult status;
		
		@Nullable
		public ExtremeCraftingTableRecipe result() {
			return result;
		}
		
		@NotNull
		public RecipeLookupResult status() {
			return status;
		}
		
		@Nullable
		public ExtremeCraftingTableRecipe resultOrCall(Consumer<RecipeLookupAction> r) {
			if(!isCompleted()) {
				r.accept(this);
				return null;
			}
			return result;
		}
		
		@NotNull
		public ExtremeCraftingTableRecipe resultOrDefault(Supplier<ExtremeCraftingTableRecipe> supplier) {
			if(!isCompleted()) {
				return supplier.get();
			}
			return result;
		}

		public boolean isCompleted() {
			return status == RecipeLookupResult.COMPLETED;
		}
	}
	
	/**
	 * A recipe class for the ExtremeCraftingTable.
	 * Supports stack sizes in crafting.
	 */
	public static class ExtremeCraftingTableRecipe extends MachineRecipe implements Cloneable {
		
		@Override
		public ExtremeCraftingTableRecipe clone() {
			return new ExtremeCraftingTableRecipe(getInput(), getOutput()[0]);
		}
		
		public ItemStack getRecipeOutput() {
			return getOutput()[0];
		}
		
		private static ArrayList<ExtremeCraftingTableRecipe> EXTREME_CRAFTING_RECIPES = new ArrayList<>();
		
		public static List<ExtremeCraftingTableRecipe> getRecipes() {
			return EXTREME_CRAFTING_RECIPES.stream().collect(Collectors.toUnmodifiableList());
		}
		
		protected static void addRecipe(ExtremeCraftingTableRecipe recipe) {
			EXTREME_CRAFTING_RECIPES.add(recipe);
		}
		
		public static ExtremeCraftingTableRecipe create(ItemStack[] input, SlimefunItemStack output, int amount) {
			return create(input, output.item(), amount);
		}
		
		public static ExtremeCraftingTableRecipe create(ItemStack[] input, ItemStack output, int amount) {
			if(input.length < 25) {
				throw new UnsupportedOperationException("An Extreme Crafting Recipe cannot be below 25 in length!");
			}
			return new ExtremeCraftingTableRecipe(input, output);
		}
		
		private ExtremeCraftingTableRecipe(ItemStack[] input, ItemStack output) {
			super(0, input, new ItemStack[] {
					output
			});
		}
		
		private ExtremeCraftingTableRecipe(ItemStack[] input, SlimefunItemStack output) {
			this(input, output.item());
		}
		
		/**
		 * Gets an {@link ExtremeCraftingTableRecipe} by a given input array.
		 * @param input The input items in the table.
		 * @return The discovered recipe, or null if none exist.
		 */
		@Nullable
		public static RecipeLookupAction getByInput(ItemStack[] input) {
			for(ExtremeCraftingTableRecipe recipe : getRecipes()) {
				boolean matches = true;
				for(int i=0; i<=input.length; i++) {
					// check if the recipe array matches with the input one
					if(input[i] == null && recipe.getInput()[i] == null) {
						continue;
					} else if(input[i] == null || recipe.getInput()[i] == null) {
						matches = false;
						break;
					}
					if(input[i].isSimilar(recipe.getInput()[i])) {
						// check if the stack size is greater than or equal to the required one
						if(recipe.getInput()[i].getAmount() > input[i].getAmount()) {
							matches = false;
							break;
						}
					}
				}
				if(matches) {
					return new RecipeLookupAction(recipe, RecipeLookupAction.RecipeLookupResult.COMPLETED);
				}
			}
			return new RecipeLookupAction(null, RecipeLookupAction.RecipeLookupResult.NO_RESULT);
		}
		
		public static RecipeLookupAction getByInput(Collection<ItemStack> input) {
			return getByInput(input.toArray(ItemStack[]::new));
		}

		/**
		 * This method does not take into account whether or not an ItemStack actually
		 * contains the items it needs to take. It should have already been checked in
		 * {@link #getByInput(ItemStack[])}.
		 * @param items
		 */
		public void take(ItemStack[] items) {
			for(ItemStack item : items) {
				for(ItemStack input : getInput()) {
					if(input.isSimilar(item)) {
						item.subtract(input.getAmount());
					}
				}
			}
		}
		
		public void take(Collection<ItemStack> items) {
			take(items.toArray(ItemStack[]::new));
		}
	}
	
	public static final SlimefunItemStack EXTREME_CRAFTING_TABLE_ITEM = new SlimefunItemStack("EXTREME_CRAFTING_TABLE_ITEM", 
			new ItemStackBuilder(Material.STRIPPED_PALE_OAK_WOOD)
			.name("<red>Extreme Crafting Table")
			.lore(new String[] {
					"",
					"<white>This machine is used for crafting end-game items.",
					"<white>It requires a one-time charge of 100,000J per craft.",
					"",
					"%rainbow%Extreme Machine</rainbow>",
					UtilEnergy.energyPowerPerItem(100000),
					UtilEnergy.energyBuffer(100000),
					"",
					"<gradient:#c13838:#8ba542:#672ca5:#a92e7f:#86dd5a>ᴇxᴛʀᴇᴍᴇ ᴍᴀᴄʜɪɴᴇ"
			})
			.pdc(container -> {
				container.set(ItemStackBuilder.COLOR_LERP_KEY, PersistentDataType.DOUBLE, -100D);
			})
	.build());

	/**
	 * ExtremeCraftingTable recipes are a 5x5 grid.
	 * Adds the default recipes for the ExtremeCraftingTable.
	 */
	public void addDefaultRecipes() {
		addRecipe(ExtremeCraftingTableRecipe.create(new ItemStack[] {
				ExtremeResource.CORE_OF_ALLOY_SINGULARITY.getItem(), ExtremeResource.SUPREME_SINGULARITY.getItem(), ExtremeResource.LIFE_ALLOY.getItem(), ExtremeResource.SUPREME_SINGULARITY.getItem(), ExtremeResource.CORE_OF_ALLOY_SINGULARITY.getItem(),
				ExtremeResource.CORE_OF_BLOCK_SINGULARITY.getItem(), ExtremeResource.SUPREME_SINGULARITY.getItem(), ExtremeResource.LIFE_ALLOY.getItem(), ExtremeResource.SUPREME_SINGULARITY.getItem(), ExtremeResource.CORE_OF_BLOCK_SINGULARITY.getItem(),
				ExtremeResource.CORE_OF_DEATH_SINGULARITY.getItem(), ExtremeResource.SUPREME_SINGULARITY.getItem(), ExtremeResource.LIFE_ALLOY.getItem(), ExtremeResource.SUPREME_SINGULARITY.getItem(), ExtremeResource.CORE_OF_DEATH_SINGULARITY.getItem(),
				ExtremeResource.CORE_OF_COLOR_SINGULARITY.getItem(), IEItems.INSTANCE.getVOID_SMELTERY().item(), IEItems.INSTANCE.getVOID_SMELTERY().item(), IEItems.INSTANCE.getVOID_SMELTERY().item(), ExtremeResource.CORE_OF_DEATH_SINGULARITY.getItem(),
				ExtremeResource.CORE_OF_NATURE_SINGULARITY.getItem(), IEItems.INSTANCE.getINFINITY_MACHINE_CIRCUIT().item(), IEItems.INSTANCE.getINFINITY_MACHINE_CORE().item(), IEItems.INSTANCE.getINFINITY_MACHINE_CIRCUIT().item(), ExtremeResource.CORE_OF_NATURE_SINGULARITY.getItem(),
		}, ExtremeSmeltery.EXTREME_SMELTERY_I, 1));
	}
	
	public static void addRecipe(ExtremeCraftingTableRecipe recipe) {
		ExtremeCraftingTableRecipe.addRecipe(recipe);
	}
	
	public ExtremeCraftingTable(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item, recipeType, recipe);
		
		addItemHandler(new BlockTicker() {
			@Override
			public boolean isSynchronized() {
				return false;
			}

			@Override
			public void tick(Block b, SlimefunItem item, Config data) {
				if(unique) {
					onUniqueTick(BlockStorage.getInventory(b), b);
				}
			}
		});
		
		addDefaultRecipes();
	}
	
	private void onUniqueTick(BlockMenu inventory, Block b) {
		inventory.replaceExistingItem(16, LEFT_CLICK_TO_CRAFT);
		inventory.addMenuClickHandler(16, new MenuClickHandler() {
			@Override
			public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
				tryCraft(p, b);
				return false;
			}
		});
	}

	
	/**
	 * Attempts to craft using the items in the crafting table.
	 * @param p The player crafting.
	 * @param b The block location.
	 */
	public void tryCraft(Player p, Block b) {
		// check charge first
		boolean hasCharge = getCharge(b.getLocation()) >= 100000;
		if(!hasCharge) {
			p.sendMessage(FormatUtils.mm("<red>You do not have enough electricity to complete this action!"));
			p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_PLACE, 0.5F, 0);
			return;
		}
		
		BlockMenu menu = BlockStorage.getInventory(b);
		ArrayList<ItemStack> items = new ArrayList<>();
		for(int slot : getInputSlots()) {
			items.add(menu.getItemInSlot(slot));
		}
		
		RecipeLookupAction result = ExtremeCraftingTableRecipe.getByInput(items);
		if(!result.isCompleted()) {
			// at this point, the recipe is not null
			ExtremeCraftingTableRecipe recipe = result.result();
			
			// first we check if the output slot has room for the item
			if(!menu.fits(recipe.getRecipeOutput(), getOutputSlots())) {
				// if not, cancel craft
				p.sendMessage(FormatUtils.mm("<red>You do not have room to craft this item!"));
				p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_PLACE, 0.5F, 0);
				return;
			}
			
			// now we remove the charge from the block
			removeCharge(b.getLocation(), 100000);
			
			// remove the recipe items
			recipe.take(items);
			
			// finally, push to output
			menu.pushItem(recipe.getRecipeOutput().clone(), getOutputSlots());
		} else {
			p.sendMessage(FormatUtils.mm("<red>The given recipe did not exist."));
			p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_PLACE, 0.5F, 0);
		}
	}

	private static final ItemStack LEFT_CLICK_TO_CRAFT = new ItemStackBuilder(Material.CRAFTING_TABLE)
			.name("<red>Left-click to craft.")
			.build();
	
	@Override
	protected void constructMenu(BlockMenuPreset preset) {

		super.constructMenu(preset);
	}
	
	@Override
	public int[] getInputSlots() {
		return new int[] {
			9,10,11,12,13,
			18,19,20,21,22,
			27,28,29,30,31,
			36,37,38,39,40,
			45,46,47,48,49
		};
	}

	@Override
	public int[] getBorderSlots() {
		return new int[] {
				33,35
		};
	}
	
	@Override
	public int[] getInputBorderSlots() {
		return new int[] {
				0,1,2,3,4,5,
						  23,
						  32,
						  41,
						  50
		};
	}
	
	@Override
	public int[] getOutputBorderSlots() {
		return new int[] {
				42,43,44,
				51,53
		};
	}
	
	@Override
	public int getStatusSlot() {
		return 34;
	}
	
	@Override
	public int[] getOutputSlots() {
		return new int[] {
			52	
		};
	}

}
