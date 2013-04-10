package cp.mods.TestMod2.proxy;

import cp.mods.TestMod2.api.item.SimpleTestItems;
import cp.mods.TestMod2.item.SimpleTestItemsInit;
import cp.mods.TestMod2.network.ItemUpdateChannel;
import cp.mods.core.mod.TypeRegistry;
import cp.mods.core.mod.proxy.ProxyBase;

public class CommonProxy extends ProxyBase
{

    @Override
    public void initializeRegistration()
    {
        TypeRegistry.register(SimpleTestItems.class, SimpleTestItemsInit.class);
        TypeRegistry.registerNetworkChannel(ItemUpdateChannel.class, "iuchan");
    }

    @Override
    public void initializeConfig()
    {
        config().get("mod.options", "enableSomething", true).getBoolean(true);
    }

}
