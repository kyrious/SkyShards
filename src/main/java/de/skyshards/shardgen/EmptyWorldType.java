package de.skyshards.shardgen;

import java.util.Collections;
import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;

public class EmptyWorldType extends WorldType {

	public static final String EMPTY_WORLD_TYPE_NAME = "EmptyWorldType";

	public EmptyWorldType() {
		super(EMPTY_WORLD_TYPE_NAME);
	}

	@Override
	public IChunkProvider getChunkGenerator(final World world, String generatorOptions) {
		return new IChunkProvider() {

			@Override
			public boolean unloadQueuedChunks() {
				return false;
			}

			@Override
			public void saveExtraData() {
			}

			@Override
			public boolean saveChunks(boolean p_73151_1_, IProgressUpdate progressCallback) {
				return true;
			}

			@Override
			public void recreateStructures(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {
			}

			@Override
			public Chunk provideChunk(int x, int z) {
				ChunkPrimer chunkprimer = new ChunkPrimer();
				Chunk chunk = new Chunk(world, chunkprimer, x, z);
				chunk.generateSkylightMap();
				return chunk;
			}

			@Override
			public Chunk provideChunk(BlockPos blockPosIn) {
				return this.provideChunk(blockPosIn.getX() >> 4, blockPosIn.getZ() >> 4);
			}

			@Override
			public void populate(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {

			}

			@Override
			public String makeString() {
				return "";
			}

			@Override
			public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position) {
				return null;
			}

			@Override
			public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
				return Collections.emptyList();
			}

			@Override
			public int getLoadedChunkCount() {
				return 0;
			}

			@Override
			public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_,
					int p_177460_4_) {
				return false;
			}

			@Override
			public boolean chunkExists(int x, int z) {
				return true;
			}

			@Override
			public boolean canSave() {
				return true;
			}
		};
	}
}
