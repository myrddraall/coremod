package cp.mods.CoreMod.core.item;

import net.minecraftforge.common.Configuration;
import cp.mods.CoreMod.core.api.type.TypeDescriptor;
import cp.mods.CoreMod.core.api.type.exception.WrongNumberOfTypesException;
import cp.mods.CoreMod.core.api.type.item.ItemTypeDescriptor;
import cp.mods.CoreMod.core.type.TypeInitializationData;
import cp.mods.CoreMod.core.type.item.ItemTypeInitializationData;

public class SharedItemInitializer extends ItemInitailizer
{

    protected ItemBase itemInst = null;

    public SharedItemInitializer(Class<? extends ItemTypeDescriptor> typeDescriptorClass, Class<? extends ItemTypeInitializationData> typeDataClass)
    {
        super();
        this.typeDescriptorClass = typeDescriptorClass;
        this.typeDataClass = typeDataClass;
        if (getDescriptorTypes().length != getDataTypes().length - 1) { throw new WrongNumberOfTypesException(); }
    }

    @Override
    protected TypeInitializationData getDataType(TypeDescriptor type)
    {
        return getDataTypes()[type.ordinal() + 1];
    }

    @Override
    public void config()
    {
        ItemTypeInitializationData data = (ItemTypeInitializationData) getDataTypes()[0];
        itemIDs = new int[]
            { getConfig().get(Configuration.CATEGORY_ITEM, getItemConfigName(data.getName()), data.getID()) };
    }

    @Override
    protected ItemBase createItem(ItemTypeInitializationData data)
    {
        if (itemInst == null)
        {
            itemInst = super.createItem((ItemTypeInitializationData) getDataTypes()[0]);
        }
        return itemInst;
    }
    
    @Override
    protected void initialize(ItemBase item, ItemTypeInitializationData data)
    {
        item.addIcon(data.getName());
    }

}
