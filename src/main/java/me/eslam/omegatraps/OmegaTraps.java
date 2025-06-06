package me.eslam.omegatraps;

import me.eslam.omegatraps.blocks.ModBlocks;
import me.eslam.omegatraps.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OmegaTraps implements ModInitializer {
	public static final String MOD_ID = "omegatraps";
	 public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}