package uo.mp.battleship.model.board;

public class Coordinate {
		
	public final static char[] COLUMNS = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T'};
	private int col;
	private int row;
	/**
	 * It creates a new Coordinate object referring to column column and row row.
	 * 
	 * @param col
	 * @param row
	 */
	public Coordinate(int col, int row) {
		setCol(col);
		setRow(row);
	}

	/**
	 * It returns the column value.
	 * @return
	 */
	public int getCol() {
		return col;
	}
	
	private void setCol( int col) {
		//CHECK TODO
		this.col = col;
	}
	
	private void setRow( int row) {
		//CHECK TODO
		this.row = row;
	}

	/**
	 * It returns the row value.
	 * @return
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * It overrides the toString method and returns the coordinate’s values in the array:
	*Coordinate [ x = column, y = row ]
	 */
	@Override
	public String toString() {
		return "Coordinate [ x = %d , y = %d".formatted(getCol(), getRow());
	}

	/**
	 * It returns the coordinate’s values in a user-friendly format. Instead of
Coordinate [x = 2, y = 0] this method returns: C-1
	 * @return
	 */
	public String toUserString() {
		return "%c - %d".formatted(COLUMNS[getCol()], getRow()+1);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Coordinate))
			return false;
		Coordinate other = (Coordinate) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

}
