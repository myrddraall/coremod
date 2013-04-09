package cp.mods.core.old.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;


public abstract class CoordinatePacketBase extends PacketBase implements ICoordinatePacket
{
    private double x;
    private double y;
    private double z;

    @Override
    public void writeExternal(DataOutputStream out) throws IOException
    {
        out.writeDouble(getX());
        out.writeDouble(getY());
        out.writeDouble(getZ());
    }

    @Override
    public void readExternal(DataInputStream in) throws IOException
    {
        setX(in.readDouble());
        setY(in.readDouble());
        setZ(in.readDouble());
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getZ()
    {
        return z;
    }

    public void setZ(double z)
    {
        this.z = z;
    }
}
