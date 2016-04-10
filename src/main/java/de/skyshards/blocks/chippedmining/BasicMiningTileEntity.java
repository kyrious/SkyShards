package de.skyshards.blocks.chippedmining;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BasicMiningTileEntity extends TileEntity {

	public static final String tileEntityName = "BasicMiningTileEntity";

	private static final String NBT_TAG_MINEABLE_ITEM_STACK = "BasicMiningTileEntity.NBT_TAG_MINEABLE_ITEM_STACK";
	private static final String NBT_TAG_AMOUNT_VARIABLE = "BasicMiningTileEntity.NBT_TAG_AMOUNT_VARIABLE";
	private static final String NBT_TAG_INITIALIZED = "BasicMiningTileEntity.NBT_TAG_INITIALIZED";

	static {
		GameRegistry.registerTileEntity(BasicMiningTileEntity.class, tileEntityName);
	}

	private boolean initialized = false;

	private int amountOfMineableItemStacks;
	private ItemStack mineableItemStack;

	public BasicMiningTileEntity() {
	}

	public void initMineableItems(ItemStack mineableItemStack, int amountOfMineableItemStacks) {
		if (!this.initialized && mineableItemStack != null) {
			this.initialized = true;

			this.mineableItemStack = mineableItemStack;
			this.amountOfMineableItemStacks = amountOfMineableItemStacks;
		}
	}

	public boolean attemptRemoval() {
		if (!initialized) {
			throw new RuntimeException("BasicMiningTileEntity must be initialized directly after construction.");
		}
		boolean spawnItem = false;
		boolean deleteBlock = false;
		synchronized (this) {
			if (amountOfMineableItemStacks > 0) {
				amountOfMineableItemStacks--;
				spawnItem = true;
				if (amountOfMineableItemStacks <= 0) {
					deleteBlock = true;
				}
			}
		}
		if (spawnItem) {
			Block.spawnAsEntity(this.worldObj, this.pos, this.mineableItemStack.copy());
		}
		return deleteBlock;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		if (initialized) {
			NBTTagCompound stackTag = new NBTTagCompound();
			this.mineableItemStack.writeToNBT(stackTag);
			nbt.setTag(NBT_TAG_MINEABLE_ITEM_STACK, stackTag);
			nbt.setInteger(NBT_TAG_AMOUNT_VARIABLE, this.amountOfMineableItemStacks);
			nbt.setBoolean(NBT_TAG_INITIALIZED, this.initialized);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		this.initialized = nbt.getBoolean(NBT_TAG_INITIALIZED);
		if (this.initialized) {
			NBTTagCompound stackTag = (NBTTagCompound) nbt.getTag(NBT_TAG_MINEABLE_ITEM_STACK);
			this.mineableItemStack = ItemStack.loadItemStackFromNBT(stackTag);
			this.amountOfMineableItemStacks = nbt.getInteger(NBT_TAG_AMOUNT_VARIABLE);
		}
	}
}
