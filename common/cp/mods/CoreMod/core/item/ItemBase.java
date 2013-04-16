package cp.mods.CoreMod.core.item;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ItemBase extends Item
{
    private static final int shiftIndex = 256;
    private String itemName;
    private String resourceName;
    private String fullName;

    protected Set<String> iconNames = new HashSet<String>();
    protected Map<String, Icon> icons = new HashMap<String, Icon>();

    public ItemBase(int par1)
    {
        super(par1 - shiftIndex);
    }
    
    @SideOnly(Side.CLIENT)
    public void addIcon(String name)
    {
        iconNames.add(name);
    }
    @SideOnly(Side.CLIENT)
    public void addIcons(String[] names)
    {
        if(names != null && names.length > 0){
            for(String name : names){
                iconNames.add(name);
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void updateIcons(IconRegister iconRegister)
    {
        Icon defaultIcon = null;
        if (iconNames.size() > 0)
        {
            for (String name : iconNames)
            {
                Icon icon = iconRegister.registerIcon(name);
                if (defaultIcon == null)
                    defaultIcon = icon;
                icons.put(name, icon);
            }
        } else
        {
            defaultIcon = iconRegister.registerIcon(fullName);
        }
        this.iconIndex = defaultIcon;
    }

    public String getName()
    {
        return itemName;
    }

    public void setName(String name)
    {
        String[] parts = name.split(":", 2);
        String n;
        if (parts.length == 1)
        {
            resourceName = null;
            fullName = n = itemName = parts[0];
        } else
        {
            resourceName = parts[0];
            itemName = parts[1];
            n = resourceName + "." + itemName;
            fullName = resourceName + ":" + itemName;
        }
        setUnlocalizedName(n);
    }
}
