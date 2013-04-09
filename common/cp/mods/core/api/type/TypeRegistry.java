package cp.mods.core.api.type;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.minecraftforge.common.Configuration;
import cp.mods.core.network.NetworkManager;
import cp.mods.core.network.packet.IPacketType;

public final class TypeRegistry
{
    private static Set<Class<? extends IEnumerableType>> registeredTypes = new HashSet<Class<? extends IEnumerableType>>();
    private static Map<Class<? extends IEnumerableType>, Class<? extends ITypeInitializer>> registeredTypeInitializers = new HashMap<Class<? extends IEnumerableType>, Class<? extends ITypeInitializer>>();
    private static Map<Class<? extends IEnumerableType>, Class<? extends ITypeInitializer>> registeredTypeClientInitializers = new HashMap<Class<? extends IEnumerableType>, Class<? extends ITypeInitializer>>();

    private static Map<Class<? extends IPacketType>, String> registeredNetworkChannels = new HashMap<Class<? extends IPacketType>, String>();

    public static boolean register(Class<? extends IEnumerableType> typeClass, Class<? extends ITypeInitializer> commonInit)
    {
        if (register(typeClass))
        {
            registeredTypeInitializers.put(typeClass, commonInit);
            return true;
        }
        return false;
    }

    public static boolean register(Class<? extends IEnumerableType> typeClass, Class<? extends ITypeInitializer> commonInit,
            Class<? extends ITypeInitializer> clientInit)
    {
        if (register(typeClass, commonInit))
        {
            registeredTypeClientInitializers.put(typeClass, clientInit);
            return true;
        }
        return false;
    }

    public static boolean register(Class<? extends IEnumerableType> typeClass)
    {
        return registeredTypes.add(typeClass);
    }

    public static boolean registerNetworkChannel(Class<? extends IPacketType> typeClass, String channel)
    {
        if (register(typeClass))
        {
            registeredNetworkChannels.put(typeClass, channel);
            return true;
        }
        return false;
    }

    public static boolean unregister(Class<? extends IEnumerableType> typeClass)
    {
        return registeredTypes.remove(typeClass);
    }

    public static Set<Class<? extends IEnumerableType>> getRegisteredTypes()
    {
        return registeredTypes;
    }

    public static void doConfigPhase(Configuration config)
    {
        for (Class<? extends IEnumerableType> typeClass : registeredTypes)
        {
            IEnumerableType[] types = (IEnumerableType[]) typeClass.getEnumConstants();
            IEnumerableType firstType = types[0];
            if (firstType instanceof IIndividuallyConfiguredType)
            {
                for (IEnumerableType type : types)
                {
                    IConfigurableType t = (IConfigurableType) type;
                    t.config(config);
                }
            } else if (firstType instanceof IConfigurableType)
            {
                IConfigurableType t = (IConfigurableType) firstType;
                t.config(config);
            }
        }
    }

    public static void doNetworkPhase()
    {
        for (Class<? extends IEnumerableType> typeClass : registeredTypes)
        {
            if (registeredNetworkChannels.containsKey(typeClass))
            {
                IPacketType[] types = (IPacketType[]) typeClass.getEnumConstants();
                String channel = registeredNetworkChannels.get(typeClass);
                NetworkManager.registerChannel(channel, types);
            }
        }
    }

    public static void doInitializationPhase()
    {
        for (Class<? extends IEnumerableType> typeClass : registeredTypes)
        {
            if (registeredTypeInitializers.containsKey(typeClass))
            {
                Class<? extends ITypeInitializer> initializer = registeredTypeInitializers.get(typeClass);
                if (initializer.isEnum())
                {
                    ITypeInitializer[] insts = initializer.getEnumConstants();
                    for (ITypeInitializer inst : insts)
                    {
                        inst.initialize(typeClass);
                    }
                } else
                {
                    try
                    {
                        ITypeInitializer inst = initializer.newInstance();
                        inst.initialize(typeClass);
                    } catch (InstantiationException | IllegalAccessException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void doClientInitializationPhase()
    {
        for (Class<? extends IEnumerableType> typeClass : registeredTypes)
        {
            if (registeredTypeClientInitializers.containsKey(typeClass))
            {
                Class<? extends ITypeInitializer> initializer = registeredTypeClientInitializers.get(typeClass);
                if (initializer.isEnum())
                {
                    ITypeInitializer[] insts = initializer.getEnumConstants();
                    for (ITypeInitializer inst : insts)
                    {
                        inst.initialize(typeClass);
                    }
                } else
                {
                    try
                    {
                        ITypeInitializer inst = initializer.newInstance();
                        inst.initialize(typeClass);
                    } catch (InstantiationException | IllegalAccessException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
