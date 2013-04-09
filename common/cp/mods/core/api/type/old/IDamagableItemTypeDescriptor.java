package cp.mods.core.api.type.old;

public interface IDamagableItemTypeDescriptor extends IItemTypeDescriptor
{
    Enum<? extends IDamagableItemTypeDescriptor> getRelatedType();
}
