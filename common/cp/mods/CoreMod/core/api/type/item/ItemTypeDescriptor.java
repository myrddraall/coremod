package cp.mods.CoreMod.core.api.type.item;

import net.minecraft.item.ItemStack;
import cp.mods.CoreMod.core.api.type.TypeDescriptor;
import cp.mods.CoreMod.core.item.ItemBase;

public interface ItemTypeDescriptor extends TypeDescriptor
{
    ItemBase item();
    void setItem(ItemBase item);
    
    ItemStack stack();
    void setItemStack(ItemStack stack);
}
