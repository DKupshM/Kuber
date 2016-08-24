package com.kupsh.Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class LoadingScreen {
		
	private Game game;
	
	private int ticks;
	private boolean initialized;
	private BufferedImage image;
	
	private HashMap<String, BufferedImage> anim;
	
	
	private int loadingamount = 3;
	
	public LoadingScreen(Game game){
		this.game = game;
		for(int i = 1; i <= 21; i++){
			BufferedImage temp = load("/Anim/" + i + ".jpg");
			anim.put(Integer.toString(i), temp);
		}
	}
	public void tick(){
		ticks++;
		loadingamount += 3;
		if(ticks == 200){
			game.setGame_State(State.MainMenu);
		}
		if(initialized == false && ticks == 100){
			game.init();
			initialized = true;
		}
	}
	public void render(Graphics g){
		int temp = ticks/2;
		System.out.println("hi");
		if(temp >= 20)
			temp = 20;
		g.drawImage(anim.get(temp), 0, 0, Game.WIDTH, Game.HEIGHT, null);
		
		g.setColor(Color.BLACK);
		g.drawRect((int) (Game.WIDTH/64), (int) (Game.HEIGHT/48), (int) (Game.WIDTH/1.066),(int) (Game.HEIGHT/19.2));
		g.fillRect((int) (Game.WIDTH/64), (int) (Game.HEIGHT/48), (int) (loadingamount * (Game.WIDTH/640)), (int) (Game.HEIGHT/19.2));
	}
	private BufferedImage load(String path){
		image = null;
		try {
		    image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
