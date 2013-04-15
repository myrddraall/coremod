package cp.mods.core.item;

import net.minecraft.item.ItemStack;
import cp.mods.core.api.type.IEnumerableType;
import cp.mods.core.api.type.ITypeInitializer;
import cp.mods.core.api.type.item.SubtypedItemType;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class SubtypedItemInitializer implements ITypeInitializer
{
    protected MultiItemItem item;

    @Override
    public void initialize()
    {
        if (item != null)
            GameRegistry.registerItem(item, item.getUnlocalizedName());
    }

    @Override
    public void initialize(IEnumerableType type)
    {
        SubtypedItemType t = (SubtypedItemType) type;
        ItemStack stack = new ItemStack(t.getItem(), 1, t.ordinal());
        t.setItemStack(stack);
    }
}
