package uo.mp.battleship.model.board.squares;

import uo.mp.battleship.model.board.Square.Damage;

public interface Target {

	Damage shootAt();

	char toChar();

	char toFiredChar();

}
