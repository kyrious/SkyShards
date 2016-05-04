package de.skyshards.blocks.chippedmining;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BasicChippingMiningBlock extends BlockContainer {

	private ItemStack mineableItemStack;
	private int amountMineableItemStacks;

	public BasicChippingMiningBlock(Material material, ItemStack mineableItemStack, int amountMineableItemStacks) {
		super(material);
		this.mineableItemStack = mineableItemStack;
		this.amountMineableItemStacks = amountMineableItemStacks;
	}
	
	public BasicChippingMiningBlock(ItemStack mineableItemStack, int amountMineableItemStacks) {
		this(Material.rock, mineableItemStack, amountMineableItemStacks);
	}

	@Override
	public String getUnlocalizedName() {
		return "BasicChippingMiningBlock";
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		BasicMiningTileEntity miningTileEntity = new BasicMiningTileEntity();
		miningTileEntity.initMineableItems(mineableItemStack, amountMineableItemStacks);
		return miningTileEntity;
	}

	@Override
	public boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof BasicMiningTileEntity) {
			boolean attemptValidated = ((BasicMiningTileEntity) te).attemptRemoval();
			if (attemptValidated)
				super.removedByPlayer(world, pos, player, willHarvest);
		}
		return false;
	}

}
