package cp.mods.TestMod2.network;

import cp.mods.core.network.packet.IPacket;
import cp.mods.core.network.packet.IPacketType;

public enum ItemUpdateChannel implements IPacketType
{
    TEST_PACKET;

    @Override
    public Class<IPacket> getPacketClass()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IPacket create()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setChannel(String channel)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getChannel()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
