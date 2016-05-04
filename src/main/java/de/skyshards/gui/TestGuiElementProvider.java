package de.skyshards.gui;

import de.skyshards.gui.containers.BasicInventoryContainer;
import de.skyshards.gui.core.AbstractInventoryTileEntity;
import de.skyshards.gui.core.IGuiElementProvider;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class TestGuiElementProvider implements IGuiElementProvider {

	public static int GUI_ID = 0;

	@Override
	public int getGuiId() {
		return GUI_ID;
	}

	@Override
	public Container provideServerContainer(EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
		if (te instanceof AbstractInventoryTileEntity) {
			int[] slotXPos = new int[9];
			int[] slotYPos = new int[9];
			for(int i=0;i<9;i++){
				slotXPos[i] = i*18;
				slotYPos[i] = 0;
			}
			return new BasicInventoryContainer(player.inventory, (AbstractInventoryTileEntity) te, slotXPos, slotYPos);
		}
		return null;
	}

	@Override
	public GuiContainer provideClientContainer(EntityPlayer player, World world, int x, int y, int z) {
		Container c = provideServerContainer(player, world, x, y, z);
		if (c != null) {
			return new GuiContainer(c) {
				@Override
				protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
					drawRect(0, 0, 1024, 1024, Integer.MAX_VALUE);
				}
			};
		}
		return null;
	}

}
