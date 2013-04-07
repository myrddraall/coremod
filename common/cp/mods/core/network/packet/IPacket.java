package cp.mods.core.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Externalizable;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import cpw.mods.fml.common.network.Player;

public interface IPacket
{
    void setType(IChannelPacketType type);
    IChannelPacketType getType();

    void setChannel(String channel);
    String getChannel();

    void setPlayer(Player player);
    Player getPlayer();

    void setOriginalPacket(Packet250CustomPayload  packet);
    Packet250CustomPayload getOriginalPacket();

    void setManager(INetworkManager manager);
    INetworkManager getManager();

    void execute();

    public void writeExternal(DataOutputStream out) throws IOException;

    public void readExternal(DataInputStream in) throws IOException;
}
