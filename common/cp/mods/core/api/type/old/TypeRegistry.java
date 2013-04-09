package cp.mods.core.api.type.old;

import java.util.HashMap;

public class TypeRegistry
{

    public final static TypeRegistry all = new TypeRegistry();

    public static class Items
    {
        public static TypeRegistry simpleItems = new TypeRegistry();
        public static TypeRegistry damagableItems = new TypeRegistry();
        public static TypeRegistry subtypedItems = new TypeRegistry();
    }

    public static void registerSimpleItem(Class<? extends ISimpleItemTypeDescriptor> type)
    {
        Items.simpleItems.registeredTypes.put(type, type.getEnumConstants());
        all.registeredTypes.put(type, type.getEnumConstants());
    }

    public static void registerDamagableItem(Class<? extends IDamagableItemTypeDescriptor> type)
    {
        Items.damagableItems.registeredTypes.put(type, type.getEnumConstants());
        all.registeredTypes.put(type, type.getEnumConstants());
    }

    public static void registerSubtypedItem(Class<? extends ISubtypedItemTypeDescriptor> type)
    {
        Items.subtypedItems.registeredTypes.put(type, type.getEnumConstants());
        all.registeredTypes.put(type, type.getEnumConstants());
    }

    private HashMap<Class<? extends IConfigurableType>, IConfigurableType[]> registeredTypes = new HashMap<Class<? extends IConfigurableType>, IConfigurableType[]>();

    public HashMap<Class<? extends IConfigurableType>, IConfigurableType[]> getTypes()
    {
        return registeredTypes;
    }

    public IConfigurableType[] getTypes(Class<? extends IConfigurableType> subTypesOf)
    {
        return registeredTypes.get(subTypesOf);
    }
}
