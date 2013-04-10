package cp.mods.TestMod2.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import cp.mods.core.item.ItemBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SimpleTestItem extends ItemBase
{

    public SimpleTestItem(int par1)
    {
        super(par1);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }
}
