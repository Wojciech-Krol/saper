package scripts;

import javax.swing.*;

import gui.components.*;
import gui.endScreen;
import gui.winScreen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

enum E_GAME_STAGE {
	PRE_GAME,
	IN_GAME,
	LOST_GAME,
	WIN_GAME,
}

public class GameState {
	public static Timer timer;
	int bombs;
	// Czy gracz dokonał 
	private E_GAME_STAGE gameStage;
	
	// zrobić klase GameTile, która będzie pojedynczym kafelkiem na planszy
	public GameTile[][] gameTiles; 
	private int iconSize;
	
	public void boardClicked(GameTile clickedTile) {
		//zrobic dzialajacy switch
		switch (gameStage) {
			case PRE_GAME:
				handleTileClicked(clickedTile);
				gameStage = E_GAME_STAGE.IN_GAME;
				break;
				
			case IN_GAME:
				handleTileClicked(clickedTile);
				break;
				
			default:
				// przegrał jakies info or what albo nic albo restart
				break;
		}
	}
	
	private void handleTileClicked(GameTile clickedTile) {
		if (clickedTile.hasBomb) {
			gameStage = E_GAME_STAGE.LOST_GAME;
			return;
		}
		
		// tu logika z odslanianiem bombek na planszy
		// petla np przez gameTiles i uzycie na nich metody .showUp();
		clickedTile.showUp();
	}
	
	private void generateBombs(int allbombs, int size) {
		Random random = new Random();
		int bombs = allbombs;
		while (bombs > 0) {
			int x = random.nextInt(size-1);
			int y = random.nextInt(size-1);
			if (!gameTiles[x][y].ifHasBomb())  {
				gameTiles[x][y].setBomb();
				bombs--;

			}

		}
	}

	private Boolean getIfEnabled(GameTile clickedTile) {
		if(clickedTile.isPressed) {
			return false;
		}
		return true;
	}

	public void odkrywanieInnych(int w, int k, int size,GameTile[][] gameTiles) {
		if (countNeighbours(w, k, size,gameTiles) == 0) {
			for (int i = w - 1; i < w + 2; i++) {
				for (int j = k - 1; j < k + 2; j++) {
					if (i < 0 || i > size - 1 || j < 0 || j > size - 1) {
					} else if (i == w && j == k) {
					} else if(gameTiles[i][j].isPressed){
					} else if (countNeighbours(i, j, size,gameTiles) == 0) {
						gameTiles[i][j].setBackground(new Color(240, 240, 240));
						gameTiles[i][j].setPressed(true);
						gameTiles[i][j].setEnabled(false);
						odkrywanieInnych(i, j, size,gameTiles);
					} else {
						int neighbours = countNeighbours(i, j, size,gameTiles);
						gameTiles[i][j].setBackground(new Color(240, 240, 240));
						gameTiles[i][j].setPressed(true);
						gameTiles[i][j].setEnabled(false);
						gameTiles[i][j].setText(Integer.toString(neighbours));
					}
				}
			}
		}

	}

	private int countNeighbours(int w, int k, int size, GameTile[][] gameTiles) {
		int neighbours = 0;
		for (int i = w - 1; i < w + 2; i++) {
			for (int j = k - 1; j < k + 2; j++) {
				if (i < 0 || i > size - 1 || j < 0 || j > size - 1) {
				} else if (i == w && j == k) {
				} else if (gameTiles[i][j].hasBomb==true) {
					neighbours++;
				}
			}
		}
		return neighbours;
	}

