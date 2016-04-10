package de.skyshards.core;

import de.skyshards.blocks.BlockRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e) {

	}

	@Override
	public void init(FMLInitializationEvent e) {
		BlockRegistry.getInstance().registerRenders();
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {

	}
}
