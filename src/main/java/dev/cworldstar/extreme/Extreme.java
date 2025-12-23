package dev.cworldstar.extreme;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import com.github.relativobr.supreme.Supreme;
import com.github.relativobr.supreme.util.ItemGroups;

import dev.cworldstar.protocol.ItemEditorProtocol;
import dev.cworldstar.protocol.ItemNameLerpPassthrough;
import dev.cworldstar.utils.ItemStackBuilder;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;

public class Extreme {
	
	public static SubItemGroup EXTREME_RESOURCE_GROUP;
	public static SubItemGroup EXTREME_MACHINE_GROUP;

	/**
	 * Uses reflection to setup items.
	 * @param clazz
	 * @param plugins The plugins that the setup requires.
	 */
	private static void setup(Class<?> clazz, String...plugins) {
		for(String plugin : plugins) {
			if(
				!Bukkit.getPluginManager().isPluginEnabled(plugin)
			) {
				Supreme.inst().log(Level.WARNING, plugin + " was not installed, content will be missing!");
				return;
			}
		}
		try {
			Method setup = clazz.getMethod("setup");
			setup.invoke(null);
			
			Method register = clazz.getMethod("register");
			register.invoke(null);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	private static void setupMachines() {
		setup(ExtremeMachine.class, "InfinityExpansion2");
	}

	private static void setupItemGroups() {
		EXTREME_RESOURCE_GROUP = new SubItemGroup(Supreme.key("EXTREME_RESOURCES"), ItemGroups.MULTI_CATEGORY, new ItemStackBuilder(Material.RESIN_BRICK).name("<gradient:#c13838:#8ba542:#672ca5:#a92e7f:#86dd5a>Extreme Resources").build());
		EXTREME_MACHINE_GROUP = new SubItemGroup(Supreme.key("EXTREME_MACHINES"), ItemGroups.MULTI_CATEGORY, new ItemStackBuilder(Material.OXIDIZED_COPPER_BULB).name("<gradient:#c13838:#8ba542:#672ca5:#a92e7f:#86dd5a>Extreme Machines").build());
		
		EXTREME_RESOURCE_GROUP.register(Supreme.inst());
		EXTREME_MACHINE_GROUP.register(Supreme.inst());
	}

	private static void setupMaterials() {
		setup(ExtremeResource.class, "InfinityExpansion2");
	}

	public static void tryEnable() {
		setupItemGroups();
		setupMaterials();
		setupMachines();
	    if(Bukkit.getPluginManager().isPluginEnabled("ProtocolLib")) {
	    	ItemEditorProtocol.start();
	    	ItemNameLerpPassthrough.start();
	    } else {
	    	Supreme.inst().getLogger().log(Level.WARNING, "ProtocolLib is not installed. Some items will not work as intended.");
	    }
	}
}
