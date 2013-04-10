package cp.mods.core.api.type;

public interface IGlobalTypeInitializer extends ITypeInitializer
{
    void initialize(Class<? extends IEnumerableType> typeClass);
}
