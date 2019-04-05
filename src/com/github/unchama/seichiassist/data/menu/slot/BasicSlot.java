package com.github.unchama.seichiassist.data.menu.slot;

import com.avaje.ebean.validation.NotNull;
import com.github.unchama.seichiassist.data.PlayerData;
import com.github.unchama.seichiassist.data.menu.icon.Icon;
import com.github.unchama.seichiassist.data.menu.icon.SlotIconBuilder;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.persistence.Version;

import static java.util.Objects.requireNonNull;

/**
 * 単純な機能を持たないスロットのクラス.
 * Created by karayuu on 2019/03/29
 */
public class BasicSlot implements Slot {
    /**
     * スロットのメニューでの列番号
     */
    private int row = 0;
    /**
     * スロットのメニューでの行番号
     */
    private int column = 0;
    /**
     * スロットのIconのBuilder ({@code null} は許容されません)
     */
    @Nonnull
    private SlotIconBuilder builder;

    /**
     * Slotを生成します.
     *
     * @param builder スロットに表示するiconのbuilder ({@code null} は許容されません)
     */
    public BasicSlot(@Nonnull SlotIconBuilder builder) {
        requireNonNull(builder);
        this.builder = builder;
    }

    /**
     * Slotを生成します.
     *
     * @param builder スロットに表示するIconのbuilder ({@code null} は許容されません)
     */
    public static BasicSlot of(@Nonnull SlotIconBuilder builder) {
        requireNonNull(builder);
        return new BasicSlot(builder);
    }

    @Override
    public void setArrangement(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public int getInventoryNum() {
        //the size of chest type inventory must be 9.
        final int WIDTH = 9;
        return (column - 1) * WIDTH + row - 1;
    }

    @Nonnull
    @Override
    public Icon getIcon(PlayerData playerData) {
        return builder.build(playerData);
    }

    @Nonnull
    @Override
    public SlotIconBuilder getBuilder() {
        return this.builder;
    }

    @Nonnull
    @Override
    public ItemStack getItemStack(PlayerData playerData) {
        return getIcon(playerData).itemStackValue();
    }

    @Override
    public void setIcon(@Nonnull SlotIconBuilder builder, boolean overwrite) {
        requireNonNull(builder);
        if (overwrite) {
            this.builder = builder;
        }
    }

    @Override
    public void invoke(@Nonnull InventoryClickEvent event) {
        //Do nothing.
    }
}
