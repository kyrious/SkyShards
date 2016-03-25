package de.skyshards.core;

import de.skyshards.proxy.ProxyReg;
import de.skyshards.shardgen.EmptyWorldGen;
import de.skyshards.shardgen.EmptyWorldType;
import net.minecraft.world.WorldType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = SkyShardsMod.MODID, version = SkyShardsMod.VERSION, name = SkyShardsMod.MODID)
public class SkyShardsMod {
	public static final String MODID = "skyshards";
	public static final String VERSION = "0.1.1";

	@Instance
	public static SkyShardsMod instance = new SkyShardsMod();

	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		ProxyReg.getCommonProxy().init(event);

		WorldType.worldTypes[0] = new EmptyWorldType();
		
		GameRegistry.registerWorldGenerator(new EmptyWorldGen(), Integer.MAX_VALUE);
//		GameRegistry.registerWorldGenerator(new FullWorldGen(), 0);
	}
}
