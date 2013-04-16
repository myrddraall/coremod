package cp.mods.CoreMod.core.type;

import cp.mods.CoreMod.core.api.type.TypeDescriptor;
import cp.mods.CoreMod.core.config.Config;

public abstract class InitializerBase implements TypeInitializer
{
    protected Class<? extends TypeDescriptor> typeDescriptorClass;
    protected Class<? extends TypeInitializationData> typeDataClass;
    protected Config config;

    public InitializerBase(Class<? extends TypeDescriptor> typeDescriptorClass, Class<? extends TypeInitializationData> typeDataClass)
    {
        this.typeDescriptorClass = typeDescriptorClass;
        this.typeDataClass = typeDataClass;
    }
    protected InitializerBase(){
        
    }

    @Override
    public Class<? extends TypeDescriptor> getTypeDescriptorClass()
    {
        return typeDescriptorClass;
    }

    @Override
    public Class<? extends TypeInitializationData> getTypeDataClass()
    {
        return typeDataClass;
    }

    @Override
    public Config getConfig()
    {
        return config;
    }

    @Override
    public void setConfig(Config config)
    {
        this.config = config;
    }
    protected TypeDescriptor[] getDescriptorTypes(){
        return typeDescriptorClass.getEnumConstants();
    }
    protected TypeInitializationData[] getDataTypes(){
        return typeDataClass.getEnumConstants();
    }
    protected TypeDescriptor getDescriptorType(TypeInitializationData data){
        return getDescriptorTypes()[data.ordinal()];
    }
    protected TypeInitializationData getDataType(TypeDescriptor type){
        return getDataTypes()[type.ordinal()];
    }
}
