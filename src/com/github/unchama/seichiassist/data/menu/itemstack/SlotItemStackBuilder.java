package com.github.unchama.seichiassist.data.menu.itemstack;

import org.bukkit.Material;

import javax.annotation.Nonnull;

/**
 * ItemStack,特にメニューに使用するスロットのItemStackを生成するBuilderです.
 *
 * Created by karayuu on 2019/03/30
 */
public class SlotItemStackBuilder extends ItemStackBuilder {
    /**
     * ItemStackを生成するBuilderです.
     *
     * @param material ItemStackに設定するMaterial ({@code null} は許容されません)
     */
    public SlotItemStackBuilder(@Nonnull Material material) {
        super(material);
    }
}
