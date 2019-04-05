package com.github.unchama.seichiassist.util.builder;

import javax.annotation.Nonnull;

/**
 * Builderを表すinterface.
 * Created by karayuu on 2019/03/29
 *
 * @param <T> Buildするインスタンスの型
 */
public interface Builder<T> {
    /**
     * Builderによって指定された各引数を用いてインスタンスを生成します
     *
     * @return 生成されたインスタンス. ({@code null} は許容されません)
     */
    @Nonnull
    T build();
}


