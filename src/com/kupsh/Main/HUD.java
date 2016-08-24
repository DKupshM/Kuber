package com.kupsh.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

	public static int Health = 100;
	// health of player multiply by 2 to get bar
	private int greenValue = 255;

	private int score = 0;
	private int coins = 0;
	private Game game;

	public HUD(Game game) {
		this.game = game;
	}

	public void tick() {
		greenValue = Health * 2;
		greenValue = Game.clamp(greenValue, 0, 255);
		score++;
		if (Health <= 0) {
			game.setGame_State(State.GameOver);
		}
		if(score % 100 == 0){
			Health += 1;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect((int) (Game.WIDTH - 210), 10,
				200, 25);
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect((int) (Game.WIDTH - 210), 10,
				(int) (Health * 2), 25);
		g.setColor(Color.black);
		g.drawRect((int) (Game.WIDTH - 210), 10,
				200, 25);
		Font fnt = new Font("ariel", Font.BOLD, 20);
		g.setFont(fnt);
		g.drawString("Score: " + score,(int) (Game.WIDTH  - 210),
				55);
		g.drawString("Koins:  " + coins, (int) (Game.WIDTH  - 210),
				 75);
		if (game.isShowfps() == true && game.isShowtps() == true) {
			g.drawString("Fps: " + game.getFrames(), 10,
					30);
			g.drawString("Tps: " + game.getUpdates(), 10,
					60);
		} else if (game.isShowfps() == true && game.isShowtps() == false)
			g.drawString("Fps: " + game.getFrames(), 10,
					30);
		else if (game.isShowfps() == false && game.isShowtps() == true)
			g.drawString("Tps: " + game.getUpdates(), 10,
					30);

	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
