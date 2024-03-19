package uo.mp.battleship.model.board.squares;

import uo.mp.battleship.model.board.Board;
import uo.mp.battleship.model.board.Square.Damage;

public class Ship implements Target{

	private int size;
	private int shotCount = 0;
	private char symbol;
	
	
	void setSize(int size) {
		if(size > 0 && size < 5)
			this.size = size;
	}
	
	public Damage shootAt () {
		++shotCount;
		if(shotCount < size)
			return Damage.SEVERE_DAMAGE;
		else
			return Damage.MASSIVE_DAMAGE;
	}

	public int size () {
		return size;
	}
	
	public boolean isSunk () {
		if(shotCount >= size())
			return true;
		return false;
	}

	
	public char toChar () {
		if(size() == Board.SUBMARINE_VALUE)
			return Board.SUBMARINE_LETTER;
		
		if(size() == Board.DESTROYER_VALUE)
			return Board.DESTROYER_LETTER;
		
		if(size() == Board.CRUSIER_VALUE)
			return Board.CRUSIER_LETTER;
		
		
		else 
			return Board.BATTLESHIP_LETTER;
		
		
		
	}
	
	public char toFiredChar () {
		if(isSunk())
			return Board.SUNK_LETTER;
		return Board.HIT_LETTER;
	}
}
