package cp.mods.core.api.type;

public interface ITypeInitializer
{
    void initialize(Class<? extends IEnumerableType> typeClass);
}
