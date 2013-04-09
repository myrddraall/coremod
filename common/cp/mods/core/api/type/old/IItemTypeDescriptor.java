package cp.mods.core.api.type.old;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;

public interface IItemTypeDescriptor extends IConfigurableType
{
    Integer getId();

    Item getItem();

    void setItem(Item item);

    ItemStack getItemStack();

    void setItemStack(ItemStack stack);
}
