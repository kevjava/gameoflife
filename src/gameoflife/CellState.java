package gameoflife;

public enum CellState {
	/**
	 * This cell is alive.
	 */
	ALIVE,
	/**
	 * This cell is dead and has three neighbors, and will come alive.
	 */
	SPAWNING,
	/**
	 * This cell has more than three neighbors, and is overcrowded.
	 */
	OVERCROWDED,
	/**
	 * This cell has less than two neighbors, and is lonely.
	 */
	LONELY,
	/**
	 * This cell contains no life.
	 */
	DEAD;
}
