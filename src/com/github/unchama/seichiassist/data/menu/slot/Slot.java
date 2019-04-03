package com.github.unchama.seichiassist.data.menu.slot;

import com.avaje.ebean.validation.NotNull;
import com.github.unchama.seichiassist.data.menu.icon.Icon;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * 単純な無機能のスロットを表すinterface.
 * Created by karayuu on 2019/03/29
 */
public interface Slot {
    /**
     * スロットにあるIconを返します.
     *
     * @return 設置されているIcon
     */
    @NotNull
    Icon getIcon();

    /**
     * スロットにアイテムを設置します.
     *
     * @param icon      設置するIcon ({@code null} は許容されません)
     * @param overwrite true: アイテムを上書きする / false: アイテムを上書きしない.
     */
    void setIcon(Icon icon, boolean overwrite);

    /**
     * スロットにあるItemStackを返します.
     *
     * @return セットされているItemStack
     */
    @Nonnull
    ItemStack getItemStack();
}
