package com.github.unchama.seichiassist.data.menu.component;

import com.github.unchama.seichiassist.data.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.util.*;
import java.util.function.*;

/**
 * MenuのButtonを表すクラス。
 * Created by karayuu on 2019/01/10
 */
public class ButtonBuilder {
    /** メニューに表示されるアイコンの種類(指定必須) */
    private Material icon;
    /** アイコンの名前 */
    private String name;
    /** アイコンのLore */
    private List<Function<PlayerData, String>> lore;

    public ButtonBuilder(Material icon) {
        this.icon = icon;
    }

    public ButtonBuilder name(String name) {
        this.name = name;
        return this;
    }

    @SafeVarargs
    public final ButtonBuilder lore(Function<PlayerData, String>... lore) {
        this.lore = Arrays.asList(lore);
        return this;
    }

    public ButtonBuilder lore(List<Function<PlayerData, String>> lore) {
        this.lore.addAll(lore);
        return this;
    }

    public ItemStack build(PlayerData playerData) {
        ItemStack item = new ItemStack(icon, 1);
        ItemMeta meta = Bukkit.getItemFactory().getItemMeta(icon);

        if (name != null) {
            meta.setDisplayName(name);
        }

        List<String> appliedLore = new ArrayList<>();
        if (lore != null) {
            lore.forEach((_lore) -> {appliedLore.add(_lore.apply(playerData));});
            meta.setLore(appliedLore);
        }

        item.setItemMeta(meta);
        return item;
    }
}
