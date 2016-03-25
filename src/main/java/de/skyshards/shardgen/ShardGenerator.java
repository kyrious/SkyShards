package de.skyshards.shardgen;

import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ShardGenerator {

	private int posX;
	private int posY;
	private int posZ;
	private int radius;
	private World world;

	public ShardGenerator(int x, int y, int z, int radius, World world) {
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.radius = radius;
		this.world = world;
	}

	public void floating_rock() {
		double size = 2 * radius + 1;
		System.out.println("SPawning rock");
		double caves, center_falloff, plateau_falloff, density;
		for (int i = 1; i < size - 1; i++) {
			for (int j = 1; j < size - 1; j++) {
				for (int k = 1; k < size - 1; k++) {
					double xf = i / size;
					double yf = j / size;
					double zf = k / size;

					if (yf <= 0.5) {
						plateau_falloff = 1.0;
					} else if (0.5 < yf && yf < 0.7) {
						plateau_falloff = 1.0 - Math.pow((yf - 0.5) * 10/2d,2);
					} else {
						plateau_falloff = 0.0;
					}

					center_falloff = 0.1 / (Math.pow((xf - 0.5) * 1.5, 2) + Math.pow((yf - 1.0) * 0.8, 2)
							+ Math.pow((zf - 0.5) * 1.5, 2));
					// caves = Math.pow(SimplexNoise.simplex_noise(1, xf * 5, yf
					// * 5, zf * 5), 3);
//					 density = (SimplexNoise.simplex_noise(5, xf, yf * 0.5,
//					 zf) * center_falloff * plateau_falloff);
					// density *= Math.pow(Math.pow(SimplexNoise.noise((xf + 1)
					// * 3.0, (yf + 1) * 3.0, (zf + 1) * 3.0) + 0.4, 18),1/10d);
					// if (caves < 0.5) {
					// density = 0;
					// }
					// density = ((Math.pow(xf-0.5, 2)+Math.pow(yf-1,
					// 2)+Math.pow(zf-0.5, 2))*plateau_falloff);
					
					
					density = ((SimplexNoise.simplex_noise(3, xf+7.3*234.5, yf * 0.5+7.3*234.5,
							 zf+7.3*234.5))+1)/2;
					density *= plateau_falloff/ ( 
							(Math.pow((xf - 0.5) * 1.5, 2) + Math.pow((yf - 0.5) * 0.8, 2)
							+ Math.pow((zf - 0.5) * 1.5, 2)) * 5   
							);
					caves = Math.pow(SimplexNoise.simplex_noise(1, xf * 5, yf* 5, zf * 5), 3);
//					if (caves < -0.1) {
//						density = 0;
//					}
//					System.out.println(density);
					if (density > 0.75) {
						world.setBlockState(new BlockPos(posX + i, posY + j, posZ + k),
								GameRegistry.findBlock("minecraft", "stone").getDefaultState(), 2);
					} else {
						world.setBlockState(new BlockPos(posX + i, posY + j, posZ + k),
								GameRegistry.findBlock("minecraft", "air").getDefaultState(), 2);
					}
					// put(x, y, z, density > 3.1 ? 1 : 0);
				}
			}
		}
	}

}
