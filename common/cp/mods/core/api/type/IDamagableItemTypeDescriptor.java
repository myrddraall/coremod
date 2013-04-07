package cp.mods.core.api.type;

public interface IDamagableItemTypeDescriptor extends IItemTypeDescriptor
{
    Enum<? extends IDamagableItemTypeDescriptor> getRelatedType();
}
