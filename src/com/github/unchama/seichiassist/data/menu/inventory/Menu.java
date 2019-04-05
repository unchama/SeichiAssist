package com.github.unchama.seichiassist.data.menu.inventory;

import com.github.unchama.seichiassist.data.menu.slot.Slot;
import com.github.unchama.seichiassist.data.menu.slot.functional.FunctionalSlot;
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
public interface Menu extends Listener {
    /**
     * メニューを開きます.
     *
     * @param player メニューを開くプレイヤー ({@code null} は許容されません.)
     */
    void open(@Nonnull Player player);

    /**
     * メニューにSlotをセットします.
     * すでにメニューにSlotがセットされている場合は上書きします.
     *
     * @param slot セットするSlot ({@code null} は許容されません.)
     */
    void setSlot(@Nonnull Slot slot);

    /**
     * メニューにSlotをセットします.
     * すでにメニューにSlotがセットされている場合は上書きします.
     *
     * @param slots セットするFunctionalSlotのList (各要素全てにおいて {@code null} は許容されません.)
     */
    void setSlot(@Nonnull List<Slot> slots);

    /**
     * メニューにSlotをセットします.
     * すでにメニューにSlotがセットされている場合は上書きします.
     *
     * @param slots セットするSlot (各要素全てにおいて {@code null} は許容されません.)
     */
    void setSlot(@Nonnull Slot... slots);

    /**
     * 与えられたInventoryClickEventからスロット番号を取得してtrigger,actionを起こします
     * もし,そのスロットにTrigger,Actionが付与されていなかったり,どちらかが欠けていた場合は何も起こしません.
     *
     * @param event InventoryClickEvent
     */
    @EventHandler
    void invoke(@Nonnull InventoryClickEvent event);
}
