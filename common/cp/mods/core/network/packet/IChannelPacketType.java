package cp.mods.core.network.packet;


public interface IChannelPacketType
{
    public Class <? extends IPacket > getPacketClass();
    public IPacket create();
    public int ordinal();

    public void setChannel(String channel);
    public String getChannel();
}
