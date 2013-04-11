package cp.mods.core.mod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.minecraftforge.common.Configuration;
import cp.mods.core.api.type.IConfigurableType;
import cp.mods.core.api.type.IEnumerableType;
import cp.mods.core.api.type.IIndividuallyConfiguredType;
import cp.mods.core.api.type.ISubtypeInitializer;
import cp.mods.core.api.type.ITypeInitializer;
import cp.mods.core.api.type.exception.TypeAlreadyRegisteredException;
import cp.mods.core.api.type.exception.TypeNotRegisteredException;
import cp.mods.core.network.NetworkManager;
import cp.mods.core.network.packet.IPacketType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public final class TypeRegistry
{
    private Set<Class<? extends IEnumerableType>> registeredTypes = new HashSet<Class<? extends IEnumerableType>>();
    private Map<Class<? extends IEnumerableType>, Class<? extends ITypeInitializer>> registeredTypeInitializers = new HashMap<Class<? extends IEnumerableType>, Class<? extends ITypeInitializer>>();
    private Map<Class<? extends IEnumerableType>, Class<? extends ITypeInitializer>> registeredTypeClientInitializers = new HashMap<Class<? extends IEnumerableType>, Class<? extends ITypeInitializer>>();

    private Map<Class<? extends IPacketType>, String> registeredNetworkChannels = new HashMap<Class<? extends IPacketType>, String>();

    public void register(Class<? extends IEnumerableType> typeClass)
    {
        if (!registeredTypes.add(typeClass))
            throw new TypeAlreadyRegisteredException();
    }

    public void register(Class<? extends IEnumerableType> typeClass, Class<? extends ITypeInitializer> commonInit)
    {
        register(typeClass);
        registeredTypeInitializers.put(typeClass, commonInit);
    }

    @SideOnly(Side.CLIENT)
    public void registerClientInitializer(Class<? extends IEnumerableType> typeClass, Class<? extends ITypeInitializer> clientInit)
    {
        if (!registeredTypes.contains(typeClass))
            throw new TypeNotRegisteredException();
        registeredTypeClientInitializers.put(typeClass, clientInit);
    }

    public void registerNetworkChannel(Class<? extends IPacketType> typeClass, String channel)
    {
        register(typeClass);
        registeredNetworkChannels.put(typeClass, channel);
    }

    public boolean unregister(Class<? extends IEnumerableType> typeClass)
    {
        return registeredTypes.remove(typeClass);
    }

    public Set<Class<? extends IEnumerableType>> getRegisteredTypes()
    {
        return registeredTypes;
    }

    public void doConfigPhase(Configuration config)
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

    public void doNetworkPhase()
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

    private void doInitializationPhase(Map<Class<? extends IEnumerableType>, Class<? extends ITypeInitializer>> initializers)
    {
        for (Class<? extends IEnumerableType> typeClass : registeredTypes)
        {
            if (initializers.containsKey(typeClass))
            {
                try
                {
                    Class<? extends ITypeInitializer> initializer = initializers.get(typeClass);
                    if (initializer.isEnum())
                    {
                        IEnumerableType[] typeEnums = typeClass.getEnumConstants();
                        ITypeInitializer[] initEnums = initializer.getEnumConstants();
                        if (initEnums.length > 0 && initEnums.length == typeEnums.length)
                        {
                            ITypeInitializer first = initEnums[0];
                            if (first instanceof ISubtypeInitializer)
                            {
                                ISubtypeInitializer f = (ISubtypeInitializer) first;
                                f.initialize(typeClass);

                                for (int i = 0; i < initEnums.length; i++)
                                {
                                    IEnumerableType type = typeEnums[i];
                                    ISubtypeInitializer init = (ISubtypeInitializer) initEnums[i];
                                    init.initialize(type);
                                }
                            }
                        } else
                        {
                            // throw error
                        }
                    } else
                    {
                        ITypeInitializer init = initializer.newInstance();
                        init.initialize(typeClass);
                        if (init instanceof ISubtypeInitializer)
                        {
                            IEnumerableType[] typeEnums = typeClass.getEnumConstants();
                            for (int i = 0; i < typeEnums.length; i++)
                            {
                                IEnumerableType type = typeEnums[i];
                                ((ISubtypeInitializer) init).initialize(type);
                            }
                        }
                    }

                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void doInitializationPhase()
    {
        doInitializationPhase(registeredTypeInitializers);
    }

    @SideOnly(Side.CLIENT)
    public void doClientInitializationPhase()
    {
        doInitializationPhase(registeredTypeClientInitializers);
    }
}
