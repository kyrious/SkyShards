package de.skyshards.blocks;

import java.util.LinkedList;

import de.skyshards.blocks.chippedmining.ChippedIronOreBlock;
import de.skyshards.blocks.hedron.Hedron;
import de.skyshards.blocks.inventorytest.InventoryTileEntityTestBlock;
import de.skyshards.core.SkyShardsMod;
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
//		registeredBlocks.add(new InventoryTileEntityTestBlock());
		registeredBlocks.add(new ChippedIronOreBlock());
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
		ModelResourceLocation resourceLocation;
		if (b instanceof IModelResourceLocationProvider) {
			resourceLocation = ((IModelResourceLocationProvider) b).getModelResourceLocation();
		} else {
			resourceLocation = new ModelResourceLocation(SkyShardsMod.MODID+":"+b.getUnlocalizedName(), "inventory");
		}
		System.err.println("registerRenderForBlock b.class: "+b.getClass()+ " resourceLocation="+resourceLocation);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, resourceLocation);
	}
}
