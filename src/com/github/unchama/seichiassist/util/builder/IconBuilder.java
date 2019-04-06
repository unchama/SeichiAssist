package com.github.unchama.seichiassist.util.builder;

import com.github.unchama.seichiassist.data.PlayerData;
import com.github.unchama.seichiassist.data.menu.icon.Icon;

import javax.annotation.Nonnull;

/**
 * プレイヤーデータを用いてIconをBuildするBuilderを表すインターフェース.
 * @param <T> Buildする際に生成するインスタンスの型
 */
public interface IconBuilder<T extends Icon> extends Builder {
    /**
     * Builderによって指定された各引数を用いてインスタンスを生成します
     *
     * @return 生成されたインスタンス. ({@code null} は許容されません)
     */
    @Nonnull
    T build(PlayerData playerData);
}
