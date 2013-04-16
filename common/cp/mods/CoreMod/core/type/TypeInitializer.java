package cp.mods.CoreMod.core.type;

import cp.mods.CoreMod.core.api.type.TypeDescriptor;
import cp.mods.CoreMod.core.config.Config;

public interface TypeInitializer
{
    Class<? extends TypeDescriptor> getTypeDescriptorClass();

    Class<? extends TypeInitializationData> getTypeDataClass();

    Config getConfig();

    void setConfig(Config config);

    void initialize();

    void config();
}
