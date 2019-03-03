package com.github.unchama.seichiassist.arroweffect;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import javax.annotation.*;

/**
 * エフェクト・マジックの矢の代替エンティティ実装クラス.
 * 遠距離系スキル(矢を放つ系のスキル)の代替であるポーションエンティティを生成し動かしている.
 *
 * @see com.github.unchama.seichiassist.breakeffect.MagicTaskRunnable (範囲破壊系のエフェクト処理はこちら)
 * @see com.github.unchama.seichiassist.listener.EntityListener (ここで破壊処理を行っている)
 */
public class ArrowMagicTaskRunnable extends ArrowEffectTaskRunnable<ThrownPotion> {
    /**
     * エンティティを生成します.
     * @param player スキルを発動したプレイヤー
     */
    public ArrowMagicTaskRunnable(@Nonnull final Player player) {
        super(EffectSounds.arrowMagicSound, player, ThrownPotion.class, 0.8);

        //ポーションデータを生成
        ItemStack potion = new ItemStack(Material.SPLASH_POTION);
        PotionMeta pm = (PotionMeta) Bukkit.getItemFactory().getItemMeta(Material.SPLASH_POTION);
        pm.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL));
        potion.setItemMeta(pm);
        entity.setItem(potion);

        //エフェクト実行
        invoke();
    }
}
