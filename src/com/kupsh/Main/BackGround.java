package com.kupsh.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class BackGround {

	private Game game;

	public BackGround(Game game) {
		this.game = game;
	}

	public void render(Graphics g) {

		Font fnt = new Font("arial", Font.BOLD, (int) (Game.HEIGHT / 9.6));
		g.setFont(fnt);
		g.setColor(Color.black);
		g.fillRect((int) (Game.WIDTH / 4.923), (int) (Game.HEIGHT / 3.2),
				(int) (Game.WIDTH / 1.6), (int) (Game.HEIGHT / 9.6));
		g.drawString("Background", (int) (Game.WIDTH / 3.2),
				(int) (Game.HEIGHT / 4.8));

		Font fnt1 = new Font("arial", Font.BOLD, (int) (Game.HEIGHT / 16));
		g.setFont(fnt1);
		g.drawString("Koins: " + game.getTotalCoins(), (int) (Game.WIDTH / 64),
				(int) (Game.HEIGHT / 12));
		g.setColor(Color.white);
		g.drawString("Main Menu", (int) (Game.WIDTH / 4.923)
				+ (int) (Game.WIDTH / 25.6), (int) (Game.HEIGHT / 3.2)
				+ (int) (Game.HEIGHT / 13.714));

		// find selected one and fills green

		g.setColor(Color.green);
		if (game.isDefaultselected() == true)
			g.fillRect((int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 2.28),
					(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 9.6));
		if (game.isSkyselected() == true)
			g.fillRect((int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 1.77),
					(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 9.6));
		if (game.isUnderwaterselected() == true)
			g.fillRect((int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 1.454),
					(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 9.6));

		// finds locked ones and fills red

		g.setColor(Color.red);
		if (game.isSkyunlocked() == false)
			g.fillRect((int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 1.77),
					(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 9.6));
		if (game.isUnderwaterunlocked() == false)
			g.fillRect((int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 1.454),
					(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 9.6));

		// finds if not locked and not selected and draws black

		g.setColor(Color.black);
		if (game.isDefaultselected() == false)
			g.fillRect((int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 2.28),
					(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 9.6));
		if (game.isSkyselected() == false && game.isSkyunlocked() == true)
			g.fillRect((int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 1.77),
					(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 9.6));
		if (game.isUnderwaterselected() == false
				&& game.isUnderwaterunlocked() == true)
			g.fillRect((int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 1.454),
					(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 9.6));

		// finds if unlocked then sets string to name

		g.setFont(fnt1);
		g.setColor(Color.white);
		g.drawString("Default", (int) (Game.WIDTH / 64)
				+ (int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 2.28)
				+ (int) (Game.HEIGHT / 13.714));
		if (game.isSkyunlocked() == true)
			g.drawString("Sky", (int) (Game.WIDTH / 64)
					+ (int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 1.77)
					+ (int) (Game.HEIGHT / 13.714));
		if (game.isUnderwaterunlocked() == true)
			g.drawString("Underwater", (int) (Game.WIDTH / 64)
					+ (int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 1.454)
					+ (int) (Game.HEIGHT / 13.714));
		if (game.isSkyunlocked() == false)
			g.drawString("1000 Coins", (int) (Game.WIDTH / 64)
					+ (int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 1.77)
					+ (int) (Game.HEIGHT / 13.714));
		if (game.isUnderwaterunlocked() == false)
			g.drawString("1000 Coins", (int) (Game.WIDTH / 64)
					+ (int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 1.454)
					+ (int) (Game.HEIGHT / 13.714));
	}
}
