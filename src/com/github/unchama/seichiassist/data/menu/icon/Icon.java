package com.github.unchama.seichiassist.data.menu.icon;

import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

import static java.util.Objects.requireNonNull;

/**
 * メニューのIconとして利用するItemStackのラッパークラス.
 * @author karayuu
 */
public class Icon {
    @Nonnull private ItemStack itemStack;

    private Icon(@Nonnull ItemStack itemStack) {
        requireNonNull(itemStack);
        this.itemStack = itemStack;
    }

    /**
     * ItemStackからiconのインスタンスを生成します.
     *
     * @param itemStack IconのItemStack
     * @return アッパーであるIconクラス
     */
    @Nonnull
    public static Icon of(@Nonnull ItemStack itemStack) {
        requireNonNull(itemStack);
        return new Icon(itemStack);
    }

    /**
     * IconであるItemStackを取得します.
     *
     * @return ItemStack
     */
    @Nonnull
    public ItemStack itemStackValue() {
        return itemStack;
    }
}
