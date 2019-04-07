package com.github.unchama.seichiassist.data.menu.inventory;

import com.github.unchama.seichiassist.data.menu.inventory.chest.ChestMenu;
import com.github.unchama.seichiassist.data.menu.slot.Slot;
import com.github.unchama.seichiassist.data.menu.slot.functional.FunctionalSlot;
import com.github.unchama.seichiassist.util.builder.SlotBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Menuの根本となるinterface.
 * チェストベースのMenuを作成できます.
 *
 * @author karayuu
 */
public interface Menu<T> extends Listener {
    /**
     * Menuの名前を取得します.
     * あけているMenuの判定に用います.
     *
     * @return Menuの名前
     */
    @Nonnull
    String getTitle();

    /**
     * メニューを開きます.
     *
     * @param player メニューを開くプレイヤー ({@code null} は許容されません.)
     */
    void open(@Nonnull Player player);

    /**
     * メニューにSlotのBuilderを追加します.
     * 同じrow, columnにIconがSlotがあった場合,どちらかが消滅します.
     *
     * @param builder 追加するSlotのBuilder ({@code null} は許容されません.)
     * @return このMenu
     */
    T addSlotBuilder(@Nonnull SlotBuilder<Slot> builder);

    /**
     * メニューにSlotのBuilderを追加します.
     * 同じrow, columnにIconがSlotがあった場合,どちらかが消滅します.
     *
     * @param builders セットするSlotのBuilderのList (各要素全てにおいて {@code null} は許容されません.)
     * @return このMenu
     */
    T addSlotBuilder(@Nonnull List<SlotBuilder<Slot>> builders);

    /**
     * 与えられたInventoryClickEventからスロット番号を取得してtrigger,actionを起こします. <br>
     * もし,そのスロットにTrigger,Actionが付与されていなかったり,どちらかが欠けていた場合は何も起こしません. <br>
     * プレイヤー内のインベントリをクリックした場合も何も起こしません. <br>
     *
     * @param event InventoryClickEvent
     */
    @EventHandler
    void invoke(@Nonnull InventoryClickEvent event);

    /**
     * Menuにおいて,プレイヤーのInventoryのアイテム移動を制限します.
     *
     * @return このMenu
     */
    T restrictPlayerInvItemMoving();
}
