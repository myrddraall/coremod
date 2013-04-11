package cp.mods.core.mod.proxy;

import net.minecraftforge.common.Configuration;
import cp.mods.core.mod.TypeRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;

public interface IModProxy
{

    void preInitialize(FMLPreInitializationEvent event);

    void initialize(FMLInitializationEvent event);

    void postInitialize(FMLPostInitializationEvent event);

    void initializeVersion();

    void initializeLanguages();

    void initializeRegistration();

    void loadConfig();

    void initializeConfig();

    void saveConfig();
    Side getSide();

    Configuration config();
    
    TypeRegistry getTypeRegistry();
}
