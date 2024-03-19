package uo.mp.battleship.model.game;

import uo.mp.battleship.interaction.ConsoleWriter;
import uo.mp.battleship.model.board.Board;
import uo.mp.battleship.model.board.Coordinate;
import uo.mp.battleship.model.board.Square.Damage;
import uo.mp.battleship.model.player.ComputerPlayer;
import uo.mp.battleship.model.player.HumanPlayer;
import uo.mp.battleship.model.player.Player;

public class Game {

	public final static int DEFAULT_SIZE = 10;
	
	public final static int NUMBER_OF_PLAYERS = 2;
	
	final static public boolean NORMAL = false;
	final static public boolean DEBUG = true;
	
	private HumanPlayer player;
	private ComputerPlayer computer;
	private int boardSize;
	private boolean gameMode;
	
	private TurnSelector turn;
	
	private Player[] players= new Player[NUMBER_OF_PLAYERS];
	
	
	/**
	 * It receives a HumanPlayer as first argument and a ComputerPlayer, as the second. Other 
	*game parameters as size of the boards take default values. That is, boards to play are 10×10. 
	*Game mode is set to normal mode. It also creates a turn selector and the boards to play and 
	*passes to the players the board references
	*
	 * @param leftPlayer
	 * @param rightPlayer
	 */
	public Game(HumanPlayer leftPlayer, ComputerPlayer rightPlayer) {
		player = leftPlayer;
		computer = rightPlayer;
		
		players[TurnSelector.HUMAN] = leftPlayer;
		players[TurnSelector.COMPUTER] = rightPlayer;
		
		boardSize = DEFAULT_SIZE;
		gameMode = NORMAL;
		
		turn = new TurnSelector();
		
		Board playerBoard = new Board(boardSize);
		Board computerBoard = new Board(boardSize);
		
		player.setMyShips(playerBoard);
		player.setOpponentShips(computerBoard);
		
		computer.setOpponentShips(playerBoard);
		computer.setMyShips(computerBoard);
		
		
	}
	
	
	/**
	 * It sets the game mode. If mode is false, then game mode is set to normal (computer’s fleet is 
	*concealed). If it is true, game mode is set to debug so computer’s fleet will be displayed. 
	 * @param gameMode
	 */
	public void setDebugMode ( boolean gameMode ) {
		this.gameMode = gameMode;
		
	}

	/**
	 * It is the only responsible for user interaction as well as for managing the main loop. In this 
	*loop, players alternate turns to shoot while there is no winner. 
	 */
	public void play() {
		int whoseTurn = turn.next();
		Coordinate choice ;
		ConsoleWriter display = new ConsoleWriter();
		
		while(!player.hasWon() && !computer.hasWon()) {
			
			
			display.showGameStatus(player.getMyShips(), 
					player.getOpponentShips(), gameMode);
			
			display.showTurn(players[whoseTurn].getName());
			
			choice = players[whoseTurn].makeChoice();
			
			display.showShootingAt(choice);
			
			Damage dam = players[whoseTurn].shootAt(
					choice);
			display.showShotMessage(dam);
			if(dam == Damage.NO_DAMAGE)
				whoseTurn = turn.next();
		}
		display.showWinner(players[turn.next()].getName());
		display.showGameOver();
	
	}
	
}
