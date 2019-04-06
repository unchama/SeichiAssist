package com.github.unchama.seichiassist.bungee;

import com.github.unchama.seichiassist.SeichiAssist;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

/**
 * @author karayuu
 */
public class BungeeStreamFactory {
    /**
     * Bungeeのプレイヤーテレポートを行うActionを生成します.
     *
     * @param serverName Bungeeのサーバ名の拡張子 ({@code null} は許容されません.)
     * @return Action
     */
    @Nonnull
    public static Consumer<InventoryClickEvent> makeAction(@Nonnull String serverName) {
        requireNonNull(serverName);

        return e -> {
            ByteArrayDataOutput stream = ByteStreams.newDataOutput();
            stream.writeUTF("Connect");
            stream.writeUTF(serverName);
            ((Player) e.getWhoClicked()).sendPluginMessage(SeichiAssist.plugin, "BungeeCord", stream.toByteArray());
        };
    }
}
