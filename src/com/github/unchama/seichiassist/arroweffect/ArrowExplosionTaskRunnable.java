package com.github.unchama.seichiassist.arroweffect;

import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;

import javax.annotation.Nonnull;

/**
 * エフェクト・エクスプロージョンの矢の代替エンティティ実装クラス.
 * 遠距離系スキル(矢を放つ系のスキル)の代替であるファイアーボールエンティティを生成し動かしている.
 *
 * @see com.github.unchama.seichiassist.breakeffect.ExplosionTaskRunnable (範囲破壊系のエフェクト処理はこちら)
 * @see com.github.unchama.seichiassist.listener.EntityListener (ここで破壊処理を行っている)
 */
public class ArrowExplosionTaskRunnable extends ArrowEffectTaskRunnable<SmallFireball> {
    /**
     * エンティティを生成します.
     * @param player スキルを発動したプレイヤー
     */
    public ArrowExplosionTaskRunnable(@Nonnull final Player player) {
        super(EffectSounds.arrowExplosionSound, player, SmallFireball.class, 0.4);
        invoke();
    }
}
