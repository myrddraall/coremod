package cp.mods.core.util;

import net.minecraft.item.ItemStack;

public class ItemKey
{
    Integer id;
    Integer damage;
    Integer tag;

    public ItemKey(Integer id, Integer damage, Integer tag)
    {
        this.id = id;
        this.damage = damage;
        this.tag = tag;
    }

    public ItemKey(Integer id, Integer damage)
    {
        this(id, damage, null);
    }

    public ItemKey(Integer id)
    {
        this(id, 0, null);
    }

    public ItemKey(ItemStack stack)
    {
        id = stack.itemID;
        damage = stack.getItemDamage();
        tag = null;
        if (stack.hasTagCompound())
        {
            tag = stack.stackTagCompound.hashCode();
        }
    }

    public ItemKey(String key)
    {
        String[] parts = key.split("\\.", 3);
        id = Integer.valueOf(parts[0]);
        if (parts.length > 1)
            damage = Integer.valueOf(parts[1]);
        if (parts.length > 2)
            tag = Integer.valueOf(parts[2]);
    }

    public boolean equals(ItemKey key)
    {
        if (id != key.id)
            return false;
        if (damage != key.damage)
            return false;
        if (tag != key.tag)
            return false;

        return true;
    }
}
