package de.skyshards.shardgen;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ShardSpawner extends Item{
	
	public ShardSpawner(String unlocalizedName) {
		super();
		this.setUnlocalizedName(unlocalizedName);
	    this.setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		int radius = 30;
		ShardGenerator gen = new ShardGenerator((int)playerIn.posX+radius+5, (int)playerIn.posY-(radius/2), (int)playerIn.posZ-(radius/2), radius, worldIn);
		gen.floating_rock();
        return true;
    }

}
