package com.kupsh.Main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static int WIDTH = 640;
	public static int HEIGHT = 480;
	public static final String TITLE = "Kuber";

	private boolean running = false;
	private Thread thread;
	// private BufferedImage spriteSheet;
	private BufferedImage Koin;
	private BufferedImage backGround;
	private BufferedImage sky;
	private BufferedImage Underwater;
	private Handler handler;
	private Player p;
	private HUD hud;
	private Spawn spawn;
	private State Game_State = State.LoadingScreen;
	private Menu menu;
	private GameOver gameover;
	private Random r;
	private Customize c;
	private PlayerChooser pc;
	// private SpriteSheet ss;
	private BackGround bg;
	private Options o;
	private Sqlite sql;
	private Pauseselecter ps;
	private PauseScreen pscrn;
	private LoadingScreen ls;
	private BufferedImageLoader loader;

	private int frames1;
	private int updates1;
	private int frames;
	private int updates;

	private boolean paused = false;
	private boolean resized = false;
	private boolean testingmode = false;

	private boolean showfps;
	private boolean showtps;
	private boolean soundOn = true;

	private int TotalCoins;
	private int HighScore;
	private String Backround = "default";
	private String Trail = "none";
	private String cube = "Black";

	private boolean greenunlocked;
	private boolean blueunlocked;
	private boolean blackselected;
	private boolean greenselected;
	private boolean blueselected;

	private boolean defaultselected;
	private boolean skyselected;
	private boolean underwaterselected;

	private boolean skyunlocked;
	private boolean underwaterunlocked;

	private boolean coinboolean = false;
	private boolean addplayer = true;

	public void init() {
		sql = new Sqlite(this);
		sql.Connected();

		// initialize variables according to sql

		this.showfps = sql.isShowfps();
		this.showtps = sql.isShowtps();
		this.greenunlocked = sql.isGreenunlocked();
		this.blueunlocked = sql.isBlueunlocked();
		this.blackselected = sql.isBlackselected();
		this.greenselected = sql.isGreenselected();
		this.blueselected = sql.isBlueselected();
		this.defaultselected = sql.isDefaultselected();
		this.skyselected = sql.isSkyselected();
		this.underwaterselected = sql.isUnderwaterselected();
		this.skyunlocked = sql.isSkyunlocked();
		this.underwaterunlocked = sql.isUnderwaterunlocked();
		this.soundOn = sql.isSoundOn();

		this.TotalCoins = sql.getTotalCoins();
		this.HighScore = sql.getHighScore();

		handler = new Handler();
		loader = new BufferedImageLoader();
		menu = new Menu(this);
		r = new Random();
		pc = new PlayerChooser(this);
		hud = new HUD(this);
		c = new Customize(this);
		bg = new BackGround(this);
		o = new Options(this);
		ps = new Pauseselecter(this);
		pscrn = new PauseScreen();
		spawn = new Spawn(handler, r, Koin);
		p = new Player(HEIGHT / 2, WIDTH / 2, ID.Player, handler, hud, this);
		gameover = new GameOver(hud, spawn, this);
		this.setFocusTraversalKeysEnabled(false);
		this.addKeyListener(new KeyInput(handler, p, this));
		this.addMouseListener(new MouseInput(this, spawn, hud));
		try {
			Koin = loader.loadImage("/Entity/koin.png");
			// spriteSheet = loader.loadImage("/Entity/SpriteSheet.png");
			backGround = loader.loadImage("/Backgrounds/Backround.png");
			sky = loader.loadImage("/Backgrounds/sky.png");
			Underwater = loader.loadImage("/Backgrounds/Underwater.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ss = new SpriteSheet(spriteSheet);

		if (defaultselected == false && skyselected == false
				&& underwaterselected == false)
			defaultselected = true;
		if (blackselected == false && blueselected == false
				&& greenselected == false)
			blackselected = true;
	}

	private synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		ls = new LoadingScreen(this);
		thread = new Thread(this);
		thread.start();
	}

	private synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	public void run() {
		long lastTime = System.nanoTime();
		final double amountOfTicks = 20.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		updates1 = 0;
		frames1 = 0;
		long timer = System.currentTimeMillis();
		// Game loop
		// tick is 20 a second
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates1++;
				delta--;
			}
			render();
			frames1++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				updates = updates1;
				frames = frames1;
				updates1 = 0;
				frames1 = 0;
			}
		}
		stop();
	}

	private void tick() {
		if(Game_State == State.LoadingScreen)
			ls.tick();
		else if (Game_State == State.Game) {
			if (paused == false) {
				handler.tick();
				hud.tick();
				spawn.tick();
				p.tick();
			}
		}else if (Game_State == State.GameOver) {
			if (coinboolean == false) {
				addInfo("HighScore", HighScore);
				addInfo("Coins", TotalCoins);
				coinboolean = true;
			}

		} else if (Game_State == State.MainMenu) {
			if (coinboolean == true) {
				coinboolean = false;
			}
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		if (defaultselected == true) {
			g.drawImage(backGround, 0, 0, getWidth(), getHeight(), null);
		} else if (skyselected == true) {
			g.drawImage(sky, 0, 0, getWidth(), getHeight(), null);
		} else if (underwaterselected == true) {
			g.drawImage(Underwater, 0, 0, getWidth(), getHeight(), null);
		}
		if(Game_State == State.LoadingScreen){
			ls.render(g);
		}
		if (Game_State == State.Game) {
			if (addplayer == true) {
				handler.addObject(p);

				addplayer = false;
			}
			ps.render(g);
			handler.render(g);
			p.render(g);
			hud.render(g);
			if (paused) {
				pscrn.render(g);
			}
		}
		if (Game_State == State.MainMenu) {
			menu.render(g);
			if (blackselected == true) {
				cube = "Black";
			} else if (greenselected == true) {
				cube = "Green";
			} else if (blueselected == true) {
				cube = "Blue";
			}
			if (defaultselected == true) {
				Backround = "Default";
			} else if (skyselected == true) {
				Backround = "Sky";
			} else if (underwaterselected == true) {
				Backround = "Underwater";
			}
		} else if (Game_State == State.GameOver) {
			gameover.render(g);
			removeall();
		} else if (Game_State == State.Customize) {
			c.render(g);
		} else if (Game_State == State.Options) {
			o.render(g);
		} else if (Game_State == State.PlayerChooser) {
			pc.render(g);
		} else if (Game_State == State.Background) {
			bg.render(g);
		}
		g.dispose();
		bs.show();
	}
	
	public void updateVel(){
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Coin || tempObject.getId() == ID.BasicEnemy) {
				tempObject.setVelx((int) -(Game.WIDTH/64));
			}
		}
	}

	public static int clamp(int var, int min, int max) {
		if (var > +max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

	public void removeall() {
		if(handler.object.size() == 0)
			return;
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.Coin || tempObject.getId() == ID.Player)
				handler.removeObject(tempObject);
		}
		if(1 <= handler.object.size())
			removeall();
		else if(handler.object.size() == 0)
			return;
	}

	public static void main(String args[]) {
		Game game = new Game();
		Window window = new Window();
		window.window(TITLE, game);
		game.start();
	}

	public void addInfo(String variable, int Value) {
		sql.insert(variable, Value);
		System.out.println(variable + " = " + Value);
	}

	public void addInfo(String variable, boolean truefalse) {
		sql.insert(variable, truefalse);
		System.out.println(variable + " = " + truefalse);
	}

	public int getFrames() {
		return frames;
	}

	public void setFrames(int frames) {
		this.frames = frames;
	}

	public int getUpdates() {
		return updates;
	}

	public void setUpdates(int updates) {
		this.updates = updates;
	}

	public State getGame_State() {
		return Game_State;
	}

	public void setGame_State(State game_State) {
		Game_State = game_State;
	}

	public int getTotalCoins() {
		return TotalCoins;
	}

	public void setTotalCoins(int totalCoins) {
		TotalCoins = totalCoins;
	}

	public String getBackround() {
		return Backround;
	}

	public void setBackround(String backround) {
		Backround = backround;
	}

	public String getTrail() {
		return Trail;
	}

	public void setTrail(String trail) {
		Trail = trail;
	}

	public String getCube() {
		return cube;
	}

	public void setCube(String cube) {
		this.cube = cube;
	}

	public int getHighScore() {
		return HighScore;
	}

	public void setHighScore(int highScore) {
		HighScore = highScore;
	}

	public boolean isGreenunlocked() {
		return greenunlocked;
	}

	public void setGreenunlocked(boolean greenunlocked) {
		this.greenunlocked = greenunlocked;
	}

	public boolean isBlueunlocked() {
		return blueunlocked;
	}

	public void setBlueunlocked(boolean blueunlocked) {
		this.blueunlocked = blueunlocked;
	}

	public boolean isBlackselected() {
		return blackselected;
	}

	public void setBlackselected(boolean blackselected) {
		this.blackselected = blackselected;
	}

	public boolean isGreenselected() {
		return greenselected;
	}

	public void setGreenselected(boolean greenselected) {
		this.greenselected = greenselected;
	}

	public boolean isBlueselected() {
		return blueselected;
	}

	public void setBlueselected(boolean blueselected) {
		this.blueselected = blueselected;
	}

	public boolean isDefaultselected() {
		return defaultselected;
	}

	public void setDefaultselected(boolean defaultselected) {
		this.defaultselected = defaultselected;
	}

	public boolean isSkyselected() {
		return skyselected;
	}

	public void setSkyselected(boolean skyselected) {
		this.skyselected = skyselected;
	}

	public boolean isUnderwaterselected() {
		return underwaterselected;
	}

	public void setUnderwaterselected(boolean underwaterselected) {
		this.underwaterselected = underwaterselected;
	}

	public boolean isSkyunlocked() {
		return skyunlocked;
	}

	public void setSkyunlocked(boolean skyunlocked) {
		this.skyunlocked = skyunlocked;
	}

	public boolean isUnderwaterunlocked() {
		return underwaterunlocked;
	}

	public void setUnderwaterunlocked(boolean underwaterunlocked) {
		this.underwaterunlocked = underwaterunlocked;
	}

	public boolean isShowfps() {
		return showfps;
	}

	public void setShowfps(boolean showfps) {
		this.showfps = showfps;
	}

	public boolean isShowtps() {
		return showtps;
	}

	public void setShowtps(boolean showtps) {
		this.showtps = showtps;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public boolean isAddplayer() {
		return addplayer;
	}

	public void setAddplayer(boolean addplayer) {
		this.addplayer = addplayer;
	}

	public boolean isResized() {
		return resized;
	}

	public void setResized(boolean resized) {
		this.resized = resized;
	}

	public boolean isSoundOn() {
		return soundOn;
	}

	public void setSoundOn(boolean soundOn) {
		this.soundOn = soundOn;
	}

	public boolean isTestingmode() {
		return testingmode;
	}

	public void setTestingmode(boolean testingmode) {
		this.testingmode = testingmode;
	}
}