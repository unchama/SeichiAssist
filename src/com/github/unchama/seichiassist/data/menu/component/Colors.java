package com.github.unchama.seichiassist.data.menu.component;


import org.bukkit.*;

/**
 * Created by karayuu on 2019/01/26
 */
public enum Colors {
    YELLOW(ChatColor.YELLOW),
    BOLD(ChatColor.BOLD),
    UNDERLINE(ChatColor.UNDERLINE)
    ;

    private String text;

    Colors(ChatColor colorCode) {
        text += colorCode;
    }

    public String into(String content) {
        return text + content;
    }

    public Colors also(Colors color) {
        text += color;
        return color;
    }
}
