package cp.mods.CoreMod.core.util;

import net.minecraft.item.ItemStack;

public class ItemSearchKey
{
    String id;
    String damage;
    String tag;

    public ItemSearchKey(String id, String damage, String tag)
    {
        this.id = id;
        this.damage = damage;
        this.tag = tag;
    }

    public ItemSearchKey(String id, String damage)
    {
        this(id, damage, null);
    }

    public ItemSearchKey(ItemStack stack)
    {
        id = String.valueOf(stack.itemID);
        damage = String.valueOf(stack.getItemDamage());
        tag = null;
        if (stack.hasTagCompound())
        {
            tag = String.valueOf(stack.stackTagCompound.hashCode());
        }
    }

    public ItemSearchKey(String key)
    {
        String[] parts = key.split("\\.", 3);
        id = parts[0].trim();
        if (parts.length > 1)
            damage = parts[1].trim();
        if (parts.length > 2)
            tag = parts[2].trim();
    }

    public boolean equals(ItemSearchKey key)
    {
        return toString().equals(key.toString());
    }

    public boolean equals(ItemKey key)
    {
        if(!id.equals(String.valueOf(key.id)))
                return false;
        
        if(!damage.equals("*") && !damage.equals(String.valueOf(key.damage)))
            return false;
        
        if(!tag.equals("*") && !tag.equals(String.valueOf(key.tag)))
            return false;
        
        return true;
    }

    @Override
    public String toString()
    {
        return id + "." + (damage == null ? "*" : damage) + "." + (tag == null ? "*" : tag);
    }

}
