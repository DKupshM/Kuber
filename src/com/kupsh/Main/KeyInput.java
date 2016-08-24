package com.kupsh.Main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	private Handler handler;
	private Player player;
	private State game_state;
	private Game game;

	public KeyInput(Handler handler, Player player, Game game) {
		this.handler = handler;
		this.player = player;
		this.game = game;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		this.game_state = game.getGame_State();
		if (game_state == State.Game) {
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if (tempObject.getId() == ID.Player) {
					if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
						player.setGoingUp(true);
					}
				}
			}
		}
		if(game.isTestingmode()){
			if(key == KeyEvent.VK_O){
				game.setTotalCoins(game.getTotalCoins() + 1000);
				game.addInfo("Coins", game.getTotalCoins());
			}
			if(key == KeyEvent.VK_U){
				if(game.getGame_State() == State.Game){
					if (HUD.Health >= 100){
						HUD.Health = 100;
					}
				}
			}
				if(key == KeyEvent.VK_P){
					game.setTotalCoins(0);
					game.setTestingmode(false);
					game.setDefaultselected(true);
					game.setUnderwaterselected(false);
					game.setSkyselected(false);
					game.setUnderwaterunlocked(false);
					game.setSkyunlocked(false);
					game.setBlackselected(true);
					game.setBlueselected(false);
					game.setGreenselected(false);
					game.setBlueunlocked(false);
					game.setGreenunlocked(false);
					game.setHighScore(0);
					game.setShowfps(false);
					game.setShowtps(false);
					game.setSoundOn(true);
					game.setGame_State(State.MainMenu);
					game.addInfo("Coins", game.getTotalCoins());
					game.addInfo("HighScore", game.getHighScore());
					game.addInfo("ShowFps", game.isShowfps());
					game.addInfo("ShowTps", game.isShowtps());
					game.addInfo("BlackSelected", game.isBlackselected());
					game.addInfo("GreenSelected", game.isGreenselected());
					game.addInfo("BlueSelected", game.isBlueselected());
					game.addInfo("GreenUnlocked", game.isGreenunlocked());
					game.addInfo("BlueUnlocked", game.isBlueunlocked());
					game.addInfo("DefaultSelected", game.isDefaultselected());
					game.addInfo("SkySelected", game.isSkyselected());
					game.addInfo("UnderWaterSelected", game.isUnderwaterselected());
					game.addInfo("SkyUnlocked", game.isSkyunlocked());
					game.addInfo("UnderWaterUnlocked", game.isUnderwaterunlocked());
					game.addInfo("SoundOn", game.isSoundOn());
				
			}
		}
		if(key == KeyEvent.VK_ESCAPE)
			System.exit(1);
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (game_state == State.Game) {
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if (tempObject.getId() == ID.Player) {
					if (key == KeyEvent.VK_W) {
						tempObject.setVely(0);
						player.setGoingUp(false);
					}
				}
			}
		}
	}
}