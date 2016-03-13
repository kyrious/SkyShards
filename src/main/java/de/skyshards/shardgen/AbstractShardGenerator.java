package de.skyshards.shardgen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public abstract class AbstractShardGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider) {
	}

	protected abstract void generateBlock(Random random, int blockX, int blockZ, int blockY, World world,
			int minShardHight, int avgShardHight, int maxShardHight);

}
