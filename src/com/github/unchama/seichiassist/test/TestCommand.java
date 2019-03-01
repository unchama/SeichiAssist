package com.github.unchama.seichiassist.test;

import org.bukkit.command.*;

import java.util.*;

/**
 * Created by karayuu on 2019/03/01
 */
public class TestCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof ConsoleCommandSender)) {
            commandSender.sendMessage("このコマンドはコンソールのみで実行可能です.");
            return true;
        }
        
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
