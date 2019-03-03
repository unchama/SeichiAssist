package com.github.unchama.seichiassist.arroweffect;

import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;

import javax.annotation.*;

/**
 * プレミアムエフェクト・ブレイドの矢の代替エンティティ実装クラス.
 * 遠距離系スキル(矢を放つ系のスキル)の代替を生成し動かしている.
 * 現在は不使用.
 *
 * @see com.github.unchama.seichiassist.breakeffect.BladeTaskRunnable (範囲破壊系のエフェクト処理はこちら※現在不使用)
 * @see com.github.unchama.seichiassist.listener.EntityListener (こちらで破壊処理を行っている)
 */
@Deprecated
public class ArrowBladeTaskRunnable extends ArrowEffectTaskRunnable<SmallFireball> {
    /**
     * エンティティを生成します.
     * @param player スキルを発動したプレイヤー
     */
    public ArrowBladeTaskRunnable(@Nonnull Player player) {
        super(EffectSounds.arrowBladeSound, player, SmallFireball.class, 0.4);
        invoke();
    }
}
