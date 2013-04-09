package cp.mods.core.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cp.mods.core.network.packet.IBlockPacket;
import cp.mods.core.network.packet.IPacket;
import cp.mods.core.network.packet.IPacketType;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public final class NetworkManager implements IPacketHandler
{
    private static NetworkManager instance = new NetworkManager();

    private static Map<String, IPacketType[]> channels = new HashMap<String, IPacketType[]>();

    public static void registerChannel(String channel, IPacketType[] packetTypes)
    {
        if (!channels.containsKey(channel))
        {
            for (int i = 0; i < packetTypes.length; i++)
            {
                IPacketType pt = packetTypes[i];
                pt.setChannel(channel);
            }
            NetworkRegistry.instance().registerChannel(instance, channel);
            channels.put(channel, packetTypes);
        }
    }

    private static IPacket createPacket(String channel, int typeIdx) throws InstantiationException, IllegalAccessException
    {
        if (!channels.containsKey(channel))
            return null;

        IPacketType[] types = channels.get(channel);
        IPacketType type = types[typeIdx];
        return type.create();
    }

    private static Packet250CustomPayload buildPacket(IPacket packet)
    {
        byte[] data = null;

        try
        {
            ByteArrayOutputStream bas = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bas);
            out.write(packet.getChannelType().ordinal());
            packet.writePacket(out);
            data = bas.toByteArray();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return new Packet250CustomPayload(packet.getChannelType().getChannel(), data);
    }

    public static void broadcast(IPacket packet)
    {
        Packet250CustomPayload p = buildPacket(packet);
        PacketDispatcher.sendPacketToAllPlayers(p);
    }

    public static void broadcast(IPacket packet, int dimensionId)
    {
        Packet250CustomPayload p = buildPacket(packet);
        PacketDispatcher.sendPacketToAllInDimension(p, dimensionId);
    }

    public static void broadcastAround(IPacket packet, int dimensionId, double x, double y, double z, double range)
    {
        Packet250CustomPayload p = buildPacket(packet);
        PacketDispatcher.sendPacketToAllAround(x, y, z, range, dimensionId, p);
    }

    public static void broadcastAround(IPacket packet, Entity ent)
    {
        broadcastAround(packet, ent, 64);
    }

    public static void broadcastAround(IPacket packet, Entity ent, double range)
    {
        broadcastAround(packet, ent.worldObj.getWorldInfo().getDimension(), ent.posX, ent.posY, ent.posZ, range);
    }

    public static void broadcastAround(IBlockPacket packet, double range)
    {
        broadcastAround(packet, packet.getDimention(), packet.getX(), packet.getY(), packet.getZ(), range);
    }

    public static void broadcastAround(IBlockPacket packet)
    {
        broadcastAround(packet, 64);
    }

    public void send(IPacket packet, Player player)
    {
        Packet250CustomPayload p = buildPacket(packet);
        PacketDispatcher.sendPacketToPlayer(p, player);
    }

    public static void sendToServer(IPacket packet)
    {
        Packet250CustomPayload p = buildPacket(packet);
        PacketDispatcher.sendPacketToServer(p);
    }

    private NetworkManager()
    {
    }

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        String channel = packet.channel;

        if (channels.containsKey(channel))
        {
            ByteArrayInputStream dataStream = new ByteArrayInputStream(packet.data);
            DataInputStream dis = new DataInputStream(dataStream);

            try
            {
                int typeId = dis.read();
                IPacket inst = createPacket(channel, typeId);
                inst.readPacket(dis);
                inst.setManager(manager);
                inst.setPacket(packet);
                inst.setPlayer(player);
                inst.execute();
            } catch (IOException e)
            {
                e.printStackTrace();
            } catch (InstantiationException e)
            {
                e.printStackTrace();
            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
    }

}
