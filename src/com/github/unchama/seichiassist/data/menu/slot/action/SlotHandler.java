package com.github.unchama.seichiassist.data.menu.slot.action;

import org.bukkit.event.inventory.InventoryClickEvent;

import javax.annotation.Nonnull;

import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

/**
 * Slotのアクションを管理するクラスです.
 * <p>
 * @author karayuu
 */
public class SlotHandler {
    @Nonnull
    private Function<InventoryClickEvent, Boolean> trigger;
    @Nonnull
    private Consumer<InventoryClickEvent> action;

    public SlotHandler(@Nonnull Function<InventoryClickEvent, Boolean> trigger, @Nonnull Consumer<InventoryClickEvent> action) {
        requireNonNull(trigger);
        requireNonNull(action);

        this.trigger = trigger;
        this.action = action;
    }

    /**
     * Triggerを満たすか判断し,満たす場合は動作を行わせます.
     *
     * @param event InventoryClickEvent
     */
    public void invoke(InventoryClickEvent event) {
        if (trigger.apply(event)) {
            action.accept(event);
        }
    }
}
