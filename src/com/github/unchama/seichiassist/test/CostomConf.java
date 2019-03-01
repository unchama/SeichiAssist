package com.github.unchama.seichiassist.test;

import com.github.unchama.seichiassist.*;
import org.bukkit.configuration.file.*;

import java.io.*;
import java.nio.charset.*;

/**
 * Created by karayuu on 2019/03/01
 */
public class CostomConf {
    private SeichiAssist pl = SeichiAssist.plugin;
    private FileConfiguration conf;
    private File file;
    private String fileName;

    public CostomConf(String fileName) {
        this.fileName = fileName;
        file = new File(pl.getDataFolder(), fileName);
    }

    public void reloadConfig() {
        conf = YamlConfiguration.loadConfiguration(file);

        InputStream stream = pl.getResource(fileName);
        if (stream == null) {
            return;
        }

        conf.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(stream, StandardCharsets.UTF_8)));
    }

    public FileConfiguration getConfig() {
        if (conf == null) {
            reloadConfig();
        }
        return conf;
    }

    public void saveConfig() {
        if (conf == null) {
            return;
        }

        try {
            getConfig().save(file);
        } catch (IOException e) {
            pl.getLogger().warning("Can't save config (" + fileName + ")");
            e.printStackTrace();
        }
    }
}
