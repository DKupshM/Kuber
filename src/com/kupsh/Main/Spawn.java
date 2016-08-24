package com.kupsh.Main;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Spawn {
	private Handler handler;
	private Random r;
	private BufferedImage Koin;

	private int ticks;
	private int level = 1;
	private int enemies_spawned;

	public Spawn(Handler handler, Random r, BufferedImage Koin) {
		this.handler = handler;
		this.r = r;
		this.Koin = Koin;
	}

	public void tick() {
		ticks++;
		if (level == 1) {
			if (ticks == 20) {
				handler.addObject(new BasicEnemy(Game.WIDTH, r
						.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
				handler.addObject(new Coin(Game.WIDTH, r.nextInt(Game.HEIGHT),
						ID.Coin, handler, Koin));
				enemies_spawned++;
			}
			if (ticks == 40) {
				handler.addObject(new Coin(Game.WIDTH, r.nextInt(Game.HEIGHT),
						ID.Coin, handler, Koin));
				for (int i = 0; i < r.nextInt(2) + 1; i++)
					handler.addObject(new BasicEnemy(Game.WIDTH, r
							.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
				enemies_spawned++;
			}
			if (ticks == 60) {
				level++;
				ticks = 0;
				System.out.println("Level Up: " + level);
				handler.addObject(new Coin(Game.WIDTH, r.nextInt(Game.HEIGHT),
						ID.Coin, handler, Koin));
				for (int i = 0; i < r.nextInt(2) + 1; i++)
					handler.addObject(new BasicEnemy(Game.WIDTH, r
							.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
				enemies_spawned++;
			}
		}
		if (level == 2) {
			if (isDivisible(ticks, 10)) {
				for (int i = 0; i < r.nextInt(2) + 1; i++)
					handler.addObject(new BasicEnemy(Game.WIDTH, r
							.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
				enemies_spawned++;
			}
			if (isDivisible(ticks, 20))
				handler.addObject(new Coin(Game.WIDTH, r.nextInt(Game.HEIGHT),
						ID.Coin, handler, Koin));
			if (ticks == 300) {
				level++;
				ticks = 0;
				System.out.println("Level Up: " + level);
			}
		}
		if (level == 3) {
			if (isDivisible(ticks, 10)) {
				for (int i = 0; i < r.nextInt(3) + 1; i++)
					handler.addObject(new BasicEnemy(Game.WIDTH, r
							.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
				enemies_spawned++;
			}
			if (isDivisible(ticks, 20))
				handler.addObject(new Coin(Game.WIDTH, r.nextInt(Game.HEIGHT),
						ID.Coin, handler, Koin));
		}
	}

	public boolean isDivisible(int ticks, int number) {
		if (ticks % number == 0) {
			return true;
		} else {
			return false;
		}
	}

	public int getEnemies_spawned() {
		return enemies_spawned;
	}

	public void setEnemies_spawned(int enemies_spawned) {
		this.enemies_spawned = enemies_spawned;
	}

	public int getTicks() {
		return ticks;
	}

	public void setTicks(int ticks) {
		this.ticks = ticks;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
