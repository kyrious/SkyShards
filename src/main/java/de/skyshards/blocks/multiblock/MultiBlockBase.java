package de.skyshards.blocks.multiblock;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class MultiBlockBase extends Block {

	public MultiBlockBase(Material material, MapColor mapColor) {
		super(material, mapColor);
	}

	@Override
	public final TileEntity createTileEntity(World world, IBlockState state) {
		return createMultiBlockTileEntity();
	}

	protected MultiBlockTileEntity createMultiBlockTileEntity() {
		return new MultiBlockTileEntity();
	}

}
