package cp.mods.core.api.type.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cp.mods.core.api.type.IConfigurableType;

interface IItemType extends IConfigurableType
{
    int getItemId();
    void setItemId(int itemId);
    
    Item getItem();
    void setItem(Item item);
    
    ItemStack getItemStack();
    void setItemStack(ItemStack stack);
}
