package cp.mods.testmod;

import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cp.mods.core.type.exception.BlockTypeAlreadyInitialized;
import cp.mods.testmod.blocks.LogisticsChestType;
import cp.mods.testmod.mod.CommonProxy;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "cpTestMod", name = "Test Mod")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class TestMod extends cp.mods.core.mod.ModBase {

	// @SidedProxy(clientSide = "",
	// serverSide = "")
	// public static CommonProxy proxy;

	@SidedProxy(clientSide = "cp.mods.testmod.client.mod.ClientProxy", serverSide = "cp.mods.testmod.mod.CommonProxy")
	public static CommonProxy proxy;

	@Instance("cpTestMod")
	public static TestMod instance;

	public static int logisticsChestsBlockId;

	@PreInit
	public void initialize(FMLPreInitializationEvent event) {
		super.initialize(proxy, event);
	}

	@Init
	@Override
	public void load(FMLInitializationEvent event) {
		super.load(event);
	}

	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event) {

	}

	@Override
	protected void initializeFromConfig(Configuration cfg) {
		logisticsChestsBlockId = cfg.getBlock("logisticsChests", 900).getInt(
				900);

	}

	@Override
	protected void registerBlocksAndItems() {
		try {
			LogisticsChestType.initialize(logisticsChestsBlockId);
		} catch (BlockTypeAlreadyInitialized e) {
			FMLLog.log(Level.SEVERE, e, e.getMessage());
		}
	}

}
