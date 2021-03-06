package cp.mods.CoreMod.core.mod.proxy;

import java.io.File;
import java.util.Properties;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cp.mods.CoreMod.core.lang.LanguageLoader;
import cp.mods.CoreMod.core.mod.ModVersion;
import cp.mods.CoreMod.core.type.TypeRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;

public abstract class ProxyBase implements IModProxy
{

    public static final Side side = FMLCommonHandler.instance().getEffectiveSide();

    
    protected TypeRegistry typeRegistry;
    
    protected Configuration configuration;
    protected ModMetadata modData;
    protected Properties versionProperties;
    protected File configFile;
    

    public Side getSide(){
        return side;
    }
    
    @Override
    public void preInitialize(FMLPreInitializationEvent event)
    {
        typeRegistry = new TypeRegistry(this);
        modData = event.getModMetadata();
        versionProperties = event.getVersionProperties();
        configFile = event.getSuggestedConfigurationFile();
        ProxyInitializer.preInitialize(this, event);
    }

    @Override
    public void initialize(FMLInitializationEvent event)
    {
        ProxyInitializer.initialize(this, event);
    }

    @Override
    public void postInitialize(FMLPostInitializationEvent event)
    {
        ProxyInitializer.postInitialize(this, event);
    }

    public Configuration config()
    {
        return configuration;
    }

    @Override
    public void initializeVersion()
    {
        ModVersion.initialize(modData.modId, versionProperties);
        modData.version = ModVersion.toVersionString(modData.modId);
    }

    @Override
    public void initializeLanguages()
    {
        LanguageLoader.load(modData.modId);
    }

    @Override
    public void loadConfig()
    {
        try
        {
            Configuration cfg = new Configuration(configFile);
            cfg.load();
            configuration = cfg;
        } catch (Exception e)
        {
            FMLLog.log(Level.SEVERE, e,
                    "%s has a problem loading the configuration file at %s",
                    modData.name,
                    configFile);
        }
    }

    @Override
    public void initializeConfig()
    {
        
    }
    @Override
    public void saveConfig()
    {
        configuration.save();
    }
    
    @Override
    public TypeRegistry getTypeRegistry()
    {
        return typeRegistry;
    }

}
