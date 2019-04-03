package com.github.unchama.seichiassist.util;

import com.github.unchama.seichiassist.data.PlayerData;

import javax.annotation.Nonnull;

/**
 * プレイヤーデータを用いてBuildするBuilderを表すインターフェース.
 * @param <T> Buildする際に生成するインスタンスの型
 */
public interface PlayerDataHandleBuilder<T> {
    /**
     * Builderによって指定された各引数を用いてインスタンスを生成します
     *
     * @return 生成されたインスタンス. ({@code null} は許容されません)
     */
    @Nonnull
    T build(PlayerData playerData);
}
