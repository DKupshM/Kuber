package com.kupsh.Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Coin extends GameObject {

	private Handler handler;
	private BufferedImage Koin;

	public Coin(int x, int y, ID id, Handler handler, BufferedImage Koin) {
		super(x, y, id);
		this.Koin = Koin;
		this.handler = handler;
		velx = (int) -(Game.WIDTH/64);
		vely = 0;
	}

	public void tick() {
		y += vely;
		x += velx;

		y = Game.clamp(y, 0, (int) ((Game.HEIGHT) - (int) (Game.HEIGHT / 8.9)));

		remove();
	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, Game.HEIGHT / 15, Game.HEIGHT / 15);
		g.setColor(Color.black);
		g.drawImage(Koin, x, y, Game.HEIGHT / 15, Game.HEIGHT / 15, null);
		g.drawRect(x, y, Game.HEIGHT / 15, Game.HEIGHT / 15);
	}

	private void remove() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Coin) {
				if (tempObject.getX() < -32) {
					handler.removeObject(tempObject);
				}
			}
		}
	}

	public Rectangle getbounds() {
		return new Rectangle(x, y, Game.HEIGHT / 15, Game.HEIGHT / 15);
	}
}
