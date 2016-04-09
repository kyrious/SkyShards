package de.skyshards.blocks;

import java.util.LinkedList;

import de.skyshards.blocks.hedron.Hedron;
import de.skyshards.blocks.inventorytest.InventoryTileEntityTestBlock;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockRegistry {

	private static BlockRegistry instance;

	public static BlockRegistry getInstance() {
		if (instance == null)
			instance = new BlockRegistry();
		return instance;
	}

	private final LinkedList<Block> registeredBlocks = new LinkedList<Block>();

	private BlockRegistry() {
		registeredBlocks.add(new Hedron());
		registeredBlocks.add(new InventoryTileEntityTestBlock());
	}

	public void registerBlocks() {
		for (Block b : registeredBlocks)
			registerBlock(b);
	}

	private void registerBlock(Block b) {
		GameRegistry.registerBlock(b, b.getUnlocalizedName());
	}

	public void registerRenders() {
		for (Block b : registeredBlocks)
			registerRenderForBlock(b);
	}

	private void registerRenderForBlock(Block b) {
		Item item = Item.getItemFromBlock(b);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
				new ModelResourceLocation(b.getUnlocalizedName()));
	}
}
