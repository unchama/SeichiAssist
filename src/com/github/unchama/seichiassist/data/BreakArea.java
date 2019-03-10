package com.github.unchama.seichiassist.data;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.github.unchama.seichiassist.ActiveSkill;
import com.github.unchama.seichiassist.util.BreakUtil;

public class BreakArea {
	//スキルタイプ番号
    private final int type;
	//スキルレベル
    private final int level;
	//フラグ
    private final int mineflagnum;
	//南向きを基準として破壊の範囲座標
    private final Coordinate breaklength;
	//破壊回数
    private final int breaknum;
	//向いている方角
    private String dir;
	//破壊範囲を示す相対座標リスト
    private final List<Coordinate> startlist;
    private final List<Coordinate> endlist;
	//変数として利用する相対座標
	private Coordinate start,end;
	//アサルトスキルの時true
    private final boolean assaultflag;


	public BreakArea(Player player,int type, int skilllevel,int mineflagnum,boolean assaultflag) {
		this.type = type;
		this.level = skilllevel;
		this.mineflagnum = mineflagnum;
		this.assaultflag = assaultflag;
		this.dir = BreakUtil.getCardinalDirection(player);
		this.startlist = new ArrayList<Coordinate>();
		this.endlist = new ArrayList<Coordinate>();
		//初期化
		ActiveSkill[] as = ActiveSkill.values();
		this.breaklength = as[type-1].getBreakLength(level);
		this.breaknum = as[type-1].getRepeatTimes(level);

		//初期範囲設定
		makeArea();
	}

	public List<Coordinate> getStartList() {
		return startlist;
	}
	public List<Coordinate> getEndList() {
		return endlist;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = new String(dir);
	}
	//破壊範囲の設定
	public void makeArea() {
		startlist.clear();
		endlist.clear();
		//中心座標(0,0,0)のスタートとエンドを仮取得
		start = new Coordinate(-(breaklength.x-1)/2, -(breaklength.y-1)/2, -(breaklength.z-1)/2);
		end = new Coordinate((breaklength.x-1)/2, (breaklength.y-1)/2, (breaklength.z-1)/2);
		//アサルトスキルの時
		if(assaultflag){
			if(type == 6 && level == 10){
				shift((breaklength.y-1)/2 - 1,0);
			}
		}
		//上向きまたは下向きの時
		else if(dir.equals("U") || dir.equals("D")){
			if(!assaultflag && level < 3){}
			else{shift((breaklength.y-1)/2, 0);}
		}
		//それ以外の範囲
		else{
			shift((breaklength.y-1)/2 - 1, (breaklength.z-1)/2);

		}

		if(type == ActiveSkill.BREAK.gettypenum() && level < 3){
			end.add(0, 1, 0);
		}
		if(type == ActiveSkill.BREAK.gettypenum() && level < 3 && mineflagnum == 1){
			shift(1,0);
		}

		//スタートリストに追加
		startlist.add(new Coordinate(start));
		endlist.add(new Coordinate(end));

		//破壊回数だけリストに追加

		for(int count = 1; count < breaknum;count++){
			switch(dir){
			case "N":
			case "E":
			case "S":
			case "W":
				shift(0, breaklength.z);
				break;
			case "U":
			case "D":
				if(!assaultflag && level < 3){}
				else{shift(breaklength.y, 0);}
				break;

			}
			startlist.add(new Coordinate(start));
			endlist.add(new Coordinate(end));
		}


		switch(dir){
		case "N":
			rotateXZ(180);
			break;
		case "E":
			rotateXZ(270);
			break;
		case "S":
			break;
		case "W":
			rotateXZ(90);
			break;
		case "U":
			break;
		case "D":
			if(!assaultflag)multiply_Y();
			break;
		}
	}
	private void multiply_Y() {
		for(int count = 0;count < breaknum ; count++){
			Coordinate start = startlist.get(count);
			Coordinate end = endlist.get(count);
			Coordinate tmpstart = new Coordinate(startlist.get(count));
			if(-1 >=0){
				start.setXYZ(start.x,start.y * -1,start.z);
				end.setXYZ(end.x,end.y * -1,end.z);
			}else{
				start.setXYZ(start.x,end.y * -1,start.z);
				end.setXYZ(end.x,tmpstart.y * -1,end.z);
			}
		}
	}
	private void shift(int y, int z) {
		start.add(0,y,z);
		end.add(0,y,z);
	}
	private void rotateXZ(int d) {
		for(int count = 0;count < breaknum ; count++){
			Coordinate start = startlist.get(count);
			Coordinate end = endlist.get(count);
			Coordinate tmpstart = new Coordinate(startlist.get(count));
			switch(d){
			case 90:
				start.setXYZ(-end.z, start.y, start.x);
				end.setXYZ(-tmpstart.z,end.y,end.x);
				break;
			case 180:
				start.setZ(-end.z);
				end.setZ(-tmpstart.z);
				break;
			case 270:
				start.setXYZ(start.z,start.y,start.x);
				end.setXYZ(end.z, end.y, end.x);
				break;
			case 360:
				break;
			}
		}
	}
	public Coordinate getBreakLength() {
		return breaklength;
	}
	public int getBreakNum() {
		return breaknum;
	}


}
