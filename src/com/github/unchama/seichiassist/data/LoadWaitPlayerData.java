package com.github.unchama.seichiassist.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LoadWaitPlayerData {
	public Player player;
	public PlayerData playerData;
	public Boolean flag;
	public int i;
	
	public LoadWaitPlayerData(PlayerData playerData){
		this.playerData = playerData;
		this.player = Bukkit.getPlayer(playerData.uuid);
		flag = true;
		i = 0;
	}
}
