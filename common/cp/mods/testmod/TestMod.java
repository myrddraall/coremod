package cp.mods.testmod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "cpTestMod", name = "Test Mod")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class TestMod {

	@PreInit
	public void preLoad(FMLPreInitializationEvent event) {

	}

	@Init
	public void load(FMLInitializationEvent evt) {

	}

	@PostInit
	public void modsLoaded(FMLPostInitializationEvent evt) {
		
	}

}
