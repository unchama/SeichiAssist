package com.github.unchama.seichiassist.data.menu.inventory.chest;

import com.github.unchama.seichiassist.SeichiAssist;
import com.github.unchama.seichiassist.data.PlayerData;
import com.github.unchama.seichiassist.data.menu.inventory.Menu;
import com.github.unchama.seichiassist.data.menu.slot.Slot;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Chestベースのメニューを作成する際に使用するクラスです.
 *
 * @author karayuu
 */
public final class ChestMenu implements Menu {
    @Nonnull
    private Inventory inventory;

    @Nonnull
    private List<Slot> slots = Collections.emptyList();

    /**
     * MenuのベースとなるInventoryを生成します.
     *
     * @param size Inventoryのサイズ(9の倍数である必要があります.)
     */
    private ChestMenu(int size) {
        if (size % 9 == 0) throw new IllegalArgumentException("ChestMenuのsizeは9の倍数でなければなりません.");
        inventory = Bukkit.createInventory(null, size);
    }

    /**
     * MenuのベースとなるInventoryを生成します.
     *
     * @param size Inventoryのサイズ(9の倍数である必要があります.)
     */
    public static ChestMenu fromSize(int size) {
        return new ChestMenu(size);
    }

    @Override
    public void open(@Nonnull Player player) {
        PlayerData playerData = SeichiAssist.playermap.get(player.getUniqueId());
        slots.forEach(slot ->
                inventory.setItem(slot.getInventoryNum(), slot.getItemStack(playerData)));
    }

    @Override
    public void setSlot(@Nonnull Slot slot) {
        slots.add(slot);
    }

    @Override
    public void setSlot(@Nonnull List<Slot> slots) {
        slots.forEach(this::setSlot);
    }

    @Override
    public void setSlot(@Nonnull Slot... slots) {
        Arrays.asList(slots).forEach(this::setSlot);
    }

    @Override
    public void invoke(@Nonnull InventoryClickEvent event) {
        slots.forEach(slot -> {
            if (slot.getInventoryNum() == event.getSlot()) {
                slot.invoke(event);
            }
        });
    }
}
