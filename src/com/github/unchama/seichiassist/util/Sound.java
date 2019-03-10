package com.github.unchama.seichiassist.util;

import org.bukkit.*;
import org.bukkit.entity.*;

import javax.annotation.*;

/**
 * BukkitのSoundクラスのラッパーです.
 * Created by karayuu on 2019/03/02
 */
public class Sound {
    /** 鳴らす音の種類 */
    @Nonnull
    private final org.bukkit.Sound sound;
    /** 音の音量(標準は1) */
    private final float volume;
    /** 音の音程(標準は1) */
    private final float interval;

    /**
     * BukkitのSoundクラスのラッパーです.
     * @param sound 音色
     * @param volume 音量
     * @param interval 音程
     */
    public Sound(@Nonnull org.bukkit.Sound sound, float volume, float interval) {
        this.sound = sound;
        this.volume = volume;
        this.interval = interval;
}

    public Sound(@Nonnull org.bukkit.Sound sound) {
        this(sound, (short) 1, (short) 1);
    }

    /**
     * プレイヤーの位置に音を鳴らします.
     * @param player 鳴らすプレイヤー
     */
    public void playSound(@Nonnull Player player) {
        player.playSound(player.getLocation(), sound, volume, interval);
    }

    /**
     * 指定された位置で音を鳴らします.
     * @param location 音を鳴らす位置
     */
    public void playSound(@Nonnull Location location) {
        location.getWorld().playSound(location, sound, volume, interval);
    }
}
