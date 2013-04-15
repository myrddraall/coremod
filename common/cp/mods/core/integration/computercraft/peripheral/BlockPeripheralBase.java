package cp.mods.core.integration.computercraft.peripheral;

import net.minecraft.tileentity.TileEntity;

public abstract class BlockPeripheralBase extends PeriperalBase
{
    protected TileEntity tile;

    public BlockPeripheralBase(TileEntity te)
    {
        super();
        this.tile = te;
    }
}
