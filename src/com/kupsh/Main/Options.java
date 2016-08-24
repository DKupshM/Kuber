package com.kupsh.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Options {
	private Game game;

	public Options(Game game) {
		this.game = game;
	}

	public void render(Graphics g) {

		Font fnt = new Font("arial", Font.BOLD, (int) (Game.HEIGHT / 9.6));
		g.setFont(fnt);
		g.setColor(Color.black);
		g.drawString("Options", (int) (Game.WIDTH / 3.2),
				(int) (Game.HEIGHT / 4.8));
		g.fillRect((int) (Game.WIDTH / 4.92), (int) (Game.HEIGHT / 3.2),
				(int) (Game.WIDTH / 1.6), (int) (Game.HEIGHT / 9.6));

		g.setColor(Color.green);
		if (game.isShowfps() == true)
			g.fillRect((int) (Game.WIDTH / 4.92), (int) (Game.HEIGHT / 2.28),
					(int) (Game.WIDTH / 1.6), (int) (Game.HEIGHT / 9.6));

		if (game.isShowtps() == true)
			g.fillRect((int) (Game.WIDTH / 4.92), (int) (Game.HEIGHT / 1.77),
					(int) (Game.WIDTH / 1.6), (int) (Game.HEIGHT / 9.6));
		if (game.isSoundOn() == true)
			g.fillRect((int) (Game.WIDTH / 4.92), (int) (Game.HEIGHT/1.449), (int) (Game.WIDTH / 1.6), (int) (Game.HEIGHT / 9.6));
		
		g.setColor(Color.red);
		if (game.isShowfps() == false)
			g.fillRect((int) (Game.WIDTH / 4.92), (int) (Game.HEIGHT / 2.28),
					(int) (Game.WIDTH / 1.6), (int) (Game.HEIGHT / 9.6));
		if (game.isShowtps() == false)
			g.fillRect((int) (Game.WIDTH / 4.92), (int) (Game.HEIGHT / 1.77),
					(int) (Game.WIDTH / 1.6), (int) (Game.HEIGHT / 9.6));
		if(game.isSoundOn() == false)
			g.fillRect((int) (Game.WIDTH / 4.92), (int) (Game.HEIGHT/1.449), (int) (Game.WIDTH / 1.6), (int) (Game.HEIGHT / 9.6));

		Font fnt1 = new Font("arial", Font.BOLD, (int) (Game.HEIGHT / 16));
		g.setColor(Color.white);
		g.setFont(fnt1);
		g.drawString("Main Menu", (int) (Game.WIDTH / 4.92)
				+ (int) (Game.WIDTH / 6.4), (int) (Game.HEIGHT / 3.2)
				+ (int) (Game.HEIGHT / 13.71));
		g.drawString("Show Frames per second ", (int) (Game.WIDTH / 4.92)
				+ (int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 2.28)
				+ (int) (Game.HEIGHT / 13.71));
		g.drawString("Show Ticks per second ", (int) (Game.WIDTH / 4.92)
				+ (int) (Game.WIDTH / 64), (int) (Game.HEIGHT / 1.77)
				+ (int) (Game.HEIGHT / 13.71));
		if(game.isSoundOn() == true)
			g.drawString("Sound On", (int) (Game.WIDTH / 4.92)
				+ (int) (Game.WIDTH / 64), (int) (Game.HEIGHT/1.449)
				+ (int) (Game.HEIGHT / 13.71));
		else if(game.isSoundOn() == false)
			g.drawString("Sound Off", (int) (Game.WIDTH / 4.92)
					+ (int) (Game.WIDTH / 64), (int) (Game.HEIGHT/1.449)
					+ (int) (Game.HEIGHT / 13.71));
	}
}
