package com.github.unchama.seichiassist.data.menu.slot;

import com.avaje.ebean.validation.NotNull;
import org.bukkit.inventory.ItemStack;

/**
 * 単純な無機能のスロットを表すinterface.
 * Created by karayuu on 2019/03/29
 */
public interface Slot {
    /**
     * スロットにあるItemStackを返します.
     *
     * @return 設置されているItemStack
     */
    @NotNull
    ItemStack getIcon();

    /**
     * スロットにアイテムを設置します.
     *
     * @param icon      設置するItemStack ({@code null} は許容されません)
     * @param overwrite true: アイテムを上書きする / false: アイテムを上書きしない.
     */
    void setIcon(ItemStack icon, boolean overwrite);
}
