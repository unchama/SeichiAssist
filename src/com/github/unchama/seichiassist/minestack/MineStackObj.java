package com.github.unchama.seichiassist.minestack;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.*;
import org.bukkit.inventory.ItemStack;

public class MineStackObj implements Comparable<MineStackObj>{

	private final String objname;
	private final String japanesename;
	private final int level;
	private final Material material;
	private final int durability;
	private final boolean nameloreflag;
	private final int gachatype;
	private List<String> lore;
	private ItemStack itemstack;
	private final int stacktype;
	private Enchantment needed_enchantment;

	protected MineStackObj(String objname, String japanesename,
                           int level, Material material, int durability,
                           boolean nameloreflag, int gachatype, int stacktype){
		this.objname = objname;
		this.japanesename = japanesename;
		this.level = level;
		this.material = material;
		this.durability = durability;
		this.nameloreflag = nameloreflag;
		this.gachatype = gachatype;
		this.lore = null;
		this.itemstack = null;
		this.stacktype = stacktype;
	}

	protected MineStackObj(String objname, String japanesename,
                           int level, Material material, int durability,
                           boolean nameloreflag, int gachatype, List<String> lore, int stacktype){
		this.objname = objname;
		this.japanesename = japanesename;
		this.level = level;
		this.material = material;
		this.durability = durability;
		this.nameloreflag = nameloreflag;
		this.gachatype = gachatype;
		this.lore = lore;
		this.itemstack = null;
		this.stacktype = stacktype;
	}

	public MineStackObj(String objname, int level, ItemStack itemstack, boolean nameloreflag, int gachatype, int stacktype){
		this.objname = objname;
		this.japanesename = itemstack.getItemMeta().getDisplayName();
		this.level = level;
		this.material = itemstack.getType();
		this.durability = itemstack.getDurability();
		this.nameloreflag = nameloreflag;
		this.gachatype = gachatype;
		this.lore = itemstack.getItemMeta().getLore();
		this.itemstack = itemstack.clone();
		this.stacktype = stacktype;
	}

	protected MineStackObj(String objname, String japanesename, int level, Material material, int durability,
                           int gachatype, Enchantment needed_enchantment) {
		this.objname = objname;
		this.japanesename = japanesename;
		this.level = level;
		this.material = material;
		this.durability = durability;
		this.nameloreflag = true;
		this.gachatype = gachatype;
		this.stacktype = 5;
		this.needed_enchantment = needed_enchantment;
	}

	public String getMineStackObjName(){
		return objname;
	}
	public String getJapaneseName(){
		return japanesename;
	}
	public int getLevel(){
		return level;
	}
	public Material getMaterial(){
		return material;
	}
	public int getDurability(){
		return durability;
	}
	public boolean getNameloreflag(){
		return nameloreflag;
	}
	public int getGachatype(){
		return gachatype;
	}

	public List<String> getLore(){
		return lore;
	}

	public ItemStack getItemStack(){
		return itemstack;
	}

	public int getStacktype(){
		return stacktype;
	}

	public Enchantment getNeeded_enchantment() {
		return needed_enchantment;
	}

	@Override
	public int compareTo(MineStackObj o) {
		// TODO 自動生成されたメソッド・スタブ
		return this.level-o.level;
	}



}
