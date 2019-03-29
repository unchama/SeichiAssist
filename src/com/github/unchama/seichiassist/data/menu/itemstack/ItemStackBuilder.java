package com.github.unchama.seichiassist.data.menu.itemstack;

import com.github.unchama.seichiassist.util.Builder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

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
     * ItemStackを生成するBuilderです.
     *
     * @param material ItemStackに設定するMaterial ({@code null} は許容されません)
     */
    public ItemStackBuilder(@Nonnull Material material) {
        requireNonNull(material);
        this.material = material;
    }

    @Nonnull
    @Override
    public ItemStack build() {
        return null;
    }
}
