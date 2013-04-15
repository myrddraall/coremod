package cp.mods.core.integration.computercraft.peripheral;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import dan200.computer.api.IHostedPeripheral;
import dan200.turtle.api.ITurtleAccess;

public abstract class TurtlePeripheralBase extends PeriperalBase implements IHostedPeripheral
{
    protected ITurtleAccess turtle;

    public TurtlePeripheralBase(ITurtleAccess turtle)
    {
        super();
        this.turtle = turtle;
    }

    protected ItemStack getSlotContents(Integer slot)
    {
        return turtle.getSlotContents(slot);
    }

    @Override
    public void readFromNBT(NBTTagCompound arg0)
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void update()
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void writeToNBT(NBTTagCompound arg0)
    {
        // TODO Auto-generated method stub
    }
}
