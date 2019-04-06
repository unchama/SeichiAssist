package com.github.unchama.seichiassist.data.menu.slot.functional;

import com.github.unchama.seichiassist.data.menu.icon.Icon;
import com.github.unchama.seichiassist.data.menu.slot.BasicSlot;
import com.github.unchama.seichiassist.data.menu.slot.action.SlotHandler;
import com.github.unchama.seichiassist.util.builder.IconBuilder;
import org.bukkit.event.inventory.InventoryClickEvent;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;


/**
 * 機能をもつSlotを追加します.
 * ボタンに利用することを想定しています.
 * <p>
 * Created by karayuu on 2019/03/30
 */
public final class FunctionalSlot extends BasicSlot {
    @Nonnull
    private List<SlotHandler> handlers = Collections.emptyList();

    private boolean isReadOnly = false;

    /**
     * Slotを生成します.
     *
     * @param builder スロットに表示するIconのBuilder ({@code null} は許容されません)
     */
    private FunctionalSlot(@Nonnull IconBuilder<? extends Icon> builder) {
        super(builder);
    }

    /**
     * Slotを生成します.
     *
     * @param builder スロットに表示するiconのBuilder ({@code null} は許容されません)
     */
    @Nonnull
    public static FunctionalSlot of(@Nonnull IconBuilder<? extends Icon> builder) {
        requireNonNull(builder);
        return new FunctionalSlot(builder);
    }

    /**
     * Slotに付与されたHandlerを返します.
     *
     * @return 付与されたActionのList
     */
    @Nonnull
    public List<SlotHandler> getHandler() {
        return handlers;
    }

    /**
     * SlotにHandlerを付与します.
     *
     * @param action 付与するAction ({@code null} は許容されません.)
     */
    public void addHandler(@Nonnull SlotHandler action) {
        requireNonNull(action);
        this.handlers.add(action);
    }

    /**
     * Slotに付与されたTriggerで動作を行うか判断し,Actionを実行させます.
     *
     * @param event InventoryClickEvent ({@code null} は許容されません.)
     */
    @Override
    public void invoke(@Nonnull InventoryClickEvent event) {
        requireNonNull(event);
        handlers.forEach(action -> action.invoke(event));
    }

    /**
     * スロットをReadOnlyにします.
     * (アイテムを取り出せないようにする.)
     */
    public void readOnly() {
        this.isReadOnly = true;

        Function<InventoryClickEvent, Boolean> trigger = event -> event.getSlot() == this.getInventoryNum();
        Consumer<InventoryClickEvent> action = event -> event.setCancelled(true);
        handlers.add(new SlotHandler(trigger, action));
    }

    /**
     * スロットがReadOnlyかを返します.
     *
     * @return true: ReadOnly(取り出し不可) / false: 取り出し可能
     */
    public boolean isReadOnly() {
        return this.isReadOnly;
    }
}