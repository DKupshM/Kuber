package com.kupsh.Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {

	private Handler handler;

	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velx = (int) -(Game.WIDTH/64);
		vely = 0;
	}

	public void tick() {
		y += vely;
		x += velx;
		y = Game.clamp(y, 0, (int) ((Game.HEIGHT) - (int) (Game.HEIGHT / 8.9)));
		remove();
		coinCollision();
	}

	private void coinCollision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Coin) {
				if (getbounds().intersects(tempObject.getbounds())) {
					handler.object.remove(tempObject);
				}
			}

		}
	}

	private void remove() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.BasicEnemy) {
				if (tempObject.getX() < -32) {
					handler.removeObject(tempObject);
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, Game.HEIGHT / 15, Game.HEIGHT / 15);
		g.setColor(Color.black);
		g.drawRect(x, y, Game.HEIGHT / 15, Game.HEIGHT / 15);
	}

	public Rectangle getbounds() {
		return new Rectangle(x, y, Game.HEIGHT / 15, Game.HEIGHT / 15);
	}
}