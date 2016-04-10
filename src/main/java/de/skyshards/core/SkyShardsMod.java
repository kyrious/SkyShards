package de.skyshards.core;

import de.skyshards.gui.core.SkyShardsGuiHandler;
import de.skyshards.shardgen.EmptyWorldType;
import net.minecraft.world.WorldType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = SkyShardsMod.MODID, version = SkyShardsMod.VERSION, name = SkyShardsMod.MODID)
public class SkyShardsMod {
	public static final String MODID = "skyshards";
	public static final String VERSION = "0.1.1";

	@Instance
	public static SkyShardsMod instance = new SkyShardsMod();

	@SidedProxy(clientSide = "de.skyshards.core.CommonProxy", serverSide = "de.skyshards.core.CommonProxy")
	private static CommonProxy common;

	@SidedProxy(clientSide = "de.skyshards.core.ClientProxy")
	private static ClientProxy client;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		common.preInit(e);
		client.preInit(e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		common.init(e);
		client.init(e);
		WorldType.worldTypes[4] = new EmptyWorldType();
		SkyShardsGuiHandler.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		common.postInit(e);
		client.postInit(e);
	}
	
	public static boolean isClientSide(){
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		
		for(StackTraceElement e : stackTrace){
			if(e.getClassName().equals("net.minecraft.client.main.Main")){
				return true;
			}
		}
		
		return false;
	}

}