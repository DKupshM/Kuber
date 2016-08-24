package com.kupsh.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {

	private Game game;

	public Menu(Game game) {
		this.game = game;
	}

	public void render(Graphics g) {

		Font fnt = new Font("arial", Font.BOLD, (int) (Game.HEIGHT / 9.6));
		g.setFont(fnt);
		g.setColor(Color.black);

		g.drawString(Game.TITLE, (int) (Game.WIDTH / 2.6),
				(int) (Game.HEIGHT / 4.8));
		Font fnt1 = new Font("arial", Font.BOLD, (int) (Game.HEIGHT / 16));
		g.setFont(fnt1);
		g.setColor(Color.black);
		g.fillRect((int) (Game.WIDTH / 2.8), (int) (Game.HEIGHT / 3.2),
				(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 9.6));
		g.fillRect((int) (Game.WIDTH / 2.8), (int) (Game.HEIGHT / 2.3),
				(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 9.6));
		g.fillRect((int) (Game.WIDTH / 2.8), (int) (Game.HEIGHT / 1.8),
				(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 9.6));
		g.fillRect((int) (Game.WIDTH / 2.8), (int) (Game.HEIGHT / 1.47),
				(int) (Game.WIDTH / 3.2), (int) (Game.HEIGHT / 9.6));
		g.setColor(Color.white);
		g.drawString("Play", (int) (Game.WIDTH / 2.8)
				+ (int) (Game.WIDTH / 9.14), (int) (Game.HEIGHT / 3.2)
				+ (int) (Game.HEIGHT / 13.71));
		g.drawString("Customize", (int) (Game.WIDTH / 2.8)
				+ (int) (Game.WIDTH / 25.6), (int) (Game.HEIGHT / 2.3)
				+ (int) (Game.HEIGHT / 13.7));
		g.drawString("Options", (int) (Game.WIDTH / 2.8)
				+ (int) (Game.WIDTH / 14.2), (int) (Game.HEIGHT / 1.8)
				+ (int) (Game.HEIGHT / 13.75));
		g.drawString("Quit", (int) (Game.WIDTH / 2.8)
				+ (int) (Game.WIDTH / 9.14), (int) (Game.HEIGHT / 1.47)
				+ (int) (Game.HEIGHT / 13.75));
		g.setColor(Color.black);
		g.drawString("Koins: " + game.getTotalCoins(), Game.WIDTH / 64,
				Game.HEIGHT / 15);
		if(game.isTestingmode() == true){
			g.drawRect((int) (Game.WIDTH / 2.),
					(int) (Game.HEIGHT / 6.25), (int) (Game.WIDTH/40), (int) (Game.HEIGHT/30));
			g.drawRect((int) (Game.WIDTH / 2.23),
					(int) (Game.HEIGHT / 6.25), (int) (Game.WIDTH/40), (int) (Game.HEIGHT/30));
		}
	}
}
