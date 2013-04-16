package cp.mods.CoreMod.core.integration.computercraft.peripheral;

import net.minecraft.tileentity.TileEntity;
import dan200.turtle.api.ITurtleAccess;

public abstract class BlockAndTurtlePeripheralBase extends TurtlePeripheralBase
{
    protected TileEntity tile;
    public BlockAndTurtlePeripheralBase(TileEntity te, ITurtleAccess turtle)
    {
        super(turtle);
        this.tile = te;
    }
}
