package de.skyshards.gui.containers;

import de.skyshards.gui.core.AbstractInventoryTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class BasicInventoryContainer extends Container {

	private AbstractInventoryTileEntity te;
	private int tileEntityInventorySize;

	public BasicInventoryContainer(IInventory playerInv, AbstractInventoryTileEntity te, int[] xPos, int[] yPos) {
		if (xPos.length != yPos.length)
			throw new IllegalArgumentException("Both slot-position arrays must have the same length.");

		this.tileEntityInventorySize = xPos.length;
		this.te = te;

		// Player Inventory Slot IDs 0-26
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				int index = 9 + x + y * 9;
				int xPosition = 8 + x * 18;
				int yPosition = 84 + y * 18;
				this.addSlotToContainer(new Slot(playerInv, index, xPosition, yPosition));
			}
		}

		// Player Inventory Slot IDs 27-35
		for (int x = 0; x < 9; x++) {
			int xPosition = 8 + x * 18;
			this.addSlotToContainer(new Slot(playerInv, x, xPosition, 142));
		}

		// Tile Entity Slot IDs 36-x
		for (int i = 0; i < xPos.length; i++) {
			this.addSlotToContainer(new Slot(te, i, xPos[i], yPos[i]));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
		ItemStack previous = null;
		Slot slot = (Slot) this.inventorySlots.get(fromSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack current = slot.getStack();
			previous = current.copy();

			if (fromSlot >= 36) {
				// From TE Inventory to Player Inventory
				if (!this.mergeItemStack(current, 0, 36, true)){
					System.err.println("mergeItemStack(current, 0, 36, false) = false");
					return null;
				}
			} else {
				// From Player Inventory to TE Inventory
				if (!this.mergeItemStack(current, 36, 36 + tileEntityInventorySize - 1, false)) {
					System.err.println("mergeItemStack(current, 36, 36 + tileEntityInventorySize - 1, true) = false");
					return null;
				}
			}

			if (current.stackSize == 0)
				slot.putStack((ItemStack) null);
			else
				slot.onSlotChanged();

			if (current.stackSize == previous.stackSize)
				return null;
			slot.onPickupFromSlot(playerIn, current);
		}
		return previous;
	}

	@Override
	public void putStackInSlot(int slotID, ItemStack stack) {
		super.putStackInSlot(slotID, stack);
	}

	@Override
	public ItemStack slotClick(int slotId, int clickedButton, int mode, EntityPlayer playerIn) {
		return super.slotClick(slotId, clickedButton, mode, playerIn);
	}

	@Override
	protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean useEndIndex) {
		boolean success = false;
		int index = startIndex;

		if (useEndIndex)
			index = endIndex - 1;

		Slot slot;
		ItemStack stackinslot;

		if (stack.isStackable()) {
			while (stack.stackSize > 0 && (!useEndIndex && index < endIndex || useEndIndex && index >= startIndex)) {
				slot = (Slot) this.inventorySlots.get(index);
				stackinslot = slot.getStack();

				if (stackinslot != null && stackinslot.getItem() == stack.getItem()
						&& (!stack.getHasSubtypes() || stack.getMetadata() == stackinslot.getMetadata())
						&& ItemStack.areItemStackTagsEqual(stack, stackinslot)) {
					int l = stackinslot.stackSize + stack.stackSize;
					int maxsize = Math.min(stack.getMaxStackSize(), slot.getItemStackLimit(stack));

					if (l <= maxsize) {
						stack.stackSize = 0;
						stackinslot.stackSize = l;
						slot.onSlotChanged();
						success = true;
					} else if (stackinslot.stackSize < maxsize) {
						stack.stackSize -= stack.getMaxStackSize() - stackinslot.stackSize;
						stackinslot.stackSize = stack.getMaxStackSize();
						slot.onSlotChanged();
						success = true;
					}
				}

				if (useEndIndex) {
					--index;
				} else {
					++index;
				}
			}
		}

		if (stack.stackSize > 0) {
			if (useEndIndex) {
				index = endIndex - 1;
			} else {
				index = startIndex;
			}

			while (!useEndIndex && index < endIndex || useEndIndex && index >= startIndex && stack.stackSize > 0) {
				slot = (Slot) this.inventorySlots.get(index);
				stackinslot = slot.getStack();

				// Forge: Make sure to respect isItemValid in the slot.
				if (stackinslot == null && slot.isItemValid(stack)) {
					if (stack.stackSize < slot.getItemStackLimit(stack)) {
						slot.putStack(stack.copy());
						stack.stackSize = 0;
						success = true;
						break;
					} else {
						ItemStack newstack = stack.copy();
						newstack.stackSize = slot.getItemStackLimit(stack);
						slot.putStack(newstack);
						stack.stackSize -= slot.getItemStackLimit(stack);
						success = true;
					}
				}

				if (useEndIndex) {
					--index;
				} else {
					++index;
				}
			}
		}

		return success;
	}

}
