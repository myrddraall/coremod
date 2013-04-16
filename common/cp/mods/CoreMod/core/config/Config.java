package cp.mods.CoreMod.core.config;

import net.minecraftforge.common.Configuration;

public class Config
{
    private Configuration cfg;

    public Config(Configuration cfg)
    {
        this.cfg = cfg;
    }

    public int get(String category, String key, int defaultValue)
    {
        return cfg.get(category, key, defaultValue).getInt();
    }

    public boolean get(String category, String key, boolean defaultValue)
    {
        return cfg.get(category, key, defaultValue).getBoolean(defaultValue);
    }

    public double get(String category, String key, double defaultValue)
    {
        return cfg.get(category, key, defaultValue).getDouble(defaultValue);
    }

    public String get(String category, String key, String defaultValue)
    {
        return cfg.get(category, key, defaultValue).getString();
    }

    public String[] get(String category, String key, String[] defaultValue)
    {
        return cfg.get(category, key, defaultValue).getStringList();
    }

    public int[] get(String category, String key, int[] defaultValue)
    {
        return cfg.get(category, key, defaultValue).getIntList();
    }

    public double[] get(String category, String key, double[] defaultValue)
    {
        return cfg.get(category, key, defaultValue).getDoubleList();
    }

    public boolean[] get(String category, String key, boolean[] defaultValue)
    {
        return cfg.get(category, key, defaultValue).getBooleanList();
    }

}
