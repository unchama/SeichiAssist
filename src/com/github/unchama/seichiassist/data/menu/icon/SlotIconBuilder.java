package com.github.unchama.seichiassist.data.menu.icon;

import com.github.unchama.seichiassist.data.PlayerData;
import com.github.unchama.seichiassist.data.menu.slot.functional.FunctionalSlotBuilder;
import com.github.unchama.seichiassist.util.builder.IconBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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
    private Material material;
    @Nonnull
    private Function<PlayerData, String> title;
    @Nonnull
    private Function<PlayerData, List<String>> lore;
    private Boolean isEnchanted = false;
    private Boolean showAttribute = false;

    private SlotIconBuilder(@Nonnull Material material) {
        requireNonNull(material);
        this.material = material;
        this.title = playerData -> Bukkit.getItemFactory().getItemMeta(material).getDisplayName();
        this.lore = playerData -> Collections.emptyList();
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

    /**
     * ItemStack(IconBuilder)の表示名を設定します.
     *
     * @param title PlayerDataを受け取り,表示名を返すFunction
     * @return このBuilder
     */
    @Nonnull
    public SlotIconBuilder title(@Nonnull Function<PlayerData, String> title) {
        requireNonNull(title);
        this.title = title;
        return this;
    }

    /**
     * ItemStack(IconBuilder)のloreを設定します.
     *
     * @param lore PlayerDataを受け取り,loreを返すFunction
     * @return このBuilder
     */
    @Nonnull
    public SlotIconBuilder lore(@Nonnull Function<PlayerData, List<String>> lore) {
        requireNonNull(lore);
        this.lore = lore;
        return this;
    }

    /**
     * ItemStack(IconBuilder)にエンチャントを付与します.
     *
     * @return このBuilder
     */
    @Nonnull
    public SlotIconBuilder enchanted() {
        this.isEnchanted = true;
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

    /**
     * SlotIconBuilderからFunctionalSlotBuilderを生成します.
     *
     * @return FunctionalSlotBuilder
     */
    @Nonnull
    public FunctionalSlotBuilder toFunctionalSlotBuilder() {
        return FunctionalSlotBuilder.of(this);
    }

    @Nonnull
    @Override
    public Icon build(PlayerData playerData) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = Bukkit.getItemFactory().getItemMeta(material);
        meta.setDisplayName(title.apply(playerData));
        meta.setLore(lore.apply(playerData));

        if (isEnchanted) {
            meta.addEnchant(Enchantment.DIG_SPEED, 100, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        if (!showAttribute) {
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }

        itemStack.setItemMeta(meta);
        return com.github.unchama.seichiassist.data.menu.icon.Icon.of(itemStack);
    }
}
