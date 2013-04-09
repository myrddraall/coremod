package cp.mods.TestMod2;

import cp.mods.TestMod2.proxy.CommonProxy;
import cp.mods.core.mod.ModBase;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "cpTestMod2", name = "Test Mod 2")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class TestMod2 extends ModBase<CommonProxy>
{
    @SidedProxy(clientSide = "cp.mods.TestMod2.proxy.ClientProxy", serverSide = "cp.mods.TestMod2.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Override
    public CommonProxy getProxy()
    {
        return proxy;
    }

    @Override
    @PreInit
    public void preInitialize(FMLPreInitializationEvent event)
    {
        super.preInitialize(event);
    }

    @Override
    @Init
    public void initialize(FMLInitializationEvent event)
    {
        super.initialize(event);
    }

    @Override
    @PostInit
    public void postInitialize(FMLPostInitializationEvent event)
    {
        super.postInitialize(event);
    }
}
