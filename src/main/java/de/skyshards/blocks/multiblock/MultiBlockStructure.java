package de.skyshards.blocks.multiblock;

import de.skyshards.blocks.RelativePosition;
import net.minecraft.block.state.BlockState;
import net.minecraft.util.BlockPos;

public abstract class MultiBlockStructure {

	private BlockPos startingPositon;
	
	public MultiBlockStructure(BlockPos startingPositon) {
		this.startingPositon = startingPositon;
	}
	
	public final BlockPos getStartingPositon() {
		return startingPositon;
	}

	public abstract boolean isBlockInStructure(RelativePosition pos, BlockState block);
}
