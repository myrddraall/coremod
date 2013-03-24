package cp.mods.core.mod;

import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cp.mods.core.lang.LanguageHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;

public abstract class ModBase {
	public void initialize(FMLPreInitializationEvent event) {
		ModMetadata meta = event.getModMetadata();
		String modId = meta.modId;
		// Initialize Lang Files
		LanguageHandler.load(modId);

		// Initialize mod version
		ModVersion.initialize(modId, event.getVersionProperties());
		meta.version = ModVersion.toVersionString(modId);

		// Get Configuration data for the mod
		Configuration cfg = new Configuration(
				event.getSuggestedConfigurationFile());

		try {
			cfg.load();
			initializeFromConfig(cfg);
		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, e,
					"%s has a problem loading the configuration file at %s",
					meta.name, event.getSuggestedConfigurationFile());
		} finally {
			cfg.save();
		}
	}

	protected abstract void initializeFromConfig(Configuration cfg);
}
