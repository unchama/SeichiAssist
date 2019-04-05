package com.github.unchama.seichiassist.data.menu.slot.functional;

import com.avaje.ebean.validation.NotNull;
import com.github.unchama.seichiassist.data.menu.icon.SlotIconBuilder;
import com.github.unchama.seichiassist.data.menu.slot.action.SlotHandler;
import com.github.unchama.seichiassist.util.builder.Builder;
import org.bukkit.event.inventory.InventoryClickEvent;

import javax.annotation.Nonnull;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

/**
 * @author karayuu
 */
public class FunctionalSlotBuilder implements Builder<FunctionalSlot> {
    @Nonnull
    private SlotIconBuilder builder;

    @Nonnull
    private Function<InventoryClickEvent, Boolean> trigger;

    @NotNull
    private Consumer<InventoryClickEvent> action;

    private boolean isReadOnly = true;

    private int row = 0;
    private int column = 0;

    private FunctionalSlotBuilder(@Nonnull SlotIconBuilder builder) {
        requireNonNull(builder);
        this.builder = builder;
    }

    /**
     * Functionalスロットを構成するBuilderを作成します.
     *
     * @param builder SlotIconBuilder ({@code null} は許容されません.)
     */
    @Nonnull
    public static FunctionalSlotBuilder of(@Nonnull SlotIconBuilder builder) {
        return new FunctionalSlotBuilder(builder);
    }

    /**
     * triggerをセットします.
     * triggerとはactionを行うかをBoolean型で返すFunctionです.
     *
     * @param trigger トリガー ({@code null} は許容されません.)
     * @return Builder
     * @see #action
     */
    @Nonnull
    public FunctionalSlotBuilder trigger(@Nonnull Function<InventoryClickEvent, Boolean> trigger) {
        requireNonNull(trigger);
        this.trigger = trigger;
        return this;
    }

    /**
     * actionをセットします.
     * actionとはtriggerがtrueを返した際に行わせる動作のことです.
     *
     * @param action アクション ({@code null} は許容されません.)
     * @return Builder
     */
    @Nonnull
    public FunctionalSlotBuilder action(Consumer<InventoryClickEvent> action) {
        requireNonNull(action);
        this.action = action;
        return this;
    }

    /**
     * SlotのReadOnlyを解除します.
     * 通常は使用しないべきです.
     *
     * @return Builder
     * @Deprecated 通常使用するべきではないため.(不具合を生じる恐れがある.)
     */
    @Nonnull
    @Deprecated
    public FunctionalSlotBuilder unlockReadOnly() {
        this.isReadOnly = false;
        return this;
    }

    /**
     * Slotをセットする位置を指定します.
     *
     * @param row    行(非負整数)
     * @param column 列(非負整数)
     * @return Builder
     */
    @Nonnull
    public FunctionalSlotBuilder at(int row, int column) {
        this.row = row;
        this.column = column;
        return this;
    }

    @Nonnull
    @Override
    public FunctionalSlot build() {
        FunctionalSlot slot = FunctionalSlot.of(builder);
        //Require multiple nonNull.
        requireNonNull(trigger);
        requireNonNull(action);

        SlotHandler handler = new SlotHandler(trigger, action);

        slot.addHandler(handler);

        if (isReadOnly) {
            slot.readOnly();
        }

        if (row == 0 && column == 0) {
            throw new IllegalArgumentException("FunctionalSlotBuilderでは#atを利用してSlotの配置位置を指定してください.");
        }

        slot.setArrangement(row, column);

        return slot;
    }
}
