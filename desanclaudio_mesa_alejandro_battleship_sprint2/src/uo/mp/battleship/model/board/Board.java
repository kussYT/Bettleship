package uo.mp.battleship.model.board;

import java.util.ArrayList;
import java.util.List;

import uo.mp.battleship.model.board.Square.Damage;
import uo.mp.battleship.model.board.squares.Battleship;
import uo.mp.battleship.model.board.squares.Crusier;
import uo.mp.battleship.model.board.squares.Destroyer;
import uo.mp.battleship.model.board.squares.Ship;
import uo.mp.battleship.model.board.squares.Submarine;

public class Board {

	public final static int BATTLESHIP_VALUE = 4;
	public final static char BATTLESHIP_LETTER = 'B';
	
	public final static int CRUSIER_VALUE = 3;
	public final static char CRUSIER_LETTER = 'C';
	
	public final static int DESTROYER_VALUE = 2;
	public final static char DESTROYER_LETTER = 'D';
	
	public final static int SUBMARINE_VALUE = 1;
	public final static char SUBMARINE_LETTER = 'S';

	public final static char HIT_LETTER = '*';
	
	public final static char SUNK_LETTER = '#';
	
	public final static int MISSED_VALUE = -10;
	public final static char MISSED_LETTER = 'Ø';
	
	public final static int WATER_VALUE = 0;
	public final static char WATER_LETTER = ' ';
	
	private int size;
	private Square[][] board;
	
	/**
	 *  Uses BoardBuilder to build a squared 2D array
	 * @param size
	 */
	public Board( int size ) {
		board = BoardBuilder.build(size, setFleet());
		setSize(size);
	}
	
	private List<Ship> setFleet() {
		List<Ship> fleet = new ArrayList<>();
		fleet.add(new Battleship());
		
		fleet.add(new Crusier());
		fleet.add(new Crusier());
		
		fleet.add(new Destroyer());
		fleet.add(new Destroyer());
		fleet.add(new Destroyer());
		
		fleet.add(new Submarine());
		fleet.add(new Submarine());
		fleet.add(new Submarine());
		fleet.add(new Submarine());
		
		return fleet;
	}
	Board (Square[][] arg)
	{
		board = arg;
	}

	private void setSize(int size) {
		if(size >= 10 && size <= 20)
			this.size = size;
		
	}

	/**
	 *  If there is a ship there, in any state, returns true. If the 
	*user shoots the same position twice, the application will always return true, and the content 
	*of that square will not change from the first to the second shot. If there is no ship there 
	*, it returns false and changes the content to -10. If the user shoots the same water cell 
	*twice, the application will always return false, and the content of that square will not change 
	*from the first to the second shot
	 * 
	 * @param position
	 * @return enum Damage
	 */
	public Damage shootAt (Coordinate position) {
		Square pos = board[position.getRow()][position.getCol()];
		
		return pos.shootAt();
	}	
	
	/**
	 *Checks whether all the ships in the fleet have been sunk, returning true if so; otherwise, 
	*returns false. 
	 * @return
	 */
	public boolean isFleetSunk() {
		for(int i = 0; i < board.length; i++) 
			for(int j = 0; j < board.length; j++)
				if(board[i][j].getContent().toFiredChar() != Board.SUNK_LETTER) 
						return false;
					
		
		return true;
		
	}
	
	/**
	 *  number or rows and columns in the 2D squared array
	 * @param position
	 * @return
	 */
	public int getSize() {
		return board.length;
	}

	/**
	 * returns a 2D array of characters representing the state of the board. A character at (x, y) 
coordinates represents the state of this square
	 * @return
	 */
	public char[][] getFullStatus() {
		char[][] newBoard = new char[size][size];
		
		for(int i = 0; i < board.length; i++) 
			for(int j = 0; j < board.length; j++) {
				newBoard[i][j] = board[i][j].toChar();
				}
		
		return newBoard;
	}

	/**
	 * returns a 2D array of characters. However, it just returns the actual value 
	*of those squares in the board that have already been shot. For those not guessed, it returns a 
	*blank character
	 * @return
	 */
	public char[][] getMinimalStatus() {
		char[][] newBoard = new char[size][size];
		
		for(int i = 0; i < board.length; i++) 
			for(int j = 0; j < board.length; j++) {
				if(board[i][j].isShot())
					newBoard[i][j] = board[i][j].toChar();
				else
					newBoard[i][j] = Board.WATER_LETTER;
			}
					
		return newBoard;
	}
	
	/**
	 * returns a copy of the inner array of integers
	 * @return
	 */
	Square[][] getInnerArray (){
		return board.clone();
	}
	 boolean isCrusier(int pos) {
		return pos == CRUSIER_VALUE;
	}
	
	 boolean isDestroyer(int pos) {
		return pos == DESTROYER_VALUE;
	}
	
	 boolean isSubmarine(int pos) {
		return pos == SUBMARINE_VALUE;
	}
	
	 boolean isMissed(int pos) {
		return pos == MISSED_VALUE;
	}
	
	boolean isBattleship(int pos) {
		return pos == BATTLESHIP_VALUE;
	}
	
	 boolean isWater(int pos) {
		return pos == WATER_VALUE;
	}

}
