package cp.mods.core.mod;

import java.util.HashMap;
import java.util.Properties;

public class ModVersion {
	private static HashMap<String, ModVersion> instances = new HashMap<String, ModVersion>();
	public String major;
	public String minor;
	public String revision;
	public String build;
	public String minecraftVersion;

	public static void initialize(String modId, Properties properties) {
		if (modId != null && properties != null) {
			ModVersion v;
			if (!instances.containsKey(modId)) {
				v = new ModVersion();
				instances.put(modId, v);
			} else {
				v = instances.get(modId);
			}

			v.major = properties.getProperty(modId + ".build.major.number");
			v.minor = properties.getProperty(modId + ".build.minor.number");
			v.revision = properties.getProperty(modId + ".build.revision.number");
			v.build = properties.getProperty(modId + ".build.number");
			v.minecraftVersion = properties.getProperty(modId + ".build.mcversion");
		}
	}

	public static String toVersionString(String modId) {
		if (modId == null || !instances.containsKey(modId))
			return "Unknown Version";

		ModVersion v = instances.get(modId);

		return String.format("%s.%s%s build %s", v.major, v.minor, v.revision, v.build);
	}
}
