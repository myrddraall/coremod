package cp.mods.core.network.packet;


public interface ICoordinatePacket extends IPacket
{
    public double getX();

    public double getY();

    public double getZ();

    public void setX(double x);

    public void setY(double y);

    public void setZ(double z);
}
