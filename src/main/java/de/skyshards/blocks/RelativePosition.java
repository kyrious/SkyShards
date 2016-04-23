package de.skyshards.blocks;

import net.minecraft.util.BlockPos;

public class RelativePosition {

	private int x;
	private int y;
	private int z;

	public RelativePosition(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public RelativePosition(BlockPos fromPos, BlockPos toPos) {
		super();
		this.x = toPos.getX() - fromPos.getX();
		this.y = toPos.getY() - fromPos.getY();
		this.z = toPos.getZ() - fromPos.getZ();
	}

	public BlockPos toBlockPos(BlockPos absPos) {
		return new BlockPos(absPos.getX() + this.x, absPos.getY() + this.y, absPos.getZ() + this.z);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
	
	
}
