package com.github.unchama.seichiassist;

import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.github.unchama.seichiassist.arroweffect.ArrowBladeTaskRunnable;
import com.github.unchama.seichiassist.arroweffect.ArrowMagicTaskRunnable;
import com.github.unchama.seichiassist.arroweffect.ArrowTiamatTaskRunnable;
import com.github.unchama.seichiassist.arroweffect.ArrowVladmiaTaskRunnable;
import com.github.unchama.seichiassist.breakeffect.BladeTaskRunnable;
import com.github.unchama.seichiassist.breakeffect.MagicTaskRunnable;
import com.github.unchama.seichiassist.breakeffect.TiamatTaskRunnable;
import com.github.unchama.seichiassist.breakeffect.VladmiaTaskRunnable;
import com.github.unchama.seichiassist.data.Coordinate;
import com.github.unchama.seichiassist.data.PlayerData;

public enum ActiveSkillPremiumEffect {

	MAGIC(ChatColor.RED + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "マジック"),
/*	BLADE(2,"ef_blade",ChatColor.GOLD + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "ブレイド","切り刻む",15,Material.IRON_SWORD),
	VLADMIA(3,"ef_vladmia",ChatColor.DARK_RED + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "ブラッドミア","吸血する",20,Material.REDSTONE),
	TIAMAT(4,"ef_tiamat",ChatColor.BLUE + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "ティアマト","彗星を落とす",25,Material.NETHER_STAR),
*/
	;

	private final SeichiAssist plugin = SeichiAssist.plugin;

	private final int num;
	private final String sql_name;
	private final String name;
	private final String explain;
	private final int usepoint;
	private final Material material;

	ActiveSkillPremiumEffect(String name){
		this.num = 1;
		this.sql_name = "ef_magic";
		this.name = name;
		this.explain = "鶏が出る手品";
		this.usepoint = 10;
		this.material = Material.RED_ROSE;
	}

	public int getNum(){
        return this.num;
    }
	public String getsqlName(){
		return this.sql_name;
	}
	public String getName(){
		return this.name;
	}
	public String getExplain(){
		return this.explain;
	}
	public int getUsePoint(){
		return this.usepoint;
	}
	public Material getMaterial(){
		return this.material;
	}
	//プレイヤーが所持しているかどうか
	public Boolean isObtained(Map<Integer,Boolean> flagmap){
		return flagmap.get(getNum());
	}
	//獲得させる処理
	public void setObtained(Map<Integer,Boolean> flagmap) {
		flagmap.put(getNum(), true);
		return;
	}
	//エフェクトの実行処理分岐 範囲破壊と複数範囲破壊
	public void runBreakEffect(Player player,PlayerData playerdata,ItemStack tool,List<Block> breaklist,Coordinate start,Coordinate end,Location standard){
		switch(this.toString()){
		case "MAGIC":
			if(SeichiAssist.DEBUG){
				new MagicTaskRunnable(player,playerdata,tool,breaklist,start,end,standard).runTaskTimer(plugin, 0, 100);
			}else{
				new MagicTaskRunnable(player,playerdata,tool,breaklist,start,end,standard).runTaskTimer(plugin, 0, 10);
			}

			break;
		case "BLADE":
			new BladeTaskRunnable(player,playerdata,tool,breaklist,start,end,standard).runTaskLater(plugin, 1);
			break;
		case "VLADMIA":
			new VladmiaTaskRunnable(player,playerdata,tool,breaklist,start,end,standard).runTaskLater(plugin, 1);
			break;
		case "TIAMAT":
			new TiamatTaskRunnable(player,playerdata,tool,breaklist,start,end,standard).runTaskLater(plugin, 1);
			break;
		default :
			break;
		}
		return;
	}

	//エフェクトの実行処理分岐
	public void runArrowEffect(Player player){
		switch(this.toString()){
		case "MAGIC":
			new ArrowMagicTaskRunnable(player).runTaskTimer(plugin,0,1);
			break;
		case "BLADE":
			new ArrowBladeTaskRunnable(player).runTaskTimer(plugin,0,1);
			break;
		case "VLADMIA":
			new ArrowVladmiaTaskRunnable(player).runTaskTimer(plugin,0,1);
			break;
		case "TIAMAT":
			new ArrowTiamatTaskRunnable(player).runTaskTimer(plugin,0,1);
			break;
		default :
			break;
		}
		return;
	}


	public static String getNamebyNum(int effectnum) {
		ActiveSkillEffect[] skilleffect = ActiveSkillEffect.values();
		for(int i = 0 ; i < skilleffect.length ; i++){
			if(skilleffect[i].getNum() == effectnum){
				return skilleffect[i].getName();
			}
		}
		return "未設定";
	}
}
