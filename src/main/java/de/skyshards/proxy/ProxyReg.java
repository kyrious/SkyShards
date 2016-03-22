package de.skyshards.proxy;

import net.minecraftforge.fml.common.SidedProxy;

public class ProxyReg {

	@SidedProxy(clientSide="de.skyshards.proxy.CommonProxy", serverSide="de.skyshards.proxy.CommonProxy")
	private static CommonProxy common;
	
	@SidedProxy(clientSide="de.skyshards.proxy.ClientProxy")
	private static ClientProxy client;
	
	public static CommonProxy getCommonProxy() {
		return common;
	}

	public static ClientProxy getClient() {
		return client;
	}
}
