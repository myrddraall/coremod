package cp.mods.TestMod2.api.item;

import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cp.mods.core.api.type.IConfigurableType;
import cpw.mods.fml.common.FMLLog;

public enum SimpleTestItems implements IConfigurableType
{
    TEST_ITEM;

    @Override
    public void config(Configuration config)
    {
        String name = config.get("items.firstTestItem", "name", "asdf").getString();
        FMLLog.log(Level.INFO, "name: %s", name);
    }

   
   
    
    
}
