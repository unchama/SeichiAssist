package com.github.unchama.seichiassist.arroweffect;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

/**
 * エフェクト・メテオの矢の代替エンティティ実装クラス.
 * 遠距離系スキル(矢を放つ系のスキル)の代替である矢エンティティを生成し動かしている.
 *
 * @see com.github.unchama.seichiassist.breakeffect.MeteoTaskRunnable (範囲破壊系のエフェクト処理はこちら)
 * @see com.github.unchama.seichiassist.listener.EntityListener (ここで破壊処理を行っている)
 */
public class ArrowMeteoTaskRunnable extends ArrowEffectTaskRunnable<Arrow> {
    /**
     * エンティティを生成します.
     * @param player スキルを発動したプレイヤー
     */
    public ArrowMeteoTaskRunnable(@Nonnull final Player player) {
        super(EffectSounds.arrowMeteoSound, player, Arrow.class, 1.0);
        invoke();
    }
}
