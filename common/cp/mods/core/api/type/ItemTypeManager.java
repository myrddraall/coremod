package cp.mods.core.api.type;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public final class ItemTypeManager
{
    
    public static void initializeSubtypedItem(Class<? extends Enum<? extends ISubtypedItemTypeDescriptor>> typeClass, Item item)
    {
        if(typeClass.isEnum()){
            Enum<? extends ISubtypedItemTypeDescriptor>[] list = typeClass.getEnumConstants();
            for(int i = 0; i < list.length; i++){
                initializeItem((IItemTypeDescriptor)list[i], item, i);
            }
        }
    }
    public static void initializeDamagableItem(Class<? extends Enum<? extends IDamagableItemTypeDescriptor>> typeClass, Item fullItem, Item damagedItem)
    {
        if(typeClass.isEnum()){
            Enum<? extends IDamagableItemTypeDescriptor>[] list = typeClass.getEnumConstants();
            
            if(list.length != 2){
                // error
                return;
            }
            
            initializeItem((IItemTypeDescriptor)list[0], fullItem);
            initializeItem((IItemTypeDescriptor)list[1], fullItem);
        }
    }
    public static void initializeSimpleItem(Enum<? extends ISimpleItemTypeDescriptor> typeENum, Item item)
    {
        initializeItem((IItemTypeDescriptor)typeENum, item);
    }

    private static void initializeItem(IItemTypeDescriptor type, Item item, int damage){
        type.setItem(item);
        type.setItemStack(new ItemStack(item, 1, damage));
    }
    private static void initializeItem(IItemTypeDescriptor type, Item item){
        initializeItem(type, item, 0);
    }
}
