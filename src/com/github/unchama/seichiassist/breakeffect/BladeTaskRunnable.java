package com.github.unchama.seichiassist.breakeffect;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.unchama.seichiassist.ActiveSkill;
import com.github.unchama.seichiassist.data.Coordinate;
import com.github.unchama.seichiassist.data.PlayerData;

@Deprecated
public class BladeTaskRunnable extends BukkitRunnable{
	private final Player player;
	private final PlayerData playerdata;
	private final ItemStack tool;
	//破壊するブロックリスト
    private final List<Block> breaklist;
	//スキルで破壊される相対座標
    private final Coordinate start;
    private final Coordinate end;
	//スキルが発動される中心位置
    private final Location droploc;
	//相対座標から得られるスキルの範囲座標
    private final Coordinate breaklength;
	//逐一更新が必要な位置
	Location explosionloc;


	public BladeTaskRunnable(Player player,PlayerData playerdata,ItemStack tool,List<Block> breaklist, Coordinate start,
			Coordinate end, Location droploc) {
		this.player = player;
		this.playerdata = playerdata;
		this.tool = tool;
		this.breaklist = breaklist;
		this.start = start;
		this.end = end;
		this.droploc = droploc;
		breaklength = ActiveSkill.BREAK.getBreakLength(playerdata.activeskilldata.skillnum);
	}
	@Override
	public void run() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
