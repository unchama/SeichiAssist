package com.github.unchama.seichiassist.arroweffect;

import com.github.unchama.seichiassist.util.Sound;

/**
 * ゲーム内のエフェクトで使用している音のまとめ.
 *
 * Created by karayuu on 2019/03/03
 */
class EffectSounds {
    /** 遠距離スキル時のブレイド発動時の音 */
    @Deprecated
    static final Sound arrowBladeSound = new Sound(org.bukkit.Sound.ENTITY_GHAST_SHOOT, 1, (float) 1.3);
    /** 遠距離スキル時のブリザード発動時の音 */
    static final Sound arrowBlizzaedSound = new Sound(org.bukkit.Sound.ENTITY_SNOWBALL_THROW, 1, (float) 1.3);
    /** 遠距離スキル時のエクスプロージョン発動時の音 */
    static final Sound arrowExplosionSound = new Sound(org.bukkit.Sound.ENTITY_GHAST_SHOOT, 1, (float) 1.3);
    /** 遠距離スキル時のマジック発動時の音 */
    static final Sound arrowMagicSound = new Sound(org.bukkit.Sound.ENTITY_WITCH_THROW, 1, (float) 1.3);
    /** 遠距離スキル時のメテオ発動時の音 */
    static final Sound arrowMeteoSound = new Sound(org.bukkit.Sound.ENTITY_ARROW_SHOOT, 1, (float) 1.3);
}
