package com.github.unchama.seichiassist.arroweffect;

import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

import javax.annotation.Nonnull;

/**
 * エフェクト・ブリザードの矢の代替エンティティ実装クラス.
 * 遠距離系スキル(矢を放つ系のスキル)の代替である雪玉エンティティを生成し動かしている.
 *
 * @see com.github.unchama.seichiassist.breakeffect.BlizzardTaskRunnable (範囲破壊系のエフェクト処理はこちら)
 * @see com.github.unchama.seichiassist.listener.EntityListener (ここで破壊処理を行っている)
 */
public class ArrowBlizzardTaskRunnable extends ArrowEffectTaskRunnable<Snowball> {
    /**
     * エンティティを生成します.
     * @param player スキルを発動したプレイヤー
     */
    public ArrowBlizzardTaskRunnable(@Nonnull final Player player) {
        super(EffectSounds.arrowBlizzaedSound, player, Snowball.class, 1.0);
        invoke();
    }
}
