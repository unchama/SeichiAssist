package com.github.unchama.seichiassist.test;

import org.bukkit.configuration.file.*;

/**
 * Created by karayuu on 2019/03/01
 */
public class TestConfig {
    public static FileConfiguration conf;

    public static void setConf(FileConfiguration conf) {
        TestConfig.conf = conf;
    }
}
