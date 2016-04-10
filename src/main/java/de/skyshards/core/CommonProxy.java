package de.skyshards.core;

import de.skyshards.blocks.BlockRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {

	}

	public void init(FMLInitializationEvent e) {
		BlockRegistry.getInstance().registerBlocks();
	}

	public void postInit(FMLPostInitializationEvent e) {

	}
}
