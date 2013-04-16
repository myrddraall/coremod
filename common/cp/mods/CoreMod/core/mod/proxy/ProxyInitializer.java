package cp.mods.CoreMod.core.mod.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;

public class ProxyInitializer
{

    public static void preInitialize(IModProxy proxy, FMLPreInitializationEvent event)
    {
        proxy.initializeVersion();
        proxy.initializeRegistration();
        proxy.initializeLanguages();
        proxy.loadConfig();
        proxy.initializeConfig();
        proxy.getTypeRegistry().doConfigPase();
        proxy.saveConfig();
    }

    public static void initialize(IModProxy proxy, FMLInitializationEvent event)
    {
        proxy.getTypeRegistry().doInitPase();
    }

    public static void postInitialize(IModProxy proxy, FMLPostInitializationEvent event)
    {
        // TODO Auto-generated method stub

    }

}
