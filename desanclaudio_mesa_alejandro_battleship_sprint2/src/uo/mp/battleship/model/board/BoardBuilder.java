package uo.mp.battleship.model.board;

import java.util.List;
import java.util.Random;

import uo.mp.battleship.model.board.squares.Ship;
import uo.mp.battleship.model.board.squares.Water;

class BoardBuilder {

	public final static int[][] prefixedBoard = new int[][] {	
		{ 1, 0, 1, 0, 1, 0, 1, 0, 0, 0 }, 
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
		{ 2, 2, 0, 2, 2, 0, 2, 2, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{ 3, 3, 3, 0, 3, 3, 3, 0, 0, 0},
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{ 0, 0, 0, 0, 4, 4, 4, 4, 0, 0},
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
 };
 static Square[][] build(int size, List<Ship> fleet) {
	    Square[][] squares = new Square[size][size];
	    Random random = new Random();

	    for (Ship ship : fleet) {
	        int shipSize = ship.size();
	        boolean isHorizontal = random.nextBoolean();
	        int row, col;

	        do {
	            row = isHorizontal ? random.nextInt(size) : random.nextInt(size - shipSize + 1);
	            col = isHorizontal ? random.nextInt(size - shipSize + 1) : random.nextInt(size);

	        } while (!isValidPosition(squares, row, col, shipSize, isHorizontal, size));

	        placeShip(squares, ship, row, col, shipSize, isHorizontal);
	    }

	    fillWithWater(squares, size);

	    return squares;
	}

	private static boolean isValidPosition(Square[][] squares, int row, int col, int shipSize, boolean isHorizontal, int size) {
	    for (int i = -1; i <= shipSize; i++) {
	        for (int j = -1; j <= 1; j++) {
	            int r = isHorizontal ? row + j : row + i;
	            int c = isHorizontal ? col + i : col + j;
	            if (r >= 0 && r < size && c >= 0 && c < size && squares[r][c] != null) {
	                return false;
	            }
	        }
	    }
	    return true;
	}

	private static void placeShip(Square[][] squares, Ship ship, int row, int col, int shipSize, boolean isHorizontal) {
	    for (int i = 0; i < shipSize; i++) {
	        int r = isHorizontal ? row : row + i;
	        int c = isHorizontal ? col + i : col;
	        squares[r][c] = new Square().setContent(ship);
	    }
	}

	private static void fillWithWater(Square[][] squares, int size) {
	    for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	            if (squares[i][j] == null) {
	                squares[i][j] = new Square().setContent(new Water());
	            }
	        }
	    }
	}

}