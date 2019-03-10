package com.github.unchama.seichiassist.task;

import org.bukkit.Location;
import org.bukkit.entity.Projectile;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.github.unchama.seichiassist.SeichiAssist;

public class ArrowControlTaskRunnable extends BukkitRunnable{

	private final Projectile proj;
	private final Location standard;
	private Location projloc;
	private long tick;
	private final double speed;
	public ArrowControlTaskRunnable(Projectile proj, Location standard) {
		this.proj = proj;
		this.standard = standard;
		this.tick = 0;
		this.speed = 6;
	}

	@Override
	public void run() {
		tick++;
		if(tick > 15){
			proj.remove();
			SeichiAssist.entitylist.remove(proj);
			this.cancel();
		}
		projloc = proj.getLocation();



		Vector vec = new Vector(standard.getX()-projloc.getX(),standard.getY()-projloc.getY(),standard.getZ()-projloc.getZ());
		vec.normalize();

		proj.setVelocity(vec.multiply(speed));

	}

}
