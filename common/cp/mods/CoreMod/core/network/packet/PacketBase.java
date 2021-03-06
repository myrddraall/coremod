package cp.mods.CoreMod.core.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.Player;

public abstract class PacketBase implements IPacket
{
    private IPacketType type;
    private Player player;
    private Packet250CustomPayload packet;
    private INetworkManager manager;

    @Override
    public void setPlayer(Player player)
    {
        this.player = player;
    }

    @Override
    public Player getPlayer()
    {
        return player;
    }

    @Override
    public void setPacket(Packet250CustomPayload packet)
    {
        this.packet = packet;
    }

    @Override
    public Packet250CustomPayload getPacket()
    {
        return packet;
    }

    @Override
    public void setManager(INetworkManager manager)
    {
        this.manager = manager;
    }

    @Override
    public INetworkManager getManager()
    {
        return manager;
    }

    public void writeExternal(DataOutputStream out) throws IOException
    {
    }

    public void readExternal(DataInputStream in) throws IOException
    {
    }

    @Override
    public void setChannelType(IPacketType type)
    {
        this.type = type;
    }

    @Override
    public IPacketType getChannelType()
    {
        return type;
    }
}
