package cp.mods.CoreMod.core.item.old;

import net.minecraft.item.ItemStack;
import cp.mods.CoreMod.core.api.type.old.item.SubtypedItemType;
import cp.mods.CoreMod.core.item.ItemBase;

public class MultiItemItem extends ItemBase
{
    private SubtypedItemType[] types;
    
    public MultiItemItem(int par1)
    {
        super(par1);
    }

    public SubtypedItemType[] getTypes()
    {
        return types;
    }

    public void setTypes(SubtypedItemType[] types)
    {
        this.types = types;
    }
    
    @Override
    public boolean getHasSubtypes()
    {
        return types != null && types.length > 0;
    }
    
    public SubtypedItemType getType(int typeID){
        return types[typeID];
    }
    public SubtypedItemType getType(ItemStack stack){
        return getType(stack.getItemDamage());
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        Enum<?> e = (Enum<?>)getType(stack);
        return super.getUnlocalizedName(stack) + "." + e.name().toLowerCase();
    }
    
    

}
