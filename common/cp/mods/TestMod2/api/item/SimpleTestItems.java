package cp.mods.TestMod2.api.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import cp.mods.core.api.type.item.SingularItemsType;

public enum SimpleTestItems implements SingularItemsType
{
    TEST_ITEM(9000);

    private int itemId;
    private Item item;
    private ItemStack itemStack;
    
    SimpleTestItems(int defaultID){
        itemId = defaultID;
    }
    
    @Override
    public void config(Configuration config)
    {
        setItemId(config.get("items." +  this.name(), "id", getItemId()).getInt());
    }

    @Override
    public int getItemId()
    {
        return itemId;
    }

    @Override
    public void setItemId(int itemId)
    {
       this.itemId = itemId;
    }

    @Override
    public Item getItem()
    {
        return item;
    }

    @Override
    public void setItem(Item item)
    {
        this.item = item;
    }

    @Override
    public ItemStack getItemStack()
    {
        return itemStack.copy();
    }

    @Override
    public void setItemStack(ItemStack stack)
    {
        this.itemStack = stack;
    }

   
   
    
    
}
