package com.github.unchama.seichiassist.data.menu.buttons;

import com.github.unchama.seichiassist.data.menu.component.*;
import org.bukkit.*;

import static com.github.unchama.seichiassist.data.menu.component.Colors.*;

/**
 * Created by karayuu on 2019/01/26
 */
public class MainMenuButtons {
    private MainMenuButtons() {}

    public static ButtonBuilder miningSpeedToggle =
            new ButtonBuilder(Material.DIAMOND_PICKAXE)
                    .name(YELLOW.also(BOLD).also(UNDERLINE).into("採掘効果上昇"));
}
