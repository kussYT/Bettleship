package uo.mp.battleship.model.player;

import java.util.ArrayList;
import java.util.List;

import uo.mp.battleship.model.board.Board;
import uo.mp.battleship.model.board.Coordinate;
import uo.mp.battleship.model.board.Square.Damage;

public abstract class Player {

	private Board ownBoard;
	private Board opponentBoard;
	private String name;
	
	
	List<Coordinate> lastCoords = new ArrayList<>();
	
	Player(String name){
		setName(name);
	}
	
/**
 * Returns the name of the player.
 * @return
 */
	public String getName() {
		return name;
	}
	
	/**
	 * It sets my ships field to the argument.
	 * @param board
	 */
	public void setMyShips(Board board) {
		ownBoard = board;
	}
	
	/**
	 * Returns the board recording my ships.
	 * @return
	 */
	public Board getMyShips() {
		return ownBoard;
	}
	
	/**
	 * Returns the board object recording opponent’s fleet and my guesses.
	 * @return
	 */
	public Board getOpponentShips() {
		return opponentBoard;
	}
	
	/**
	 * It sets opponent’s ships field to the argument.
	 * @param board
	 */
	public void  setOpponentShips(Board board) {
		opponentBoard = board;
	}
	
	/**
	 * 
	 * @return
	 */
	public abstract Coordinate makeChoice();
	
	public Damage shootAt(Coordinate position) {
		return opponentBoard.shootAt(position);
	}
	
	/**
	 * Return true if the opponent’s fleet has been sunk; false, otherwise.
	 * @return
	 */
	public boolean hasWon() {
		return opponentBoard.isFleetSunk();
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	protected boolean checkCoords(Coordinate coord) {
		for (Coordinate c : lastCoords) 
			if(c.equals(coord))
				return false;
		return true;
	}
}
