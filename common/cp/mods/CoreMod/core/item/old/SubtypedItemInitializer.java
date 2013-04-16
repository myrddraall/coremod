package cp.mods.CoreMod.core.item.old;

import net.minecraft.item.ItemStack;
import cp.mods.CoreMod.core.api.type.old.IEnumerableType;
import cp.mods.CoreMod.core.api.type.old.ITypeInitializer;
import cp.mods.CoreMod.core.api.type.old.item.SubtypedItemType;
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
