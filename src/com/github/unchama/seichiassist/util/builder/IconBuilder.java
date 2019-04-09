package com.github.unchama.seichiassist.util.builder;

import com.github.unchama.seichiassist.data.PlayerData;
import com.github.unchama.seichiassist.data.menu.icon.Icon;
import com.github.unchama.seichiassist.data.menu.icon.SlotIconBuilder;
import com.github.unchama.seichiassist.data.menu.slot.functional.FunctionalSlotBuilder;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Function;

/**
 * プレイヤーデータを用いてIconをBuildするBuilderを表すインターフェース.
 *
 * @param <T> Buildする際に生成するインスタンスの型
 */
public interface IconBuilder<T extends Icon> extends Builder {
    /**
     * ItemStack(IconBuilder)の表示名を設定します.
     *
     * @param title PlayerDataを受け取り,表示名を返すFunction
     * @return このBuilder
     */
    @Nonnull
    IconBuilder<T> title(@Nonnull Function<PlayerData, String> title);

    /**
     * ItemStack(IconBuilder)のloreを設定します.
     *
     * @param lore PlayerDataを受け取り,loreを返すFunction
     * @return このBuilder
     */
    @Nonnull
    IconBuilder<T> lore(@Nonnull Function<PlayerData, List<String>> lore);

    /**
     * ItemStack(IconBuilder)にエンチャントを付与します.
     *
     * @return このBuilder
     */
    @Nonnull
    IconBuilder<T> enchanted();

    /**
     * ItemStackの個数を指定します.
     *
     * @param number ItemStackの個数
     * @return このBuilder
     */
    @Nonnull
    IconBuilder<T> number(int number);

    /**
     * SlotIconBuilderからFunctionalSlotBuilderを生成します.
     *
     * @return FunctionalSlotBuilder
     */
    @Nonnull
    FunctionalSlotBuilder toFunctionalSlotBuilder();

    /**
     * Builderによって指定された各引数を用いてインスタンスを生成します
     *
     * @return 生成されたインスタンス. ({@code null} は許容されません)
     */
    @Nonnull
    T build(@Nonnull PlayerData playerData);
}
