package cp.mods.core.network.packet;

import cp.mods.core.api.type.IEnumerableType;

public interface IPacketType extends IEnumerableType
{
    Class<IPacket> getPacketClass();

    IPacket create();

    public void setChannel(String channel);

    public String getChannel();
}
