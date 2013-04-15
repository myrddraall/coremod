package cp.mods.core.api.type.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cp.mods.core.api.type.IEnumerableType;

interface IItemType extends IEnumerableType
{
    int getItemId();
    void setItemId(int itemId);
    
    String getItemName();
    
    Item getItem();
    void setItem(Item item);
    
    ItemStack getItemStack();
    void setItemStack(ItemStack stack);
}
