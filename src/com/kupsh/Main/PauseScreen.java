package com.kupsh.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class PauseScreen {

	public PauseScreen() {
	}

	public void render(Graphics g) {

		Font fnt = new Font("arial", Font.BOLD, (int) (Game.HEIGHT / 9.6));
		g.setFont(fnt);
		g.setColor(Color.black);
		g.fillRect((int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 9.6),
				(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 6.85));
		;
		g.setColor(Color.white);
		g.drawString("Paused", (int) (Game.WIDTH / 3.2)
				+ (int) (Game.HEIGHT / 64), (int) (Game.HEIGHT / 9.6)
				+ (int) (Game.HEIGHT / 8.72));

		Font fnt1 = new Font("arial", Font.BOLD, (int) (Game.HEIGHT / 16));
		g.setFont(fnt1);
		g.setColor(Color.black);
		g.fillRect((int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 2.4),
				(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 8));
		g.fillRect((int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 1.77),
				(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 8));
		g.fillRect((int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 1.41),
				(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 8));
		g.setColor(Color.white);
		g.drawString("Resume", (int) (Game.WIDTH / 3.2)
				+ (int) (Game.WIDTH / 16), (int) (Game.HEIGHT / 2.4)
				+ (int) (Game.HEIGHT / 12));
		g.drawString("Main Menu", (int) (Game.WIDTH / 3.2)
				+ (int) (Game.WIDTH / 25.6), (int) (Game.HEIGHT / 1.77)
				+ (int) (Game.HEIGHT / 12));
		g.drawString("Quit", (int) (Game.WIDTH / 3.2)
				+ (int) (Game.WIDTH / 10.66), (int) (Game.HEIGHT / 1.41)
				+ (int) (Game.HEIGHT / 12));
	}
}
