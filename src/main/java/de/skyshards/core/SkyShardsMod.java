package de.skyshards.core;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = SkyShardsMod.MODID, version = SkyShardsMod.VERSION)
public class SkyShardsMod {
	public static final String MODID = "SkyShards";
	public static final String VERSION = "0.1";

	@Instance
	public static SkyShardsMod instance = new SkyShardsMod();

	@EventHandler
	public void init(FMLInitializationEvent event) {

	}
}
