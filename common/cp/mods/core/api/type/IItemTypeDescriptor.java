package cp.mods.core.api.type;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

interface IItemTypeDescriptor
{
    Integer getId();

    Item getItem();

    void setItem(Item item);

    ItemStack getItemStack();

    void setItemStack(ItemStack stack);
}
