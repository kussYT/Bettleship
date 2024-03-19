package uo.mp.battleship.model.board;

import uo.mp.battleship.model.board.squares.Target;

public class Square {

	public enum Damage{
		NO_DAMAGE, SEVERE_DAMAGE, MASSIVE_DAMAGE
	};
	
	private Target content;
	private boolean shot;
	
	
	Damage shootAt() {
		shot = true;
		return content.shootAt();
	}
	
	boolean isShot () {
		return shot;
	}
	
	char toChar () {
		if(isShot())
			return content.toFiredChar();
		else
			return content.toChar();
	}
	
	Square setContent (Target target)
	{
		content = target;
		return this;
	}
	
	boolean hasContent () {
		return content != null;
	}

	Target getContent() {
		return content;
	}

}
