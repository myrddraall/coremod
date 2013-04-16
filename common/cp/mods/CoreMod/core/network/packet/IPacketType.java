package cp.mods.CoreMod.core.network.packet;

import cp.mods.CoreMod.core.api.type.old.IEnumerableType;

public interface IPacketType extends IEnumerableType
{
    Class <? extends IPacket> getPacketClass();

    IPacket create();

    public void setChannel(String channel);

    public String getChannel();
}
