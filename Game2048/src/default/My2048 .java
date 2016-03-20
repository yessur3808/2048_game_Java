/*
<<<<<<< HEAD
 * Date: 24 
 * Student Name:
=======
 * Date: 
 * Student Name: a
>>>>>>> b69cad700e0ca642b2a7b0db8b2f37362920545c
 * Student ID:
 * 
 */
package student;

import game.v2.Console;
import game.v2.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Game skeleton of 2048
 *
 * @author Van
 */
public class My2048 extends Game implements Board {

	/*
	 * You can declare any data fields here for your game as usual.
	 */
	static Random rand = new Random();
	Scanner scanner = new Scanner(System.in);

	private Board board; // initialize it with your own implementation
	static int[] tile = new int[16];
	static int[][] tileValue = new int[4][4];
	/*
	 * tileLocation 0 1 2 3 4 5 6 7 8 9 10 11
	 */
	static int[] tileLocation = new int[16];
	static int[][] tileLocationX = new int[4][4];
	static int[][] tileLocationY = new int[4][4];
	static int tileTopLeftX = 50;
	static int tileTopLeftY = 150;
	static int distance = 110;

	int randX;
	int randY;

	boolean restart = false;

	boolean isFull = false;

	/*
	 * Main method
	 */
	public static void main(String[] args) {

		// Initialization

		// Initialize the tile Value
		// for (int j = 0; j < 4; j++)
		// for (int k = 0; k < 4; k++)
		// tileValue[j][k] = 0;

		// Initialize the tile location
		for (int j = 0; j < 4; j++) {
			for (int k = 0; k < 4; k++) {
				tileLocationX[j][k] = tileTopLeftX + j * distance;
				tileLocationY[j][k] = tileTopLeftY + k * distance;
			}
		}

		/*
		 * Customize the console window per your need but do not show it yet.
		 */
		Console.getInstance().setTitle("Game 2048").setWidth(480)
				.setHeight(640).setTheme(Console.Theme.LIGHT);

		/*
		 * Similar to the Console class, use the chaining setters to configure
		 * the game. Call start() at the end of the chain to start the game
		 * loop.
		 */
		new My2048().setFps(60) // set frame rate
				.setShowFps(true) // set to display fps on screen
				.setBackground(Console.loadImage("/assets/board.png")) // set
																		// background
																		// image
				.start(); // start game loop

	}

	/**
	 * *************************************************************************
	 * ********************* There are three abstract methods must be override:
	 * protected abstract void cycle(); protected abstract void
	 * keyPressed(KeyEvent e); protected abstract void mouseClicked(MouseEvent
	 * e);
	 */
	@Override
	protected void cycle() {
		// Uncomment this line when the board is initialized with your
		// implementation of Board interface
		// board.draw();
		draw();
	}

	@Override
	protected void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			move(Direction.UP);
			break;
		case KeyEvent.VK_DOWN:
			move(Direction.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			move(Direction.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			move(Direction.RIGHT);
			break;
		}
	}

	@Override
	protected void mouseClicked(MouseEvent e) {
		// it use to check the image position
		System.out.println("Click on (" + e.getX() + "," + e.getY() + ")");
	}

	@Override
	public boolean hasLegalMove() {
		// TODO Auto-generated method stub
		if (isFull) {
			return true;
		}
		return false;
	}

	// TODO
	@Override
	public void move(Direction dir) {
		if (dir == Direction.UP) {
			boolean[][] tempTile = new boolean[4][4];
			for (int i = 0; i < 4; i++) {
				for (int j = 1; j < 4; j++) {
					if (tileValue[i][j] != 0) {
						for (int k = j; k != 0; k--) {
							if (tileValue[i][k - 1] != 0) {
								System.out.println("upper " + k + " tile exist");
								if (tileValue[i][k - 1] == tileValue[i][j]) {
									tileValue[i][k - 1] *= 2;
									tileValue[i][j] = 0;
									System.out.println("merge");
								} else {
									System.out.println("stay");
								}
							} else {
								System.out.println("upper tile not exist");
								if (tileValue[i][k - 1] != tileValue[i][j]) {
									tileValue[i][k - 1] = tileValue[i][j];
									tileValue[i][j] = 0;
									System.out.println("move upward");
								}
							}
						}
					}
				}
			}
			newTile();
		}

		if (dir == Direction.DOWN) {

		}
		if (dir == Direction.LEFT) {

		}

		if (dir == Direction.RIGHT) {

		}

	}

	@Override
	public boolean isWon() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setNewTileValue(int n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void newTile() {
		// check where is the empty tile and set
		do {
			// randomly set the new tile location(x,y)
			randX = rand.nextInt(4);
			randY = rand.nextInt(4);
		} while (tileValue[randX][randY] != 0);
		tileValue[randX][randY] = 2;

	}

	public void restart() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				tileValue[i][j] = 0;
			}
		}

		int i = rand.nextInt(4);
		int j = rand.nextInt(4);
		tileValue[i][j] = 2;

		do {
			// randomly set the new tile location(x,y)
			i = rand.nextInt(4);
			j = rand.nextInt(4);
		} while (tileValue[i][j] != 0);
		tileValue[i][j] = 2;
	}

	@Override
	public void draw() {
		if (restart == false) {
			restart();
			restart = true;
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (tileValue[i][j] != 0) {
					Console.getInstance()
							.drawRectangle(tileLocationX[i][j],
									tileLocationY[i][j], 128, 128,
									new Color(0xeee4da), 20) // rounded
																// rectangle
																// with arc
																// radius 20
							.drawText(tileLocationX[i][j],
									tileLocationY[i][j] + 100,
									Integer.toString(tileValue[i][j]),
									new Font("Arial", Font.BOLD, 72),
									Color.black);
				}
			}
		}

	}

	@Override
	public int[][] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setArray(int[][] array) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setScore(int score) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getBestScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBestScore(int bestScore) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTileExist(int[][] array, boolean exist) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getTileExist(int[][] array) {
		// TODO Auto-generated method stub
		return false;
	}
}
