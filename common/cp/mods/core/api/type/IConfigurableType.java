package cp.mods.core.api.type;

import net.minecraftforge.common.Configuration;

public interface IConfigurableType extends IEnumerableType
{
    void config(Configuration config);
}
