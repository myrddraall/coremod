package cp.mods.core.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.Player;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

public interface IPacket
{
    IPacketType getChannelType();

    void setChannelType(IPacketType channelType);

    Packet250CustomPayload getPacket();

    void setPacket(Packet250CustomPayload packet);

    void setPlayer(Player player);

    Player getPlayer();

    void setManager(INetworkManager manager);

    INetworkManager getManager();

    void writePacket(DataOutputStream out) throws IOException;

    void readPacket(DataInputStream in) throws IOException;

    void execute();
}
