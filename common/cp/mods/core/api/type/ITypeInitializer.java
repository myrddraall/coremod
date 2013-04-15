package cp.mods.core.api.type;

import net.minecraftforge.common.Configuration;

public interface ITypeInitializer
{
   void initialize();
   void initialize(IEnumerableType type);
   void config(Configuration config);
   void config(Configuration config, IEnumerableType type);
}
