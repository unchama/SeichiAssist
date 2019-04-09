package com.github.unchama.seichiassist.data.menu.icon.skull;

import com.github.unchama.seichiassist.data.PlayerData;
import com.github.unchama.seichiassist.data.menu.icon.Icon;
import com.github.unchama.seichiassist.data.menu.icon.component.BaseIconComponent;
import com.github.unchama.seichiassist.data.menu.slot.functional.FunctionalSlotBuilder;
import com.github.unchama.seichiassist.util.builder.IconBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

/**
 * Created by karayuu on 2019/04/09
 */
public class SkullIconBuilder implements IconBuilder<Icon> {
    @Nonnull
    private final BaseIconComponent component;

    @Nonnull
    private String ownerName;

    private SkullIconBuilder() {
        this.component = new BaseIconComponent(Material.SKULL_ITEM);
    }

    private SkullIconBuilder of() {
        return new SkullIconBuilder();
    }

    @Nonnull
    @Override
    public SkullIconBuilder title(@Nonnull Function<PlayerData, String> title) {
        requireNonNull(title);
        component.setTitle(title);
        return this;
    }

    @Nonnull
    @Override
    public SkullIconBuilder lore(@Nonnull Function<PlayerData, List<String>> lore) {
        requireNonNull(lore);
        component.setLore(lore);
        return this;
    }

    @Nonnull
    @Override
    public SkullIconBuilder enchanted() {
        component.setEnchanted(true);
        return this;
    }

    @Nonnull
    @Override
    public SkullIconBuilder number(int number) {
        component.setNumber(number);
        return this;
    }

    @Nonnull
    @Override
    public FunctionalSlotBuilder toFunctionalSlotBuilder() {
        return FunctionalSlotBuilder.of(this);
    }

    @Nonnull
    public SkullIconBuilder owner(@Nonnull String ownerName) {
        requireNonNull(ownerName);
        this.ownerName = ownerName;
        return this;
    }

    @Nonnull
    @Override
    public Icon build(@Nonnull PlayerData playerData) {
        requireNonNull(playerData);

        ItemStack skull = component.getItemStack();
        SkullMeta meta = (SkullMeta) component.getItemMeta(playerData);

        meta.setOwner(ownerName);
        skull.setItemMeta(meta);

        return Icon.of(skull);
    }
}
