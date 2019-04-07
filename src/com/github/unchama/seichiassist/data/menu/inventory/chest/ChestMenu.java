package com.github.unchama.seichiassist.data.menu.inventory.chest;

import com.github.unchama.seichiassist.SeichiAssist;
import com.github.unchama.seichiassist.data.PlayerData;
import com.github.unchama.seichiassist.data.menu.MenuListener;
import com.github.unchama.seichiassist.data.menu.inventory.Menu;
import com.github.unchama.seichiassist.data.menu.slot.Slot;
import com.github.unchama.seichiassist.util.builder.SlotBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Chestベースのメニューを作成する際に使用するクラスです.
 *
 * @author karayuu
 */
public final class ChestMenu implements Menu<ChestMenu> {
    @Nonnull
    private String title = "";

    private int size;

    @Nonnull
    private List<SlotBuilder<Slot>> builders = new ArrayList<>();

    /**
     * MenuのベースとなるInventoryを生成します.
     *
     * @param size Inventoryのサイズ(9の倍数である必要があります.)
     */
    private ChestMenu(int size) {
        if (size % 9 != 0) throw new IllegalArgumentException("ChestMenuのsizeは9の倍数でなければなりません.");
        this.size = size;
        MenuListener.menus.add(this);
    }

    /**
     * MenuのベースとなるInventoryを生成します.
     *
     * @param size Inventoryのサイズ(9の倍数である必要があります.)
     */
    public static ChestMenu fromSize(int size) {
        return new ChestMenu(size);
    }

    /**
     * Menuのtitleを設定します.
     *
     * @param title Menuのtitle (ただし,uniqueである必要があります.重複は動作保証外.) ({@code null} は許容されません.)
     * @return ChestMenu
     */
    @Nonnull
    public ChestMenu title(@Nonnull String title) {
        requireNonNull(title);
        this.title = title;
        return this;
    }

    @Nonnull
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void open(@Nonnull Player player) {
        Inventory inventory = Bukkit.createInventory(null, size, title);
        PlayerData playerData = SeichiAssist.playermap.get(player.getUniqueId());
        builders.forEach(builder -> {
            Slot slot = builder.build();
            inventory.setItem(slot.getInventoryNum(), slot.getItemStack(playerData));
        });
        player.openInventory(inventory);
    }

    @Override
    public ChestMenu addSlotBuilder(@Nonnull SlotBuilder<Slot> builder) {
        requireNonNull(builder);
        builders.add(builder);
        return this;
    }

    @Override
    public ChestMenu addSlotBuilder(@Nonnull List<SlotBuilder<Slot>> slotBuilders) {
        requireNonNull(slotBuilders);
        slotBuilders.forEach(this::addSlotBuilder);
        return this;
    }

    @Override
    @EventHandler
    public void invoke(@Nonnull InventoryClickEvent event) {
        if (event.getClickedInventory() == null || event.getWhoClicked() == null) {
            return;
        }

        //IF clicked slot belongs to the player's inventory, THEN make slot readonly(= can't move item) and return.
        if (event.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
            event.setCancelled(true);
            return;
        }

        //IF non-player clicked inventory, THEN return.
        if (!event.getWhoClicked().getType().equals(EntityType.PLAYER)) {
            return;
        }

        builders.forEach(slotBuilder -> {
            Slot slot = slotBuilder.build();
            if (slot.getInventoryNum() == event.getSlot()) {
                slot.invoke(event);
            }
        });
    }
}
