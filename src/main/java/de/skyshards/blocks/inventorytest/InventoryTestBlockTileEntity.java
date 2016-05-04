package de.skyshards.blocks.inventorytest;

import de.skyshards.gui.core.AbstractInventoryTileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class InventoryTestBlockTileEntity extends AbstractInventoryTileEntity {

	static {
		GameRegistry.registerTileEntity(InventoryTestBlockTileEntity.class, "InventoryTestBlockTileEntity");
	}

	public InventoryTestBlockTileEntity() {
		super("InventoryTestBlockTileEntity", 9);
	}

}
