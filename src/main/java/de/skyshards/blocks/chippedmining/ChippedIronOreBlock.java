package de.skyshards.blocks.chippedmining;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ChippedIronOreBlock extends BasicChippingMiningBlock {

	public ChippedIronOreBlock() {
		super(new ItemStack(Block.getBlockFromName("iron_ore"), 1), 10);
	}

	@Override
	public String getUnlocalizedName() {
		return "ChippedIronOreBlock";
	}
	
	@Override
	public int getRenderType() {
		return 3;
	}
	
	@Override
	public float getBlockHardness(World worldIn, BlockPos pos) {
		return 1.5f;
	}
	
	@Override
	public int getHarvestLevel(IBlockState state) {
		return 2;
	}
	
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}
}
