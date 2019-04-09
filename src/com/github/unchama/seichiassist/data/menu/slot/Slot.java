package com.github.unchama.seichiassist.data.menu.slot;

import com.avaje.ebean.validation.NotNull;
import com.github.unchama.seichiassist.data.PlayerData;
import com.github.unchama.seichiassist.data.menu.icon.Icon;
import com.github.unchama.seichiassist.data.menu.icon.SlotIconBuilder;
import com.github.unchama.seichiassist.util.builder.IconBuilder;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * 単純な無機能のスロットを表すinterface.
 * Created by karayuu on 2019/03/29
 */
public interface Slot {
    /**
     * Slotの配置場所を決定します.
     *
     * @param row    行(非負整数)
     * @param column 列(非負整数)
     */
    void setArrangement(int row, int column);

    /**
     * Slotの配置をBukkitのinventoryで使用できる形で返します.
     *
     * @return Slotの配置場所
     */
    int getInventoryNum();

    /**
     * スロットにあるIconを返します.
     *
     * @return セットされているIcon
     */
    @NotNull
    Icon getIcon(PlayerData playerData);

    /**
     * スロットにあるIconのBuilderを返します.
     *
     * @return セットされているiconのbuilder
     */
    @Nonnull
    IconBuilder<? extends Icon> getBuilder();

    /**
     * スロットにアイテムを設置します.
     *
     * @param builder   設置するIconのBuilder ({@code null} は許容されません)
     * @param overwrite true: アイテムを上書きする / false: アイテムを上書きしない.
     */
    void setIcon(IconBuilder<? extends Icon> builder, boolean overwrite);

    /**
     * スロットにあるItemStackを返します.
     *
     * @return セットされているItemStack
     */
    @Nonnull
    ItemStack getItemStack(PlayerData playerData);

    /**
     * Slotに付与されたTriggerで動作を行うか判断し,Actionを実行させます.
     * もし,TriggerやActionが付与されていなかったり,片方が欠けていた場合は何も起こしません.
     *
     * @param event InventoryClickEvent ({@code null} は許容されません.)
     */
    void invoke(@Nonnull InventoryClickEvent event);
}
