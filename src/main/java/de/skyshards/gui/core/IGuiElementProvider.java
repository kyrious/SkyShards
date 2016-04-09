package de.skyshards.gui.core;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

public interface IGuiElementProvider {
	
	public int getGuiId();
	
	public Container provideServerContainer(EntityPlayer player, World world, int x, int y, int z);
	
	public GuiContainer provideClientContainer(EntityPlayer player, World world, int x, int y, int z);
}
