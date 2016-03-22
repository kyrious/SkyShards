package de.skyshards.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockRegistry {

	private static BlockRegistry instance;
	
	public static BlockRegistry getInstance(){
		if(instance == null)
			instance = new BlockRegistry();
		return instance;
	}
	
	public final Block HEDRON_BLOCK;

	private BlockRegistry() {
		HEDRON_BLOCK = new Hedron();
	}

	public void registerBlocks() {
		registerBlock(HEDRON_BLOCK);
	}
	
	private void registerBlock(Block b){
		GameRegistry.registerBlock(b, b.getUnlocalizedName());
	}

	public void registerRenders() {
		registerRenderForBlock(HEDRON_BLOCK);
	}

	private void registerRenderForBlock(Block b) {
		Item item = Item.getItemFromBlock(b);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
				new ModelResourceLocation(b.getUnlocalizedName()));
	}
}
