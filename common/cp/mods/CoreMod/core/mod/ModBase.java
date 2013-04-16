package cp.mods.CoreMod.core.mod;

import cp.mods.CoreMod.core.mod.proxy.IModProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


public abstract class ModBase<P extends IModProxy>
{
    public ModBase(){
       
    }
    
    public abstract P getProxy();
    
    public void preInitialize(FMLPreInitializationEvent event){
        getProxy().preInitialize(event);
    }
    public void initialize(FMLInitializationEvent event){
        getProxy().initialize(event);
    }
    public void postInitialize(FMLPostInitializationEvent event){
        getProxy().postInitialize(event);
    }
    
}
