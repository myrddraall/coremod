package cp.mods.CoreMod.core.type.item;

import cp.mods.CoreMod.core.item.ItemBase;
import cp.mods.CoreMod.core.type.TypeInitializationData;

public interface ItemTypeInitializationData extends TypeInitializationData
{
    int getID();
    int getDamage();
    String getName();
    Class<? extends ItemBase> getItem();
}
