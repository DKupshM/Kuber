package com.kupsh.Main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput extends MouseAdapter implements MouseListener {

	private Game game;
	private Spawn spawn;
	private State game_state;
	private HUD hud;
	private boolean testing1;

	public MouseInput(Game game, Spawn spawn, HUD hud) {
		this.game = game;
		this.spawn = spawn;
		this.hud = hud;
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		this.game_state = game.getGame_State();
		if (game_state == State.MainMenu) {
			if (mx >= (int) (Game.WIDTH / 2.8)
					&& mx <= (int) (Game.WIDTH / 1.49)) {

				// playButton
				if (my >= (int) (Game.HEIGHT / 3.2)
						&& my <= (int) (Game.HEIGHT / 2.4))
					game.setGame_State(State.Game);

				// customizeButton
				if (my >= (int) (Game.HEIGHT / 2.3)
						&& my <= (int) (Game.HEIGHT / 1.8))
					game.setGame_State(State.Customize);

				// optionsButton
				if (my >= (int) (Game.HEIGHT / 1.8)
						&& my <= (int) (Game.HEIGHT / 1.5)) {
					game.setGame_State(State.Options);
				}

				// quitButton
				if (my >= (int) (Game.HEIGHT / 1.47)
						&& my <= (int) (Game.HEIGHT / 1.27))
					System.exit(0);
			}
			//Testing Mode
			if(my >= (int) (Game.HEIGHT / 6.25) && my <= (int) (Game.HEIGHT/5.172)){
				if(testing1 == false){
					if(mx >= (int) (Game.WIDTH / 2.23) && mx <= (int) (Game.WIDTH/2.11))
						testing1 = true;
				}
				if(testing1 == true){
					if(mx >= (int) (Game.WIDTH/2) && mx <= Game.WIDTH/1.904)
						game.setTestingmode(true);
				
				}
			}
		} else if (game_state == State.Game) {
			if (game.isPaused() == false) {
				if (mx >= 0 && mx <= 75) {
					if (my >= (int) (Game.HEIGHT - 50)
							&& my <= (int) (Game.HEIGHT)) {
						game.setPaused(true);
					}

				}
			} else if (game.isPaused()) {
				if (mx >= (int) (Game.WIDTH / 3.04)
						&& mx <= (int) (Game.WIDTH / 1.56)) {
					if (my >= (int) (Game.HEIGHT / 200)
							&& my <= (int) (Game.HEIGHT / 1.846)) {
						game.setPaused(false);
					}
					if (my >= (int) (Game.HEIGHT / 1.77)
							&& my <= (int) (Game.HEIGHT / 1.45)) {
						game.setPaused(false);
						HUD.Health = 100;
						spawn.setTicks(0);
						spawn.setLevel(1);
						hud.setScore(0);
						hud.setCoins(0);
						game.addInfo("Coins", game.getTotalCoins());
						game.setGame_State(State.MainMenu);
						game.setAddplayer(true);
						game.removeall();

					}
					if (my >= (int) (Game.HEIGHT / 1.411)
							&& my <= (int) (Game.HEIGHT / 1.2)) {
						System.exit(1);
					}
				}
			}
		} else if (game_state == State.GameOver) {
			if (mx >= (int) (Game.WIDTH / 2.8)
					&& mx <= (int) (Game.WIDTH / 1.49)) {
				// mainmenu
				if (my >= (int) (Game.HEIGHT / 3.2)
						&& my <= (int) (Game.HEIGHT / 2.4)) {
					HUD.Health = 100;
					spawn.setTicks(0);
					spawn.setLevel(1);
					hud.setScore(0);
					hud.setCoins(0);
					game.setGame_State(State.MainMenu);
					game.setAddplayer(true);
					game.removeall();
				}

			}
		} else if (game_state == State.Options) {
			if (mx >= (int) (Game.WIDTH / 4.923)
					&& mx <= (int) (Game.WIDTH / 1.207)) {
				if (my >= (int) (Game.HEIGHT / 3.2)
						&& my <= (int) (Game.HEIGHT / 2.4))
					game.setGame_State(State.MainMenu);
				else if (my >= (int) (Game.HEIGHT / 2.28)
						&& my <= (int) (Game.HEIGHT / 1.846)) {
					if (game.isShowfps() == false)
						game.setShowfps(true);
						
					else if (game.isShowfps() == true)
						game.setShowfps(false);
					game.addInfo("Showfps", game.isShowfps());
				} else if (my >= (int) (Game.HEIGHT / 1.77)
						&& my <= (Game.HEIGHT / 1.454)) {
					if (game.isShowtps() == false) 
						game.setShowtps(true);
					else if (game.isShowtps() == true) 
						game.setShowtps(false);
					game.addInfo("Showtps", game.isShowtps());
				} else if(my >= (int) (Game.HEIGHT/1.449) && my <= (int) (Game.HEIGHT/1.259)){
					if(game.isSoundOn() == false)
						game.setSoundOn(true);
					else if(game.isSoundOn() == true) 
						game.setSoundOn(false);
					game.addInfo("SoundOn", game.isSoundOn());
				}
			}
		} else if (game_state == State.Customize) {
			if (mx >= (int) (Game.WIDTH / 4.923)
					&& mx <= (int) (Game.WIDTH / 1.207)) {
				// mainmenu
				if (my >= (int) (Game.HEIGHT / 3.2)
						&& my <= (int) (Game.HEIGHT / 2.4))
					game.setGame_State(State.MainMenu);
				// player chooser
				if (my >= (int) (Game.HEIGHT / 2.28)
						&& my <= (int) (Game.HEIGHT / 1.842))
					game.setGame_State(State.PlayerChooser);
				// back ground
				if (my >= (int) (Game.HEIGHT / 1.77)
						&& my <= (int) (Game.HEIGHT / 1.49))
					game.setGame_State(State.Background);
			}
		} else if (game_state == State.Background) {
			if (mx >= (int) (Game.WIDTH / 4.923)
					&& mx <= (int) (Game.WIDTH / 1.207)) {
				// mainmenu
				if (my >= (int) (Game.HEIGHT / 3.2)
						&& my <= (int) (Game.HEIGHT / 2.4)) {
					game.setGame_State(State.MainMenu);
				}
			}
			if (mx >= (int) (Game.WIDTH / 64)
					&& mx <= (int) (Game.WIDTH / 3.04)) {
				if (my >= (int) (Game.HEIGHT / 2.28)
						&& my <= (int) (Game.HEIGHT / 1.846)) {
					if (game.isDefaultselected() == false) {
						game.setDefaultselected(true);
						game.setUnderwaterselected(false);
						game.setSkyselected(false);
						game.addInfo("SkySelected", game.isSkyselected());
						game.addInfo("DefaultSelected",
								game.isDefaultselected());
						game.addInfo("UnderWaterSelected",
								game.isUnderwaterselected());

					}
				}
				if (my >= (int) (Game.HEIGHT / 1.77)
						&& my <= (int) (Game.HEIGHT / 1.5)) {
					if (game.isSkyunlocked() == false) {
						if (game.getTotalCoins() >= 1000) {
							game.setTotalCoins(game.getTotalCoins() - 1000);
							game.setSkyunlocked(true);
							game.setDefaultselected(false);
							game.setSkyselected(true);
							game.setUnderwaterselected(false);
							game.addInfo("SkyUnlocked", game.isSkyunlocked());
							game.addInfo("SkySelected", game.isSkyselected());
							game.addInfo("DefaultSelected",
									game.isDefaultselected());
							game.addInfo("UnderWaterSelected",
									game.isUnderwaterselected());
						}
					} else if (game.isSkyunlocked() == true) {
						game.setSkyselected(true);
						game.setDefaultselected(false);
						game.setUnderwaterselected(false);
						game.addInfo("SkySelected", game.isSkyselected());
						game.addInfo("DefaultSelected",
								game.isDefaultselected());
						game.addInfo("UnderWaterSelected",
								game.isUnderwaterselected());
					}
				}
				if (my >= (int) (Game.HEIGHT / 1.454)
						&& my <= (int) (Game.HEIGHT / 1.26)) {
					if (game.isUnderwaterunlocked() == false) {
						if (game.getTotalCoins() >= 1000) {
							game.setTotalCoins(game.getTotalCoins() - 1000);
							game.setUnderwaterunlocked(true);
							game.setDefaultselected(false);
							game.setSkyselected(false);
							game.setUnderwaterselected(true);
							game.addInfo("UnderWaterUnlocked",
									game.isUnderwaterunlocked());
							game.addInfo("SkySelected", game.isSkyselected());
							game.addInfo("DefaultSelected",
									game.isDefaultselected());
							game.addInfo("UnderWaterSelected",
									game.isUnderwaterselected());

						}
					} else if (game.isUnderwaterunlocked() == true) {
						game.setUnderwaterselected(true);
						game.setDefaultselected(false);
						game.setSkyselected(false);
						game.addInfo("SkySelected", game.isSkyselected());
						game.addInfo("DefaultSelected",
								game.isDefaultselected());
						game.addInfo("UnderWaterSelected",
								game.isUnderwaterselected());

					}
				}
			}
		} else if (game_state == State.PlayerChooser) {
			if (mx >= (int) (Game.WIDTH / 4.923)
					&& mx <= (int) (Game.WIDTH / 1.207)) {
				if (my >= (int) (Game.HEIGHT / 3.2)
						&& my <= (int) (Game.HEIGHT / 2.4)) {
					game.setGame_State(State.MainMenu);
				}
			}
			if (mx >= (int) (Game.WIDTH / 64)
					&& mx <= (int) (Game.WIDTH / 3.04)) {
				if (my >= (int) (Game.HEIGHT / 2.28)
						&& my <= (int) (Game.HEIGHT / 1.846)) {
					if (game.isBlackselected() == false) {
						game.setBlackselected(true);
						game.setBlueselected(false);
						game.setGreenselected(false);
						game.addInfo("BlackSelected", game.isBlackselected());
						game.addInfo("BlueSelected", game.isBlueselected());
						game.addInfo("GreenSelected", game.isGreenselected());
					}
				}
				if (my >= (int) (Game.HEIGHT / 1.77)
						&& my <= (int) (Game.HEIGHT / 1.5)) {
					if (game.isGreenunlocked() == false) {
						if (game.getTotalCoins() >= 100) {
							game.setTotalCoins(game.getTotalCoins() - 100);
							game.setGreenunlocked(true);
							game.setBlackselected(false);
							game.setGreenselected(true);
							game.setBlueselected(false);
							game.addInfo("GreenUnlocked",
									game.isGreenunlocked());
							game.addInfo("BlackSelected",
									game.isBlackselected());
							game.addInfo("BlueSelected", game.isBlueselected());
							game.addInfo("GreenSelected",
									game.isGreenselected());
						}
					} else if (game.isGreenunlocked() == true) {
						game.setGreenselected(true);
						game.setBlackselected(false);
						game.setBlueselected(false);
						game.addInfo("BlackSelected", game.isBlackselected());
						game.addInfo("BlueSelected", game.isBlueselected());
						game.addInfo("GreenSelected", game.isGreenselected());
					}
				}
				if (my >= (int) (Game.HEIGHT / 1.454)
						&& my <= (int) (Game.HEIGHT / 1.26)) {
					if (game.isBlueunlocked() == false) {
						if (game.getTotalCoins() >= 100) {
							game.setTotalCoins(game.getTotalCoins() - 100);
							game.setBlueunlocked(true);
							game.setBlackselected(false);
							game.setGreenselected(false);
							game.setBlueselected(true);
							game.addInfo("BlueUnlocked", game.isBlueunlocked());
							game.addInfo("BlackSelected",
									game.isBlackselected());
							game.addInfo("BlueSelected", game.isBlueselected());
							game.addInfo("GreenSelected",
									game.isGreenselected());
						}
					} else if (game.isBlueunlocked() == true) {
						game.setBlueselected(true);
						game.setBlackselected(false);
						game.setGreenselected(false);
						game.addInfo("BlackSelected", game.isBlackselected());
						game.addInfo("BlueSelected", game.isBlueselected());
						game.addInfo("GreenSelected", game.isGreenselected());
					}
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}
