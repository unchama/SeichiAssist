package com.github.unchama.seichiassist.arroweffect;

import com.github.unchama.seichiassist.SeichiAssist;
import com.github.unchama.seichiassist.util.Sound;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;

/**
 * 遠距離系スキルにおけるエフェクトエンティティのベースとなるクラス.
 * @param <E> エンティティの型
 *
 * Created by karayuu on 2019/03/02
 */
abstract class ArrowEffectTaskRunnable<E extends Projectile> extends BukkitRunnable {
    /** スキル発動時に鳴らす音 */
    @Nonnull
    private final Sound sound;
    /** スキルを発動したプレイヤー */
    @Nonnull
    private final Player player;
    /** 速度(相対的な) */
    private final double velocity;
    /** 経過時間(20tick = 1s) */
    private long tick = 0;
    /** 矢の代わりとなるエンティティ */
    final E entity;
    /** プレイヤーの位置 */
    @Nonnull
    private final Location location;

    /**
     * コンストラクタ.
     * エンティティ生成までを行います.
     * @param player スキルを発動したプレイヤー
     * @param entityClass スキルエフェクトのエンティティ
     * @param velocity 速度(相対的な)
     */
    ArrowEffectTaskRunnable(@Nonnull final Sound sound, @Nonnull final Player player,
                            @Nonnull final Class<E> entityClass, double velocity) {
        this.sound = sound;
        this.player = player;
        /* 生成するエンティティクラス */
        this.velocity = velocity;

        //エフェクトの実行
        final double margin = 1.6;
        //プレイヤーの位置を取得.
        this.location = player.getLocation();
        location.add(location.getDirection()).add(0, margin, 0);
        entity = player.getWorld().spawn(location, entityClass);
    }

    /**
     * 音を鳴らし,エンティティを移動させる処理です.
     * コンストラクタと同時に呼び出してください.
     */
    void invoke() {
        //音を鳴らす.
        sound.playSound(player);

        Vector vector = location.getDirection();
        vector.setX(vector.getX() * velocity);
        vector.setY(vector.getY() * velocity);
        vector.setZ(vector.getZ() * velocity);
        SeichiAssist.entitylist.add(entity);
        entity.setShooter(player);
        entity.setGravity(false);
        entity.setMetadata("ArrowSkill", new FixedMetadataValue(SeichiAssist.plugin, true));
        entity.setVelocity(vector);
    }

    /**
     * スキル発動時のエンティティを5s(=100tick)後に削除する処理.
     */
    @Override
    public void run() {
        tick++;

        final int limitTick = 100;
        if (tick > limitTick) {
            //制限値を超えたらエンティティを削除
            entity.remove();
            SeichiAssist.entitylist.remove(entity);
            this.cancel();
        }
    }
}
