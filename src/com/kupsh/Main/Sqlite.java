package com.kupsh.Main;

import java.io.File;
import java.io.IOException;
import java.sql.*;

import javax.swing.JOptionPane;

import org.sqlite.SQLiteDataSource;
import org.sqlite.SQLiteJDBCLoader;

public class Sqlite {

	private Game game;
	private boolean initialize = false;
	private String sql;

	private boolean greenunlocked;
	private boolean blueunlocked;
	private boolean blackselected = true;
	private boolean greenselected;
	private boolean blueselected;

	private boolean defaultselected = true;
	private boolean skyselected;
	private boolean underwaterselected;

	private boolean skyunlocked;
	private boolean underwaterunlocked;

	private boolean showfps;
	private boolean showtps;
	private boolean soundOn;

	private int TotalCoins = 0;
	private int HighScore = 0;

	private File file;
	private File file1;
	private File file2;

	public Sqlite(Game game) {
		this.game = game;
		file = new File(System.getProperty("user.dir", "/Kuber"));
		file1 = new File(file + "/saves");
		file2 = new File(file1 + "/Kuber.sqlite");
		sql = "jdbc:sqlite:" + file2.getAbsolutePath();
		exists();
	}

	public void exists() {
		file = new File(System.getProperty("user.dir", "/Kuber"));
		file1 = new File(file + "/saves");
		if (!file.exists()) {
			file.mkdir();
		}
		if (!file1.exists()) {
			file1.mkdir();
		}
		file2 = new File(file1 + "/Kuber.sqlite");
		if (!file2.exists()) {
			System.out.println("Creating file");
			try {
				file2.createNewFile();
				Connection();
				Connection1();
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Create new File Failed"
						+ e);
			}
		}
	}

	public final boolean Connected() {
		try {
			initialize = SQLiteJDBCLoader.initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}

		SQLiteDataSource dataSource = new SQLiteDataSource();
		dataSource.setUrl(sql);
		try {
			ResultSet executeQuery = dataSource.getConnection()
					.createStatement().executeQuery("select * from tbl1");
			while (executeQuery.next()) {
				String Variable = executeQuery.getString("Variable");
				boolean Value = executeQuery.getBoolean("truefalse");
				if ("ShowFps".equals(Variable))
					showfps = Value;
				if ("ShowTps".equals(Variable))
					showtps = Value;
				if ("BlackSelected".equals(Variable))
					blackselected = Value;
				if ("GreenSelected".equals(Variable))
					greenselected = Value;
				if ("BlueSelected".equals(Variable))
					blueselected = Value;
				if ("GreenUnlocked".equals(Variable))
					greenunlocked = Value;
				if ("BlueUnlocked".equals(Variable))
					blueunlocked = Value;
				if ("DefaultSelected".equals(Variable))
					defaultselected = Value;
				if ("SkySelected".equals(Variable))
					skyselected = Value;
				if ("UnderWaterSelected".equals(Variable))
					underwaterselected = Value;
				if ("SkyUnlocked".equals(Variable))
					skyunlocked = Value;
				if ("UnderWaterUnlocked".equals(Variable))
					underwaterunlocked = Value;
				if ("SoundOn".equals(Variable))
					setSoundOn(Value);
				System.out.println(Variable + " = " + Value);
			}
			ResultSet executeQuery1 = dataSource.getConnection()
					.createStatement().executeQuery("select * from tbl2");
			while (executeQuery1.next()) {
				String Variable = executeQuery1.getString("Variable");
				int Value = executeQuery1.getInt("Amount");
				if ("Coins".equals(Variable))
					TotalCoins = Value;
				if ("HighScore".equals(Variable))
					HighScore = Value;
				System.out.println(Variable + " = " + Value);
			}
			executeQuery.close();
			executeQuery1.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return initialize;
	}

	public void Connection() {
		Connection conn = null;
		Statement stat = null;
		PreparedStatement prep = null;
		String sql2 = "INSERT INTO tbl1 values(?,?)";
		if (initialize == false) {
			System.out.println("error");
		}
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(sql);
			stat = conn.createStatement();
			stat.setQueryTimeout(30);
			stat.executeUpdate("DROP TABLE IF EXISTS tbl1");
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS tbl1(Variable varchar(15),"
					+ "truefalse Boolean);");
			prep = conn.prepareStatement(sql2);
			prep.setString(1, "ShowFps");
			prep.setBoolean(2, game.isShowfps());
			prep.executeUpdate();
			prep.setString(1, "ShowTps");
			prep.setBoolean(2, game.isShowtps());
			prep.executeUpdate();
			prep.setString(1, "BlackSelected");
			prep.setBoolean(2, game.isBlackselected());
			prep.executeUpdate();
			prep.setString(1, "GreenSelected");
			prep.setBoolean(2, game.isGreenselected());
			prep.executeUpdate();
			prep.setString(1, "BlueSelected");
			prep.setBoolean(2, game.isBlueselected());
			prep.executeUpdate();
			prep.setString(1, "GreenUnlocked");
			prep.setBoolean(2, game.isGreenunlocked());
			prep.executeUpdate();
			prep.setString(1, "BlueUnlocked");
			prep.setBoolean(2, game.isBlueunlocked());
			prep.executeUpdate();
			prep.setString(1, "DefaultSelected");
			prep.setBoolean(2, game.isDefaultselected());
			prep.executeUpdate();
			prep.setString(1, "SkySelected");
			prep.setBoolean(2, game.isSkyselected());
			prep.executeUpdate();
			prep.setString(1, "UnderWaterSelected");
			prep.setBoolean(2, game.isUnderwaterselected());
			prep.executeUpdate();
			prep.setString(1, "SkyUnlocked");
			prep.setBoolean(2, game.isSkyunlocked());
			prep.executeUpdate();
			prep.setString(1, "UnderWaterUnlocked");
			prep.setBoolean(2, game.isUnderwaterunlocked());
			prep.executeUpdate();
			prep.setString(1, "SoundOn");
			prep.setBoolean(2, game.isSoundOn());
			prep.executeUpdate();
			prep.close();
			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Connection1() {
		Connection conn = null;
		Statement stat = null;
		PreparedStatement prep1 = null;
		String sql2 = "REPLACE INTO tbl2 values(?,?)";

		if (initialize == false) {
			System.out.println("error");
		}
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(sql);
			stat = conn.createStatement();
			stat.setQueryTimeout(30);
			stat.executeUpdate("DROP TABLE IF EXISTS tbl2");
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS tbl2(Variable varchar(15),"
					+ "Amount Integer);");
			prep1 = conn.prepareStatement(sql2);
			prep1.setString(1, "Coins");
			prep1.setInt(2, game.getTotalCoins());
			prep1.executeUpdate();
			prep1.setString(1, "HighScore");
			prep1.setInt(2, game.getHighScore());
			prep1.executeUpdate();
			prep1.close();
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(String Variable, int Value) {
		Connection conn = null;
		Statement stat = null;
		PreparedStatement prep = null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(sql);
			stat = conn.createStatement();
			stat.setQueryTimeout(30);
			prep = conn
					.prepareStatement("UPDATE tbl2 SET Amount = ? WHERE VARIABLE = ?");
			prep.setInt(1, Value);
			prep.setString(2, Variable);
			prep.executeUpdate();
			prep.close();
			stat.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(String Variable, Boolean truefalse) {
		Connection conn = null;
		Statement stat = null;
		PreparedStatement prep = null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(sql);
			stat = conn.createStatement();
			stat.setQueryTimeout(30);
			prep = conn
					.prepareStatement("UPDATE tbl1 SET truefalse = ? WHERE VARIABLE = ?");
			prep.setBoolean(1, truefalse);
			prep.setString(2, Variable);
			prep.executeUpdate();
			prep.close();
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean isBlackselected() {
		return blackselected;
	}

	public void setBlackselected(boolean blackselected) {
		this.blackselected = blackselected;
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

	public int getTotalCoins() {
		return TotalCoins;
	}

	public void setTotalCoins(int totalCoins) {
		TotalCoins = totalCoins;
	}

	public int getHighScore() {
		return HighScore;
	}

	public void setHighScore(int highScore) {
		HighScore = highScore;
	}

	public boolean isSoundOn() {
		return soundOn;
	}

	public void setSoundOn(boolean soundOn) {
		this.soundOn = soundOn;
	}
}
