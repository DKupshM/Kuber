package com.kupsh.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class PlayerChooser {

	private Game game;

	public PlayerChooser(Game game) {
		this.game = game;
	}

	public void render(Graphics g) {
		Font fnt = new Font("arial", Font.BOLD, (int) (Game.HEIGHT / 9.6));
		g.setFont(fnt);
		g.setColor(Color.black);
		g.fillRect((int) (Game.WIDTH / 4.923), (int) (Game.HEIGHT / 3.2),
				(int) (Game.WIDTH / 1.6), (int) (Game.HEIGHT / 9.6));
		g.drawString("Customize", (int) (Game.WIDTH / 3.2),
				(int) (Game.HEIGHT / 4.8));

		Font fnt1 = new Font("arial", Font.BOLD, (int) (Game.HEIGHT / 16));
		g.setFont(fnt1);
		g.drawString("Koins: " + game.getTotalCoins(), (int) (Game.WIDTH / 64),
				(int) (Game.HEIGHT / 12));
		g.setColor(Color.white);
		g.drawString("Main Menu", (int) (Game.WIDTH / 4.923)
				+ (int) (Game.WIDTH / 25.6), (int) (Game.HEIGHT / 3.2)
				+ (int) (Game.HEIGHT / 13.714));

		// draws cube show icons
		g.setColor(Color.green);
		g.fillRect((int) (Game.WIDTH / 2.91), (int) (Game.HEIGHT / 1.72),
				(int) (Game.WIDTH / 20), (Game.HEIGHT / 15));
		g.setColor(Color.black);
		g.fillRect((int) (Game.WIDTH / 2.91), (int) (Game.HEIGHT / 2.19),
				(int) (Game.WIDTH / 20), (int) (Game.HEIGHT / 15));
		g.setColor(Color.blue);
		g.fillRect((int) (Game.WIDTH / 2.91), (int) (Game.HEIGHT / 1.41),
				(int) (Game.WIDTH / 20), (Game.HEIGHT / 15));

		// find selected one and fills green

		g.setColor(Color.green);
		if (game.isBlackselected() == true)
			g.fillRect((int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 2.28),
					(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 9.6));
		if (game.isBlueselected() == true)
			g.fillRect((int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 1.45),
					(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 9.6));
		if (game.isGreenselected() == true)
			g.fillRect((int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 1.77),
					(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 9.6));

		// finds locked ones and fills red

		g.setColor(Color.red);
		if (game.isGreenunlocked() == false)
			g.fillRect((int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 1.77),
					(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 9.6));
		if (game.isBlueunlocked() == false)
			g.fillRect((int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 1.45),
					(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 9.6));

		// finds if not locked and not selected and draws black

		g.setColor(Color.black);
		if (game.isBlackselected() == false)
			g.fillRect((int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 2.28),
					(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 9.6));
		if (game.isBlueselected() == false && game.isBlueunlocked() == true)
			g.fillRect((int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 1.45),
					(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 9.6));
		if (game.isGreenselected() == false && game.isGreenunlocked() == true)
			g.fillRect((int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 1.77),
					(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 9.6));

		// finds if unlocked then sets string to name

		g.setFont(fnt1);
		g.setColor(Color.white);
		g.drawString("Black Cube", (int) (Game.WIDTH / 64)
				+ (int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 2.28)
				+ (int) (Game.HEIGHT / 13.714));
		if (game.isBlueunlocked() == true)
			g.drawString("Blue Cube", (int) (Game.WIDTH / 64)
					+ (int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 1.45)
					+ (int) (Game.HEIGHT / 13.714));
		if (game.isGreenunlocked() == true)
			g.drawString("Green Cube", (int) (Game.WIDTH / 64)
					+ (int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 1.77)
					+ (int) (Game.HEIGHT / 13.714));

		// finds if locked then sets price

		g.setFont(fnt1);
		g.setColor(Color.white);
		if (game.isBlueunlocked() == false)
			g.drawString("100 Coins", (int) (Game.WIDTH / 64)
					+ (int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 1.45)
					+ (int) (Game.HEIGHT / 13.714));
		if (game.isGreenunlocked() == false)
			g.drawString("100 Coins", (int) (Game.WIDTH / 64)
					+ (int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 1.77)
					+ (int) (Game.HEIGHT / 13.714));
	}
}
