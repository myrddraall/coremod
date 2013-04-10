package cp.mods.core.api.type;

public interface IEnumeratedTypeInitializer extends ITypeInitializer
{
    void initialize(IEnumerableType typeClass);
    int ordinal();
}
