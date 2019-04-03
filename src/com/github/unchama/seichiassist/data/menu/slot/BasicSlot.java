package com.github.unchama.seichiassist.data.menu.slot;

import com.github.unchama.seichiassist.data.menu.icon.Icon;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

import static java.util.Objects.requireNonNull;

/**
 * 単純な機能を持たないスロットのクラス.
 * Created by karayuu on 2019/03/29
 */
public class BasicSlot implements Slot {
    /**
     * スロットのIcon ({@code null} は許容されません)
     */
    @Nonnull
    private Icon icon;

    /**
     * Slotを生成します.
     *
     * @param itemStack スロットに表示するItemStack ({@code null} は許容されません)
     */
    private BasicSlot(@Nonnull ItemStack itemStack) {
        requireNonNull(itemStack);
        this.icon = Icon.of(itemStack);
    }

    /**
     * Slotを生成します.
     *
     * @param itemStack スロットに表示するItemStack ({@code null} は許容されません)
     */
    public static BasicSlot of(@Nonnull ItemStack itemStack) {
        requireNonNull(itemStack);
        return new BasicSlot(itemStack);
    }

    @Nonnull
    @Override
    public Icon getIcon() {
        return icon;
    }

    @Nonnull
    @Override
    public ItemStack getItemStack() {
        return icon.getItemStack();
    }

    @Override
    public void setIcon(@Nonnull Icon icon, boolean overwrite) {
        requireNonNull(icon);
        if (overwrite) {
            this.icon = icon;
        }
    }
}
