package com.kupsh.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameOver {

	private int score;
	private int enemies_spawned;
	private int coins;

	private HUD hud;
	private Spawn spawn;
	private Game game;

	public GameOver(HUD hud, Spawn spawn, Game game) {
		this.hud = hud;
		this.spawn = spawn;
		this.game = game;
	}

	public void render(Graphics g) {

		score = hud.getScore();
		enemies_spawned = spawn.getEnemies_spawned();
		coins = hud.getCoins();

		if (score > game.getHighScore()) {
			game.setHighScore(score);
		}

		Font fnt = new Font("arial", Font.BOLD, (int) (Game.HEIGHT / 9.6));
		g.setFont(fnt);
		g.setColor(Color.black);
		g.drawString("Game Over!", (int) (Game.WIDTH / 4),
				(int) (Game.HEIGHT / 4.8));
		g.fillRect((int) (Game.WIDTH / 4.923), (int) (Game.HEIGHT / 3.2),
				(int) (Game.WIDTH / 1.6), (int) (Game.HEIGHT / 9.6));
		g.fillRect((int) (Game.WIDTH / 4.923), (int) (Game.HEIGHT / 2.28),
				(int) (Game.WIDTH / 1.6), (int) (Game.HEIGHT / 9.6));
		g.fillRect((int) (Game.WIDTH / 4.923), (int) (Game.HEIGHT / 1.77),
				(int) (Game.WIDTH / 1.6), (int) (Game.HEIGHT / 9.6));
		g.fillRect((int) (Game.WIDTH / 4.923), (int) (Game.HEIGHT / 1.454),
				(int) (Game.WIDTH / 1.6), (int) (Game.HEIGHT / 9.6));
		g.fillRect((int) (Game.WIDTH / 4.923), (int) (Game.HEIGHT / 1.23),
				(int) (Game.WIDTH / 1.6), (int) (Game.HEIGHT / 9.6));
		g.setColor(Color.white);
		Font fnt1 = new Font("arial", Font.BOLD, (int) (Game.HEIGHT / 16));
		g.setFont(fnt1);
		g.drawString("Main Menu", (int) (Game.WIDTH / 4.923)
				+ (int) (Game.WIDTH / 6.4), (int) (Game.HEIGHT / 3.2)
				+ (int) (Game.HEIGHT / 13.714));
		g.drawString("Score: " + score, (int) (Game.WIDTH / 4.923)
				+ (int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 2.28)
				+ (int) (Game.HEIGHT / 13.714));
		g.drawString("Enemies Spawned: " + enemies_spawned,
				(int) (Game.WIDTH / 4.923) + (int) (Game.WIDTH / 64),
				(int) (Game.HEIGHT / 1.77) + (int) (Game.HEIGHT / 13.714));
		g.drawString("Koins: " + coins, (int) (Game.WIDTH / 4.923)
				+ (int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 1.454)
				+ (int) (Game.HEIGHT / 13.714));
		g.drawString("High Score: " + game.getHighScore(),
				(int) (Game.WIDTH / 4.923) + (int) (Game.WIDTH / 64),
				(int) (Game.HEIGHT / 1.23) + (int) (Game.HEIGHT / 13.714));

	}
}
