package com.kupsh.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Customize {

	private Game game;

	public Customize(Game game) {
		this.game = game;
	}

	public void render(Graphics g) {

		Font fnt = new Font("arial", Font.BOLD, (int) (Game.HEIGHT / 9.6));
		g.setFont(fnt);
		g.setColor(Color.black);
		g.fillRect((int) (Game.WIDTH / 4.923), (int) (Game.HEIGHT / 3.2),
				(int) (Game.WIDTH / 1.6), (int) (Game.HEIGHT / 9.6));
		g.fillRect((int) (Game.WIDTH / 4.923), (int) (Game.HEIGHT / 2.28),
				(int) (Game.WIDTH / 1.6), (int) (Game.HEIGHT / 9.6));
		g.fillRect((int) (Game.WIDTH / 4.923), (int) (Game.HEIGHT / 1.77),
				(int) (Game.WIDTH / 1.6), (int) (Game.HEIGHT / 9.6));
		g.drawString("Customize", (int) (Game.WIDTH / 3.2),
				(int) (Game.HEIGHT / 4.8));
		Font fnt1 = new Font("arial", Font.BOLD, (int) (Game.HEIGHT / 16));
		g.setColor(Color.white);
		g.setFont(fnt1);
		g.drawString("Main Menu", (int) (Game.WIDTH / 4.923)
				+ (int) (Game.WIDTH / 6.4), (int) (Game.HEIGHT / 3.2)
				+ (int) (Game.HEIGHT / 13.7));
		g.setFont(fnt1);
		g.drawString("Cube: " + game.getCube(), (int) (Game.WIDTH / 4.923)
				+ (int) (Game.WIDTH / 6.4), (int) (Game.HEIGHT / 2.28)
				+ (int) (Game.HEIGHT / 13.71));
		g.setFont(fnt1);
		g.drawString("Background: " + game.getBackround(),
				(int) (Game.WIDTH / 4.923) + (int) (Game.WIDTH / 64),
				(int) (Game.HEIGHT / 1.77) + (int) (Game.HEIGHT / 13.71));
	}
}
