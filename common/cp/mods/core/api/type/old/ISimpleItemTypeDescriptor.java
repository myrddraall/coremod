package cp.mods.core.api.type.old;

import net.minecraft.item.Item;

public interface ISimpleItemTypeDescriptor extends IItemTypeDescriptor
{
    public void initialize(Class<? extends Item> item);
}
