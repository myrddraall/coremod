package cp.mods.CoreMod.core.type;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cp.mods.CoreMod.core.api.type.TypeDescriptor;
import cp.mods.CoreMod.core.api.type.exception.TypeAlreadyRegisteredException;
import cp.mods.CoreMod.core.config.Config;
import cp.mods.CoreMod.core.mod.proxy.IModProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class TypeRegistry
{
    private IModProxy proxy;
    public TypeRegistry(IModProxy proxy){
        this.proxy = proxy;
    }
    
    private Set<Class<? extends TypeDescriptor>> registeredTypes = new HashSet<Class<? extends TypeDescriptor>>();
    private Map<Class<? extends TypeDescriptor>, TypeInitializer> commonInitailizers = new HashMap<Class<? extends TypeDescriptor>, TypeInitializer>();
    private Map<Class<? extends TypeDescriptor>, TypeClientInitializer> clientInitailizers = new HashMap<Class<? extends TypeDescriptor>, TypeClientInitializer>();
    
    private void register(Class<? extends TypeDescriptor> descClass){
        registeredTypes.add(descClass);
    }
    public void register(TypeInitializer init){
        Class<? extends TypeDescriptor> descClass = init.getTypeDescriptorClass();
        register(descClass);
        if(commonInitailizers.containsKey(descClass))
            throw new TypeAlreadyRegisteredException();
        commonInitailizers.put(descClass, init);
    }
    @SideOnly(Side.CLIENT)
    public void register(TypeClientInitializer init){
        Class<? extends TypeDescriptor> descClass = init.getTypeDescriptorClass();
        register(descClass);
        if(clientInitailizers.containsKey(descClass))
            throw new TypeAlreadyRegisteredException();
        clientInitailizers.put(descClass, init);
    }
    
    private TypeInitializer getInitailizer(Class<? extends TypeDescriptor> descClass){
        return commonInitailizers.get(descClass);
    }
    private TypeClientInitializer getClientInitailizer(Class<? extends TypeDescriptor> descClass){
        return clientInitailizers.get(descClass);
    }
    public void doConfigPase(){
        Config cfg = new Config(proxy.config());
        for(Class<? extends TypeDescriptor> td : registeredTypes){
            TypeInitializer init = getInitailizer(td);
            if(init != null){
                init.setConfig(cfg);
                init.config();
            }
            if(proxy.getSide() == Side.CLIENT){
                TypeClientInitializer initClient = getClientInitailizer(td);
                if(initClient != null){
                    initClient.setConfig(cfg);
                    initClient.config();
                }
            }
        }
    }
    public void doInitPase(){
        for(Class<? extends TypeDescriptor> td : registeredTypes){
            TypeInitializer init = getInitailizer(td);
            if(init != null){
                init.initialize();
            }
            if(proxy.getSide() == Side.CLIENT){
                TypeClientInitializer initClient = getClientInitailizer(td);
                if(initClient != null){
                    initClient.initialize();
                }
            }
        }
    }
    public void doCleanupPase(){
        for(Class<? extends TypeDescriptor> td : registeredTypes){
            TypeInitializer init = getInitailizer(td);
            if(init != null){
               // init.initialize();
            }
            if(proxy.getSide() == Side.CLIENT){
                TypeClientInitializer initClient = getClientInitailizer(td);
                if(initClient != null){
                //    initClient.initialize();
                }
            }
        }
    }
    
//    private Set<Class<? extends TypeDescriptor>> registeredTypes = new HashSet<Class<? extends TypeDescriptor>>();
//    private Map<Class<? extends TypeDescriptor>,  Class<? extends TypeInitializer>> initializers = new HashMap<Class<? extends TypeDescriptor>,  Class<? extends TypeInitializer>>();
//    private Map<Class<? extends TypeDescriptor>,  Class<? extends TypeClientInitializer>> initializersClient = new HashMap<Class<? extends TypeDescriptor>,  Class<? extends TypeClientInitializer>>();
//    public void registerType(Class<? extends TypeDescriptor> type){
//        if(registeredTypes.contains(type))
//            throw new TypeAlreadyRegisteredException();
//        registeredTypes.add(type);
//    }
//    public void registerType(Class<? extends TypeDescriptor> type, Class<? extends TypeInitializer> init){
//        registerType(type);
//        if(initializers.containsKey(type))
//            throw new TypeAlreadyRegisteredException();
//        initializers.put(type, init);
//    }
//    @SideOnly(Side.CLIENT)
//    public void registerTypeClient(Class<? extends TypeDescriptor> type, Class<? extends TypeClientInitializer> init){
//        try{
//            registerType(type);
//        }catch(TypeAlreadyRegisteredException e){}
//        if(initializersClient.containsKey(type))
//            throw new TypeAlreadyRegisteredException();
//        initializersClient.put(type, init);
//    }
}
