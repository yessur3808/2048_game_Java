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

        public My2048() {
		try {
			String tmp = null;
			File file = new File("highScore.txt");
			if (!file.exists())
				file.createNewFile();

			Scanner scan = new Scanner(file);
			while (scan.hasNextLine())
				tmp = scan.nextLine();
			scan.close();
			if (tmp == null)
				highScore = 0;
			else
				highScore = Integer.parseInt(tmp);
		} catch (Exception e) {
		}
		System.out.println("High Score: " + highScore);

		System.out.println("object created");
		setArray(toArray());
		newTile();
		newTile();
	}
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
	
	/**
	 * *************************************************************************
	 * ********************* There are three abstract methods must be override:
	 * protected abstract void cycle(); protected abstract void
	 * keyPressed(KeyEvent e); protected abstract void mouseClicked(MouseEvent
	 * e);
	 */
	
        public void move(enum dir) {
		switch (dir) {
		case UP: {
			System.out.println("up");
			for (int j = 0; j < size; j++) {
				for (int i = 0; i < size; i++) {
					if (rak[i][j] != 0) {
						for (int k = i + 1; k < size; k++) {
							if (rak[k][j] != 0) {
								if (rak[k][j] == rak[i][j]) {
									rak[i][j] *= 2;
									rak[k][j] = 0;
									setScore(rak[i][j]);

								}
								break;
							}
						}
					} else {
						for (int k = i; k < size - 1; k++)
							rak[k][j] = rak[k + 1][j];
						rak[3][j] = 0;
						boolean found = false;
						for (int k = i; k < size && !found; k++)
							if (rak[k][j] != 0)
								found = true;
						if (found)
							i--;
					}
				}
			}
		}
			break;

		case DOWN: {
			System.out.println("down");
			for (int j = 0; j < size; j++) {
				for (int i = size - 1; i >= 0; i--) {
					if (rak[i][j] != 0) {
						for (int k = i - 1; k >= 0; k--) {
							if (rak[k][j] != 0) {
								if (rak[k][j] == rak[i][j]) {
									rak[i][j] *= 2;
									rak[k][j] = 0;
									setScore(rak[i][j]);

								}
								break;
							}
						}
					} else {
						for (int k = i; k > 0; k--)
							rak[k][j] = rak[k - 1][j];
						rak[0][j] = 0;

						boolean found = false;
						for (int k = i; k > 0 && !found; k--)
							if (rak[k][j] != 0)
								found = true;
						if (found)
							i++;
					}
				}
			}

			break;
		}
		case LEFT: {
			System.out.println("left");
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (rak[i][j] != 0) {
						for (int k = j + 1; k < size; k++) {
							if (rak[i][k] != 0) {
								if (rak[i][k] == rak[i][j]) {
									rak[i][j] *= 2;
									rak[i][k] = 0;
									setScore(rak[i][j]);

								}
								break;
							}
						}
					} else {
						for (int k = j; k < size - 1; k++)
							rak[i][k] = rak[i][k + 1];
						rak[i][3] = 0;
						boolean found = false;
						for (int k = j; k < size && !found; k++)
							if (rak[i][k] != 0)
								found = true;
						if (found)
							j--;
					}
				}
			}
		}
			break;
		case RIGHT: {
			System.out.println("right");
			for (int i = 0; i < size; i++) {
				for (int j = size - 1; j >= 0; j--) {
					if (rak[i][j] != 0) {

						for (int k = j - 1; k >= 0; k--) {
							if (rak[i][k] != 0) {
								if (rak[i][k] == rak[i][j]) {
									rak[i][j] *= 2;
									rak[i][k] = 0;
									setScore(rak[i][j]);

								}
								break;
							}
						}
					} else {
						for (int k = j; k > 0; k--)
							rak[i][k] = rak[i][k - 1];
						rak[i][0] = 0;

						boolean found = false;
						for (int k = j; k > 0 && !found; k--)
							if (rak[i][k] != 0)
								found = true;
						if (found)
							j++;
					}
				}
			}

			break;
		}
		}

		newTile();
	}
        
        
        
        
        
        public void draw() {
		int x1 = 22;        //initialize coordinates
		int y1 = 188;
		int tileLen = 100;
		int tileSep = 15;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Console.getInstance().drawRectangle(
						x1 + (tileLen + tileSep) * j,
						y1 + (tileLen + tileSep) * i,
						tileLen, tileLen, Color.LIGHT_GRAY, 20);
				if (rak[i][j] == 2) {
					Console.getInstance().drawRectangle(
						x1 + (tileLen + tileSep) * j,
						y1 + (tileLen + tileSep) * i,
							tileLen, tileLen, new Color(0xe9e9e9),0x000000);
				}
				if (rak[i][j] == 4) {
					Console.getInstance().drawRectangle(
						x1 + (tileLen + tileSep) * j,
						y1 + (tileLen + tileSep) * i,
							tileLen, tileLen, new Color(0xe6daab),0x000000);
				}
				if (rak[i][j] == 8) {
					Console.getInstance().drawRectangle(
						x1 + (tileLen + tileSep) * j,
						y1 + (tileLen + tileSep) * i,
							tileLen, tileLen, new Color(0xf79d3d),0xffffff);
				}
				if (rak[i][j] == 16) {
					Console.getInstance().drawRectangle(
						x1 + (tileLen + tileSep) * j,
						y1 + (tileLen + tileSep) * i,
							tileLen, tileLen, new Color(0xe6daab),0xffffff);
				}
				if (rak[i][j] == 32) {
					Console.getInstance().drawRectangle(
						x1 + (tileLen + tileSep) * j,
						y1 + (tileLen + tileSep) * i,
							tileLen, tileLen, new Color(0xf28007),0xffffff);
				}
				if (rak[i][j] == 64) {
					Console.getInstance().drawRectangle(
						x1 + (tileLen + tileSep) * j,
						y1 + (tileLen + tileSep) * i,
							tileLen, tileLen, new Color(0xf55e3b),0xffffff);
				}
				if (rak[i][j] == 128) {
					Console.getInstance().drawRectangle(
						x1 + (tileLen + tileSep) * j,
						y1 + (tileLen + tileSep) * i,
							tileLen, tileLen, new Color(0xff0000),0xffffff);
				}
				if (rak[i][j] == 256) {
					Console.getInstance().drawRectangle(
						x1 + (tileLen + tileSep) * j,
						y1 + (tileLen + tileSep) * i,
							tileLen, tileLen, new Color(0xe9de84),0xffffff);
				}
				if (rak[i][j] == 512) {
					Console.getInstance().drawRectangle(
						x1 + (tileLen + tileSep) * j,
						y1 + (tileLen + tileSep) * i,
							tileLen, tileLen, new Color(0xf5e455),0xffffff);
				}
				if (rak[i][j] == 1024) {
					Console.getInstance().drawRectangle(
						x1 + (tileLen + tileSep) * j,
						y1 + (tileLen + tileSep) * i,
							tileLen, tileLen, new Color(0xf7e12c),0xffffff);
				}
				if (rak[i][j] == 2048) {
					Console.getInstance().drawRectangle(
						x1 + (tileLen + tileSep) * j,
						y1 + (tileLen + tileSep) * i,
							tileLen, tileLen, new Color(0xffe4f0),0xffffff);
				}
				if (rak[i][j] >= 4096) {
					Console.getInstance().drawRectangle(
						x1 + (tileLen + tileSep) * j,
						y1 + (tileLen + tileSep) * i,
							tileLen, tileLen, new Color(0xffe400),0xffffff);
				}

				if (rak[i][j] != 0) {
					int fontsize = 55;
					if (rak[i][j] < 10) {
						fontsize = 56;
					} else if (rak[i][j] < 100) {
						fontsize = 44;
					} else if (rak[i][j] < 1000) {
						fontsize = 36;
					} else if (rak[i][j] < 10000) {
						fontsize = 24;
					} else
						fontsize = 16;
					Console.getInstance().drawText(
							x1 + (tileLen + tileSep) * j + 25,
							y1 + (tileLen + tileSep) * i + 70,
							Integer.toString(rak[i][j]),
							new Font("Lato", Font.BOLD, fontsize),
							Color.LIGHT_GRAY);
				}
			}
		}
			Console.getInstance().drawRectangle(234, 56,100, 20, new Color(187, 173, 160),20);
			Console.getInstance().drawText(264, 75,
					Integer.toString(currentScore),

					new Font("Lato", Font.BOLD, 24), Color.DARK_GRAY);
			Console.getInstance().drawRectangle(359, 56,

			100, 20, new Color(187, 173, 160), 20);
                        
			Console.getInstance().drawText(389, 75,Integer.toString(highScore),

					new Font("Lato", Font.BOLD, 24), Color.DARK_GRAY);
                        
			if (getScore() >= 2048 && limit == false) {
				Console.getInstance().drawText(110, 345, "Winner!!!",
						new Font("Lato", Font.BOLD, 50), Color.DARK_GRAY);
				Console.getInstance().drawRectangle(97, 434,100, 40, Color.DARK_GRAY, 20);
                                
				Console.getInstance().drawRectangle(264, 434,100, 40, Color.DARK_GRAY, 20);
                                
				Console.getInstance().drawText(103, 460, "Continue",
                                        
				new Font("Lato", Font.BOLD, 16), Color.WHITE);
                                
				Console.getInstance().drawText(280, 460, "Try Again",
                                        
				new Font("Lato", Font.BOLD, 16), Color.WHITE);
			} else if (!hasLegalMove()) {
				Console.getInstance().drawText(110, 345, "Game Over",
						new Font("Arial", Font.BOLD, 50), Color.DARK_GRAY);
                                
				Console.getInstance().drawRectangle(264, 434, 100, 40, Color.DARK_GRAY, 20);
				Console.getInstance().drawText(280, 460, "Retry",
                                        
				new Font("Lato", Font.BOLD, 16), Color.WHITE);
			};
	}
        
        
        
        
        
        
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

	/*@Override
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
*/
        
        
        
        
                

	public boolean hasLegalMove() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (rak[i][j] == 0)
					return true;
			}
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size - 1; j++) {
				if (rak[i][j] == rak[i][j + 1])
					return true;
			}
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size - 1; j++) {
				if (rak[j][i] == rak[j + 1][i])
					return true;
			}
		}
		return false;
	}
	public boolean isWon() {

		return false;
                }//after having lost the game or (not won)
	}

	public void setNewTileValue(int n) {
		n = (ran.nextInt(10) < 9) ? 2 : 4;
		boolean exists = true;
		while (exists) {
			int i = ran.nextInt(size);
			int j = ran.nextInt(size);
			if (rak[i][j] == 0) {
				rak[i][j] = n;
				exists = true;

			}
		}
	}


	public void newTile() {
		int value = (ran.nextInt(10) < 9) ? 2 : 4;
		boolean exists = false;
		while (!exists) {
			int i = ran.nextInt(size);
			int j = ran.nextInt(size);
			if (rak[i][j] == 0) {
				rak[i][j] = value;
				exists = true;

			}
		}
	}



	public int[][] toArray() {
		rak= new int[size][size];

		for (int i = 0; i < size; i++)
			for (int j = 0; j < 0; j++) {
				rak[i][j] = 0;

			}
		return rak;
	}

	public void setArray(int[][] array) {
		for (int i = 0; i <size; i++)
			for (int j = 0; j < size; j++)
				rak[i][j] = array[i][j];
	}

	public int getScore() {
		return currentScore;
	}

	public void setScore(int score) {
		if (score == -1) {
			currentScore = 0;
			limit = false;
		} else if (score == -2)
			limit = true;
		else {
			currentScore = currentScore + score;
			setBestScore(currentScore);
		}
	}

	public int getBestScore() {
		return 0;
	}

	public void setBestScore(int bestScore) {
		if (bestScore > highScore) {
			highScore = bestScore;
			File file = new File("highScore.txt");
			try {
				file.delete();
				file.createNewFile();
				FileWriter writer = new FileWriter(file);
				writer.write(Integer.toString(bestScore));
				writer.close();
			} catch (Exception e) {
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
