package com.github.unchama.seichiassist.data.menu.slot;

import com.github.unchama.seichiassist.data.menu.itemstack.SlotItemStackBuilder;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

import static java.util.Objects.requireNonNull;

/**
 * 単純な機能を持たないスロットのクラス.
 * Created by karayuu on 2019/03/29
 */
public class BasicSlot implements Slot {
    /**
     * スロットのItemStack ({@code null} は許容されません)
     */
    @Nonnull
    private ItemStack icon;

    /**
     * Slotを生成します.
     *
     * @param icon スロットに表示するItemStack ({@code null} は許容されません)
     */
    private BasicSlot(@Nonnull ItemStack icon) {
        requireNonNull(icon);
        this.icon = icon;
    }

    /**
     * Slotを生成します.
     *
     * @param icon スロットに表示するItemStack ({@code null} は許容されません)
     */
    public static BasicSlot of(@Nonnull ItemStack icon) {
        return new BasicSlot(icon);
    }

    @Nonnull
    @Override
    public ItemStack getIcon() {
        return icon;
    }

    @Override
    public void setIcon(@Nonnull ItemStack icon, boolean overwrite) {
        requireNonNull(icon);
        if (overwrite) {
            this.icon = icon;
        }
    }

    public void test() {
        ItemStack sth = SlotItemStackBuilder.of()
    }
}
