package uo.mp.battleship;

import uo.mp.battleship.model.game.Game;
import uo.mp.battleship.model.player.ComputerPlayer;
import uo.mp.battleship.model.player.HumanPlayer;

public class Main {

	private Game game;
	private boolean gameMode = false;

	public static void main(String[] args) {
		new Main()
			.configure(args)
			.run();
	}

	private boolean readDebugMode(String mode) {
		if (mode == null ||
				"DEBUG".equals(mode.toUpperCase()))
			return true;
		return false;
	}
	
	private Main configure(String[] args) {
		HumanPlayer user = new HumanPlayer("User");
		ComputerPlayer computer = new ComputerPlayer("Computer");
		game = new Game( user, computer );
		gameMode = readDebugMode(args[0]);
		game.setDebugMode(gameMode);
		return this;
	}

	private void run() {
		game.play();
	}
}
