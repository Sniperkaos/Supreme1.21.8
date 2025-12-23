package dev.cworldstar.utils;

import org.bukkit.Material;

public class PlayerHeadBuilder extends ItemStackBuilder {

	public PlayerHeadBuilder() {
		super(Material.PLAYER_HEAD);
	}
	
	public PlayerHeadBuilder texture(String texture) {
		SkullCreator.loadBase64(item, texture);
		return this;
	}

}
