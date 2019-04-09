package com.github.unchama.seichiassist.data.menu.icon;

import com.github.unchama.seichiassist.data.PlayerData;
import com.github.unchama.seichiassist.data.menu.icon.component.BaseIconComponent;
import com.github.unchama.seichiassist.data.menu.slot.functional.FunctionalSlotBuilder;
import com.github.unchama.seichiassist.util.builder.IconBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

/**
 * ItemStack,特にメニューに使用するスロットのIconを生成するBuilderです.
 * <p>
 * Created by karayuu on 2019/03/30
 */
public class SlotIconBuilder implements IconBuilder<Icon> {
    @Nonnull
    private final BaseIconComponent component;
    private Boolean showAttribute = false;

    protected SlotIconBuilder(@Nonnull Material material) {
        requireNonNull(material);
        this.component = new BaseIconComponent(material);
    }

    /**
     * Iconを生成するBuilderを生成します.
     *
     * @param material ItemStackに設定するMaterial ({@code null} は許容されません)
     */
    @Nonnull
    public static SlotIconBuilder of(@Nonnull Material material) {
        requireNonNull(material);
        return new SlotIconBuilder(material);
    }

    @Override
    @Nonnull
    public SlotIconBuilder title(@Nonnull Function<PlayerData, String> title) {
        requireNonNull(title);
        this.component.setTitle(title);
        return this;
    }

    @Override
    @Nonnull
    public SlotIconBuilder lore(@Nonnull Function<PlayerData, List<String>> lore) {
        requireNonNull(lore);
        this.component.setLore(lore);
        return this;
    }

    @Override
    @Nonnull
    public SlotIconBuilder enchanted() {
        this.component.setEnchanted(true);
        return this;
    }

    @Override
    @Nonnull
    public SlotIconBuilder number(int number) {
        this.component.setNumber(number);
        return this;
    }

    /**
     * ItemStack(IconBuilder)の各種情報を表示させます.(シャベルの採掘速度等)
     *
     * @return このBuilder
     */
    @Nonnull
    public SlotIconBuilder showAttribute() {
        this.showAttribute = true;
        return this;
    }

    @Override
    @Nonnull
    public FunctionalSlotBuilder toFunctionalSlotBuilder() {
        return FunctionalSlotBuilder.of(this);
    }

    @Override
    @Nonnull
    public Icon build(@Nonnull PlayerData playerData) {
        requireNonNull(playerData);

        ItemStack itemStack = component.getItemStack();
        ItemMeta meta = component.getItemMeta(playerData);

        if (!showAttribute) {
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }

        itemStack.setItemMeta(meta);
        return Icon.of(itemStack);
    }
}
