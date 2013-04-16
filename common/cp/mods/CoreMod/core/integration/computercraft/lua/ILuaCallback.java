package cp.mods.CoreMod.core.integration.computercraft.lua;

public interface ILuaCallback
{
    Object[] call(Object[] args);
    String getName();
    boolean async();
}
