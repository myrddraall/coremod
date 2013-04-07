package cp.mods.core.network.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteOrder;
import java.util.HashMap;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketHandler implements IPacketHandler
{
    private static HashMap<String, IChannelPacketType[]> channelTypes = new HashMap<String, IChannelPacketType[]>();
    private static PacketHandler inst = new PacketHandler();

    private static PacketHandler instance()
    {
        return inst;
    }

    public static boolean registerChannelTypes(String channel, Class <? extends Enum <? extends IChannelPacketType >> packetTypes)
    {
        IChannelPacketType[] types = (IChannelPacketType[]) packetTypes.getEnumConstants();
        return registerChannelTypes(channel, types);
    }

    public static boolean registerChannelTypes(String channel, IChannelPacketType[] packetTypes)
    {
        if (!channelTypes.containsKey(channel))
        {
            for (int i = 0; i < packetTypes.length; i++)
            {
                IChannelPacketType pt = packetTypes[i];
                pt.setChannel(channel);
            }

            channelTypes.put(channel, packetTypes);
            NetworkRegistry.instance().registerChannel(instance(), channel);
            return true;
        }

        return false;
    }

    private static IPacket createPacket(String channel, int typeIdx) throws InstantiationException, IllegalAccessException
    {
        IChannelPacketType[] types = channelTypes.get(channel);
        IChannelPacketType type = types[typeIdx];
        return createPacket(type);
    }

    public static IPacket createPacket(IChannelPacketType type) throws InstantiationException, IllegalAccessException
    {
        return type.create();
    }

    private static Packet250CustomPayload buildPacket(IPacket packet)
    {
        byte[] data = null;

        try
        {
            ByteArrayOutputStream bas = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bas);
            out.write(packet.getType().ordinal());
            packet.writeExternal(out);
            data = bas.toByteArray();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return new Packet250CustomPayload(packet.getChannel(), data);
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

    public static void broadcast(IPacket packet, int dimensionId, double x, double y, double z, double range)
    {
        Packet250CustomPayload p = buildPacket(packet);
        PacketDispatcher.sendPacketToAllAround(x, y, z, range, dimensionId, p);
    }

    public static void broadcastAround(ICoordinatePacket packet, int dimensionId, double range)
    {
        broadcast(packet, dimensionId, packet.getX(), packet.getY(), packet.getZ(), range);
    }

    public static void broadcastAround(ICoordinatePacket packet, int dimensionId)
    {
        broadcastAround(packet, dimensionId, 64);
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

    private PacketHandler()
    {
    }

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        String channel = packet.channel;

        if (channelTypes.containsKey(channel))
        {
            // IChannelPacketType[] types = channelTypes.get(channel);
            ByteArrayInputStream dataStream = new ByteArrayInputStream(packet.data);
            DataInputStream dis = new DataInputStream(dataStream);

            try
            {
                int typeId = dis.read();
                IPacket inst = createPacket(channel, typeId);
                inst.readExternal(dis);
                inst.setManager(manager);
                inst.setOriginalPacket(packet);
                inst.setPlayer(player);
                inst.execute();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (InstantiationException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
    }
}
