package cp.mods.core.network.packet;

import com.jcraft.jorbis.Block;

import net.minecraft.tileentity.TileEntity;

public interface IBlockPacket extends IPacket
{
    int getDimention();
    void setDimention(int dim);
    
    int getX();

    void setX(int x);

    int getY();

    void setY(int y);

    int getZ();

    void setZ(int z);
    
    Block getBlock();
    TileEntity getTileEntity();
}
