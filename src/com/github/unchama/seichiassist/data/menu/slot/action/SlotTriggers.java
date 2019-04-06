package com.github.unchama.seichiassist.data.menu.slot.action;

import org.bukkit.entity.EntityType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.Function;

/**
 * 汎用性のあるTriggerをまとめるクラス.
 *
 * @author karayuu
 */
public class SlotTriggers {
    private SlotTriggers() {

    }

    /**
     * 左クリックかつプレイヤーインベントリのクリックではない時に実行する基本的なActionです.
     */
    public static Function<InventoryClickEvent, Boolean> basicTrigger =
            event -> event.isLeftClick() && !event.getWhoClicked().getType().equals(EntityType.PLAYER);
}