	private void gameLost(int size) throws InterruptedException {
		ImageIcon imageIcon1 = new ImageIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/gui/icon.jpg"))).getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_DEFAULT));
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				gameTiles[i][j].setEnabled(false);
				gameTiles[i][j].setBackground(new Color(240,240,240));
				gameTiles[i][j].removeMouseListener(gameTiles[i][j].getMouseListeners()[1]);
				if(gameTiles[i][j].hasBomb) {
					gameTiles[i][j].setEnabled(true);
					gameTiles[i][j].setIcon(imageIcon1);
				}
			}
		}
		endScreen end = new endScreen();
		end.setBackground(new Color(24, 22, 22));
		end.getContentPane().setBackground(new Color(24, 22, 22));
		timer = new Timer(400, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				end.setVisible(true);
			}
		});
		timer.start();

	}

	private boolean checkIfWin(int size){
		int properflags=0;
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(gameTiles[i][j].hasBomb==true && gameTiles[i][j].ifFlagged()==true){
					properflags++;
				}
			}
		}
		if(properflags==bombs){
			return true;
		}
		return false;
	}
	private void gameWon() throws InterruptedException {
		winScreen win = new winScreen();
		win.setBackground(new Color(24, 22, 22));
		win.getContentPane().setBackground(new Color(24, 22, 22));
		win.setVisible(true);
	}
	private void addMouseListeners(int size){
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				int finalI = i;
				int finalJ = j;
				gameTiles[i][j].addMouseListener(new MouseAdapter(){
					ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("./src/nieuzywane/saper/icon.jpg").getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_DEFAULT));
					boolean pressed;
					int neighbours = countNeighbours(finalI, finalJ, size,gameTiles);

					@Override
					public void mousePressed(MouseEvent e) {
						gameTiles[finalI][finalJ].getModel().setPressed(true);
						pressed = true;
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						gameTiles[finalI][finalJ].getModel().setArmed(false);
						gameTiles[finalI][finalJ].getModel().setPressed(false);
						if (pressed) {
							if (SwingUtilities.isRightMouseButton(e)) {
								if (gameTiles[finalI][finalJ].isEnabled()) {
									if (!gameTiles[finalI][finalJ].ifFlagged()) {
										gameTiles[finalI][finalJ].setText("F");
										gameTiles[finalI][finalJ].setBackground(Color.RED);
									}
									else{
										gameTiles[finalI][finalJ].setText("");
										gameTiles[finalI][finalJ].setBackground(new Color(128, 128, 128));
									}
								}
								if(checkIfWin(size)){
									try {
										gameWon();
									} catch (InterruptedException ex) {
										throw new RuntimeException(ex);
									}
								}
							}
							else {
								gameTiles[finalI][finalJ].setEnabled(false);
								gameTiles[finalI][finalJ].setBackground(new Color(240, 240, 240));
								if(gameTiles[finalI][finalJ].hasBomb) {
									gameTiles[finalI][finalJ].setIcon(imageIcon1);
									try {
										gameLost(size);
									} catch (InterruptedException ex) {
										throw new RuntimeException(ex);
									}

								}
								else {
									gameTiles[finalI][finalJ].setText(Integer.toString(neighbours));
									System.out.println(neighbours);
									if(neighbours == 0) {
										gameTiles[finalI][finalJ].setText("");
										odkrywanieInnych(finalI, finalJ, size,gameTiles);
									}
								}
							}
						}
						pressed = false;
					}

					@Override
					public void mouseExited(MouseEvent e) {
						pressed = false;
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						pressed = true;
					}
				});
			}
		}

	}
	private void generateBoard(int size, JPanel panel,int bombss) {
		if(size ==15) {
			iconSize = 45;
		}
		else if(size == 25) {
			iconSize = 30;
		}
		else if(size == 35) {
			iconSize = 24;
		}
		else {
			iconSize = 20;
		}

		panel.setLayout(new GridLayout(size,size));
		Icon icon = new ImageIcon("./src/nieuzywane/saper/icon.jpg");
	    
	    gameTiles = new GameTile[size][size];
	    
	    for(int i = 0; i < size; i++) {
	    	for(int j = 0; j < size; j++) {
	    		gameTiles[i][j] = new GameTile();
	    		gameTiles[i][j].setMargin(new Insets(0, 0, 0, 0));
	    		gameTiles[i][j].putClientProperty("column", i);
	    		gameTiles[i][j].putClientProperty("row", j);
				gameTiles[i][j].setBackground(new Color(128, 128, 128));
				gameTiles[i][j].setOpaque(true);
	    		panel.add(gameTiles[i][j]);
	    	}
	    }
		panel.setPreferredSize(new Dimension(30*size,30*size));
		generateBombs(bombss,size);
		addMouseListeners(size);
	}
	
	public GameState(int size, JPanel gameScene,int bombs) {
		gameStage = E_GAME_STAGE.PRE_GAME;
		this.bombs=bombs;
		generateBoard(size,gameScene,bombs);
	}
}
