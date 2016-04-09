package de.skyshards.blocks.inventorytest;

import de.skyshards.core.SkyShardsMod;
import de.skyshards.gui.TestGuiElementProvider;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class InventoryTileEntityTestBlock extends BlockContainer {
	
	public InventoryTileEntityTestBlock() {
		super(Material.rock, MapColor.blackColor);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public String getUnlocalizedName() {
		return "InventoryTestBlock";
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new InventoryTestBlockTileEntity();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			playerIn.openGui(SkyShardsMod.instance, TestGuiElementProvider.GUI_ID, worldIn, pos.getX(), pos.getY(),
					pos.getZ());
		}
		return true;
	}
}
