package com.github.unchama.seichiassist.data.menu.menus;

import com.github.unchama.seichiassist.SeichiAssist;
import com.github.unchama.seichiassist.bungee.BungeeStreamFactory;
import com.github.unchama.seichiassist.data.menu.icon.SlotIconBuilder;
import com.github.unchama.seichiassist.data.menu.inventory.chest.ChestMenu;
import com.github.unchama.seichiassist.data.menu.slot.action.SlotTriggers;
import com.github.unchama.seichiassist.data.menu.slot.functional.FunctionalSlotBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import javax.annotation.Nonnull;
import java.util.Collections;

import static java.util.Objects.requireNonNull;

/**
 * @author karayuu
 */
public class ServerSwitchMenu {
    private ServerSwitchMenu() {
    }

    @Nonnull
    private static FunctionalSlotBuilder s1Builder;

    @Nonnull
    private static FunctionalSlotBuilder s2Builder;

    @Nonnull
    private static FunctionalSlotBuilder s3Builder;

    @Nonnull
    public static ChestMenu menu;

    static {
        //(1)SlotBuilderを作成する.
        s1Builder = createBuilder(Material.DIAMOND_PICKAXE, "アルカディアサーバー", "旧第一サバイバルサーバー", "s1")
                .at(1, 0);
        s2Builder = createBuilder(Material.DIAMOND_SPADE, "エデンサーバー", "旧第二サバイバルサーバー", "s2")
                .at(2, 0);
        s3Builder = createBuilder(Material.DIAMOND_AXE, "ヴァルハラサーバー", "旧第三サバイバルサーバー", "s3")
                .at(3, 0);

        //(2)Menuを作成する.
        menu = ChestMenu.fromSize(2 * 9).addSlotBuilder(s1Builder, s2Builder, s3Builder);

        //(3)SeichiAssistのmenuのListに追加する
        SeichiAssist.menus.add(menu);

        //(4)このMenuを開く動作は
        //menu.open(Player player)
        //これは,開きたい時に実行する(Eventと絡めるのもよし,FunctionalSlotと絡めるのもよし)
    }

    /**
     * Server移動メニューにおけるFunctionalSlotBuilderを生成します.
     * 位置指定は行なっていないので,生成後に位置指定を行うこと.
     *
     * @param iconMaterial     IconとするMaterial ({@code null} は許容されません.)
     * @param serverName       現在のサーバ名(タイトルになります) ({@code null} は許容されません.)
     * @param oldServerName    旧サーバ名
     * @param bungeeServerName Bungeeで使用するサーバの拡張子名 ({@code null} は許容されません.)
     * @return
     */
    public static FunctionalSlotBuilder createBuilder(@Nonnull Material iconMaterial, @Nonnull String serverName,
                                                      String oldServerName, @Nonnull String bungeeServerName) {
        requireNonNull(iconMaterial);
        requireNonNull(serverName);
        requireNonNull(bungeeServerName);

        return SlotIconBuilder.of(iconMaterial)
                .title(d -> ChatColor.BOLD + "" + ChatColor.YELLOW + serverName)
                .lore(d -> Collections.singletonList(ChatColor.GRAY + oldServerName))
                .enchanted()
                .toFunctionalSlotBuilder()
                .trigger(SlotTriggers.basicTrigger)
                .action(BungeeStreamFactory.makeAction(bungeeServerName));
    }
}
