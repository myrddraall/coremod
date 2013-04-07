package cp.mods.core.mod;

import java.lang.reflect.Method;

import cp.mods.core.game.block.TileEntityExtended;
import cp.mods.core.type.BlockType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class CommonProxyBase {
	@SideOnly(Side.CLIENT)
	public void initializeRenderers() {
		BlockType[] bts = BlockType.getBlockTypes();
		for (int i = 0; i < bts.length; i++) {
			BlockType type = bts[i];
			Class<? extends TileEntityExtended>[] teClasses = type.getSubEntities();
			for (int j = 0; j < teClasses.length; j++) {
				Class<? extends TileEntityExtended> teClass = teClasses[j];
				try {
					Method m = teClass.getMethod("initializeRenderer");
					System.out.println(m);
				} catch (NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
