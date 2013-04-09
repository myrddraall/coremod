package cp.mods.core.old.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.Player;

public abstract class PacketBase implements IPacket
{
    private IChannelPacketType type;
    private String channel;
    private Player player;
    private Packet250CustomPayload packet;
    private INetworkManager manager;

    @Override
    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    @Override
    public String getChannel()
    {
        return channel;
    }

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
    public void setOriginalPacket(Packet250CustomPayload packet)
    {
        this.packet = packet;
    }

    @Override
    public Packet250CustomPayload getOriginalPacket()
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
    public void setType(IChannelPacketType type)
    {
        this.type = type;
    }

    @Override
    public IChannelPacketType getType()
    {
        return type;
    }
}
