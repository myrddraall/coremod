package cp.mods.CoreMod.core.mod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.minecraftforge.common.Configuration;
import cp.mods.CoreMod.core.api.type.exception.TypeAlreadyRegisteredException;
import cp.mods.CoreMod.core.api.type.exception.TypeNotRegisteredException;
import cp.mods.CoreMod.core.api.type.old.IEnumerableType;
import cp.mods.CoreMod.core.api.type.old.ITypeInitializer;
import cp.mods.CoreMod.core.network.NetworkManager;
import cp.mods.CoreMod.core.network.packet.IPacketType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public final class TypeRegistry
{
    private Set<Class<? extends IEnumerableType>> registeredTypes = new HashSet<Class<? extends IEnumerableType>>();
    private Map<Class<? extends IEnumerableType>, Class<? extends ITypeInitializer>> registeredTypeInitializers = new HashMap<Class<? extends IEnumerableType>, Class<? extends ITypeInitializer>>();
    private Map<Class<? extends IEnumerableType>, Class<? extends ITypeInitializer>> registeredTypeClientInitializers = new HashMap<Class<? extends IEnumerableType>, Class<? extends ITypeInitializer>>();

    private Map<Class<? extends IPacketType>, String> registeredNetworkChannels = new HashMap<Class<? extends IPacketType>, String>();

    private Class<? extends ITypeInitializer> getInitializer(Class<? extends IEnumerableType> type)
    {
        return registeredTypeInitializers.get(type);
    }

    private void register(Class<? extends IEnumerableType> typeClass)
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
            try
            {
                Class<? extends ITypeInitializer> initClass = getInitializer(typeClass);
                ITypeInitializer init;
                init = initClass.newInstance();
                init.config(config);

                IEnumerableType[] types = (IEnumerableType[]) typeClass.getEnumConstants();
                for (IEnumerableType type : types)
                {
                    init.config(config, type);
                }
            } catch (InstantiationException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
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

                    ITypeInitializer init = initializer.newInstance();
                    init.initialize();

                    IEnumerableType[] typeEnums = typeClass.getEnumConstants();
                    for (int i = 0; i < typeEnums.length; i++)
                    {
                        IEnumerableType type = typeEnums[i];
                        init.initialize(type);
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
