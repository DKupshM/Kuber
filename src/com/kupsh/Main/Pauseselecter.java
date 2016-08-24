package com.kupsh.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Pauseselecter {

	private Game game;

	public Pauseselecter(Game game) {
		this.game = game;
	}

	public void render(Graphics g) {

		Font fnt1 = new Font("arial", Font.BOLD, 20);
		g.setFont(fnt1);
		if (game.isPaused() == false) {
			g.setColor(Color.black);
			g.fillRect(0, (int) (Game.HEIGHT - 50),
					75, 50);
			g.setColor(Color.white);
			g.drawString("Pause", 5,
					(int) (Game.HEIGHT - 25));
		}
	}

}
