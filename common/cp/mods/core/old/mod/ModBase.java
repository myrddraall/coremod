package cp.mods.core.old.mod;

import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cp.mods.core.lang.LanguageLoader;
import cp.mods.core.mod.ModVersion;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public abstract class ModBase {
	protected CommonProxyBase proxy;

	public void initialize(CommonProxyBase proxy, FMLPreInitializationEvent event) {
		this.proxy = proxy;
		ModMetadata meta = event.getModMetadata();
		String modId = meta.modId;
		// Initialize Lang Files
		LanguageLoader.load(modId);

		// Initialize mod version
		ModVersion.initialize(modId, event.getVersionProperties());
		meta.version = ModVersion.toVersionString(modId);

		// Get Configuration data for the mod
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());

		try {
			cfg.load();
			initializeFromConfig(cfg);
		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, "%s has a problem loading the configuration file at %s", meta.name, event.getSuggestedConfigurationFile());
		} finally {
			cfg.save();
		}
	}

	public void load(FMLInitializationEvent event) {
		registerBlocksAndItems();
		proxy.initializeRenderers();
	}

	protected abstract void initializeFromConfig(Configuration cfg);

	protected abstract void registerBlocksAndItems();
}
