package cp.mods.core.mod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.minecraftforge.common.Configuration;
import cp.mods.core.api.type.IConfigurableType;
import cp.mods.core.api.type.IEnumerableType;
import cp.mods.core.api.type.IEnumeratedTypeInitializer;
import cp.mods.core.api.type.IGlobalTypeInitializer;
import cp.mods.core.api.type.IIndividuallyConfiguredType;
import cp.mods.core.api.type.ITypeInitializer;
import cp.mods.core.api.type.exception.TypeAlreadyRegisteredException;
import cp.mods.core.api.type.exception.TypeNotRegisteredException;
import cp.mods.core.network.NetworkManager;
import cp.mods.core.network.packet.IPacketType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public final class TypeRegistry
{
    private static Set<Class<? extends IEnumerableType>> registeredTypes = new HashSet<Class<? extends IEnumerableType>>();
    private static Map<Class<? extends IEnumerableType>, Class<? extends ITypeInitializer>> registeredTypeInitializers = new HashMap<Class<? extends IEnumerableType>, Class<? extends ITypeInitializer>>();
    private static Map<Class<? extends IEnumerableType>, Class<? extends ITypeInitializer>> registeredTypeClientInitializers = new HashMap<Class<? extends IEnumerableType>, Class<? extends ITypeInitializer>>();

    private static Map<Class<? extends IPacketType>, String> registeredNetworkChannels = new HashMap<Class<? extends IPacketType>, String>();

    public static void register(Class<? extends IEnumerableType> typeClass)
    {
        if (!registeredTypes.add(typeClass))
            throw new TypeAlreadyRegisteredException();
    }

    public static void register(Class<? extends IEnumerableType> typeClass, Class<? extends ITypeInitializer> commonInit)
    {
        register(typeClass);
        registeredTypeInitializers.put(typeClass, commonInit);
    }

    @SideOnly(Side.CLIENT)
    public static void registerClientInitializer(Class<? extends IEnumerableType> typeClass, Class<? extends ITypeInitializer> clientInit)
    {
        if (!registeredTypes.contains(typeClass))
            throw new TypeNotRegisteredException();
        registeredTypeClientInitializers.put(typeClass, clientInit);
    }

    public static void registerNetworkChannel(Class<? extends IPacketType> typeClass, String channel)
    {
        register(typeClass);
        registeredNetworkChannels.put(typeClass, channel);
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
                try
                {
                    Class<? extends ITypeInitializer> initializer = registeredTypeInitializers.get(typeClass);
                    if (initializer.isEnum())
                    {
                        IEnumeratedTypeInitializer[] insts = (IEnumeratedTypeInitializer[]) initializer.getEnumConstants();
                        IEnumerableType[] types = (IEnumerableType[]) typeClass.getEnumConstants();
                        for (IEnumeratedTypeInitializer inst : insts)
                        {
                            inst.initialize(types[((Enum<?>) inst).ordinal()]);
                        }
                    } else
                    {
                        IGlobalTypeInitializer inst = (IGlobalTypeInitializer) initializer.newInstance();
                        inst.initialize(typeClass);

                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void doClientInitializationPhase()
    {
        for (Class<? extends IEnumerableType> typeClass : registeredTypes)
        {
            if (registeredTypeClientInitializers.containsKey(typeClass))
            {
                try
                {
                    Class<? extends ITypeInitializer> initializer = registeredTypeClientInitializers.get(typeClass);
                    if (initializer.isEnum())
                    {
                        IEnumeratedTypeInitializer[] insts = (IEnumeratedTypeInitializer[]) initializer.getEnumConstants();
                        IEnumerableType[] types = (IEnumerableType[]) typeClass.getEnumConstants();
                        for (IEnumeratedTypeInitializer inst : insts)
                        {
                            inst.initialize(types[((Enum<?>) inst).ordinal()]);
                        }
                    } else
                    {
                        IGlobalTypeInitializer inst = (IGlobalTypeInitializer) initializer.newInstance();
                        inst.initialize(typeClass);
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
