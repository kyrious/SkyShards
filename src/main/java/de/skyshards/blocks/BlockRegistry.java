package de.skyshards.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockRegistry {

	public static Block HEDRON_BLOCK;

	public void init() {
		HEDRON_BLOCK = new Hedron();
		register();
		registerRenders();
	}

	public void register() {
		GameRegistry.registerBlock(HEDRON_BLOCK, HEDRON_BLOCK.getUnlocalizedName());
	}

	public void registerRenders() {
		registerRenderForBlock(HEDRON_BLOCK);
	}

	public void registerRenderForBlock(Block b) {
		Item i = Item.getItemFromBlock(b);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, 0,
				new ModelResourceLocation(b.getUnlocalizedName()));
	}
}
