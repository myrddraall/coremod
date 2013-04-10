package cp.mods.TestMod2.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cp.mods.TestMod2.api.item.SimpleTestItems;
import cp.mods.core.api.type.IEnumerableType;
import cp.mods.core.api.type.ITypeInitializer;
import cpw.mods.fml.common.registry.GameRegistry;

public class SimpleTestItemsInit implements ITypeInitializer
{
    @Override
    public void initialize(Class<? extends IEnumerableType> typeClass)
    {        Item item;
        ItemStack stack;
        
        item = new SimpleTestItem(SimpleTestItems.TEST_ITEM.getItemId());
        item.setUnlocalizedName("testitem");
        GameRegistry.registerItem(item, item.getUnlocalizedName());
        stack = new ItemStack(item);
        SimpleTestItems.TEST_ITEM.setItem(item);
        SimpleTestItems.TEST_ITEM.setItemStack(stack);
    }

}
