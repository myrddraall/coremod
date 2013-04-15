package cp.mods.core.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.jcraft.jorbis.Block;

import cpw.mods.fml.common.network.Player;

public abstract class BlockPacketBase extends PacketBase implements IBlockPacket
{
    private int x;
    private int y;
    private int z;
    private int dimention;

    @Override
    public int getDimention()
    {
        return dimention;
    }

    @Override
    public void setDimention(int dimention)
    {
       this.dimention = dimention;
    }

    @Override
    public void writeExternal(DataOutputStream out) throws IOException
    {
        out.write(getX());
        out.write(getY());
        out.write(getZ());
        out.write(getDimention());
    }

    @Override
    public void readExternal(DataInputStream in) throws IOException
    {
        setX(in.read());
        setY(in.read());
        setZ(in.read());
        setDimention(in.read());
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getZ()
    {
        return z;
    }

    public void setZ(int z)
    {
        this.z = z;
    }
    

    @Override
    public TileEntity getTileEntity()
    {
        TileEntity ent = null;
        EntityPlayer player = (EntityPlayer)getPlayer();
        World world = player.worldObj;
        if(world.getWorldInfo().getDimension() == getDimention())
            ent = world.getBlockTileEntity(getX(), getY(), getZ());
        return ent;
    }
}
