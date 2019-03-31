package com.github.unchama.seichiassist.data.menu.itemstack;

import com.github.unchama.seichiassist.util.Builder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * Created by karayuu on 2019/03/29
 */
public class ItemStackBuilder implements Builder<ItemStack> {
    /**
     * ItemStackのMaterial
     */
    @Nonnull
    private final Material material;

    /**
     * Materialの個数
     */
    private int amount;

    /**
     * Lore
     */
    @Nonnull
    private List<String> lore;

    /**
     * ItemStackを生成するBuilderです.
     *
     * @param material ItemStackに設定するMaterial ({@code null} は許容されません)
     */
    private ItemStackBuilder(@Nonnull Material material) {
        requireNonNull(material);
        this.material = material;
        this.amount = 0;
        this.lore = Collections.emptyList();
    }

    /**
     * ItemStackを生成するBuilderを生成します.
     *
     * @param material ItemStackに設定するMaterial ({@code null} は許容されません)
     * @return Builder
     */
    @Nonnull
    public static ItemStackBuilder of(@Nonnull Material material) {
        return new ItemStackBuilder(material);
    }

    /**
     * ItemStackの個数を指定します.
     *
     * @param amount ItemStackの個数
     * @return このBuilder
     */
    @Nonnull
    public ItemStackBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Loreを指定します.
     *
     * @param lore ItemStackのLore (全ての要素は {@code null} であってはならない.
     * @return このBuilder
     */
    @Nonnull
    public ItemStackBuilder lore(@Nonnull List<String> lore) {
        lore.forEach(Objects::requireNonNull);
        this.lore = lore;
        return this;
    }

    @Nonnull
    @Override
    public ItemStack build() {
        return null;
    }
}
