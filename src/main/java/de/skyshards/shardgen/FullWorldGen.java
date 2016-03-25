package de.skyshards.shardgen;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FullWorldGen implements IWorldGenerator {

	private static final IBlockState OBSIDIAN_BLOCK = GameRegistry.findBlock("minecraft", "obsidian").getDefaultState();

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider) {
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 0; y < 255; y++) {
					BlockPos pos = new BlockPos(chunkX * 16 + x, y, chunkZ * 16 + z);
					if (!world.isAirBlock(pos))
						world.setBlockState(pos, OBSIDIAN_BLOCK, 2);
				}
			}
		}
	}
}
