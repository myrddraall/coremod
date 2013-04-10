package cp.mods.TestMod2.proxy;


public class ClientProxy extends CommonProxy
{
    @Override
    public void initializeRegistration()
    {
        super.initializeRegistration();
      //  TypeRegistry.registerClientInitializer(SimpleTestItems.class, SimpleTestItemsInit.class);
    }
}
