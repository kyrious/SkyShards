package de.skyshards.items;

import de.skyshards.shardgen.ShardSpawner;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegistry {

	private static ItemRegistry instance;

	public static ItemRegistry getInstance() {
		if (instance == null)
			instance = new ItemRegistry();
		return instance;
	}

	public final Item SHARD_SPAWNER;

	private ItemRegistry() {
		SHARD_SPAWNER = new ShardSpawner("shardspawner");
	}

	public void registerItems() {
		registerItem(SHARD_SPAWNER);
	}

	private void registerItem(Item i) {
		GameRegistry.registerItem(i, i.getUnlocalizedName());
	}
}
