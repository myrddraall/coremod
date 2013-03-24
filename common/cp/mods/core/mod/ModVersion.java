package cp.mods.core.mod;

import java.util.Properties;

public class ModVersion {
	private static String major;
	private static String minor;
	private static String revision;
	private static String build;
	private static String minecraftVersion;

	public static void initialize(Properties properties) {
		if (properties != null) {
			major = properties.getProperty("IronChest.build.major.number");
			minor = properties.getProperty("IronChest.build.minor.number");
			revision = properties.getProperty("IronChest.build.revision.number");
			build = properties.getProperty("IronChest.build.number");
			minecraftVersion = properties.getProperty("IronChest.build.mcversion");
		}
	}
	
	public static String toVersionString()
    {
        return String.format("%s.%s%s build %s for Minecraft %s", major, minor, revision, build, minecraftVersion);
    }
}
