package cp.mods.CoreMod.core.item;

import cp.mods.CoreMod.core.api.type.TypeDescriptor;
import cp.mods.CoreMod.core.api.type.exception.WrongNumberOfTypesException;
import cp.mods.CoreMod.core.api.type.item.ItemTypeDescriptor;
import cp.mods.CoreMod.core.type.TypeInitializationData;
import cp.mods.CoreMod.core.type.item.ItemTypeInitializationData;

public class SharedItemClientInitializer extends ItemClientInitializer
{

    protected ItemBase itemInst = null;

    public SharedItemClientInitializer(Class<? extends ItemTypeDescriptor> typeDescriptorClass, Class<? extends ItemTypeInitializationData> typeDataClass)
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
}
