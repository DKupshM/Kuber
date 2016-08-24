package com.kupsh.Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;

public class Player extends GameObject {
	private int ticks = 0;
	private boolean goingUp = false;
	private Handler handler;
	private HUD hud;
	private Game game;
	private HashMap<String, AudioPlayer> sfx;
	private boolean playenemy;
	private boolean playcoin;
	private int enemyi;
	private int coini;

	public Player(int x, int y, ID id, Handler handler, HUD hud, Game game) {
		super(x, y, id);
		velx = 0;
		vely = 3;
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.playenemy = true;
		this.playcoin = true;
		sfx = new HashMap<String, AudioPlayer>();
		sfx.put("coin", new AudioPlayer("/SFX/Pickup_Coin.mp3"));
		sfx.put("enemy", new AudioPlayer("/SFX/Hit_Hurt.mp3"));
	}

	public void tick() {
		x += velx;
		y += vely;
		if (playenemy == false)
			enemyi++;

		if (enemyi == 24 & playenemy == false) {
			playenemy = true;
			enemyi = 0;
		}
		if (playcoin == false)
			coini++;

		if (coini == 24 & playcoin == false) {
			playcoin = true;
			coini = 0;
		}

		y = Game.clamp(y, 0, (int) ((Game.HEIGHT) - (int) (Game.HEIGHT / 8.9)));

		collision();
		
		if(goingUp){
			vely = (int) -(Game.HEIGHT/32);
		}

		if (!goingUp) {
			if(game.isPaused() == false){
				ticks++;
			}
			if (ticks <= 20) {
				vely = (int) (Game.HEIGHT/160);
			}
			if (ticks <= 30 && ticks > 20) {
				vely = (int) (Game.HEIGHT/80);
			}
			if (ticks >= 40 && ticks > 30) {
				vely = (int) (Game.HEIGHT/53.33);
			}
			if (ticks > 40) {
				vely = (int) (Game.HEIGHT/40);
			}
		} else {
			ticks = 0;
		}
	}

	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.BasicEnemy) {
				if (getbounds().intersects(tempObject.getbounds())) {
					HUD.Health -= 2;
					if(game.isSoundOn() == true){
						if (playenemy == true) {
							sfx.get("enemy").play();
							playenemy = false;
						}
					}
				}
			}
			if (tempObject.getId() == ID.Coin) {
				if (getbounds().intersects(tempObject.getbounds())) {
					hud.setScore(hud.getScore() + 100);
					hud.setCoins(hud.getCoins() + 1);
					game.setTotalCoins(game.getTotalCoins() + 1);
					handler.removeObject(tempObject);
					if(game.isSoundOn() == true){
						if (playcoin == true) {
							sfx.get("coin").play();
							playcoin = false;
						}
					}
				}
			}
		}
	}

	public void render(Graphics g) {
		if (game.isBlackselected() == true) {
			g.setColor(Color.black);
			g.fillRect(x, y, Game.HEIGHT / 15, Game.HEIGHT / 15);
		}
		if (game.isBlueselected() == true) {
			g.setColor(Color.blue);
			g.fillRect(x, y, Game.HEIGHT / 15, Game.HEIGHT / 15);
		}
		if (game.isGreenselected() == true) {
			g.setColor(Color.green);
			g.fillRect(x, y, Game.HEIGHT / 15, Game.HEIGHT / 15);
		}
		g.setColor(Color.black);
		g.drawRect(x, y, Game.HEIGHT / 15, Game.HEIGHT / 15);
	}

	public boolean isGoingUp() {
		return goingUp;
	}

	public void setGoingUp(boolean goingUp) {
		this.goingUp = goingUp;
	}

	public Rectangle getbounds() {
		return new Rectangle(x, y, Game.HEIGHT / 15, Game.HEIGHT / 15);
	}

}
