package com.kupsh.Main;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class ComponentInput implements ComponentListener {

	private Game game;
	
	public ComponentInput(Game game) {
		this.game = game;
	}

	public void componentResized(ComponentEvent e) {
		Game.HEIGHT = e.getComponent().getHeight();
		Game.WIDTH = e.getComponent().getWidth();
		if(game.getGame_State() == State.Game){
			game.setPaused(true);
			game.updateVel();
		}
		System.out.println("New demensions are: " + e.getComponent().getHeight() + " x " + e.getComponent().getWidth());
	}

	public void componentMoved(ComponentEvent e) {
		if(game.getGame_State() == State.Game){
			game.setPaused(true);
		}
	}

	public void componentShown(ComponentEvent e) {
	}
	public void componentHidden(ComponentEvent e) {
		if(game.getGame_State() == State.Game){
			game.setPaused(true);
		}
	}
}
