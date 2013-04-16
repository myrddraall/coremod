package cp.mods.CoreMod.core.item;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import cp.mods.CoreMod.core.api.type.exception.WrongNumberOfTypesException;
import cp.mods.CoreMod.core.api.type.item.ItemTypeDescriptor;
import cp.mods.CoreMod.core.type.InitializerBase;
import cp.mods.CoreMod.core.type.item.ItemTypeInitializationData;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemInitailizer extends InitializerBase
{

    protected int[] itemIDs;

    public ItemInitailizer(Class<? extends ItemTypeDescriptor> typeDescriptorClass, Class<? extends ItemTypeInitializationData> typeDataClass)
    {
        super(typeDescriptorClass, typeDataClass);
        if (getDataTypes().length != getDescriptorTypes().length) { throw new WrongNumberOfTypesException(); }
    }

    protected ItemInitailizer()
    {
        super();
    }

    @Override
    public void config()
    {
        ItemTypeDescriptor[] types = (ItemTypeDescriptor[]) getDescriptorTypes();
        itemIDs = new int[types.length];
        for (ItemTypeDescriptor type : types)
        {
            config((ItemTypeInitializationData) getDataType(type));
        }
    }

    protected void config(ItemTypeInitializationData data)
    {
        itemIDs[data.ordinal()] = getConfig().get(Configuration.CATEGORY_ITEM, getItemConfigName(data.getName()), data.getID());
    }

    protected String getItemConfigName(String name)
    {
        String[] parts = name.split(":", 2);
        String n;
        if (parts.length == 1)
            n = parts[0];
        else n = parts[1];
        return n;
    }

    @Override
    public void initialize()
    {
        ItemTypeDescriptor[] types = (ItemTypeDescriptor[]) getDescriptorTypes();
        for (ItemTypeDescriptor type : types)
        {
            initialize(type, (ItemTypeInitializationData) getDataType(type));
        }
    }

    protected ItemBase createItem(ItemTypeInitializationData data)
    {
        ItemBase item = null;
        int id = itemIDs[data.ordinal()];
        Class<? extends ItemBase> itemClass = data.getItem();
        try
        {
            Constructor<? extends ItemBase> ctor = itemClass.getConstructor(int.class);
            item = ctor.newInstance(id);
            item.setName(data.getName());
            GameRegistry.registerItem(item, item.getUnlocalizedName());

        } catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        } catch (SecurityException e)
        {
            e.printStackTrace();
        } catch (InstantiationException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        } catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        return item;
    }

    protected void initialize(ItemTypeDescriptor type, ItemTypeInitializationData data)
    {
        ItemBase item = createItem(data);
        type.setItem(item);
        type.setItemStack(new ItemStack(item, 0, data.getDamage()));
        initialize(item, data);
    }

    protected void initialize(ItemBase item, ItemTypeInitializationData data)
    {
    }

}
