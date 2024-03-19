package uo.mp.battleship.model.game;

class TurnSelector {

	public final static int HUMAN = 1;
	public final static int COMPUTER = 0;
	
	private int prevTurn = 0;
	TurnSelector() {
	}
	
	int next() {
		if(prevTurn == COMPUTER)
		{
			prevTurn = HUMAN;
			
		}
		else {
			prevTurn = COMPUTER;
		}
		
		return prevTurn;
	}
	
	void repeat() {
		prevTurn = next();
	}
}

