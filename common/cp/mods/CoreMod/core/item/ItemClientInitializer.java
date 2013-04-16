package cp.mods.CoreMod.core.item;

import cp.mods.CoreMod.core.api.type.exception.WrongNumberOfTypesException;
import cp.mods.CoreMod.core.api.type.item.ItemTypeDescriptor;
import cp.mods.CoreMod.core.type.InitializerBase;
import cp.mods.CoreMod.core.type.TypeClientInitializer;
import cp.mods.CoreMod.core.type.item.ItemTypeClientInitailizationData;

public class ItemClientInitializer extends InitializerBase implements TypeClientInitializer
{

    public ItemClientInitializer(Class<? extends ItemTypeDescriptor> typeDescriptorClass, Class<? extends ItemTypeClientInitailizationData> typeDataClass)
    {
        super(typeDescriptorClass, typeDataClass);
        if (getDataTypes().length != getDescriptorTypes().length) { throw new WrongNumberOfTypesException(); }
    }

    protected ItemClientInitializer()
    {
        super();
    }

    @Override
    public void config()
    {
        ItemTypeDescriptor[] types = (ItemTypeDescriptor[]) getDescriptorTypes();
        for (ItemTypeDescriptor type : types)
        {
            config((ItemTypeClientInitailizationData) getDataType(type));
        }
    }
    protected void config(ItemTypeClientInitailizationData data)
    {
    }

    @Override
    public void initialize()
    {
        ItemTypeDescriptor[] types = (ItemTypeDescriptor[]) getDescriptorTypes();
        for (ItemTypeDescriptor type : types)
        {
            initialize(type, (ItemTypeClientInitailizationData) getDataType(type));
        }
    }

    protected void initialize(ItemTypeDescriptor type, ItemTypeClientInitailizationData data)
    {
        type.item().addIcons(data.getIcons());
    }

   

}
