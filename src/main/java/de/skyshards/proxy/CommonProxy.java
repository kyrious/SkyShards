package de.skyshards.proxy;

import de.skyshards.blocks.BlockRegistry;
import de.skyshards.items.ItemRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {

	}

	public void init(FMLInitializationEvent e) {
		BlockRegistry.getInstance().registerBlocks();
		ItemRegistry.getInstance().registerItems();
	}

	public void postInit(FMLPostInitializationEvent e) {

	}
}
