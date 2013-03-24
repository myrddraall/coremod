package cp.mods.testmod;

import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cp.mods.core.mod.ModVersion;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "cpTestMod", name = "Test Mod")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class TestMod {

	// @SidedProxy(clientSide = "",
	// serverSide = "")
	// public static CommonProxy proxy;
	@Instance("cpTestMod")
	public static TestMod instance;

	@PreInit
	public void preLoad(FMLPreInitializationEvent event) {
		// Initialize mod version
		ModVersion.initialize(event.getVersionProperties());
		event.getModMetadata().version = ModVersion.toVersionString();

		// Get Configuration data for the mod
		Configuration cfg = new Configuration(
				event.getSuggestedConfigurationFile());
		try {
			cfg.load();
			initializeFromConfig(cfg);
		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, e,
					"%s has a problem loading the configuration file at %s",
					event.getModMetadata().name,
					event.getSuggestedConfigurationFile());
		} finally {
			cfg.save();
		}
	}

	@Init
	public void load(FMLInitializationEvent evt) {
		registerBlocksAndItems();
	}

	@PostInit
	public void modsLoaded(FMLPostInitializationEvent evt) {

	}

	private void initializeFromConfig(Configuration cfg) {

	}
	private void registerBlocksAndItems() {
		
	}

}
