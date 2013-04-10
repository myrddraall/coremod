package cp.mods.core.lang;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class LanguageLoader {

	public static void load(String modId) {
		String basePath = "/mods/" + modId + "/lang/";
		URL url = LanguageLoader.class.getResource(basePath);
		if (url != null) {
			try {
				LanguageRegistry lr = LanguageRegistry.instance();
				File dir = new File(url.toURI());
				if (dir.isDirectory()) {
					File[] files = dir.listFiles();
					for (int i = 0; i < files.length; i++) {
						File file = files[i];
						String fileName = file.getName();
						String lang = fileName.substring(0, fileName.lastIndexOf('.'));
						boolean isXML = fileName.endsWith(".xml");
						lr.loadLocalization(basePath + fileName, lang, isXML);
					}
				}
			} catch (URISyntaxException e) {
			}
		}

	}
}
