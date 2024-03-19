package uo.mp.battleship.model.player;

import java.util.Random;

import uo.mp.battleship.model.board.Coordinate;

public class ComputerPlayer extends Player{

	
	

	public ComputerPlayer(String name) {
		super(name);
		if(name == null || name == "")
			setName("computer");
	}

	
	public Coordinate makeChoice() {

		Coordinate coord;
		Random ran = new Random();
		do {
		int row = ran.nextInt(getMyShips().getSize());
		int col = ran.nextInt(getMyShips().getSize());
		
		coord = new Coordinate(row, col);
		}
		while(!checkCoords(coord));
		
		lastCoords.add(coord);
		
		return coord;
	}

		
}
