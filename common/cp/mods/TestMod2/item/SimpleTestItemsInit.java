package cp.mods.TestMod2.item;

import java.util.logging.Level;

import cp.mods.core.api.type.IEnumerableType;
import cp.mods.core.api.type.ITypeInitializer;
import cpw.mods.fml.common.FMLLog;

public enum SimpleTestItemsInit implements ITypeInitializer
{
    TEST_ITEM;

    @Override
    public void initialize(Class<? extends IEnumerableType> typeClass)
    {
        FMLLog.log("TTTTTTTTTTT", Level.FINE, "initializing %s.%s ...", "SimpleTestItems", this.name());
       
    }

}
