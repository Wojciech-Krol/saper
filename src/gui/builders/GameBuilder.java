package gui.builders;

import scripts.GameState;

import javax.swing.*;

public class GameBuilder {
	
	public GameBuilder() {
	
	}
	
	public GameState getGame(int size, JPanel gameScene, int bombs) {
		return new GameState(size, gameScene,bombs);
	}
}
