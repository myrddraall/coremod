package cp.mods.core.integration.computercraft.peripheral;

import java.util.ArrayList;

import dan200.computer.api.IComputerAccess;
import dan200.computer.api.IPeripheral;

public abstract class PeriperalBase implements IPeripheral
{
    protected ArrayList<String> collectedLuaMethods = new ArrayList<String>();

    public PeriperalBase()
    {
        initLuaMethods();
    }

    @Override
    public String[] getMethodNames()
    {
        String[] methods = new String[collectedLuaMethods.size()];
        collectedLuaMethods.toArray(methods);
        return methods;
    }

    protected void initLuaMethods()
    {
    }

    @Override
    public void attach(IComputerAccess turtle)
    {
        // turtle.mountFixedDir(arg0, arg1, arg2, arg3)
    }

    @Override
    public Object[] callMethod(IComputerAccess arg0, int arg1, Object[] arg2) throws Exception
    {
        return null;
    }

    @Override
    public boolean canAttachToSide(int arg0)
    {
        return false;
    }

    @Override
    public void detach(IComputerAccess arg0)
    {
    }

    @Override
    public String getType()
    {
        return null;
    }
}
