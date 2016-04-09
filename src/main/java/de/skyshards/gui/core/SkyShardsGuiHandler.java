package de.skyshards.gui.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import de.skyshards.core.SkyShardsMod;
import de.skyshards.gui.TestGuiElementProvider;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class SkyShardsGuiHandler implements IGuiHandler {

	private static SkyShardsGuiHandler instance;

	public static SkyShardsGuiHandler getInstance() {
		if (instance == null){
			instance = new SkyShardsGuiHandler();
			
			NetworkRegistry.INSTANCE.registerGuiHandler(SkyShardsMod.instance, instance);

			List<IGuiElementProvider> guiProviders = Arrays.asList(new TestGuiElementProvider(), new IGuiElementProvider() {
				
				@Override
				public Container provideServerContainer(EntityPlayer player, World world, int x, int y, int z) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public GuiContainer provideClientContainer(EntityPlayer player, World world, int x, int y, int z) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public int getGuiId() {
					// TODO Auto-generated method stub
					return -1;
				}
			});
			
			for(IGuiElementProvider guiProvider : guiProviders){
				instance.addGuiElementProvider(guiProvider);
			}
		}
		return instance;
	}
	
	public static void init() {
		getInstance();
	}

	private HashMap<Integer, IGuiElementProvider> registeredGuiProviders;

	private SkyShardsGuiHandler() {
		this.registeredGuiProviders = new HashMap<Integer, IGuiElementProvider>();
	}

	public void addGuiElementProvider(IGuiElementProvider provider) {
		registeredGuiProviders.put(provider.getGuiId(), provider);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (registeredGuiProviders.containsKey(ID)) {
			return registeredGuiProviders.get(ID).provideServerContainer(player, world, x, y, z);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (registeredGuiProviders.containsKey(ID)) {
			return registeredGuiProviders.get(ID).provideClientContainer(player, world, x, y, z);
		}
		return null;
	}

}
