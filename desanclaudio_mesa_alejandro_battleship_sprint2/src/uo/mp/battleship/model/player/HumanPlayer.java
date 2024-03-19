package uo.mp.battleship.model.player;

import uo.mp.battleship.interaction.ConsoleReader;
import uo.mp.battleship.model.board.Coordinate;

public class HumanPlayer extends Player{

	public HumanPlayer(String name) {
		super(name);
		if( name == null || name == "")
			setName("user");
	}

	
	public Coordinate makeChoice() {
		ConsoleReader read = new ConsoleReader();
		Coordinate coord;
		do{
			coord = read.readCoordinates();
		}while(!checkCoords(coord));
		
		lastCoords.add(coord);
		
		return coord;
		
	}
	
}
