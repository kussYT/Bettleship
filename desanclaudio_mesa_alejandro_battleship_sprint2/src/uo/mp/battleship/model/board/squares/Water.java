package uo.mp.battleship.model.board.squares;

import uo.mp.battleship.model.board.Board;
import uo.mp.battleship.model.board.Square.Damage;

public class Water implements Target{

	
	public Damage shootAt() {
		return Damage.NO_DAMAGE;
	}

	
	public char toChar() {
		return Board.WATER_LETTER;
	}

	
	public char toFiredChar() {
		return Board.MISSED_LETTER;
	}

}
