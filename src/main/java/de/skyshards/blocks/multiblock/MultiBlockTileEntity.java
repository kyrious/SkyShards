package de.skyshards.blocks.multiblock;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

public class MultiBlockTileEntity extends TileEntity {

	private static int[][] RELATIVE_POSITIONS = new int[][] { { -1, 0, 0 }, { 1, 0, 0 }, { 0, -1, 0 }, { 0, 1, 0 },
			{ 0, 0, -1 }, { 0, 0, 1 } };

	private boolean isLeader = false;
	private MultiBlockTileEntity leader = null;

	@Override
	public void setPos(BlockPos posIn) {
		super.setPos(posIn);
		searchLeader();
	}

	@Override
	public void invalidate() {
		super.invalidate();
		resetLeader();
		searchLeader();
		for (TileEntity neighbor : getAllRelativeTileEntities()) {
			if (neighbor != null && neighbor instanceof MultiBlockTileEntity) {
				((MultiBlockTileEntity) neighbor).searchLeader();
			}
		}
	}

	public void lockLeaderForUse() {

	}

	protected MultiBlockTileEntity getLeader() {
		if (isLeader) {
			return this;
		} else if (leader != null) {
			return leader;
		} else {
			return null;
		}
	}

	protected boolean hasLeader() {
		return !isLeader && leader == null;
	}

	protected boolean isLeader() {
		return isLeader;
	}

	private void searchLeader() {
		for (TileEntity neighbor : getAllRelativeTileEntities()) {
			if (neighbor != null && neighbor instanceof MultiBlockTileEntity) {
				this.leader = ((MultiBlockTileEntity) neighbor).getLeader();
				if (this.leader != null) {
					return;
				}
			}
		}
		isLeader = true;
	}

	private void resetLeader() {
		if (this.isLeader == false && this.leader == null) {
			return;
		}
		this.isLeader = false;
		this.leader = null;
		for (TileEntity neighbor : getAllRelativeTileEntities()) {
			if (neighbor != null && neighbor instanceof MultiBlockTileEntity) {
				((MultiBlockTileEntity) neighbor).resetLeader();
			}
		}

	}

	protected final List<TileEntity> getAllRelativeTileEntities() {
		LinkedList<TileEntity> neighbors = new LinkedList<TileEntity>();
		for (int[] relativePos : RELATIVE_POSITIONS) {
			TileEntity neighbor = getRelativeTileEntiy(relativePos);
			neighbors.add(neighbor);
		}
		return neighbors;
	}

	protected final TileEntity getRelativeTileEntiy(int[] pos) {
		return getRelativeTileEntiy(pos[0], pos[1], pos[2]);
	}

	protected final TileEntity getRelativeTileEntiy(int x, int y, int z) {
		BlockPos pos = new BlockPos(getPos().getX() + x, getPos().getY() + y, getPos().getZ() + z);
		return this.getWorld().getTileEntity(pos);
	}
}
