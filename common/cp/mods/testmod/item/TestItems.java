package cp.mods.testmod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import cp.mods.core.api.type.old.ISubtypedItemTypeDescriptor;
import cp.mods.core.api.type.old.ItemTypeManager;

public enum TestItems implements ISubtypedItemTypeDescriptor
{
    FIRSTITEM();

    private static Item _item = null;

    public static void initialize(Item item)
    {
        if (_item == null)
        {
            _item = item;
            ItemTypeManager.initializeSubtypedItem(TestItems.class, item);
        }
    }

    private Item item;
    private ItemStack stack;

    TestItems()
    {

    }

    @Override
    public Integer getId()
    {
        return item != null ? item.itemID : null;
    }

    @Override
    public Item getItem()
    {
        return item;
    }

    @Override
    public ItemStack getItemStack()
    {
        return stack != null ? stack.copy() : null;
    }

    @Override
    public void setItem(Item item)
    {
        this.item = item;
    }

    @Override
    public void setItemStack(ItemStack stack)
    {
        this.stack = stack;
    }

    @Override
    public void config(Configuration config)
    {
        // TODO Auto-generated method stub
        
    }

}
