package cp.mods.core.integration.computercraft.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LuaUtil
{
    private LuaUtil()
    {
    }

    public static Integer toInteger(Object value)
    {
        return ((Double) value).intValue();
    }

    public static Double toDouble(Object value)
    {
        return (Double) value;
    }

    public static Long toLong(Object value)
    {
        return ((Double) value).longValue();
    }

    public static String toString(Object value)
    {
        return value.toString();
    }

    public static Integer toSlot(Object value)
    {
        return toInteger(value) - 1;
    }

    public static Map<Integer, Object> toLuaArray(List<?> list)
    {
        HashMap<Integer, Object> array = new HashMap<Integer, Object>();
        int len = list.size();

        for (int i = 0; i < len; i++)
        {
            array.put(i + 1, list.get(i));
        }

        return array;
    }

    public static Map<Integer, Object> toLuaArray(Object[] list)
    {
        HashMap<Integer, Object> array = new HashMap<Integer, Object>();
        int len = list.length;

        for (int i = 0; i < len; i++)
        {
            array.put(i + 1, list[i]);
        }

        return array;
    }
}
