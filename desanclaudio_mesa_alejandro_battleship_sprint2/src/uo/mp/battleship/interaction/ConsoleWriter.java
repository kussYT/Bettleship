package uo.mp.battleship.interaction;

import uo.mp.battleship.model.board.Board;
import uo.mp.battleship.model.board.Coordinate;
import uo.mp.battleship.model.board.Square.Damage;
import uo.mp.battleship.model.game.Game;


public class ConsoleWriter {

	
	
	public void showGameStatus(Board left, Board right, boolean debugMode) {
		System.out.println(title(left));
		System.out.println(colLabel(left));
		
		String playerB = complexBoard(left);
		String computerB = (debugMode ? complexBoard(right) : simpleBoard(right));
		
		System.out.println(columnBoards(playerB, computerB));
		
	}	

	/*
	 * Expected message 
	 * 		The winner is ... winner name
	 */
	public void showWinner(String winner) {
		System.out.println("The winner is " + winner);
	}

	/*
	 * Expected message
	 * 		The game is over!!
	 */
	public void showGameOver() {
		System.out.println("The game is over!");
	}
	
	/*
	 * Expected message
	 * 		Now the turn is for the player player name
	 */
	public void showTurn(String playerName) {
		System.out.println("Now the turn is for the player " + playerName);
	}

	/*
	 * Expected message
	 * 		Shoot at user-friendly
	 */
	public void showShootingAt(Coordinate position) {
		
		System.out.println("Shoot at " + position.toUserString());
	}

	/*
	 * Expected message
	 * 		Depending on the shot result:
	 * 		MISS, HIT!! (sprint 1)
	 * 		MISS, HIT!!. Continue, HIT AND SUNK!!. Continue (from sprint 2 on)
	 */
	public void showShotMessage(Damage damage) {
		if(damage.equals(Damage.NO_DAMAGE))
			System.out.println("MISS");
		else if(damage.equals(Damage.SEVERE_DAMAGE))
			System.out.println("HIT, Continue");
		else 
			System.out.println("HIT AND STUNK, Continue");
	}
	
	
	private String title(Board board) {
		StringBuilder sb = new StringBuilder("       MY SHIPS");
		int size = board.getSize();
		for(int i = 0; i < size*2; i++)
			sb.append(" ");
		sb.append("OPPONENTS'S SHIPS \n");
		return sb.toString();
	}
	
	private String colLabel(Board board) {
		StringBuilder sb = new StringBuilder("   ");
		char letter;
		for(int i= 0; i < Game.NUMBER_OF_PLAYERS; i++) {
			for(int j = 0; j < board.getSize(); j++) {
				letter = Coordinate.COLUMNS[j];
			
				sb.append(letter + " ");
			}
			sb.append("             ");
		}
		return sb.toString();
	}
	
	private int[] rowLabel(Board board) {
		int[] numbers = new int[Coordinate.COLUMNS.length * 
		                          Game.NUMBER_OF_PLAYERS];
		
		int e = 0;
		for(int i= 0; i < Game.NUMBER_OF_PLAYERS; i++) {
			for(int number = 1; number <= board.getSize() ; number++ )
				{numbers[e] = number;
				e++;}
			
		}
		return numbers;
	}
	
	private String simpleBoard(Board oldBoard){
		StringBuilder sb = new StringBuilder();
		int[] label = rowLabel(oldBoard);
		int e = -1;
		for(char[] row : oldBoard.getMinimalStatus()) {
				e += 1;
				sb.append(label[e] / 10 == 0 ? " " : "");
				sb.append(label[e] + "|");
				for(char c : row)
					sb.append(c + "|");
				sb.append("\n");
				
				
			
		}
		return sb.toString();
	}
	
	private String complexBoard(Board oldBoard){
		StringBuilder sb = new StringBuilder();
		
		int[] label = rowLabel(oldBoard);
		int e = -1;
		
		for(char[] row : oldBoard.getFullStatus()) {
			e += 1;
			sb.append(label[e] / 10 == 0 ? " " : "");
			sb.append(label[e] + "|");
			for(char c : row)
				sb.append(c + "|");
			sb.append("\n");
			
		
		}
		return sb.toString();
		
	}
	
	private String columnBoards(String leftB, String rightB) {
		String[] linesL = leftB.split("\n");
		String[] linesR = rightB.split("\n");
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < linesL.length ; i++) {
			sb.append(linesL[i] + "          " + linesR[i] + "\n");
		}
		return sb.toString();
	}
}