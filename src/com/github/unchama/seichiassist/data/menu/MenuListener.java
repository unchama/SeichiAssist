package com.github.unchama.seichiassist.data.menu;

import com.github.unchama.seichiassist.data.menu.inventory.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by karayuu on 2019/04/06
 */
public class MenuListener implements Listener {
    public static List<Menu<? extends Menu>> menus = new ArrayList<>();

    @EventHandler
    public void menuEvent(@Nonnull InventoryClickEvent event) {
        menus.forEach(menu -> menu.invoke(event));
    }
}
