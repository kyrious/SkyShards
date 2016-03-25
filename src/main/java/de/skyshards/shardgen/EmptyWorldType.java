package de.skyshards.shardgen;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;

public class EmptyWorldType extends WorldType {

	public static final String EMPTY_WORLD_TYPE_NAME="EmptyWorldType";
	
	public EmptyWorldType() {
		super(EMPTY_WORLD_TYPE_NAME);
	}

	@Override
	public IChunkProvider getChunkGenerator(World world, String generatorOptions) {
		// TODO Auto-generated method stub
		return new ChunkProviderGenerate(world, world.getSeed(), false, generatorOptions);
    }
	
}
